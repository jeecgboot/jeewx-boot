package com.jeecg.p3.tmessage.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>消息模板表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时21分04秒 星期三 
 * @version:1.0
 */
public class WeixinTmessage implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *模板ID	 */	private String templateId;	/**	 *JWID	 */	private String jwid;	/**	 *标题	 */	private String title;	/**	 *行业	 */	private String industry;	/**	 *子行业	 */	private String subIndustry;	/**	 *内容	 */	private String content;	/**	 *示例	 */	private String example;	/**	 *创建时间	 */	private Date createDate;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getIndustry() {	    return this.industry;	}	public void setIndustry(String industry) {	    this.industry=industry;	}	public String getSubIndustry() {	    return this.subIndustry;	}	public void setSubIndustry(String subIndustry) {	    this.subIndustry=subIndustry;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getExample() {	    return this.example;	}	public void setExample(String example) {	    this.example=example;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}
}

