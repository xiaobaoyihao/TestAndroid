package com.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dingbaosheng.R;

public class TestWebViewActivity extends Activity implements OnClickListener {

	WebView mWebView;
	
	Button mButton;
	TextView mTextView;
	
	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_webview_activity);
		
		mWebView = (WebView)findViewById(R.id.webview);
		mTextView = (TextView)findViewById(R.id.msg);
		
		
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("file:///android_asset/webview_js.html");
		
		mButton = (Button)findViewById(R.id.button);
		mButton.setOnClickListener(this);
		mWebView.addJavascriptInterface(this, "dbs");
		
		//启用
	}
	@Override
	public void onClick(View v) {
		if(v.getId() == mButton.getId()){
			//java 调用js
			
			mWebView.loadUrl("javascript:javacalljs()");
			
			mWebView.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");
		}
	}
	
	@JavascriptInterface
	public void startFunction(){
		Log.e("TestWebViewActivity", "==========startFunction()===========");
		Log.e("TestWebViewActivity", "currentThread id:"+Thread.currentThread().getName());
		Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show(); 
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTextView.setText("js invoke java no param of function");
			}
		});
		
	}
	
	@JavascriptInterface
	public void startFunction(final String arg){
		Log.e("TestWebViewActivity", "==========startFunction(String arg)===========");
		Log.e("TestWebViewActivity", "currentThread id:"+Thread.currentThread().getName());
		
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mTextView.setText("js invoke java function with param . param is:"+arg);
			}
		});
	}
	
}
