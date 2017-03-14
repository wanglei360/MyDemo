package com.recyclerview.android_native.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplayer.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

/**
 * 创建者：wanglei
 * <p>时间：16/8/16  19:53
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ListViewHolder> implements View.OnClickListener {
    private String[] imageArray;
    private Activity context;
    private int state;
    private OnItemClickListener onItemClickListener = null;

    /**
     * @param imageArray 数据源
     * @param context    上下文
     * @param state      0就是list的样式加载布局，0就是grid的样式加载布局
     */
    public SimpleListAdapter(String[] imageArray, Activity context, int state) {
        this.context = context;
        this.imageArray = imageArray;
        this.state = state;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = null;
        if (state < 4)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_recycler_native, viewGroup, false);
        else if (state > 3)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_recycler_native, viewGroup, false);
        if (view != null)
            view.setOnClickListener(this);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        String imageNetUrl = imageArray[position];
        holder.setData(imageNetUrl, position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return imageArray.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivIcon;
        private final TextView tvName;

        public ListViewHolder(View view) {
            super(view);
            LinearLayout.LayoutParams lp = getLayoutParams();
            if (lp != null)
                lp.setMargins(dp2px(9), dp2px(10), dp2px(9), dp2px(10));
            view.setLayoutParams(lp);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            tvName = (TextView) view.findViewById(R.id.tvName);
        }

        public void setData(String imageNetUrl, int position) {
            tvName.setText("图片 " + position);
            showListImag(imageNetUrl, ivIcon);
        }

        private LinearLayout.LayoutParams getLayoutParams() {
            int width;
            int height;
            switch (state) {
                case 0:
                    width = ViewGroup.LayoutParams.MATCH_PARENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 1:
                    width = ViewGroup.LayoutParams.MATCH_PARENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 2:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 3:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 4:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 5:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 6:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 7:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 8:
                    width = ViewGroup.LayoutParams.MATCH_PARENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 9:
                    width = ViewGroup.LayoutParams.MATCH_PARENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 10:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                case 11:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
                default:
                    width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    break;
            }
            return new LinearLayout.LayoutParams(width, height);
        }
    }

    public int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClickListener(v, (Integer) v.getTag());
    }

    public void showListImag(String imageNetUrl, final ImageView iv) {
        Picasso.with(context).load(imageNetUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).config(Bitmap.Config.RGB_565)
//               .config(Bitmap.Config.ARGB_8888)
                .into(iv);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, int positon);
    }
}
