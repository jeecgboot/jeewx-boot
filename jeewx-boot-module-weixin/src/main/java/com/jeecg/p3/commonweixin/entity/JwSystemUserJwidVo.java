package com.jeecg.p3.commonweixin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

public class JwSystemUserJwidVo implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *用户编码	 */	private String userId;	/**	 *公众号	 */	private String jwid;
	/**
	 * 默认登录jwid的标识
	 */
	private String defaultFlag;
	/**
	 * 名称
	 */
	private String userName;
	/**
	 * 手机号
	 */
	private String opePhoneNum;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOpePhoneNum() {
		return opePhoneNum;
	}
	public void setOpePhoneNum(String opePhoneNum) {
		this.opePhoneNum = opePhoneNum;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getUserId() {	    return this.userId;	}	public void setUserId(String userId) {	    this.userId=userId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
}

