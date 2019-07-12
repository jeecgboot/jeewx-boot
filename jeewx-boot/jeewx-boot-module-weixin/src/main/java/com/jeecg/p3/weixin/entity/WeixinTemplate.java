package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>图文样式库表<br>
 * @author weijian.zhang
 * @since：2018年07月24日 20时01分05秒 星期二 
 * @version:1.0
 */
public class WeixinTemplate implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *创建人名称	 */	private String createName;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建日期	 */	private Date createDate;	/**	 *更新人名称	 */	private String updateName;	/**	 *更新人登录名称	 */	private String updateBy;	/**	 *更新日期	 */	private Date updateDate;	/**	 *标题	 */	private String title;	/**	 *类型	 */	private String type;	/**	 *模板内容	 */	private String content;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}
}

