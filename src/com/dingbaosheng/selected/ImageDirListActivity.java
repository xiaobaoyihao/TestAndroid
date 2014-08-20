package com.dingbaosheng.selected;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ListView;

import com.dingbaosheng.R;

public class ImageDirListActivity extends Activity {
	static String TAG = ImageDirListActivity.class.getSimpleName();

	ListView listView;
	Set<String> pathSet = new TreeSet<String>();
	List<List<File>> pathList = new ArrayList<List<File>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_dirlist);
		listView = (ListView) findViewById(R.id.lv_image_dir);

		ArrayList<String> listImage = new ArrayList<String>();
		// 扫描外部设备中的照片
		String str[] = { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str, null, null, null);

		while (cursor.moveToNext()) {
			Log.e(TAG, "=================================\n");
			// 图片绝对路径
			String pathString = cursor.getString(0).substring(0, cursor.getString(0).lastIndexOf("/"));
			Log.e(TAG, "image path:" + pathString); // 图片路径不含文件名
			pathSet.add(pathString);
		}
		
		Log.e(TAG, "pathset size:" + pathSet.size()); // 图片绝对路径

	}
	
	public List<File> getFileArray(String path){
		List<File> fileList = new ArrayList<File>();
		File dir = new File(path);
		File[] fileArray = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if(pathname.getName().contains(".jpg")|| pathname.getName().contains(".JPG")  
                        || pathname.getName().contains(".jpeg")|| pathname.getName().contains(".JPEG")                                    
                        || pathname.getName().contains(".png") || pathname.getName().contains(".PNG")
                        || pathname.getName().contains(".gif") || pathname.getName().contains(".GIF")
                        || pathname.getName().contains(".bmp") || pathname.getName().contains(".BMP")){
					return true;
				}
				return false;
			}
		});
		for (File file : fileArray) {
			fileList.add(file);
		}
		return fileList;
	}
}
