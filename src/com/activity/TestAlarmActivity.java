package com.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.IndexActivity;
import com.dingbaosheng.R;
import com.receiver.AlarmTask;

/**
 * 轮询实现
 * 
 * @author dingbaosheng
 * @date 2014-9-10
 */
public class TestAlarmActivity extends Activity {
	String TAG = "TestAlarmActivity";
	Button mBtnStart;
	Button mBtnCancel;

	
	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			test();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_alarm_activity);

		mBtnStart = (Button) findViewById(R.id.btn_start);
		mBtnCancel = (Button) findViewById(R.id.btn_cancel);
		
		IntentFilter intentFilter = new IntentFilter(AlarmTask.ACTION_REPEART2);
		registerReceiver(mReceiver, intentFilter);
		
		
		Log.e("testAlarmActivity", "topactivity:"+IndexActivity.getTopActivity(this));
	}
	
	
	

	public void onStart(View view) {
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		Intent intent = new Intent(this, AlarmTask.class);
		intent.setAction(AlarmTask.ACTION_REPEART);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		long firstTime = SystemClock.elapsedRealtime();
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 5000, pendingIntent);
		
		/*long firstTime = System.currentTimeMillis();
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, 5000, pendingIntent);*/
	}

	public void onCancel(View view) {
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		
		Intent intent = new Intent(this, AlarmTask.class);
		intent.setAction(AlarmTask.ACTION_REPEART);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		
		alarmManager.cancel(pendingIntent);
		Log.e(TAG, "pendintent:"+pendingIntent);
		Log.e(TAG, "pendintent:"+pendingIntent);
	}
	
	public void onFinish(View view){
		finish();
	}
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	public void test(){
		Toast.makeText(this, "test function", Toast.LENGTH_SHORT).show();
	}
}
