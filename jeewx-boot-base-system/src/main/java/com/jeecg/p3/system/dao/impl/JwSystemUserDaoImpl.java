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
//import com.jeecg.p3.system.dao.JwSystemUserDao;
//import com.jeecg.p3.system.entity.JwSystemUser;
//import com.jeecg.p3.system.entity.Menu;
//import com.jeecg.p3.system.vo.LoginUser;
//
///**
// * 描述：</b>JwSystemUserDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年12月21日 10时28分29秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemUserDao")
//public class JwSystemUserDaoImpl extends GenericDaoDefault<JwSystemUser> implements JwSystemUserDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemUser> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemUser> queryPageList(
//			PageQuery<JwSystemUser> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemUser> wrapper = new PageQueryWrapper<JwSystemUser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemUser>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<String> queryUserRoles(String userId) {
//		return (List<String>)super.query("queryUserRoles",userId);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Menu> queryUserMenuAuth(List<String> roleIds) {
//		return (List<Menu>)super.query("queryUserMenuAuth",roleIds);
//	}
//	
//	
//	@Override
//	public void insertUserRoleRel(String userId, String roleId) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userId", userId);
//		map.put("roleId", roleId);
//		super.getSqlSession().insert(getStatementId("insertUserRoleRel"), map);
//	}
//
//	@Override
//	public void deleteRolesByUserId(String userId) {
//		this.delete("deleteRolesByUserId", userId);
//	}
//
//	@Override
//	public LoginUser queryUserByUserId(String userId) {
//		return (LoginUser) super.queryOne("queryUserByUserId",userId);
//	}
//
//	@Override
//	public LoginUser queryUserByOpenid(String openid) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("openid", openid);
//		return (LoginUser) super.queryOne("queryUserByOpenid",map);
//	}
//	//update--begin--author: gj_shaojc--date:20180503--------for:增加代理商管理-
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemUser> queryAgentPageList(
//			PageQuery<JwSystemUser> pageQuery, Integer itemCount) {
//		PageQueryWrapper<JwSystemUser> wrapper = new PageQueryWrapper<JwSystemUser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemUser>)super.query("queryAgentPageList",wrapper);
//	}
//	//update--end--author: gj_shaojc--date:20180503--------for:增加代理商管理-
//
//	@Override
//	public String getUserChargeState(String userid) {
//		return (String) super.queryOne("getUserChargeState", userid);
//	}
//}
//
