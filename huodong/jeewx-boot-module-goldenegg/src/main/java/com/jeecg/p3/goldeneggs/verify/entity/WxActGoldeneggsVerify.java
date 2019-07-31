package com.jeecg.p3.goldeneggs.verify.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>砸金蛋审核员表<br>
 * @author junfeng.zhou
 * @since：2018年10月17日 09时53分08秒 星期三 
 * @version:1.0
 */
public class WxActGoldeneggsVerify implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *id	 */	private String id;	/**	 *活动id	 */	private String actId;	/**	 *核销员id	 */	private String openid;	/**	 *状态（0启用/1未启用）	 */	private String status;	/**	 *微信头像	 */	private String headimg;	/**	 *微信昵称	 */	private String nickname;
	
	private String name;
	private String jwid;
	private String img;
	private String title;
	private String code;
	private String realname;
	private String phone;
	private String recieveStatus;
	private Date endtime;
	private Date recieveTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getHeadimg() {	    return this.headimg;	}	public void setHeadimg(String headimg) {	    this.headimg=headimg;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRecieveStatus() {
		return recieveStatus;
	}
	public void setRecieveStatus(String recieveStatus) {
		this.recieveStatus = recieveStatus;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getRecieveTime() {
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}
	
}

