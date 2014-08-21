package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * the customview just for test onMesaure how to work;
 * @author dingbaosheng
 * @date 2014-8-21
 */
public class CustomView extends View {
	private static final String TAG = CustomView.class.getSimpleName();
	
	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		
		if(heightMode == MeasureSpec.AT_MOST){
			Log.e(TAG, "heightMode == MeasureSpec.AT_MOST");
		}
		if(heightMode == MeasureSpec.EXACTLY){
			Log.e(TAG, "heightMode == MeasureSpec.EXACTLY");
		}
		if(heightMode == MeasureSpec.UNSPECIFIED){
			Log.e(TAG, "heightMode == MeasureSpec.UNSPECIFIED");
		}
		
		Log.e(TAG, "width:"+width+" height:"+height);
		
		setMeasuredDimension(width, height);
	}
}
