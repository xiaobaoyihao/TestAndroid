package com.hypers.frame.http;


/**接口总表*/
public enum PAPMInterface {
 
	/**
	 * 目标项目批量接口
	 */
	LOAD_TARGET_PROJECT_LIST("mobile/latent/find.json"),

	/**
	 * 目标项目单个接口
	 */
	LOAD_TARGET_PROJECT_SINGLE("mobile/latent/get.json"),
	
	/**
	 * 执行项目批量接口
	 */
	LOAD_EXECUTE_PROJECT_LIST("mobile/execution/find.json"),
	/**
	 * 执行项目单个接口
	 */
	LOAD_EXECUTE_PROJECT_SINGLE("mobile/execution/get.json"),
	
	/**
	 * 收件箱列表
	 * */
	INBOX_LIST("mobile/message/inbox/find.json"),
	
	/**
	 * 登录服务
	 */
	LOGIN("mobile/user/login.json"),
	
	/**
	 * 角标信息接口
	 */
	BADGE("mobile/message/badge.json"),
	
	/**
	 * 登出接口
	 */
	LOGOUT("mobile/user/logout.json"),
	
	/**
	 * 请求通讯录
	 */
	USER_LIST("mobile/user/list.json"),
	
	/**
	 * 发送消息
	 */
	SEND_MESSAGE("mobile/message/send.json"),
	
	/**
	 * 收件箱分页接口
	 */
	INBOX_PAGE("mobile/message/inbox/paging.json"),
	
	
	/**
	 * 单条消息接口
	 */
	SINGLE_MESSAGE("mobile/message/inbox/info.json"),
	
	/**
	 * 更新已读消息接口
	 */
	UPDATE_READED_MESSAGE("mobile/message/read.json"),
	
	/**
	 * 系统消息列表接口
	 */
	SYSTEM_MESSAGE_LIST("mobile/message/system/find.json"),
	
	/**
	 * 系统分页接口
	 */
	SYSTEM_PAGE("mobile/message/system/paging.json"),
	
	
	UPLOAD_FILE("user/fileupload"),
	
	USER_INFO("user/user_info"),
	
	
//	/android.json
		
	TEST("update/android.json");
	
	String subUrl = null;

	PAPMInterface(String subUrl) {
		this.subUrl = subUrl;
	}
	
	public String getSubUrl(){
		return subUrl;
	}
}
