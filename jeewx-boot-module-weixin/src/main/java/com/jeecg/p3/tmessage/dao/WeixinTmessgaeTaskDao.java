package com.jeecg.p3.tmessage.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.tmessage.entity.WeixinTmessgaeTask;

/**
 * 描述：</b>发送模板消息表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时23分28秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessgaeTaskDao extends GenericDao<WeixinTmessgaeTask>{
	
	public Integer count(PageQuery<WeixinTmessgaeTask> pageQuery);
	
	public List<WeixinTmessgaeTask> queryPageList(PageQueryWrapper<WeixinTmessgaeTask> wrapper);
	
}

