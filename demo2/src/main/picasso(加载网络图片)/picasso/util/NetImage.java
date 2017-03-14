package picasso.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import gong_yong.util.CircleTransform;

/**
 * 创建者：wanglei
 * <p>时间：16/6/7  14:44
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class NetImage {
    /**
     * Picasso默认会使用设备的15%的内存作为内存图片缓存，且现有的api无法清空内存缓存。
     * 我们可以在查看大图时放弃使用内存缓存，图片从网络下载完成后会缓存到磁盘中，加载会从磁盘中加载，
     * 这样可以加速内存的回收。memoryPolicy
     *
     * @param context       Context
     * @param url           图片的网络地址
     * @param iv            需要加载图片的ImgeView控件
     * @param pb            加载图片时等待的ProgressBar
     * @param placeholderId 加载图片时的默认图片
     * @param errorId       加载图片出错后的图片
     */
    public static void showBigImag(Context context, String url, final ImageView iv
            , final ProgressBar pb, int placeholderId, int errorId) {
        initPicasso(context, url, placeholderId, errorId)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(iv, callBack(iv, pb));
    }

    /**
     * 在列表页尽量使用裁剪后的图片，在查看大图模式下才加载完整的图片。resize
     * 默认情况下，Android使用ARGB_8888,
     * ARGB_8888:每个像素占用4byte内存
     * RGB_565:每个像素占用2byte内存
     *
     * @param context           Context
     * @param imageNetUrl       图片的网络地址
     * @param iv                需要加载图片的ImgeView控件
     * @param pb                加载图片时等待的ProgressBar
     * @param placeholderId     加载图片时的默认图片
     * @param errorId           加载图片出错后的图片
     * @param roundRectOrCircle true 矩形圆角  false 圆形
     * @param radius            30就有效果了，弧度，roundRectOrCircle为false就给0就可以
     */
    public static void showListImag(Context context, String imageNetUrl, final ImageView iv, final ProgressBar pb
            , int placeholderId, int errorId, boolean roundRectOrCircle, int radius) {
        initPicasso(context, imageNetUrl, placeholderId, errorId)
                .resize(dp2px(context, 250), dp2px(context, 250))
                .config(Bitmap.Config.RGB_565)
//                .config(Bitmap.Config.ARGB_8888)
                .transform(new CircleTransform(roundRectOrCircle, radius))
                .into(iv, callBack(iv, pb));
    }

    public static RequestCreator initPicasso(Context context, String url, int placeholderId, int errorId) {
        return Picasso.with(context).load(url).placeholder(placeholderId).error(errorId);
    }

    public static Callback callBack(final ImageView iv, final ProgressBar pb) {
        return new Callback() {
            @Override
            public void onSuccess() {
                setVisible();
            }

            @Override
            public void onError() {
                setVisible();
            }

            public void setVisible() {
                iv.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
            }
        };
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
