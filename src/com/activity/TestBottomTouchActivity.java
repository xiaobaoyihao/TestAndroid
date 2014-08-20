package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.dingbaosheng.R;

/**
 * 手势从底部滑动上门
 * 
 * @author dingbaosheng
 * 
 */
public class TestBottomTouchActivity extends Activity {

	RelativeLayout rlRootView;
	LinearLayout llBottom;
	int bottomHeight;
	int screenWidth;
	int screenHeigth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_bottom_touch);
		rlRootView = (RelativeLayout)findViewById(R.id.rl_root_view);
		
		llBottom = (LinearLayout)getLayoutInflater().inflate(R.layout.item_bottom, null);
		llBottom.postDelayed(new Runnable() {

			@Override
			public void run() {
				bottomHeight = 100;
				Log.e("post", "bottomHeight:" + bottomHeight);
			}
		}, 500);

		screenWidth = getResources().getDisplayMetrics().widthPixels;
		screenHeigth = getResources().getDisplayMetrics().heightPixels;

		Log.e("TestBottomTouchActivity", "screenWidth:" + screenWidth + "  screenHeigth:"
				+ screenHeigth);
		
		
	}

	float startX;
	float endX;

	float startY;
	float endY;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {

		Log.e("TestBottomTouchActivity", "onTouchEvent");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getRawX();
			startY = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:

			break;

		case MotionEvent.ACTION_UP:
			endX = event.getRawX();
			endY = event.getRawY();

			Log.e("TestBottomTouchActivity", "ACTION_UP==========================");
			Log.e("TestBottomTouchActivity", "startX:" + startX + "  endX:" + endX);
			Log.e("TestBottomTouchActivity", "startY:" + startY + "  endY:" + endY);
			Log.e("TestBottomTouchActivity", "endY-startY:" + (endY - startY));
			Log.e("TestBottomTouchActivity", "startY-screenHeigth:" + (startY - screenHeigth));
			Log.e("TestBottomTouchActivity",
					"bellll:" + Math.abs(Math.abs(screenHeigth - startY) - bottomHeight));

			if (startY - endY >= bottomHeight && Math.abs(startY - screenHeigth) < 30) {
				popBottomLayout();
			} else if ((endY - startY) >= bottomHeight
					&& Math.abs(Math.abs(screenHeigth - startY) - bottomHeight) < 200) {
				hideBottomLayout();
			}

			break;
		default:
			break;
		}

		return super.dispatchTouchEvent(event);
	}

	private void popBottomLayout() {
		//将llBottom设置为容器底部对其
		if(rlRootView.getChildCount() > 0) return;
		Toast.makeText(this, "弹出视图", Toast.LENGTH_SHORT).show();
		rlRootView.removeAllViews();
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, -1);
		rlRootView.addView(llBottom, params);
	}
	
	private void hideBottomLayout() {
		if(rlRootView.getChildCount() == 0) return;
		Toast.makeText(this, "隐藏视图", Toast.LENGTH_SHORT).show();
		rlRootView.removeAllViews();
	}

	

}
