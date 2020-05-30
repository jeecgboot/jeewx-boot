//package com.jeecg.p3.cms.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.cms.dao.CmsAdDao;
//import com.jeecg.p3.cms.entity.CmsAd;
//
///**
// * 描述：</b>广告管理<br>
// * @author：junfeng.zhou
// * @since：2018年09月25日 17时45分31秒 星期二 
// * @version:1.0
// */
//@Repository("cmsAdDao")
//public class CmsAdDaoImpl extends GenericDaoDefault<CmsAd> implements CmsAdDao{
//
//	@Override
//	public Integer count(PageQuery<CmsAd> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsAd> queryPageList(
//			PageQuery<CmsAd> pageQuery,Integer itemCount) {
//		PageQueryWrapper<CmsAd> wrapper = new PageQueryWrapper<CmsAd>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<CmsAd>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<CmsAd> getAll(String mainId) {
//		return (List<CmsAd>)super.query("getAll",mainId);
//	}
//
//}
//
