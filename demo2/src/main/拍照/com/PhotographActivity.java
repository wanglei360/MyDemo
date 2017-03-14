package com;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.myplayer.R;

import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * 创建者：wanglei
 * <p>时间：16/7/26  20:07
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class PhotographActivity  extends Activity {

    private Button but;
    private ImageView image;
    private Photograph photograph;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photograph_layout);

        but = (Button) findViewById(R.id.but);
        image = (ImageView) findViewById(R.id.image);
        tv = (TextView) findViewById(R.id.tv);

        photograph = new Photograph(this);

        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    photograph.selectCameraOrAlbum();
                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case 0://相册回来 所以data肯定有值
                    if (data != null)
                        photograph.startClip(data.getData(),1);
                    break;
                case 1://剪辑完回来
                    if (data != null) {
                        Bitmap bitmap = data.getParcelableExtra("data");
                        image.setImageBitmap(bitmap);
                    }
                    break;
                case 2://拍照回来的，所以data有的机型是null，所以直接用本地存的地址肯定有照片，不用判断
                    photograph.startClip(null,3);
                    break;
                case 3:
                    if (data != null) {//相机拍照，有的机型会data返回null,所以本地要有地址，直接用
                        Bitmap bitmap = data.getParcelableExtra("data");
                        Log.e("Bitmap","Bitmap Width = "+bitmap.getWidth()+"  Bitmap Height = "+bitmap.getHeight());
                        image.setImageBitmap(bitmap);
                    }else{
                        Uri uri = photograph.getUri();
                        Bitmap bitmap1 = photograph.getBitmap(new File(new URI(uri.toString())).getAbsolutePath());
                        Log.e("Bitmap","Bitmap Width = "+bitmap1.getWidth()+"  Bitmap Height = "+bitmap1.getHeight());
                        image.setImageBitmap(bitmap1);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }
}
