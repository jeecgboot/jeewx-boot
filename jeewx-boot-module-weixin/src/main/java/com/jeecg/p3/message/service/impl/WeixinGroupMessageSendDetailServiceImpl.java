package com.jeecg.p3.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.message.service.WeixinGroupMessageSendDetailService;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendDetail;
import com.jeecg.p3.message.dao.WeixinGroupMessageSendDetailDao;

/**
 * 描述：</b>群发日志明细表<br>
 * @author：weijian.zhang
 * @since：2018年08月20日 17时49分30秒 星期一 
 * @version:1.0
 */
@Service("weixinGroupMessageSendDetailService")
public class WeixinGroupMessageSendDetailServiceImpl implements WeixinGroupMessageSendDetailService {
	@Resource
	private WeixinGroupMessageSendDetailDao weixinGroupMessageSendDetailDao;

	@Override
	public void doAdd(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail) {
		weixinGroupMessageSendDetailDao.insert(weixinGroupMessageSendDetail);
	}

	@Override
	public void doEdit(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail) {
		weixinGroupMessageSendDetailDao.update(weixinGroupMessageSendDetail);
	}

	@Override
	public void doDelete(String id) {
		weixinGroupMessageSendDetailDao.delete(id);
	}

	@Override
	public WeixinGroupMessageSendDetail queryById(String id) {
		WeixinGroupMessageSendDetail weixinGroupMessageSendDetail  = weixinGroupMessageSendDetailDao.get(id);
		return weixinGroupMessageSendDetail;
	}

	@Override
	public PageList<WeixinGroupMessageSendDetail> queryPageList(
		PageQuery<WeixinGroupMessageSendDetail> pageQuery) {
		PageList<WeixinGroupMessageSendDetail> result = new PageList<WeixinGroupMessageSendDetail>();
		Integer itemCount = weixinGroupMessageSendDetailDao.count(pageQuery);
		PageQueryWrapper<WeixinGroupMessageSendDetail> wrapper = new PageQueryWrapper<WeixinGroupMessageSendDetail>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinGroupMessageSendDetail> list = weixinGroupMessageSendDetailDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	/**
	 * @功能：根据群发日志id查询明细表信息
	 */
	@Override
	public WeixinGroupMessageSendDetail queryBysendLogId(String sendLogId) {
		return weixinGroupMessageSendDetailDao.queryBysendLogId(sendLogId);
	}

	/**
	 * @功能：根据jwid和msgid查询群发日志明细表信息
	 */
	@Override
	public List<WeixinGroupMessageSendDetail> queryByMsgId(String msgid,String jwid) {
		return weixinGroupMessageSendDetailDao.queryByMsgId(msgid,jwid);
	}
	
}
