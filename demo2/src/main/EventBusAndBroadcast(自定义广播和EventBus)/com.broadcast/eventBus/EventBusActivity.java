package com.broadcast.eventBus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myplayer.R;

import de.greenrobot.event.EventBus;

/**
 * 创建者：wanglei
 * <p>时间：16/8/4  09:57
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：compile 'de.greenrobot:eventbus:2.4.0'
 */
public class EventBusActivity extends Activity {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus_layout);

        EventBus.getDefault().register(this);//在要接收消息的页面注册
        tv = (TextView)findViewById(R.id.tv);
    }

    public void btn(View view){
        Intent intent = new Intent(getApplicationContext(),ReceiverEventBusActivity.class);
        startActivity(intent);
    }

    public void onEventMainThread(FirstEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("harvic", msg);
        tv.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
