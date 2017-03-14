package com.fragment.wanglei.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.adapter.ViewPagerBitImageAdapter;
import com.fragment.wanglei.myapplication.base.BaseActivityImage;
import com.fragment.wanglei.myapplication.widget.HackyViewPager;

import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/3/22  17:19
 * 类描述：列表形式的图片放大，用Intent传进来一堆网络图片的地址，键为list，就可点击放大
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BigImageActivity_1 extends BaseActivityImage implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private List<String> infoSource;
    private String POSITION = "position";//上一个页面列表图片显示到了哪一张的标识
    private int myPosition;//当前显示的哪个图片

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.big_imag_pager_layou);

        myPosition = initDataSource();

        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerBitImageAdapter(infoSource, imageOptions));
        mViewPager.setCurrentItem(myPosition);//设置当前Viewpager显示的是第几个图片
        mViewPager.addOnPageChangeListener(this);//添加当前显示到第几张了的监听
    }

    private int initDataSource() {
        Intent intent = getIntent();
        infoSource = intent.getStringArrayListExtra("list");
        return intent.getIntExtra(POSITION, 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt(POSITION,myPosition);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(LISTIMEGRESULTCODE, intent);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onPageSelected(int position) {//获取当前Viewpager的position
        myPosition = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
}
