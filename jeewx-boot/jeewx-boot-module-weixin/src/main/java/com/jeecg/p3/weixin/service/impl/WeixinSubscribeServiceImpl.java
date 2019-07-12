package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinSubscribeService;
import com.jeecg.p3.weixin.entity.WeixinSubscribe;
import com.jeecg.p3.weixin.dao.WeixinSubscribeDao;

/**
 * 描述：</b>关注欢迎语<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时12分09秒 星期五 
 * @version:1.0
 */
@Service("weixinSubscribeService")
public class WeixinSubscribeServiceImpl implements WeixinSubscribeService {
	@Resource
	private WeixinSubscribeDao weixinSubscribeDao;

	@Override
	public void doAdd(WeixinSubscribe weixinSubscribe) {
		weixinSubscribeDao.insert(weixinSubscribe);
	}

	@Override
	public void doEdit(WeixinSubscribe weixinSubscribe) {
		weixinSubscribeDao.update(weixinSubscribe);
	}

	@Override
	public void doDelete(String id) {
		weixinSubscribeDao.delete(id);
	}

	@Override
	public WeixinSubscribe queryById(String id) {
		WeixinSubscribe weixinSubscribe  = weixinSubscribeDao.get(id);
		return weixinSubscribe;
	}

	@Override
	public PageList<WeixinSubscribe> queryPageList(
		PageQuery<WeixinSubscribe> pageQuery) {
		PageList<WeixinSubscribe> result = new PageList<WeixinSubscribe>();
		Integer itemCount = weixinSubscribeDao.count(pageQuery);
		PageQueryWrapper<WeixinSubscribe> wrapper = new PageQueryWrapper<WeixinSubscribe>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinSubscribe> list = weixinSubscribeDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WeixinSubscribe querySubscribeByJwid(String jwid) {
		return weixinSubscribeDao.querySubscribeByJwid(jwid);
	}

}
