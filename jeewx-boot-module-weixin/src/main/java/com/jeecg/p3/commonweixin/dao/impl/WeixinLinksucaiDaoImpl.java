//package com.jeecg.p3.commonweixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.commonweixin.dao.WeixinLinksucaiDao;
//import com.jeecg.p3.commonweixin.entity.WeixinLinksucai;
//
///**
// * 描述：</b>WeixinLinksucaiDaoImpl<br>
// * @author：chen
// * @since：2016年11月14日 10时16分24秒 星期一 
// * @version:1.0
// */
//@Repository("weixinLinksucaiDao")
//public class WeixinLinksucaiDaoImpl extends GenericDaoDefault<WeixinLinksucai> implements WeixinLinksucaiDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinLinksucai> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinLinksucai> queryPageList(
//			PageQuery<WeixinLinksucai> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinLinksucai> wrapper = new PageQueryWrapper<WeixinLinksucai>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinLinksucai>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
