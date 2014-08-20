package com.dingbaosheng;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.View;

public class TestGetSystemPhoneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_system_phone);

	}

	public void gotoSystemContact(View view) {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setData(Contacts.CONTENT_URI);
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case RESULT_OK:
			switch (requestCode) {
			case 1:
				if (data == null) {
					return;
				}
				Uri contactData = data.getData();
				if (contactData == null) {
					return;
				}
				Cursor cursor = managedQuery(contactData, null, null, null, null);
				if(cursor != null && cursor.moveToFirst()){
					//联系人id
					String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
					//联系人姓名
					String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					// 查看该联系人有多少个电话号码。如果没有这返回值为0
					int phoneCount = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
					
					//查询联系号码
					List<String> phoneList = getContactPhone(contactId, phoneCount);
					
					//查询邮件
					List<String> emailList = getContactEmails(contactId);
				}
				if(cursor != null){
					cursor.close();
				}
				
			}
			break;
		}
	}
	
	
	/**
	 * 获得联系人的电话号码
	 * @param user
	 * @param contactId
	 * @param phoneCount
	 */
	private List<String> getContactPhone(String contactId, int phoneCount) {
		List<String> phoneList = null;
		if (phoneCount > 0) {
			phoneList = new ArrayList<String>();
			
			Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
					null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
					null,
					null);
			if (phones.moveToFirst()) {
				do {
					// 遍历所有的电话号码
					String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//					String phoneType = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
					
					phoneList.add(phoneNumber);
					Log.e("phoneNumber", phoneNumber);
//					Log.e("phoneType", phoneType);
				} while (phones.moveToNext());
			}
			if(phones != null){
				phones.close();
			}
		}
		
		return phoneList;
	}
	
	/**
	 * 获取该联系人邮箱
	 * @param user
	 * @param contactId
	 */
	private List<String> getContactEmails(String contactId) {
		List<String> emailList = null;
		
		Cursor emailsCusor = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
				null,
				null);
		if (emailsCusor.moveToFirst()) {
			emailList = new ArrayList<String>();
			
			do {
				// 遍历所有的电话号码
//				String emailType = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
				String emailValue = emailsCusor.getString(emailsCusor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

				emailList.add(emailValue);
//				Log.e("emailType", emailType);
				Log.e("emailValue", emailValue);
			} while (emailsCusor.moveToNext());
		}
		if(emailsCusor != null){
			emailsCusor.close();
		}
		return emailList;
	}
}
