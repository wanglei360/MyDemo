package com.fragment.wanglei.myapplication.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.activity.DBActivity;
import com.fragment.wanglei.myapplication.activity.LabelActivity;
import com.fragment.wanglei.myapplication.adapter.TeachereAdapter;
import com.fragment.wanglei.myapplication.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;


/**
 * 创建者：wanglei
 * 时间：16/3/10  10:59
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@ContentView(R.layout.teachere_layout)
public class TeachereFragment extends BaseFragment {

    @ViewInject(R.id.teachere_lv)
    private ListView lv;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> list = new ArrayList<>();
        list.add("进入new数据库Activity");
        list.add("进入labelActivity");
        lv.setAdapter(new TeachereAdapter(this.getActivity(), list));

    }

    /**
     * 必须私有，否则无效
     */
    @Event(value = R.id.teachere_lv, type = AdapterView.OnItemClickListener.class)
    private void onImageItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                goDBActivity(DBActivity.class);
                break;
            case 1:
                goDBActivity(LabelActivity.class);
                break;
        }
    }

    private void goDBActivity(Class<?> cls) {
        startActivity(new Intent(this.getActivity(), cls));
    }

}
