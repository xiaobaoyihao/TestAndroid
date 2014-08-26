package com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingbaosheng.R;

public class TabView extends LinearLayout {

	

	public TabView(Context context) {
		super(context);
	}
	
	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
		
		addView("历史");
		addView("联系人");
		addView("设置");
	}

	@SuppressLint("NewApi")
	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	private void addView(String label){
		TextView labelView = new TextView(getContext());
		
		LayoutParams param  = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
		param.gravity = Gravity.CENTER;
		labelView.setLayoutParams(param);
		labelView.setText(label);
		labelView.setTextColor(getContext().getResources().getColorStateList(R.color.tabview_text_color));
		labelView.setBackgroundResource(R.drawable.bg_tabview);
		labelView.setClickable(true);
		labelView.setGravity(Gravity.CENTER);
		
		
		addView(labelView);
	}
}
