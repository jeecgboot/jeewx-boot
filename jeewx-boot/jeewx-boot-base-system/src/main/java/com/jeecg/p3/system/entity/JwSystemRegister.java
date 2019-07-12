package com.jeecg.p3.system.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemRegister:<br>
 * @author alex
 * @since：2016年03月23日 18时07分59秒 星期三 
 * @version:1.0
 */
public class JwSystemRegister implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *注册邮箱	 */	private String email;	/**	 *密码	 */	private String password;	/**	 *验证串	 */	private String authstring;	/**	 *注册时间	 */	private Date registertime;	/**	 *重发时间	 */	private Date lastresendtime;	/**	 *确认标志	 */	private Integer checksign;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getPassword() {	    return this.password;	}	public void setPassword(String password) {	    this.password=password;	}	public String getAuthstring() {	    return this.authstring;	}	public void setAuthstring(String authstring) {	    this.authstring=authstring;	}	public Date getRegistertime() {	    return this.registertime;	}	public void setRegistertime(Date registertime) {	    this.registertime=registertime;	}	public Date getLastresendtime() {	    return this.lastresendtime;	}	public void setLastresendtime(Date lastresendtime) {	    this.lastresendtime=lastresendtime;	}	public Integer getChecksign() {	    return this.checksign;	}	public void setChecksign(Integer checksign) {	    this.checksign=checksign;	}
}

