package com.jeecg.p3.message.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;

/**
 * 描述：</b>群发消息日志表<br>
 * @author：weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
public interface WeixinGroupMessageSendLogDao extends GenericDao<WeixinGroupMessageSendLog>{
	
	public Integer count(PageQuery<WeixinGroupMessageSendLog> pageQuery);
	
	public List<WeixinGroupMessageSendLog> queryPageList(PageQueryWrapper<WeixinGroupMessageSendLog> wrapper);
	
}

