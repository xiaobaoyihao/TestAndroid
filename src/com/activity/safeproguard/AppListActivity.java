package com.activity.safeproguard;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.app.Activity;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dingbaosheng.R;

public class AppListActivity extends Activity{
	String TAG = AppListActivity.class.getSimpleName();
	
	private ListView mListView;
	private ArrayList<PackageInfo> mList = new ArrayList<PackageInfo>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_list);
		mListView = (ListView)findViewById(R.id.listview);
		
		PackageManager packManger = getPackageManager();
		mList.addAll(packManger.getInstalledPackages(0));
		
		mListView.setAdapter(new MyAdapter());
		
	}
	
	private class MyAdapter extends BaseAdapter{

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
			
			ViewHolder viewHolder;
			
			if(convertView == null){
				convertView = getLayoutInflater().inflate(R.layout.item_list_app_list, null);
				viewHolder = new ViewHolder();
				viewHolder.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
				viewHolder.mTvPackName = (TextView) convertView.findViewById(R.id.tv_pack_name);
				viewHolder.mTvAppName = (TextView) convertView.findViewById(R.id.tv_app_name);
				viewHolder.mTvSize = (TextView) convertView.findViewById(R.id.tv_size);
				
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			
			PackageInfo item = (PackageInfo) getItem(position);
			
			viewHolder.mIvIcon.setImageDrawable(item.applicationInfo.loadIcon(getPackageManager()));
			viewHolder.mTvPackName.setText(item.applicationInfo.packageName);
			viewHolder.mTvAppName.setText(item.applicationInfo.loadLabel(getPackageManager()));
			
			try {
				querypackageSize(item.applicationInfo.packageName);
			} catch (Exception e) {
				Log.e("AppListActivity", e.getMessage());
			}
//			viewHolder.mTvSize.setText(item.applicationInfo.)
			
			
			return convertView;
		}
		
		
		/**
		 * note: you must add permission in AndroidMainifest.xml
		 * <uses-permission android:name="android.permission.GET_PACKAGE_SIZE"></uses-permission>  
		 * @param packageName
		 * @throws Exception
		 */
		public void querypackageSize(String packageName)throws Exception{
			Log.e(TAG, "querypackageSize  process Id"+Process.myPid()+" threadId:"+Process.myTid());
			if(packageName != null){
				try {
					PackageManager pm = getPackageManager();
					
					Method getPackageSizeInfoMethod = pm.getClass().getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
					getPackageSizeInfoMethod.invoke(pm, packageName, new PkgSizeObserver());
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("AppListActivity", e.getMessage());
					throw e;
				}
			}
		}
		
		
		
		
		private class ViewHolder{
			ImageView mIvIcon;
			TextView mTvAppName;
			TextView mTvPackName;
			TextView mTvSize;
		}
		
	}

	
	
	public class PkgSizeObserver extends IPackageStatsObserver.Stub{

		private final String TAG1 = PkgSizeObserver.class.getSimpleName();
		
		@Override
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
			Log.e(TAG1, "===========================================");
			Log.e(TAG, "PkgSizeObserver  process Id"+Process.myPid()+" threadId:"+Process.myTid());
			String result = android.text.format.Formatter.formatFileSize(AppListActivity.this, pStats.cacheSize+pStats.codeSize+pStats.dataSize);
			Log.e(TAG1, "onGetStatsCompleted: " 
		            +"cacheSize:"+pStats.cacheSize
					+"codeSize:"+pStats.codeSize
					+"dataSize:"+pStats.dataSize+
					"succeeded:"+succeeded+"\n result:"+result);
			
			
		}

	}
	
}
