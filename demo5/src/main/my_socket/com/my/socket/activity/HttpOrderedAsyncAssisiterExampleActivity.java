/**
 * Copyright 2013, the diamond:dogs|group
 */
package com.my.socket.activity;

import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.demo5.R;

import com.my.socket.dataobject.Weather;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest;
import com.my.socket.library.at.diamonddogs.data.dataobjects.WebRequest.Type;
import com.my.socket.library.at.diamonddogs.service.net.HttpOrderedAsyncAssister;
import com.my.socket.library.at.diamonddogs.service.net.HttpOrderedAsyncAssister.HttpOrderedAsyncHandler2;
import com.my.socket.library.at.diamonddogs.service.net.HttpOrderedAsyncAssister.HttpOrderedAsyncRequest;
import com.my.socket.library.at.diamonddogs.service.net.HttpOrderedAsyncAssister.NextWebRequestDelegate;
import com.my.socket.library.at.diamonddogs.service.net.HttpOrderedAsyncAssister.NoNextWebRequestDelegate;
import com.my.socket.library.at.diamonddogs.service.processor.HeadRequestProcessor;
import com.my.socket.library.at.diamonddogs.service.processor.ServiceProcessorMessageUtil;
import com.my.socket.processor.WeatherProcessor;

/**
 * A simple example that illustrates how asynchronous {@link WebRequest}s can be
 * executed in order, according to certain conditions.
 */
public class HttpOrderedAsyncAssisiterExampleActivity extends Activity {


	private HttpOrderedAsyncAssister assister;

	/**
	 * Text view to display a weather string
	 */
	private TextView text;

	/**
	 * Text view to display the temperature
	 */
	private TextView temperature;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httporderedasyncassisterexampleactivity);
		assister = new HttpOrderedAsyncAssister(this);
		text = (TextView) findViewById(R.id.httporderedasyncassisiterexampleactivity_text);
		temperature = (TextView) findViewById(R.id.httporderedasyncassisiterexampleactivity_temperature);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	protected void onResume() {
		super.onResume();
		assister.bindService();
		String weatherUrl = getWeatherUrl("Austria", "Vienna");
		WebRequest headRequest = getHeadRequest();
		// @formatter:off
		HttpOrderedAsyncRequest initialRequest = new HttpOrderedAsyncRequest(
				headRequest,
				new WeatherHandler(assister),
				new NextWebRequestDelegate() {
					@Override
					public HttpOrderedAsyncRequest getNextWebRequest(Message message) {
						Map<String, List<String>> header = (Map<String, List<String>>) message.obj;
						if (header != null) {
							if (header.containsKey("Content-Type") && header.get("Content-Type").get(0).equals("application/json; charset=utf-8")) {
								return new HttpOrderedAsyncRequest(
										getWeatherRequest(),
										new WeatherHandler(assister),
										new NoNextWebRequestDelegate(),
										new WeatherProcessor()
								);
							} else {
								return null;
							}
						} else {
							return null;
						}
					}
				},
				new HeadRequestProcessor()
		);
		// @formatter:on
		assister.runRequests(initialRequest);
	}

	private WebRequest getHeadRequest() {
		WebRequest webRequest = new WebRequest();
		webRequest.setUrl(getWeatherUrl("Austria", "Vienna"));
		webRequest.setRequestType(Type.HEAD);

		// default header request processor
		webRequest.setProcessorId(HeadRequestProcessor.ID);

		// required for HEAD request
		webRequest.addHeaderField("Accept-Encoding", "gzip, deflate");
		return webRequest;
	}

	private WebRequest getWeatherRequest() {
		WebRequest webRequest = new WebRequest();
		webRequest.setUrl(getWeatherUrl("Austria", "Vienna"));
		webRequest.setRequestType(Type.GET);

		// default header request processor
		webRequest.setProcessorId(WeatherProcessor.ID);

		return webRequest;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onPause() {
		super.onPause();
		assister.unbindService();
	}

	/**
	 * Formats the openweathermap.org weather URL
	 * 
	 * @param country
	 *            the country
	 * @param city
	 *            the city
	 * @return the weather url for country & city
	 */
	private String getWeatherUrl(String country, String city) {
		Uri u = Uri.parse("http://api.openweathermap.org/data/2.5/weather/");
		// @formatter:off
		u = u.buildUpon()
			.appendQueryParameter("q", city + "," + country)
			.appendQueryParameter("units", "metric")
		.build();
		// @formatter:on
		return u.toString();
	}

	/**
	 * This handler receives a callback once the web request has been processed.
	 */
	private class WeatherHandler extends HttpOrderedAsyncHandler2 {
		/**
		 * @param arg0
		 */
		public WeatherHandler(HttpOrderedAsyncAssister arg0) {
			super(arg0);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean onNextWebRequestComplete(Message msg) {
			if (ServiceProcessorMessageUtil.isFromProcessor(msg, HeadRequestProcessor.ID)) {
				return ServiceProcessorMessageUtil.isSuccessful(msg);
			} else {
				return false;
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onWebRequestChainCompleted(Message msg) {
			if (ServiceProcessorMessageUtil.isFromProcessor(msg, WeatherProcessor.ID)) {
				if (ServiceProcessorMessageUtil.isSuccessful(msg)) {
					Weather w = (Weather) msg.obj;
					text.setText(w.getText());
					temperature.setText(String.valueOf(w.getTemperature()));
				} else {
					Toast.makeText(HttpOrderedAsyncAssisiterExampleActivity.this, "Error fetching weather", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
