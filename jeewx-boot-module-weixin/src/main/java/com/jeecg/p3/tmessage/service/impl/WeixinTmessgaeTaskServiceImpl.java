package com.jeecg.p3.tmessage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.tmessage.service.WeixinTmessgaeTaskService;
import com.jeecg.p3.tmessage.entity.WeixinTmessgaeTask;
import com.jeecg.p3.tmessage.dao.WeixinTmessgaeTaskDao;

/**
 * 描述：</b>发送模板消息表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时23分28秒 星期三 
 * @version:1.0
 */
@Service("weixinTmessgaeTaskService")
public class WeixinTmessgaeTaskServiceImpl implements WeixinTmessgaeTaskService {
	@Resource
	private WeixinTmessgaeTaskDao weixinTmessgaeTaskDao;

	@Override
	public void doAdd(WeixinTmessgaeTask weixinTmessgaeTask) {
		weixinTmessgaeTaskDao.insert(weixinTmessgaeTask);
	}

	@Override
	public void doEdit(WeixinTmessgaeTask weixinTmessgaeTask) {
		weixinTmessgaeTaskDao.update(weixinTmessgaeTask);
	}

	@Override
	public void doDelete(String id) {
		weixinTmessgaeTaskDao.delete(id);
	}

	@Override
	public WeixinTmessgaeTask queryById(String id) {
		WeixinTmessgaeTask weixinTmessgaeTask  = weixinTmessgaeTaskDao.get(id);
		return weixinTmessgaeTask;
	}

	@Override
	public PageList<WeixinTmessgaeTask> queryPageList(
		PageQuery<WeixinTmessgaeTask> pageQuery) {
		PageList<WeixinTmessgaeTask> result = new PageList<WeixinTmessgaeTask>();
		Integer itemCount = weixinTmessgaeTaskDao.count(pageQuery);
		PageQueryWrapper<WeixinTmessgaeTask> wrapper = new PageQueryWrapper<WeixinTmessgaeTask>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTmessgaeTask> list = weixinTmessgaeTaskDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
