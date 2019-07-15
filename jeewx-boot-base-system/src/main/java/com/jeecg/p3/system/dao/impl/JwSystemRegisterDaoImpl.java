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
//import com.jeecg.p3.system.dao.JwSystemRegisterDao;
//import com.jeecg.p3.system.entity.JwSystemProject;
//import com.jeecg.p3.system.entity.JwSystemRegister;
//
///**
// * 描述：</b>JwSystemRegisterDaoImpl<br>
// * @author：alex
// * @since：2016年03月23日 18时07分59秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemRegisterDao")
//public class JwSystemRegisterDaoImpl extends GenericDaoDefault<JwSystemRegister> implements JwSystemRegisterDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemRegister> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemRegister> queryPageList(
//			PageQuery<JwSystemRegister> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemRegister> wrapper = new PageQueryWrapper<JwSystemRegister>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemRegister>)super.query("queryPageList",wrapper);
//	}
//
//	public List<JwSystemRegister> queryByProperty(JwSystemRegister property){
//		Map<String,String> param = Maps.newConcurrentMap();
//		if(property.getAuthstring()!=null){
//			param.put("authstring", property.getAuthstring());
//		}
//		if(property.getEmail()!=null){
//			param.put("email", property.getEmail());
//		}
//		return (List<JwSystemRegister>)super.query("queryByProperty",param);
//	}
//
//}
//
