package com.myVR;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.video.VrVideoEventListener;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.myplayer.R;


/**
 * 创建者：wanglei
 * <p>时间：16/8/19  20:05
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class VrVideoActivity extends Activity {

    private VrVideoView vr_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_video_layout);
        vr_video = (VrVideoView) findViewById(R.id.vr_video_view);


        try {
            VrVideoView.Options options = new VrVideoView.Options();
            int typeStereoOverUnder = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
            vr_video.loadVideoFromAsset("congo.mp4", options);
            vr_video.setEventListener(new VrVideoEventListener() {
                @Override
                public void onLoadSuccess() {
                    super.onLoadSuccess();
                    Toast.makeText(VrVideoActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                    Log.e("VrImageActivity", "加载成功");
                }

                @Override
                public void onLoadError(String errorMessage) {
                    super.onLoadError(errorMessage);
                    Toast.makeText(VrVideoActivity.this, "Error 加载失败", Toast.LENGTH_SHORT).show();
                    Log.e("VrImageActivity", "Error 加载失败");
                }

                @Override
                public void onCompletion() {
                    super.onCompletion();
                    if (vr_video != null)
                        vr_video.seekTo(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        vr_video.pauseVideo();
        vr_video = null;
    }
}
