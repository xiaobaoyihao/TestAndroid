package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingbaosheng.R;

public class TestLayoutAnimationActivity extends Activity {

	LinearLayout layout ;
	Button mBtnAdd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout_animation);
		layout = (LinearLayout)findViewById(R.id.ll_contain);
		mBtnAdd = (Button)findViewById(R.id.btn_add);
	}
	
	public void add(View view){
		View newView = getLayoutInflater().inflate(R.layout.item_layout_animation, null);
		TextView tvLabel = (TextView) newView.findViewById(R.id.tv_label);
		tvLabel.setText(TITLE[(int) (Math.random()*TITLE.length)]);
		
		Button btnAddDel = (Button) newView.findViewById(R.id.btn_del);
		btnAddDel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				layout.removeView((View)v.getParent());
			}
		});
		
		layout.addView(newView);
	}
	
	private String[] TITLE = {"丁保胜","张淑琴","丁淑霞","丁小姐","丁代娣"};
}
