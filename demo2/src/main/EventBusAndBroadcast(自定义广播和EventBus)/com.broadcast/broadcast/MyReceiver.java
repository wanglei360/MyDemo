package com.broadcast.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*  广播接收器 ，响应发送广播的操作  *//* 接受广播 */
public class MyReceiver extends BroadcastReceiver {

    /* 覆写该方法，对广播事件执行响应的动作  */
    public void onReceive(Context context, Intent intent) {

      /* 获取Intent对象中的数据 */
        String msg = intent.getStringExtra("msg");

      /*  */
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
