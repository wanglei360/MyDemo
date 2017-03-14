package com.fragment.wanglei.myapplication.base;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.fragment.wanglei.myapplication.R;
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;

/**
 * 创建者：wanglei
 * 时间：16/3/22  14:39
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseNetImgeAdapter extends BaseAdapter {

    public static ImageOptions imageOptions;
    public BaseNetImgeAdapter(){
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(600), DensityUtil.dip2px(600))//清晰度，值越大越清晰
                .setRadius(DensityUtil.dip2px(5))//角的弧度
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
                .setLoadingDrawableId(R.mipmap.ic_launcher)//加载时的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)//加载失败时的图片
                .build();
    }
}
