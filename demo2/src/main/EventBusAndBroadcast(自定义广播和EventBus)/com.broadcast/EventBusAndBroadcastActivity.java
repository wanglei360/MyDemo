package com.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.broadcast.broadcast.BroadcastActivity;
import com.broadcast.eventBus.EventBusActivity;
import com.broadcast.local_broadcast.LocalBroadcastActivity;
import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/4  10:05
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class EventBusAndBroadcastActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_and_broadcast_layout);
    }

    public void but_onClik_1(View view){
        gotoActivity(BroadcastActivity.class);
    }

    public void but_onClik_2(View view){
        gotoActivity(EventBusActivity.class);
    }
    public void but_onClik_3(View view){
        gotoActivity(LocalBroadcastActivity.class);
    }

    private void gotoActivity(Class<?> cls){
        startActivity(new Intent(this,cls));
    }
}
