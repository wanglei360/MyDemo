package com.rtmp.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplayer.R;
import com.rtmp.bean.RtmpBean;

import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/9/1  19:19
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class RTMPAdapter extends BaseAdapter {

    Activity Activity;
    List<RtmpBean> list;
    public RTMPAdapter(Activity Activity, List<RtmpBean> list) {
    this.Activity = Activity;
    this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView tv = (TextView) View.inflate(Activity, R.layout.rtmp_item, null);
        tv.setText(list.get(i).name);
        return tv;
    }
}
