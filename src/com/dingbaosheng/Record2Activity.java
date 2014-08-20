package com.dingbaosheng;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

public class Record2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);

		Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//		intent.setType("audio/amr");
		startActivityForResult(intent, 2);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(resultCode == RESULT_OK){  
            Uri audioPath = data.getData();  
            Toast.makeText(this, audioPath.toString(), Toast.LENGTH_LONG).show();  
        }  
	}
}
