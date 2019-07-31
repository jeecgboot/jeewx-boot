//package com.jeecg.p3.baseApi.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.persistence.Entity;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.baseApi.dao.BaseApiActTxtDao;
//
//
//@Repository("baseApiActTxtDao")
//public class BaseApiActTxtDaoImpl extends GenericDaoDefault<Entity> implements BaseApiActTxtDao{
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> queryListByActCode(String actCode) {
//		Map<String, String> params=Maps.newConcurrentMap();
//		params.put("actCode", actCode);
//		return (List<Map<String, Object>>)super.getSqlSession().selectList("com.jeecg.p3.baseApi.queryListByActCode", params);
//	}
//	
//	@Override
//	public void batchDeleteByActCode(String actCode) {
//		Map<String, String> params=Maps.newConcurrentMap();
//		params.put("actCode", actCode);
//		super.getSqlSession().delete("com.jeecg.p3.baseApi.batchDeleteByActCode",params);
//	}
//
//	@Override
//	public void insert(Map<String, Object> map) {
//		super.getSqlSession().insert("com.jeecg.p3.baseApi.insert", map);
//	}
//}
//
