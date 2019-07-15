//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinReceivetextDao;
//import com.jeecg.p3.weixin.entity.WeixinReceivetext;
//
///**
// * 描述：</b>消息存储<br>
// * @author：LeeShaoQing
// * @since：2018年07月25日 16时02分13秒 星期三 
// * @version:1.0
// */
//@Repository("weixinReceivetextDao")
//public class WeixinReceivetextDaoImpl extends GenericDaoDefault<WeixinReceivetext> implements WeixinReceivetextDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinReceivetext> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinReceivetext> queryPageList(
//			PageQuery<WeixinReceivetext> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinReceivetext> wrapper = new PageQueryWrapper<WeixinReceivetext>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinReceivetext>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<Map<String, Object>> queryAllChatLog(
//			WeixinReceivetext weixinReceivetext) {
//		Map<String,Object> m=new HashMap<String,Object>();
//		m.put("fromUserName", weixinReceivetext.getFromUserName());
//		m.put("toUserName", weixinReceivetext.getToUserName());
//		if(weixinReceivetext.getInRecentTime() !=null){
//			m.put("inRecentTime", weixinReceivetext.getInRecentTime());
//		}
//		return super.query("queryAllChatLog",m);
//	}
//
//	@Override
//	public List<Map<String, Object>> queryMoreChatLog(
//			WeixinReceivetext weixinReceivetext, String firstRecordTime) {
//		Map<String,Object> m=new HashMap<String,Object>();
//		m.put("fromUserName", weixinReceivetext.getFromUserName());
//		m.put("toUserName", weixinReceivetext.getToUserName());
//		if(firstRecordTime !=null){
//			m.put("firstRecordTime", firstRecordTime);
//		}
//		return super.query("queryMoreChatLog",m);
//	}
//
//
//}
//
