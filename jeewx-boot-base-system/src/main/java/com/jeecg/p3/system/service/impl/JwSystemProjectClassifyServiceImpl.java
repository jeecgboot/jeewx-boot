package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemProjectClassifyService;
import com.jeecg.p3.system.entity.JwSystemProjectClassify;
import com.jeecg.p3.system.dao.JwSystemProjectClassifyDao;

@Service("jwSystemProjectClassifyService")
public class JwSystemProjectClassifyServiceImpl implements JwSystemProjectClassifyService {
	@Resource
	private JwSystemProjectClassifyDao jwSystemProjectClassifyDao;

	@Override
	public void doAdd(JwSystemProjectClassify jwSystemProjectClassify) {
		jwSystemProjectClassifyDao.insert(jwSystemProjectClassify);
	}

	@Override
	public void doEdit(JwSystemProjectClassify jwSystemProjectClassify) {
		jwSystemProjectClassifyDao.update(jwSystemProjectClassify);
	}

	@Override
	public void doDelete(String id) {
		jwSystemProjectClassifyDao.delete(id);
	}

	@Override
	public JwSystemProjectClassify queryById(String id) {
		JwSystemProjectClassify jwSystemProjectClassify  = jwSystemProjectClassifyDao.get(id);
		return jwSystemProjectClassify;
	}

	@Override
	public PageList<JwSystemProjectClassify> queryPageList(
		PageQuery<JwSystemProjectClassify> pageQuery) {
		PageList<JwSystemProjectClassify> result = new PageList<JwSystemProjectClassify>();
		Integer itemCount = jwSystemProjectClassifyDao.count(pageQuery);
		PageQueryWrapper<JwSystemProjectClassify> wrapper = new PageQueryWrapper<JwSystemProjectClassify>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemProjectClassify> list = jwSystemProjectClassifyDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<JwSystemProjectClassify> queryAllByType(String type) {
		return jwSystemProjectClassifyDao.queryAllByType(type);
	}

	@Override
	public List<JwSystemProjectClassify> queryAllByBaseId(String baseId) {
		return jwSystemProjectClassifyDao.queryAllByBaseId(baseId);
	}

	@Override
	public List<JwSystemProjectClassify> queryListByAll() {
		return jwSystemProjectClassifyDao.queryListByAll();
	}
	
}
