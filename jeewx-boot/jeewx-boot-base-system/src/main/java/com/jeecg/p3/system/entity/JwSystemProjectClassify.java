package com.jeecg.p3.system.entity;

import java.util.Date;
import java.util.List;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemProjectClassify:<br>
 * @author huangqingquan
 * @since：2016年08月08日 09时33分31秒 星期一 
 * @version:1.0
 */
public class JwSystemProjectClassify implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *分类名称	 */	private String name;	/**	 *父ID	 */	private String baseId;	/**	 *父分类名称	 */	private String baseName;	/**	 *排序	 */	private String sort;	/**	 *创建人	 */	private String creatName;	/**	 *创建时间	 */	private Date createTime;	/**	 *更新人名称	 */	private String updateName;
	/**
	 * 当前类的集合
	 */
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getBaseId() {	    return this.baseId;	}	public void setBaseId(String baseId) {	    this.baseId=baseId;	}	public String getBaseName() {	    return this.baseName;	}	public void setBaseName(String baseName) {	    this.baseName=baseName;	}	public String getSort() {	    return this.sort;	}	public void setSort(String sort) {	    this.sort=sort;	}	public String getCreatName() {	    return this.creatName;	}	public void setCreatName(String creatName) {	    this.creatName=creatName;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}
	@Override
	public String toString() {
		return "JwSystemProjectClassify [id=" + id + ", name=" + name
				+ ", baseId=" + baseId + ", baseName=" + baseName + ", sort="
				+ sort + ", creatName=" + creatName + ", createTime="
				+ createTime + ", updateName=" + updateName + "]";
	}
}

