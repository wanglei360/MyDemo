package com.fragment.wanglei.myapplication.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseNetImgeAdapter;
import com.fragment.wanglei.myapplication.utils.CustomBitmapLoadCallBack;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/3/22  15:36
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ImageListAdapter extends BaseNetImgeAdapter {


    private final LayoutInflater mInflater;


    public ArrayList<String> imgSrcList;

    public ImageListAdapter(Activity activity){
        mInflater = LayoutInflater.from(activity);
        imgSrcList = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return imgSrcList.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_home, parent, false);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imgPb.setProgress(0);
        x.image().bind(holder.imgItem,
                imgSrcList.get(position),
                imageOptions,//imageOptions对象中的设置可以设置圆角
                new CustomBitmapLoadCallBack(holder));

        holder.tv1.setText("ForceLoadingDrawable = "+imageOptions.isForceLoadingDrawable());
        holder.tv2.setText("FadeIn = "+imageOptions.isFadeIn());

        return convertView;
    }
    public class ViewHolder {
        @ViewInject(R.id.img_item)
        private ImageView imgItem;

        @ViewInject(R.id.tv1)
        private TextView tv1;

        @ViewInject(R.id.tv2)
        private TextView tv2;

        @ViewInject(R.id.img_pb)
        public ProgressBar imgPb;
    }



    public void addSrc(List<String> imgSrcList) {
        this.imgSrcList.addAll(imgSrcList);
    }

    public ArrayList<String> getImgSrcList() {
        return imgSrcList;
    }

}
