//package com.jeecg.p3.tmessage.dao.impl;
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
//import com.jeecg.p3.tmessage.dao.WeixinTmessageDao;
//import com.jeecg.p3.tmessage.entity.WeixinTmessage;
//
///**
// * 描述：</b>消息模板表<br>
// * @author：LeeShaoQing
// * @since：2018年11月21日 18时21分04秒 星期三 
// * @version:1.0
// */
//@Repository("weixinTmessageDao")
//public class WeixinTmessageDaoImpl extends GenericDaoDefault<WeixinTmessage> implements WeixinTmessageDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTmessage> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTmessage> queryPageList(
//			PageQuery<WeixinTmessage> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTmessage> wrapper = new PageQueryWrapper<WeixinTmessage>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTmessage>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTmessage> queryByJwid(String jwid) {
//		Map<String,Object> map = Maps.newConcurrentMap();
//		map.put("jwid", jwid);
//		return (List<WeixinTmessage>)super.query("queryByJwid", map);
//	}
//
//	@Override
//	public WeixinTmessage queryByTemplateId(String templateId) {
//		Map<String,Object> map = Maps.newConcurrentMap();
//		map.put("templateId", templateId);
//		return (WeixinTmessage) super.queryOne("queryByTemplateId", map);
//	}
//
//
//}
//
