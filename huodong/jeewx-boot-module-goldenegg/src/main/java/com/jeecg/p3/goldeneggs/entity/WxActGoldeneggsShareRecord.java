package com.jeecg.p3.goldeneggs.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>分享记录表<br>
 * @author junfeng.zhou
 * @since：2018年10月10日 10时27分14秒 星期三 
 * @version:1.0
 */
public class WxActGoldeneggsShareRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *活动id	 */	private String actId;	/**	 *openid	 */	private String openid;	/**	 *分享时间	 */	private String relDate;	/**	 *分享类型：0朋友，1朋友圈	 */	private String type;	/**	 *创建时间	 */	private Date createTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getRelDate() {
		return relDate;
	}
	public void setRelDate(String relDate) {
		this.relDate = relDate;
	}
	public String getType() {	    return this.type;	}	public void setType(String type) {	    this.type=type;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}
}

