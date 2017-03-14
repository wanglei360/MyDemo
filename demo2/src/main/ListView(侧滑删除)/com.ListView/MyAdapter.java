package com.ListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplayer.R;

import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/6/3  15:22
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    List<String> list;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
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
        View inflate = View.inflate(context, R.layout.swipe_menu_listview_item, null);
        TextView tv = (TextView) inflate.findViewById(R.id.myTv);
        tv.setText(list.get(position));
        return inflate;
    }
}
