package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.dingbaosheng.R;

public class TestAnimationActiivty extends Activity {

	ImageView button = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		button = (ImageView)findViewById(R.id.button1);
	}
	
	
	public void clickMe(View view){
		button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
		
		button.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				button.clearAnimation();
			}
		},5000);
		
	}
}
