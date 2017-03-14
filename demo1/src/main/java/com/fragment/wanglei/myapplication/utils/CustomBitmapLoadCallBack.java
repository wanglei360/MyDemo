package com.fragment.wanglei.myapplication.utils;

import android.graphics.drawable.Drawable;

import com.fragment.wanglei.myapplication.adapter.ImageListAdapter;

import org.xutils.common.Callback;

/**
 * 创建者：wanglei
 * 时间：16/3/22  14:15
 * 类描述：XUtils下载图片时用的返回进度
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CustomBitmapLoadCallBack implements Callback.ProgressCallback<Drawable> {
    private ImageListAdapter.ViewHolder holder;

    public CustomBitmapLoadCallBack(ImageListAdapter.ViewHolder holder) {
        if (holder != null)
            this.holder = holder;
    }

    @Override
    public void onWaiting() {
        if (holder != null)
            this.holder.imgPb.setProgress(0);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onLoading(long total, long current, boolean isDownloading) {
        if (holder != null)
            this.holder.imgPb.setProgress((int) (current * 100 / total));
    }

    @Override
    public void onSuccess(Drawable result) {
        if (holder != null)
            this.holder.imgPb.setProgress(100);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
    }

    @Override
    public void onCancelled(Callback.CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
