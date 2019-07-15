package com.jeecg.p3.commonweixin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

public class JwSystemUserVo implements Entity<Long> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private Long id;	/**	 *用户编码	 */	private String userId;	/**	 *用户名称	 */	private String userName;
	/**
	 *密码
	 */
	private String password;	/**	 *手机电话	 */	private String opePhoneNum;	/**	 *注册邮箱	 */	private String email;	private String agentId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getOpePhoneNum() {
		return opePhoneNum;
	}
	public void setOpePhoneNum(String opePhoneNum) {
		this.opePhoneNum = opePhoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
}

