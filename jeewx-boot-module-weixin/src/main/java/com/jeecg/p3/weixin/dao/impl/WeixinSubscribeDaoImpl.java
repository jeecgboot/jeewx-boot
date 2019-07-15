//package com.jeecg.p3.weixin.dao.impl;
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
//import com.jeecg.p3.weixin.dao.WeixinSubscribeDao;
//import com.jeecg.p3.weixin.entity.WeixinSubscribe;
//
///**
// * 描述：</b>关注欢迎语<br>
// * @author：LeeShaoQing
// * @since：2018年07月20日 10时12分09秒 星期五 
// * @version:1.0
// */
//@Repository("weixinSubscribeDao")
//public class WeixinSubscribeDaoImpl extends GenericDaoDefault<WeixinSubscribe> implements WeixinSubscribeDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinSubscribe> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinSubscribe> queryPageList(
//			PageQuery<WeixinSubscribe> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinSubscribe> wrapper = new PageQueryWrapper<WeixinSubscribe>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinSubscribe>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public WeixinSubscribe querySubscribeByJwid(String jwid) {
//		Map<String, Object> param = Maps.newConcurrentMap();
//		param.put("jwid", jwid);
//		return (WeixinSubscribe)super.queryOne("querySubscribeByJwid", param);
//	}
//
//
//}
//
