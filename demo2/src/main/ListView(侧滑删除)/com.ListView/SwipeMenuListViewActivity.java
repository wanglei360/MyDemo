package com.ListView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ListView.widget.SwipeMenu;
import com.ListView.widget.SwipeMenuCreator;
import com.ListView.widget.SwipeMenuItem;
import com.ListView.widget.SwipeMenuListView;
import com.myplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/6/30  15:24
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：条目高于60dp没有问题，否则
 */
public class SwipeMenuListViewActivity extends Activity {
    private List<String> list;
    private List<Integer> list1;
    private SwipeMenuListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_menu_listview_layout);

        list = new ArrayList<>();
        list1 = new ArrayList<>();
        lv = (SwipeMenuListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter(this, list));

        for (int x = 0; x < 30; x++) {
            list.add(String.valueOf(x));
            if(x%2==0)
                list1.add(x);
        }
        lv.setPositionList(list1);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
//                 create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                int id = openItem.getId();
                showToast("id = "+id);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(13);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        lv.setMenuCreator(creator);

        setMyMenuItemClick();
        setMySwipeListener();
        setMyMenuStateChangeListener();
        setMyItemLongClick();
    }

    private void setMyItemLongClick() {// test item long click
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                showToast("ItemLongClickListener   position = " + position);
                return false;
            }
        });
    }

    private void setMyMenuStateChangeListener() {// set MenuStateChangeListener
        lv.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
//                showToast("MenuStateChangeListener   onMenuOpen  position = " + position);
            }

            @Override
            public void onMenuClose(int position) {
//                showToast("MenuStateChangeListener   onMenuClose  position = " + position);
            }
        });
    }

    private void setMySwipeListener() {
        // set SwipeListener
        lv.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                showToast("SwipeListener   onSwipeStart  position = " + position);
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                showToast("SwipeListener   onSwipeEnd  position = " + position);
            }
        });
    }

    private void setMyMenuItemClick() {//taskComplete
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                String item = list.get(position);
                switch (index) {
                    case 0:
                        // open
//                        open(item);
//                        Toast.makeText(SwipeMenuListViewActivity.this, "open", Toast.LENGTH_SHORT).show();
//                        showToast("ItemClick   open");
                        break;
                    case 1:
//                        showToast("ItemClick   delete");
                        // delete
//					delete(item);
//                        mAppList.remove(position);
//                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private void showToast(String str) {
        Toast.makeText(SwipeMenuListViewActivity.this, str, Toast.LENGTH_SHORT).show();

    }
    boolean isTouch;
}
