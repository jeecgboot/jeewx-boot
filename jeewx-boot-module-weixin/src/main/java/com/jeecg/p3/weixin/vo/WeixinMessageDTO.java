package com.jeecg.p3.weixin.vo;

public class WeixinMessageDTO {
	/**
	 * 发送方帐号（open_id）
	 */
	private String fromUserName;
	/**
	 * 公众帐号(JWID)
	 */
	private String toUserName;
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 消息id
	 */
	private String msgId;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 多媒体ID
	 */
	private String mediaId;
	/**
	 * 菜单Key
	 */
	private String key;
	/**
	 * Event
	 */
	private String Event;
	/**
	 * 捷微公众账号ID(全局的数据权限ID)
	 */
	private String sysAccountId;
	/**
	 * 默认返回的文本消息内容
	 */
	private String respContent;
	/**
	 * 模板ID
	 */
	private String templateId;
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getSysAccountId() {
		return sysAccountId;
	}
	public void setSysAccountId(String sysAccountId) {
		this.sysAccountId = sysAccountId;
	}
	public String getRespContent() {
		return respContent;
	}
	public void setRespContent(String respContent) {
		this.respContent = respContent;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeixinMessageDTO [fromUserName=");
		builder.append(fromUserName);
		builder.append(", toUserName=");
		builder.append(toUserName);
		builder.append(", msgType=");
		builder.append(msgType);
		builder.append(", msgId=");
		builder.append(msgId);
		builder.append(", content=");
		builder.append(content);
		builder.append(", mediaId=");
		builder.append(mediaId);
		builder.append(", key=");
		builder.append(key);
		builder.append(", Event=");
		builder.append(Event);
		builder.append(", sysAccountId=");
		builder.append(sysAccountId);
		builder.append(", respContent=");
		builder.append(respContent);
		builder.append(", templateId=");
		builder.append(templateId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
