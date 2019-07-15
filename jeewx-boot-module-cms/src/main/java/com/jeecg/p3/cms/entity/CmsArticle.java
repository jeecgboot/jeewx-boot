package com.jeecg.p3.cms.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>文章管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
public class CmsArticle implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 站点ID
	 */
	private String mainId;
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	
}
