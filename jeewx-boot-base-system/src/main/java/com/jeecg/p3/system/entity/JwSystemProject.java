package com.jeecg.p3.system.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemProject:活动项目管理表<br>
 * @author huangqingquan
 * @since：2016年08月08日 18时09分26秒 星期一 
 * @version:1.0
 */
public class JwSystemProject implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *项目编码	 */	private String code;	/**	 *项目名称	 */	private String name;	/**	 *项目图片	 */	private String img;
	//update-begin--Author:zhangweijian  Date: 20181205 for：新增logo图字段	/**	 *logo图片	 */	private String logoImg;
		public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	/**	 *活动描述	 */
	//update-end--Author:zhangweijian  Date: 20181205 for：新增logo图字段	private String discribe;	/**	 *编辑地址 （作废了）	 */	private String bjurl;	/**	 *入口地址（素材链接地址 oauth2.0 地址 &本地素材，非本地素材都需要 ）	 */	private String hdurl;	/**	 *gif图片地址	 */	private String gifUrl;
	/**
	 * 活动真实请求地址（本地	授权情况才会用到）
	 */
	private String hdzsUrl;
	/**
	 * 1授权素材/2本地素材
	 */
	private Integer scType;	/**	 *详情	 */	private String detail;	/**	 * 示例活动地址	 */	private String entrance;	/**	 *类型1，推荐2，不推荐	 */	private String type;	/**	 *排序	 */	private String sort;	/**	 *项目分类ID	 */	private String projectClassifyId;
	/**
	 * 项目分类名称
	 */
	private String projectClassifyName;	/**	 *应用类型	 */	private String applicationType;	/**	 *创建人	 */	private String creatName;	/**	 *创建时间	 */	private Date creatTime;	/**	 *修改人	 */	private String updateName;	/**	 *修改时间	 */	private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDiscribe() {
		return discribe;
	}
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
	public String getBjurl() {
		return bjurl;
	}
	public void setBjurl(String bjurl) {
		this.bjurl = bjurl;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
	public String getGifUrl() {
		return gifUrl;
	}
	public void setGifUrl(String gifUrl) {
		this.gifUrl = gifUrl;
	}
	public String getHdzsUrl() {
		return hdzsUrl;
	}
	public void setHdzsUrl(String hdzsUrl) {
		this.hdzsUrl = hdzsUrl;
	}
	public Integer getScType() {
		return scType;
	}
	public void setScType(Integer scType) {
		this.scType = scType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getProjectClassifyId() {
		return projectClassifyId;
	}
	public void setProjectClassifyId(String projectClassifyId) {
		this.projectClassifyId = projectClassifyId;
	}
	public String getProjectClassifyName() {
		return projectClassifyName;
	}
	public void setProjectClassifyName(String projectClassifyName) {
		this.projectClassifyName = projectClassifyName;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getCreatName() {
		return creatName;
	}
	public void setCreatName(String creatName) {
		this.creatName = creatName;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JwSystemProject [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", img=");
		builder.append(img);
		builder.append(", discribe=");
		builder.append(discribe);
		builder.append(", bjurl=");
		builder.append(bjurl);
		builder.append(", hdurl=");
		builder.append(hdurl);
		builder.append(", gifUrl=");
		builder.append(gifUrl);
		builder.append(", hdzsUrl=");
		builder.append(hdzsUrl);
		builder.append(", scType=");
		builder.append(scType);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", entrance=");
		builder.append(entrance);
		builder.append(", type=");
		builder.append(type);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", projectClassifyId=");
		builder.append(projectClassifyId);
		builder.append(", projectClassifyName=");
		builder.append(projectClassifyName);
		builder.append(", applicationType=");
		builder.append(applicationType);
		builder.append(", creatName=");
		builder.append(creatName);
		builder.append(", creatTime=");
		builder.append(creatTime);
		builder.append(", updateName=");
		builder.append(updateName);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}
}

