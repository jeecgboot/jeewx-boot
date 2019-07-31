//package com.jeecg.p3.baseApi.dao.impl;
//
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.persistence.Entity;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.baseApi.dao.BaseApiJwidDao;
//
//
//
///**
// * 获取二维码
// * @author huangqingquan
// *
// */
//@Repository("baseApiJwidDao")
//public class BaseApiJwidDaoImpl extends GenericDaoDefault<Entity> implements BaseApiJwidDao{
//
//	@Override
//	public String queryOneByJwid(String jwid) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("jwid", jwid);
//		return (String)super.getSqlSession().selectOne("com.jeecg.p3.baseApi.queryOneByJwid", param);
//	}
//}
//
