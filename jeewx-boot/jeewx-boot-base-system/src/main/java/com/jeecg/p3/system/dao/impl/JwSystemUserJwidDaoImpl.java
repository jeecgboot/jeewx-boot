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
//import com.jeecg.p3.system.dao.JwSystemUserJwidDao;
//import com.jeecg.p3.system.entity.JwSystemUserJwid;
//
///**
// * 描述：</b>JwSystemUserJwidDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年12月23日 16时02分42秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemUserJwidDao")
//public class JwSystemUserJwidDaoImpl extends GenericDaoDefault<JwSystemUserJwid> implements JwSystemUserJwidDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemUserJwid> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemUserJwid> queryPageList(
//			PageQuery<JwSystemUserJwid> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemUserJwid> wrapper = new PageQueryWrapper<JwSystemUserJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemUserJwid>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public JwSystemUserJwid queryOneByUserIdAndDefaultFlag(String userId,
//			String defaultFlag) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("userId", userId);
//		param.put("defaultFlag", defaultFlag);
//		return (JwSystemUserJwid)super.queryOne("queryOneByUserIdAndDefaultFlag",param);
//	}
//
//
//}
//
