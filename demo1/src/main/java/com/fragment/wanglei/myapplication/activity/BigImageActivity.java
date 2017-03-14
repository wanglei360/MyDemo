package com.fragment.wanglei.myapplication.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseActivityImage;

import org.xutils.common.Callback;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 创建者：wanglei
 * 时间：16/3/22  17:19
 * 类描述：单张形式的图片的放大，用Intent传进来一个网络图片的地址，键为url，就可点击放大
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@ContentView(R.layout.big_imag_layou)
public class BigImageActivity extends BaseActivityImage {


    @ViewInject(R.id.big_bitmap_iv)
    private ImageView big_bitmap_iv;
    private PhotoViewAttacher photoViewAttacher;
    private boolean isLoadComplete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.image().bind(big_bitmap_iv, getIntent().getStringExtra("url"), imageOptions, new Callback.CommonCallback<Drawable>() {
           //要加载完图片之后再初始化photoView，否则大点的图片，还没加载出来已经初始化了，放大的时候就会先缩小
            @Override
            public void onSuccess(Drawable result) {
                initPhotoView();
            }
            @Override
            public void onFinished() {
                initPhotoView();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {}
            @Override
            public void onCancelled(CancelledException cex) {}
        });
    }


    private void initPhotoView() {
        if (!isLoadComplete) {
            photoViewAttacher = new PhotoViewAttacher(big_bitmap_iv);
            isLoadComplete = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //清理
        photoViewAttacher.cleanup();
        photoViewAttacher = null;
    }
}
