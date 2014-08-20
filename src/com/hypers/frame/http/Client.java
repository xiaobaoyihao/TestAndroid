package com.hypers.frame.http;

import android.content.Context;

import com.hypers.frame.http.core.AsyncHttpClient;
import com.hypers.frame.http.core.AsyncHttpResponseHandler;
import com.hypers.frame.http.core.RequestParams;

public class Client extends AsyncHttpClient {

	//http://papm.gurutech.com.cn/test/
	//http://papm.hypers.com/
	
//	private static final String BASE_ADDRESS = "http://papm.hypers.com/";
//	private static final String BASE_ADDRESS = "http://papm.gurutech.com.cn/test/";
//	private static final String BASE_ADDRESS = "http://10.2.0.62:8080/DBSServer/";
	private static final String BASE_ADDRESS = "http://www.mamabang.com/";
	//
	
	public void request(Context context, PAPMInterface papmInterface, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		 
		super.post(context, BASE_ADDRESS + papmInterface.getSubUrl(), params,responseHandler);
		 
	}
}
