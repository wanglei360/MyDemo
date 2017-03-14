package com.XListView;

import android.os.Build;

/**
 * 创建者：wanglei
 * <p>时间：16/5/9  12:47
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyTools {

    /**
     * 判断传进来的版本号是否大于当前手机版本号（系统版本）
     *
     * @param versionCode
     * @return 传进来的大于当前手机版本返回true，否则返回false
     */
    public static boolean isNewVersion(int versionCode) {
        if (Build.VERSION.SDK_INT >= versionCode)
            return true;
        return false;
    }


}
