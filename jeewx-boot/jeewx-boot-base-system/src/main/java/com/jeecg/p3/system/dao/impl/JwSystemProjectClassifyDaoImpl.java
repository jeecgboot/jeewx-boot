//package com.jeecg.p3.system.dao.impl;
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
//import com.jeecg.p3.system.dao.JwSystemProjectClassifyDao;
//import com.jeecg.p3.system.entity.JwSystemProjectClassify;
//
///**
// * 描述：</b>JwSystemProjectClassifyDaoImpl<br>
// * @author：huangqingquan
// * @since：2016年08月08日 09时33分31秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemProjectClassifyDao")
//public class JwSystemProjectClassifyDaoImpl extends GenericDaoDefault<JwSystemProjectClassify> implements JwSystemProjectClassifyDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemProjectClassify> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemProjectClassify> queryPageList(
//			PageQuery<JwSystemProjectClassify> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemProjectClassify> wrapper = new PageQueryWrapper<JwSystemProjectClassify>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemProjectClassify>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemProjectClassify> queryAllByType(String type) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("type", type);
//		return (List<JwSystemProjectClassify>)super.query("queryAllByType",param);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemProjectClassify> queryAllByBaseId(String baseId) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("baseId", baseId);
//		return (List<JwSystemProjectClassify>)super.query("queryAllByBaseId",param);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemProjectClassify> queryListByAll() {
//		return (List<JwSystemProjectClassify>)super.query("queryListByAll");
//	}
//}
//
