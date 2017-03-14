package com.broadcast.local_broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/15  20:02
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>备注：在android-support-v4.jar中引入了LocalBroadcastManager，称为局部通知管理器，这种通知的好处是安全性高，效率也高，
 * <p>      适合局部通信，可以用来代替Handler更新UI模块间有消息需要传递时，使用LocalBroadcastManager替代Listener进行模块解耦。
 * <p>      除了解耦，这样发送消息和执行消息差一个线程循环，可以减小方法的调用链
 */
public class LocalBroadcastActivity extends Activity implements OnClickListener {
    static final String ACTION_STARTED = "com.example.android.supportv4.STARTED";
    static final String ACTION_UPDATE = "com.example.android.supportv4.UPDATE";
    static final String ACTION_STOPPED = "com.example.android.supportv4.STOPPED";

    LocalBroadcastManager mLocalBroadcastManager;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_broadcast_layout);

        ((TextView) findViewById(R.id.broadcast_explain)).setText("在android-support-v4.jar中引入了LocalBroadcastManager，称为局部通知管理器，这种通知的好处是安全性高，效率也高，适合局部通信，可以用来代替Handler更新UI\n         模块间有消息需要传递时，使用LocalBroadcastManager替代Listener进行模块解耦。除了解耦，这样发送消息和执行消息差一个线程循环，可以减小方法的调用链");
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);

        final TextView callbackData = (TextView) findViewById(R.id.callback);
        callbackData.setText("No broadcast received yet");
        initBroadcast(callbackData);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                startService(new Intent(LocalBroadcastActivity.this, LocalService.class));
                break;
            case R.id.stop:
                stopService(new Intent(LocalBroadcastActivity.this, LocalService.class));
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }

    private void initBroadcast(final TextView callbackData) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_STARTED);
        filter.addAction(ACTION_UPDATE);
        filter.addAction(ACTION_STOPPED);

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()){
                    case ACTION_STARTED:
                        callbackData.setText("STARTED");
                        break;
                    case ACTION_UPDATE:
                        callbackData.setText("Got update: " + intent.getIntExtra("value", 0));
                        break;
                    case ACTION_STOPPED:
                        callbackData.setText("STOPPED");
                        break;
                }
            }
        };
        mLocalBroadcastManager.registerReceiver(mReceiver, filter);
    }
}
