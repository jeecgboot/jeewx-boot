package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>粉丝表<br>
 * @author weijian.zhang
 * @since：2018年07月26日 15时38分40秒 星期四 
 * @version:1.0
 */
public class WeixinGzuser implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *openid	 */	private String openid;	/**	 *昵称	 */	private String nickname;	/**	 *过滤后昵称	 */	private String nicknameTxt;	/**	 *备注名称	 */	private String bzname;	/**	 *用户头像	 */	private String headimgurl;	/**	 *性别	 */	private String sex;	/**	 *是否关注:'0':未关注；'1':关注	 */	private String subscribe;	/**	 *关注时间	 */	private String subscribeTime;	/**	 *用户关注渠道来源	 */	private String subscribeScene;	/**	 *手机号	 */	private String mobile;	/**	 *绑定状态：'0':未绑定；'1':已绑定	 */	private String bindStatus;	/**	 *绑定时间	 */	private Date bindTime;	/**	 *标签id	 */	private String tagidList;	/**	 *省份	 */	private String province;	/**	 *城市	 */	private String city;	/**	 *地区	 */	private String country;	/**	 *二维码扫码场景	 */	private String qrScene;	/**	 *二维码扫码常见描述	 */	private String qrSceneStr;	/**	 *用户所在分组id	 */	private String groupid;	/**	 *用户的语言，简体中文为zh_CN	 */	private String language;
	//update-begin--Author:zhangweijian  Date: 20180801 for：新增unionid	/**	 *unionid	 */	private String unionid;
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	/**	 *公众号原始id	 */	private String jwid;
	//update-end--Author:zhangweijian  Date: 20180801 for：新增unionid
	/**	 *创建时间	 */	private Date createTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getNicknameTxt() {	    return this.nicknameTxt;	}	public void setNicknameTxt(String nicknameTxt) {	    this.nicknameTxt=nicknameTxt;	}	public String getBzname() {	    return this.bzname;	}	public void setBzname(String bzname) {	    this.bzname=bzname;	}	public String getHeadimgurl() {	    return this.headimgurl;	}	public void setHeadimgurl(String headimgurl) {	    this.headimgurl=headimgurl;	}	public String getSex() {	    return this.sex;	}	public void setSex(String sex) {	    this.sex=sex;	}	public String getSubscribe() {	    return this.subscribe;	}	public void setSubscribe(String subscribe) {	    this.subscribe=subscribe;	}	public String getSubscribeTime() {	    return this.subscribeTime;	}	public void setSubscribeTime(String subscribeTime) {	    this.subscribeTime=subscribeTime;	}	public String getSubscribeScene() {	    return this.subscribeScene;	}	public void setSubscribeScene(String subscribeScene) {	    this.subscribeScene=subscribeScene;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getBindStatus() {	    return this.bindStatus;	}	public void setBindStatus(String bindStatus) {	    this.bindStatus=bindStatus;	}	public Date getBindTime() {	    return this.bindTime;	}	public void setBindTime(Date bindTime) {	    this.bindTime=bindTime;	}	public String getTagidList() {	    return this.tagidList;	}	public void setTagidList(String tagidList) {	    this.tagidList=tagidList;	}	public String getProvince() {	    return this.province;	}	public void setProvince(String province) {	    this.province=province;	}	public String getCity() {	    return this.city;	}	public void setCity(String city) {	    this.city=city;	}	public String getCountry() {	    return this.country;	}	public void setCountry(String country) {	    this.country=country;	}	public String getQrScene() {	    return this.qrScene;	}	public void setQrScene(String qrScene) {	    this.qrScene=qrScene;	}	public String getQrSceneStr() {	    return this.qrSceneStr;	}	public void setQrSceneStr(String qrSceneStr) {	    this.qrSceneStr=qrSceneStr;	}	public String getGroupid() {	    return this.groupid;	}	public void setGroupid(String groupid) {	    this.groupid=groupid;	}	public String getLanguage() {	    return this.language;	}	public void setLanguage(String language) {	    this.language=language;	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}
}

