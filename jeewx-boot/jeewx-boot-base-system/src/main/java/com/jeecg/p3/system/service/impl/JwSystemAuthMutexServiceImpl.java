package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemAuthMutexService;
import com.jeecg.p3.system.entity.JwSystemAuthMutex;
import com.jeecg.p3.system.dao.JwSystemAuthMutexDao;

@Service("jwSystemAuthMutexService")
public class JwSystemAuthMutexServiceImpl implements JwSystemAuthMutexService {
	@Resource
	private JwSystemAuthMutexDao jwSystemAuthMutexDao;

	@Override
	public void doAdd(JwSystemAuthMutex jwSystemAuthMutex) {
		jwSystemAuthMutexDao.insert(jwSystemAuthMutex);
	}

	@Override
	public void doEdit(JwSystemAuthMutex jwSystemAuthMutex) {
		jwSystemAuthMutexDao.update(jwSystemAuthMutex);
	}

	@Override
	public void doDelete(String id) {
		jwSystemAuthMutexDao.delete(id);
	}

	@Override
	public JwSystemAuthMutex queryById(String id) {
		JwSystemAuthMutex jwSystemAuthMutex  = jwSystemAuthMutexDao.get(id);
		return jwSystemAuthMutex;
	}

	@Override
	public PageList<JwSystemAuthMutex> queryPageList(
		PageQuery<JwSystemAuthMutex> pageQuery) {
		PageList<JwSystemAuthMutex> result = new PageList<JwSystemAuthMutex>();
		Integer itemCount = jwSystemAuthMutexDao.count(pageQuery);
		PageQueryWrapper<JwSystemAuthMutex> wrapper = new PageQueryWrapper<JwSystemAuthMutex>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemAuthMutex> list = jwSystemAuthMutexDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<JwSystemAuthMutex> queryAllAuthMutex() {
		return jwSystemAuthMutexDao.queryAllAuthMutex();
	}
	
}
