package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.Toast;

import com.dingbaosheng.R;

/**
 * 左划关闭页面
 * @author dingbaosheng
 *
 */
public class LeftSlideCloseActivity extends Activity {
	private static final String TAG = LeftSlideCloseActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_left_slide_close);
	}
	
	float startX;
	float startY;
	
	float endX;
	float endY;
	
	VelocityTracker vtTracker;
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				
				startX = ev.getRawX();
				startY = ev.getRawY();
				Log.e(TAG, "====================================");
				Log.e(TAG, "ACTION_DOWN"+ "getRawX:"+startX+" getX:"+ev.getX());
				Log.e(TAG, "ACTION_DOWN"+ "getRawY:"+startY+" getY:"+ev.getY());
				if(vtTracker == null){
					vtTracker = VelocityTracker.obtain();
					vtTracker.addMovement(ev);
				}else{
					vtTracker.clear();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				vtTracker.addMovement(ev);
				vtTracker.computeCurrentVelocity(1000);
				Log.e(TAG, "====================================");
				Log.e(TAG, "ACTION_MOVE"+ "  x速度:"+vtTracker.getXVelocity());
				Log.e(TAG, "ACTION_MOVE"+ "  y速度:"+vtTracker.getYVelocity());
				break;
			case MotionEvent.ACTION_UP:
				endX = ev.getRawX();
				endY = ev.getRawY();
				
				if(startX-endX > 200){
					Toast.makeText(this, "close activity", Toast.LENGTH_SHORT).show();
				}
				Log.e(TAG, "====================================");
				Log.e(TAG, "ACTION_UP"+ "getRawX:"+endX+ " getX:"+ev.getX());
				Log.e(TAG, "ACTION_UP"+ "getRawY:"+endY+" getY:"+ev.getY());
				
				if(vtTracker != null){
					vtTracker.clear();
					vtTracker.recycle();
					vtTracker = null;
				}
				break;
			default:
				break;
		}
		
		
		return super.dispatchTouchEvent(ev);
	}
}
