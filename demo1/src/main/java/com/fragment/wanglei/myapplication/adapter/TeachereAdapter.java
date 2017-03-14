package com.fragment.wanglei.myapplication.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fragment.wanglei.myapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 创建者：wanglei
 * 时间：16/4/18  13:49
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TeachereAdapter extends BaseAdapter {
    private FragmentActivity activity;
    private ArrayList<String> list;

    public TeachereAdapter(FragmentActivity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.item_teachere, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));

        return convertView;
    }

    public class ViewHolder {
        @ViewInject(R.id.item_teachere_tv)
        private TextView tv;
    }
}
