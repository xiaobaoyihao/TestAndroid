package com;

import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activity.TestAnimationActiivty;
import com.activity.TestBottomTouchActivity;
import com.activity.TestCropPictureActivity;
import com.activity.TestCustomView;
import com.activity.TestDateUtilsActivity;
import com.activity.TestIntentFilterActivity;
import com.activity.TestLargePicActivity;
import com.activity.TestLayoutAnimationActivity;
import com.activity.TestLeftSlidCloseActivity;
import com.activity.TestTouchEventActivity;
import com.activity.TestViewScrollActivity;
import com.activity.safeproguard.AppListActivity;
import com.activity.sameid.TestSameIdActivity;
import com.dingbaosheng.AActivity;
import com.dingbaosheng.AudioRecordTest;
import com.dingbaosheng.GotoSelectContactActivity;
import com.dingbaosheng.NewActivity;
import com.dingbaosheng.PicturesListActivity;
import com.dingbaosheng.ProgressSeekBarRecord;
import com.dingbaosheng.R;
import com.dingbaosheng.Record2Activity;
import com.dingbaosheng.TakePictureActivity;
import com.dingbaosheng.TestDatePickDialog;
import com.dingbaosheng.TestGetSystemPhoneActivity;
import com.dingbaosheng.TestHttpActivity;
import com.dingbaosheng.TestMessageHandleInThread;
import com.dingbaosheng.TestViewPostMethodActivity;
import com.dingbaosheng.ZouMaDengActivity;
import com.dingbaosheng.selected.SelectedPictureActivty;

public class IndexActivity extends Activity implements OnItemClickListener{

	ListView listView;
	ArrayAdapter<Item> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		listView = (ListView)findViewById(R.id.listView);
		
		List<Item> data = new ArrayList<Item>();
		data.add(new Item("拍照", TakePictureActivity.class));
		data.add(new Item("录音", AudioRecordTest.class));
		data.add(new Item("录音2", Record2Activity.class));
		data.add(new Item("http",TestHttpActivity.class));
		data.add(new Item("选择联系人", TestGetSystemPhoneActivity.class));
		data.add(new Item("contacts", GotoSelectContactActivity.class));
		data.add(new Item("照片库", PicturesListActivity.class));
		data.add(new Item("griditem", NewActivity.class));
		data.add(new Item("fsdfsfs", ProgressSeekBarRecord.class));
		data.add(new Item("走马灯", ZouMaDengActivity.class));
		data.add(new Item("add image", SelectedPictureActivty.class));
		data.add(new Item("test view post method", TestViewPostMethodActivity.class));
		data.add(new Item("test datepick dialog", TestDatePickDialog.class));
		data.add(new Item("A --- B", AActivity.class));
		data.add(new Item("在子线程总处理主线程", TestMessageHandleInThread.class));
		data.add(new Item("图片裁剪", TestCropPictureActivity.class));
		data.add(new Item("发大图片", TestLargePicActivity.class));
		data.add(new Item("Animation", TestAnimationActiivty.class));
		data.add(new Item("testTouchEvent", TestTouchEventActivity.class));
		data.add(new Item("intentfilter data", TestIntentFilterActivity.class));
		data.add(new Item("左划关闭", TestLeftSlidCloseActivity.class));
		data.add(new Item("测试底部划出动画", TestBottomTouchActivity.class));
		data.add(new Item("测试View scrollTo scrollBy", TestViewScrollActivity.class));
		data.add(new Item("layoutanimation",TestLayoutAnimationActivity.class));
		data.add(new Item("TestSameIdActivity",TestSameIdActivity.class));
		data.add(new Item("TestDateUtils",TestDateUtilsActivity.class));
		data.add(new Item("safeProguard",AppListActivity.class));
		data.add(new Item("TestCustomView onMeasure",TestCustomView.class));
		
		
		
		
		
		adapter = new ArrayAdapter<Item>(IndexActivity.this, android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		//初始化科大讯飞
	}
	
	/**
	 * 检测是否读取联系人权限
	 * @param context
	 * @return
	 */
	public static boolean checkReadContactPermission(Context context){
		int hasPerm = context.getPackageManager().checkPermission(
				Manifest.permission.READ_CONTACTS, 
			    context.getPackageName());
		
		return  hasPerm == PackageManager.PERMISSION_GRANTED;
	}
	
	public static boolean checkReadContactPermission2(Context context){
		int hasPerm = context.checkCallingOrSelfPermission(Manifest.permission.READ_CONTACTS);
		return  hasPerm == PackageManager.PERMISSION_GRANTED;
	}
	
	class Item {
		String name;
		Class<?> clazz;
		
		public Item(String name, Class<?> clazz){
			this.name = name;
			this.clazz = clazz;
		}
		@Override
		public String toString() {
			return name;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Item item = adapter.getItem(position);
		startActivity(new Intent(this, item.clazz));
	}
}
