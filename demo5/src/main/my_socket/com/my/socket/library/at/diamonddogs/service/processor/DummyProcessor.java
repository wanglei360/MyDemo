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
package com.my.socket.library.at.diamonddogs.service.processor;

import android.os.Handler;
import com.my.socket.library.at.diamonddogs.data.dataobjects.Request;
import com.my.socket.library.at.diamonddogs.util.CacheManager.CachedObject;

/**
 * To run a webrequest through the HttpService class it is required to supply a
 * Processor.ID and register the Processor with the Service. If there is no data
 * to process use this class
 */
public class DummyProcessor extends DataProcessor<Void, Void> {


	/**
	 * The processors ID
	 */
	public static final int ID = 91591;

	@Override
	public void processCachedObject(CachedObject cachedObject, Handler handler, Request request) {
	}

	@Override
	public int getProcessorID() {
		return ID;
	}

	@Override
	protected Void createParsedObjectFromByteArray(byte[] data) {
		return null;
	}

	@Override
	protected Void parse(Void inputObject) {
		return null;
	}
}
