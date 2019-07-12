//package com.jeecg.p3.system.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.system.dao.JwSystemRoleDao;
//import com.jeecg.p3.system.entity.JwSystemRole;
//
///**
// * 描述：</b>JwSystemRoleDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年12月21日 10时28分28秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemRoleDao")
//public class JwSystemRoleDaoImpl extends GenericDaoDefault<JwSystemRole> implements JwSystemRoleDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemRole> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemRole> queryPageList(
//			PageQuery<JwSystemRole> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemRole> wrapper = new PageQueryWrapper<JwSystemRole>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemRole>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemRole> queryAllRoleList() {
//		return (List<JwSystemRole>)super.query("queryAllRoleList");	
//	}
//
//
//}
//
