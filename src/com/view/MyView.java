package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
	public static final String TAG = "MyView";
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyView(Context context) {
		super(context);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/*switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			  Log.e(TAG,"---onTouchEvent---DOWN");
			  break;
			case MotionEvent.ACTION_MOVE:
			  Log.e(TAG,"---onTouchEvent---MOVE");
			  break;
			case MotionEvent.ACTION_UP:
			  Log.e(TAG,"---onTouchEvent---UP");
			  break;
			default:
			  break;
		}*/
		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		/*switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			  Log.e(TAG,"---dispatchTouchEvent---DOWN");
			  break; 
			case MotionEvent.ACTION_MOVE:
			  Log.e(TAG,"---dispatchTouchEvent---MOVE");
			  break;
			case MotionEvent.ACTION_UP:
			  Log.e(TAG,"---dispatchTouchEvent---UP");
			  break;
			default:
			  break;
	}*/
		return super.dispatchTouchEvent(event);
	}

}
