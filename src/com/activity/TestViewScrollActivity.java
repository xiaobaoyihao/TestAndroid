package com.activity;

import org.apache.http.impl.client.BasicResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dingbaosheng.R;

public class TestViewScrollActivity extends Activity implements OnClickListener {

	Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_scroll_view);
		mBtn = (Button) findViewById(R.id.btn_click);
		mBtn.setOnClickListener(this);
	}

	public void onClick(View view) {
		view.scrollBy(-10, 0);
	}

	public void onClickImageView(View view) {
		view.scrollTo(-50, 0);
	}
	
	
	public void test(){
		new BasicResponseHandler(){
			
		};
	}
}
