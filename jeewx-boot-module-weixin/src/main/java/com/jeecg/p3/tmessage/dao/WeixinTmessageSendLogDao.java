package com.jeecg.p3.tmessage.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;

/**
 * 描述：</b>发送模板消息日志表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时24分48秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessageSendLogDao extends GenericDao<WeixinTmessageSendLog>{
	
	public Integer count(PageQuery<WeixinTmessageSendLog> pageQuery);
	
	public List<WeixinTmessageSendLog> queryPageList(PageQueryWrapper<WeixinTmessageSendLog> wrapper);
	
}

