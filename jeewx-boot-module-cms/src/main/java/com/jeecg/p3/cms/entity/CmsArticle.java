package com.jeecg.p3.cms.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>文章管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
public class CmsArticle implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *标题	 */	private String title;	/**	 *图片地址	 */	private String imageHref;	/**	 *摘要	 */	private String summary;	/**	 *	 */	private String contentHtml;	/**	 *内容	 */	private String content;	/**	 *栏目id	 */	private String columnId;	/**	 *创建人	 */	private String createName;	/**	 *创建人id	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *是否发布	 */	private String publish;	/**	 *发布时间	 */	private Date publishDate;	/**	 *作者	 */	private String author;	/**	 *标签	 */	private String label;	/**	 *序号	 */	private Integer serialNumber;	/**	 *文章编码	 */	private String code;	/**	 *是否链接，0没有链接，1链接	 */	private Integer isLink;	/**	 *链接地址	 */	private String link;	/**	 *是否在明细页面显示 0不显示，1显示	 */	private Integer isShow;
	/**
	 * 站点ID
	 */
	private String mainId;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getImageHref() {	    return this.imageHref;	}	public void setImageHref(String imageHref) {	    this.imageHref=imageHref;	}	public String getSummary() {	    return this.summary;	}	public void setSummary(String summary) {	    this.summary=summary;	}	public String getContentHtml() {	    return this.contentHtml;	}	public void setContentHtml(String contentHtml) {	    this.contentHtml=contentHtml;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getColumnId() {	    return this.columnId;	}	public void setColumnId(String columnId) {	    this.columnId=columnId;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getPublish() {	    return this.publish;	}	public void setPublish(String publish) {	    this.publish=publish;	}	public Date getPublishDate() {	    return this.publishDate;	}	public void setPublishDate(Date publishDate) {	    this.publishDate=publishDate;	}	public String getAuthor() {	    return this.author;	}	public void setAuthor(String author) {	    this.author=author;	}	public String getLabel() {	    return this.label;	}	public void setLabel(String label) {	    this.label=label;	}	public Integer getSerialNumber() {	    return this.serialNumber;	}	public void setSerialNumber(Integer serialNumber) {	    this.serialNumber=serialNumber;	}	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public Integer getIsLink() {	    return this.isLink;	}	public void setIsLink(Integer isLink) {	    this.isLink=isLink;	}	public String getLink() {	    return this.link;	}	public void setLink(String link) {	    this.link=link;	}	public Integer getIsShow() {	    return this.isShow;	}	public void setIsShow(Integer isShow) {	    this.isShow=isShow;	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	
}

