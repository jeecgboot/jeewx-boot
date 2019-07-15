package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemProjectErrorConfigService;
import com.jeecg.p3.system.entity.JwSystemProjectErrorConfig;
import com.jeecg.p3.system.dao.JwSystemProjectErrorConfigDao;

@Service("jwSystemProjectErrorConfigService")
public class JwSystemProjectErrorConfigServiceImpl implements JwSystemProjectErrorConfigService {
	@Resource
	private JwSystemProjectErrorConfigDao jwSystemProjectErrorConfigDao;

	@Override
	@CacheEvict(value="system_error_config",allEntries=true)
	public void doAdd(JwSystemProjectErrorConfig jwSystemProjectErrorConfig) {
		jwSystemProjectErrorConfigDao.insert(jwSystemProjectErrorConfig);
	}

	@Override
	@CacheEvict(value="system_error_config",allEntries=true)
	public void doEdit(JwSystemProjectErrorConfig jwSystemProjectErrorConfig) {
		jwSystemProjectErrorConfigDao.update(jwSystemProjectErrorConfig);
	}

	@Override
	@CacheEvict(value="system_error_config",allEntries=true)
	public void doDelete(String id) {
		jwSystemProjectErrorConfigDao.delete(id);
	}

	@Override
	public JwSystemProjectErrorConfig queryById(String id) {
		JwSystemProjectErrorConfig jwSystemProjectErrorConfig  = jwSystemProjectErrorConfigDao.get(id);
		return jwSystemProjectErrorConfig;
	}

	@Override
	public PageList<JwSystemProjectErrorConfig> queryPageList(
		PageQuery<JwSystemProjectErrorConfig> pageQuery) {
		PageList<JwSystemProjectErrorConfig> result = new PageList<JwSystemProjectErrorConfig>();
		Integer itemCount = jwSystemProjectErrorConfigDao.count(pageQuery);
		PageQueryWrapper<JwSystemProjectErrorConfig> wrapper = new PageQueryWrapper<JwSystemProjectErrorConfig>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemProjectErrorConfig> list = jwSystemProjectErrorConfigDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
