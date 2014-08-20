package com.dingbaosheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestViewPostMethodActivity extends Activity {

	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_view_post_method);
//		textView = (TextView) findViewById(R.id.textview);

	}

	public void click(View view) {

		// textView.postDelayed(new Runnable() {
		// @Override
		// public void run() {
		// textView.setText("new data1" + "currentTHread:" +
		// Thread.currentThread().getName());
		// }
		// }, 2000);

		new Thread(new Runnable() {
			@Override
			public void run() {

				textView.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						textView.setText("new data in threadName:"
								+ Thread.currentThread().getName());
					}
				}, 2000);
			}
		}).start();
	}
}
