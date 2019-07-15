package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>文本模板表<br>
 * @author weijian.zhang
 * @since：2018年07月13日 12时45分36秒 星期五 
 * @version:1.0
 */
public class WeixinTexttemplate implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *模板名称	 */	private String templateName;	/**	 *模板内容	 */	private String templateContent;	/**	 *公众号原始id	 */	private String jwid;	/**	 *创建人名称	 */	private String createBy;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改人名称	 */	private String updateBy;	/**	 *更新时间	 */	private Date updateTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTemplateName() {	    return this.templateName;	}	public void setTemplateName(String templateName) {	    this.templateName=templateName;	}	public String getTemplateContent() {	    return this.templateContent;	}	public void setTemplateContent(String templateContent) {	    this.templateContent=templateContent;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
}

