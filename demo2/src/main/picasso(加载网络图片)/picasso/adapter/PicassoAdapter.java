package picasso.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.myplayer.R;
import picasso.util.NetImage;

/**
 * 创建者：wanglei
 * <p>时间：16/6/7  11:16
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class PicassoAdapter extends BaseAdapter {
    String[] lists;
    Activity mainActivity;

    public PicassoAdapter(Activity mainActivity, String[] lists) {
        this.lists = lists;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return lists.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.picasso_item_adapter, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.pb = (ProgressBar) convertView.findViewById(R.id.pb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = lists[position];
        NetImage.showListImag(mainActivity,
                url,
                holder.iv,
                holder.pb,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                false, 0);
        holder.tv.setText(url);
        return convertView;
    }

    public class ViewHolder {

        private TextView tv;

        private ImageView iv;

        public ProgressBar pb;
    }


}
