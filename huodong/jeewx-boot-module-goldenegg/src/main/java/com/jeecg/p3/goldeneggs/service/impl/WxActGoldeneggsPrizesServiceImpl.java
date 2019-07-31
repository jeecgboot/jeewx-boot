package com.jeecg.p3.goldeneggs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsPrizesDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsPrizesService;

@Service("wxActGoldeneggsPrizesService")
public class WxActGoldeneggsPrizesServiceImpl implements WxActGoldeneggsPrizesService {
	@Resource
	private WxActGoldeneggsPrizesDao wxActGoldeneggsPrizesDao;

	@Override
	public void doAdd(WxActGoldeneggsPrizes wxActGoldeneggsPrizes) {
		wxActGoldeneggsPrizesDao.insert(wxActGoldeneggsPrizes);
	}

	@Override
	public void doEdit(WxActGoldeneggsPrizes wxActGoldeneggsPrizes) {
		wxActGoldeneggsPrizesDao.update(wxActGoldeneggsPrizes);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsPrizesDao.delete(id);
	}

	@Override
	public WxActGoldeneggsPrizes queryById(String id) {
		WxActGoldeneggsPrizes wxActGoldeneggsPrizes  = wxActGoldeneggsPrizesDao.get(id);
		return wxActGoldeneggsPrizes;
	}

	@Override
	public PageList<WxActGoldeneggsPrizes> queryPageList(
		PageQuery<WxActGoldeneggsPrizes> pageQuery) {
		PageList<WxActGoldeneggsPrizes> result = new PageList<WxActGoldeneggsPrizes>();
		Integer itemCount = wxActGoldeneggsPrizesDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsPrizes> wrapper = new PageQueryWrapper<WxActGoldeneggsPrizes>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsPrizes> list = wxActGoldeneggsPrizesDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActGoldeneggsPrizes> queryPrizes(String jwid,String createBy) {
		return wxActGoldeneggsPrizesDao.queryPrizes(jwid,createBy);
	}

	@Override
	public List<WxActGoldeneggsPrizes> queryByActId(String actId) {
		return wxActGoldeneggsPrizesDao.queryByActId(actId);
	}

	@Override
	public List<WxActGoldeneggsPrizes> queryPrizesByName(String jwid, String createBy, String name) {
		
		return wxActGoldeneggsPrizesDao.queryPrizesByName(jwid, createBy, name);
	}
	
}
