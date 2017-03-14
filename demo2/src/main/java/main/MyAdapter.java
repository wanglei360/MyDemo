package main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplayer.R;

import java.util.ArrayList;

/**
 * 创建者：wanglei
 * <p>时间：16/8/17  18:22
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyAdapter extends BaseAdapter {
    mainActivity mainActivity;
    ArrayList<String> list;

    public MyAdapter(mainActivity mainActivity, ArrayList<String> list) {
        this.mainActivity = mainActivity;
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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder hoder;
        if (view == null) {
            hoder = new ViewHoder();
            view = View.inflate(mainActivity, R.layout.item, null);
            hoder.tv = (TextView) view.findViewById(R.id.item_tv);
            view.setTag(hoder);
        } else {
            hoder = (ViewHoder) view.getTag();
        }
        hoder.tv.setText(list.get(position));
        return view;
    }

    public class ViewHoder {
        TextView tv;
    }
}
