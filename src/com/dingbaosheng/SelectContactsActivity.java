package com.dingbaosheng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Nickname;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.provider.ContactsContract.CommonDataKinds.Organization;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.adapter.UserEntityAdapter;
import com.entity.UserEntity;

public class SelectContactsActivity extends Activity{

	ListView listView;

	private static final String TAG = SelectContactsActivity.class.getSimpleName();

	ProgressDialog dialog;
	
	private List<UserEntity> allUserList;
	private List<UserEntity> selectUserList;
	
	public static final int LOADING = 1;
	public static final int FINISH = 2;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case LOADING:
				Log.e(TAG, "LOADING");
				dialog.setTitle("reading contact");
				dialog.setMessage("loading...");
				dialog.show();
				break;
			case FINISH:
				Log.e(TAG, "FINISH");
				dialog.dismiss();
				UserEntityAdapter adapter = new UserEntityAdapter(SelectContactsActivity.this, allUserList);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(adapter);
				break;
			default:
				break;
			}
		}
	};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_contact);
		listView = (ListView)findViewById(R.id.lv_select_contact);
		dialog = new ProgressDialog(this);
		
		selectUserList = new ArrayList<UserEntity>();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				handler.sendMessage(handler.obtainMessage(LOADING));
				getAllContacts();
				handler.sendMessage(handler.obtainMessage(FINISH));
			}
		}).start();
	}
	
	
	public void submit(View view){
		selectUserList.clear();
		for (UserEntity item : allUserList) {
			if(item.isSelected()){
				selectUserList.add(item);
			}
		}
		Intent intent = new Intent(this, GotoSelectContactActivity.class);
		intent.putExtra("result", (Serializable)selectUserList);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void getAllContacts() {
		// 获得所有的联系人
		Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, 
				null, 
				null,
				null,
				ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
		if (cur.moveToFirst()) {
			int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);
			int displayNameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			allUserList = new ArrayList<UserEntity>();
			do {
				
				UserEntity user = new UserEntity();
				
				// 获得联系人的ID号
				String contactId = cur.getString(idColumn);
				// 获得联系人姓名
				String disPlayName = cur.getString(displayNameColumn);
				
				//设置用户名
				user.setUserName(disPlayName);
				
				// 查看该联系人有多少个电话号码。如果没有这返回值为0
				int phoneCount = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				Log.e("username", disPlayName);
				
				getContactPhone(user, contactId, phoneCount);
				
				getContactEmails(user, contactId);

				
//				getContactsIM(contactId);
				
//				getContactAddress(contactId);
				
//				getContactOrgnization(contactId);
				
//				getContactNotes(contactId);
				
//				getContactNickname(contactId);
				
				allUserList.add(user);

			} while (cur.moveToNext());
			
		}
		if(cur != null){
			cur.close();
		}
	}


	/**
	 * 获得联系人的电话号码
	 * @param user
	 * @param contactId
	 * @param phoneCount
	 */
	private void getContactPhone(UserEntity user, String contactId, int phoneCount) {
		if (phoneCount > 0) {
			
			List<String> phoneList = new ArrayList<String>();
			user.setPhones(phoneList);;
			
			Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
					null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
					null,
					null);
			if (phones.moveToFirst()) {
				do {
					// 遍历所有的电话号码
					String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					String phoneType = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
					
					phoneList.add(phoneNumber);
					Log.e("phoneNumber", phoneNumber);
					Log.e("phoneType", phoneType);
				} while (phones.moveToNext());
			}
			if(phones != null){
				phones.close();
			}
		}
	}


	/**
	 * 获取nickname信息
	 * @param contactId
	 */
	private void getContactNickname(String contactId) {
		// 获取nickname信息
		Cursor nicknames = getContentResolver().query(Data.CONTENT_URI, 
				new String[] { Data._ID, Nickname.NAME },
				Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Nickname.CONTENT_ITEM_TYPE + "'",
				new String[] { contactId }, null);
		if (nicknames.moveToFirst()) {
			do {
				String nickname_ = nicknames.getString(nicknames.getColumnIndex(Nickname.NAME));
				Log.e("nickname_", nickname_);
			} while (nicknames.moveToNext());
		}
		if(nicknames != null){
			nicknames.close();
		}
	}


	/**
	 * // 获取备注信息
	 * @param contactId
	 */
	private void getContactNotes(String contactId) {
		
		Cursor notes = getContentResolver().query(Data.CONTENT_URI,
				new String[] { Data._ID, Note.NOTE },
				Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Note.CONTENT_ITEM_TYPE + "'", 
				new String[] { contactId }, 
				null);
		if (notes.moveToFirst()) {
			do {
				String noteinfo = notes.getString(notes.getColumnIndex(Note.NOTE));
				Log.e("noteinfo", noteinfo);
			} while (notes.moveToNext());
		}
		if(notes != null){
			notes.close();
		}
	}


	/**
	 * 获取该联系人组织
	 * @param contactId
	 */
	private void getContactOrgnization(String contactId) {
		Cursor organizations = getContentResolver().query(Data.CONTENT_URI,
				new String[] { Data._ID, Organization.COMPANY, Organization.TITLE },
				Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Organization.CONTENT_ITEM_TYPE + "'",
				new String[] { contactId },
				null);
		if (organizations.moveToFirst()) {
			do {
				String company = organizations.getString(organizations.getColumnIndex(Organization.COMPANY));
				String title = organizations.getString(organizations.getColumnIndex(Organization.TITLE));
				Log.e("company", company);
				Log.e("title", title);
			} while (organizations.moveToNext());
		}
		if(organizations != null){
			organizations.close();
		}
	}


	/**
	 * 获取该联系人地址
	 * @param contactId
	 */
	private void getContactAddress(String contactId) {
		// 
		Cursor address = getContentResolver().query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, 
				null,
				null);
		if (address.moveToFirst()) {
			do {
				// 遍历所有的地址
				String street = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
				String city = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
				String region = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
				String postCode = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
				String formatAddress = address.getString(address
						.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS));
				Log.e("street", street);
				Log.e("city", city);
				Log.e("region", region);
				Log.e("postCode", postCode);
				Log.e("formatAddress", formatAddress);
			} while (address.moveToNext());
		}
		if(address != null){
			address.close();
		}
	}

	/**
	 *  获取该联系人IM
	 * @param contactId
	 */

	private void getContactsIM(String contactId) {
		Cursor IMs = getContentResolver().query(Data.CONTENT_URI, 
				new String[] { Data._ID, Im.PROTOCOL, Im.DATA },
				Data.CONTACT_ID + "=?" + " AND " + Data.MIMETYPE + "='" + Im.CONTENT_ITEM_TYPE + "'",
				new String[] { contactId },
				null);
		if (IMs.moveToFirst()) {
			do {
				String protocol = IMs.getString(IMs.getColumnIndex(Im.PROTOCOL));
				String date = IMs.getString(IMs.getColumnIndex(Im.DATA));
				Log.e("protocol", protocol);
				Log.e("date", date);
			} while (IMs.moveToNext());
		}
		if(IMs != null){
			IMs.close();
		}
	}


	/**
	 * 获取该联系人邮箱
	 * @param user
	 * @param contactId
	 */
	private void getContactEmails(UserEntity user, String contactId) {
		Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
				null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
				null,
				null);
		if (emails.moveToFirst()) {
			List<String> emailList = new ArrayList<String>();
			user.setEmails(emailList);
			
			do {
				// 遍历所有的电话号码
				String emailType = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
				String emailValue = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));

				emailList.add(emailValue);
				Log.e("emailType", emailType);
				Log.e("emailValue", emailValue);
			} while (emails.moveToNext());
		}
		if(emails != null){
			emails.close();
		}
	}
}
