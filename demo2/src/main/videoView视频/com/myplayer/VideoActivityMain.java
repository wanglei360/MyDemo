package com.myplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myplayer.video_1.VideoActivity_1;
import com.myplayer.video_2.VideoActivity;

/**
 * 创建者：wanglei
 * <p>时间：16/8/5  14:22
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class VideoActivityMain extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity_main_layout);
    }
    public void but1(View view){
        startActivity(new Intent(this, VideoActivity_1.class));
    }
    public void but2(View view){
        startActivity(new Intent(this, VideoActivity.class));
    }
}
