package myXListView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.myplayer.R;

import java.util.ArrayList;
import java.util.List;

import myXListView.view.XListView;

/**
 * 创建者：wanglei
 * <p>时间：16/7/4  11:17
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class XListViewActivity extends Activity  implements XListView.IXListViewListener {
    private XListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    private XListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_xlist_view_layout);
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        mAdapter = new XListViewAdapter(this, geneItems());
        mListView.setAdapter(mAdapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    private List<String>  geneItems() {
        for (int i = 0; i != 20; ++i) {
            list.add("refresh cnt " + (++start));
        }
        return list;
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                list.clear();
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();

            }
        }, 2000);
    }
}
