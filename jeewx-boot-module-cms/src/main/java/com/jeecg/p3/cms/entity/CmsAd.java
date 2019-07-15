package com.jeecg.p3.cms.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>广告管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时45分31秒 星期二 
 * @version:1.0
 */
public class CmsAd implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *createName	 */	private String createName;	/**	 *createBy	 */	private String createBy;	/**	 *createDate	 */	private Date createDate;	/**	 *标题	 */	private String title;	/**	 *图片地址	 */	private String imageHref;	/**	 *是否显示，0不显示，1显示	 */	private Integer isShow;	/**	 *简述	 */	private String resume;	/**	 *链接	 */	private String link;	/**	 *顺序	 */	private Integer sort;
	/**
	 * 站点ID
	 */
	private String mainId;
	
	/**
	 * html5网站网址
	 */
	private String htmlLink;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getImageHref() {	    return this.imageHref;	}	public void setImageHref(String imageHref) {	    this.imageHref=imageHref;	}	public Integer getIsShow() {	    return this.isShow;	}	public void setIsShow(Integer isShow) {	    this.isShow=isShow;	}	public String getResume() {	    return this.resume;	}	public void setResume(String resume) {	    this.resume=resume;	}	public String getLink() {	    return this.link;	}	public void setLink(String link) {	    this.link=link;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	/**
	 * @return the htmlLink
	 */
	public String getHtmlLink() {
		return htmlLink;
	}
	/**
	 * @param htmlLink the htmlLink to set
	 */
	public void setHtmlLink(String htmlLink) {
		this.htmlLink = htmlLink;
	}
	
}

