package com.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dingbaosheng.R;




public class IndexStatusView extends LinearLayout {
	
	private int focusDrawableId;
	private int unfousDrawableId;
	private int size;
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	
	public IndexStatusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IndexStatusView);
		focusDrawableId = typedArray.getResourceId(R.styleable.IndexStatusView_focus_drawable, R.drawable.ic_launcher2);
		unfousDrawableId = typedArray.getResourceId(R.styleable.IndexStatusView_un_focus_drawable, R.drawable.ic_launcher);
		size = typedArray.getInt(R.styleable.IndexStatusView_size, 0);
		
		setOrientation(LinearLayout.HORIZONTAL);//设置水平布局
		
		for (int i = 0; i < size; i++) {
			
			ImageView imageView = new ImageView(getContext());
			imageView.setImageResource(unfousDrawableId);
			
			addView(imageView);
			imageViews.add(imageView);
		}
		
		typedArray.recycle();
	}

	public IndexStatusView(Context context) {
		super(context);
	}
	
	
	public void onPageSelected(int index){
		if(size > index){
			for (int i = 0; i < imageViews.size(); i++) {
				if(index != i){
					imageViews.get(i).setImageResource(unfousDrawableId);
				}else {
					imageViews.get(i).setImageResource(focusDrawableId);
				}
			}
		}
	}
}
