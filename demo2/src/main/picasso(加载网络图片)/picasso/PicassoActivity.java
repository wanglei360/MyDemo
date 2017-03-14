package picasso;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.myplayer.R;

import gong_yong.BigImageActivity;
import picasso.adapter.PicassoAdapter;

import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/7/1  09:50
 * <p>类描述：
 * <p>修改人：
 * <p>备注：todo onConfigurationChanged重写和清单文件中要有screenSize配置。需要权限：<uses-permission android:name="android.permission.CHANGE_CONFIGURATION" />
 *           http://blog.csdn.net/xiaodongvtion/article/details/6799386
 * <p>备注：Fresco网络图片可加载gif：官方：http://www.fresco-cn.org/！介绍：http://www.muyaan.com/archives/10.html! todo 效率好像好像很高，
 * <p>备注：glide可加载gif，但是静态图好像还是picasso效率高  http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0327/2650.html
 * <p>备注：Fresco最优，glide其次，picasso在次！只有picasso不能加载gif
 * <p>备注：Fresco最优，glide其次，picasso在次！只有picasso不能加载gif
 */
public class PicassoActivity extends Activity implements AdapterView.OnItemClickListener {

    private List<String> list;
    private ListView lv;
    public int LISTIMEGRESULTCODE = 1;//ViewPager使用的ActivityResultCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picasso_layout);

        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new PicassoAdapter(this, str));
        lv.setOnItemClickListener(this);

    }

    private boolean bb = false;

    public void my_But(View view) {
        if (!bb) {//todo 当横竖屏切换的时候bb就从新等于false了，所以效果是if走两遍，else走一遍
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            bb = !bb;
            System.out.println("111111111"+bb);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            bb = !bb;
            System.out.println("222222222"+bb);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        String message = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "屏幕设置为：横屏" : "屏幕设置为：竖屏";

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(PicassoActivity.this, BigImageActivity.class);
        intent.putExtra("urls", str);
        intent.putExtra("position", position);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == LISTIMEGRESULTCODE) {
            Bundle extras = data.getExtras();
            String POSITION = "position";
            int position = extras.getInt(POSITION);
//            lv.requestFocusFromTouch();//获取焦点
            lv.setSelection(position);
        }
    }

    String[] str = {"http://img0.imgtn.bdimg.com/it/u=2020656580,2086071908&fm=21&gp=0.jpg","http://img0.bdstatic.com/img/image/wise/1%E6%B8%85%E7%BA%AF%E7%BE%8E%E5%A5%B3.jpg", "https://raw.githubusercontent.com/Frank-Zhu/PullZoomView/master/art/pull-to-zoom.gif", "http://wap.baidu.com/static/img/r/image/2014-04-18/a250be0f0af4d9dd116b4bf1f37dc5c6.jpg", "http://img0.bdstatic.com/img/image/wise/3%E8%B6%B3%E7%90%83%E5%AE%9D%E8%B4%9D.jpg", "http://img0.bdstatic.com/img/image/wise/4%E5%AB%A9%E8%90%9D%E8%8E%89.jpg", "http://img0.bdstatic.com/img/image/wise/5%E5%AE%85%E7%94%B7%E5%A5%B3%E7%A5%9E.jpg", "http://img0.bdstatic.com/img/image/wise/6%E6%B0%94%E8%B4%A8%E7%BE%8E%E5%A5%B3.jpg", "http://img0.bdstatic.com/img/image/wise/7%E5%8F%A4%E8%A3%85%E7%BE%8E%E5%A5%B3.jpg", "http://img0.bdstatic.com/img/image/wise/8%E7%BE%8E%E5%A5%B3%E8%BD%A6%E6%A8%A1.jpg", "http://img0.bdstatic.com/img/image/wise/9%E6%80%A7%E6%84%9F%E7%BE%8E%E5%A5%B3.jpg", "http://img0.bdstatic.com/img/image/wise/10%E7%99%BD%E5%AF%8C%E7%BE%8E.jpg", "http://img0.bdstatic.com/img/image/wise/11%E7%BD%91%E7%BB%9C%E7%BE%8E%E5%A5%B3.jpg", "http://img0.bdstatic.com/img/image/wise/12%E8%90%8C%E5%A6%B9%E5%AD%90.jpg", "http://img3.moko.cc/pics/guanggao/42/img3_src_11283158.jpg", "http://img3.moko.cc/pics/guanggao/9f/img3_src_11257859.jpg", "http://img3.moko.cc/pics/guanggao/b8/img3_src_11283582.jpg", "http://img3.moko.cc/pics/guanggao/79/img3_src_11274021.jpg", "http://img3.moko.cc/pics/guanggao/b7/img3_src_11249148.jpg", "http://img1qn.moko.cc/2016-06-06/058009fb-2742-4795-b783-d1148a86eea8.jpg", "http://img1qn.moko.cc/2016-06-06/d231403b-f3a4-4397-a3c7-66fdf3082a6d.jpg", "http://img1qn.moko.cc/2016-06-06/853d7ad4-761f-4aa8-896c-b581cfdf8286.jpg", "http://img1qn.moko.cc/2016-06-06/729ef6ea-298a-4799-8a9c-aae4ff1bd4e1.jpg", "http://img1qn.moko.cc/2016-06-06/2b7ba469-d2e4-4fd1-9a9c-6b00ad5c4f13.jpg", "http://img1qn.moko.cc/2016-06-06/dd521ce5-c461-4783-b5f9-658c12890f7c.jpg", "http://img1qn.moko.cc/2016-06-06/a22c4173-0090-4e13-a180-6b411c57b43b.jpg", "http://img1qn.moko.cc/2016-06-06/74390b25-8f3a-41ad-8ee9-7545ff07ad81.jpg", "http://img1qn.moko.cc/2016-06-06/56fae249-fb8c-4a8d-a439-b74d173634e2.jpg", "http://img1qn.moko.cc/2016-06-06/2238e135-b0f2-4606-8f6a-a023a9750998.jpg", "http://img1qn.moko.cc/2016-06-03/49d6ec19-0db6-4572-bbfb-11717edd35e0.jpg", "http://img1qn.moko.cc/2016-06-03/86b7514c-1bdf-481a-859a-4ba990ff7626.jpg", "http://img2.moko.cc/users/0/0/0/mtb/img2_src_10148669.jpg", "http://img2.moko.cc/users/19/5732/1719622/mtb/img2_src_9932518.jpg", "http://img3.moko.cc/users/0/148/44474/mtb/img3_src_11057770.jpg", "http://img.mb.moko.cc/2015-12-21/809e57ba-c00a-4028-9f98-2972b60c5e34.jpg", "http://img2.moko.cc/users/0/18/5503/mtb/img2_src_9114578.jpg", "http://img1.moko.cc/users/0/1/419/logo/img1_des_6633098.jpg", "http://img2.moko.cc/users/0/0/46/logo/img2_des_9115994.jpg", "http://img.mb.moko.cc/2015-11-10/717b5c90-96c8-461c-bcc5-470164a321f7.jpg", "http://img2.moko.cc/users/15/4643/1393141/logo/img2_des_10189893.jpg", "http://img2.moko.cc/users/2/689/206875/logo/img2_des_11028552.jpg", "http://img2.moko.cc/users/30/9295/2788606/logo/img2_des_10966148.jpg", "http://img2.moko.cc/users/29/8746/2623997/logo/img2_des_10663308.jpg", "http://img2.moko.cc/users/28/8697/2609264/logo/img2_des_10605063.jpg", "http://www.sinaimg.cn/dy/slidenews/1_simg/2016_23/0f58c35cc698f9ea2ca110f9c9ef7feb.jpg", "http://www.sinaimg.cn/dy/slidenews/1_t198/2016_23/2841_699139_967162.jpg", "http://www.sinaimg.cn/dy/slidenews/4_simg/2016_23/4035fc709682b9f0703b9387a74f9710.jpg", "http://www.sinaimg.cn/dy/slidenews/4_simg/2016_23/f79d0824287e6763b7b6e782cb343c65.jpg", "http://www.sinaimg.cn/dy/slidenews/1_t198/2016_23/2841_699101_998741.jpg", "http://www.sinaimg.cn/dy/slidenews/1_simg/2016_23/924c7e07cdb85d5c32959488130a7b42.jpg", "http://www.sinaimg.cn/dy/slidenews/2_simg/2016_23/18ff753a399f79a7fe99148b5b2b8694.jpg", "http://www.sinaimg.cn/dy/slidenews/1_simg/2016_23/834b9fd5c0ec43c6239532e1af42c35f.jpg", "http://www.sinaimg.cn/dy/slidenews/1_t198/2016_23/2841_698963_267034.jpg", "http://www.sinaimg.cn/dy/slidenews/4_simg/2016_23/e4cd61a941723ff258b7be992e6e37ff.jpg", "http://www.sinaimg.cn/dy/slidenews/4_simg/2016_23/6c6cfb7abe0db89bd3afe2365cb301ec.jpg", "http://www.sinaimg.cn/dy/slidenews/8_t198/2016_23/203_179767_934821.jpg", "http://www.sinaimg.cn/dy/slidenews/1_t198/2016_23/2841_699157_410741.jpg", "http://www.sinaimg.cn/dy/slidenews/2_simg/2016_23/9e6d7fe996ccddf20aa7a40eba061146.jpg", "http://www.sinaimg.cn/dy/slidenews/4_simg/2016_23/b24dd606ab42fb4c1583808ed30d870c.jpg", "http://22mm-img.xiuna.com/recpic/2016/14.jpg", "http://22mm-img.xiuna.com/recpic/2016/13.jpg", "http://22mm-img.xiuna.com/recpic/2016/12.jpg", "http://22mm-img.xiuna.com/recpic/2016/11.jpg", "http://22mm-img.xiuna.com/recpic/2016/10.jpg", "http://22mm-img.xiuna.com/recpic/2016/9.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-6-2/1/s0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-4-29/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-6-3/1/h0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-6-3/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-6-2/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-21/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-19/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-19/2/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-11/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-10/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-6/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-5-4/1/s0.jpg", "http://22mm-img.xiuna.com/pic/qingliang/2016-4-29/1/s0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-6-3/1/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-6-2/1/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-5-21/1/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-5-19/1/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-5-19/2/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-5-11/1/0.jpg", "http://22mm-img.xiuna.com/pic/jingyan/2016-5-10/1/0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-11/1/h0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-6-3/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-6-2/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-21/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-19/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-19/2/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-11/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-10/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-6/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-5-4/1/s0.jpg", "http://22mm-img.xiuna.com/pic/bagua/2016-4-29/1/s0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-6-3/1/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-6-2/1/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-5-21/1/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-5-19/2/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-5-19/1/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-5-11/1/0.jpg", "http://22mm-img.xiuna.com/pic/suren/2016-5-10/1/0.jpg"};
}
