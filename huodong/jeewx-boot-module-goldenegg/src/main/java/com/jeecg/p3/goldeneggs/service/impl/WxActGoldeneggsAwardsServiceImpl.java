package com.jeecg.p3.goldeneggs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsAwardsDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsAwardsService;

@Service("wxActGoldeneggsAwardsService")
public class WxActGoldeneggsAwardsServiceImpl implements WxActGoldeneggsAwardsService {
	@Resource
	private WxActGoldeneggsAwardsDao wxActGoldeneggsAwardsDao;

	@Override
	public void doAdd(WxActGoldeneggsAwards wxActGoldeneggsAwards) {
		wxActGoldeneggsAwardsDao.insert(wxActGoldeneggsAwards);
	}

	@Override
	public void doEdit(WxActGoldeneggsAwards wxActGoldeneggsAwards) {
		wxActGoldeneggsAwardsDao.update(wxActGoldeneggsAwards);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsAwardsDao.delete(id);
	}

	@Override
	public WxActGoldeneggsAwards queryById(String id) {
		WxActGoldeneggsAwards wxActGoldeneggsAwards  = wxActGoldeneggsAwardsDao.get(id);
		return wxActGoldeneggsAwards;
	}

	@Override
	public PageList<WxActGoldeneggsAwards> queryPageList(
		PageQuery<WxActGoldeneggsAwards> pageQuery) {
		PageList<WxActGoldeneggsAwards> result = new PageList<WxActGoldeneggsAwards>();
		Integer itemCount = wxActGoldeneggsAwardsDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsAwards> wrapper = new PageQueryWrapper<WxActGoldeneggsAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsAwards> list = wxActGoldeneggsAwardsDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActGoldeneggsAwards> queryAwards(String jwid,String createBy) {
	
		return wxActGoldeneggsAwardsDao.queryAwards(jwid,createBy);
	}

	@Override
	public List<WxActGoldeneggsAwards> queryAwards(String jwid) {
		return wxActGoldeneggsAwardsDao.queryAwards(jwid);
	}

	@Override
	public List<WxActGoldeneggsAwards> queryAwardsByName(String jwid, String createBy, String name) {
		
		return wxActGoldeneggsAwardsDao.queryAwardsByName(jwid, createBy, name);
	}

}
