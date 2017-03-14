package com.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myplayer.R;
import com.recyclerview.android_native.RecyclerViewNativeMainActivity;
import com.recyclerview.plugins.activity.RecyclerViewPluginsMainActivity;

/**
 * 创建者：wanglei
 * <p>时间：16/8/17  18:14
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class RecyclerViewMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_main_layout);
    }

    public void recyclerViewNativeMainClick(View v) {
        startActivity(new Intent(this, RecyclerViewNativeMainActivity.class));
    }

    public void recyclerViewPluginsMainClick(View v) {
        startActivity(new Intent(this, RecyclerViewPluginsMainActivity.class));
    }
}
