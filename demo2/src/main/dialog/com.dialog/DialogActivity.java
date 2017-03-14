package com.dialog;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.dialog.dialog.OftenDialog;
import com.dialog.dialog.DialogImp;
import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  09:42
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class DialogActivity extends Activity implements DialogImp {

    private int height;
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point point = new Point();
            getWindowManager().getDefaultDisplay().getSize(point);
            width = point.x;
            height = point.y;
        } else {
            Display defaultDisplay = this.getWindowManager().getDefaultDisplay();
            height = defaultDisplay.getHeight();
            width = defaultDisplay.getWidth();
        }
    }

    public void but(View view) {
        final OftenDialog oftenDialog = new OftenDialog(this, this, width, height);
        oftenDialog.showOftenDealog("zhege阿斯蒂芬阿斯顿发的说法zhege阿斯蒂芬阿斯顿发的说法zhege阿斯蒂芬阿斯顿发的说法zhege阿斯蒂芬阿斯顿发的说法zhege阿斯蒂芬阿斯顿发的说法");

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                WaitDialog.dismissWaitDialog();
//            }
//        }, 5000);
    }

    @Override
    public void dialog_but_no() {
        Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dialog_but_ok() {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

}
