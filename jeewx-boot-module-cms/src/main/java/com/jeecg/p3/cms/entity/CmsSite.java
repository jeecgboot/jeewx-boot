package com.jeecg.p3.cms.entity;

import java.util.Date;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>网站管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时53分26秒 星期二 
 * @version:1.0
 */
public class CmsSite implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *	 */	private String updateName;	/**	 *	 */	private Date updateDate;	/**	 *	 */	private Date createDate;	/**	 *	 */	private String createName;	/**	 *公司电话	 */	private String companyTel;	/**	 *站点Logo	 */	private String siteLogo;	/**	 *站点名称	 */	private String siteName;	/**	 *站点模板	 */	private String siteTemplateStyle;	/**	 *备案信息	 */	private String recordInformation;	/**	 *经度	 */	private String longitude;	/**	 *纬度	 */	private String latitude;
	/**
	 * 微信公众号
	 */
	private String jwid;
	/**
	 * 长链接
	 */
	private String hdurl;
	/**
	 * 短链接
	 */
	private String shortUrl;
	/**
	 * 项目编码
	 */
	private String projectCode;
	// update-begin--Author:gj_duzy Date:20181113 for：新增站点背景图片属性
	/**
	 * 站点背景图片
	 */
	private String siteBackgroundImg;	
    // update-end--Author:gj_duzy Date:20181113 for：新增站点背景图片属性
	
	//update-begin-author:taoyan date:20181015 for:分享配置
	private String shareFriendTitle;
	private String shareFriendDesc;
	private String shareFriendCircle;
	private String appid;
	//update-end-author:taoyan date:20181015 for:分享配置	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public String getCompanyTel() {	    return this.companyTel;	}	public void setCompanyTel(String companyTel) {	    this.companyTel=companyTel;	}	public String getSiteLogo() {	    return this.siteLogo;	}	public void setSiteLogo(String siteLogo) {	    this.siteLogo=siteLogo;	}	public String getSiteName() {	    return this.siteName;	}	public void setSiteName(String siteName) {	    this.siteName=siteName;	}	public String getSiteTemplateStyle() {	    return this.siteTemplateStyle;	}	public void setSiteTemplateStyle(String siteTemplateStyle) {	    this.siteTemplateStyle=siteTemplateStyle;	}	public String getRecordInformation() {	    return this.recordInformation;	}	public void setRecordInformation(String recordInformation) {	    this.recordInformation=recordInformation;	}	public String getLongitude() {	    return this.longitude;	}	public void setLongitude(String longitude) {	    this.longitude=longitude;	}	public String getLatitude() {	    return this.latitude;	}	public void setLatitude(String latitude) {	    this.latitude=latitude;	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getShareFriendTitle() {
		return shareFriendTitle;
	}
	public void setShareFriendTitle(String shareFriendTitle) {
		this.shareFriendTitle = shareFriendTitle;
	}
	public String getShareFriendDesc() {
		return shareFriendDesc;
	}
	public void setShareFriendDesc(String shareFriendDesc) {
		this.shareFriendDesc = shareFriendDesc;
	}
	public String getShareFriendCircle() {
		return shareFriendCircle;
	}
	public void setShareFriendCircle(String shareFriendCircle) {
		this.shareFriendCircle = shareFriendCircle;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSiteBackgroundImg() {
		return siteBackgroundImg;
	}
	public void setSiteBackgroundImg(String siteBackgroundImg) {
		this.siteBackgroundImg = siteBackgroundImg;
	}
	
	
}

