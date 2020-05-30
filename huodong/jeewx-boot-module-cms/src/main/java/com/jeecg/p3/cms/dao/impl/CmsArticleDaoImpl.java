//package com.jeecg.p3.cms.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.cms.dao.CmsArticleDao;
//import com.jeecg.p3.cms.entity.CmsArticle;
//
///**
// * 描述：</b>文章管理<br>
// * @author：junfeng.zhou
// * @since：2018年09月25日 17时49分51秒 星期二 
// * @version:1.0
// */
//@Repository("cmsArticleDao")
//public class CmsArticleDaoImpl extends GenericDaoDefault<CmsArticle> implements CmsArticleDao{
//
//	@Override
//	public Integer count(PageQuery<CmsArticle> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsArticle> queryPageList(
//			PageQuery<CmsArticle> pageQuery,Integer itemCount) {
//		PageQueryWrapper<CmsArticle> wrapper = new PageQueryWrapper<CmsArticle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<CmsArticle>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<CmsArticle> getPagesAllMenu(PageQuery<CmsArticle> pageQuery, Integer itemCount, String coulmnId, String ishref) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("query", pageQuery.getQuery());
//		map.put("startRow", pageQuery.getPageNo());
//		map.put("pageSize", pageQuery.getPageSize());
//		map.put("itemCount", itemCount);
//		map.put("columnId", coulmnId);
//		map.put("ishref", ishref);
//		return (List<CmsArticle>)super.query("getPagesAllMenu", map);
//	}
//
//	@Override
//	public Integer newCount(String columnId, String ishref) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("columnId", columnId);
//		map.put("ishref", ishref);
//		return (Integer) super.queryOne("newCount",map);
//	}
//
//	@Override
//	public void updatePublish(String id, String publish) {
//		Map<String,Object> map = new ConcurrentHashMap();
//		map.put("id", id);
//		map.put("publish", publish);
//		super.update("updatePublish", map);
//	}
//
//}
//
