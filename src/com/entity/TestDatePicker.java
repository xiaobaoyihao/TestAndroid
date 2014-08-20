package com.entity;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.DatePicker;

public class TestDatePicker extends DatePicker {

	public TestDatePicker(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public TestDatePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TestDatePicker(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(int year, int monthOfYear, int dayOfMonth, OnDateChangedListener onDateChangedListener) {
		super.init(year, monthOfYear, dayOfMonth, onDateChangedListener);
	}
}
