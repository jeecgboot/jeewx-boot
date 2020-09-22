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
	
	/**
	 * 标题
	 */
	private String title;
	/**
	 * JSON数据
	 */
	private String dataJson;
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
