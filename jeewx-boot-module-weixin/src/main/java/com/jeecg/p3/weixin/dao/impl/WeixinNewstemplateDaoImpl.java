//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.google.common.collect.Maps;
//import com.jeecg.p3.weixin.dao.WeixinNewstemplateDao;
//import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
//
///**
// * 描述：</b>图文模板表<br>
// * @author：weijian.zhang
// * @since：2018年07月13日 12时46分13秒 星期五 
// * @version:1.0
// */
//@Repository("weixinNewstemplateDao")
//public class WeixinNewstemplateDaoImpl extends GenericDaoDefault<WeixinNewstemplate> implements WeixinNewstemplateDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinNewstemplate> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinNewstemplate> queryPageList(
//			PageQuery<WeixinNewstemplate> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinNewstemplate> wrapper = new PageQueryWrapper<WeixinNewstemplate>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinNewstemplate>)super.query("queryPageList",wrapper);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180820 for：获取所有图文素材
//	/**
//	 * @功能：获取所有图文素材
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinNewstemplate> getAllItems(String jwid, String uploadType) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("jwid", jwid);
//		param.put("uploadType", uploadType);
//		return super.query("getAllItems",param);
//	}
//	//update-end--Author:zhangweijian  Date: 20180820 for：获取所有图文素材
//
//}
//
