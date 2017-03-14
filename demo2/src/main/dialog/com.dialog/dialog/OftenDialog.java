package com.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/6/6  13:32
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class OftenDialog extends Dialog implements View.OnClickListener/*android.view.View.OnClickListener */ {

    private DialogImp dialogImp;
    private TextView tv;

    /**
     * @param context activity
     * @param width   屏幕的宽度
     * @param height  屏幕的高度
     *                //     * @param tvStr   要显示的内容
     */
    public OftenDialog(Context context, DialogImp DialogImp, int width, int height) {
        super(context, R.style.Theme_dialog);//设置dialog没有title
        this.dialogImp = DialogImp;
        View view = LayoutInflater.from(context).inflate(R.layout.often_dialog, null);
        tv = (TextView) view.findViewById(R.id.dialog_tv);
        View ok = view.findViewById(R.id.dialog_ok);
        View no = view.findViewById(R.id.dialog_no);
        ok.setOnClickListener(this);
        no.setOnClickListener(this);
        setContentView(view);

        Window window = getWindow(); // 得到对话框
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = width / 8 * 6;
        wl.height = height / 8 * 2;
        window.setAttributes(wl);
        // 设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(false);
    }

    public void showOftenDealog(String tvStr) {
        tv.setText(tvStr);
        show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_no:
                dialogImp.dialog_but_no();
                break;
            case R.id.dialog_ok:
                dialogImp.dialog_but_ok();
                dimisDialog();
                break;
        }
        dismiss();
    }

    private void dimisDialog(){
        dialogImp = null;
        tv = null;
    }
}
