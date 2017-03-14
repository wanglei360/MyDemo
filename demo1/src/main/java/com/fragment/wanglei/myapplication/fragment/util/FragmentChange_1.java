package com.fragment.wanglei.myapplication.fragment.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseFragment;
import com.fragment.wanglei.myapplication.imp.FragmentResume;

import java.util.List;

/**
 * 在activity中布局里面的那个fragment的地方的布局id要是fragment，本类中有使用
 */
public class FragmentChange_1 {


    public static SoftMap<Integer, BaseFragment> fragmentMap;
    private static FragmentChange_1 instance;
    private FragmentManager sfManager;
    private FragmentResume onResume;
    private Class aClass;

    private FragmentChange_1(FragmentActivity activity) {
        sfManager = activity.getSupportFragmentManager();
    }

    public static FragmentChange_1 getInstance(FragmentActivity activity) {
        if (instance == null) {
            instance = new FragmentChange_1(activity);
            fragmentMap = new SoftMap<>();
        }
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
        if (b)
            return fragment;
        return null;
    }

    /**
     * 自定义fragment的获取焦点生命周期
     * @param onResume  要实现FragmentResume接口传进来
     * @param aClass    当前fragment的字节码
     */
    public void setOnResume(FragmentResume onResume, Class aClass) {
        this.onResume = onResume;
        this.aClass = aClass;
    }

    /** TODO
     * 关闭当前fragment所在的activity时要调用这个方法，
     * 否则下次启动该activity时所有的fragment不显示，
     * 不走onViewCreated这样的方法
     */
    public void destroy(){
        fragmentMap.removeAll();
        instance = null;
        sfManager = null;
        onResume = null;
        aClass = null;
    }

    private BaseFragment getFragment(Class c) {
        String fragmentName = c.getName();
        BaseFragment fragment = fragmentMap.get(fragmentName.hashCode());
        if (null != fragment) {
            setMyResume(c);
            return fragment;
        }
        return addFragmentToMap(fragmentName);
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
                setMyResume(fragment.getClass());
                return fragment;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private boolean clearSelection(BaseFragment fragment) {
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

    private void setMyResume(Class c) {
        if (null != onResume) {
            if (c == aClass)
                onResume.onMyResume();
        }
    }
}
