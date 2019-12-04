package com.jeecg.p3.tmessage.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.tmessage.entity.WeixinTmessgaeTask;

/**
 * 描述：</b>发送模板消息表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时23分28秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessgaeTaskService {
	
	
	public void doAdd(WeixinTmessgaeTask weixinTmessgaeTask);
	
	public void doEdit(WeixinTmessgaeTask weixinTmessgaeTask);
	
	public void doDelete(String id);
	
	public WeixinTmessgaeTask queryById(String id);
	
	public PageList<WeixinTmessgaeTask> queryPageList(PageQuery<WeixinTmessgaeTask> pageQuery);
}

