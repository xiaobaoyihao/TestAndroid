package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dingbaosheng.R;

/**
 * 
 * @author dingbaosheng
 * @date 2014-9-12
 */
public class TestLoadModeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_load_mode);
	}

	public void onClickSingleTop(View view) {

	}

	public void onClickSingleTask(View view) {

	}
	
	public void onClickSingleInstance(View view) {

	}

	

}
