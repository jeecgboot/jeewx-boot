package com.jeecg.p3.message.model;

public class BaseMessage {
	
	private Filter filter;
	
	private String msgtype;
	private Integer send_ignore_reprint;
	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Integer getSend_ignore_reprint() {
		return send_ignore_reprint;
	}

	public void setSend_ignore_reprint(Integer send_ignore_reprint) {
		this.send_ignore_reprint = send_ignore_reprint;
	}
	
}
