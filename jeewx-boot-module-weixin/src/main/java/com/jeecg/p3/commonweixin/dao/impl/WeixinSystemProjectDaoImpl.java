//package com.jeecg.p3.commonweixin.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.common.StringUtils;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.commonweixin.dao.WeixinSystemProjectDao;
//import com.jeecg.p3.commonweixin.entity.WeixinSystemProject;
//
///**
// * 描述：</b>WeixinSystemProjectDaoImpl<br>
// * @author：pituo
// * @since：2015年12月21日 17时49分18秒 星期一 
// * @version:1.0
// */
//@Repository("weixinSystemProjectDao")
//public class WeixinSystemProjectDaoImpl extends GenericDaoDefault<WeixinSystemProject> implements WeixinSystemProjectDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinSystemProject> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinSystemProject> queryPageList(
//			PageQuery<WeixinSystemProject> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinSystemProject> wrapper = new PageQueryWrapper<WeixinSystemProject>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinSystemProject>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public void editHdurl(String tableName, String hdurlName, String jwidName,
//			String shortUrlName, String linksucai) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("tableName", tableName);
//		param.put("hdurlName",hdurlName);
//		param.put("jwidName",jwidName);
//		if(StringUtils.isNotEmpty(shortUrlName)){
//			param.put("shortUrlName",shortUrlName);
//		}
//		param.put("linksucai",linksucai);
//		super.update("editHdurl",param);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Map<String,String>> queryAllActByTableName(String tableName) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("tableName", tableName);
//		return (List<Map<String,String>>)super.query("queryAllActByTableName",param);
//	}
//
//	@Override
//	public void doEditShortByTableName(String tableName, String actId,String shortUrl) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("tableName", tableName);
//		param.put("actId", actId);
//		param.put("shortUrl", shortUrl);
//		super.update("doEditShortByTableName",param);
//	}
//
//	@Override
//	public void doEditShortEmpty(String tableName) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("tableName", tableName);
//		super.update("doEditShortEmpty",param);
//	}
//}
//
