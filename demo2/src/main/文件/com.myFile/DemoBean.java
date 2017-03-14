package com.myFile;

import java.io.Serializable;

/**
 * 创建者：wanglei
 * <p>时间：16/7/4  09:37
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
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
