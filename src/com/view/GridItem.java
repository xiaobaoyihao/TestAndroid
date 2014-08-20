package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingbaosheng.R;

public class GridItem extends LinearLayout {

	private ImageView imageView;
	private TextView textView;
	
	public GridItem(Context context) {
		super(context);
	}

	public GridItem(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GridItem);

		int backgroundColor = typedArray.getResourceId(R.styleable.GridItem_background, android.R.color.black);
		Drawable drawable = typedArray.getDrawable(R.styleable.GridItem_icon);
		String text = typedArray.getString(R.styleable.GridItem_text);
		int textSize = typedArray.getInt(R.styleable.GridItem_textSize, 12);

		LayoutInflater.from(getContext()).inflate(R.layout.item_grid, this, true);
		
		imageView = (ImageView) findViewById(R.id.item_icon);
		textView = (TextView) findViewById(R.id.item_title);
		
		imageView.setImageDrawable(drawable);
		textView.setText(text);
		textView.setTextSize(textSize);
//		setBackgroundColor(backgroundColor);
		setBackgroundResource(backgroundColor);
	}
}
