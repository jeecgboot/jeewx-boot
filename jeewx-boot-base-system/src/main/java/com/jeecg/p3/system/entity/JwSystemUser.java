package com.jeecg.p3.system.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemUser:运营用户表; InnoDB free: 9216 kB<br>
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分29秒 星期一 
 * @version:1.0
 */
public class JwSystemUser implements Entity<Long> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private Long id;	/**	 *用户编码	 */	private String userId;	/**	 *用户名称	 */	private String userName;
	/**
	 *密码
	 */
	private String password;	/**	 *用户ERP号	 */	private String userErpNo;	/**	 *用户种类	 */	private String userTyp;	/**	 *部门编码	 */	private String opeDepId;	/**	 *手机电话	 */	private String opePhoneNum;	/**	 *注册邮箱	 */	private String email;	/**	 *邮箱认证信息	 */	private String opeEmailAuthinfo;	/**	 *用户头像	 */	private String userIcon;	/**	 *手机是否认证	 */	private String opeMobileAuthInd;	/**	 *邮箱是否认证	 */	private String opeEmailAuthInd;	/**	 *证件号码	 */	private String idNum;	/**	 *证件种类	 */	private String idTyp;	/**	 *状态	 */	private String state;	/**	 *用户状态 NORMAL:正常；INVALID：无效	 */	private String userStat;	/**	 *上次登录日期	 */	private Date lastLognDttm;	/**	 *上次登录IP	 */	private String lastLognIp;	/**	 *是否保持密码	 */	private String opePasswdInd;	/**	 *删除标志	 */	private String delStat;	/**	 *建立者	 */	private String creator;	/**	 *编辑者	 */	private String editor;	/**	 *建立日期	 */	private Date createDt;	/**	 *编辑日期	 */	private Date editDt;	/**	 *上次修改日期	 */	private Date lastEditDt;	/**	 *版本号	 */	private String recordVersion;
	/**
	 * openid
	 */
	private String openid;
	//update--begin--author: gj_shaojc--date:20180503--------for:增加代理商管理-
	/**
	 * 代理商id
	 */
	private String agentId;
	private String relName;
	public String getRelName() {
		return relName;
	}
	public void setRelName(String relName) {
		this.relName = relName;
	}
	public String getRelPhone() {
		return relPhone;
	}
	public void setRelPhone(String relPhone) {
		this.relPhone = relPhone;
	}
	private String relPhone;
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	//update--end--author: gj_shaojc--date:20180503--------for:增加代理商管理-
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Long getId() {	    return this.id;	}	public void setId(Long id) {	    this.id=id;	}	public String getUserId() {	    return this.userId;	}	public void setUserId(String userId) {	    this.userId=userId;	}	public String getUserName() {	    return this.userName;	}	public void setUserName(String userName) {	    this.userName=userName;	}	public String getUserErpNo() {	    return this.userErpNo;	}	public void setUserErpNo(String userErpNo) {	    this.userErpNo=userErpNo;	}	public String getUserTyp() {	    return this.userTyp;	}	public void setUserTyp(String userTyp) {	    this.userTyp=userTyp;	}	public String getOpeDepId() {	    return this.opeDepId;	}	public void setOpeDepId(String opeDepId) {	    this.opeDepId=opeDepId;	}	public String getOpePhoneNum() {	    return this.opePhoneNum;	}	public void setOpePhoneNum(String opePhoneNum) {	    this.opePhoneNum=opePhoneNum;	}	public String getEmail() {	    return this.email;	}	public void setEmail(String email) {	    this.email=email;	}	public String getOpeEmailAuthinfo() {	    return this.opeEmailAuthinfo;	}	public void setOpeEmailAuthinfo(String opeEmailAuthinfo) {	    this.opeEmailAuthinfo=opeEmailAuthinfo;	}	public String getUserIcon() {	    return this.userIcon;	}	public void setUserIcon(String userIcon) {	    this.userIcon=userIcon;	}	public String getOpeMobileAuthInd() {	    return this.opeMobileAuthInd;	}	public void setOpeMobileAuthInd(String opeMobileAuthInd) {	    this.opeMobileAuthInd=opeMobileAuthInd;	}	public String getOpeEmailAuthInd() {	    return this.opeEmailAuthInd;	}	public void setOpeEmailAuthInd(String opeEmailAuthInd) {	    this.opeEmailAuthInd=opeEmailAuthInd;	}	public String getIdNum() {	    return this.idNum;	}	public void setIdNum(String idNum) {	    this.idNum=idNum;	}	public String getIdTyp() {	    return this.idTyp;	}	public void setIdTyp(String idTyp) {	    this.idTyp=idTyp;	}	public String getState() {	    return this.state;	}	public void setState(String state) {	    this.state=state;	}	public String getUserStat() {	    return this.userStat;	}	public void setUserStat(String userStat) {	    this.userStat=userStat;	}	public Date getLastLognDttm() {	    return this.lastLognDttm;	}	public void setLastLognDttm(Date lastLognDttm) {	    this.lastLognDttm=lastLognDttm;	}	public String getLastLognIp() {	    return this.lastLognIp;	}	public void setLastLognIp(String lastLognIp) {	    this.lastLognIp=lastLognIp;	}	public String getOpePasswdInd() {	    return this.opePasswdInd;	}	public void setOpePasswdInd(String opePasswdInd) {	    this.opePasswdInd=opePasswdInd;	}	public String getDelStat() {	    return this.delStat;	}	public void setDelStat(String delStat) {	    this.delStat=delStat;	}	public String getCreator() {	    return this.creator;	}	public void setCreator(String creator) {	    this.creator=creator;	}	public String getEditor() {	    return this.editor;	}	public void setEditor(String editor) {	    this.editor=editor;	}	public Date getCreateDt() {	    return this.createDt;	}	public void setCreateDt(Date createDt) {	    this.createDt=createDt;	}	public Date getEditDt() {	    return this.editDt;	}	public void setEditDt(Date editDt) {	    this.editDt=editDt;	}	public Date getLastEditDt() {	    return this.lastEditDt;	}	public void setLastEditDt(Date lastEditDt) {	    this.lastEditDt=lastEditDt;	}	public String getRecordVersion() {	    return this.recordVersion;	}	public void setRecordVersion(String recordVersion) {	    this.recordVersion=recordVersion;	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JwSystemUser [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", userErpNo=");
		builder.append(userErpNo);
		builder.append(", userTyp=");
		builder.append(userTyp);
		builder.append(", opeDepId=");
		builder.append(opeDepId);
		builder.append(", opePhoneNum=");
		builder.append(opePhoneNum);
		builder.append(", email=");
		builder.append(email);
		builder.append(", opeEmailAuthinfo=");
		builder.append(opeEmailAuthinfo);
		builder.append(", userIcon=");
		builder.append(userIcon);
		builder.append(", opeMobileAuthInd=");
		builder.append(opeMobileAuthInd);
		builder.append(", opeEmailAuthInd=");
		builder.append(opeEmailAuthInd);
		builder.append(", idNum=");
		builder.append(idNum);
		builder.append(", idTyp=");
		builder.append(idTyp);
		builder.append(", state=");
		builder.append(state);
		builder.append(", userStat=");
		builder.append(userStat);
		builder.append(", lastLognDttm=");
		builder.append(lastLognDttm);
		builder.append(", lastLognIp=");
		builder.append(lastLognIp);
		builder.append(", opePasswdInd=");
		builder.append(opePasswdInd);
		builder.append(", delStat=");
		builder.append(delStat);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", editor=");
		builder.append(editor);
		builder.append(", createDt=");
		builder.append(createDt);
		builder.append(", editDt=");
		builder.append(editDt);
		builder.append(", lastEditDt=");
		builder.append(lastEditDt);
		builder.append(", recordVersion=");
		builder.append(recordVersion);
		builder.append(", openid=");
		builder.append(openid);
		builder.append("]");
		return builder.toString();
	}
	
}

