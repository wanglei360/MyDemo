package com.ScrollViewListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 创建者：wanglei
 * <p>时间：16/7/23  11:37
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class myListView extends ListView {
    public myListView(Context context) {
        super(context);
    }
    public myListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public myListView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}