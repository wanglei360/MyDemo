package com.fragment.wanglei.myapplication.base;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import org.xutils.x;

import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/3/22  14:44
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseActivity extends FragmentActivity implements DialogInterface.OnClickListener {

    public int LISTIMEGRESULTCODE = 1;//ViewPager使用的ActivityResultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindowRransverseOrVertical();
        x.view().inject(this);
    }

    /**
     * 界面失去焦点，点击home键的时候调用
     */
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Toast.makeText(this, "Home键被点击了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 屏幕是横着的还是竖着的设置，不在清单文件中设置了
     * android:screenOrientation="portrait","landscape"// 始终保持竖屏,始终保持横屏
     * SCREEN_ORIENTATION_PORTRAIT  竖着的
     * SCREEN_ORIENTATION_LANDSCAPE 横着的
     */
    private void setWindowRransverseOrVertical() {
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 返回键如果有特殊处理就从新这个方法
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            isNullTask();
        }
        return false;
    }

    /**
     * 默认返回键调用的方法，返回，关闭当前Activity，如果只剩一个了，询问是否关闭
     */
    public void isNullTask() {
        ActivityManager manager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
//        List<ActivityManager.AppTask> appTasks = manager.getAppTasks();
//        Size appTaskThumbnailSize = manager.getAppTaskThumbnailSize();
        List<ActivityManager.RunningAppProcessInfo> rapList = manager.getRunningAppProcesses();
        ActivityManager.RunningAppProcessInfo rapManager = rapList.get(0);

        if (runningTaskInfos != null) {
            for (ActivityManager.RunningTaskInfo ar : runningTaskInfos) {
                if (ar.numActivities == 1) {
                    dialog();
                } else {
                    finish();
                }
            }
        }
    }

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", this);
        builder.setNegativeButton("取消", this);
        builder.create().show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                myFinish();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                /*
                在任务开始的时候显示一个对话框，然后当任务完成了再Dismiss对话框，
                如果在此期间如果Activity因为某种原因被杀掉且又重新启动了，
                那么当Dismiss的时候WindowManager检查发现Dialog所属的Activity已经不存在了，
                所以会报IllegalArgumentException： View not attached to window manager.
                 */
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void myFinish() {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        } else {// android2.1 todo 权限<uses-permission android:name="android.permission.RESTART_PACKAGES" />
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
        }
    }
}
