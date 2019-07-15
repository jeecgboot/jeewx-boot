//package com.jeecg.p3.system.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.system.dao.JwSystemAuthMutexDao;
//import com.jeecg.p3.system.entity.JwSystemAuthMutex;
//
///**
// * 描述：</b>JwSystemAuthMutexDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年12月21日 10时28分28秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemAuthMutexDao")
//public class JwSystemAuthMutexDaoImpl extends GenericDaoDefault<JwSystemAuthMutex> implements JwSystemAuthMutexDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemAuthMutex> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemAuthMutex> queryPageList(
//			PageQuery<JwSystemAuthMutex> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemAuthMutex> wrapper = new PageQueryWrapper<JwSystemAuthMutex>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemAuthMutex>)super.query("queryPageList",wrapper);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemAuthMutex> queryAllAuthMutex() {
//		return (List<JwSystemAuthMutex>)super.query("queryAllAuthMutex");
//	}
//
//
//}
//
