package com.fragment.wanglei.myapplication.bean;

import java.io.Serializable;

/**
 * 创建者：wanglei
 * 时间：16/4/26  08:47
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DemoBean implements Serializable {
    String str;
    int ii;
    boolean bb;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getIi() {
        return ii;
    }

    public void setIi(int ii) {
        this.ii = ii;
    }

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
    }
}
