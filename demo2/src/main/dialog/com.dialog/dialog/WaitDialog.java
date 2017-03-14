package com.dialog.dialog;

import android.app.Dialog;
import android.content.Context;

import com.myplayer.R;


/**
 * 创建者：wanglei
 * <p>时间：16/6/6  19:45
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class WaitDialog {

    public static Dialog waitDialog;

    public static void showWaitDialog(Context context) {
        if (waitDialog == null) {
            waitDialog = new Dialog(context, R.style.Theme_dialog);
            waitDialog.setContentView(R.layout.wait_dialog);
            waitDialog.setCanceledOnTouchOutside(false);// dialog以外的地方点击，dialog不消失
        }
        waitDialog.show();
    }

    public static void dismissWaitDialog() {
        if (waitDialog != null && waitDialog.isShowing())
            waitDialog.dismiss();
    }
}
