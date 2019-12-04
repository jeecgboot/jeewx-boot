//package com.jeecg.p3.message.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.message.dao.WeixinGroupMessageSendLogDao;
//import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;
//
///**
// * 描述：</b>群发消息日志表<br>
// * @author：weijian.zhang
// * @since：2018年08月03日 14时43分17秒 星期五 
// * @version:1.0
// */
//@Repository("weixinGroupMessageSendLogDao")
//public class WeixinGroupMessageSendLogDaoImpl extends GenericDaoDefault<WeixinGroupMessageSendLog> implements WeixinGroupMessageSendLogDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinGroupMessageSendLog> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinGroupMessageSendLog> queryPageList(
//			PageQuery<WeixinGroupMessageSendLog> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinGroupMessageSendLog> wrapper = new PageQueryWrapper<WeixinGroupMessageSendLog>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinGroupMessageSendLog>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
