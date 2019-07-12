//package com.jeecg.p3.system.dao.impl;
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
//import com.jeecg.p3.system.dao.JwSystemAuthDao;
//import com.jeecg.p3.system.entity.Auth;
//import com.jeecg.p3.system.entity.JwSystemAuth;
//import com.jeecg.p3.system.entity.Menu;
//import com.jeecg.p3.system.entity.MenuFunction;
//
///**
// * 描述：</b>JwSystemAuthDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年12月21日 10时28分27秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemAuthDao")
//public class JwSystemAuthDaoImpl extends GenericDaoDefault<JwSystemAuth> implements JwSystemAuthDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemAuth> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemAuth> queryPageList(
//			PageQuery<JwSystemAuth> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemAuth> wrapper = new PageQueryWrapper<JwSystemAuth>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemAuth>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MenuFunction> queryMenuAndFuncAuth() {
//		return (List<MenuFunction>)super.query("queryMenuAndFuncAuth");
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<MenuFunction> queryMenuAndFuncAuthByRoleId(String roleId) {
//		return (List<MenuFunction>)super.query("queryMenuAndFuncAuthByRoleId",roleId);
//	}
//
//	@Override
//	public Menu queryMenuByAuthId(String authId) {
//		return (Menu)super.queryOne("queryMenuByAuthId",authId);
//	}
//
//	@Override
//	public void deleteRoleAuthRels(String roleId) {
//		super.delete("deleteRoleAuthRels", roleId);
//	}
//
//	@Override
//	public void insertRoleAuthRels(String roleId, String authId) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("roleId", roleId);
//		map.put("authId", authId);
//		super.getSqlSession().insert(getStatementId("insertRoleAuthRels"), map);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Menu> queryMenuByUserIdAndParentAuthId(String userId,
//			String parentAuthId) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userId", userId);
//		map.put("parentAuthId", parentAuthId);
//		return (List<Menu>)super.query("queryMenuByUserIdAndParentAuthId",map);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Auth> queryAuthByUserId(String userId) {
//		return (List<Auth>)super.query("queryAuthByUserId",userId);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Auth> queryAuthByAuthContr(String authContr) {
//		return (List<Auth>)super.query("queryAuthByAuthContr",authContr);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Auth> queryAuthByUserIdAndAuthContr(String userId,
//			String authContr) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userId", userId);
//		map.put("authContr", authContr);
//		return (List<Auth>)super.query("queryAuthByUserIdAndAuthContr",map);
//	}
//
//	@Override
//	public JwSystemAuth queryOneByAuthId(String authId) {
//		Map<String,String> param = new HashMap<String,String>();
//		param.put("authId",authId);
//		return (JwSystemAuth)super.queryOne("queryOneByAuthId",param);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20181107 for：查询父级下面的所有子集元素
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemAuth> queryByParentAuthId(String parentAuthId) {
//		return super.query("queryByParentAuthId",parentAuthId);
//	}
//	//update-end--Author:zhangweijian  Date: 20181107 for：查询父级下面的所有子集元素
//}
//
