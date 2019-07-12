package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinAutoresponseDefaultService;
import com.jeecg.p3.weixin.entity.WeixinAutoresponseDefault;
import com.jeecg.p3.weixin.dao.WeixinAutoresponseDefaultDao;

/**
 * 描述：</b>未识别回复语<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时11分50秒 星期五 
 * @version:1.0
 */
@Service("weixinAutoresponseDefaultService")
public class WeixinAutoresponseDefaultServiceImpl implements WeixinAutoresponseDefaultService {
	@Resource
	private WeixinAutoresponseDefaultDao weixinAutoresponseDefaultDao;

	@Override
	public void doAdd(WeixinAutoresponseDefault weixinAutoresponseDefault) {
		weixinAutoresponseDefaultDao.insert(weixinAutoresponseDefault);
	}

	@Override
	public void doEdit(WeixinAutoresponseDefault weixinAutoresponseDefault) {
		weixinAutoresponseDefaultDao.update(weixinAutoresponseDefault);
	}

	@Override
	public void doDelete(String id) {
		weixinAutoresponseDefaultDao.delete(id);
	}

	@Override
	public WeixinAutoresponseDefault queryById(String id) {
		WeixinAutoresponseDefault weixinAutoresponseDefault  = weixinAutoresponseDefaultDao.get(id);
		return weixinAutoresponseDefault;
	}

	@Override
	public PageList<WeixinAutoresponseDefault> queryPageList(
		PageQuery<WeixinAutoresponseDefault> pageQuery) {
		PageList<WeixinAutoresponseDefault> result = new PageList<WeixinAutoresponseDefault>();
		Integer itemCount = weixinAutoresponseDefaultDao.count(pageQuery);
		PageQueryWrapper<WeixinAutoresponseDefault> wrapper = new PageQueryWrapper<WeixinAutoresponseDefault>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinAutoresponseDefault> list = weixinAutoresponseDefaultDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	/**
	 * 根据触发类型查询记录是否存在
	 * @param msgType
	 * @return
	 */
	@Override
	public WeixinAutoresponseDefault queryBymsgTriggerType(String msgTriggerType, String jwid) {
		return weixinAutoresponseDefaultDao.queryBymsgTriggerType(msgTriggerType, jwid);
	}

}
