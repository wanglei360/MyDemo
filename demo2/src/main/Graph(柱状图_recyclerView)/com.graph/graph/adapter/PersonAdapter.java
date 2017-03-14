package com.graph.graph.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.graph.graph.bean.GraphList;
import com.graph.graph.holder.PersonViewHolder;
import com.graph.graph.imp.OnRecyclerViewListener;
import com.myplayer.R;
import java.util.List;



/**
 * 创建者：wanglei
 * <p>时间：16/5/25  10:40
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class PersonAdapter extends RecyclerView.Adapter {

    private List<GraphList> list;
    private OnRecyclerViewListener onRecyclerViewListener;
    private Context mainActivity;
    private int height;

    public PersonAdapter(Context mainActivity, List<GraphList> list) {
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.graph_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view, onRecyclerViewListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = position;
        GraphList graphList = list.get(position);

        ViewGroup.LayoutParams lp = holder.column.getLayoutParams();
        float i3 = (float) height / 110;
        float i1 = (float) (i3 * Integer.valueOf(graphList.getColumn()) + 0.5);
        lp.height = (int) i1;
        holder.column.setBackgroundResource(R.drawable.graph_item_selector);
        if (clikPosition != -1)
            if (clikPosition == position)
                holder.column.setSelected(true);
            else
                holder.column.setSelected(false);
        holder.bottomNum.setText(graphList.getBottomNum() + "/" + graphList.getBottomNum());
        holder.topNum.setText(graphList.getTopNum());
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public int clikPosition = -1;

    public void setHeight(int height) {
        //32是底下的横线和文字的高度，是在布局中写死的
        this.height = height - dip2px(mainActivity, 32);
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
