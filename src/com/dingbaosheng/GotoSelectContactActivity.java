package com.dingbaosheng;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.entity.UserEntity;

public class GotoSelectContactActivity extends Activity {

	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_go_to_select_contact);
		
		Button button = (Button)findViewById(R.id.btn_go_to_select);
		textView = (TextView)findViewById(R.id.tv_select_result);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(GotoSelectContactActivity.this, SelectContactsActivity.class);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK && requestCode == 1){
			List<UserEntity> list = (List<UserEntity>)data.getSerializableExtra("result");
			if(list != null){
				StringBuffer result = new StringBuffer();
				for (UserEntity userEntity : list) {
					result.append("username:"+userEntity.getUserName()+" phones:"+userEntity.getPhones()+" emails:"+userEntity.getEmails());
				}
				textView.setText(result);
			}
		}
	}
}
