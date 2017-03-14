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
package com.my.socket.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.demo5.R;
import com.my.socket.dataobject.Weather;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest.Type;
import com.my.socket.library.at.diamonddogs.service.net.HttpService;
import com.my.socket.library.at.diamonddogs.service.net.HttpService.HttpServiceBinder;
import com.my.socket.library.at.diamonddogs.service.processor.HeadRequestProcessor;
import com.my.socket.library.at.diamonddogs.service.processor.ServiceProcessorMessageUtil;
import com.my.socket.processor.WeatherProcessor;

import java.util.List;
import java.util.Map;

/**
 * Basic HTTP request example
 */
public class HttpExampleActivity extends Activity {

    /**
     * The service connection that will be used to connect with
     * 服务连接将用于连接
     * {@link HttpService}
     */
    private HttpExampleConnection serviceConnection;

    /**
     * An instance of {@link HttpService} that can be used to run web requests
     */
    private HttpService httpService;

    /**
     * Text view to display a weather string
     */
    private TextView text;

    /**
     * Text view to display the temperature
     */
    private TextView temperature;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpexampleactivity);
        text = (TextView) findViewById(R.id.httpexampleactivity_text);
        temperature = (TextView) findViewById(R.id.httpexampleactivity_temperature);
    }

    /**
     * Binds the {@link HttpService} if the {@link HttpExampleConnection} is
     * null
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (serviceConnection == null) {
            Intent i = new Intent(this, HttpService.class);
            bindService(i, serviceConnection = new HttpExampleConnection(), BIND_AUTO_CREATE);
        }
    }

    /**
     * Unbinds the {@link HttpService} if the {@link HttpExampleConnection} is
     * not null
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }

    /**
     * 构建和运行天气网请求
     */
    @SuppressWarnings("unchecked")
    private void runWebRequest() {
        String weatherUrl = getWeatherUrl("Austria", "Vienna");
        // --- SYNC WEB REQUEST
        // --- 同步Web请求

        // sync webrequest POC, usually you should not execute synchronous web
        // requests on the main thread. The result of this call will be logged,
        // but not displayed in the UI
        //同步WebRequest POC，通常你不应该执行的同步网络
//		主线程上的请求。此调用的结果将被记录，
//		但不显示在用户界面中
        WebRequest syncWebRequest = new WebRequest();
        syncWebRequest.setUrl(weatherUrl);
        syncWebRequest.setRequestType(Type.HEAD);

        // default header request processor
        // 默认的头请求处理器
        syncWebRequest.setProcessorId(HeadRequestProcessor.ID);

        Map<String, List<String>> headers = (Map<String, List<String>>) httpService.runSynchronousWebRequest(syncWebRequest).getPayload();
        if (headers != null) {
            for (String key : headers.keySet()) {
                for (String value : headers.get(key)) {
                }
            }
        } else {
//			Toast.makeText(this, "Error while optaining headers", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "而optaining标题错误", Toast.LENGTH_SHORT).show();
        }

        // --- ASYNC WEB REQUEST
        // --- 异步Web请求

        WebRequest asyncRequest = new WebRequest();
        // takes a String or URL object!
        // 需要一个字符串或网址对象！
        asyncRequest.setUrl(weatherUrl);

        // The processorid tells HttpService what to do once a web reply has
        // been received.
        // You MUST set a processor id and the processor needs to be registered
        // with HttpService.
        // You may use DummyProcessor.ID to circumvent processor implementation
        // processorid告诉服务做什么当一个Web的回答已收到。
        // 必须设置一个处理器的身份证和处理器需要注册服务。
        //你可以用dummyprocessor.id绕过处理器的实现
        asyncRequest.setProcessorId(WeatherProcessor.ID);

        // run the web request, WeatherHandler will receive a callback once the
        // web request has been finished
//        运行Web请求，WeatherHandler将接受一个回调一旦已完成Web请求
        httpService.runWebRequest(new WeatherHandler(), asyncRequest);
    }

    /**
     * Formats the openweathermap.org weather URL
     * 天气openweathermap.org the URL格式
     *
     * @param country the country
     * @param city    the city
     * @return the weather url for country & city
     */
    private String getWeatherUrl(String country, String city) {
        Uri u = Uri.parse("http://api.openweathermap.org/data/2.5/weather/");
        // @formatter:off
        u = u.buildUpon()
                .appendQueryParameter("q", city + "," + country)
                .appendQueryParameter("units", "metric")
                .build();
        // @formatter:on
        return u.toString();
    }

    /**
     * A Simple {@link ServiceConnection} to be used in conjunction with
     * 一个简单的与使用相结合
     * {@link HttpService}
     */
    private final class HttpExampleConnection implements ServiceConnection {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof HttpServiceBinder) {
                // gaining access to the HttpService
                httpService = ((HttpServiceBinder) service).getHttpService();

                // registering the WeatherProcessor that will take care of
                // parsing the XML and returning a POJO containing weather information
//                注册WeatherProcessor会照顾解析XML并返回一个POJO包含天气信息
                if (!httpService.isProcessorRegistered(WeatherProcessor.ID)) {
                    httpService.registerProcessor(new WeatherProcessor());
                }

//              registering the DummyProcessor, we need this processor for our HEAD request
//              注册dummyprocessor，我们需要这样的处理器 我们的头要求
                if (!httpService.isProcessorRegistered(HeadRequestProcessor.ID)) {
                    httpService.registerProcessor(new HeadRequestProcessor());
                }

//                 run the webrequest once the processor has been registered
//                运行WebRequest一旦处理器已经注册
                runWebRequest();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

    }

    /**
     * This handler receives a callback once the web request has been processed.
     * 该处理程序在Web请求被处理后收到一个回调。
     */
    private class WeatherHandler extends Handler {
        /**
         * {@inheritDoc}
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (ServiceProcessorMessageUtil.isFromProcessor(msg, WeatherProcessor.ID)) {
                if (ServiceProcessorMessageUtil.isSuccessful(msg)) {
                    Weather w = (Weather) msg.obj;
                    text.setText(w.getText());
                    temperature.setText(String.valueOf(w.getTemperature()));

                    // getting the http status code
//                    获取HTTP状态码
                } else {
//                    Toast.makeText(HttpExampleActivity.this, "Error fetching weather", Toast.LENGTH_LONG).show();
                    Toast.makeText(HttpExampleActivity.this, "获取错误的天气", Toast.LENGTH_LONG).show();
                }
            }

        }
    }
}
