package com.myVR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/19  20:04
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class VRMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_main_layout);
    }

    public void but1(View v){
        startActivity(new Intent(this,VrImageActivity.class));
    }

    public void but2(View v){
        startActivity(new Intent(this,VrVideoActivity.class));
    }
}
