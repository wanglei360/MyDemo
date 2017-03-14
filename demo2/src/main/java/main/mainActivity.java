package main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.GetApplicationKeyInfo.GetApplicationKeyInfo;
import com.broadcast.EventBusAndBroadcastActivity;
import com.FoldingLineLActivity;
import com.ListView.SwipeMenuListViewActivity;
import com.MediaPlayer.MediaPlayerActivity;
import com.PhotographActivity;
import com.ScrollViewListView.ScrollViewListViewActivity;
import com.XListView.XListViewActivity;
import com.dialog.DialogActivity;
import com.download.MyDownloadActivity;
import com.exception.ExceptionActivity;
import com.graph.GraphActivity;
import com.myFile.FileActivity;
import com.myVR.VRMainActivity;
import com.mylabel.LabelActivity;
import com.myplayer.R;
import com.myplayer.VideoActivityMain;
import com.recyclerview.RecyclerViewMainActivity;
import com.rtmp.RTMPMainActivity;
import com.telephone_book.AppleTelephoneBookDemoActivity;

import picasso.PicassoActivity;

import java.util.ArrayList;

import db.dbActivity;
import webView.WebViewDemo;

/**
 * 创建者：wanglei
 * <p>时间：16/6/16  17:11
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class mainActivity extends Activity implements AdapterView.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this, initInfo()));
        lv.setOnItemClickListener(this);
    }

    /**
     * onStart, onResume, onCreate都不是真正visible的时间点，真正的visible时间点是onWindowFocusChanged()函数被执行时。
     * todo onWindowFocusChanged指的是这个Activity得到或者失去焦点的时候 就会调用。
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(this,"我被调用了",Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> initInfo() {
        ArrayList<String> list = new ArrayList<>();
        list.add("声音播放");//0
        list.add("视频播放");//1
        list.add("webView回调Android响应");//2
        list.add("greenDao数据库");//3
        list.add("dialog_Activity");//4
        list.add("XListViewActivity");//5
        list.add("柱状图RecyclerView");//6
        list.add("抓取全局异常");//7
        list.add("ListView侧滑删除");//8
        list.add("picasso加载网络图片");//9
        list.add("动态添加标签");//10
        list.add("序列化文件到本地");//11
        list.add("原生的XlistView");//12
        list.add("ScrollView嵌套listView");//13
        list.add("拍照");//14
        list.add("广播和EventBus");//15
        list.add("TextView折行");//16
        list.add("RecyclerView");//17
        list.add("VR");//18
        list.add("自己写的下载");//19
        list.add("rtmp播放");//20
        list.add("ListView苹果电话本样式的");//21
        list.add("获取应用签名的的信息");//22
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                goActivity(MediaPlayerActivity.class);
                break;
            case 1:
                goActivity(VideoActivityMain.class);
                break;
            case 2:
                goActivity(WebViewDemo.class);
                break;
            case 3:
//                Toast.makeText(this, "需要重新配置Application", Toast.LENGTH_LONG).show();
                goActivity(dbActivity.class);
                break;
            case 4:
                goActivity(DialogActivity.class);
                break;
            case 5:
                goActivity(XListViewActivity.class);
                break;
            case 6:
                goActivity(GraphActivity.class);
                break;
            case 7:
                Toast.makeText(this, "需要重新配置Application", Toast.LENGTH_LONG).show();
                goActivity(ExceptionActivity.class);
                break;
            case 8:
                goActivity(SwipeMenuListViewActivity.class);
                break;
            case 9:
                goActivity(PicassoActivity.class);
                break;
            case 10:
                goActivity(LabelActivity.class);
                break;
            case 11:
                goActivity(FileActivity.class);
                break;
            case 12:
                goActivity(myXListView.XListViewActivity.class);
                break;
            case 13:
                goActivity(ScrollViewListViewActivity.class);
                break;
            case 14:
                goActivity(PhotographActivity.class);
                break;
            case 15:
                goActivity(EventBusAndBroadcastActivity.class);
                break;
            case 16:
                goActivity(FoldingLineLActivity.class);
                break;
            case 17:
                goActivity(RecyclerViewMainActivity.class);
                break;
            case 18:
                goActivity(VRMainActivity.class);
                break;
            case 19:
                goActivity(MyDownloadActivity.class);
                break;
            case 20:
                goActivity(RTMPMainActivity.class);
                break;
            case 21:
                goActivity(AppleTelephoneBookDemoActivity.class);
                break;
            case 22:
                goActivity(GetApplicationKeyInfo.class);
                break;
        }
    }

    public void goActivity(Class<?> cls) {//311
        startActivity(new Intent(this, cls));
    }
}
