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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

import com.demo5.R;

import com.my.socket.dataobject.Tripple;
import com.my.socket.factory.NonTimeCriticalExampleConfigFactory;
import com.my.socket.library.at.diamonddogs.data.dataobjects.NonTimeCriticalTask;
import com.my.socket.library.at.diamonddogs.data.dataobjects.NonTimeCriticalTask.PRIORITY;
import com.my.socket.library.at.diamonddogs.data.dataobjects.NonTimeCriticalWebRequest;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest;
import com.my.socket.library.at.diamonddogs.nontimecritical.NonTimeCriticalTaskQueue.NonTimeCriticalTaskProcessingListener;
import com.my.socket.library.at.diamonddogs.service.net.HttpServiceAssister;
import com.my.socket.library.at.diamonddogs.service.processor.DummyProcessor;
import com.my.socket.library.at.diamonddogs.service.processor.ServiceProcessor;

/**
 * {@link NonTimeCriticalExampleActivity} illustrates the use of the non time
 * critical task API. Will print error logs whenever a task is being processed
 * and whenever a task is done being processed.
 */
public class NonTimeCriticalExampleActivity extends Activity implements OnClickListener {


	private HttpServiceAssister assister;
	private CheckBox cb;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nontimecriticalexampleactivity);
		cb = (CheckBox) findViewById(R.id.nontimecriticalexampleactivity_trigger);
		findViewById(R.id.nontimecriticalexampleactivity_runwebrequests).setOnClickListener(this);

		// we are providing our own global queue configuration for non time
		// critical tasks
		assister = new HttpServiceAssister(this, new NonTimeCriticalExampleConfigFactory());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onResume() {
		super.onResume();
		// make sure to rebind the assisters HttpService
		assister.bindService();
		// make sure to register the task processing listener
		assister.addNonTimeCriticalTaskProcessingListener(new NonTimeCriticalProcessingListenerExample());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause() {
		super.onPause();
		// make sure to unbind the assisters HttpService
		assister.unbindService();
		// THIS PART IS CRUCIAL, DO NOT FORGET TO REMOVE LISTENERS! IF YOU DO,
		// YOU MIGHT BE DEALING WITH MEMORY LEAKS LATER ON!
		assister.removeNonTimeCriticalTaskProcessingListener(new NonTimeCriticalProcessingListenerExample());
	}

	private void addNonTimeCriticalWebRequestsToQueue() {

		// creating some webrequests with different priorities
		Tripple<WebRequest, ServiceProcessor<?>, Handler.Callback> tmp;

		tmp = createNonTimeCriticalWebRequest(PRIORITY.LOWER);
		assister.runNonTimeCriticalWebRequest(tmp.first);

		tmp = createNonTimeCriticalWebRequest(PRIORITY.HIGHEST);
		assister.runNonTimeCriticalWebRequest(tmp.first);

		tmp = createNonTimeCriticalWebRequest(PRIORITY.NORMAL);
		assister.runNonTimeCriticalWebRequest(tmp.first);
	}

	private Tripple<WebRequest, ServiceProcessor<?>, Handler.Callback> createNonTimeCriticalWebRequest(PRIORITY p) {
		Tripple<WebRequest, ServiceProcessor<?>, Handler.Callback> ret = new Tripple<WebRequest, ServiceProcessor<?>, Handler.Callback>();
		ret.second = new DummyProcessor();
		ret.third = new NonTimeCriticalCallBack(p, this);
		ret.first = new NonTimeCriticalWebRequest(p, ret.second, ret.third);
		ret.first.setUrl("http://google.com/");
		ret.first.setProcessorId(DummyProcessor.ID);
		return ret;
	}

	private WebRequest createTimeCriticalWebRequest() {
		WebRequest wr = new WebRequest();
		wr.setUrl("http://google.com/");
		wr.setProcessorId(DummyProcessor.ID);
		return wr;
	}

	@Override
	public void onClick(View v) {
		addNonTimeCriticalWebRequestsToQueue();
		if (cb.isChecked()) {
			assister.runWebRequest(new Handler(), createTimeCriticalWebRequest(), new DummyProcessor());
		} else {
			assister.forceProcessAllNonTimeCriticalWebRequests();
		}
	}

	private static final class NonTimeCriticalProcessingListenerExample implements NonTimeCriticalTaskProcessingListener {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onProcessingStarted(NonTimeCriticalTask task) {
		}

	}

	private static final class NonTimeCriticalCallBack implements Handler.Callback {
		private Context c;
		private PRIORITY p;

		public NonTimeCriticalCallBack(PRIORITY p, Context c) {
			this.c = c.getApplicationContext();
			this.p = p;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean handleMessage(Message msg) {
			return true;
		}

	}

}
