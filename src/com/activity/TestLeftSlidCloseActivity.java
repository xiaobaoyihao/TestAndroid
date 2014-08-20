package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dingbaosheng.R;

public class TestLeftSlidCloseActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_left_slide_close);
		
		findViewById(R.id.textView1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TestLeftSlidCloseActivity.this, LeftSlideCloseActivity.class);
				startActivity(intent);
			}
		});;
	}
}
