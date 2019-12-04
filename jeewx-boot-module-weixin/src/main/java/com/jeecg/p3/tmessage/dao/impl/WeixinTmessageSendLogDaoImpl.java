//package com.jeecg.p3.tmessage.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.tmessage.dao.WeixinTmessageSendLogDao;
//import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;
//
///**
// * 描述：</b>发送模板消息日志表<br>
// * @author：LeeShaoQing
// * @since：2018年11月21日 18时24分48秒 星期三 
// * @version:1.0
// */
//@Repository("weixinTmessageSendLogDao")
//public class WeixinTmessageSendLogDaoImpl extends GenericDaoDefault<WeixinTmessageSendLog> implements WeixinTmessageSendLogDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTmessageSendLog> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTmessageSendLog> queryPageList(
//			PageQuery<WeixinTmessageSendLog> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTmessageSendLog> wrapper = new PageQueryWrapper<WeixinTmessageSendLog>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTmessageSendLog>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
