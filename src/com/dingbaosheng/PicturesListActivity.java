package com.dingbaosheng;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.adapter.PictureAdapter;

public class PicturesListActivity extends Activity implements OnItemClickListener, OnItemLongClickListener{

	ListView listView;
	List<File> files;
	File picDir;
	PictureAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pictures_list);
		listView = (ListView)findViewById(R.id.lv_pictures);
		
		picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		files = new ArrayList<File>();
		
		adapter = new PictureAdapter(this, files);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		files.clear();
		if(picDir.exists()){
			for (File file : picDir.listFiles()) {
				files.add(file);
			}
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		/*File file = files.get(position);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "image/*");
		startActivity(intent);*/
		
		
//		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//		startActivity(intent);
		
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		final int index = position;
		Builder builder = new Builder(this);
		builder.setMessage("确定要删除吗?");
		builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				files.remove(index).delete();
				adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).create().show();;
		
		return true;
	}
}
