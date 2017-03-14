package com.fragment.wanglei.myapplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import com.fragment.wanglei.myapplication.db.communal.DaoMaster;
import com.fragment.wanglei.myapplication.db.communal.Session;
import com.fragment.wanglei.myapplication.utils.LogUtils;
//import com.squareup.leakcanary.LeakCanary;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by wyouflf on 15/10/28.
 */
public class MyApplication extends Application {

    public static Session daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        GlobalParams.applicationContext = this;
        //TODO Log输出级别 上线需要修改
        GlobalParams.Debuggable = LogUtils.LEVEL_ERROR;
        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();
        mInstance = this;

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        getDaoSession();
//        LeakCanary.install(this);
    }

    /**
     * 初始化数据库
     */
    public void getDaoSession() {
        SQLiteDatabase db = new DaoMaster.DevOpenHelper(this.getApplicationContext(), Constants.DBNAME, null).getWritableDatabase();//获取可写的数据库
        DaoMaster daoMaster = new DaoMaster(db);//在数据库中建表
        if(daoSession == null)
            daoSession = daoMaster.newSession();
    } /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    protected static Context mInstance;
    /**
     * 主线程ID
     */
    protected static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    protected static Thread mMainThread;
    /**
     * 主线程Handler
     */
    protected static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    protected static Looper mMainLooper;

    public static MyApplication getInstance() {
        return (MyApplication) mInstance;
    }

    /**
     * 获取应用的上下文
     *
     * @return 上下文
     */
    public static Context getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looperf
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }
}
