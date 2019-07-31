package com.jeecg.p3.goldeneggs.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActGoldeneggsPrizes:奖品表<br>
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分27秒 星期二 
 * @version:1.0
 */
public class WxActGoldeneggsPrizes implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *奖品名称	 */	private String name;	/**	 *奖品图片	 */	private String img;	/**	 *微信原始id	 */	private String jwid;	/**	 *创建人	 */	private String createBy;
	/**
	 * 奖品等级
	 */
	private String awardsName;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getImg() {	    return this.img;	}	public void setImg(String img) {	    this.img=img;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

