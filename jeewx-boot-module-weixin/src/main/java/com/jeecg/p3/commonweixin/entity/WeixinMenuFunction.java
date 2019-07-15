package com.jeecg.p3.commonweixin.entity;

import java.io.Serializable;

/**
 * 描述：菜单权限和按钮权限对象
 * @author：<a href="mailto:zhoujunfeng@jd.com">周俊峰</a>
 * @since：2013年11月27日 上午9:23:38
 * @version:1.0
 */
public class WeixinMenuFunction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/***
	 * 用户编码
	 */
	private String userId;
	/***
	 * 权限名称
	 */
	private String authName;
	/***
	 * 权限说明
	 */
	private String authDesc;
	/***
	 * 权限控制
	 */
	private String authContr;
	/***
	 * 权限编码
	 */
	private String authId;
	
	/**
	 * 权限类型
	 */
	private String authType;
	
	/***
	 * 上一级权限编码
	 */
	private String parentAuthId;
	
	/***
	 * 是否叶子节点
	 */
	private String leafInd;
	/**
	 *菜单图标
	 */
	private String iconType;
	
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
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public String getParentAuthId() {
		return parentAuthId;
	}
	public void setParentAuthId(String parentAuthId) {
		this.parentAuthId = parentAuthId;
	}
	public String getLeafInd() {
		return leafInd;
	}
	public void setLeafInd(String leafInd) {
		this.leafInd = leafInd;
	}
	@Override
	public String toString() {
		return "MenuFunction [userId=" + userId + ", authName=" + authName
				+ ", authDesc=" + authDesc + ", authContr=" + authContr
				+ ", authId=" + authId + ", authType=" + authType
				+ ", parentAuthId=" + parentAuthId + ", leafInd=" + leafInd
				+ "]";
	}
	public void setIconType(String iconType) {
		this.iconType = iconType;
	}
	public String getIconType() {
		return iconType;
	}
	
	
}
