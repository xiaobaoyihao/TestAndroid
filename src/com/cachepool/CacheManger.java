package com.cachepool;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import android.os.Handler;

import com.hypers.frame.http.core.AsyncHttpResponseHandler;

public abstract class CacheManger<T> {

	static int LOADING;
	static int SUCCESS;
	static int FAILS;
	
	private ExecutorService cachePool;
	
	private Handler handler;
	private boolean called;
	private AsyncHttpResponseHandler responseHandler;
	
	public void loading(Callable<List<T>> task) throws Exception{
		handler.sendMessage(handler.obtainMessage(LOADING));
		
		Future<List<T>> future = cachePool.submit(task);
		
		//如果本地存在
		if(future.get() != null && future.get().size() >0){
			handler.sendMessage(handler.obtainMessage(SUCCESS, task.call()));
			return;
		}else if(!called){
			//如果本地不存在数据，且未调用过服务
			callService();
		}else {
			handler.sendMessage(handler.obtainMessage(SUCCESS));
		}
	}

	public abstract void callService();
	
	public abstract void cacheNetWork();
}
