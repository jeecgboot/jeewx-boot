package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.system.dao.JwSystemActTxtDao;
import com.jeecg.p3.system.entity.JwSystemActTxt;
import com.jeecg.p3.system.service.JwSystemActTxtService;

@Service("jwSystemActTxtService")
public class JwSystemActTxtServiceImpl implements JwSystemActTxtService {
	@Resource
	private JwSystemActTxtDao jwSystemActTxtDao;
	@Autowired
	private SystemActTxtService systemActTxtService;
	@Override
	@CacheEvict(value="txtCache",allEntries=true)
	public void doAdd(JwSystemActTxt jwSystemActTxt) {
		jwSystemActTxtDao.insert(jwSystemActTxt);
	}

	@Override
	@CacheEvict(value="txtCache",allEntries=true)
	public void doEdit(JwSystemActTxt jwSystemActTxt) {
		jwSystemActTxtDao.update(jwSystemActTxt);
	}

	@Override
	@CacheEvict(value="txtCache",allEntries=true)
	public void doDelete(String id) {
		jwSystemActTxtDao.delete(id);
	}

	@Override
	public JwSystemActTxt queryById(String id) {
		JwSystemActTxt jwSystemActTxt  = jwSystemActTxtDao.get(id);
		return jwSystemActTxt;
	}

	@Override
	@Transactional
	public PageList<JwSystemActTxt> queryPageList(
		PageQuery<JwSystemActTxt> pageQuery) {
		PageList<JwSystemActTxt> result = new PageList<JwSystemActTxt>();
		Integer itemCount = jwSystemActTxtDao.count(pageQuery);
		PageQueryWrapper<JwSystemActTxt> wrapper = new PageQueryWrapper<JwSystemActTxt>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemActTxt> list = jwSystemActTxtDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	@Transactional
	public void copyActText(String actCode, String actId) {
		if(StringUtils.isNotEmpty(actCode)){
			List<JwSystemActTxt> jwSystemActTxts = jwSystemActTxtDao.queryByActCode(actCode);
			for(JwSystemActTxt jwSystemActTxt:jwSystemActTxts){
				jwSystemActTxt.setActCode(actId);
				jwSystemActTxt.setId(null);
				jwSystemActTxtDao.insert(jwSystemActTxt);
			}
		}
		
	}
	@Override
	public void batchDeleteByActCode(String actCode){
		systemActTxtService.batchDeleteByActCode(actCode);
	}
	
	@Override
	public List<JwSystemActTxt> queryListByActCode(String actCode) {
		return jwSystemActTxtDao.queryByActCode(actCode);
	}
	
}
