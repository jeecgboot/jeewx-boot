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
		/**	 *	 */	private String id;	/**	 *模板名称	 */	private String templateName;	/**	 *模板类型	 */	private String templateType;
	//update-begin--Author:zhangweijian  Date: 20180802 for：新增media_id字段	/**	 *图文素材媒体id	 */	private String mediaId;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	/**	 *公众号原始id	 */
	//update-end--Author:zhangweijian  Date: 20180802 for：新增media_id字段	private String jwid;	/**	 *上传状态	 */	private String uploadType;
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	/**	 *创建人名称	 */	private String createBy;	/**	 *上传时间	 */	private Date uploadTime;
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**	 *创建时间	 */	private Date createTime;	/**	 *修改人名称	 */	private String updateBy;	/**	 *修改人名称	 */	private Date updateTime;
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
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplateName() {	    return this.templateName;	}	public void setTemplateName(String templateName) {	    this.templateName=templateName;	}	public String getTemplateType() {	    return this.templateType;	}	public void setTemplateType(String templateType) {	    this.templateType=templateType;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
}

