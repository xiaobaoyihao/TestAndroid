package com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmTask extends BroadcastReceiver {
	private static final String TAG = AlarmTask.class.getSimpleName();
	public static final String ACTION_REPEART = "ACTION_REPEART";
	public static final String ACTION_REPEART2 = "ACTION_REPEART2";

	public static String FLAG_COUNT = "FLAG_COUNT";
	private static int COUNT = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(TAG, ACTION_REPEART);
		COUNT++;
		if (ACTION_REPEART.equals(intent.getAction())) {
			Toast.makeText(context, ACTION_REPEART + COUNT, Toast.LENGTH_SHORT).show();
			intent.putExtra(FLAG_COUNT, COUNT);
			
			Intent intent2 = new Intent(ACTION_REPEART2);
			context.sendBroadcast(intent2);
		}
	}

}
