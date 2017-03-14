package com.graph.graph.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.graph.graph.imp.OnRecyclerViewListener;


/**
 * 创建者：wanglei
 * <p>时间：16/5/27  16:14
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class Graph extends RecyclerView {
    private OnRecyclerViewListener onRecyclerViewListener;

    public Graph(Context context) {
        super(context);
    }

    public Graph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Graph(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private float mLastY;
    private float deltaY;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                deltaY = ev.getRawY() - mLastY;
                break;
            case MotionEvent.ACTION_UP:
                deltaY = 0;
                onRecyclerViewListener.dismissPopupWindow();
                break;
        }
        if (deltaY > 10 || deltaY < -10) {
            onRecyclerViewListener.dismissPopupWindow();
        }
        return super.onTouchEvent(ev);
    }

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
}
