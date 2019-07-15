package com.jeecg.p3.system.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemProjectErrorConfig:活动错误页面配置管理表<br>
 * @author junfeng.zhou
 * @since：2016年02月24日 10时21分06秒 星期三 
 * @version:1.0
 */
public class JwSystemProjectErrorConfig implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *项目请求地址	 */	private String projectUrl;	/**	 *项目名称	 */	private String name;	/**	 *跳转地址	 */	private String redirectUrl;	/**	 *创建人	 */	private String creatName;	/**	 *创建时间	 */	private Date creatTime;	/**	 *修改人	 */	private String updateName;	/**	 *修改时间	 */	private Date updateTime;	/**	 *微信公众号	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getProjectUrl() {	    return this.projectUrl;	}	public void setProjectUrl(String projectUrl) {	    this.projectUrl=projectUrl;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getRedirectUrl() {	    return this.redirectUrl;	}	public void setRedirectUrl(String redirectUrl) {	    this.redirectUrl=redirectUrl;	}	public String getCreatName() {	    return this.creatName;	}	public void setCreatName(String creatName) {	    this.creatName=creatName;	}	public Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(Date creatTime) {	    this.creatTime=creatTime;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
}

