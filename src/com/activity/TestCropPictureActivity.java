package com.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.dingbaosheng.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TestCropPictureActivity extends Activity {

	/* 组件 */
	private LinearLayout switchAvatar;
	private ImageView faceImage;

	private String[] items = new String[] { "选择本地图片", "拍照" };
	/* 头像名称 */
	private static final String IMAGE_FILE_NAME = "faceImage.jpg";

	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 去掉标题
		setContentView(R.layout.activity_test_crop_picture);
		switchAvatar = (LinearLayout) findViewById(R.id.ll);
		faceImage = (ImageView) findViewById(R.id.face);
		// 设置事件监听
		switchAvatar.setOnClickListener(listener);
	}

	private View.OnClickListener listener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			showDialog();
		}
	};

	/**
	 * 显示选择对话框
	 */
	private void showDialog() {

		new AlertDialog.Builder(this).setTitle("设置头像").setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent intentFromGallery = new Intent();
					intentFromGallery.setType("image/*"); // 设置文件类型
					intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
					break;
				case 1:

					Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// 判断存储卡是否可以用，可用进行存储
					if (hasSdcard()) {

						intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
					}

					startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 结果码不等于取消时候 if (resultCode != RESULT_CANCELED) {
		switch (requestCode) {
		case IMAGE_REQUEST_CODE:
			startPhotoZoom(data.getData());
			break;
		case CAMERA_REQUEST_CODE:
			if (hasSdcard()) {
				File tempFile = new File(Environment.getExternalStorageDirectory(),IMAGE_FILE_NAME);
				startPhotoZoom(Uri.fromFile(tempFile));
			} else {
				Toast.makeText(TestCropPictureActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
			}

			break;
		case RESULT_REQUEST_CODE:
			if (data != null) {
				getImageToView(data);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			faceImage.setImageDrawable(drawable);
			
			FileOutputStream outputStream = null;
			try {
				outputStream = new FileOutputStream(new File(Environment.getExternalStorageDirectory(),"face.png"));
				
				//保存裁剪图
				photo.compress(CompressFormat.PNG, 100, outputStream);
				
				outputStream.flush();
				outputStream.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
