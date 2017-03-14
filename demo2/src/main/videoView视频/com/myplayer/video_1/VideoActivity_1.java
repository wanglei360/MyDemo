package com.myplayer.video_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/5  14:17
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>备注：去掉<item name="android:windowIsTranslucent">true</item>视频就不透明了
 * <p>备注：
 */
public class VideoActivity_1 extends Activity {

    private Video video;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//todo 无标题栏设置 必须在setContentView前面，否则异常
        setContentView(R.layout.video_1_layout);
        video = (Video) findViewById(R.id.video);
        uri = "http://i.tbzbimg.cn/test/amp4.mp4";
        video.setPath(uri);

        //todo 全屏设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    public void but_1(View view) {
        startActivity(new Intent(this, VideoActivity_B.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (video != null)//SurfaceView会透明，漏出下面的activity，所以当前获取焦点时就显示这个ImageView
            video.showackground();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (video != null)
            video.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (video != null)
            video.stop();
    }
}
