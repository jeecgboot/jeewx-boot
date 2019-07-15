package com.jeecg.p3.commonweixin.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：微信公众号账号表
 * @author pituo
 * @since：2015年12月21日 17时22分01秒 星期一 
 * @version:1.0
 */
public class MyJwWebJwid implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *公众号原始id	 */	private String jwid;	/**	 *公众号名称	 */	private String name;	/**	 *应用类型	 */	private String applicationType;	/**	 *二维码图片	 */	private String qrcodeimg;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 微信号
	 */
	private String weixinNumber;
	/**
	 * 微信AppId
	 */
	private String weixinAppId;
	/**
	 * 微信AppSecret
	 */
	private String weixinAppSecret;
	/**
	 * 公众号类型
	 */
	private String accountType;
	/**
	 * 是否认证
	 */
	private String authStatus;
	/**
	 * Access_Token
	 */
	private String accessToken;
	/**
	 * token获取时间
	 */
	private Date tokenGetTime;
	/**
	 * Apiticket
	 */
	private String apiTicket;
	/**
	 * Apiticketf获取时间
	 */
	private Date apiTicketTime;
	/**
	 * jsApiticket
	 */
	private String jsApiTicket;
	/**
	 * jsApiticket获取时间
	 */
	private Date jsApiTicketTime;
	/**
	 * 令牌
	 */
	private String token;
	/**
	 *授权状态（1正常，2取消）
	 */
	private String authorizationStatus;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getApplicationType() {	    return this.applicationType;	}	public void setApplicationType(String applicationType) {	    this.applicationType=applicationType;	}
	
	
	public String getQrcodeimg() {
		return qrcodeimg;
	}
	public void setQrcodeimg(String qrcodeimg) {
		this.qrcodeimg = qrcodeimg;
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
	public String getWeixinNumber() {
		return weixinNumber;
	}
	public void setWeixinNumber(String weixinNumber) {
		this.weixinNumber = weixinNumber;
	}
	public String getWeixinAppId() {
		return weixinAppId;
	}
	public void setWeixinAppId(String weixinAppId) {
		this.weixinAppId = weixinAppId;
	}
	public String getWeixinAppSecret() {
		return weixinAppSecret;
	}
	public void setWeixinAppSecret(String weixinAppSecret) {
		this.weixinAppSecret = weixinAppSecret;
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
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Date getTokenGetTime() {
		return tokenGetTime;
	}
	public void setTokenGetTime(Date tokenGetTime) {
		this.tokenGetTime = tokenGetTime;
	}
	public String getApiTicket() {
		return apiTicket;
	}
	public void setApiTicket(String apiTicket) {
		this.apiTicket = apiTicket;
	}
	public Date getApiTicketTime() {
		return apiTicketTime;
	}
	public void setApiTicketTime(Date apiTicketTime) {
		this.apiTicketTime = apiTicketTime;
	}
	public String getJsApiTicket() {
		return jsApiTicket;
	}
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	public Date getJsApiTicketTime() {
		return jsApiTicketTime;
	}
	public void setJsApiTicketTime(Date jsApiTicketTime) {
		this.jsApiTicketTime = jsApiTicketTime;
	}
	//update begin Author:huangqingquan date:2016-12-1 11:53:15 for:一键授权使用字段---------------------------------
	
	private String authType;
	/**
	 * 功能的开通状况：1代表已开通
	 */
	private String businessInfo;
	/**
	 * 公众号授权给开发者的权限集
	 */
	private String funcInfo;
	/**
	 * 授权方昵称
	 */
	private String nickName;
	/**
	 * 授权方头像
	 */
	private String headimgurl;
	/**
	 * 授权信息
	 */
	private String authorizationInfo;
	
	/**
	 * 刷新token
	 */
	private String authorizerRefreshToken;
	
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getBusinessInfo() {
		return businessInfo;
	}
	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}
	public String getFuncInfo() {
		return funcInfo;
	}
	public void setFuncInfo(String funcInfo) {
		this.funcInfo = funcInfo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getAuthorizationInfo() {
		return authorizationInfo;
	}
	public void setAuthorizationInfo(String authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}
	public String getAuthorizerRefreshToken() {
		return authorizerRefreshToken;
	}
	public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
		this.authorizerRefreshToken = authorizerRefreshToken;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(String authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MyJwWebJwid [id=");
		builder.append(id);
		builder.append(", jwid=");
		builder.append(jwid);
		builder.append(", name=");
		builder.append(name);
		builder.append(", applicationType=");
		builder.append(applicationType);
		builder.append(", qrcodeimg=");
		builder.append(qrcodeimg);
		builder.append(", createBy=");
		builder.append(createBy);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", weixinNumber=");
		builder.append(weixinNumber);
		builder.append(", weixinAppId=");
		builder.append(weixinAppId);
		builder.append(", weixinAppSecret=");
		builder.append(weixinAppSecret);
		builder.append(", accountType=");
		builder.append(accountType);
		builder.append(", authStatus=");
		builder.append(authStatus);
		builder.append(", accessToken=");
		builder.append(accessToken);
		builder.append(", tokenGetTime=");
		builder.append(tokenGetTime);
		builder.append(", apiTicket=");
		builder.append(apiTicket);
		builder.append(", apiTicketTime=");
		builder.append(apiTicketTime);
		builder.append(", jsApiTicket=");
		builder.append(jsApiTicket);
		builder.append(", jsApiTicketTime=");
		builder.append(jsApiTicketTime);
		builder.append(", authType=");
		builder.append(authType);
		builder.append(", businessInfo=");
		builder.append(businessInfo);
		builder.append(", funcInfo=");
		builder.append(funcInfo);
		builder.append(", nickName=");
		builder.append(nickName);
		builder.append(", headimgurl=");
		builder.append(headimgurl);
		builder.append(", authorizationInfo=");
		builder.append(authorizationInfo);
		builder.append(", authorizerRefreshToken=");
		builder.append(authorizerRefreshToken);
		builder.append(", token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}
}

