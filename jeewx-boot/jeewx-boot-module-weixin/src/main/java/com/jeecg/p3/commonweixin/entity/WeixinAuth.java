package com.jeecg.p3.commonweixin.entity;

import java.io.Serializable;

/**
 * 描述：用户权限数据对象
 * @author：<a href="mailto:zhoujunfeng@jd.com">周俊峰</a>
 * @since：2013年11月26日 上午9:13:02
 * @version:1.0
 */
public class WeixinAuth implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户编码
	 */
	private String userId;
	
	/***
	 * 权限编码
	 */
	private String authId;
		/**
	 * 权限控制
	 */
	private String authContr;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuthContr() {
		return authContr;
	}
	public void setAuthContr(String authContr) {
		this.authContr = authContr;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	@Override
	public String toString() {
		return "Auth [userId=" + userId + ", authId=" + authId + ", authContr="
				+ authContr + "]";
	}	}

