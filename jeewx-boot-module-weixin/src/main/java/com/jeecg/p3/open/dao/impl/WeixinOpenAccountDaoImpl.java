//package com.jeecg.p3.commonweixin.dao.impl;
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
//import com.jeecg.p3.open.dao.WeixinOpenAccountDao;
//import com.jeecg.p3.open.entity.WeixinOpenAccount;
//
//@Repository("weixinOpenAccountDao")
//public class WeixinOpenAccountDaoImpl extends GenericDaoDefault<WeixinOpenAccount> implements WeixinOpenAccountDao{
//	@Override
//	public WeixinOpenAccount queryOneByAppid(String appid) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("appid", appid);
//		return (WeixinOpenAccount)super.queryOne("queryOneByAppid", param);
//	}
//	@Override
//	public Integer count(PageQuery<WeixinOpenAccount> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinOpenAccount> queryPageList(
//			PageQuery<WeixinOpenAccount> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinOpenAccount> wrapper = new PageQueryWrapper<WeixinOpenAccount>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinOpenAccount>)super.query("queryPageList",wrapper);
//	}
//}
//
