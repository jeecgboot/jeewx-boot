//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinAutoresponseDao;
//import com.jeecg.p3.weixin.entity.WeixinAutoresponse;
//
///**
// * 描述：</b>关键字表<br>
// * @author：LeeShaoQing
// * @since：2018年07月20日 10时11分12秒 星期五 
// * @version:1.0
// */
//@Repository("weixinAutoresponseDao")
//public class WeixinAutoresponseDaoImpl extends GenericDaoDefault<WeixinAutoresponse> implements WeixinAutoresponseDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinAutoresponse> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinAutoresponse> queryPageList(
//			PageQuery<WeixinAutoresponse> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinAutoresponse> wrapper = new PageQueryWrapper<WeixinAutoresponse>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinAutoresponse>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinAutoresponse> queryByJwid(String jwid) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("jwid",jwid);
//		return (List<WeixinAutoresponse>)super.query("queryByJwid",map);
//	}
//
//
//}
//
