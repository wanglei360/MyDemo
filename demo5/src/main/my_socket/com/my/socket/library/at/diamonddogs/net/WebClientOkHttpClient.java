/*
 * Copyright (C) 2012, 2013 the diamond:dogs|group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.my.socket.library.at.diamonddogs.net;

import android.content.Context;
import android.os.Environment;

import com.my.socket.library.at.diamonddogs.data.dataobjects.WebReply;
import com.my.socket.library.at.diamonddogs.exception.WebClientException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import com.my.socket.library.at.diamonddogs.data.adapter.ReplyAdapter;
import com.my.socket.library.at.diamonddogs.net.ssl.SSLHelper;
import com.my.socket.library.at.diamonddogs.util.Log;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WebClientOkHttpClient extends WebClient {

    private static final String TAG = WebClientOkHttpClient.class.getSimpleName();
    private int retryCount = 0;

    private OkHttpClient httpClient;
    private Request request;
    private Response response;

    /**
     * Constructs a {@link at.diamonddogs . net.WebClient}
     *
     * @param context a context
     */
    public WebClientOkHttpClient(Context context) {
        super(context);
        httpClient = new OkHttpClient();
    }

    @Override
    public ReplyAdapter call() {
        ReplyAdapter listenerReply;
        if (webRequest == null) {
            throw new WebClientException("WebRequest must not be null!");
        }
        retryCount = webRequest.getNumberOfRetries();
        do {
            try {
                retryCount--;
                WebReply reply;

                Request.Builder requestBuilder = new Request.Builder()
                        .url(webRequest.getUrl());

                configureConnection(requestBuilder);

                request = requestBuilder.build();
                response = httpClient.newCall(request).execute();
                reply = runRequest();

                if (needsFollowRedirect(reply)) {
                    String url = getRedirectUrl(reply);
                    Log.d(TAG, "following redirect manually to new url: " + url);
                    configureConnection(requestBuilder);

                    request = requestBuilder.url(new URL(url)).build();
                    response = httpClient.newCall(request).execute();
                    reply = runRequest();
                }

                listenerReply = createListenerReply(webRequest, reply, null, ReplyAdapter.Status.OK);
                int status = ((WebReply) listenerReply.getReply()).getHttpStatusCode();
                if (!(status == -1)) {
                    retryCount = -1;
                }
            } catch (Throwable tr) {
                if (retryCount != 0) {
                    try {
                        Thread.sleep(webRequest.getRetryInterval());
                    } catch (InterruptedException e) {
                        Log.e(TAG, "Error in WebRequest: " + webRequest, e);
                    }
                }
                listenerReply = createListenerReply(webRequest, null, tr, ReplyAdapter.Status.FAILED);
                Log.w(TAG, "Error running webrequest: " + webRequest.getUrl(), tr);
            }
        } while (retryCount >= 0);

        if (webClientReplyListener != null) {
            webClientReplyListener.onWebReply(this, listenerReply);
        }
        return listenerReply;
    }

    private String getRedirectUrl(WebReply wr) {
        return wr.getReplyHeader().get("location").get(0);
    }

    private boolean needsFollowRedirect(WebReply wr) {
        if (!followProtocolRedirect || !webRequest.isFollowRedirects()) {
            return false;
        }
        return wr.getHttpStatusCode() == HTTPStatus.HTTP_MOVED_TEMP
                || wr.getHttpStatusCode() == HTTPStatus.HTTP_MOVED_PERM;
    }

    private void configureConnection(Request.Builder requestBuilder) throws IOException {

        httpClient = httpClient.newBuilder()
                .connectTimeout(webRequest.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(webRequest.getConnectionTimeout(), TimeUnit.MILLISECONDS)
                .followRedirects(webRequest.isFollowRedirects()).build();


        setSslFactory();
        setRequestType(requestBuilder);
        buildHeader(requestBuilder);
    }

    private void setSslFactory() {
        SSLSocketFactory sslSocketFactory = SSLHelper.getInstance().SSL_FACTORY_JAVA;
        if (sslSocketFactory != null) {
            httpClient = httpClient.newBuilder().sslSocketFactory(sslSocketFactory).build();
        }
    }

    private void setRequestType(Request.Builder requestBuilder) throws IOException {
        switch (webRequest.getRequestType()) {
            case POST:
                requestBuilder.method("POST", getRequestBody());
                break;
            case GET:
                requestBuilder.method("GET", null);
                break;
            case HEAD:
                requestBuilder.method("HEAD", null);
                break;
        }
        request = requestBuilder.build();
    }

    private RequestBody getRequestBody() throws IOException {
        RequestBody rb = webRequest.getRequestBody();
        if (rb == null) {
            MediaType parse = MediaType.parse(webRequest.getHeader().get("Content-Type"));
            rb = RequestBody.create(parse, "");
        }
        return rb;
    }

    @Override
    protected void buildHeader(Request.Builder requestBuilder) {
        Map<String, String> header = webRequest.getHeader();
        if (header != null) {
            for (String field : header.keySet()) {
                if (webRequest.isAppendHeader()) {
                    requestBuilder.addHeader(field, header.get(field)).build();
                } else {
                    requestBuilder.header(field, header.get(field)).build();
                }
            }
        }
    }

    private WebReply runRequest() throws IOException {
        int statusCode = response.code();

        WebReply reply = null;
        switch (statusCode) {
            case HttpURLConnection.HTTP_PARTIAL:
            case HttpURLConnection.HTTP_OK:
                publishFileSize(response.body().contentLength());
                reply = handleResponseOk(response.body().byteStream(), statusCode, convertHeaders(response.headers()));
                break;
            case HttpURLConnection.HTTP_NOT_MODIFIED:
                Log.d(TAG, "WebRequest Not modified: " + webRequest);
                reply = handleResponseNotModified(statusCode, convertHeaders(response.headers()));
                break;
            case HttpURLConnection.HTTP_NO_CONTENT:
                reply = handleResponseOk(null, statusCode, convertHeaders(response.headers()));
            default:
                Log.d(TAG, "WebRequest DEFAULT: " + webRequest);
                ResponseBody rBody = response.body();
                if (rBody != null) {
                    InputStream content = rBody.byteStream();
                    writeErrorLog(content);
                    reply = handleResponseNotOk(content, statusCode, convertHeaders(response.headers()));
                } else {
                    reply = handleResponseNotOk(null, statusCode, convertHeaders(response.headers()));
                }
                break;
        }
        return reply;
    }

    private Map<String, List<String>> convertHeaders(Headers headers) {
        HashMap<String, List<String>> ret = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            String key = headers.name(i).toString();
            String value = headers.value(i).toString();
            if (ret.containsKey(key)) {
                ret.get(key).add(value);
            } else {
                List<String> values = new ArrayList<>(10);
                values.add(value);
                ret.put(key, values);
            }
        }
        return ret;
    }

    private void writeErrorLog(InputStream content) {
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "errorlog.txt");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream fos = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = content.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            Log.e(TAG, "error writing log", e);
        }
    }

}
