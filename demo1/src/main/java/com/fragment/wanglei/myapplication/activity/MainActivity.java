package com.fragment.wanglei.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.base.BaseActivity;
import com.fragment.wanglei.myapplication.base.BaseFragment;
import com.fragment.wanglei.myapplication.fragment.DiscoverFragment;
import com.fragment.wanglei.myapplication.fragment.HomeFragment;
import com.fragment.wanglei.myapplication.fragment.MySelfFragment;
import com.fragment.wanglei.myapplication.fragment.TeachereFragment;
import com.fragment.wanglei.myapplication.fragment.util.FragmentChange_1;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;



@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {


    @ViewInject(R.id.home)
    private ImageButton home;

    @ViewInject(R.id.teacher)
    private ImageButton teacher;

    @ViewInject(R.id.discover)
    private ImageButton discover;

    @ViewInject(R.id.myself)
    private ImageButton myself;

    public FragmentChange_1 fc;
    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fc = FragmentChange_1.getInstance(this);
        fragment = fc.fragmentChange(HomeFragment.class);
        buttomButSelected(home);
    }

    @Override
    protected void onDestroy() {
        fc.destroy();
        super.onDestroy();
    }

    /**
     * 点击的方法必须私有(private)，否则无效
     */
    @Event(value = R.id.home, type = View.OnClickListener.class)
    private void buttomHome(View view) {
        buttomButSelected(home);
        fragment = fc.fragmentChange(HomeFragment.class);
        System.out.println();
    }

    /**
     * 点击的方法必须私有(private)，否则无效
     */
    @Event(value = R.id.discover, type = View.OnClickListener.class)
    private void buttomDiscover(View view) {
        buttomButSelected(discover);
        fc.fragmentChange(DiscoverFragment.class);
    }

    /**
     * 点击的方法必须私有(private)，否则无效
     */
    @Event(value = R.id.teacher, type = View.OnClickListener.class)
    private void buttomTeacher(View view) {
        buttomButSelected(teacher);
        fc.fragmentChange(TeachereFragment.class);
    }

    /**
     * 点击的方法必须私有(private)，否则无效
     */
    @Event(value = R.id.myself, type = View.OnClickListener.class)
    private void buttomMyself(View view) {
        buttomButSelected(myself);
        fc.fragmentChange(MySelfFragment.class);
    }

    private void buttomButSelected(ImageButton but) {
        home.setSelected(false);
        discover.setSelected(false);
        teacher.setSelected(false);
        myself.setSelected(false);
        but.setSelected(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == LISTIMEGRESULTCODE) {
            Bundle extras = data.getExtras();
            String POSITION = "position";
            int position = extras.getInt(POSITION);
            fragment.setListShowAppointItem(position);
        }
    }
}
