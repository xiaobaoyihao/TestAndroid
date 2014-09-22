package com.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.dingbaosheng.R;
import com.view.MyDialog;

public class TestDialogFragmentActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_dialog_fragment);
	}
	
	
	public void showDialog(View view){
		MyDialog dialog = new MyDialog();
		dialog.show(getSupportFragmentManager(), "this is tag");
	}
	
}
