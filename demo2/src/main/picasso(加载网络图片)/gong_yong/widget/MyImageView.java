package gong_yong.widget;

import android.content.Context;
import android.util.AttributeSet;

import uk.co.senab.photoview.PhotoView;

/**
 * 创建者：wanglei
 * <p>时间：16/6/7  14:54
 * <p>类描述：重写ImageView的onDetachedFromWindow方法，在它从屏幕中消失时回调，去掉drawable引用，
 *              能加快内存的回收
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyImageView extends PhotoView {
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }
}

