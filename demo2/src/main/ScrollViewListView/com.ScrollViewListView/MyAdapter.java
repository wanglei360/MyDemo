package com.ScrollViewListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplayer.R;

import java.util.ArrayList;

/**
 * 创建者：wanglei
 * <p>时间：16/7/23  11:27
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyAdapter extends BaseAdapter {
    Activity mainActivity;
    ArrayList<Integer> list;

    public MyAdapter(Activity mainActivity, ArrayList<Integer> list) {
        this.mainActivity = mainActivity;
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
        View view = View.inflate(mainActivity, R.layout.scroll_view_listview_item, null);
        TextView tv = (TextView) view.findViewById(R.id.item_tv);
        tv.setText(list.get(position)+"");
        return view;
    }
}
