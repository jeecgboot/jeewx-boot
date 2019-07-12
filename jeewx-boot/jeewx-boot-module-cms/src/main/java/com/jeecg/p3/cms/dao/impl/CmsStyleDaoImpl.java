//package com.jeecg.p3.cms.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.cms.dao.CmsStyleDao;
//import com.jeecg.p3.cms.entity.CmsStyle;
//
///**
// * 描述：</b>网站模板管理<br>
// * @author：junfeng.zhou
// * @since：2018年09月25日 17时55分54秒 星期二 
// * @version:1.0
// */
//@Repository("cmsStyleDao")
//public class CmsStyleDaoImpl extends GenericDaoDefault<CmsStyle> implements CmsStyleDao{
//
//	@Override
//	public Integer count(PageQuery<CmsStyle> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsStyle> queryPageList(
//			PageQuery<CmsStyle> pageQuery,Integer itemCount) {
//		PageQueryWrapper<CmsStyle> wrapper = new PageQueryWrapper<CmsStyle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<CmsStyle>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
