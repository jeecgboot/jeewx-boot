package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinTemplateService;
import com.jeecg.p3.weixin.entity.WeixinTemplate;
import com.jeecg.p3.weixin.dao.WeixinTemplateDao;

/**
 * 描述：</b>图文样式库表<br>
 * @author：weijian.zhang
 * @since：2018年07月24日 20时01分05秒 星期二 
 * @version:1.0
 */
@Service("weixinTemplateService")
public class WeixinTemplateServiceImpl implements WeixinTemplateService {
	@Resource
	private WeixinTemplateDao weixinTemplateDao;

	@Override
	public void doAdd(WeixinTemplate weixinTemplate) {
		weixinTemplateDao.insert(weixinTemplate);
	}

	@Override
	public void doEdit(WeixinTemplate weixinTemplate) {
		weixinTemplateDao.update(weixinTemplate);
	}

	@Override
	public void doDelete(String id) {
		weixinTemplateDao.delete(id);
	}

	@Override
	public WeixinTemplate queryById(String id) {
		WeixinTemplate weixinTemplate  = weixinTemplateDao.get(id);
		return weixinTemplate;
	}

	@Override
	public PageList<WeixinTemplate> queryPageList(
		PageQuery<WeixinTemplate> pageQuery) {
		PageList<WeixinTemplate> result = new PageList<WeixinTemplate>();
		Integer itemCount = weixinTemplateDao.count(pageQuery);
		PageQueryWrapper<WeixinTemplate> wrapper = new PageQueryWrapper<WeixinTemplate>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTemplate> list = weixinTemplateDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	//获取单个样式库
	@Override
	public List<WeixinTemplate> queryByType(String type) {
		return weixinTemplateDao.queryByType(type);
	}
	
}
