package com.myplayer.video_2.view;

import android.content.Context;
import android.util.AttributeSet;

/**
 * 创建者：leiwang
 * <p>时间：2017/3/31 15:41
 * <p>类描述：从写VideoView,因为原生的没有播放和暂停的监听
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class VideoView extends android.widget.VideoView {
    private PlayPauseListener mListener;

    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setPlayPauseListener(PlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if (mListener != null) {
            mListener.onPlay();
        }
    }

    interface PlayPauseListener {
        void onPlay();
        void onPause();
    }
}
