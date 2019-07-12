package com.jeecg.p3.wxconfig.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.wxconfig.dao.WeixinHuodongBizJwidDao;
import com.jeecg.p3.wxconfig.entity.WeixinHuodongBizJwid;
import com.jeecg.p3.wxconfig.service.WeixinHuodongBizJwidService;

/**
 * 描述：</b>微信活动jwid表<br>
 * @author：weijian.zhang
 * @since：2018年08月08日 09时32分33秒 星期三 
 * @version:1.0
 */
@Service("weixinHuodongBizJwidService")
public class WeixinHuodongBizJwidServiceImpl implements WeixinHuodongBizJwidService {
	@Resource
	private WeixinHuodongBizJwidDao weixinHuodongBizJwidDao;

	@Override
	public void doAdd(WeixinHuodongBizJwid weixinHuodongBizJwid) {
		weixinHuodongBizJwidDao.insert(weixinHuodongBizJwid);
	}

	@Override
	public void doEdit(WeixinHuodongBizJwid weixinHuodongBizJwid) {
		weixinHuodongBizJwidDao.update(weixinHuodongBizJwid);
	}

	@Override
	public void doDelete(String id) {
		weixinHuodongBizJwidDao.delete(id);
	}

	@Override
	public WeixinHuodongBizJwid queryById(String id) {
		WeixinHuodongBizJwid weixinHuodongBizJwid  = weixinHuodongBizJwidDao.get(id);
		return weixinHuodongBizJwid;
	}

	@Override
	public PageList<WeixinHuodongBizJwid> queryPageList(
		PageQuery<WeixinHuodongBizJwid> pageQuery) {
		PageList<WeixinHuodongBizJwid> result = new PageList<WeixinHuodongBizJwid>();
		Integer itemCount = weixinHuodongBizJwidDao.count(pageQuery);
		PageQueryWrapper<WeixinHuodongBizJwid> wrapper = new PageQueryWrapper<WeixinHuodongBizJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinHuodongBizJwid> list = weixinHuodongBizJwidDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
