package com.fragment.wanglei.myapplication.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fragment.wanglei.myapplication.fragment.util.ViewUtils;

import org.xutils.x;

/**
 * 创建者：wanglei
 * 时间：16/3/22  14:42
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseFragment extends Fragment {

    private boolean injected = false;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        if (null != view) {//这个判断能解决getActivity为null的问题
            ViewUtils.removeParent(view);
        } else {
            view = x.view().inject(this, inflater, container);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    public void setListShowAppointItem(int position) {

    }
}
