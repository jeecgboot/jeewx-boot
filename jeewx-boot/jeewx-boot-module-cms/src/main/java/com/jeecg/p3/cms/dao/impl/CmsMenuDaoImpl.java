//package com.jeecg.p3.cms.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.cms.dao.CmsMenuDao;
//import com.jeecg.p3.cms.entity.CmsMenu;
//
///**
// * 描述：</b>栏目管理<br>
// * @author：junfeng.zhou
// * @since：2018年09月25日 17时51分57秒 星期二 
// * @version:1.0
// */
//@Repository("cmsMenuDao")
//public class CmsMenuDaoImpl extends GenericDaoDefault<CmsMenu> implements CmsMenuDao{
//
//	@Override
//	public Integer count(PageQuery<CmsMenu> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsMenu> queryPageList(
//			PageQuery<CmsMenu> pageQuery,Integer itemCount) {
//		PageQueryWrapper<CmsMenu> wrapper = new PageQueryWrapper<CmsMenu>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<CmsMenu>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<CmsMenu> getFirstMenu(String mainId, String ishref) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("mainId",mainId);
//		map.put("ishref",ishref);
//		return (List<CmsMenu>)super.query("getFirstMenu", map);
//	}
//
//	@Override
//	public List<CmsMenu> getChildNode(String id) {
//		return (List<CmsMenu>)super.query("getChildNode", id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CmsMenu> queryAllMenus(String mainId) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("mainId", mainId);
//		return (List<CmsMenu>)super.query("queryAllMenus", map);
//	}
//
//	@Override
//	public String getMaxLocalCode(String localCodeLength, String parentCode) {
//		Map map = new ConcurrentHashMap();
//		map.put("localCodeLength", localCodeLength);
//		map.put("parentCode", parentCode);
//		return (String) super.queryOne("getMaxLocalCode", map);
//	}
//}
//
