package com.jeecg.p3.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.cms.service.CmsAdService;
import com.jeecg.p3.cms.entity.CmsAd;
import com.jeecg.p3.cms.dao.CmsAdDao;

/**
 * 描述：</b>广告管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时45分31秒 星期二 
 * @version:1.0
 */
@Service("cmsAdService")
public class CmsAdServiceImpl implements CmsAdService {
	@Resource
	private CmsAdDao cmsAdDao;

	@Override
	public void doAdd(CmsAd cmsAd) {
		cmsAdDao.insert(cmsAd);
	}

	@Override
	public void doEdit(CmsAd cmsAd) {
		cmsAdDao.update(cmsAd);
	}

	@Override
	public void doDelete(String id) {
		cmsAdDao.delete(id);
	}

	@Override
	public CmsAd queryById(String id) {
		CmsAd cmsAd  = cmsAdDao.get(id);
		return cmsAd;
	}

	@Override
	public PageList<CmsAd> queryPageList(PageQuery<CmsAd> pageQuery) {
		PageList<CmsAd> result = new PageList<CmsAd>();
		Integer itemCount = cmsAdDao.count(pageQuery);
		PageQueryWrapper<CmsAd> wrapper = new PageQueryWrapper<CmsAd>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsAd> list = cmsAdDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<CmsAd> getAll(String mainId) {
		return cmsAdDao.getAll(mainId);
	}
	
}
