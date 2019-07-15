package com.jeecg.p3.system.vo;

import java.io.Serializable;

/**
 * 描述：</b>LoginUser:登录用户; <br>
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分29秒 星期一 
 * @version:1.0
 */
public class LoginUser implements Serializable{
	private static final long serialVersionUID = 1L;
		/**	 *用户编码	 */	private String userId;	/**	 *用户名称	 */	private String userName;
	/**
	 *密码
	 */
	private String password;
	/**
	 *用户状态 NORMAL:正常；INVALID：无效
	 */
	private String userStat;
	/**
	 *用户是否欠费状态0:正常；1：欠费
	 */
	private String chargeState;
	public void setChargeState(String chargeState) {
		this.chargeState = chargeState;
	}
	public String getChargeState() {
		return chargeState;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserStat() {
		return userStat;
	}
	public void setUserStat(String userStat) {
		this.userStat = userStat;
	}
	@Override
	public String toString() {
		return "LoginUser [userId=" + userId + ", userName=" + userName
				+ ", userStat=" + userStat + "]";
	}		
	
}

