/*
 * Copyright (C) 2013 the diamond:dogs|group
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

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.demo5.R;

import com.my.socket.dataobject.WebComic;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest;
import com.my.socket.library.at.diamonddogs.service.net.HttpServiceAssister;
import com.my.socket.library.at.diamonddogs.service.processor.ServiceProcessorMessageUtil;
import com.my.socket.processor.WebComicProcessor;
import com.my.socket.adapter.ImageLoadingExampleAdapter;

/**
 * Demonstrates how to populate a {@link ListView} with images
 */
public class ImageLoadingExampleListActivity extends ListActivity {
	/**
	 * Base url for the RSS feed
	 */
	private static final String WEBCOMIC_RSS_URL = "http://www.commitstrip.com/en/feed/";

	/**
	 * The {@link HttpServiceAssister} to run all {@link WebRequest}s
	 */
	private HttpServiceAssister assister;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		assister = new HttpServiceAssister(this);
		setContentView(R.layout.imageloadingexamplelistactivity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onResume() {
		super.onResume();
		runGetRssWebRequest();
		assister.bindService();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause() {
		super.onPause();
		assister.unbindService();
	}

	private void runGetRssWebRequest() {
		WebRequest wr = new WebRequest();
		wr.setProcessorId(WebComicProcessor.ID);
		wr.setUrl("http://www.commitstrip.com/en/feed/");
		assister.runWebRequest(new Handler() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (ServiceProcessorMessageUtil.isSuccessful(msg) && ServiceProcessorMessageUtil.isFromProcessor(msg, WebComicProcessor.ID)) {
					WebComic wc = (WebComic) ServiceProcessorMessageUtil.getPayLoad(msg);
					setListAdapter(new ImageLoadingExampleAdapter(ImageLoadingExampleListActivity.this, assister, wc.getImagePaths()));
				}
			}
		}, wr, new WebComicProcessor());
	}

}
