package com.jeecg.p3.tmessage.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;

/**
 * 描述：</b>发送模板消息日志表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时24分48秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessageSendLogService {
	
	
	public void doAdd(WeixinTmessageSendLog weixinTmessageSendLog);
	
	public void doEdit(WeixinTmessageSendLog weixinTmessageSendLog);
	
	public void doDelete(String id);
	
	public WeixinTmessageSendLog queryById(String id);
	
	public PageList<WeixinTmessageSendLog> queryPageList(PageQuery<WeixinTmessageSendLog> pageQuery);
}

