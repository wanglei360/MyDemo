package webView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.myplayer.R;


/**
 * 创建者：wanglei
 * <p>时间：16/6/17  10:02
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class WebViewDemo extends Activity {
    private static final String LOG_TAG = "WebViewDemo";
    private WebView mWebView;
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.web_view_layout);
//        findViewById(R.id.d)
        mWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
        mWebView.loadUrl("file:///android_asset/demo.html");
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    final class DemoJavaScriptInterface {
        DemoJavaScriptInterface() {
            Log.i("aaaa", "create DemoJavaScriptInterface>>>>>>>>>>>>>>>>>");
        }

        /**
         * Runnable是在主（UI）线程
         */
        @JavascriptInterface
        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    Toast.makeText(WebViewDemo.this,"点击事件调用成功",Toast.LENGTH_SHORT).show();
                    /**
                     * 这个wave()的方法是跟html中的想对应的，这里走完走的是class MyWebChromeClient
                     */
                    mWebView.loadUrl("javascript:wave()");
                }
            });
        }
        @JavascriptInterface
        public void clickOnAndroid_1() {
            mHandler.post(new Runnable() {
                public void run() {
                    Toast.makeText(WebViewDemo.this,"点击事件1111调用成功",Toast.LENGTH_SHORT).show();
                    /**
                     * 这个wave()的方法是跟html中的想对应的，这里走完走的是class MyWebChromeClient
                     */
                    mWebView.loadUrl("javascript:wave()");
                }
            });
        }
        @JavascriptInterface
        public void clickOnAndroid_2() {
            mHandler.post(new Runnable() {
                public void run() {
                    Toast.makeText(WebViewDemo.this,"点击事件2222调用成功",Toast.LENGTH_SHORT).show();
                    /**
                     * 这个wave()的方法是跟html中的想对应的，这里走完走的是class MyWebChromeClient
                     */
                    mWebView.loadUrl("javascript:wave()");
                }
            });
        }
    }

    /**
     * 提供了一个钩子的电话“警报”从JavaScript。有用的调试JavaScript。
     */
    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
