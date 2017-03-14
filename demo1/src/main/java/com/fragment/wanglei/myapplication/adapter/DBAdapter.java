package com.fragment.wanglei.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.activity.DBActivity;
import com.fragment.wanglei.myapplication.db.bean.Biao_1_bean;

import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/21  13:48
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DBAdapter extends BaseAdapter {

    DBActivity dbActivity;

    public void setMyBeen(List<Biao_1_bean> myBeen) {
        this.myBeen = myBeen;
    }

    List<Biao_1_bean> myBeen;
    public DBAdapter(DBActivity dbActivity, List<Biao_1_bean> myBeen) {
        this.dbActivity = dbActivity;
        this.myBeen = myBeen;
    }

    @Override
    public int getCount() {
        return myBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return myBeen.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(dbActivity, R.layout.item_db, null);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        Biao_1_bean myBean = myBeen.get(position);
        text1.setText(myBean.getText());
        text2.setText(String.valueOf(myBean.getComment()));
        return view;
    }
}
