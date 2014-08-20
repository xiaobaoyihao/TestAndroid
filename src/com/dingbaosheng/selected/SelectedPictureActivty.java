package com.dingbaosheng.selected;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dingbaosheng.R;

public class SelectedPictureActivty extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selected_image);
	}
	
	public void addImage(View view){
		Intent intent = new Intent(this, ImageDirListActivity.class);
		startActivityForResult(intent, 12);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	}
}
