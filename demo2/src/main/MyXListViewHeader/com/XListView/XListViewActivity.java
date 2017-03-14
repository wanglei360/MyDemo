package com.XListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.XListView.adapter.MyAdapter;
import com.XListView.view.MyImage;
import com.XListView.view.XListView;
import com.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  10:04
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class XListViewActivity extends Activity implements XListView.IXListViewListener, AdapterView.OnItemClickListener {


    private XListView mListView;
    private Handler mHandler;
    private MyAdapter mAdapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_listview_layout);
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setIv((MyImage) findViewById(R.id.topView_iv));
        mListView.setLayout((LinearLayout) findViewById(R.id.title));
        mListView.settv((TextView) findViewById(R.id.title_tv));
        mListView.setXListViewListener(this);
        mListView.setPullLoadEnable(true);
        mHandler = new Handler();
        list = new ArrayList<>();
        geneItems();
        mAdapter = new MyAdapter(this, list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "" + String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    private void geneItems() {
        for (int i = 0; i < 7; i++) {
            list.add("refresh cnt ");
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();

            }
        }, 2000);
    }
}
