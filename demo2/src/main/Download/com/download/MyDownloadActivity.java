package com.download;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.myplayer.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 创建者：wanglei
 * <p>时间：16/8/26  12:46
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MyDownloadActivity extends Activity {
    Activity activity;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_download_layout);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void main_but(View view) {
        Toast.makeText(MyDownloadActivity.this, "按钮被点击了", Toast.LENGTH_SHORT).show();
        new Thread() {
            @Override
            public void run() {
                try {
                    downloadTo("http://test.new.api.bandu.in/apk/bandu.apk", storagePath());
                } catch (IOException e) {
                    Toast.makeText(MyDownloadActivity.this, "下载异常了", Toast.LENGTH_SHORT).show();
                    Log.e("download", "下载异常了");
                    e.printStackTrace();
                }
            }
        }.start();
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
        return new File(folder + "ban_du_123.apk");
    }

    private void downloadTo(final String netUrl, final File file) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        int downLoadFileSize = 0;
        if (file.exists())
            file.delete();
        if (!file.exists())
            file.createNewFile();
        // 构造URL
        URL url = new URL(netUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        int fileSize = con.getContentLength();//文件的大小
        //设置超时时间
        con.setConnectTimeout(5000);
        is = con.getInputStream();
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        os = new FileOutputStream(file);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
            downLoadFileSize += len;
            update(fileSize, downLoadFileSize);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    public void update(final float fileSize, final float downLoadFileSize) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int i = (int) ((downLoadFileSize / fileSize) * 100);
                tv.setText(String.valueOf(i) + " %");
            }
        });
    }

    /**
     * 应用的替换安装
     * @param file 下载走后的新应用的apk的位置,上面storagePath()方法的那个就行
     */
    public void intallAPP(File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
