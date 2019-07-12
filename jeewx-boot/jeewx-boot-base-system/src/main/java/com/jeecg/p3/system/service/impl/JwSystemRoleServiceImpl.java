package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemRoleService;
import com.jeecg.p3.system.entity.JwSystemRole;
import com.jeecg.p3.system.dao.JwSystemAuthDao;
import com.jeecg.p3.system.dao.JwSystemRoleDao;

@Service("jwSystemRoleService")
public class JwSystemRoleServiceImpl implements JwSystemRoleService {
	@Resource
	private JwSystemRoleDao jwSystemRoleDao;
	@Resource
	private JwSystemAuthDao jwSystemAuthDao;

	@Override
	public void doAdd(JwSystemRole jwSystemRole) {
		jwSystemRoleDao.insert(jwSystemRole);
	}

	@Override
	public void doEdit(JwSystemRole jwSystemRole) {
		jwSystemRoleDao.update(jwSystemRole);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void doDelete(Long id,String roleId) {
		jwSystemAuthDao.deleteRoleAuthRels(roleId);
		jwSystemRoleDao.delete(id);
	}

	@Override
	public JwSystemRole queryById(Long id) {
		JwSystemRole jwSystemRole  = jwSystemRoleDao.get(id);
		return jwSystemRole;
	}

	@Override
	public PageList<JwSystemRole> queryPageList(
		PageQuery<JwSystemRole> pageQuery) {
		PageList<JwSystemRole> result = new PageList<JwSystemRole>();
		Integer itemCount = jwSystemRoleDao.count(pageQuery);
		PageQueryWrapper<JwSystemRole> wrapper = new PageQueryWrapper<JwSystemRole>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemRole> list = jwSystemRoleDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<JwSystemRole> queryAllRoleList() {
		return jwSystemRoleDao.queryAllRoleList();
	}
	
}
