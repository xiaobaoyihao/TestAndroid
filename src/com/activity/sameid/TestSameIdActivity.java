package com.activity.sameid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import com.dingbaosheng.R;

public class TestSameIdActivity extends Activity {

	
	private TextView mTvTitle;
	
	private TextView mTvTitle2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_same_id);
		
		mTvTitle = (TextView)findViewById(R.id.tv_title);
		
		mTvTitle.setText("new title");
		
		mTvTitle2 = (TextView)findViewById(R.id.tv_title3);
		float textSizePx = mTvTitle2.getTextSize();
		float result1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
		float result2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, getResources().getDisplayMetrics());
		float result3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());
		Log.e("", "textSizePx:"+textSizePx);
		
		Log.e("", "10dp =="+result1+"px");
		Log.e("", "10px =="+result2+"px");
		Log.e("", "10sp =="+result3+"px");
	}
}
