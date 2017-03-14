package com.broadcast.eventBus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myplayer.R;

import de.greenrobot.event.EventBus;

/**
 * 创建者：wanglei
 * <p>时间：16/8/4  09:59
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ReceiverEventBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver_event_bus_layout);
    }
    public void btu(View view){
        EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));
    }
}
