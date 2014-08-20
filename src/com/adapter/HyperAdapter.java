package com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class HyperAdapter<T,V> extends BaseAdapter {

	private Context context;
	private List<T> list ;
	private int layoutRes;
	
	public HyperAdapter(Context context, List<T> list, int layoutRes){
		this.context = context;
		this.list = list;
		this.layoutRes = layoutRes;
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
		V viewholder = null;
		if(convertView == null){
			
			createInstance(viewholder);
			
			convertView = LayoutInflater.from(context).inflate(layoutRes, null);
			
			findViewFromView(convertView, viewholder);
			
			convertView.setTag(viewholder);
		}else {
			viewholder = (V) convertView.getTag();
		}
		
		bindData(viewholder, (T) getItem(position));
		
		return convertView;
	}

	
	@SuppressWarnings("unchecked")
	private void createInstance(V viewholder) {
		
		try {
			viewholder = (V) viewholder.getClass().newInstance();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
	public abstract void bindData(V viewholder, T item);
	
	public abstract void findViewFromView(View view, V viewholder);
	
}
