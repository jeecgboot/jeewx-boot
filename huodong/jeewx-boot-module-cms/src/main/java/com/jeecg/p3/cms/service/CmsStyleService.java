package com.jeecg.p3.cms.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.cms.entity.CmsStyle;

/**
 * 描述：</b>网站模板管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时55分54秒 星期二 
 * @version:1.0
 */
public interface CmsStyleService {
	
	
	public void doAdd(CmsStyle cmsStyle);
	
	public void doEdit(CmsStyle cmsStyle);
	
	public void doDelete(String id);
	
	public CmsStyle queryById(String id);
	
	public PageList<CmsStyle> queryPageList(PageQuery<CmsStyle> pageQuery);
}

