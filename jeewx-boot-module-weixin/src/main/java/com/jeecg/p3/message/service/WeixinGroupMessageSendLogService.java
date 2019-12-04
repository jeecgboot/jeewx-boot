package com.jeecg.p3.message.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;

/**
 * 描述：</b>群发消息日志表<br>
 * @author：weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
public interface WeixinGroupMessageSendLogService {
	
	
	public void doAdd(WeixinGroupMessageSendLog weixinGroupMessageSendLog);
	
	public void doEdit(WeixinGroupMessageSendLog weixinGroupMessageSendLog);
	
	public void doDelete(String id);
	
	public WeixinGroupMessageSendLog queryById(String id);
	
	public PageList<WeixinGroupMessageSendLog> queryPageList(PageQuery<WeixinGroupMessageSendLog> pageQuery);

}

