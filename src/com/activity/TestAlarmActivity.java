package com.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dingbaosheng.R;

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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_alarm_activity);

		mBtnStart = (Button) findViewById(R.id.btn_start);
		mBtnCancel = (Button) findViewById(R.id.btn_cancel);
		
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
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public void test(int count){
		Toast.makeText(this, "test function", Toast.LENGTH_SHORT).show();
	}
	
	
	private static int COUNT = 0;
	
	private class AlarmTask extends BroadcastReceiver {
		public static final String ACTION_REPEART = "ACTION_REPEART";
		
		private Activity mActivity;
		
		public AlarmTask(Activity activity){
			this.mActivity = activity;
		}
		
		public AlarmTask() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e(TAG, ACTION_REPEART+ (context instanceof TestAlarmActivity));
			COUNT++;
			if(ACTION_REPEART.equals(intent.getAction())){
				Toast.makeText(context, ACTION_REPEART+COUNT, Toast.LENGTH_SHORT).show();
				
				test(COUNT);
			}
		}

	}
}
