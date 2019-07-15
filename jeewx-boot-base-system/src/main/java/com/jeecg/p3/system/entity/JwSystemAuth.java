package com.jeecg.p3.system.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemAuth:运营系统权限表; InnoDB free: 9216 kB<br>
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分27秒 星期一 
 * @version:1.0
 */
public class JwSystemAuth implements Entity<Long> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private Long id;	/**	 *权限编码	 */	private String authId;	/**	 *权限名称	 */	private String authName;	/**	 *是否记录日志 0不记录 1记录	 */	private String isLogs;	/**	 *权限类型 0:菜单;1:功能	 */	private String authType;	/**	 *权限说明	 */	private String authDesc;	/**	 *权限控制	 */	private String authContr;	/**	 *上一级权限编码	 */	private String parentAuthId;	/**	 *排序	 */	private Integer sortNo;	/**	 *层级	 */	private String bizLevel;	/**	 *是否叶子节点	 */	private String leafInd;	/**	 *删除标志 0未删除 1已删除	 */	private String delStat;
	/**
	 *菜单图标
	 */
	private String iconType;	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getAuthId() {	    return this.authId;	}	public void setAuthId(String authId) {	    this.authId=authId;	}	public String getAuthName() {	    return this.authName;	}	public void setAuthName(String authName) {	    this.authName=authName;	}	public String getIsLogs() {	    return this.isLogs;	}	public void setIsLogs(String isLogs) {	    this.isLogs=isLogs;	}	public String getAuthType() {	    return this.authType;	}	public void setAuthType(String authType) {	    this.authType=authType;	}	public String getAuthDesc() {	    return this.authDesc;	}	public void setAuthDesc(String authDesc) {	    this.authDesc=authDesc;	}	public String getAuthContr() {	    return this.authContr;	}	public void setAuthContr(String authContr) {	    this.authContr=authContr;	}	public String getParentAuthId() {	    return this.parentAuthId;	}	public void setParentAuthId(String parentAuthId) {	    this.parentAuthId=parentAuthId;	}	public Integer getSortNo() {	    return this.sortNo;	}	public void setSortNo(Integer sortNo) {	    this.sortNo=sortNo;	}	public String getBizLevel() {	    return this.bizLevel;	}	public void setBizLevel(String bizLevel) {	    this.bizLevel=bizLevel;	}	public String getLeafInd() {	    return this.leafInd;	}	public void setLeafInd(String leafInd) {	    this.leafInd=leafInd;	}	public String getDelStat() {	    return this.delStat;	}	public void setDelStat(String delStat) {	    this.delStat=delStat;	}
	@Override
	public String toString() {
		return "JwSystemAuth [id=" + id + ", authId=" + authId + ", authName="
				+ authName + ", isLogs=" + isLogs + ", authType=" + authType
				+ ", authDesc=" + authDesc + ", authContr=" + authContr
				+ ", parentAuthId=" + parentAuthId + ", sortNo=" + sortNo
				+ ", bizLevel=" + bizLevel + ", leafInd=" + leafInd
				+ ", delStat=" + delStat + "]";
	}
	public void setIconType(String iconType) {
		this.iconType = iconType;
	}
	public String getIconType() {
		return iconType;
	}
	
}

