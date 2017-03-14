package com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * 创建者：wanglei
 * <p>时间：16/7/26  20:07
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class Photograph {

    private Activity context;

    /**
     * todo 这个方法功能还要加入两个权限
     * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * @param context dialog要挂载在哪个activity上，所以对象静态是没有用的
     */
    public Photograph(Activity context) {
        this.context = context;
    }

    /**
     * 弹出提示窗口，选择是用相机还是直接从相册拿图片裁剪
     */
    public void selectCameraOrAlbum() {
        AlertDialog dialog = new AlertDialog.Builder(context).setItems(
                new String[]{"相机", "相册"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            startPhotograph();
                        } else {
                            goOnAlbum();
                        }
                    }
                }).create();
        dialog.show();
    }

    /**
     * 开始拍照的方法
     */
    public void startPhotograph() {
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(storagePath()));
        context.startActivityForResult(cameraintent, 2);
    }

    /**
     * 进入相册
     */
    private void goOnAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, 0);
    }

    /**
     * 剪辑
     * 附加选项	数据类型	描述
     crop	String	发送裁剪信号
     aspectX	int	X方向上的比例
     aspectY	int	Y方向上的比例
     outputX	int	裁剪区的宽
     outputY	int	裁剪区的高
     scale	boolean	是否保留比例
     return-data	boolean	是否将数据保留在Bitmap中返回
     data	Parcelable	相应的Bitmap数据
     circleCrop	String	圆形裁剪区域？
     MediaStore.EXTRA_OUTPUT ("output")	URI	将URI指向相应的file:///...，详见代码示例
     */
    public void startClip(Uri data, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (data == null)
            data = Uri.fromFile(storagePath());
        intent.setDataAndType(data, "image/**");
        intent.putExtra("crop", "true");
        // 下面两行加上注释就是自由裁剪，打开就是长和宽按1:1裁剪
//		intent.putExtra("aspectX", 1);
//		intent.putExtra("aspectY", 1);
        int crop = 100;
        intent.putExtra("outputX", crop);
        intent.putExtra("outputY", crop);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 图片的存储路径
     *
     * @return 返回图片的地址
     */
    private File storagePath() {
        String folder;
        // 如果sd卡挂载了
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            folder = Environment.getExternalStorageDirectory() + File.separator;// 存SD卡上
        else
            folder = Environment.getRootDirectory() + File.separator;// 否则存机器上
        return new File(folder + "1222333" + ".jpg");
    }

    /**
     * @param path 根据图片的地址将图片压缩
     * @return 返回一个bitmap
     */
    public Bitmap getBitmap(String path) {
        Options opts = new Options();
        opts.inJustDecodeBounds = true; // 设置只加载图片大小
        BitmapFactory.decodeFile(path, opts);

        int xScale = opts.outWidth / (500 / 2);// todo 500应该是屏幕的宽
        int yScale = opts.outHeight / (500 / 2);//500应该是屏幕的高
        int scale = xScale > yScale ? xScale : yScale; // 计算缩放比例

        opts.inJustDecodeBounds = false; // 设置不只加载图片大小
        opts.inSampleSize = scale; // 设置缩放比例
        return BitmapFactory.decodeFile(path, opts); // 按照设置加载图片(缩放)
    }

    public Uri getUri() {
        return Uri.fromFile(storagePath());
    }
}
