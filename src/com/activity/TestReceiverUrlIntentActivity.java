package com.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.dingbaosheng.R;

public class TestReceiverUrlIntentActivity extends Activity {

	TextView tvReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_receiver_url_intent);

		tvReceiver = (TextView) findViewById(R.id.textView1);
		Uri uri = getIntent().getData();
		tvReceiver.setText(getIntent().getData().toString() + "\n host:" + uri.getHost()
				+ "\n path:" + uri.getPath() + "\n port:" + uri.getPort() + "\n getQuery:"
				+ uri.getQuery() + "\n paramName:" + uri.getQueryParameter("name")
				+ "\n paramPassword:" + uri.getQueryParameter("password"));

	}
}
