package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>微信菜单表<br>
 * @author weijian.zhang
 * @since：2018年07月12日 13时58分38秒 星期四 
 * @version:1.0
 */
public class WeixinMenu implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *父id	 */	private String fatherId;	/**	 *菜单KEY	 */	private String menuKey;	/**	 *菜单类型	 */	private String menuType;	/**	 *菜单名称	 */	private String menuName;	/**	 *网页链接	 */	private String url;	/**	 *相应消息类型	 */	private String msgtype;	/**	 *菜单位置	 */	private String orders;	/**	 *关联素材id	 */	private String templateId;	/**	 *公众号原始id	 */	private String jwid;	/**	 *小程序appid	 */	private String miniprogramAppid;	/**	 *小程序页面路径	 */	private String miniprogramPagepath;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改人登录名称	 */	private String updateBy;	/**	 *修改时间	 */	private Date updateTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getFatherId() {	    return this.fatherId;	}	public void setFatherId(String fatherId) {	    this.fatherId=fatherId;	}	public String getMenuKey() {	    return this.menuKey;	}	public void setMenuKey(String menuKey) {	    this.menuKey=menuKey;	}	public String getMenuType() {	    return this.menuType;	}	public void setMenuType(String menuType) {	    this.menuType=menuType;	}	public String getMenuName() {	    return this.menuName;	}	public void setMenuName(String menuName) {	    this.menuName=menuName;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public String getMsgtype() {	    return this.msgtype;	}	public void setMsgtype(String msgtype) {	    this.msgtype=msgtype;	}	public String getOrders() {	    return this.orders;	}	public void setOrders(String orders) {	    this.orders=orders;	}	public String getTemplateId() {	    return this.templateId;	}	public void setTemplateId(String templateId) {	    this.templateId=templateId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getMiniprogramAppid() {	    return this.miniprogramAppid;	}	public void setMiniprogramAppid(String miniprogramAppid) {	    this.miniprogramAppid=miniprogramAppid;	}	public String getMiniprogramPagepath() {	    return this.miniprogramPagepath;	}	public void setMiniprogramPagepath(String miniprogramPagepath) {	    this.miniprogramPagepath=miniprogramPagepath;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
}

