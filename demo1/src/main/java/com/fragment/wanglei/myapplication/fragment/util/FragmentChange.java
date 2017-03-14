package com.fragment.wanglei.myapplication.fragment.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.LruCache;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseFragment;

import java.util.List;

/**
 * 在activity中布局里面的那个fragment的地方的布局id要是fragment，本类中有使用
 */
public class FragmentChange {


    public static LruCache<Integer, BaseFragment> fragmentMap;
    private static FragmentChange instance;
    private FragmentManager sfManager;

    private FragmentChange(FragmentActivity activity) {
        sfManager = activity.getSupportFragmentManager();
    }

    public static FragmentChange getInstance(FragmentActivity activity) {
//        if (instance == null) {
//            synchronized (FragmentChange.class) {
                if (instance == null) {
                    instance = new FragmentChange(activity);
                    //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
                    int maxMemory = (int) Runtime.getRuntime().maxMemory();
                    long l = Runtime.getRuntime().totalMemory();
                    int mCacheSize = maxMemory / 15;
                    fragmentMap = new LruCache<Integer, BaseFragment>(1){
                        @Override
                        protected void entryRemoved(boolean evicted, Integer key, BaseFragment oldValue, BaseFragment newValue) {
                            super.entryRemoved(evicted, key, oldValue, newValue);

                            System.out.println();
                        }
                    };
                }
//            }
//        }
        return instance;
    }

    /**
     * 进入某一个fragment
     *
     * @param c fragment字节码
     * @return true是进入成功，false是进入失败
     */
    public BaseFragment fragmentChange(Class c) {
        BaseFragment fragment = getFragment(c);
        boolean b = clearSelection(fragment);
        if(b)
            return fragment;
        return null;
    }


    private BaseFragment getFragment(Class c) {
        String fragmentName = c.getName();
        if (fragmentMap.size() > 0) {//如果集合中有东西
            int i = fragmentName.hashCode();
            BaseFragment fragment = fragmentMap.get(i);
            if (null != fragment)
                return fragment;
        }
        return addFragmentToMap(fragmentName);
    }


    private boolean clearSelection(Fragment fragment) {
        try {
            FragmentTransaction ftc = sfManager.beginTransaction();
            if (null != fragment) {
                hideFragments(sfManager, ftc);
                if (!fragment.isAdded())
                    ftc.add(R.id.fragment, fragment);
                ftc.show(fragment).addToBackStack(null);
                ftc.commitAllowingStateLoss();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往集合中添加fragment
     *
     * @param fragmentName fragment字节码的名字
     * @return 返回一个fragment
     */
    private BaseFragment addFragmentToMap(String fragmentName) {
        BaseFragment fragment;
        try {
            fragment = (BaseFragment) Class.forName(fragmentName).newInstance();
            if (null != fragment) {
                fragmentMap.put(fragmentName.hashCode(), fragment);
                return fragment;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 隐藏所有的fragment
     *
     * @param sfManager fra管理器
     * @param ftc       fragment的事物管理器
     */
    private void hideFragments(FragmentManager sfManager, FragmentTransaction ftc) {
        List<Fragment> fragments = sfManager.getFragments();
        if (fragments != null)
            for (Fragment f : fragments)
                hideFragment(f, ftc);
    }

    /**
     * 隐藏传进来的这个fragment
     *
     * @param fragment 需要隐藏fragment
     * @param ftc      fragment事物管理器
     */
    private void hideFragment(Fragment fragment, FragmentTransaction ftc) {
        if (fragment != null && !fragment.isHidden()) {
//            ftc.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ftc.hide(fragment);
        }
    }
}
