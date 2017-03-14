package com.broadcast.local_broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ServiceCompat;
import android.support.v4.content.LocalBroadcastManager;

/**
 * 创建者：wanglei
 * <p>时间：16/8/15  20:04
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class LocalService  extends Service {
    LocalBroadcastManager mLocalBroadcastManager;
    int mCurUpdate;

    static final int MSG_UPDATE = 1;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE: {
                    mCurUpdate++;
                    Intent intent = new Intent(LocalBroadcastActivity.ACTION_UPDATE);
                    intent.putExtra("value", mCurUpdate);
                    mLocalBroadcastManager.sendBroadcast(intent);
                    Message nmsg = mHandler.obtainMessage(MSG_UPDATE);
                    mHandler.sendMessageDelayed(nmsg, 1000);
                }
                break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Tell any local interested parties about the start.
        mLocalBroadcastManager.sendBroadcast(new Intent(LocalBroadcastActivity.ACTION_STARTED));

        // Prepare to do update reports.
        mHandler.removeMessages(MSG_UPDATE);
        Message msg = mHandler.obtainMessage(MSG_UPDATE);
        mHandler.sendMessageDelayed(msg, 1000);
        return ServiceCompat.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Tell any local interested parties about the stop.
        mLocalBroadcastManager.sendBroadcast(new Intent(LocalBroadcastActivity.ACTION_STOPPED));

        // Stop doing updates.
        mHandler.removeMessages(MSG_UPDATE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
