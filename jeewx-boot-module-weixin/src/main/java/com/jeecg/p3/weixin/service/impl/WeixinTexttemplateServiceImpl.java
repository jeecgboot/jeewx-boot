package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinTexttemplateService;
import com.jeecg.p3.weixin.entity.WeixinTexttemplate;
import com.jeecg.p3.weixin.dao.WeixinTexttemplateDao;

/**
 * 描述：</b>文本模板表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时45分36秒 星期五 
 * @version:1.0
 */
@Service("weixinTexttemplateService")
public class WeixinTexttemplateServiceImpl implements WeixinTexttemplateService {
	@Resource
	private WeixinTexttemplateDao weixinTexttemplateDao;

	@Override
	public void doAdd(WeixinTexttemplate weixinTexttemplate) {
		weixinTexttemplateDao.insert(weixinTexttemplate);
	}

	@Override
	public void doEdit(WeixinTexttemplate weixinTexttemplate) {
		weixinTexttemplateDao.update(weixinTexttemplate);
	}

	@Override
	public void doDelete(String id) {
		weixinTexttemplateDao.delete(id);
	}

	@Override
	public WeixinTexttemplate queryById(String id) {
		WeixinTexttemplate weixinTexttemplate  = weixinTexttemplateDao.get(id);
		return weixinTexttemplate;
	}

	@Override
	public PageList<WeixinTexttemplate> queryPageList(
		PageQuery<WeixinTexttemplate> pageQuery) {
		PageList<WeixinTexttemplate> result = new PageList<WeixinTexttemplate>();
		Integer itemCount = weixinTexttemplateDao.count(pageQuery);
		PageQueryWrapper<WeixinTexttemplate> wrapper = new PageQueryWrapper<WeixinTexttemplate>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTexttemplate> list = weixinTexttemplateDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	//update-begin--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
	//获取所有文本素材
	@Override
	public List<WeixinTexttemplate> getAllTemplate(String jwid) {
		return weixinTexttemplateDao.getAllTemplate(jwid);
	}
	//update-end--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
	
}
