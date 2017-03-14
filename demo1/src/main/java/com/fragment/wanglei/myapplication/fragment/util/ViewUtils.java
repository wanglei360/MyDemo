package com.fragment.wanglei.myapplication.fragment.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * 创建者：wanglei
 * 时间：16/4/15  14:47
 * 类描述：frgment用到了，
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewUtils {
    /**
     * 移除 当前view的爹
     * @param v
     */
    public static void removeParent(View v){
        //  先找到爹 在通过爹去移除孩子
        ViewParent parent = v.getParent();
        //所有的控件 都有爹  爹一般情况下 就是ViewGoup
        if(parent instanceof ViewGroup){
            ViewGroup group=(ViewGroup) parent;
            group.removeView(v);
        }
    }
}
