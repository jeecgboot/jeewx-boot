package com.jeecg.p3.tmessage.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>发送模板消息日志表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时24分48秒 星期三 
 * @version:1.0
 */
public class WeixinTmessageSendLog implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *OPENID	 */	private String openid;	/**	 *任务ID	 */	private String taskId;	/**	 *消息ID	 */	private String msgId;	/**	 *状态，0：成功，1，失败	 */	private String status;	/**	 *创建时间	 */	private Date createDate;
	/**
	 * JSON数据
	 */
	private String dataJson;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getTaskId() {	    return this.taskId;	}	public void setTaskId(String taskId) {	    this.taskId=taskId;	}	public String getMsgId() {	    return this.msgId;	}	public void setMsgId(String msgId) {	    this.msgId=msgId;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}
	public String getDataJson() {
		return dataJson;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
}

