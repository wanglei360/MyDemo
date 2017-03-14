package myXListView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.myplayer.R;

import java.util.List;

/**
 * 创建者：wanglei
 * <p>时间：16/7/4  11:23
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class XListViewAdapter extends BaseAdapter {

    Activity activity;
    List<String> list;

    public XListViewAdapter(Activity activity, List<String> list) {
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
        return view;
    }
}
