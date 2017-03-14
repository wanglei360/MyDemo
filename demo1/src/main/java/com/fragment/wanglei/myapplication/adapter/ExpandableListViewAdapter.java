package com.fragment.wanglei.myapplication.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fragment.wanglei.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建者：wanglei
 * 时间：16/4/12  17:26
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    List<String> list1;
    List<String> list2;
    Activity activity;
    public int newGroupPosition=-1;
    private final int DEFAULTITEMNUM = 1;
    public boolean isClick;
    Map<Integer,Integer> map ;

    public ExpandableListViewAdapter(Activity activity, List<String> list1, List<String> list2) {
        this.list1 = list1;
        this.list2 = list2;
        this.activity = activity;
        map = new HashMap<Integer,Integer>();
    }
    /**
     * 父条目的数量
     */
    @Override
    public int getGroupCount() {
        return list1.size();
    }
    /**
     * 子条目的数量
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if(!isClick){
            map.put(groupPosition,DEFAULTITEMNUM);
            return DEFAULTITEMNUM;
        }else{
            if(groupPosition == newGroupPosition){
                if (map.get(groupPosition) == DEFAULTITEMNUM) {
                    map.put(groupPosition,list2.size());
                    return list2.size();
                }else{
                    map.put(groupPosition,DEFAULTITEMNUM);
                    return DEFAULTITEMNUM;
                }
            }else {
                return map.get(groupPosition);
            }
        }
    }

    public void setGroupPosition(int newGroupPosition) {
        this.isClick = true;
        this.newGroupPosition = newGroupPosition;
        notifyDataSetChanged();
    }

    /**
     * 父条目的布局
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.item_discover_group, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_discover_group);
        tv.setText(list1.get(groupPosition));
        return view;
    }

    /**
     * 子条目的布局
     */
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.item_discover_child, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_discover_child);
        TextView tv1 = (TextView) view.findViewById(R.id.tv_discover_child_1);
        tv.setText(list2.get(childPosition));
        tv1.setText(list2.get(childPosition));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"0位置的 父 = "+groupPosition+"  子 = "+childPosition,Toast.LENGTH_LONG).show();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"1位置的 父 = "+groupPosition+"  子 = "+childPosition,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    /**
     * 子条目的位置
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupPosition;
    }

    /**
     * 把返回0改成了groupPosition，这样删除条目时子条目的数据错乱就解决了
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 这个父条目中有几个子条目 groupPosition 父条目索引 childPosition 子条目索引
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
