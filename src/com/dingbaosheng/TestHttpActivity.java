package com.dingbaosheng;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hypers.frame.http.Client;
import com.hypers.frame.http.PAPMInterface;
import com.hypers.frame.http.core.AsyncHttpResponseHandler;
import com.hypers.frame.http.core.RequestParams;

public class TestHttpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new TextView(this));
		
		Client client = new Client();
		client.request(this, PAPMInterface.TEST, new RequestParams(), new AsyncHttpResponseHandler(this,false) {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				JSONObject object = null;
				try {
					object = new JSONObject(new String(responseBody));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.e("onSuccess", object.toString());
			}
			
			@Override
			public void onFailure(int statusCode, String errorMsg) {
				Log.e("onFailure", statusCode+"");
			}
		});
	}
}
