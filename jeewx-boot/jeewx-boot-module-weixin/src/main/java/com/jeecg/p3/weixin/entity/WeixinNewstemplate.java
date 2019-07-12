package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>图文模板表<br>
 * @author weijian.zhang
 * @since：2018年07月13日 12时46分13秒 星期五 
 * @version:1.0
 */
public class WeixinNewstemplate implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	//update-begin--Author:zhangweijian  Date: 20180802 for：新增media_id字段
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	/**
	//update-end--Author:zhangweijian  Date: 20180802 for：新增media_id字段
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	/**
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	//author:sunkai--date:2018-10-08--for:上传状态转译
	/**
	 *上传状态转译
	 */
	private String uploadStatus;
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	//author:sunkai--date:2018-10-08--for:上传状态转译
	public String getId() {
}
