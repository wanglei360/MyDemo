package com.fragment.wanglei.myapplication.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建者：wanglei
 * <p>时间：16/4/27  12:43
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class ThreadManager {
    //单例  非懒加载模式 试图让每次线程均开启新的
    private ThreadManager(){}
    private static ThreadManager instance = new ThreadManager();
    //线程池需要的参数 照AsyncTask抄就ok了.
    //返回给虚拟机可用的处理器核数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //管理多少个线程
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //如果排队满了, 额外的开的线程数
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    public static ThreadManager getInstance(){
        return instance;
    }
    private ThreadPoolHelper helper;

    public synchronized ThreadPoolHelper createHelper(){
        if(helper == null)
            helper = new ThreadPoolHelper(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 5000L);
        return helper;
    }

    public class ThreadPoolHelper{
        private ThreadPoolExecutor pool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long time;

        public ThreadPoolHelper(int corePoolSize, int maximumPoolSize, long time) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.time = time;
        }

        /**
         * 执行任务
         * @param runnable
         */
        public void execute(Runnable runnable) {
            if (pool == null) {
                // 创建线程池
				/*
				 * 1. 线程池里面管理多少个线程
				 * 2. 如果排队满了, 额外的开的线程数
				 * 3. 如果线程池没有要执行的任务 存活多久
				 * 4. 时间的单位
				 * 5  如果线程池里管理的线程都已经用了,剩下的任务 临时存到LinkedBlockingQueue对象中 排队
				 */
                pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));
            }
            pool.execute(runnable); // 调用线程池 执行异步任务
        }

        /**
         * 取消任务
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (pool != null && !pool.isShutdown() && !pool.isTerminated()) {
                pool.remove(runnable); // 取消异步任务
            }
        }
    }

}
