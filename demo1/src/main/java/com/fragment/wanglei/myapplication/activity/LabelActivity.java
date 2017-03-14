package com.fragment.wanglei.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.addlabel_library.AutoLabelUI;
import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseActivity;

/**
 * 创建者：wanglei
 * <p>时间：16/4/29  11:17
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class LabelActivity  extends BaseActivity {

    private EditText et;
    private AutoLabelUI autoLabelUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.label_layout);
        et = (EditText) findViewById(R.id.et);
        autoLabelUI = (AutoLabelUI) findViewById(R.id.autoLabelUI);
    }

    public void but(View view){
        String s = et.getText().toString();
        autoLabelUI.addLabel(s);
    }
}
