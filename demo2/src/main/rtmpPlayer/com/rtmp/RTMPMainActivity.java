package com.rtmp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.myplayer.R;
import com.pili.pldroid.player.PLNetworkManager;
import com.rtmp.adapter.RTMPAdapter;
import com.rtmp.bean.RtmpBean;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/9/1  18:37
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.WAKE_LOCK" />
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * <p/>
 * libs中添加 pldroid-player-1.3.0.jar
 * <p/>
 * compile files('libs/pldroid-player-1.3.0.jar')
 * compile 'com.qiniu:happy-dns:0.2.+'
 */
public class RTMPMainActivity extends Activity {

    private Spinner mActivitySpinner;
    private EditText mEditText;
    private RadioGroup mStreamingTypeRadioGroup;
    private RadioGroup mDecodeTypeRadioGroup;

    public static final String[] TEST_ACTIVITY_ARRAY = {
            "PLMediaPlayerActivity",
            "PLAudioPlayerActivity",
            "PLVideoViewActivity",
            "PLVideoTextureActivity",
            "VideoViewActivity"
    };
    private List<RtmpBean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtmp_main_layout);
        initList();

        mEditText = (EditText) findViewById(R.id.VideoPathEdit);

        mStreamingTypeRadioGroup = (RadioGroup) findViewById(R.id.StreamingTypeRadioGroup);
        mDecodeTypeRadioGroup = (RadioGroup) findViewById(R.id.DecodeTypeRadioGroup);

        mActivitySpinner = (Spinner) findViewById(R.id.TestSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TEST_ACTIVITY_ARRAY);
        mActivitySpinner.setAdapter(adapter);
    }


    private void initList() {
        list = new ArrayList<>();
        list.add(new RtmpBean("hks", "rtmp://live.hkstv.hk.lxdns.com/live/hks"));
        list.add(new RtmpBean("公司", "rtmp://rtmp.bandu.in:1935/mytv/ding"));
        list.add(new RtmpBean("CCTV1", "rtmp://203.207.99.19:1935/live/CCTV1"));
        list.add(new RtmpBean("CCTV2", "rtmp://203.207.99.19:1935/live/CCTV2"));
        list.add(new RtmpBean("CCTV4", "rtmp://203.207.99.19:1935/live/CCTV4"));
        list.add(new RtmpBean("CCTV5", "rtmp://203.207.99.19:1935/live/CCTV5"));
        list.add(new RtmpBean("中央6", "rtmp://203.207.99.19:1935/live/CCTV7"));
        list.add(new RtmpBean("cctv15", "rtmp://58.200.131.2:1935/livetv/cctv15"));
        list.add(new RtmpBean("CCTVnews", "rtmp://58.200.131.2:1935/livetv/cctv16"));
        list.add(new RtmpBean("深圳卫视", "rtmp://tv.sznews.com:1935/live/live_240_mc43"));
        list.add(new RtmpBean("深圳娱乐", "rtmp://tv.sznews.com:1935/live/live_233_mc43"));
        list.add(new RtmpBean("湖南卫视", "rtmp://203.207.99.19:1935/live/zgjyt"));
        list.add(new RtmpBean("江苏卫视", "rtmp://203.207.99.19:1935/live/CCTV13"));
        list.add(new RtmpBean("兵团卫视", "rtmp://v.btzx.com.cn/live/weishi.stream"));
        list.add(new RtmpBean("西藏卫视", "rtmp://video.vtibet.com/masvod/hanyuTV_q1"));
        ListView listView = (ListView) findViewById(R.id.rtmp_list_view);
        listView.setAdapter(new RTMPAdapter(this, list));
        //todo 必须手动调用一次，否则默认是listView在顶部
        ScrollView sv = (ScrollView) findViewById(R.id.rtmp_sv);
        sv.smoothScrollTo(0, 0);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = list.get(i).url;
                String[] strings = {url};
                try {
                    PLNetworkManager.getInstance().startDnsCacheService(RTMPMainActivity.this, strings);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                jumpToPlayerActivity(url);
            }
        });
    }

    public void onClickLocalFile(View v) {//本地的那个按钮
        Intent intent = new Intent(this, VideoFileActivity.class);
        startActivityForResult(intent, 0);
    }

    public void jumpToPlayerActivity(String videopath) {
        Class<?> cls = null;
        switch (mActivitySpinner.getSelectedItemPosition()) {
            case 0:
                cls = PLMediaPlayerActivity.class;
                break;
            case 1:
                cls = PLAudioPlayerActivity.class;
                break;
            case 2:
                cls = PLVideoViewActivity.class;
                break;
            case 3:
                cls = PLVideoTextureActivity.class;
                break;
            case 4:
                cls = VideoViewActivity.class;
                break;
            default:
                return;
        }
        Intent intent = new Intent(this, cls);
        intent.putExtra("videoPath", videopath);
        if (mDecodeTypeRadioGroup.getCheckedRadioButtonId() == R.id.RadioHWDecode) {
            intent.putExtra("mediaCodec", 1);
        } else {
            intent.putExtra("mediaCodec", 0);
        }
        if (mStreamingTypeRadioGroup.getCheckedRadioButtonId() == R.id.RadioLiveStreaming) {
            intent.putExtra("liveStreaming", 1);
        } else {
            intent.putExtra("liveStreaming", 0);
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        String videoPath = data.getStringExtra("videoPath");
        mEditText.setText(videoPath, TextView.BufferType.EDITABLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PLNetworkManager.getInstance().stopDnsCacheService(this);
    }
}
