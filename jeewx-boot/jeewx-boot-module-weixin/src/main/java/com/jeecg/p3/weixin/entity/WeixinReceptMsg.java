package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>客服消息记录表<br>
 * @author junfeng.zhou
 * @since：2018年10月18日 19时35分31秒 星期四 
 * @version:1.0
 */
public class WeixinReceptMsg implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *内容	 */	private String content;	/**	 *时间	 */	private Date createTime;	/**	 *发送人	 */	private String fromUsername;	/**	 *接收人	 */	private String toUsername;	/**	 *消息类型	 */	private String msgtype;	/**	 *多媒体id	 */	private String mediaid;	/**	 *素材ID	 */	private String templateId;	/**	 *发送状态，1：成功：2:失败	 */	private String sendStauts;	/**	 *公众号ID	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getFromUsername() {	    return this.fromUsername;	}	public void setFromUsername(String fromUsername) {	    this.fromUsername=fromUsername;	}	public String getToUsername() {	    return this.toUsername;	}	public void setToUsername(String toUsername) {	    this.toUsername=toUsername;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getMediaid() {	    return this.mediaid;	}	public void setMediaid(String mediaid) {	    this.mediaid=mediaid;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getSendStauts() {	    return this.sendStauts;	}	public void setSendStauts(String sendStauts) {	    this.sendStauts=sendStauts;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
}

