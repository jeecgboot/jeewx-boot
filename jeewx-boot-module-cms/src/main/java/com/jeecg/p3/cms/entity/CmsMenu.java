package com.jeecg.p3.cms.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>栏目管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时51分57秒 星期二 
 * @version:1.0
 */
public class CmsMenu implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *	 */	private String createBy;	/**	 *	 */	private Date createDate;	/**	 *	 */	private String createName;	/**	 *图片地址	 */	private String imageHref;	/**	 *图片名称	 */	private String imageName;	/**	 *栏目名称	 */	private String name;	/**	 *类型	 */	private String type;	/**	 *父节点	 */	private String parentCode;	/**	 *地址	 */	private String href;	/**	 *模版样式	 */	private String templateCode;	/**	 *序号	 */	private Integer serialNumber;	/**	 *描述	 */	private String content;
	/**
	 * 站点ID
	 */
	private String mainId;
	/**
	 * 是否显示0不显示，1显示
	 */
	private Integer isShow;
	// update-begin--Author:gj_duzy Date:20181114 for：新增栏目背景颜色属性
	/**
	 * 栏目背景颜色
	 */
	private String colorBckColor;	
	// update-end--Author:gj_duzy Date:20181114 for：新增栏目背景颜色属性
	
	/**
	 * 栏目链接地址（H5）
	 */
	private String htmlLink;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getImageHref() {	    return this.imageHref;	}	public void setImageHref(String imageHref) {	    this.imageHref=imageHref;	}	public String getImageName() {	    return this.imageName;	}	public void setImageName(String imageName) {	    this.imageName=imageName;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public String getParentCode() {	    return this.parentCode;	}	public void setParentCode(String parentCode) {	    this.parentCode=parentCode;	}	public String getHref() {	    return this.href;	}	public void setHref(String href) {	    this.href=href;	}	public String getTemplateCode() {	    return this.templateCode;	}	public void setTemplateCode(String templateCode) {	    this.templateCode=templateCode;	}	public Integer getSerialNumber() {	    return this.serialNumber;	}	public void setSerialNumber(Integer serialNumber) {	    this.serialNumber=serialNumber;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public String getHtmlLink() {
		return htmlLink;
	}
	public void setHtmlLink(String htmlLink) {
		this.htmlLink = htmlLink;
	}
	public String getColorBckColor() {
		return colorBckColor;
	}
	public void setColorBckColor(String colorBckColor) {
		this.colorBckColor = colorBckColor;
	}
	
}

