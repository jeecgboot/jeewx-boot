package com.jeecg.p3.system.vo;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwWebJwid:微信公众号字典表<br>
 * @author pituo
 * @since：2015年12月21日 17时22分01秒 星期一 
 * @version:1.0
 */
public class WeixinAccountDto implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *公众号原始id	 */	private String jwid;	/**	 *公众号名称	 */	private String name;	/**	 *应用类型	 */	private String applicationType;	/**	 *二维码图片	 */	private String qrcodeimg;	/**
	 * 公众号类型
	 */
	private String accountType;
	/**
	 * 是否认证
	 */
	private String authStatus;
	/**
	 * 类型：1手动授权，2扫码授权
	 */
	private String authType;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建人
	 */
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getQrcodeimg() {
		return qrcodeimg;
	}
	public void setQrcodeimg(String qrcodeimg) {
		this.qrcodeimg = qrcodeimg;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JwWebJwid [id=");
		builder.append(id);
		builder.append(", jwid=");
		builder.append(jwid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", applicationType=");
		builder.append(applicationType);
		builder.append(", qrcodeimg=");
		builder.append(qrcodeimg);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", authStatus=");
		builder.append(authStatus);
		builder.append(", authType=");
		builder.append(authType);
		builder.append(", createBy=");
		builder.append(createBy);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}
}

