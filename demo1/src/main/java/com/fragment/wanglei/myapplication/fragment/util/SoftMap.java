package com.fragment.wanglei.myapplication.fragment.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import android.util.Log;

/**
 * 软引用Map集合
 *
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class SoftMap<K, V> extends HashMap<K, V> {
	private static final String TAG = "SoftMap";

	// 将强引用+占用内存较多 转换成 软引用对象

	// ①转换强引用+占用内存较多对象（手机），装到袋子里
	// ②清理掉没用的空袋子

	private HashMap<K, SoftValue<K, V>> temp;// 强应用袋子的集合

	private ReferenceQueue<V> queue;// 空袋子的应用队列

	public SoftMap() {
		// Object o = new Object();// 强引用+占用内存较多
		// SoftReference sr = new SoftReference(o);// 将o的应用级别降低

		temp = new HashMap<K, SoftValue<K, V>>();
		queue = new ReferenceQueue<V>();
	}

	@Override
	public V put(K key, V value) {
		clearSR();
		// 转换强引用+占用内存较多对象（手机），装到袋子里
		SoftValue<K, V> sr = new SoftValue<K, V>(key, value, queue);
		temp.put(key, sr);
		return null;
	}

	@Override
	public V get(Object key) {
		clearSR();
		SoftValue<K, V> sr = temp.get(key);// 袋子
		if (sr != null) {
			// 如果此引用对象已经由程序或垃圾回收器清除，则此方法将返回 null。
			return sr.get();
		}
		return null;
	}

    public void removeAll(){
        temp.clear();
        System.out.println();
    }

	@Override
	public int size() {
		return temp.size();
	}

	@Override
	public boolean containsKey(Object key) {
		clearSR();
		// 何为真正的？
		SoftValue<K, V> sr = temp.get(key);
		if (sr != null) {
			V v = sr.get();
			// if (v != null) {
			// return true;
			// }
			return v != null;
		}
		return false;
	}

	/**
	 * 清理空袋子
	 */
	private void clearSR() {
		System.gc();
		// 方案一：循环temp，发现空袋清除
		// 如果内存充足，那么循环——无用

		// 方案二：循环自己的集合，GC在回收手机的时候，将空袋子的引用添加到该集合中
		// 集合
		// 轮询此队列，查看是否存在可用的引用对象。
		// 如果存在一个立即可用的对象，则从该队列中"移除"此对象并"返回"。否则此方法立即返回 null。
		SoftValue<K, V> sr = (SoftValue<K, V>) queue.poll();
		Reference<? extends V> poll = queue.poll();
		while (sr != null) {
			temp.remove(sr.key);
			Log.i(TAG, sr.key.toString());
			sr = (SoftValue<K, V>) queue.poll();
		}
	}

	/**
	 * 加强版袋子
	 *
	 * @author Administrator
	 *
	 * @param <K>：记录key
	 * @param <V>
	 */
	private class SoftValue<K, V> extends WeakReference<V> {
		Object key;

		public SoftValue(K k, V v, ReferenceQueue<? super V> q) {
			super(v, q);
			this.key = k;
		}

	}
}
