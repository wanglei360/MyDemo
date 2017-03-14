package com.fragment.wanglei.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.activity.TestActivity;
import com.fragment.wanglei.myapplication.base.BaseFragment;
import com.fragment.wanglei.myapplication.bean.DemoBean;
import com.fragment.wanglei.myapplication.utils.FileUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.io.File;

/**
 * 创建者：wanglei
 * 时间：16/3/10  10:59
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@ContentView(R.layout.myself_layout)
public class MySelfFragment extends BaseFragment {


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**TODO 序列化文件
     * 点击的方法必须私有(private)，否则无效
     */
    @Event(value = R.id.my_id, type = View.OnClickListener.class)
    private void but(View view) {
        DemoBean bean = new DemoBean();
        bean.setBb(true);
        bean.setIi(122223333);
        bean.setStr("we文件了");

        FileUtils.beanToFile(this.getActivity().getApplicationContext(),"bu_zhi_dao",bean);
        DemoBean bean1 = FileUtils.fileToBean(this.getActivity(), DemoBean.class,"bu_zhi_dao");
        Toast.makeText(this.getActivity(),bean1.getStr(),Toast.LENGTH_LONG).show();
        Toast.makeText(this.getActivity(),bean1.getIi()+"",Toast.LENGTH_LONG).show();
        Toast.makeText(this.getActivity(),bean1.isBb()+"",Toast.LENGTH_LONG).show();
    }
    @Event(value = R.id.but_2, type = View.OnClickListener.class)
    private void but1(View view) {
        Intent intent = new Intent(this.getActivity(), TestActivity.class);
        startActivity(intent);
    }
}
