package com.jeecg.p3.system.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemActTxt:系统文本表<br>
 * @author junfeng.zhou
 * @since：2015年11月11日 11时11分51秒 星期三 
 * @version:1.0
 */
public class JwSystemActTxt implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *文本编码	 */	private String code;	/**	 *文本内容	 */	private String content;	/**	 *文本描述	 */	private String discribe;	/**	 *所属活动	 */	private String actCode;
		/**	 *创建人	 */	private String creatName;	/**	 *创建时间	 */	private Date creatTime;	/**	 *修改人	 */	private String updateName;	/**	 *修改时间	 */	private Date updateTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getActCode() {	    return this.actCode;	}	public void setActCode(String actCode) {	    this.actCode=actCode;	}	public String getCreatName() {	    return this.creatName;	}	public void setCreatName(String creatName) {	    this.creatName=creatName;	}	public Date getCreatTime() {	    return this.creatTime;	}	public void setCreatTime(Date creatTime) {	    this.creatTime=creatTime;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
	public String getDiscribe() {
		return discribe;
	}
	public void setDiscribe(String discribe) {
		this.discribe = discribe;
	}
	//update-begin--Author:huangqingquan  Date:2016-7-22 15:00:22 for：添加文本类型
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "JwSystemActTxt [id=" + id + ", code=" + code + ", content="
				+ content + ", discribe=" + discribe + ", actCode=" + actCode
				+ ", creatName=" + creatName + ", creatTime=" + creatTime
				+ ", updateName=" + updateName + ", updateTime=" + updateTime
				+ ", type=" + type + "]";
	}
	//update-end--Author:huangqingquan  Date:2016-7-22 15:00:22 for：添加文本类型
}

