package com.view;

import android.content.DialogInterface;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;

public interface DateDialogListener extends android.content.DialogInterface.OnClickListener,OnDateChangedListener, OnTimeChangedListener{
	
	void confirm(DialogInterface dialog, int which);
	
	void cancel(DialogInterface dialog, int which);
}
