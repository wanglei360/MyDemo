package com.ScrollViewListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import com.myplayer.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建者：wanglei
 * <p>时间：16/7/23  11:57
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ScrollViewListViewActivity extends Activity {
    private Button but1;
    private HashMap<Integer, Boolean> map;
    private myListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_listview_layout);

        ArrayList<Integer> list = new ArrayList<>();
        for(int x = 0;x<100;x++){
            list.add(x);
        }

        lv = (myListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this,list));


        //todo 必须手动调用一次，否则默认是listView在顶部
        ScrollView sv = (ScrollView) findViewById(R.id.sv);
        sv.smoothScrollTo(0, 0);
    }
}
