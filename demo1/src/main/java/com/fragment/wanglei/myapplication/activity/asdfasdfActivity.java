package com.fragment.wanglei.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.fragment.wanglei.myapplication.utils.UIUtils;

/**
 * 创建者：wanglei
 * <p>时间：16/8/10  18:19
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class asdfasdfActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIUtils.getHandler().post(new Runnable() {
            @Override
            public void run() {

            }
        });


    }
}
