package com.jeecg.p3.tmessage.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>发送模板消息表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时23分28秒 星期三 
 * @version:1.0
 */
public class WeixinTmessgaeTask implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;
	/**
	 * 标题
	 */
	private String title;	/**	 *模板ID	 */	private String templateId;	/**	 *JWID	 */	private String jwid;	/**	 *发送模式，0：标签模式，1：列表模式	 */	private String sendType;	/**	 *标签ID	 */	private String tagId;	/**	 *OPENID	 */	private String openid;	/**	 *跳转方式，0：跳转链接，1：跳转小程序	 */	private String redirectMode;	/**	 *跳转链接	 */	private String url;	/**	 *小程序APPID	 */	private String miniAppid;	/**	 *小程序跳转链接	 */	private String pagepath;	/**	 *颜色	 */	private String color;	/**	 *发送总人数	 */	private Integer totalCount;	/**	 *成功总人数	 */	private Integer successCount;	/**	 *创建时间	 */	private Date createDate;	/**	 *发送时间	 */	private Date taskSendTime;	/**	 *发送状态，0：未发送，1：已发送，2：发送失败，9：定时发送	 */	private String taskSendStatus;	/**	 *实际发送时间	 */	private Date sendTime;
	/**
	 * JSON数据
	 */
	private String dataJson;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getSendType() {	    return this.sendType;	}	public void setSendType(String sendType) {	    this.sendType=sendType;	}	public String getTagId() {	    return this.tagId;	}	public void setTagId(String tagId) {	    this.tagId=tagId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getRedirectMode() {	    return this.redirectMode;	}	public void setRedirectMode(String redirectMode) {	    this.redirectMode=redirectMode;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public String getMiniAppid() {	    return this.miniAppid;	}	public void setMiniAppid(String miniAppid) {	    this.miniAppid=miniAppid;	}	public String getPagepath() {	    return this.pagepath;	}	public void setPagepath(String pagepath) {	    this.pagepath=pagepath;	}	public String getColor() {	    return this.color;	}	public void setColor(String color) {	    this.color=color;	}	public Integer getTotalCount() {	    return this.totalCount;	}	public void setTotalCount(Integer totalCount) {	    this.totalCount=totalCount;	}	public Integer getSuccessCount() {	    return this.successCount;	}	public void setSuccessCount(Integer successCount) {	    this.successCount=successCount;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public Date getTaskSendTime() {	    return this.taskSendTime;	}	public void setTaskSendTime(Date taskSendTime) {	    this.taskSendTime=taskSendTime;	}	public String getTaskSendStatus() {	    return this.taskSendStatus;	}	public void setTaskSendStatus(String taskSendStatus) {	    this.taskSendStatus=taskSendStatus;	}	public Date getSendTime() {	    return this.sendTime;	}	public void setSendTime(Date sendTime) {	    this.sendTime=sendTime;	}
	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

