package com.jeecg.p3.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.message.service.WeixinGroupMessageSendLogService;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;
import com.jeecg.p3.message.dao.WeixinGroupMessageSendLogDao;

/**
 * 描述：</b>群发消息日志表<br>
 * @author：weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
@Service("weixinGroupMessageSendLogService")
public class WeixinGroupMessageSendLogServiceImpl implements WeixinGroupMessageSendLogService {
	@Resource
	private WeixinGroupMessageSendLogDao weixinGroupMessageSendLogDao;

	@Override
	public void doAdd(WeixinGroupMessageSendLog weixinGroupMessageSendLog) {
		weixinGroupMessageSendLogDao.insert(weixinGroupMessageSendLog);
	}

	@Override
	public void doEdit(WeixinGroupMessageSendLog weixinGroupMessageSendLog) {
		weixinGroupMessageSendLogDao.update(weixinGroupMessageSendLog);
	}

	@Override
	public void doDelete(String id) {
		weixinGroupMessageSendLogDao.delete(id);
	}

	@Override
	public WeixinGroupMessageSendLog queryById(String id) {
		WeixinGroupMessageSendLog weixinGroupMessageSendLog  = weixinGroupMessageSendLogDao.get(id);
		return weixinGroupMessageSendLog;
	}

	@Override
	public PageList<WeixinGroupMessageSendLog> queryPageList(
		PageQuery<WeixinGroupMessageSendLog> pageQuery) {
		PageList<WeixinGroupMessageSendLog> result = new PageList<WeixinGroupMessageSendLog>();
		Integer itemCount = weixinGroupMessageSendLogDao.count(pageQuery);
		PageQueryWrapper<WeixinGroupMessageSendLog> wrapper = new PageQueryWrapper<WeixinGroupMessageSendLog>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinGroupMessageSendLog> list = weixinGroupMessageSendLogDao.queryPageList(wrapper);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getMsgType().equals("text")){
				if(list.get(i).getParam().length()>15){
					list.get(i).setParam(list.get(i).getParam().substring(0,15)+"...");
				}
			}
		}
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

}
