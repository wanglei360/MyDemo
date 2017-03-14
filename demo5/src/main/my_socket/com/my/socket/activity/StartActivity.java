/*
 * Copyright (C) 2012, 2013 the diamond:dogs|group
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
package com.my.socket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.demo5.R;

import com.my.socket.dataobject.Example;

/**
 * A simple {@link Activity} that lists all examples included in this app
 * ksoap2
 */
public class StartActivity extends Activity {
	private ListView list;
	private ExampleAdapter adapter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startactivity);
		list = (ListView) findViewById(R.id.startactivity_listview);
		adapter = new ExampleAdapter(this);
		adapter.add(new Example("HttpExampleActivity",
				"Explains how to issue, 处理异步和同步webrequests。", HttpExampleActivity.class));
		adapter.add(new Example("HttpServiceAssisterExampleActivity", "解释如何使用HTTP服务器",
				HttpServiceAssisterExampleActivity.class));
		adapter.add(new Example("HttpOrderedAsyncAssisiterExampleActivity",
				"说明如何发送命令，有条件的，异步Webrequests", HttpOrderedAsyncAssisiterExampleActivity.class));
		adapter.add(new Example("CachingExampleActivity", "显示缓存 Webrequests", CachingExampleActivity.class));
		adapter.add(new Example("NonTimeCriticalTasks", "说明如何使用处理非时间关键任务的API",
				NonTimeCriticalExampleActivity.class));
		adapter.add(new Example("ImageLoadingExampleListActivity", "显示如何使用列表中的图像工作",
				ImageLoadingExampleListActivity.class));
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int position, long id) {
				Intent i = new Intent(StartActivity.this, adapter.getItem(position).getActivityClass());
				startActivity(i);
			}
		});
	}

	private static final class ExampleAdapter extends ArrayAdapter<Example> {

		/**
		 * @param context
		 *            a {@link Context}
		 */
		public ExampleAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_2);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder h;
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
				h = new ViewHolder();
				h.t1 = (TextView) convertView.findViewById(android.R.id.text1);
				h.t2 = (TextView) convertView.findViewById(android.R.id.text2);
				convertView.setTag(h);
			} else {
				h = (ViewHolder) convertView.getTag();
			}
			Example item = getItem(position);
			h.t1.setText(item.getName());
			h.t2.setText(item.getDescription());
			return convertView;
		}

		private static final class ViewHolder {
			private TextView t1;
			private TextView t2;
		}
	}
}
