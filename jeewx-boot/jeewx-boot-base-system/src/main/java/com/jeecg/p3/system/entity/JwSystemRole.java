package com.jeecg.p3.system.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemRole:运营角色表; InnoDB free: 9216 kB<br>
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public class JwSystemRole implements Entity<Long> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private Long id;	/**	 *角色编码	 */	private String roleId;	/**	 *角色名称	 */	private String roleName;	/**	 *创建人	 */	private String crtOperator;	/**	 *创建日期	 */	private Date crtDt;	/**	 *角色种类	 */	private String roleTyp;	/**	 *删除标志	 */	private String delStat;	/**	 *建立者	 */	private String creator;	/**	 *编辑者	 */	private String editor;	/**	 *建立日期	 */	private Date createDt;	/**	 *编辑日期	 */	private Date editDt;	/**	 *上次修改日期	 */	private Date lastEditDt;	/**	 *版本号	 */	private String recordVersion;
	
	private Boolean isChecked;	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getRoleId() {	    return this.roleId;	}	public void setRoleId(String roleId) {	    this.roleId=roleId;	}	public String getRoleName() {	    return this.roleName;	}	public void setRoleName(String roleName) {	    this.roleName=roleName;	}	public String getCrtOperator() {	    return this.crtOperator;	}	public void setCrtOperator(String crtOperator) {	    this.crtOperator=crtOperator;	}	public Date getCrtDt() {	    return this.crtDt;	}	public void setCrtDt(Date crtDt) {	    this.crtDt=crtDt;	}	public String getRoleTyp() {	    return this.roleTyp;	}	public void setRoleTyp(String roleTyp) {	    this.roleTyp=roleTyp;	}	public String getDelStat() {	    return this.delStat;	}	public void setDelStat(String delStat) {	    this.delStat=delStat;	}	public String getCreator() {	    return this.creator;	}	public void setCreator(String creator) {	    this.creator=creator;	}	public String getEditor() {	    return this.editor;	}	public void setEditor(String editor) {	    this.editor=editor;	}	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	public Date getEditDt() {	    return this.editDt;	}	public void setEditDt(Date editDt) {	    this.editDt=editDt;	}	public Date getLastEditDt() {	    return this.lastEditDt;	}	public void setLastEditDt(Date lastEditDt) {	    this.lastEditDt=lastEditDt;	}	public String getRecordVersion() {	    return this.recordVersion;	}	public void setRecordVersion(String recordVersion) {	    this.recordVersion=recordVersion;	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	@Override
	public String toString() {
		return "JwSystemRole [id=" + id + ", roleId=" + roleId + ", roleName="
				+ roleName + ", crtOperator=" + crtOperator + ", crtDt="
				+ crtDt + ", roleTyp=" + roleTyp + ", delStat=" + delStat
				+ ", creator=" + creator + ", editor=" + editor + ", createDt="
				+ createDt + ", editDt=" + editDt + ", lastEditDt="
				+ lastEditDt + ", recordVersion=" + recordVersion + "]";
	}
	
}

