package www.yj.com.lineandpieicon;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * 创建者：admin
 * <p>时间：2017/3/13 12:58
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class jianjie extends Activity{

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianjie);

        webView = (WebView) findViewById(R.id.webview);

    }

    @Override
    protected void onStart() {
        super.onStart();
        webView.getSettings().setJavaScriptEnabled(false);
        //找到Html文件，也可以用网络上的文件
        webView.getSettings().setDefaultTextEncodingName("utf-8") ;
        webView.loadUrl("file:///android_asset/agreement.html");
    }

}
