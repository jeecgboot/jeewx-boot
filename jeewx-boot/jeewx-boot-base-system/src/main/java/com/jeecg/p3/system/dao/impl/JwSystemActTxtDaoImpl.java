//package com.jeecg.p3.system.dao.impl;
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
//import com.jeecg.p3.system.dao.JwSystemActTxtDao;
//import com.jeecg.p3.system.entity.JwSystemActTxt;
//
///**
// * 描述：</b>JwSystemActTxtDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2015年11月11日 11时11分51秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemActTxtDao")
//public class JwSystemActTxtDaoImpl extends GenericDaoDefault<JwSystemActTxt> implements JwSystemActTxtDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemActTxt> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemActTxt> queryPageList(
//			PageQuery<JwSystemActTxt> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemActTxt> wrapper = new PageQueryWrapper<JwSystemActTxt>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemActTxt>)super.query("queryPageList",wrapper);
//	}
//	
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemActTxt> queryByActCode(String actCode){
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("actCode", actCode);
//		return (List<JwSystemActTxt>)super.query("queryByActCode",param);
//	}
//}
//
