package com.fragment.wanglei.myapplication.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fragment.wanglei.myapplication.R;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * 创建者：wanglei
 * 时间：16/4/14  17:24
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerBitImageAdapter extends PagerAdapter {
    private List<String> infoSource;
    private ImageOptions imageOptions;

    public ViewPagerBitImageAdapter(List<String> infoSource, ImageOptions imageOptions) {
        this.infoSource = infoSource;
        this.imageOptions = imageOptions;
    }
    @Override
    public int getCount() {
        return infoSource.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        x.image().bind(photoView, infoSource.get(position), imageOptions);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    public class ViewHolder {
        @ViewInject(R.id.img_pager_item)
        private ImageView imgItem;
    }
}
