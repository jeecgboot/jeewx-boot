package com.jeecg.p3.wxconfig.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>微信活动jwid表<br>
 * @author weijian.zhang
 * @since：2018年08月08日 09时32分33秒 星期三 
 * @version:1.0
 */
public class WeixinHuodongBizJwid implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *表名称	 */	private String tableName;	/**	 *表注释	 */	private String tableRemake;
	//update-begin--Author:zhangweijian Date:20181011 for：新增一个表类型字段	/**	 *表类型：'1'：微信，'2'：活动	 */	private String tableType;
		public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	//update-end--Author:zhangweijian Date:20181011 for：新增一个表类型字段
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTableName() {	    return this.tableName;	}	public void setTableName(String tableName) {	    this.tableName=tableName;	}	public String getTableRemake() {	    return this.tableRemake;	}	public void setTableRemake(String tableRemake) {	    this.tableRemake=tableRemake;	}
}

