package com.broadcast.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/8/4  10:02
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class BroadcastActivity extends Activity {

    private final String MY_ACTION = "android.com.example.broadcastreceiver.action.MYACTION";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_layout);

//        registerBroadcast();
    }

    /**
     * 清单文件注册的另一种注册方式
     */
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter();//生成一个IntentFilter对象
        filter.addAction(MY_ACTION);//为IntentFilter添加一个Action
        registerReceiver(new MyReceiver(),filter);
    }

    public void tv(View view){
        Intent intent = new Intent();

        /*  设置Intent对象的action属性  */
        intent.setAction(MY_ACTION);

        /* 为Intent对象添加附加信息 */
        intent.putExtra("msg", "发送广播测试成功.....");

        /* 发布广播 */
        sendBroadcast(intent);
    }

}