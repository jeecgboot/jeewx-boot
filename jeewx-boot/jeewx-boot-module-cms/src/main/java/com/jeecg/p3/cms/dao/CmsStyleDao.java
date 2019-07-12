package com.jeecg.p3.cms.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.cms.entity.CmsStyle;

/**
 * 描述：</b>网站模板管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时55分54秒 星期二 
 * @version:1.0
 */
public interface CmsStyleDao extends GenericDao<CmsStyle>{
	
	public Integer count(PageQuery<CmsStyle> pageQuery);
	
	public List<CmsStyle> queryPageList(PageQueryWrapper<CmsStyle> wrapper);
	
}

