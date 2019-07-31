package com.jeecg.p3.baseApi.vo;

import org.jeecgframework.p3.core.utils.persistence.Entity;

import java.util.Date;

/**
 * 描述：</b>WeixinOpenAccount:<br>
 * @author huangqingquan
 * @since：2016年12月05日 17时50分49秒 星期一 
 * @version:1.0
 */
public class OpenAccountVo implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 *主键
	 */
	private String id;
	/**
	 *平台appid
	 */
	private String appid;
	/**
	 * appsecret
	 */
	private String appsecret;
	/**
	 *第三方平台推送 : ticket
	 */
	private String ticket;
	/**
	 *获取ticket时间
	 */
	private Date getTicketTime;
	/**
	 *平台方access_token
	 */
	private String componentAccessToken;
	/**
	 *平台方获取access_token时间
	 */
	private Date getAccessTokenTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Date getGetTicketTime() {
		return getTicketTime;
	}
	public void setGetTicketTime(Date getTicketTime) {
		this.getTicketTime = getTicketTime;
	}
	public String getComponentAccessToken() {
		return componentAccessToken;
	}
	public void setComponentAccessToken(String componentAccessToken) {
		this.componentAccessToken = componentAccessToken;
	}
	public Date getGetAccessTokenTime() {
		return getAccessTokenTime;
	}
	public void setGetAccessTokenTime(Date getAccessTokenTime) {
		this.getAccessTokenTime = getAccessTokenTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":\"");
		builder.append(id);
		builder.append("\", \"appid\":\"");
		builder.append(appid);
		builder.append("\", \"appsecret\":\"");
		builder.append(appsecret);
		builder.append("\", \"ticket\":\"");
		builder.append(ticket);
		builder.append("\", \"getTicketTime\":\"");
		builder.append(getTicketTime);
		builder.append("\", \"componentAccessToken\":\"");
		builder.append(componentAccessToken);
		builder.append("\", \"getAccessTokenTime\":\"");
		builder.append(getAccessTokenTime);
		builder.append("\"}");
		return builder.toString();
	}
}

