/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.recyclerview.plugins.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.myplayer.R;
import com.recyclerview.plugins.adapter.MainItemAdapter;
import com.recyclerview.plugins.listener.OnItemClickListener;
import com.recyclerview.plugins.view.ListViewDecoration;

import java.util.Arrays;
import java.util.List;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class RecyclerViewPluginsMainActivity extends Activity implements OnItemClickListener {

    private RecyclerView recyclerView;
    private List<String> titles;
    private List<String> descriptions;
    private MainItemAdapter mMainItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_mainlayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ListViewDecoration(this));

        titles = Arrays.asList(getResources().getStringArray(R.array.main_item));
        descriptions = Arrays.asList(getResources().getStringArray(R.array.main_item_des));
        mMainItemAdapter = new MainItemAdapter(titles, descriptions);
        mMainItemAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mMainItemAdapter);
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, AllMenuActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ViewTypeMenuActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, ListDragMenuActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, GridDragMenuActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, ListSwipeDeleteActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, DragSwipeFlagsActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, DefineActivity.class));
                break;
        }
    }

}
