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
//import com.jeecg.p3.system.dao.JwWebJwidDao;
//import com.jeecg.p3.system.entity.JwWebJwid;
//
///**
// * 描述：</b>JwWebJwidDaoImpl<br>
// * @author：pituo
// * @since：2015年12月17日 10时45分06秒 星期四 
// * @version:1.0
// */
//@Repository("jwWebJwidDao")
//public class JwWebJwidDaoImpl extends GenericDaoDefault<JwWebJwid> implements JwWebJwidDao{
//
//	@Override
//	public Integer count(PageQuery<JwWebJwid> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwWebJwid> queryPageList(PageQuery<JwWebJwid> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwWebJwid> wrapper = new PageQueryWrapper<JwWebJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwWebJwid>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwWebJwid> queryJwids() {
//		return (List<JwWebJwid>)super.query("queryJwids");
//	}
//
//	@Override
//	public JwWebJwid queryJwidNameByJwid(String jwid) {
//		return (JwWebJwid)super.queryOne("queryJwidNameByJwid",jwid);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwWebJwid> queryJwWebJwidByUserId(String userId) {
//		return (List<JwWebJwid>)super.query("queryJwWebJwidByUserId",userId);
//	}
//
//
//	/**
//	 * 根据用户编码删除用户和公众号的关联关系
//	 * @param userId
//	 */
//	public void deleteUserJwidByUserid(String userId){
//		this.delete("deleteUserJwidByUserid", userId);
//	}
//
//	@Override
//	public JwWebJwid queryJwidByJwidAndUserId(String jwid, String userId) {
//		Map<String,String> paramMap = new HashMap<String,String>();
//		paramMap.put("jwid", jwid);
//		paramMap.put("userId", userId);
//		return (JwWebJwid)super.queryOne("queryJwidByJwidAndUserId",paramMap);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwWebJwid> queryJwidsNotInUser(String userId) {
//		return (List<JwWebJwid>)super.query("queryJwidsNotInUser",userId);
//	}
//
//	@Override
//	public void insertUserJwidRels(String userId, String jwid) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userId", userId);
//		map.put("jwid", jwid);
//		super.getSqlSession().insert(getStatementId("insertUserJwidRels"), map);
//	}
//
//	@Override
//	public JwWebJwid queryOneByJwid(String jwid) {
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("jwid", jwid);
//		return (JwWebJwid)super.queryOne("queryOneByJwid",map);
//	}
//}
//
