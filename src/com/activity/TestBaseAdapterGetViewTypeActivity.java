package com.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dingbaosheng.R;

public class TestBaseAdapterGetViewTypeActivity extends Activity {

	ListView mListView;
	ArrayList<Entity> mList = new ArrayList<Entity>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_baseadapter_getviewtype_activity);
		for (int i = 0; i < 30; i++) {
			Entity item = new Entity();
			item.id = i;
			item.name = "name"+i;
			
			mList.add(item);
		}
		
		
		
		mListView = (ListView)findViewById(R.id.mlist);
		mListView.setAdapter(new MyAdapter());
		
	}
	
	private String TAG = "Adatper";
	private class MyAdapter extends BaseAdapter{

		private int TYPE_ME = 0;
		private int TYPE_FRIENDS = 1;
		private int TYPE_STRANGER = 2;
		
		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MeViewHolder mViewHolder = null;
			FriendsViewHolder fViewHolder = null;
			StrangerViewHolder sViewHolder = null;
			
			int type = getItemViewType(position);
			Log.e(TAG, "==========================");
			Log.e("Adapter", "convertview is null ? -->"+(convertView == null));
			if(convertView == null){
				Log.e(TAG, "创建convertview postion:"+position);
				if(type == TYPE_ME){
					convertView = getLayoutInflater().inflate(R.layout.item_list_my, null);
					
					mViewHolder = new MeViewHolder();
					mViewHolder.mLabel  = (TextView)convertView.findViewById(R.id.tv_my);
					convertView.setTag(mViewHolder);
				}
				if(type == TYPE_FRIENDS){
					convertView = getLayoutInflater().inflate(R.layout.item_list_friends, null);
					
					fViewHolder = new FriendsViewHolder();
					fViewHolder.mLabel  = (TextView)convertView.findViewById(R.id.tv_friends);
					convertView.setTag(fViewHolder);
				}
				
				if(type == TYPE_STRANGER){
					convertView = getLayoutInflater().inflate(R.layout.item_list_stranger, null);
					
					sViewHolder = new StrangerViewHolder();
					sViewHolder.mLabel  = (TextView)convertView.findViewById(R.id.tv_strangers);
					convertView.setTag(sViewHolder);
				}
			}else{
				Log.e(TAG, "重用convertview postion:"+position);
				Log.e(TAG, "convertview's address"+convertView.toString());
				if(type == TYPE_ME){
					mViewHolder = (MeViewHolder) convertView.getTag();
				}
				if(type == TYPE_FRIENDS){
					fViewHolder = (FriendsViewHolder) convertView.getTag();
				}
				
				if(type == TYPE_STRANGER){
					sViewHolder = (StrangerViewHolder) convertView.getTag();
				}
			}
			
			Entity item = (Entity) getItem(position);
			
			if(type == TYPE_ME){
				mViewHolder.mLabel.setText("postion:"+position+ "  "+item.name);
			}
			
			if(type == TYPE_FRIENDS){
				fViewHolder.mLabel.setText("postion:"+position+ "  "+item.name);
			}
			
			if(type == TYPE_STRANGER){
				sViewHolder.mLabel.setText("postion:"+position+ "  "+item.name);
			}
			
			
			return convertView;
		}
		
		@Override
		public int getViewTypeCount() {
			return 3;
		}
		@Override
		public int getItemViewType(int position) {
			if(position == 11){
				return TYPE_STRANGER;
			}
			if(position == 0){
				return TYPE_ME;
			}
			
			return TYPE_FRIENDS;
		}
		
		private class MeViewHolder{
			TextView mLabel;
		}
		private class FriendsViewHolder{
			TextView mLabel;
		}
		
		private class StrangerViewHolder{
			TextView mLabel;
		}
		
	}
	
	private class Entity{
		public int id;
		public String name;
		public String typeValue;
		public int type;
	}
}
