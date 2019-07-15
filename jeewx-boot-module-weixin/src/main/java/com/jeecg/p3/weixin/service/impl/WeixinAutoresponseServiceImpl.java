package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinAutoresponseService;
import com.jeecg.p3.weixin.entity.WeixinAutoresponse;
import com.jeecg.p3.weixin.dao.WeixinAutoresponseDao;

/**
 * 描述：</b>关键字表<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时11分12秒 星期五 
 * @version:1.0
 */
@Service("weixinAutoresponseService")
public class WeixinAutoresponseServiceImpl implements WeixinAutoresponseService {
	@Resource
	private WeixinAutoresponseDao weixinAutoresponseDao;

	@Override
	public void doAdd(WeixinAutoresponse weixinAutoresponse) {
		weixinAutoresponseDao.insert(weixinAutoresponse);
	}

	@Override
	public void doEdit(WeixinAutoresponse weixinAutoresponse) {
		weixinAutoresponseDao.update(weixinAutoresponse);
	}

	@Override
	public void doDelete(String id) {
		weixinAutoresponseDao.delete(id);
	}

	@Override
	public WeixinAutoresponse queryById(String id) {
		WeixinAutoresponse weixinAutoresponse  = weixinAutoresponseDao.get(id);
		return weixinAutoresponse;
	}

	@Override
	public PageList<WeixinAutoresponse> queryPageList(
		PageQuery<WeixinAutoresponse> pageQuery) {
		PageList<WeixinAutoresponse> result = new PageList<WeixinAutoresponse>();
		Integer itemCount = weixinAutoresponseDao.count(pageQuery);
		PageQueryWrapper<WeixinAutoresponse> wrapper = new PageQueryWrapper<WeixinAutoresponse>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinAutoresponse> list = weixinAutoresponseDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WeixinAutoresponse> queryByJwid(String jwid) {
		return weixinAutoresponseDao.queryByJwid(jwid);
	}
	
}
