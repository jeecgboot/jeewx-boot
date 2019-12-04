//package com.jeecg.p3.message.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.message.dao.WeixinGroupMessageSendDetailDao;
//import com.jeecg.p3.message.entity.WeixinGroupMessageSendDetail;
//
///**
// * 描述：</b>群发日志明细表<br>
// * @author：weijian.zhang
// * @since：2018年08月20日 17时49分30秒 星期一 
// * @version:1.0
// */
//@Repository("weixinGroupMessageSendDetailDao")
//public class WeixinGroupMessageSendDetailDaoImpl extends GenericDaoDefault<WeixinGroupMessageSendDetail> implements WeixinGroupMessageSendDetailDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinGroupMessageSendDetail> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinGroupMessageSendDetail> queryPageList(
//			PageQuery<WeixinGroupMessageSendDetail> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinGroupMessageSendDetail> wrapper = new PageQueryWrapper<WeixinGroupMessageSendDetail>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinGroupMessageSendDetail>)super.query("queryPageList",wrapper);
//	}
//
//	/**
//	 * @功能：根据群发日志id查询明细表信息
//	 */
//	@Override
//	public WeixinGroupMessageSendDetail queryBysendLogId(String sendLogId) {
//		return (WeixinGroupMessageSendDetail) super.queryOne("queryBysendLogId", sendLogId);
//	}
//
//	/**
//	 * @功能：根据jwid和msgid查询群发日志明细表信息
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinGroupMessageSendDetail> queryByMsgId(String msgid,String jwid) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("msgid", msgid);
//		param.put("jwid", jwid);
//		return super.query("queryByMsgId", param);
//	}
//
//
//}
//
