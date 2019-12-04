package com.jeecg.p3.message.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>群发消息日志表<br>
 * @author weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
public class WeixinGroupMessageSendLog implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *消息类型	 */	private String msgType;	/**	 *接受群组	 */	private String groupId;	/**	 *全部人员	 */	private String isToAll;	/**	 *参与	 */	private String param;	/**	 *公众号原始id	 */	private String jwid;	/**	 *审核人名称	 */	private String auditName;	/**	 *审核日期	 */	private Date auditDate;	/**	 *审核意见	 */	private String auditRemark;	/**	 *审核状态	 */	private String auditStatus;	/**	 *发送时间	 */	private Date sendDate;	/**	 *发送状态	 */	private String sendStatus;	/**	 *发送返回消息	 */	private String sendResult;	/**	 *发送的公众号原始id	 */	private String sendJwid;	/**	 *公众号名称	 */	private String sendJwidName;	/**	 *模板id	 */	private String templateId;	/**	 *模板名称	 */	private String templateName;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**	 *群发方式	 */	private String sendType;	/**	 *定时群发时间	 */	private Date sendTaskTime;	/**	 *标签id	 */	private String tagId;
	//update-begin--Author:zhangweijian Date:20181015 for：添加标签名称引用	/**	 *标签名称	 */	private String tagName;
		public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**	 *判定转载后是否继续群发 0:转载时停止群发 1:转载时继续群发	 */
	//update-end--Author:zhangweijian Date:20181015 for：添加标签名称引用	private String sendIgnoreReprint;
	/**
	 *创建人
	 */
	private String createBy;
	/**
	 *创建时间
	 */
	private Date createTime;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getMsgType() {	    return this.msgType;	}	public void setMsgType(String msgType) {	    this.msgType=msgType;	}	public String getGroupId() {	    return this.groupId;	}	public void setGroupId(String groupId) {	    this.groupId=groupId;	}	public String getIsToAll() {	    return this.isToAll;	}	public void setIsToAll(String isToAll) {	    this.isToAll=isToAll;	}	public String getParam() {	    return this.param;	}	public void setParam(String param) {	    this.param=param;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getAuditName() {	    return this.auditName;	}	public void setAuditName(String auditName) {	    this.auditName=auditName;	}	public Date getAuditDate() {	    return this.auditDate;	}	public void setAuditDate(Date auditDate) {	    this.auditDate=auditDate;	}	public String getAuditRemark() {	    return this.auditRemark;	}	public void setAuditRemark(String auditRemark) {	    this.auditRemark=auditRemark;	}	public String getAuditStatus() {	    return this.auditStatus;	}	public void setAuditStatus(String auditStatus) {	    this.auditStatus=auditStatus;	}	public Date getSendDate() {	    return this.sendDate;	}	public void setSendDate(Date sendDate) {	    this.sendDate=sendDate;	}	public String getSendStatus() {	    return this.sendStatus;	}	public void setSendStatus(String sendStatus) {	    this.sendStatus=sendStatus;	}	public String getSendResult() {	    return this.sendResult;	}	public void setSendResult(String sendResult) {	    this.sendResult=sendResult;	}	public String getSendJwid() {	    return this.sendJwid;	}	public void setSendJwid(String sendJwid) {	    this.sendJwid=sendJwid;	}	public String getSendJwidName() {	    return this.sendJwidName;	}	public void setSendJwidName(String sendJwidName) {	    this.sendJwidName=sendJwidName;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getSendType() {	    return this.sendType;	}	public void setSendType(String sendType) {	    this.sendType=sendType;	}	public Date getSendTaskTime() {	    return this.sendTaskTime;	}	public void setSendTaskTime(Date sendTaskTime) {	    this.sendTaskTime=sendTaskTime;	}	public String getTagId() {	    return this.tagId;	}	public void setTagId(String tagId) {	    this.tagId=tagId;	}	public String getSendIgnoreReprint() {	    return this.sendIgnoreReprint;	}	public void setSendIgnoreReprint(String sendIgnoreReprint) {	    this.sendIgnoreReprint=sendIgnoreReprint;	}
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
}

