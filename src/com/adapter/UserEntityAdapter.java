package com.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dingbaosheng.R;
import com.entity.UserEntity;

public class UserEntityAdapter extends BaseAdapter implements OnItemClickListener {

	private Context context;
	private List<UserEntity> list;

	public UserEntityAdapter(Context context, List<UserEntity> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int location = position;
		final UserEntity user = (UserEntity) getItem(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, null);

			viewHolder.userNameTextView = (TextView) convertView.findViewById(R.id.tv_username);
			viewHolder.phoneTextView = (TextView) convertView.findViewById(R.id.tv_phone);
			viewHolder.emailTextView = (TextView) convertView.findViewById(R.id.tv_email);
			viewHolder.selectcCheckBox = (CheckBox) convertView.findViewById(R.id.cb_select);
//			viewHolder.selectcCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					if (isChecked) {
//						Log.e("adapter", "onChange  "+location+"=======isCheck");
//						user.setSelected(true);
////						printInfo();
//					} else {
//						Log.e("adapter", "onChange  "+location+"=======unCheck");
//						user.setSelected(false);
//					}
//				}
//			});

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.userNameTextView.setText(user.getUserName());

		if (user.getPhones() == null) {
			viewHolder.phoneTextView.setText("null");
		} else {
			viewHolder.phoneTextView.setText(user.getPhones().get(0));
		}

		if (user.getEmails() == null) {
			viewHolder.emailTextView.setText("null");
		} else {
			viewHolder.emailTextView.setText(user.getEmails().get(0));
		}

		if (user.isSelected()) {
//			Log.e("adapter", location+ " user is selected");
			viewHolder.selectcCheckBox.setChecked(true);
		} else {
//			Log.e("adapter", location+ " user is unselected");
			viewHolder.selectcCheckBox.setChecked(false);
		}
		
//		printInfo();
		
		return convertView;
	}

	public void printInfo(){
		Log.e("adapter", "========================================");
		for (int i = 0; i < list.size(); i++) {
			Log.e("adapter", "position:"+i+"  selected:"+list.get(i).isSelected());
		}
		Log.e("adapter", "========================================");
	}
	class ViewHolder {
		TextView userNameTextView;
		TextView phoneTextView;
		TextView emailTextView;
		CheckBox selectcCheckBox;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.e("adapter", "onitemclick");
		UserEntity entity = (UserEntity) getItem(position);
		ViewHolder holder = (ViewHolder) view.getTag();
		if(entity.isSelected()){
			entity.setSelected(false);
			holder.selectcCheckBox.setChecked(false);
		}else {
			entity.setSelected(true);
			holder.selectcCheckBox.setChecked(true);
		}
	}
}
