package com.jeecg.p3.message.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendDetail;

/**
 * 描述：</b>群发日志明细表<br>
 * @author：weijian.zhang
 * @since：2018年08月20日 17时49分30秒 星期一 
 * @version:1.0
 */
public interface WeixinGroupMessageSendDetailService {
	
	
	public void doAdd(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail);
	
	public void doEdit(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail);
	
	public void doDelete(String id);
	
	public WeixinGroupMessageSendDetail queryById(String id);
	
	public PageList<WeixinGroupMessageSendDetail> queryPageList(PageQuery<WeixinGroupMessageSendDetail> pageQuery);

	/**
	 * @功能：根据群发日志id查询明细表信息
	 * @param sendLogId
	 * @return
	 */
	public WeixinGroupMessageSendDetail queryBysendLogId(String sendLogId);

	/**
	 * @功能：根据jwid和msgid查询群发日志明细表信息
	 * @param msgid
	 * @param jwid
	 * @return
	 */
	public List<WeixinGroupMessageSendDetail> queryByMsgId(String msgid,String jwid);
}

