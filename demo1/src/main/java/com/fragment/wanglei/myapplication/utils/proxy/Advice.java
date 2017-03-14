package com.fragment.wanglei.myapplication.utils.proxy;

import java.lang.reflect.Method;

/**
 * 创建者：wanglei
 * <p>时间：16/4/27  13:11
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public interface Advice {
    /**
     * 在方法之前添加
     * @param proxy   代理对象
     * @param method  方法
     * @param args    方法的参数
     */
    void methodBefore(Object proxy, Method method, Object[] args);
    /**
     * 在方法之后添加
     * @param proxy   代理对象
     * @param method  方法
     * @param args    方法的参数
     */
    void methodAfter(Object proxy, Method method, Object[] args);

    /**
     * 目标对象方法抛出异常以后
     * @param proxy    代理对象
     * @param method   方法
     * @param args     方法的参数
     * @param e        异常
     */
    void inThrowing(Object proxy, Method method, Object[] args, Exception e);
}
