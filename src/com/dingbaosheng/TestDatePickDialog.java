package com.dingbaosheng;

import java.util.Calendar;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.view.DateText;

public class TestDatePickDialog extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_date_pick_dialog);

		
		DateText textView = (DateText)findViewById(R.id.dateText1);
		Calendar calendar = Calendar.getInstance();
		
		textView.init(calendar, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				Log.e("test", "onDateChanged==-=="+"year:"+view.getYear()+" month:"+view.getMonth()+" day:"+view.getDayOfMonth());
			}
		}, new OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Log.e("test", "onTimeChanged==="+"  hour:"+view.getCurrentHour()+" min:"+view.getCurrentMinute());
			}
		}, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.e("test", "confirm---");
			}
		}, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.e("test", "cancel------");
			}
		});
//		final TextView text = (TextView) findViewById(R.id.text);
//		LayoutInflater l = LayoutInflater.from(this);
//		View v = l.inflate(R.layout.date_picker, null);
//		final DatePicker datePicker = (DatePicker) v.findViewById(R.id.date_picker);
//
//		datePicker.init(2014, 5, 3, new OnDateChangedListener() {
//
//			@Override
//			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//				Log.e("fuck", "onDateChanged");
//				if (isDateAfter(view)) {
//					view.init(2014, 1, 1, this);
//				}
//				if (isDateBefore(view)) {
//					view.init(2014, 1, 1, this);
//				}
//			}
//
//			private boolean isDateAfter(DatePicker tempView) {
//				if (tempView.getYear() > 2015) {
//					return true;
//				} else
//					return false;
//			}
//
//			private boolean isDateBefore(DatePicker tempView) {
//				if (tempView.getYear() < 2010) {
//					return true;
//				} else
//					return false;
//			}
//		});
//		Dialog dialog = new AlertDialog.Builder(this)
//				.setTitle(
//						datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月" + datePicker.getDayOfMonth()
//								+ "日").setView(v).setIcon(R.drawable.ic_launcher)
//				.setNeutralButton("设置", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//
//						text.setText(datePicker.getYear() + "年" + (datePicker.getMonth() + 1) + "月"
//								+ datePicker.getDayOfMonth() + "日");
//					}
//				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.cancel();
//
//					}
//				}).create();
//
//		dialog.show();

	}
}
