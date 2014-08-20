package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Scroller;

import com.dingbaosheng.R;
import com.view.MyView;

/**
 * 测试android触摸分发机制
 * @author dingbaosheng
 *
 */
public class TestTouchEventActivity extends Activity implements OnTouchListener, OnClickListener{

	MyView myView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_touch_event);
		myView = (MyView)findViewById(R.id.myView1);
		myView.setOnTouchListener(this);
		myView.setOnClickListener(this);
		Scroller scroller = new Scroller(this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("Activity", "---dispatchTouchEvent---DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("Activity", "---dispatchTouchEvent---MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e("Activity","---dispatchTouchEvent---UP");
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("Activity", "---onTouchEvent---DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("Activity","---onTouchEvent---MOVE");
			break;
		case MotionEvent.ACTION_UP:
			Log.e("Activity", "---onTouchEvent---UP");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}

	private void onInterceptTouchEven() {

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		/*switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.e("MyView","---onTouch---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				Log.e("MyView","---onTouch---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				Log.e("MyView","---onTouch---UP");
				break;
			default:
				break;
			}*/
		return false;
	}

	@Override
	public void onClick(View v) {
		Log.e("MyView","---onClick---");
	}
}
