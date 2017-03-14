package com.fragment.wanglei.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;

import com.fragment.wanglei.myapplication.R;


public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        TextView textView = (TextView) findViewById(R.id.test_text_view);

//        TestDataModel.getInstance().setRetainedTextView(textView);
        isInMainThread();

        new Thread() {
            @Override
            public void run() {
                isInMainThread();
            }
        }.start();
    }

    private boolean isInMainThread() {
        Looper myLooper = Looper.myLooper();
        Looper mainLooper = Looper.getMainLooper();
//        Log.i(LOGTAG, "isInMainThread myLooper=" + myLooper
//                + ";mainLooper=" + mainLooper);
        System.out.println("<<<<<<<<<<<<    " + "isInMainThread myLooper=" + myLooper
                + ";mainLooper=" + mainLooper);
        return myLooper == mainLooper;
    }
}