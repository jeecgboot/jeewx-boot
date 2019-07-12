//package com.jeecg.p3.system.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.system.dao.JwSystemProjectErrorConfigDao;
//import com.jeecg.p3.system.entity.JwSystemProjectErrorConfig;
//
///**
// * 描述：</b>JwSystemProjectErrorConfigDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年02月24日 10时21分06秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemProjectErrorConfigDao")
//public class JwSystemProjectErrorConfigDaoImpl extends GenericDaoDefault<JwSystemProjectErrorConfig> implements JwSystemProjectErrorConfigDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemProjectErrorConfig> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemProjectErrorConfig> queryPageList(
//			PageQuery<JwSystemProjectErrorConfig> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemProjectErrorConfig> wrapper = new PageQueryWrapper<JwSystemProjectErrorConfig>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemProjectErrorConfig>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
