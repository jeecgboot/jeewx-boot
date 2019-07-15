//package com.jeecg.p3.system.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.system.dao.JwSystemLogoTitleDao;
//import com.jeecg.p3.system.entity.JwSystemLogoTitle;
//
///**
// * 描述：</b>系统logo、title、head和footer设置表<br>
// * @author：liwenhui
// * @since：2017年08月30日 18时15分25秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemLogoTitleDao")
//public class JwSystemLogoTitleDaoImpl extends GenericDaoDefault<JwSystemLogoTitle> implements JwSystemLogoTitleDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemLogoTitle> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemLogoTitle> queryPageList(
//			PageQuery<JwSystemLogoTitle> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemLogoTitle> wrapper = new PageQueryWrapper<JwSystemLogoTitle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemLogoTitle>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public JwSystemLogoTitle queryByProp(JwSystemLogoTitle query) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("query", query);
//		return (JwSystemLogoTitle) super.queryOne("queryByProp", map);
//	}
//
//
//}
//
