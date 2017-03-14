package com.recyclerview.android_native;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myplayer.R;

public class RecyclerViewNativeMainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_native_main_layout);
        ((Button) findViewById(R.id.list_recycler_view_native_but_1)).setOnClickListener(this);
        ((Button) findViewById(R.id.list_recycler_view_native_but_2)).setOnClickListener(this);
        ((Button) findViewById(R.id.list_recycler_view_native_but_3)).setOnClickListener(this);
        ((Button) findViewById(R.id.list_recycler_view_native_but_4)).setOnClickListener(this);
        ((Button) findViewById(R.id.grid_recycler_view_native_but_1)).setOnClickListener(this);
        ((Button) findViewById(R.id.grid_recycler_view_native_but_2)).setOnClickListener(this);
        ((Button) findViewById(R.id.grid_recycler_view_native_but_3)).setOnClickListener(this);
        ((Button) findViewById(R.id.grid_recycler_view_native_but_4)).setOnClickListener(this);
        ((Button) findViewById(R.id.staggered_recycler_view_native_but_1)).setOnClickListener(this);
        ((Button) findViewById(R.id.staggered_recycler_view_native_but_2)).setOnClickListener(this);
        ((Button) findViewById(R.id.staggered_recycler_view_native_but_3)).setOnClickListener(this);
        ((Button) findViewById(R.id.staggered_recycler_view_native_but_4)).setOnClickListener(this);
    }

    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_recycler_view_native_but_1://list标准
                goActivity(0, ListRecyclerViewActivity.class);
                break;
            case R.id.list_recycler_view_native_but_2://list垂直反向
                goActivity(1, ListRecyclerViewActivity.class);
                break;
            case R.id.list_recycler_view_native_but_3://list水平
                goActivity(2, ListRecyclerViewActivity.class);
                break;
            case R.id.list_recycler_view_native_but_4://list水平反向
                goActivity(3, ListRecyclerViewActivity.class);
                break;
            case R.id.grid_recycler_view_native_but_1://grid标准
                goActivity(4, GridRecyclerViewActivity.class);
                break;
            case R.id.grid_recycler_view_native_but_2://grid垂直反向
                goActivity(5, GridRecyclerViewActivity.class);
                break;
            case R.id.grid_recycler_view_native_but_3://grid水平
                goActivity(6, GridRecyclerViewActivity.class);
                break;
            case R.id.grid_recycler_view_native_but_4://grid水平反向
                goActivity(7, GridRecyclerViewActivity.class);
                break;
            case R.id.staggered_recycler_view_native_but_1://staggered水平反向
                goActivity(8, StaggeredRecyclerViewActivity.class);
                break;
            case R.id.staggered_recycler_view_native_but_2://staggered水平反向
                goActivity(9, StaggeredRecyclerViewActivity.class);
                break;
            case R.id.staggered_recycler_view_native_but_3://staggered水平反向
                goActivity(10, StaggeredRecyclerViewActivity.class);
                break;
            case R.id.staggered_recycler_view_native_but_4://staggered水平反向
                goActivity(11, StaggeredRecyclerViewActivity.class);
                break;
        }

    }

    private void goActivity(int state, Class<?> cls) {
        startActivity(new Intent(this, cls).putExtra("state", state));
    }

}
