package com.dingbaosheng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BActivity extends Activity {

	String TAG = "B";

	VelocityTracker velocityTracker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		Button button = new Button(this);
		button.setText("finish it");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		linearLayout.addView(button);

		setContentView(linearLayout);
		Log.e(TAG, "onCreate");
	}

	float x;
	float xMove;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		createVTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = event.getRawX();

			int distance = (int) (xMove - x);
			

			// 获取滑动速率
			velocityTracker.computeCurrentVelocity(1000);
			int ver = (int) velocityTracker.getXVelocity();
			Log.e("distance:", distance+"");
			Log.e("ver", ver+"");

			if (ver > 120 && distance > 200) {
				finish();
			}
			break;
		case MotionEvent.ACTION_UP:
			if(velocityTracker != null){
				velocityTracker.recycle();
				velocityTracker = null;
			}
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void createVTracker(MotionEvent event) {
		if (velocityTracker == null) {
			velocityTracker = VelocityTracker.obtain();
		}
		velocityTracker.addMovement(event);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.e(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.e(TAG, "onRestart");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.e(TAG, "onRestoreInstanceState");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.e(TAG, "onSaveInstanceState");
	}
}
