package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemRegisterService;
import com.jeecg.p3.system.entity.JwSystemRegister;
import com.jeecg.p3.system.dao.JwSystemRegisterDao;

@Service("jwSystemRegisterService")
public class JwSystemRegisterServiceImpl implements JwSystemRegisterService {
	@Resource
	private JwSystemRegisterDao jwSystemRegisterDao;

	@Override
	public void doAdd(JwSystemRegister jwSystemRegister) {
		jwSystemRegisterDao.insert(jwSystemRegister);
	}

	@Override
	public void doEdit(JwSystemRegister jwSystemRegister) {
		jwSystemRegisterDao.update(jwSystemRegister);
	}

	@Override
	public void doDelete(String id) {
		jwSystemRegisterDao.delete(id);
	}

	@Override
	public JwSystemRegister queryById(String id) {
		JwSystemRegister jwSystemRegister  = jwSystemRegisterDao.get(id);
		return jwSystemRegister;
	}

	@Override
	public PageList<JwSystemRegister> queryPageList(
		PageQuery<JwSystemRegister> pageQuery) {
		PageList<JwSystemRegister> result = new PageList<JwSystemRegister>();
		Integer itemCount = jwSystemRegisterDao.count(pageQuery);
		PageQueryWrapper<JwSystemRegister> wrapper = new PageQueryWrapper<JwSystemRegister>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemRegister> list = jwSystemRegisterDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
	public List<JwSystemRegister> queryByProperty(JwSystemRegister property){
		return jwSystemRegisterDao.queryByProperty(property);
		 
	}
	
}
