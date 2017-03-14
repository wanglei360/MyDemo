package com.exception;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.Date;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  14:33
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyApplication  extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler()); // 解除这个注释，闪退从新开始

    }

    private class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread thread, Throwable ex) {
            // 先从启本应用
            PackageManager pm = getPackageManager();// "com.edu.suzhiyun"
            Intent intent = pm.getLaunchIntentForPackage(getPackageName());
            startActivity(intent);
//            // 自杀
            android.os.Process.killProcess(android.os.Process.myPid());// 这里是杀死了本进程，之后的代码都不会执行了
            System.exit(0);
            // System.out.println("hahah");

            System.out.println(new Date() + ":" + ex.toString());
            StackTraceElement[] st = ex.getStackTrace();
            for (StackTraceElement stackTraceElement : st) {
                String exclass = stackTraceElement.getClassName();
                String method = stackTraceElement.getMethodName();
//                System.out.println(new Date() + ":" + "[类:" + exclass + "]调用"
//                        + method + "时在第" + stackTraceElement.getLineNumber()
//                        + "行代码处发生异常!异常类型:" + ex.getClass().getName());
//                System.out.println(exclass + "  " + method+"方法" + "  在第"+stackTraceElement.getLineNumber()
//                        +"行  "+ex.getClass().getName());
//                System.out.println(stackTraceElement.toString());
                Log.e("MyException",new Date() + ":" + "[类:" + exclass + "]调用"
                        + method + "时在第" + stackTraceElement.getLineNumber()
                        + "行代码处发生异常!异常类型:" + ex.getClass().getName()+exclass + "  " + method+"方法" + "  在第"+stackTraceElement.getLineNumber()
                        +"行  "+ex.getClass().getName());
            }
        }
    }
}
