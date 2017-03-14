package com.myVR;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.myplayer.R;

import java.io.File;
import java.io.InputStream;

/**
 * 创建者：wanglei
 * <p>时间：16/8/19  20:05
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class VrImageActivity extends Activity {

    private VrPanoramaView vr_imge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vr_image_layout);
        try {
            vr_imge = (VrPanoramaView) findViewById(R.id.vr_imge_view);
            InputStream in = getAssets().open("andes.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            VrPanoramaView.Options options = new VrPanoramaView.Options();
            int typeStereoOverUnder = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;
            vr_imge.loadImageFromBitmap(bitmap,options);
            vr_imge.setEventListener(new VrPanoramaEventListener(){
                @Override
                public void onLoadSuccess() {
                    super.onLoadSuccess();
                    Toast.makeText(VrImageActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
                    Log.e("VrImageActivity","加载成功");
                }

                @Override
                public void onLoadError(String errorMessage) {
                    super.onLoadError(errorMessage);
                    Toast.makeText(VrImageActivity.this,"Error 加载失败",Toast.LENGTH_SHORT).show();
                    Log.e("VrImageActivity","Error 加载失败");
                }
            });

        } catch (Exception e) {
            Toast.makeText(VrImageActivity.this,"Exception 加载失败",Toast.LENGTH_SHORT).show();
            Log.e("VrImageActivity","Exception 加载失败");
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
