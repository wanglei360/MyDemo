package com.fragment.wanglei.myapplication.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.adapter.ExpandableListViewAdapter;
import com.fragment.wanglei.myapplication.base.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/3/10  10:59
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@ContentView(R.layout.discover_layout)
public class DiscoverFragment extends BaseFragment {


    @ViewInject(R.id.my_lv)
    private ExpandableListView elv;
    private FragmentActivity activity;
    private List<String> list1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        List<String> list = initInfo();

        activity = getActivity();


        list1 = new ArrayList<>();
        for (int x = 0; x < 20; x++) {
            list1.add("父 条 目" + String.valueOf(x));
        }
        List<String> list2 = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            list2.add("子 条 目" + String.valueOf(x));
        }
        elv.setSelector(new ColorDrawable(Color.TRANSPARENT));//本条目点击是的颜色
        elv.setGroupIndicator(null); // 去掉ExpandableListAdapter左边的箭头
        final ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(activity, list1, list2);
        elv.setAdapter(adapter);
        listViewUnfold();
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                adapter.setGroupPosition(groupPosition);
                return true;
            }
        });
    }

    /**
     * 让ExpandableListView全部展开的方法
     */
    public void listViewUnfold() {
        int size = list1.size();
        for (int i = 0; i < size; i++) {// 这么设置就是让默认全都展开的
            elv.expandGroup(i);
        }
    }

    private List<String> initInfo() {
        List<String> list1 = new ArrayList<>();
        for (int x = 0; x < 20; x++) {
            list1.add("父 条 目" + String.valueOf(x));
        }
        List<String> list2 = new ArrayList<>();
        for (int x = 0; x < 20; x++) {
            list2.add("子 条 目" + String.valueOf(x));
        }
        return list1;
    }

//    public class MyMyAdapter extends BaseAdapter {
//        List<String> list;
//        public MyMyAdapter(List<String> list) {
//            this.list = list;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = View.inflate(activity, R.layout.item_discover_group, null);
//            TextView tv = (TextView) view.findViewById(R.id.tv_discover);
//            tv.setText(list.get(position));
//            return view;
//        }
//    }
}
