package com.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.dingbaosheng.R;

public class DateText extends TextView implements View.OnClickListener, DateDialogListener {

	private View rootView;
	
	private Calendar calendar;// 显示日期

	private DatePicker datePicker;
	private TimePicker timePicker;

	private AlertDialog dialog;

	private OnDateChangedListener dateChangedListener;
	private OnTimeChangedListener timeChangedListener;

	private DialogInterface.OnClickListener confirmListener;
	private DialogInterface.OnClickListener cancelListener;

	private SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm");
	
	
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int hour = 0;
	private int min = 0;
	
	public DateText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWidget();
	}

	public DateText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWidget();
	}

	public DateText(Context context) {
		super(context);
		initWidget();
	}

	private void initWidget() {
		rootView = LayoutInflater.from(getContext()).inflate(R.layout.date_picker, null);
		datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
		timePicker = (TimePicker)rootView.findViewById(R.id.time_picker);
		dialog = new AlertDialog.Builder(getContext()).create();
		
	}

	public void init(Calendar calendar,
						OnDateChangedListener dateChangeListener,
						OnTimeChangedListener timeChangedListener,
						DialogInterface.OnClickListener confirmListener,
						DialogInterface.OnClickListener cancelListener) {
		
		this.calendar = calendar;
		
		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
		timePicker.setOnTimeChangedListener(this);
		
		
		this.dateChangedListener = dateChangeListener;
		this.timeChangedListener = timeChangedListener;
		this.confirmListener = confirmListener;
		this.cancelListener = cancelListener;
		
		dialog.setTitle(format.format(calendar.getTime()));
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", this);
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", this);
		dialog.setView(rootView);
		setText(format.format(calendar.getTime()));
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// 显示时间对话框
		dialog.show();
	}

	
	@Override
	public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
		this.year = year;
		this.month = monthOfYear;
		this.day = dayOfMonth;
		
		if (dateChangedListener != null) {
			dateChangedListener.onDateChanged(datePicker, year, monthOfYear, dayOfMonth);
		}
		// 更新对话框标题
		updateTitle(datePicker, getTimePicker());
	}

	@Override
	public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
		
		this.hour = hourOfDay;
		this.min = minute;
		
		if (timeChangedListener != null) {
			timeChangedListener.onTimeChanged(timePicker, hourOfDay, minute);
		}
		updateTitle(getDatePicker(), timePicker);
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			confirm(dialog, which);
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			cancel(dialog, which);
			break;
		default:
			break;
		}
	}

	@Override
	public void confirm(DialogInterface dialog, int which) {
		if(confirmListener != null){
			confirmListener.onClick(dialog, which);
		}
		
		calendar.set(year, month, day, hour, min, 0);
		setText(format.format(calendar.getTime()));
	}

	@Override
	public void cancel(DialogInterface dialog, int which) {
		if(cancelListener != null){
			cancelListener.onClick(dialog, which);
		}
		
		setText(format.format(calendar.getTime()));
	}

	
	
	private void updateTitle(DatePicker datePicker, TimePicker timePicker) {
		// 更新对话框标题
		
		if(datePicker != null && timePicker != null){
			Calendar calendar = Calendar.getInstance();
			calendar.set(datePicker.getYear(), 
					datePicker.getMonth(),
					datePicker.getDayOfMonth(), 
					timePicker.getCurrentHour(), 
					timePicker.getCurrentMinute());
			dialog.setTitle(format.format(calendar.getTime()));
		}
	}

	
	public Calendar getShowCalendar() {
		return calendar;
	}

	public void setShowCalendar(Calendar showCalendar) {
		this.calendar = showCalendar;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public TimePicker getTimePicker() {
		return timePicker;
	}

	public void setTimePicker(TimePicker timePicker) {
		this.timePicker = timePicker;
	}

	public OnDateChangedListener getDateChangedListener() {
		return dateChangedListener;
	}

	public void setDateChangedListener(OnDateChangedListener dateChangedListener) {
		this.dateChangedListener = dateChangedListener;
	}

	public OnTimeChangedListener getTimeChangedListener() {
		return timeChangedListener;
	}

	public void setTimeChangedListener(OnTimeChangedListener timeChangedListener) {
		this.timeChangedListener = timeChangedListener;
	}

	public DialogInterface.OnClickListener getConfirmListener() {
		return confirmListener;
	}

	public void setConfirmListener(DialogInterface.OnClickListener confirmListener) {
		this.confirmListener = confirmListener;
	}

	public DialogInterface.OnClickListener getCancelListener() {
		return cancelListener;
	}

	public void setCancelListener(DialogInterface.OnClickListener cancelListener) {
		this.cancelListener = cancelListener;
	}

}
