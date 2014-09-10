//package com.receiver;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.activity.TestAlarmActivity;
//
//public class AlarmTask extends BroadcastReceiver {
//	private static final String TAG = AlarmTask.class.getSimpleName();
//	public static final String ACTION_REPEART = "ACTION_REPEART";
//	
//	private static int COUNT = 0;
//	
//	private Activity mActivity;
//	
//	public AlarmTask(Activity activity){
//		this.mActivity = activity;
//	}
//	
//	public AlarmTask() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		Log.e(TAG, ACTION_REPEART+ (context instanceof TestAlarmActivity));
//		COUNT++;
//		if(ACTION_REPEART.equals(intent.getAction())){
//			Toast.makeText(context, ACTION_REPEART+COUNT, Toast.LENGTH_SHORT).show();
//			
//			if(mActivity != null && mActivity instanceof TestAlarmActivity){
//				TestAlarmActivity activity = (TestAlarmActivity) mActivity;
//				activity.test(COUNT);
//			}
//		}
//	}
//
//}
