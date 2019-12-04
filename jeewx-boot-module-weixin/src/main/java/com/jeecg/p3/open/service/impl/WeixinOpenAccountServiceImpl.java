package com.jeecg.p3.open.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.open.dao.WeixinOpenAccountDao;

@Service("weixinOpenAccountService")
public class WeixinOpenAccountServiceImpl implements WeixinOpenAccountService {
	@Resource
	private WeixinOpenAccountDao weixinOpenAccountDao;

	@Override
	public void doAdd(WeixinOpenAccount weixinOpenAccount) {
		weixinOpenAccountDao.insert(weixinOpenAccount);
	}

	@Override
	public void doEdit(WeixinOpenAccount weixinOpenAccount) {
		weixinOpenAccountDao.update(weixinOpenAccount);
	}

	@Override
	public void doDelete(String id) {
		weixinOpenAccountDao.delete(id);
	}

	@Override
	public WeixinOpenAccount queryById(String id) {
		WeixinOpenAccount weixinOpenAccount  = weixinOpenAccountDao.get(id);
		return weixinOpenAccount;
	}

	@Override
	public WeixinOpenAccount queryOneByAppid(String appid) {
		return weixinOpenAccountDao.queryOneByAppid(appid);
	}
	
	@Override
	public PageList<WeixinOpenAccount> queryPageList(
		PageQuery<WeixinOpenAccount> pageQuery) {
		PageList<WeixinOpenAccount> result = new PageList<WeixinOpenAccount>();
		Integer itemCount = weixinOpenAccountDao.count(pageQuery);
		PageQueryWrapper<WeixinOpenAccount> wrapper = new PageQueryWrapper<WeixinOpenAccount>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinOpenAccount> list = weixinOpenAccountDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
}
