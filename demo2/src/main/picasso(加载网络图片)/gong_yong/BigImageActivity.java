package gong_yong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.myplayer.R;
import gong_yong.adapter.ViewPagerBitImageAdapter;
import gong_yong.widget.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/6/7  14:56
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class BigImageActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private List<String> infoSource;
    private String POSITION = "position";//上一个页面列表图片显示到了哪一张的标识
    private int myPosition;//当前显示的哪个图片
    public int LISTIMEGRESULTCODE = 1;//ViewPager使用的ActivityResultCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picasso_big_img_layout);
        infoSource = new ArrayList<>();
        myPosition = initDataSource();

        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new ViewPagerBitImageAdapter(infoSource));
        mViewPager.setCurrentItem(myPosition);//设置当前Viewpager显示的是第几个图片
        mViewPager.addOnPageChangeListener(this);//添加当前显示到第几张了的监听
    }

    private int initDataSource() {
        Intent intent = getIntent();
        String[] urlses = intent.getStringArrayExtra("urls");
        for (int x = 0; x < urlses.length; x++) {
            infoSource.add(urlses[x]);
        }
        return intent.getIntExtra(POSITION, 0);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {//获取当前Viewpager的position
        myPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt(POSITION, myPosition);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(LISTIMEGRESULTCODE, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
