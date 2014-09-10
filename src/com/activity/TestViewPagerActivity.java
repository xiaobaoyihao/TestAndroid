package com.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dingbaosheng.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.utils.Constants;
import com.view.IndexStatusView;

public class TestViewPagerActivity extends Activity implements OnPageChangeListener{
	
	ViewPager viewPager;
	ArrayList<String> imageUrlList = new ArrayList<String>();
	IndexStatusView indexStatusView;
	
	Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_viewpager_activity);
		
		viewPager = (ViewPager)findViewById(R.id.image_pager);
		indexStatusView  = (IndexStatusView)findViewById(R.id.indexStatusView1);
		
		for (int i = 0; i < 4; i++) {
			imageUrlList.add(Constants.IMAGES[i]);
		}
		ImageAdapter imageAdapter = new ImageAdapter() ;
		viewPager.setAdapter(imageAdapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(0);
		
		handler.postDelayed(task, 2000);
		
	}
	
	private Runnable task = new Runnable() {
		
		@Override
		public void run() {
			viewPager.setCurrentItem((viewPager.getCurrentItem() +1)%4);
			handler.postDelayed(this, 2000);
		}
	};
	private class ImageAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return imageUrlList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			View imageLayout = getLayoutInflater().inflate(R.layout.item_pager_image, null);
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.item_image_pager_image);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.item_image_pager_loading);

			//点击图片
			imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(TestViewPagerActivity.this, "click me", Toast.LENGTH_SHORT).show();
				}
			});
			
			ImageLoader.getInstance().displayImage(imageUrlList.get(position), imageView, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {
					spinner.setVisibility(View.VISIBLE);
				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
					String message = null;
					switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					spinner.setVisibility(View.GONE);
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
				}
			});
			

			((ViewPager) container).addView(imageLayout, 0);
			return imageLayout;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int postion) {
		indexStatusView.onPageSelected(postion);
	}
}


