package com.MediaPlayer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.myplayer.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 创建者：wanglei
 * <p>时间：16/6/16  17:08
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：
 */
public class MediaPlayerActivity  extends Activity {


    private SeekBar seekBar;
    private boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_layout);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new MySeekbar());
    }

    public void but1(View view) {
//        String path = newFileName("pppp");///storage/emulated/0/pppp.mp3
//        String path = "http://win.web.ra01.sycdn.kuwo.cn/resource/n1/192/13/35/3640411453.mp3";
//        String path = "http://test.static.bandu.in/android.flv";
        String path = "http://test.static.bandu.in/android.mp3";

        playMyVoice(path);
//        san();
//        String[] str = {newFileName("xx"),newFileName("zz")};
//        uniteAMRFile(str,newFileName("wwppqq"));
    }

    public void but2(View view) {
        stopPlayVoice();
    }

    /**
     * 播放我的声音，对外提供的方法,返回真就是现在播放，返回假就是停止播放
     *
     * @param path 声音的地址，可以使本地也可以使网络的
     */
    public boolean playMyVoice(String path) {
        try {
            if (mPlayer == null) {// 没播放内容
                playVoice(path);
            } else {
                stopPlayVoice();
                playMyVoice(path);
            }
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "声音播放错误", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 播放声音文件的，一定要记住加上连网的权限
     *
     * @param voicePath 声音的地址,可以是本地的，也可以是网络的
     */
    private void playVoice(final String voicePath) {
        new Thread() {
            public void run() {
                try {
//                    setProgress(10);
                    mPlayer = new MediaPlayer();
//					播放本地音频
//					File file=new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/Recondsound.amr");
//					FileInputStream fis=new FileInputStream(file);
//					mPlayer.setDataSource(fis.getFD());

                    //播放网路音频
                    mPlayer.setDataSource(MediaPlayerActivity.this, Uri.parse(voicePath));
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    mPlayer.prepare();
                    int duration = mPlayer.getDuration();//233291
                    System.out.println("mPlayer.getDuration() = "+duration);
                    seekBar.setMax(duration);//设置进度条 256026
////----------定时器记录播放进度---------//
                    mTimer = new Timer();
                    mTimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            if (isChanging == true) {
                                return;
                            }
                            seekBar.setProgress(mPlayer.getCurrentPosition());
                        }
                    };
                    mTimer.schedule(mTimerTask, 0, 10);
                    mPlayer.start();
                    //网络声音才会生效，缓冲了多少的回调，美妙一次
//                    mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//                        @Override
//                        public void onBufferingUpdate(MediaPlayer mp, int percent) {// 开始播放，每秒发送信息
//                            seekBar.setProgress(mPlayer.getCurrentPosition());
//                            System.out.println("mPlayer.getCurrentPosition() = " + mPlayer.getCurrentPosition() + "     percent = " + percent);
//                        }
//                    });
//                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                        @Override
//                        public void onCompletion(MediaPlayer mp) {// 停止播放发送信息
//                            stopPlayVoice();
//                        }
//                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }

    Timer mTimer;
    TimerTask mTimerTask;

    /**
     * 停止播放声音
     */
    public void stopPlayVoice() {
//        setProgress(0);
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        stopPlayVoice();
    }

    // 声音录制在本地的地址
    private String newFileName(String str) {
        String folder;
        if (Environment.getExternalStorageState().equals(// 如果sd卡挂载了
                android.os.Environment.MEDIA_MOUNTED)) {
            folder = Environment.getExternalStorageDirectory()// 存SD卡上
                    + File.separator;
        } else {
            folder = Environment.getRootDirectory() + File.separator;// 否则存机器上
        }
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }
//		return folder + "gequwww" + ".mp3";
//		return folder + "gequqq" + ".mp3";
//		return folder + "pppp" + ".mp3";
//		return folder + "zz" + ".wav";//456
//		return folder + "xx" + ".wav";//123
        return folder + str + ".MP3";//123
    }

    private class MySeekbar implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            System.out.println("11111111"+"             progress = "+progress);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
//            isChanging = true;
            System.out.println("222222222");

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            System.out.println("33333333");
            if (mPlayer != null)
                mPlayer.seekTo(seekBar.getProgress());
//            isChanging = false;
        }
    }

    /**
     * 需求:将两个mp3格式音频文件合并为1个
     * @param path  需要合并的本地的MP3地址   newFileName("pppp")
     * @param path2 需要合并的本地的MP3地址
     * @param path3 合并后的本地的MP3地址
     */
    public void san(String path,String path2,String path3){
        try {
            File file1 = new File(path);
            File file2 = new File(path2);
            InputStream is1 = new FileInputStream(file1) ;
            InputStream is2 = new FileInputStream(file2) ;

            OutputStream os = new FileOutputStream(path3) ;

            byte[]b1=new byte[1024 * 2];
            byte[]b2=new byte[1024 * 2];
            int len1 =0;
            int len2 =0;
            int index = 0;

            while((len1 = is1.read(b1))!=-1){
                index++;
                if(index==1){
                    continue ;
                }
                os.write(b1, 0, len1 ) ;
            }
            index=0;
            while((len2 = is2.read(b2))!=-1){
                index++ ;
                os.write(b2, 0, len2 ) ;
            }

            is1.close() ;
            is2.close() ;
            os.flush();
            os.close() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 需求:将两个amr格式音频文件合并为1个
     * 注意:amr格式的头文件为6个字节的长度
     * @param partsPaths 各部分路径
     * @param unitedFilePath 合并后路径
     */
    public void uniteAMRFile(String[] partsPaths, String unitedFilePath) {
        try {
            File unitedFile = new File(unitedFilePath);
            FileOutputStream fos = new FileOutputStream(unitedFile);
            RandomAccessFile ra = null;
            for (int i = 0; i < partsPaths.length; i++) {
                ra = new RandomAccessFile(partsPaths[i], "r");
                if (i != 0) {
                    ra.seek(6);
                }
                byte[] buffer = new byte[1024 * 8];
                int len = 0;
                while ((len = ra.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
            ra.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
