//package com.jeecg.p3.baseApi.dao.impl;
//
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.persistence.Entity;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.baseApi.dao.BaseApiSystemDao;
//
//@Repository("baseApiSystemDao")
//public class BaseApiSystemDaoImpl extends GenericDaoDefault<Entity> implements BaseApiSystemDao {
//
//	@Override
//	public String getHuodongLogoBottomCopyright() {
//		return (String)super.getSqlSession().selectOne("com.jeecg.p3.baseApi.system.getHuodongLogoBottomCopyright");
//	}
//
//	/**
//	 * 查询用户是否可以个性化设置logo
//	 * @param jwid
//	 * @return
//	 */
//	@Override
//	public int isUserLogSet(String userId) {
//		return (Integer) super.getSqlSession().selectOne("com.jeecg.p3.baseApi.system.isUserLogSet",userId);
//	}
//
//	/**
//	 * 查询用户授权状态
//	 * @param jwid
//	 * @return
//	 */
//	@Override
//	public Map<String, Object> getUserAuthorized(String userId) {
//		return (Map<String, Object>) super.getSqlSession().selectOne("com.jeecg.p3.baseApi.system.getUserAuthorized",userId);
//	}
//
//	@Override
//	public boolean isBrushTicket(String registrationId) {
//		Integer i =  (Integer) super.getSqlSession().selectOne("com.jeecg.p3.baseApi.system.getBrushTicketCount",registrationId);
//		if(i!=0){
//			return true;
//		}
//		return false;
//	}
//
//}
