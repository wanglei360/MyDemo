package com.myplayer.video_2.view;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.myplayer.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MediaController extends android.widget.MediaController implements VideoView.PlayPauseListener, View.OnClickListener {

    private Activity mActivity;
    private boolean isModify;
    private List<Float> videoSizes;
    private View mView;
    private VideoView mVideoView;
    private VideoViewRotateListener videoViewRotateListener;


    public MediaController(Activity activity, final VideoView mVideoView, Uri uri) {
        super(activity);
        this.mActivity = activity;
        this.mVideoView = mVideoView;
        videoSizes = new ArrayList<>();

        //todo 自己建立的播放和暂停的监听
        mVideoView.setPlayPauseListener(this);
        mVideoView.setVideoURI(uri);
        mVideoView.setMediaController(this);
        mVideoView.setFocusableInTouchMode(false);
        mVideoView.setFocusable(false);
        mVideoView.setEnabled(false);
        mVideoView.start();
    }

    @Override
    public void setAnchorView(View view) {
        super.setAnchorView(view);
        try {
            //todo 找到主布局
            Field mRoot = android.widget.MediaController.class.getDeclaredField("mRoot");
            mRoot.setAccessible(true);
            ViewGroup mRootVg = (ViewGroup) mRoot.get(this);

            //todo 找到原生的那个播放按钮的布局,删除
            ViewGroup vg1 = findImageButtonParent(mRootVg);
            mRootVg.removeView(vg1);

            //todo 找到原生的那个SeekBar,删除
            ViewGroup vg = findSeekBarParent(mRootVg);
            int index = 1;
            for (int i = 0; i < vg.getChildCount(); i++) {
                if (vg.getChildAt(i) instanceof SeekBar) {
                    index = i;
                    break;
                }
            }
            vg.removeViewAt(index);

            //todo 新的SeekBar添加进去
            SeekBar sb = (SeekBar) LayoutInflater.from(getContext()).inflate(R.layout.video_seekbar, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            vg.addView(sb, index, params);

            //todo 进去之后替换mProgress,原生的SeekBar是mProgress强转的
            Field mProgress = android.widget.MediaController.class.getDeclaredField("mProgress");
            mProgress.setAccessible(true);
            mProgress.set(this, sb);

            //todo 给新的SeekBar添加原来SeekBar的mSeekListener监听
            Field mSeekListener = android.widget.MediaController.class.getDeclaredField("mSeekListener");
            mSeekListener.setAccessible(true);
            sb.setOnSeekBarChangeListener((OnSeekBarChangeListener) mSeekListener.get(this));

            //TODO 再SeekBar那个布局的后面添加了一个旋转的按钮
            Button videoViewRotateBut = (Button) LayoutInflater.from(getContext()).inflate(R.layout.right_layout, null);
            LinearLayout.LayoutParams butParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            vg.addView(videoViewRotateBut, vg.getChildCount(), butParams);
            videoViewRotateBut.setOnClickListener(this);

            //todo 播放和暂停的按钮
            mView = LayoutInflater.from(getContext()).inflate(R.layout.start_but_layout, null);
            ImageButton startBut = (ImageButton) mView.findViewById(R.id.video_view_start_but);
            Field mPauseListener = android.widget.MediaController.class.getDeclaredField("mPauseListener");
            mPauseListener.setAccessible(true);
            startBut.setOnClickListener((OnClickListener) mPauseListener.get(this));

            //TODO 新添加的播放按钮替换原生的那个
            Field mPauseButton = android.widget.MediaController.class.getDeclaredField("mPauseButton");
            mPauseButton.setAccessible(true);
            mPauseButton.set(this, startBut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ViewGroup findImageButtonParent(ViewGroup vg) {
        ViewGroup viewGroup = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View view = vg.getChildAt(i);
            if (view instanceof ImageButton) {
                viewGroup = (ViewGroup) view.getParent();
                i = vg.getChildCount();
                break;
            } else if (view instanceof ViewGroup) {
                viewGroup = findImageButtonParent((ViewGroup) view);
                i = vg.getChildCount();
            }
        }
        return viewGroup;
    }

    private ViewGroup findSeekBarParent(ViewGroup vg) {
        ViewGroup viewGroup = null;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View view = vg.getChildAt(i);
            if (view instanceof SeekBar) {
                viewGroup = (ViewGroup) view.getParent();
                break;
            } else if (view instanceof ViewGroup) {
                viewGroup = findSeekBarParent((ViewGroup) view);
            }
        }
        return viewGroup;
    }

    @Override
    public void show(int timeout) {
        super.show(timeout);
        if (mView != null && mActivity != null) {
            ((ViewGroup) mActivity.findViewById(android.R.id.content))
                    .removeView(mView);
            ((ViewGroup) mActivity.findViewById(android.R.id.content))
                    .addView(mView);
        }
    }

    @Override
    public void hide() {
        super.hide();
        if (mView != null && mActivity != null) {
            ((ViewGroup) mActivity.findViewById(android.R.id.content))
                    .removeView(mView);
        }
    }

    @Override
    public void onPlay() {
        if (mVideoView != null) {
            if (videoSizes.size() > 0 && videoSizes.size() < 4 && videoSizes.get(1) != mVideoView.getWidth()) {
                videoSizes.add((float) mVideoView.getWidth());
                videoSizes.add((float) mVideoView.getHeight());
            }
            if (videoSizes.size() > 2 && !isModify && mView != null) {
                isModify = true;
                float height = videoSizes.get(3);
                float width = videoSizes.get(2);
                float x = width / 2 - 80 / 2;
                float y = height / 2 - 80 / 2;
                mView.setX(x);
                mView.setY(y);
            }
        }
    }

    public void onDestroy() {
        mActivity = null;
        videoSizes = null;
        mView = null;
        mVideoView = null;
        videoViewRotateListener = null;
    }

    @Override
    public void onPause() {
    }

    public List<Float> getVideoSizes() {
        return videoSizes;
    }

    public View getmView() {
        return mView;
    }

    @Override
    public void onClick(View v) {
        if (videoViewRotateListener != null)
            videoViewRotateListener.videoViewRotateButClickListener();
    }

    public void setVideoViewRotateListener(VideoViewRotateListener videoViewRotateListener) {
        this.videoViewRotateListener = videoViewRotateListener;
    }

    public interface VideoViewRotateListener {
        void videoViewRotateButClickListener();
    }
}
