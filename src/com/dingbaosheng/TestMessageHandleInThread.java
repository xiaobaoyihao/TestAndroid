package com.dingbaosheng;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

public class TestMessageHandleInThread extends Activity {

	Handler handler ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//主线程发消息，由子线程去处理
		
		Button button = new Button(this);
		button.setText("button");
		setContentView(button);
		MyThread thread = new MyThread();
		thread.start();
		
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(handler != null){
			handler.sendMessage(handler.obtainMessage(1, "xiaobaoyihao"));
		}
	}
	
	private class MyThread extends Thread implements Callback{
		
		@Override
		public void run() {
			super.run();
			Looper.prepare();
			
			handler = new Handler(this);
			
			Looper.myLooper().loop();
		}

		@Override
		public boolean handleMessage(Message msg) {
			
			if(msg.what == 1){
				Log.e("thread", Thread.currentThread().getName());
				Log.e("Mythread.handleMessage", "data:"+msg.obj.toString());
			}
			return false;
		}
	}
}
