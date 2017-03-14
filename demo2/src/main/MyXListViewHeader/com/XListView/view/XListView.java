package com.XListView.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.XListView.MyTools;
import com.myplayer.R;

public class XListView extends ListView implements OnScrollListener {

    private float mLastY = -1; // 保存事件 y
    private Scroller mScroller; // 用于滚动
    private OnScrollListener mScrollListener; // 用户的滚动监听

    // 触发刷新和加载的接口。
    private IXListViewListener mListViewListener;

    // -- header view
    private XListViewHeader mHeaderView;
    // 头视图内容，使用它来计算头的高度。把它隐藏
    // 禁用刷新刷新时。
    private RelativeLayout mHeaderViewContent;

    private TextView mHeaderTimeView;
    private int mHeaderViewHeight; // 头视图的高度
    private boolean mEnablePullRefresh = true;
    private boolean isShowTopView = true;
    private boolean mPullRefreshing = false; // is refreashing.

    // -- 页脚视图
    private XListViewFooter mFooterView;
    private boolean mEnablePullLoad;
    private boolean mPullLoading;
    private boolean mIsFooterReady = false;

    // 总列表项，用于检测在ListView的底部。
    private int mTotalItemCount;

    // 对于mscroller页眉或页脚，从背部滚动。
    private int mScrollBack;
    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLLBACK_FOOTER = 1;

    private final static int SCROLL_DURATION = 400; // 回滚动时间
    private static int PULL_LOAD_MORE_DELTA; // 当拉动 up >= 50px
    // 在底部，触发
    // 负载更多。
    private final static float OFFSET_RADIO = 1.8f; // 支持iOS的拉特征。

    private MyImage iv;
    private TextView tv;
    private LinearLayout topView;
    private LinearLayout titleLayout;
    private float myTvY;
    private float ivXSizeSingleValue;
    private float ivSizeDifferentialValue;
    private int myIvX;
    private int myTvHeight;
    private int ivInitWidth;
    private int topViewHeight;
    private int ivXSizeinimumMValue;
    private int ivInitPositionHeight;

    /**
     * @param context 上下文
     */
    public XListView(Context context) {
        super(context);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWithContext(context);
    }

    public XListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        // xlistview需要滚动事件，并将调度事件
        // 用户的侦听器（作为代理）。
        super.setOnScrollListener(this);

        // 初始化头View
        mHeaderView = new XListViewHeader(context);
        mHeaderViewContent = (RelativeLayout) mHeaderView.findViewById(R.id.my_xlistview_header_content);
        topView = (LinearLayout) mHeaderView.findViewById(R.id.topView);
        mHeaderTimeView = (TextView) mHeaderView.findViewById(R.id.my_xlistview_header_time);
        addHeaderView(mHeaderView);
        // 初始化页脚View
        mFooterView = new XListViewFooter(context);
        mFooterView.setVisibility(View.GONE);
        // 初始化头View的高度
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
                new OnGlobalLayoutListener() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        mHeaderViewHeight = mHeaderViewContent.getHeight();
                        if (MyTools.isNewVersion((Build.VERSION_CODES.JELLY_BEAN))) {
                            getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        if (isShowTopView) {
                            topViewHeight = topView.getHeight();
                            mHeaderView.setTopViewHeight(topViewHeight);
                            mHeaderView.setVisiableHeight(topViewHeight);
                            ivInitPositionHeight = titleLayout.getHeight() + ((topViewHeight - iv.getHeight()) / 2);
                            int myTvX = (int) tv.getX();//304
                            myTvHeight = tv.getHeight();
                            ivXSizeinimumMValue = myTvX - myTvHeight; //最小值172
                            myIvX = (int) iv.getX();//420  最大值
                            ivInitWidth = iv.getWidth();// 240
                            myTvY = tv.getY();
                            ivXSizeSingleValue = (float) ((myIvX + ivInitWidth) - myTvX) / (ivInitPositionHeight - myTvY);
                            ivSizeDifferentialValue = (ivInitWidth - tv.getHeight()) / (ivInitPositionHeight - myTvY);
                            topViewScrollListener(ivInitPositionHeight);
                            PULL_LOAD_MORE_DELTA = mFooterView.getFooterHeight();
                        } else {
                            topView.setVisibility(View.GONE);
                        }
                    }
                });
    }


    @Override
    public void setAdapter(ListAdapter adapter) {
        // 确保xlistviewfooter最后是页脚视图，只添加一次。
        if (!mIsFooterReady && mEnablePullLoad) {
            mIsFooterReady = true;
            addFooterView(mFooterView);
        }
        super.setAdapter(adapter);
    }

    /**
     * 是否显示上面的那个单独的布局，
     *
     * @param showTopView 默认是true，显示
     */
    public void setShowTopView(boolean showTopView) {
        isShowTopView = showTopView;
    }

    /**
     * 启用或禁用下拉刷新功能。
     *
     * @param enable 默认是true，显示
     */
    public void setPullRefreshEnable(boolean enable) {
        mEnablePullRefresh = enable;
        if (!mEnablePullRefresh) { // 禁用，隐藏的内容
            mHeaderViewContent.setVisibility(View.INVISIBLE);
        } else {
            mHeaderViewContent.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 启用或禁用加载更多功能。
     *
     * @param enable 默认是false
     */
    public void setPullLoadEnable(boolean enable) {
        mEnablePullLoad = enable;
        if (!mEnablePullLoad) {
            mFooterView.hide();
            mFooterView.setOnClickListener(null);
        } else {
            mFooterView.setVisibility(View.VISIBLE);
            mPullLoading = false;
            mFooterView.show();
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
            // 两者“拉”和“点击”将调用负载更多
            mFooterView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoadMore();
                }
            });
        }
    }

    /**
     * 停止刷新，重置标题视图。
     */
    public void stopRefresh() {
        if (mPullRefreshing) {
            mPullRefreshing = false;
            resetHeaderHeight();
        }
    }

    /**
     * 停止加载，重置尾视图。
     */
    public void stopLoadMore() {
        if (mPullLoading) {
            mPullLoading = false;
            mFooterView.setState(XListViewFooter.STATE_NORMAL);
        }
    }

    /**
     * 设置上一次刷新时间
     *
     * @param time 需要显示的时间文本
     */
    public void setRefreshTime(String time) {
        mHeaderTimeView.setText(time);
    }

    private void invokeOnScrolling() {
        if (mScrollListener instanceof OnXScrollListener) {
            OnXScrollListener l = (OnXScrollListener) mScrollListener;
            l.onXScrolling(this);
        }
    }


    private void updateHeaderHeight(float delta) {
        mHeaderView.setVisiableHeight((int) delta + mHeaderView.getVisiableHeight());
        if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
            if (mHeaderView.getVisiableHeight() - topViewHeight > mHeaderViewHeight) {
                mHeaderView.setState(XListViewHeader.STATE_READY);
            } else {
                mHeaderView.setState(XListViewHeader.STATE_NORMAL);
            }
        }
        setSelection(0); // 每次滚动到顶部
    }

    /**
     * 重置标题视图的高度。
     */
    private void resetHeaderHeight() {
        int height = mHeaderView.getVisiableHeight();
        if (height == 0) // 不可见的
            return;
        // 刷新和标题没有充分显示。什么都不做。
        if (mPullRefreshing && height <= mHeaderViewHeight + topViewHeight) {
            return;
        }
        int finalHeight = 0; // 默认：向后滚动，以排除头。
        // 是清爽的，只需滚动显示所有的标题。
        if (mPullRefreshing && height > mHeaderViewHeight + topViewHeight) {
            finalHeight = mHeaderViewHeight + topViewHeight;
        } else if (isShowTopView && height > topViewHeight / 2) {
            finalHeight = topViewHeight;
        }

        mScrollBack = SCROLLBACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        // 触发计算滚动。刷新
        invalidate();
    }

    private void updateFooterHeight(float delta) {
        int height = mFooterView.getBottomMargin() + (int) delta;
        if (mEnablePullLoad && !mPullLoading) {
            if (height > PULL_LOAD_MORE_DELTA) { // 足够高的调用负载更多.
                mFooterView.setState(XListViewFooter.STATE_READY);
            } else {
                mFooterView.setState(XListViewFooter.STATE_NORMAL);
            }
        }
        mFooterView.setBottomMargin(height);
    }

    private void resetFooterHeight() {
        int bottomMargin = mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    private void startLoadMore() {
        mPullLoading = true;
        mFooterView.setState(XListViewFooter.STATE_LOADING);
        if (mListViewListener != null) {
            mListViewListener.onLoadMore();
        }
    }

    float MyrawY;
    boolean isMyClik;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }

        bbbbbb = true;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                MyrawY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getFirstVisiblePosition() == 0 && (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
                    // 第一项是显示，头已显示或向下拉。
                    updateHeaderHeight(deltaY / OFFSET_RADIO);
                    invokeOnScrolling();
                } else if (getLastVisiblePosition() == mTotalItemCount - 1 && (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
                    // 最后一个项目，已经退出或要拉。
                    updateFooterHeight(-deltaY / OFFSET_RADIO);
                }
                break;
            default:
                bbbbbb = false;
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    // 调用刷新
                    if (mEnablePullRefresh && mHeaderView.getVisiableHeight() - topViewHeight > mHeaderViewHeight) {
                        mPullRefreshing = true;
                        mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
                        if (mListViewListener != null) {
                            mListViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                }
                if (getLastVisiblePosition() == mTotalItemCount - 1) {
                    // 调用负载更多。
                    if (mEnablePullLoad && mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }
                break;
        }
        return isConsumeOnTouchEvent(ev);
    }

    private boolean isConsumeOnTouchEvent(MotionEvent ev) {
        float MyRawy = ev.getRawY();
        float v = MyrawY - MyRawy;
        if (!(v > 10 || v < -10)) {
            isMyClik = true;
        } else {
            setPressed(false);
            isMyClik = false;
        }
//        int position = getDownViewPosition(ev);
        if (mHeaderView.getVisiableHeight() > 0 )
            return !isMyClik || super.onTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    /**
     * 当前按下时是那个歌View的position
     *
     * @param ev MotionEvent
     * @return MotionEvent
     */
    private int getDownViewPosition(MotionEvent ev) {
        return pointToPosition((int)ev.getX(), (int)ev.getY());
    }



    boolean bbbbbb;

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLLBACK_HEADER) {
                mHeaderView.setVisiableHeight(mScroller.getCurrY());
            } else if (mScrollBack == SCROLLBACK_FOOTER) {
                mFooterView.setBottomMargin(mScroller.getCurrY());
            }
            setPressed(false);
            invokeOnScrolling();
            invalidate();
            topViewScrollListener(topView.getY() + ivInitPositionHeight);
            super.computeScroll();
            bbbbbb = false;
            Log.e("topViewScrollListener","topView.getY() = "+topView.getY()+"   ivInitPositionHeight"+ivInitPositionHeight);
            return;
        }
        if (bbbbbb) {
            topViewScrollListener(topView.getY() + ivInitPositionHeight);
            super.computeScroll();
        }
    }

    public void topViewScrollListener(float v1) {
        iv.setMyImageY(v1, myTvY, ivInitPositionHeight);
        iv.setMyImageX(ivXSizeinimumMValue, ivSizeDifferentialValue, ivXSizeSingleValue, myIvX, ivInitPositionHeight, v1);
        iv.setMyImageSize(myTvHeight, ivInitPositionHeight, ivSizeDifferentialValue, ivInitWidth, v1);
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mScrollListener != null) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
        switch (scrollState) {//TODO 在onTouchEvent只有return super.onTouchEvent(ev);的时候才生效
            case OnScrollListener.SCROLL_STATE_IDLE://空闲状态
                break;
            case OnScrollListener.SCROLL_STATE_FLING://滚动状态
                break;
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://触摸后滚动
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 发送给用户的侦听器
        mTotalItemCount = totalItemCount;
        if (mScrollListener != null) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    public void setXListViewListener(IXListViewListener l) {
        mListViewListener = l;
    }

    public void setIv(MyImage iv) {
        this.iv = iv;
    }

    public void setLayout(LinearLayout titleLayout) {
        this.titleLayout = titleLayout;
    }

    public void settv(TextView tv) {
        this.tv = tv;
    }

    /**
     * 你可以听listview.onscrolllistener或这一。它将调用
     * onXScrolling 当页眉/页脚滚动回。
     */
    public interface OnXScrollListener extends OnScrollListener {
        void onXScrolling(View view);
    }

    /**
     * 实现此接口，以获得刷新/负载更多事件。
     */
    public interface IXListViewListener {
        void onRefresh();

        void onLoadMore();
    }
}
