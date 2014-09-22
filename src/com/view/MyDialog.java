package com.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.dingbaosheng.R;


public class MyDialog extends DialogFragment implements OnEditorActionListener{

	private EditText mEditText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_edit_name, container);
		
		mEditText = (EditText)view.findViewById(R.id.txt_your_name);
		init(mEditText);
		
		
		mEditText.requestFocus();
		getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		mEditText.setOnEditorActionListener(this);
		
		return view;
	}
	
	public void init(EditText editText1){
		
		
		 // 搜素  
        editText1.setImeOptions(EditorInfo.IME_ACTION_SEARCH);  
//        editText1.setInputType(EditorInfo.TYPE_CLASS_TEXT);  
  
//        // 下一个  
//        editText2.setImeOptions(EditorInfo.IME_ACTION_NEXT);  
//        editText2.setInputType(EditorInfo.TYPE_CLASS_TEXT);  
//  
//        // 发送  
//        editText3.setImeOptions(EditorInfo.IME_ACTION_SEND);  
//        editText3.setInputType(EditorInfo.TYPE_CLASS_TEXT);  
//  
//        // 完成  
//        editText4.setImeOptions(EditorInfo.IME_ACTION_DONE);  
//        editText4.setInputType(EditorInfo.TYPE_CLASS_TEXT);  
        
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		
		if(EditorInfo.IME_ACTION_DONE == actionId){
			Toast.makeText(getActivity(), "IME_ACTION_DONE", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
}
