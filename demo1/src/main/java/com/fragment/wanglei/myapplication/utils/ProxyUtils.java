package com.fragment.wanglei.myapplication.utils;

import com.fragment.wanglei.myapplication.utils.proxy.Advice;
import com.fragment.wanglei.myapplication.utils.proxy.DefAdviceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建者：wanglei
 * <p>时间：16/4/27  13:09
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ProxyUtils {

    public static <T> T newTInstance(T t){
        return newTInstance(t, new DefAdviceImpl());
    }

    /**
     * 根据 目标对象 获取 代理对象
     * @param target 目标对象
     * @param advice 建议做的事
     * @param <T> 目标泛型
     * @return 目标的代理对象
     */
    public static <T> T newTInstance(final T target, final Advice advice) {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object o = null;
                try {
                    advice.methodBefore(proxy, method, args);
                    o = method.invoke(target, args);
                    advice.methodAfter(proxy, method, args);
                } catch (Exception e) {
                    advice.inThrowing(proxy, method, args, e);
                }
                return o;
            }
        });
    }

}
