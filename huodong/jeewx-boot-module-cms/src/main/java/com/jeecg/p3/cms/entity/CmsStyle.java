package com.jeecg.p3.cms.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>网站模板管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时55分54秒 星期二 
 * @version:1.0
 */
public class CmsStyle implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *	 */	private Date createDate;	/**	 *	 */	private String createName;	/**	 *	 */	private Date updateDate;	/**	 *	 */	private String updateName;	/**	 *模板名称	 */	private String templateName;	/**	 *预览图	 */	private String reviewImgUrl;	/**	 *模板地址	 */	private String templateUrl;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getTemplateName() {	    return this.templateName;	}	public void setTemplateName(String templateName) {	    this.templateName=templateName;	}	public String getReviewImgUrl() {	    return this.reviewImgUrl;	}	public void setReviewImgUrl(String reviewImgUrl) {	    this.reviewImgUrl=reviewImgUrl;	}	public String getTemplateUrl() {	    return this.templateUrl;	}	public void setTemplateUrl(String templateUrl) {	    this.templateUrl=templateUrl;	}
}

