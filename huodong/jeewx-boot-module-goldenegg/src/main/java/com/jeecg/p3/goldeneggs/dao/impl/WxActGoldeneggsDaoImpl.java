//package com.jeecg.p3.goldeneggs.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
//
///**
// * 描述：</b>WxActGoldeneggsDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月07日 18时00分24秒 星期二 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsDao")
//public class WxActGoldeneggsDaoImpl extends GenericDaoDefault<WxActGoldeneggs> implements WxActGoldeneggsDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggs> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggs> queryPageList(
//			PageQuery<WxActGoldeneggs> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggs> wrapper = new PageQueryWrapper<WxActGoldeneggs>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggs>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public void editShortUrl(String id, String shortUrl) {
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("id", id);
//		param.put("shortUrl", shortUrl);
//		super.update("editShortUrl", param);
//	}
//
//
//
//
//}
//
