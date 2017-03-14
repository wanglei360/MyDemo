package com.XListView.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.myplayer.R;

import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/5/4  11:21
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyAdapter extends BaseAdapter  {
    Activity activity;
    List<String> list;

    public MyAdapter(Activity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.x_listview_item, null);
        TextView tv = (TextView) view.findViewById(R.id.list_item_textview);
        tv.setText(list.get(position)+position);
//        setMyOnTouchListener(view,position);
        return view;
    }

    float downPointX;
    float downPointY;
    float upPointX;
    float upPointY;

//    public void setMyOnTouchListener(View convertView, final int position) {
//        convertView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    downPointX = event.getX();
//                    downPointY = event.getY();
//                    upPointX = event.getX();
//                    upPointY = event.getY();
//                }
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    upPointX = event.getX();
//                    upPointY = event.getY();
//                }
//                TextView tv_del = (TextView) v.findViewById(R.id.tv_delete_note_item);
//                if(downPointX - upPointX >= 20.0){
//                    tv_del.setVisibility(View.VISIBLE);
//                    tv_del.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(activity,""+String.valueOf(position),Toast.LENGTH_SHORT).show();
//                            mDeleteNoteItemCallback.onDeleteNoteItem(noteList.get(position));
//                        }
//                    });
//                }else if(upPointX - downPointX >= 20.0){
//                    tv_del.setVisibility(View.GONE);
//                }else if(upPointX == downPointX){
////                    mDeleteNoteItemCallback.onClickNoteItem(position, (noteList.get(position)));
//                }
//                return true;
//            }
//        });    }
}
