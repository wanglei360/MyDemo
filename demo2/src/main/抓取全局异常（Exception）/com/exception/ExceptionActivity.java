package com.exception;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.myplayer.R;

import java.util.Date;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  14:31
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ExceptionActivity extends Activity {private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exception_layout);
        tv = (TextView) findViewById(R.id.tv);
        getA();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void getA() {
//        try {
            new WWWRE().ee("1");
//        } catch (Exception ex) {
//            System.out.println(new Date() + ":" + ex.toString());
//            StackTraceElement[] st = ex.getStackTrace();
//            for (StackTraceElement stackTraceElement : st) {
//                String exclass = stackTraceElement.getClassName();
//                String method = stackTraceElement.getMethodName();
//                if(exclass.contains("com.exception")){
////                    System.out.println(new Date() + ":" + "[类:" + exclass + "]调用"
////                            + method + "时在第" + stackTraceElement.getLineNumber()
////                            + "行代码处发生异常!异常类型:" + ex.getClass().getName());
////                    System.out.println(exclass + "  " + method+"方法" + "  在第"+stackTraceElement.getLineNumber()
////                            +"行  "+ex.getClass().getName());
//                    Log.e("MyException",new Date() + ":" + "[类:" + exclass + "]调用"
//                            + method + "时在第" + stackTraceElement.getLineNumber()
//                            + "行代码处发生异常!异常类型:" + ex.getClass().getName()+exclass + "  " + method+"方法" + "  在第"+stackTraceElement.getLineNumber()
//                            +"行  "+ex.getClass().getName());
//                }
////                System.out.println(stackTraceElement.toString());
//            }
//        }
    }
}
