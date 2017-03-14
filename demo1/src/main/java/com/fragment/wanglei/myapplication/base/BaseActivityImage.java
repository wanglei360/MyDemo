package com.fragment.wanglei.myapplication.base;

import android.os.Bundle;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;

/**
 * 创建者：wanglei
 * 时间：16/3/22  14:44
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseActivityImage extends BaseActivity {

    public ImageOptions imageOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == imageOptions) {
            imageOptions = new ImageOptions.Builder()
                    // 加载中或错误图片的ScaleType
                    //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                    // 默认自动适应大小
                    // .setSize(...)
                    .setIgnoreGif(false)
                    // 如果使用本地文件url, 添加这个设置可以在本地文件更新后刷新立即生效.
                    //.setUseMemCache(false)
                    .setImageScaleType(ImageView.ScaleType.FIT_CENTER).build();
        }
    }
}
