package com.dingbaosheng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AActivity extends Activity {
	String TAG = "A";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "====onCreate");
		
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("to B activity");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AActivity.this, BActivity.class);
				startActivity(intent);;
			}
		});
		linearLayout.addView(button);
		
		setContentView(linearLayout);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.e(TAG, "====onStart");
		SharedPreferences sp = getSharedPreferences("test_name", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("id", "1212312");
		editor.commit();
		
		SharedPreferences sp2 = getPreferences(Context.MODE_PRIVATE);
		Editor editor2 = sp2.edit();
		editor2.putString("id2", "22222");
		editor2.commit();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e(TAG, "====onRestart");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.e(TAG, "====onRestoreInstanceState");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.e(TAG, "====onSaveInstanceState");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(TAG, "====onResume");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e(TAG, "====onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "====onStop");
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "====onDestroy");
	}
}
