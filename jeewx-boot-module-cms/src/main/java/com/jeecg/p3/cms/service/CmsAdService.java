package com.jeecg.p3.cms.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.cms.entity.CmsAd;

/**
 * 描述：</b>广告管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时45分31秒 星期二 
 * @version:1.0
 */
public interface CmsAdService {
	
	
	public void doAdd(CmsAd cmsAd);
	
	public void doEdit(CmsAd cmsAd);
	
	public void doDelete(String id);
	
	public CmsAd queryById(String id);
	
	public PageList<CmsAd> queryPageList(PageQuery<CmsAd> pageQuery);
	
	public List<CmsAd> getAll(String mainId);
}

