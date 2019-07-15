package com.jeecg.p3.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jeecg.p3.cms.dao.CmsSiteDao;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.enums.CmsSiteTemplateEnum;
import com.jeecg.p3.cms.service.CmsSiteService;

/**
 * 描述：</b>网站管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时53分26秒 星期二 
 * @version:1.0
 */
@Service("cmsSiteService")
public class CmsSiteServiceImpl implements CmsSiteService {
	@Resource
	private CmsSiteDao cmsSiteDao;

	@Override
	@CacheEvict(value="commonxrs",allEntries=true)
	public void doAdd(CmsSite cmsSite) {
		cmsSiteDao.insert(cmsSite);
	}

	@Override
	@CacheEvict(value="commonxrs",allEntries=true)
	public void doEdit(CmsSite cmsSite) {
		cmsSiteDao.update(cmsSite);
	}

	@Override
	@CacheEvict(value="commonxrs",allEntries=true)
	public void doDelete(String id) {
		cmsSiteDao.delete(id);
	}

	@Override
	public CmsSite queryById(String id) {
		CmsSite cmsSite  = cmsSiteDao.get(id);
		return cmsSite;
	}

	@Override
	public PageList<CmsSite> queryPageList(
		PageQuery<CmsSite> pageQuery) {
		PageList<CmsSite> result = new PageList<CmsSite>();
		Integer itemCount = cmsSiteDao.count(pageQuery);
		PageQueryWrapper<CmsSite> wrapper = new PageQueryWrapper<CmsSite>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsSite> list = cmsSiteDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<CmsSite> getAll() {
		return cmsSiteDao.getAll();
	}

	@Override
	public Integer queryByJwidAndCreateBy(String jwid, String createBy) {
		return cmsSiteDao.queryByJwidAndCreateBy(jwid, createBy);
	}

	@Override
	public CmsSite querySiteByJwidAndCreateBy(String jwid, String createBy) {
		return cmsSiteDao.querySiteByJwidAndCreateBy(jwid, createBy);
	}
	

	@Override
	@Cacheable(value="commonxrs")
	public String getSiteTemplate(String id) {
		CmsSite cmsSite  = cmsSiteDao.get(id);
		if(cmsSite!=null && oConvertUtils.isNotEmpty(cmsSite.getSiteTemplateStyle())){
			return cmsSite.getSiteTemplateStyle();
		}
		return CmsSiteTemplateEnum.WXHOME.getStyle();
	}
	
}
