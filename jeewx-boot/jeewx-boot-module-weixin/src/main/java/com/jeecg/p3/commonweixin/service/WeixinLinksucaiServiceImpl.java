package com.jeecg.p3.commonweixin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.commonweixin.dao.WeixinLinksucaiDao;
import com.jeecg.p3.commonweixin.entity.WeixinLinksucai;
import com.jeecg.p3.commonweixin.service.impl.WeixinLinksucaiService;

@Service("weixinLinksucaiService")
public class WeixinLinksucaiServiceImpl implements WeixinLinksucaiService {
	@Resource
	private WeixinLinksucaiDao weixinLinksucaiDao;

	@Override
	public void doAdd(WeixinLinksucai weixinLinksucai) {
		weixinLinksucaiDao.insert(weixinLinksucai);
	}

	@Override
	public void doEdit(WeixinLinksucai weixinLinksucai) {
		weixinLinksucaiDao.update(weixinLinksucai);
	}

	@Override
	public void doDelete(String id) {
		weixinLinksucaiDao.delete(id);
	}

	@Override
	public WeixinLinksucai queryById(String id) {
		WeixinLinksucai weixinLinksucai  = weixinLinksucaiDao.get(id);
		return weixinLinksucai;
	}

	@Override
	public PageList<WeixinLinksucai> queryPageList(
		PageQuery<WeixinLinksucai> pageQuery) {
		PageList<WeixinLinksucai> result = new PageList<WeixinLinksucai>();
		Integer itemCount = weixinLinksucaiDao.count(pageQuery);
		PageQueryWrapper<WeixinLinksucai> wrapper = new PageQueryWrapper<WeixinLinksucai>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinLinksucai> list = weixinLinksucaiDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
