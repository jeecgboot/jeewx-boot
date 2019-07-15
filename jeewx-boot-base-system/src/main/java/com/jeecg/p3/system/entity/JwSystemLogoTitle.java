package com.jeecg.p3.system.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>系统logo、title、head和footer设置表<br>
 * @author liwenhui
 * @since：2017年08月30日 18时15分25秒 星期三 
 * @version:1.0
 */
public class JwSystemLogoTitle implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *系统的logo	 */	private String logo;	/**	 *系统名称	 */	private String title1;	/**	 *系统名称	 */	private String title2;	/**	 *系统名称	 */	private String title3;	/**	 *登录页的head	 */	private String loginPageHead;	/**	 *登录页的footer	 */	private String loginPageFooter;
	/**
	 * 活动底部logo
	 * */
	private String huodongBottomCopyright;
	//update-begin--Author:zhangweijian  Date: 20181105 for：新增收费说明字段
	/**
	 * 收费说明
	 * */
	private String chargingDesc;
	public String getChargingDesc() {
		return chargingDesc;
	}
	public void setChargingDesc(String chargingDesc) {
		this.chargingDesc = chargingDesc;
	}
	//update-end--Author:zhangweijian  Date: 20181105 for：新增收费说明字段
	public String getHuodongBottomCopyright() {
		return huodongBottomCopyright;
	}
	public void setHuodongBottomCopyright(String huodongBottomCopyright) {
		this.huodongBottomCopyright = huodongBottomCopyright;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getLogo() {	    return this.logo;	}	public void setLogo(String logo) {	    this.logo=logo;	}	public String getTitle1() {	    return this.title1;	}	public void setTitle1(String title1) {	    this.title1=title1;	}	public String getTitle2() {	    return this.title2;	}	public void setTitle2(String title2) {	    this.title2=title2;	}	public String getTitle3() {	    return this.title3;	}	public void setTitle3(String title3) {	    this.title3=title3;	}	public String getLoginPageHead() {	    return this.loginPageHead;	}	public void setLoginPageHead(String loginPageHead) {	    this.loginPageHead=loginPageHead;	}	public String getLoginPageFooter() {	    return this.loginPageFooter;	}	public void setLoginPageFooter(String loginPageFooter) {	    this.loginPageFooter=loginPageFooter;	}
}

