package com.graph.graph;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.graph.graph.adapter.PersonAdapter;
import com.graph.graph.bean.GraphList;
import com.graph.graph.imp.MyOnclik;
import com.graph.graph.imp.OnRecyclerViewListener;
import com.graph.graph.widget.Graph;
import com.myplayer.R;

import java.util.Collections;
import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/5/25  19:14
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class My_Graph extends RelativeLayout implements OnRecyclerViewListener {

    private Graph recyclerView;
    private PopupWindow popupWindow;
    private boolean isAlreadyInitializedGraph = false;
    private MyOnclik myOnclik;
    private ProgressBar progress_bar;
    private PersonAdapter adapter;
    private List<GraphList> graphListList;
    private float rawY;
    private boolean isReverse;

    public My_Graph(Context context) {
        super(context);
        initView(context);
    }

    public My_Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public My_Graph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout mContainer = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.graph, null);
        addView(mContainer, lp);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (Graph) findViewById(R.id.recycler_view_test_rv);
        recyclerView.setOnRecyclerViewListener(this);
        recyclerView.setHasFixedSize(true);
    }

    public boolean setInit(Context context, int graphHeight, List<GraphList> graphListList, int width) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        //屏幕的宽度-左边那些数字的宽度/每个柱子的宽度，获取到当前剩下的这些宽度能装几个柱子来判断recyclerView是否需要烦着显示
        int i = (width - dip2px(context, 30)) / dip2px(context, 60);
        isReverse = graphListList.size() > i;
        if (isReverse) {
            layoutManager.setReverseLayout(true);//todo 顺序倒着显示，但数据源也要倒过来
        }
        Collections.reverse(graphListList);
        this.graphListList = graphListList;
        if (!isAlreadyInitializedGraph) {
            isAlreadyInitializedGraph = true;
            adapter = new PersonAdapter(context, graphListList);
            adapter.setOnRecyclerViewListener(this);
            adapter.setHeight(graphHeight);
            recyclerView.setAdapter(adapter);
        }
        return isReverse;
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev, int position, String topNum) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = ev.getRawY();
                showPopupWindow(v, topNum);
                break;
            case MotionEvent.ACTION_UP:
                float MyRawy = ev.getRawY();
                float newY = rawY - MyRawy;
                if (!(newY > 10 || newY < -10)) {
                    if (adapter.clikPosition == position) {
                        adapter.clikPosition = -2;
                    } else {
                        adapter.clikPosition = position;
                    }
                    adapter.notifyDataSetChanged();
                    if (!isReverse)
                        myOnclik.myOnclik(topNum, graphListList.size() - position - 1, adapter.clikPosition != -2);
                    else
                        myOnclik.myOnclik(topNum, position, adapter.clikPosition != -2);//740
                }
                dismissPopupWindow();
                break;
        }
        return false;
    }


    public void dismissPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private void showPopupWindow(View v, String text) {
        int width = v.getWidth();
        LinearLayout layout = new LinearLayout(v.getContext());
        layout.setBackgroundResource(R.mipmap.wwww);
        TextView tv = new TextView(v.getContext());
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(width, width);
        tv.setLayoutParams(layoutParams);
        tv.setText(text);
        tv.setPadding(0, 0, 0, width / 3);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        layout.addView(tv);

        popupWindow = new PopupWindow(layout, width, width - (width / 5));

        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]
                , location[1] - (popupWindow.getHeight() + popupWindow.getHeight() / 5));
    }

    public void setMyOnclik(MyOnclik myOnclik) {
        this.myOnclik = myOnclik;
//        recyclerView.setMyOnclik(myOnclik);
    }

    public void setShowProgressBar(boolean showProgressBar) {
        if (showProgressBar) {
            progress_bar.setVisibility(View.INVISIBLE);
            return;
        }
        progress_bar.setVisibility(View.GONE);
    }
}
