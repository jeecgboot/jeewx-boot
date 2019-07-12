package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.system.dao.JwSystemUserJwidDao;
import com.jeecg.p3.system.entity.JwSystemUserJwid;
import com.jeecg.p3.system.service.JwSystemUserJwidService;

@Service("jwSystemUserJwidService")
public class JwSystemUserJwidServiceImpl implements JwSystemUserJwidService {
	@Resource
	private JwSystemUserJwidDao jwSystemUserJwidDao;

	@Override
	public void doAdd(JwSystemUserJwid jwSystemUserJwid) {
		jwSystemUserJwidDao.insert(jwSystemUserJwid);
	}

	@Override
	public void doEdit(JwSystemUserJwid jwSystemUserJwid) {
		jwSystemUserJwidDao.update(jwSystemUserJwid);
	}

	@Override
	public void doDelete(String id) {
		jwSystemUserJwidDao.delete(id);
	}

	@Override
	public JwSystemUserJwid queryById(String id) {
		JwSystemUserJwid jwSystemUserJwid  = jwSystemUserJwidDao.get(id);
		return jwSystemUserJwid;
	}

	@Override
	public PageList<JwSystemUserJwid> queryPageList(
		PageQuery<JwSystemUserJwid> pageQuery) {
		PageList<JwSystemUserJwid> result = new PageList<JwSystemUserJwid>();
		Integer itemCount = jwSystemUserJwidDao.count(pageQuery);
		PageQueryWrapper<JwSystemUserJwid> wrapper = new PageQueryWrapper<JwSystemUserJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemUserJwid> list = jwSystemUserJwidDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public JwSystemUserJwid queryOneByUserIdAndDefaultFlag(String userId,
			String defaultFlag) {
		return jwSystemUserJwidDao.queryOneByUserIdAndDefaultFlag(userId, defaultFlag);
	}

	
}
