package com.activity;

import java.util.Date;

import org.apache.http.impl.cookie.DateUtils;

import android.os.Bundle;

import com.base.BaseActivity;

public class TestDateUtilsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StringBuffer sb = new StringBuffer();
		String result1 = DateUtils.formatDate(new Date());
		String result2 = DateUtils.formatDate(new Date(), "yyyy年MM月dd日 HH:mm:ss:SS");
		
		sb.append(result1)
		.append("\n")
		.append(result2);
		mTvContent.setText(sb.toString());
	}

}
