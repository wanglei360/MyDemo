package com.fragment.wanglei.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fragment.wanglei.myapplication.MyApplication;

/**
 * 创建者：wanglei
 * <p>时间：16/4/27  12:43
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class UIUtils {
    /**
     * 全局上下文对象
     *
     * @return 上下文
     */
    public static Context getContext() {
        return MyApplication.getApplication();
    }
//————————————————————————————————————————————————————————————————————————————————————————————

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        // 获得主线程的looper
        Looper mainLooper = MyApplication.getMainThreadLooper();
        // 获取主线程的handler
        Handler handler = new Handler(mainLooper);
        return handler;
    }

    /**
     * 是否是主线程
     * @return
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 是否是主线程ID
     * @return
     */
    public static long getMainThreadId() {
        return MyApplication.getMainThreadId();
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 延时在主线程执行runnable
     * @param runnable  可以new Runnable
     * @param delayMillis   毫秒值
     * @return
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 多线程执行任务，相当于子线程中开队列
     * @param runTask 执行的任务
     */
    public static void startTaskInThreadPool(Runnable runTask){
        ThreadManager.getInstance().createHelper().execute(runTask);
    }
//————————————————————————————————————————————————————————————————————————————————————————————
    /**
     * 获取屏幕的高，px值
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高，px值
     * @param context
     * @return
     */
    public static int getWindowHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
//————————————————————————————————————————————————————————————————————————————————————————————
    /**
     * 主线程显示Toast
     * @param str
     */
    private static void toast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程显示Toast
     * @param activity  activity
     * @param text  要显示的内容
     */
    public static void toastSonThread(final Activity activity, final String text) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final Object str) {
        if (isRunInMainThread()) {
            toast(str.toString());
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    toast(str.toString());
                }
            });
        }
    }
//————————————————————————————————————————————————————————————————————————————————————————————
    /**
     * 隐藏小键盘的方法
     */
    public static void goneKeyboard(Context activity, View editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);// 控制小键盘的那个类
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); // 强制隐藏键盘
    }
}
