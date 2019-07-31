//package com.jeecg.p3.goldeneggs.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsAwardsDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
//
///**
// * 描述：</b>WxActGoldeneggsAwardsDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月07日 18时00分26秒 星期二 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsAwardsDao")
//public class WxActGoldeneggsAwardsDaoImpl extends GenericDaoDefault<WxActGoldeneggsAwards> implements WxActGoldeneggsAwardsDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsAwards> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsAwards> queryPageList(
//			PageQuery<WxActGoldeneggsAwards> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsAwards> wrapper = new PageQueryWrapper<WxActGoldeneggsAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsAwards>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<WxActGoldeneggsAwards> queryAwards(String jwid,String createBy) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("jwid", jwid);
//		param.put("createBy", createBy);
//		return (List<WxActGoldeneggsAwards>)super.query("queryAwards", param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsAwards> queryAwards(String jwid) {
//		return super.query("queryAwards2",jwid);
//	}
//
//	@Override
//	public List<WxActGoldeneggsAwards> queryAwardsByName(String jwid, String createBy, String name) {
//		Map<String, Object> param=new HashMap<String, Object>();
//		param.put("jwid", jwid);
//		param.put("createBy", createBy);
//		param.put("name", name);
//		return super.query("queryAwardsByName", param);
//	}
//
//
//}
//
