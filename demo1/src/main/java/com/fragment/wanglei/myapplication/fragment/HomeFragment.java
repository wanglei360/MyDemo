package com.fragment.wanglei.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.activity.BigImageActivity;
import com.fragment.wanglei.myapplication.activity.BigImageActivity_1;
import com.fragment.wanglei.myapplication.activity.MainActivity;
import com.fragment.wanglei.myapplication.adapter.ImageListAdapter;
import com.fragment.wanglei.myapplication.base.BaseFragment;
import com.fragment.wanglei.myapplication.imp.FragmentResume;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建者：wanglei
 * 时间：16/3/10  10:59
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@ContentView(R.layout.home_layout)
public class HomeFragment extends BaseFragment implements FragmentResume {


    @ViewInject(R.id.lv)
    private ListView imageListView;
    @ViewInject(R.id.progress)
    private FrameLayout progress;
    private ImageListAdapter imageListAdapter;
    private boolean isViewPagerBack = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MainActivity activity = (MainActivity) getActivity();
        activity.fc.setOnResume(this, this.getClass());
        imageListAdapter = new ImageListAdapter(getActivity());
        imageListView.setAdapter(imageListAdapter);
        loadImag();
        ee = true;
        Button but = (Button) view.findViewById(R.id.My_but_112233);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> imgSrcList = imageListAdapter.getImgSrcList();
                for (String s : imgSrcList) {
                    System.out.print(s);
                }
                String str = null;
                for (int x = 0; x < imgSrcList.size(); x++) {
                    if (x != imgSrcList.size() - 1) {
                        str += "\""+imgSrcList.get(x) +"\""+ ",";
                    } else {
                        str += "\""+imgSrcList.get(x)+"\"";
                    }
                }
                System.out.println(str);
                Toast.makeText(activity,str,Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean ee;

//    public void My_but_112233(View view) {
//
//    }

    @Override
    public void onMyResume() {
        if (!ee) {
            MainActivity activity = (MainActivity) getActivity();
            activity.fc.setOnResume(this, this.getClass());
            imageListAdapter = new ImageListAdapter(getActivity());
            imageListView.setAdapter(imageListAdapter);
            loadImag();
            ee = true;
        }
        loadImag();
    }

    public void loadImag() {
        if (!isViewPagerBack) {
            progress.setVisibility(View.VISIBLE);
            // 加载url请求返回的图片连接给listview
            // 这里只是简单的示例，并非最佳实践，图片较多时，最好上拉加载更多...
            for (String url : imgSites) {
                loadImgList(url);
            }
            isViewPagerBack = false;
        }
    }

    @Override
    public void onPause() {
        System.out.println();
        if (!isViewPagerBack) {
            progress.setVisibility(View.VISIBLE);
            // 加载url请求返回的图片连接给listview
            // 这里只是简单的示例，并非最佳实践，图片较多时，最好上拉加载更多...
            for (String url : imgSites) {
                loadImgList(url);
            }
            isViewPagerBack = false;
        }
        super.onPause();
    }


    /**
     * 必须私有，否则无效
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Event(value = R.id.lv, type = AdapterView.OnItemClickListener.class)
    private void onImageItemClick(AdapterView<?> parent, View view, int position, long id) {
//       goToOneImageActivity(position);//显示一张图片放大
        goToListImageActivity(position);//显示全部图片放大
    }


    /**
     * 显示全部图片放大
     *
     * @param position
     * @return
     */
    private void goToListImageActivity(int position) {
        Intent intent = new Intent(this.getActivity(), BigImageActivity_1.class);
        intent.putStringArrayListExtra("list", imageListAdapter.imgSrcList);
        intent.putExtra("position", position);
        this.getActivity().startActivityForResult(intent, 1);
    }

    /**
     * 显示一张图片放大
     *
     * @param position
     * @return
     */
    private void goToOneImageActivity(int position) {
        Intent intent = new Intent(this.getActivity(), BigImageActivity.class);
        intent.putExtra("url", imageListAdapter.imgSrcList.get(position));
        this.getActivity().startActivity(intent);
    }

    /**
     * 从ViewPager的图片那边返回时ViewPager显示的是哪个这边从新显示时就显示哪个
     *
     * @param position ViewPager的图片那边返回时ViewPager显示到第几个了的标识
     */
    @Override
    public void setListShowAppointItem(int position) {
        imageListView.setSelection(position);
        isViewPagerBack = true;
    }


    private String[] imgSites = {
            "http://image.baidu.com/",
            "http://www.22mm.cc/",
            "http://www.moko.cc/",
            "http://eladies.sina.com.cn/photo/",
            "http://www.youzi4.com/"
    };


    private void loadImgList(String url) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                imageListAdapter.addSrc(getImgSrcList(result));
                imageListAdapter.notifyDataSetChanged();//通知listview更新数据
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.print("");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                System.out.print("");
            }

            @Override
            public void onFinished() {
                System.out.print("");
            }
        });
    }

    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgSrcList(String htmlStr) {
        List<String> pics = new ArrayList<String>();

        String regEx_img = "<img.*?src=\"http://(.*?).jpg\""; // 图片链接地址
        Pattern p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        Matcher m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            String src = m_image.group(1);
            if (src.length() < 100) {
                pics.add("http://" + src + ".jpg");
                //pics.add("http://f.hiphotos.baidu.com/zhidao/pic/item/2fdda3cc7cd98d104cc21595203fb80e7bec907b.jpg");
            }
        }
        return pics;
    }
}
