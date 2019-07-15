package com.jeecg.p3.system.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>平台活动表<br>
 * @author Alex
 * @since：2017年09月30日 10时05分08秒 星期六 
 * @version:1.0
 */
public class JwSystemAct implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *活动项目编码	 */	private String actId;	/**	 *活动名称	 */	private String actName;	/**	 *开始时间	 */	private Date beginTime;	/**	 *结束时间	 */	private Date endTime;	/**	 *活动状态	 */	private String status;	/**	 *微信原始ID	 */	private String jwid;	/**	 *活动链接	 */	private String hdurl;	/**	 *短链接	 */	private String shortUrl;	/**	 *组织者	 */	private String organizer;	/**	 *参与数	 */	private Integer joinNum;
	//update-begin--Author:zhangweijian  Date: 20180914 for：添加最大最小参与人数引用	/**	 *参与最小数	 */	private Integer joinMinNum;
	
	public Integer getJoinMinNum() {
		return joinMinNum;
	}
	public void setJoinMinNum(Integer joinMinNum) {
		this.joinMinNum = joinMinNum;
	}
	/**	 *参与最大数	 */	private Integer joinMaxNum;
		public Integer getJoinMaxNum() {
		return joinMaxNum;
	}
	public void setJoinMaxNum(Integer joinMaxNum) {
		this.joinMaxNum = joinMaxNum;
	}
	/**	 *分享数	 */
	//update-end--Author:zhangweijian  Date: 20180914 for：添加最大最小参与人数引用	private Integer shareNum;	/**	 *创建人	 */	private String createName;	/**	 *创建时间	 */	private Date createTime;	/**	 *创建时间From	 */	private Date createTimeFrom;	/**	 *创建时间To	 */	private Date createTimeTo;	/**	 *修改人	 */	private String updateName;	/**	 *修改时间	 */	private Date updateTime;	/**	 *删除标识：0正常，1删除,2永久删除	 */	private String deleteFlag;	/**	 *删除时间	 */	private Date deleteTime;	/**	 *活动类型	 */	private String projectCode;	/**	 *表名	 */	private String tableName;
	/**
	 *活动类型名称
	 */
	private String projectName;
	/**
	 *公众号名称
	 */
	private String jwName;
	//update-begin--Author:zhangweijian  Date: 20180910 for：添加是否为热门活动字段
	/**
	 *是否为热门活动：'0'：否，'1'是
	 */
	private String isHotAct;
	public String getIsHotAct() {
		return isHotAct;
	}
	public void setIsHotAct(String isHotAct) {
		this.isHotAct = isHotAct;
	}
	//update-end--Author:zhangweijian  Date: 20180910 for：添加是否为热门活动字段
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public Date getBeginTime() {	    return this.beginTime;	}	public void setBeginTime(Date beginTime) {	    this.beginTime=beginTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getShortUrl() {	    return this.shortUrl;	}	public void setShortUrl(String shortUrl) {	    this.shortUrl=shortUrl;	}	public String getOrganizer() {	    return this.organizer;	}	public void setOrganizer(String organizer) {	    this.organizer=organizer;	}	public Integer getJoinNum() {	    return this.joinNum;	}	public void setJoinNum(Integer joinNum) {	    this.joinNum=joinNum;	}	public Integer getShareNum() {	    return this.shareNum;	}	public void setShareNum(Integer shareNum) {	    this.shareNum=shareNum;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}	public String getDeleteFlag() {	    return this.deleteFlag;	}	public void setDeleteFlag(String deleteFlag) {	    this.deleteFlag=deleteFlag;	}	public Date getDeleteTime() {	    return this.deleteTime;	}	public void setDeleteTime(Date deleteTime) {	    this.deleteTime=deleteTime;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}	public String getTableName() {	    return this.tableName;	}	public void setTableName(String tableName) {	    this.tableName=tableName;	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setJwName(String jwName) {
		this.jwName = jwName;
	}
	public String getJwName() {
		return jwName;
	}
	public void setCreateTimeFrom(Date createTimeFrom) {
		this.createTimeFrom = createTimeFrom;
	}
	public Date getCreateTimeFrom() {
		return createTimeFrom;
	}
	public void setCreateTimeTo(Date createTimeTo) {
		this.createTimeTo = createTimeTo;
	}
	public Date getCreateTimeTo() {
		return createTimeTo;
	}
}

