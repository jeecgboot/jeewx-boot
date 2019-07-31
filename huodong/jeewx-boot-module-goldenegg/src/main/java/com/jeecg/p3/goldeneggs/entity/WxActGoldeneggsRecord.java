package com.jeecg.p3.goldeneggs.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;
import org.jeewx.api.core.util.DateUtils;

import com.jeecg.p3.goldeneggs.annotation.Excel;

/**
 * 描述：</b>WxActGoldeneggsRecord:砍价帮砍记录表<br>
 * @author junfeng.zhou
 * @since：2016年06月13日 10时55分39秒 星期一 
 * @version:1.0
 */
public class WxActGoldeneggsRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *记录id	 */
	private String id;	/**	 * 活动id	 */
	private String actId;
	/**
	 * 活动名称
	 */
	@Excel(exportName="活动名称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String title;	/**	 *openid	 */
	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String openid;	/**	 *昵称	 */
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String nickname;	/**	 *中奖时间	 */
	@Excel(exportName="中奖时间", exportConvertSign = 1, exportFieldWidth = 30, importConvertSign = 0)	private Date createTime;	/**	 *奖项	 */
	private String awardsId;	/**	 *收货人姓名	 */
	@Excel(exportName="收货人姓名", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String realname;	/**	 *手机号	 */
	@Excel(exportName="手机号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String phone;	/**	 *地址	 */
	@Excel(exportName="地址", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String address;	/**	 *抽奖序号	 */	private Integer seq;	/**	 *对应微信平台原始id	 */	private String jwid;	/**	 *奖品名字	 */
	@Excel(exportName="奖品名字", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String prizesName;
	/**
	 * 核销状态
	 */	private String recieveStatus;	/**
	 * 头像
	 */	private String headimgurl;	/**
	 * 奖品图片
	 */	private String img;	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**	 *中奖状态(0未中奖，1中奖)	 */	private String awardsName;	/**	 *	 */
	private String code;
	/**
	 *奖品编码
	 */
		private String prizesState;	/**
	 *核销员id
	 */
	private String verifyId;	/**	 *核销时间
	 */
	private Date recieveTime;
	/**
	 *奖项配置id
	 */
	private String relationId;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getAwardsId() {	    return this.awardsId;	}	public void setAwardsId(String awardsId) {	    this.awardsId=awardsId;	}	public String getRealname() {	    return this.realname;	}	public void setRealname(String realname) {	    this.realname=realname;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	    this.phone=phone;	}	public String getAddress() {	    return this.address;	}	public void setAddress(String address) {	    this.address=address;	}	public Integer getSeq() {	    return this.seq;	}	public void setSeq(Integer seq) {	    this.seq=seq;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getPrizesName() {	    return this.prizesName;	}	public void setPrizesName(String prizesName) {	    this.prizesName=prizesName;	}	public String getAwardsName() {	    return this.awardsName;	}	public void setAwardsName(String awardsName) {	    this.awardsName=awardsName;	}	public String getPrizesState() {	    return this.prizesState;	}	public void setPrizesState(String prizesState) {	    this.prizesState=prizesState;	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateTimeConvert() {
		return DateUtils.formatDate(this.createTime, "yyyy-MM-dd HH:mm:ss");

	}
	public String getRecieveStatus() {
		return recieveStatus;
	}
	public void setRecieveStatus(String recieveStatus) {
		this.recieveStatus = recieveStatus;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getVerifyId() {
		return verifyId;
	}
	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}
	public Date getRecieveTime() {
		return recieveTime;
	}
	public void setRecieveTime(Date recieveTime) {
		this.recieveTime = recieveTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
}

