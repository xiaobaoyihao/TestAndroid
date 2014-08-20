package com.utils;

import android.graphics.BitmapFactory.Options;

/**
 * utils 
 * @author dingbaosheng
 * @date 2014-8-15
 */
public class Utils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	
	public int calcSampleSize(Options options, int reqWidth, int reqHeight){
		int sampleSize = 1;
		int width = options.outWidth;
		int height = options.outHeight;
		
		if(reqWidth > width || reqHeight > height){
			final int halfWidth = width/2;
			final int halfHeight = height/2;
			
			while(halfWidth / sampleSize > reqWidth && halfHeight/ sampleSize > reqHeight){
				sampleSize *= 2;
			}
		}
		
		return sampleSize;
	}
}
