package com.jeecg.p3.goldeneggs.entity;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.Entity;



/**
 * 描述：</b>WxActGoldeneggs:h5页面彩蛋<br>
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分24秒 星期二 
 * @version:1.0
 */
public class WxActGoldeneggs implements Entity<String> {
	private static final long serialVersionUID = 1L;
	private List<WxActGoldeneggsRelation> awarsList;	/**	 *键主	 */	private String id;	public List<WxActGoldeneggsRelation> getAwarsList() {
		return awarsList;
	}
	public void setAwarsList(List<WxActGoldeneggsRelation> awarsList) {
		this.awarsList = awarsList;
	}
	/**	 *活动名称	 */	private String title;	/**	 *活动描述	 */	private String description;	/**	 *开始时间	 */	private Date starttime;	/**	 *结束时间	 */	private Date endtime;
	/**
	 * 模板编码
	 */
	private String templateCode;	/**	 *背景图	 */	private String banner;	/**	 *抽奖次数	 */	private Integer count;	/**	 *入口地址	 */	private String hdurl;
	/**
	 * 短链接
	 */
	private String shortUrl;	/**	 *是否关注可参加	 */	private String foucsUserCanJoin;	/**	 *是否绑定手机可参加	 */	private String bindingMobileCanJoin;	/**	 *每日抽奖次数	 */	private Integer numPerDay;	/**	 *是否中奖可参与 0：中奖可继续参与 1:中奖不可参与	 */	private String prizeStatus;	/**	 *微信原始id	 */	private String jwid;	/**	 *活动编码	 */	private String projectCode;	/**	 *创建人	 */	private String createBy;
	//update-begin--Author:lsq  Date: 20181010 for：分享得额外次数
	/**
	 *是否有额外的抽奖机会
	 */
	private String extraLuckyDraw;
	
	public String getExtraLuckyDraw() {
		return extraLuckyDraw;
	}
	public void setExtraLuckyDraw(String extraLuckyDraw) {
		this.extraLuckyDraw = extraLuckyDraw;
	}
	//update-end--Author:lsq  Date: 20181010 for：分享得额外次数	
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public Date getStarttime() {	    return this.starttime;	}	public void setStarttime(Date starttime) {	    this.starttime=starttime;	}	public Date getEndtime() {	    return this.endtime;	}	public void setEndtime(Date endtime) {	    this.endtime=endtime;	}	public String getBanner() {	    return this.banner;	}	public void setBanner(String banner) {	    this.banner=banner;	}	public Integer getCount() {	    return this.count;	}	public void setCount(Integer count) {	    this.count=count;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public String getBindingMobileCanJoin() {	    return this.bindingMobileCanJoin;	}	public void setBindingMobileCanJoin(String bindingMobileCanJoin) {	    this.bindingMobileCanJoin=bindingMobileCanJoin;	}	public Integer getNumPerDay() {	    return this.numPerDay;	}	public void setNumPerDay(Integer numPerDay) {	    this.numPerDay=numPerDay;	}	public String getPrizeStatus() {	    return this.prizeStatus;	}	public void setPrizeStatus(String prizeStatus) {	    this.prizeStatus=prizeStatus;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}

