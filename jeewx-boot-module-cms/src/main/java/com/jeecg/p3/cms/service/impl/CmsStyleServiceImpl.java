package com.jeecg.p3.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.cms.service.CmsStyleService;
import com.jeecg.p3.cms.entity.CmsStyle;
import com.jeecg.p3.cms.dao.CmsStyleDao;

/**
 * 描述：</b>网站模板管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时55分54秒 星期二 
 * @version:1.0
 */
@Service("cmsStyleService")
public class CmsStyleServiceImpl implements CmsStyleService {
	@Resource
	private CmsStyleDao cmsStyleDao;

	@Override
	public void doAdd(CmsStyle cmsStyle) {
		cmsStyleDao.insert(cmsStyle);
	}

	@Override
	public void doEdit(CmsStyle cmsStyle) {
		cmsStyleDao.update(cmsStyle);
	}

	@Override
	public void doDelete(String id) {
		cmsStyleDao.delete(id);
	}

	@Override
	public CmsStyle queryById(String id) {
		CmsStyle cmsStyle  = cmsStyleDao.get(id);
		return cmsStyle;
	}

	@Override
	public PageList<CmsStyle> queryPageList(
		PageQuery<CmsStyle> pageQuery) {
		PageList<CmsStyle> result = new PageList<CmsStyle>();
		Integer itemCount = cmsStyleDao.count(pageQuery);
		PageQueryWrapper<CmsStyle> wrapper = new PageQueryWrapper<CmsStyle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsStyle> list = cmsStyleDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
