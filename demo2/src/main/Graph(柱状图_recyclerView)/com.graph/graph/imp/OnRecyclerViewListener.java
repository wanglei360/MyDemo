package com.graph.graph.imp;

import android.view.MotionEvent;
import android.view.View;

/**
 * 创建者：wanglei
 * <p>时间：16/5/25  10:50
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public interface OnRecyclerViewListener {
    boolean onTouch(View v, MotionEvent event, int position, String text);
    void dismissPopupWindow();
}
