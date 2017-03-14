package gong_yong.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.myplayer.R;
import picasso.util.NetImage;
import gong_yong.widget.MyImageView;

import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/14  17:24
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ViewPagerBitImageAdapter extends PagerAdapter {
    private List<String> infoSource;

    public ViewPagerBitImageAdapter(List<String> infoSource) {
        this.infoSource = infoSource;
    }

    @Override
    public int getCount() {
        return infoSource.size();
    }


    @Override
    public View instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.picasso_item_viewpager, null);
        MyImageView photoView = (MyImageView) view.findViewById(R.id.MyImageView);
        ProgressBar pb = (ProgressBar) view.findViewById(R.id.my_pb);
//        MyImageView photoView = new MyImageView(container.getContext());
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        NetImage.showBigImag(container.getContext(),
                infoSource.get(position),
                photoView,
                pb,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
