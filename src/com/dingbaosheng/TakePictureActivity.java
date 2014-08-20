package com.dingbaosheng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakePictureActivity extends Activity {

static final int REQUEST_IMAGE_CAPTURE = 1;
	
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_takepictures);
		Button button = (Button)findViewById(R.id.btn_back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if(intent.resolveActivity(getPackageManager()) != null){
					
					File imageFile = null;
					try {
						imageFile = createImageFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(imageFile != null){
						intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
						startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
					}
				}
			}
		});
		imageView = (ImageView)findViewById(R.id.iv_thumbnail);
		
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File(mCurrentPhotoPath)), "image/*");
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
			mediaScan(mCurrentPhotoPath);
			setPic();
		}else if(resultCode == RESULT_CANCELED){
			deletePictures(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
		}
		
	}
	
	public void deletePictures(File dir){
		if(dir != null && dir.isDirectory()){
			for (File item : dir.listFiles()) {
				if(item.length() == 0){
					Log.e("delte file" ,item.delete()+"");
				}
			}
		}
	}
	
	private void setPic() {
	    // Get the dimensions of the View
	    int targetW = imageView.getWidth();
	    int targetH = imageView.getHeight();

	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    imageView.setImageBitmap(bitmap);
	}
	String mCurrentPhotoPath;
	
	public File createImageFile() throws IOException{
		File imageFile = null;
		long test = new Date().getTime();
		Log.e("createImageFile", "time long:"+test);
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(test);
		String imageFileName = "JPEG"+timeStamp+"_";
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		if(!storageDir.exists()){
			if(storageDir.mkdir()){
				imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
				mCurrentPhotoPath = imageFile.getAbsolutePath();
				return imageFile;
			}
			Log.e("mainactiviyt", "create Pictures directory fails");
			return imageFile;
		}
		imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
		mCurrentPhotoPath = imageFile.getAbsolutePath();
		
		Log.e("createImageFile", "mCurrentPhotoPath"+mCurrentPhotoPath);
		return imageFile;
	}
	
	
	//将图片添加媒体库
	public void mediaScan(final String filePath){
		
		MediaScannerConnectionClient mscclient = new MediaScannerConnectionClient() {
			private MediaScannerConnection msc = null;
			
			{
				msc = new MediaScannerConnection(TakePictureActivity.this, this);
				msc.connect();
			}
			
			@Override
			public void onMediaScannerConnected() {
				msc.scanFile(filePath, null);
			}
			
			@Override
			public void onScanCompleted(String path, Uri uri) {
				msc.disconnect();
				Log.e("TakePictureActivyt", "file add :"+uri.toString());
			}
		};
	}
}
