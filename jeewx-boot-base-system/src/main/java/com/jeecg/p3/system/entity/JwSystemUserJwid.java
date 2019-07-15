package com.jeecg.p3.system.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemUserJwid:微信公众号字典表<br>
 * @author junfeng.zhou
 * @since：2015年12月23日 16时02分42秒 星期三 
 * @version:1.0
 */
public class JwSystemUserJwid implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *用户编码	 */	private String userId;	/**	 *公众号	 */	private String jwid;
	
	/**
	 *公众号名称
	 */
	private String name;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getUserId() {	    return this.userId;	}	public void setUserId(String userId) {	    this.userId=userId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 默认登录jwid的标识
	 */
	private String defaultFlag;
	
	public String getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	@Override
	public String toString() {
		return "JwSystemUserJwid [id=" + id + ", userId=" + userId + ", jwid="
				+ jwid + ", name=" + name + "]";
	}
	
}

