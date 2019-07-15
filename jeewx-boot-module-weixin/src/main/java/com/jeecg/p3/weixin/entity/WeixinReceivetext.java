package com.jeecg.p3.weixin.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>消息存储实体类<br>
 * @author LeeShaoQing
 * @since：2018年07月25日 16时02分13秒 星期三 
 * @version:1.0
 */
public class WeixinReceivetext implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *消息内容	 */	private String content;	/**	 *创建日期	 */	private Date createTime;		/**	 *发送方帐号（OpenId）	 */	private String fromUserName;	/**	 *消息ID	 */	private String msgId;	/**	 *消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)消息类型	 */	private String msgType;	/**	 *JWID	 */	private String toUserName;
	//update-begin--Author:zhangweijian  Date: 20180809 for：添加jwid字段	/**	 *公众号原始ID	 */	private String jwid;
	//*******冗余字段
	/**
	 *最近日期
	 */
	private String inRecentTime;
		/**
	 *头像
	 */
	private String headimgurl;
		/**
	 *昵称
	 */
	private String nicknameTxt;
		/**
	 *查询时间
	 */
	private String queryTime;
		
	public String getInRecentTime() {
		return inRecentTime;
	}
	public void setInRecentTime(String inRecentTime) {
		this.inRecentTime = inRecentTime;
	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	/**	 *多媒体ID	 */	private String mediaId;
	//update-end--Author:zhangweijian  Date: 20180809 for：添加jwid字段	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getFromUserName() {	    return this.fromUserName;	}	public void setFromUserName(String fromUserName) {	    this.fromUserName=fromUserName;	}	public String getMsgId() {	    return this.msgId;	}	public void setMsgId(String msgId) {	    this.msgId=msgId;	}	public String getMsgType() {	    return this.msgType;	}	public void setMsgType(String msgType) {	    this.msgType=msgType;	}	public String getToUserName() {	    return this.toUserName;	}	public void setToUserName(String toUserName) {	    this.toUserName=toUserName;	}	public String getMediaId() {	    return this.mediaId;	}	public void setMediaId(String mediaId) {	    this.mediaId=mediaId;	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getNicknameTxt() {
		return nicknameTxt;
	}
	public void setNicknameTxt(String nicknameTxt) {
		this.nicknameTxt = nicknameTxt;
	}
	public String getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}
	
}

