package com.fragment.wanglei.myapplication;

import android.content.Context;

import com.fragment.wanglei.myapplication.utils.LogUtils;

/**
 * 创建者：wanglei
 * <p>时间：16/4/27  12:54
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class GlobalParams {
    //application上下文
    public static Context applicationContext;

    //主线程pid
    public static int mainPID = -1;
    //TODO Log输出级别  初始默认不输出log
    public static int Debuggable = LogUtils.LEVEL_NONE;
    //是否输入 toast
    public static boolean showToast = false;
}
