package com.activity;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TestLargePicActivity extends Activity {

	Button button;
	String FACE_NAME = "face.png";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		button = new Button(this);
		
		button.setText("enlarge");
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File file = new File(Environment.getExternalStorageDirectory(), FACE_NAME);
				Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
				
				int width = bitmap.getWidth();
				int heigh = bitmap.getHeight();
				double scale = 2;
				
				
				Matrix matrix = new Matrix();
				matrix.postScale(width, heigh, (float)(scale*width), (float)(scale*heigh));
				
				Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, heigh, matrix, true);
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(TestLargePicActivity.this);
				ImageView imageView = new ImageView(TestLargePicActivity.this);
				imageView.setImageBitmap(bitmap2);
				builder.setView(imageView);
				builder.setOnCancelListener(null);
				builder.create().show();
			}
		});
		
		setContentView(button);
	}
}
