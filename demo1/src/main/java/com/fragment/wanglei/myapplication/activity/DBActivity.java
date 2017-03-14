package com.fragment.wanglei.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.fragment.wanglei.myapplication.MyApplication;
import com.fragment.wanglei.myapplication.R;
import com.fragment.wanglei.myapplication.adapter.DBAdapter;
import com.fragment.wanglei.myapplication.base.BaseActivity;
import com.fragment.wanglei.myapplication.db.bean.Biao_1_bean;
import com.fragment.wanglei.myapplication.db.bean.Biao_2_bean;
import com.fragment.wanglei.myapplication.db.dao.Biao_1_Dao;
import com.fragment.wanglei.myapplication.db.dao.Biao_2_Dao;
import com.fragment.wanglei.myapplication.db.util.Util_Biao_1;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * 创建者：wanglei
 * 时间：16/4/19  13:09
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DBActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    private EditText editText;
    private DBAdapter adapter;
    private Biao_1_Dao myDao;
    private Biao_2_Dao myDao_1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editText = (EditText) findViewById(R.id.editTextNote);
        ListView listView = (ListView) findViewById(R.id.list);

        myDao = MyApplication.daoSession.getBiao_1_Dao();
        myDao_1 = MyApplication.daoSession.getBiao_2_dao();

        List<Biao_1_bean> myBeen = myDao.loadAll();


        adapter = new DBAdapter(this, myBeen);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    boolean ee = false;

    public void but_but(View v) {
        Util_Biao_1 biao_1 = new Util_Biao_1();
        biao_1.getListInfo(myDao,Biao_1_Dao.TEXT,new String[]{"批量更改的数据"});
    }

    public void onMyButtonClick(View view) {
        addNote();
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);//获取一个时间
        String comment = "Added on " + df.format(new Date());
        Biao_1_bean note = new Biao_1_bean(null, noteText, comment, new Date());
        myDao.insert(note);
        List<Biao_1_bean> myBeen = myDao.loadAll();
        adapter.setMyBeen(myBeen);
        adapter.notifyDataSetChanged();

        Biao_2_bean biao_2_bean = new Biao_2_bean(null, noteText, comment, new Date(), new Date());
        myDao_1.insert(biao_2_bean);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myDao.deleteByKey(id);
        Log.d("DaoExample", "Deleted note, ID: " + id);
        List<Biao_1_bean> myBeen = myDao.loadAll();
        adapter.setMyBeen(myBeen);
        adapter.notifyDataSetChanged();
    }
}
