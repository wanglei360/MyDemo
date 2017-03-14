package com.myFile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.myplayer.R;

/**
 * 创建者：wanglei
 * <p>时间：16/7/1  17:24
 * <p>类描述：
 * <p>修改人：
 * <p>修改时间：
 * <p>修改备注：todo java同级别的utils全都是工具
 */
public class FileActivity extends Activity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_layout);
        context = this.getApplicationContext();
    }

    public void file_but(View view) {
        DemoBean bean = new DemoBean();
        bean.setBb(true);
        bean.setIi(122223333);
        bean.setStr("we文件了");

        boolean bu_zhi_dao = FileUtils.beanToFile(context, "bu_zhi_dao", bean);
        DemoBean bean1 = FileUtils.fileToBean(context, DemoBean.class, "bu_zhi_dao");
        Toast.makeText(context, bean1.getStr(), Toast.LENGTH_LONG).show();
        Toast.makeText(context, bean1.getIi() + "", Toast.LENGTH_LONG).show();
        Toast.makeText(context, bean1.isBb() + "", Toast.LENGTH_LONG).show();
    }
}
