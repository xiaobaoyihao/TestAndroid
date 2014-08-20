package com.adapter;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingbaosheng.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PictureAdapter extends BaseAdapter {

	private Context context;
	private List<File> list;
	private ImageLoader imageLoader;
	private SimpleDateFormat format;
	private DecimalFormat decimalFormat;
	
	public PictureAdapter(Context context, List<File> list){
		this.context = context;
		this.list = list;
		imageLoader = ImageLoader.getInstance();
		format = new SimpleDateFormat("yyyy:MM:dd-hh:mm:ss");
		decimalFormat = new DecimalFormat("#.##");
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_picture, null);
			
			viewHolder = new ViewHolder();
			viewHolder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
			viewHolder.fileNameTextView = (TextView) convertView.findViewById(R.id.tv_filename);
			viewHolder.createDateTextView = (TextView) convertView.findViewById(R.id.tv_create_data);
			viewHolder.filesizeTextView = (TextView) convertView.findViewById(R.id.tv_file_size);
			
			convertView.setTag(viewHolder);
		}else {
			
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		File file = (File) getItem(position);
		
		imageLoader.displayImage(Uri.fromFile(file).toString(), viewHolder.thumbnailImageView);
		
		viewHolder.fileNameTextView.setText(file.getName());
		viewHolder.createDateTextView.setText(format.format(new Date(file.lastModified())));
		double size = file.length()/(double)(1024*1024);
		viewHolder.filesizeTextView.setText(decimalFormat.format(size)+"Mb");
		
		return convertView;
	}

	class ViewHolder{
		ImageView thumbnailImageView;
		TextView fileNameTextView;
		TextView createDateTextView;
		TextView filesizeTextView;
	}
}
