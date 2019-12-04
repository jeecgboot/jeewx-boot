package com.jeecg.p3.qrcode.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>二维码表<br>
 * @author sunkai
 * @since：2018年08月30日 10时29分25秒 星期四 
 * @version:1.0
 */
public class WeixinQrcode implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *二维码标题（详情）	 */	private String actionInfo;	/**	 *二维码类型：QR_SCENE：临时整型，QR_STR_SCENE：临时字符串，QR_LIMIT_SCENE：永久整型，
QR_LIMIT_STR_SCENE：永久字符串	 */	private String actionName;	/**	 *整型场景值ID	 */	private Integer sceneId;	/**	 *字符串场景值ID	 */	private String sceneStr;	/**	 *二维码ticket	 */	private String ticket;	/**	 *有效时间(秒)	 */	private Integer expireSeconds;	/**	 *过期时间	 */	private Date expireDate;	/**	 *二维码地址	 */	private String qrcodeUrl;	/**	 *公众帐号ID	 */	private String jwid;	/**	 *触发类型：text文本/news图文	 */	private String msgType;	/**	 *文本内容	 */	private String textContent;	/**	 *图文选择类型（1：自定义，2：选择模板）	 */	private String actionNewsType;	/**	 *图文标题	 */	private String newsTitle;	/**	 *图文摘要	 */	private String newsDesc;	/**	 *图文封面图	 */	private String newsImg;	/**	 *图文跳转链接	 */	private String newsUrl;	/**	 *图文选择模板ID	 */	private String newsTemplateid;	/**	 *标签，逗号隔开	 */	private String tags;	/**	 *创建人登录名称	 */	private String createBy;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改人登录名称	 */	private String updateBy;	/**	 *修改时间	 */	private Date updateTime;
	
	private Integer scanCount;
		public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActionInfo() {	    return this.actionInfo;	}	public void setActionInfo(String actionInfo) {	    this.actionInfo=actionInfo;	}	public String getActionName() {	    return this.actionName;	}	public void setActionName(String actionName) {	    this.actionName=actionName;	}	public Integer getSceneId() {	    return this.sceneId;	}	public void setSceneId(Integer sceneId) {	    this.sceneId=sceneId;	}	public String getSceneStr() {	    return this.sceneStr;	}	public void setSceneStr(String sceneStr) {	    this.sceneStr=sceneStr;	}	public String getTicket() {	    return this.ticket;	}	public void setTicket(String ticket) {	    this.ticket=ticket;	}	public Integer getExpireSeconds() {	    return this.expireSeconds;	}	public void setExpireSeconds(Integer expireSeconds) {	    this.expireSeconds=expireSeconds;	}	public Date getExpireDate() {	    return this.expireDate;	}	public void setExpireDate(Date expireDate) {	    this.expireDate=expireDate;	}	public String getQrcodeUrl() {	    return this.qrcodeUrl;	}	public void setQrcodeUrl(String qrcodeUrl) {	    this.qrcodeUrl=qrcodeUrl;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getMsgType() {	    return this.msgType;	}	public void setMsgType(String msgType) {	    this.msgType=msgType;	}	public String getTextContent() {	    return this.textContent;	}	public void setTextContent(String textContent) {	    this.textContent=textContent;	}	public String getActionNewsType() {	    return this.actionNewsType;	}	public void setActionNewsType(String actionNewsType) {	    this.actionNewsType=actionNewsType;	}	public String getNewsTitle() {	    return this.newsTitle;	}	public void setNewsTitle(String newsTitle) {	    this.newsTitle=newsTitle;	}	public String getNewsDesc() {	    return this.newsDesc;	}	public void setNewsDesc(String newsDesc) {	    this.newsDesc=newsDesc;	}	public String getNewsImg() {	    return this.newsImg;	}	public void setNewsImg(String newsImg) {	    this.newsImg=newsImg;	}	public String getNewsUrl() {	    return this.newsUrl;	}	public void setNewsUrl(String newsUrl) {	    this.newsUrl=newsUrl;	}	public String getNewsTemplateid() {	    return this.newsTemplateid;	}	public void setNewsTemplateid(String newsTemplateid) {	    this.newsTemplateid=newsTemplateid;	}	public String getTags() {	    return this.tags;	}	public void setTags(String tags) {	    this.tags=tags;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
	public Integer getScanCount() {
		return scanCount;
	}
	public void setScanCount(Integer scanCount) {
		this.scanCount = scanCount;
	}
}

