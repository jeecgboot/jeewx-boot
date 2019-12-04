package com.jeecg.p3.tmessage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.tmessage.service.WeixinTmessageSendLogService;
import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;
import com.jeecg.p3.tmessage.dao.WeixinTmessageSendLogDao;

/**
 * 描述：</b>发送模板消息日志表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时24分48秒 星期三 
 * @version:1.0
 */
@Service("weixinTmessageSendLogService")
public class WeixinTmessageSendLogServiceImpl implements WeixinTmessageSendLogService {
	@Resource
	private WeixinTmessageSendLogDao weixinTmessageSendLogDao;

	@Override
	public void doAdd(WeixinTmessageSendLog weixinTmessageSendLog) {
		weixinTmessageSendLogDao.insert(weixinTmessageSendLog);
	}

	@Override
	public void doEdit(WeixinTmessageSendLog weixinTmessageSendLog) {
		weixinTmessageSendLogDao.update(weixinTmessageSendLog);
	}

	@Override
	public void doDelete(String id) {
		weixinTmessageSendLogDao.delete(id);
	}

	@Override
	public WeixinTmessageSendLog queryById(String id) {
		WeixinTmessageSendLog weixinTmessageSendLog  = weixinTmessageSendLogDao.get(id);
		return weixinTmessageSendLog;
	}

	@Override
	public PageList<WeixinTmessageSendLog> queryPageList(
		PageQuery<WeixinTmessageSendLog> pageQuery) {
		PageList<WeixinTmessageSendLog> result = new PageList<WeixinTmessageSendLog>();
		Integer itemCount = weixinTmessageSendLogDao.count(pageQuery);
		PageQueryWrapper<WeixinTmessageSendLog> wrapper = new PageQueryWrapper<WeixinTmessageSendLog>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTmessageSendLog> list = weixinTmessageSendLogDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
