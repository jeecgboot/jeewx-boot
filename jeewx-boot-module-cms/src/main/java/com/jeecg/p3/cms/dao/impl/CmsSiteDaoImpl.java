//package com.jeecg.p3.cms.dao.impl;
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
//import com.jeecg.p3.cms.dao.CmsSiteDao;
//import com.jeecg.p3.cms.entity.CmsSite;
//
///**
// * 描述：</b>网站管理<br>
// * @author：junfeng.zhou
// * @since：2018年09月25日 17时53分26秒 星期二 
// * @version:1.0
// */
//@Repository("cmsSiteDao")
//public class CmsSiteDaoImpl extends GenericDaoDefault<CmsSite> implements CmsSiteDao{
//
//	@Override
//	public Integer count(PageQuery<CmsSite> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsSite> queryPageList(
//			PageQuery<CmsSite> pageQuery,Integer itemCount) {
//		PageQueryWrapper<CmsSite> wrapper = new PageQueryWrapper<CmsSite>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<CmsSite>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<CmsSite> getAll() {
//		return (List<CmsSite>)super.query("getAll");
//	}
//
//	@Override
//	public Integer queryByJwidAndCreateBy(String jwid, String createBy) {
//		Map map = new HashMap<String,Object>();
//		map.put("jwid", jwid);
//		map.put("createBy", createBy);
//		return (Integer) super.queryOne("queryByJwidAndCreateBy",map);
//	}
//
//	@Override
//	public CmsSite querySiteByJwidAndCreateBy(String jwid, String createBy) {
//		Map map = new HashMap<String,Object>();
//		map.put("jwid", jwid);
//		map.put("createBy", createBy);
//		return (CmsSite) super.queryOne("querySiteByJwidAndCreateBy",map);
//	}
//}
//
