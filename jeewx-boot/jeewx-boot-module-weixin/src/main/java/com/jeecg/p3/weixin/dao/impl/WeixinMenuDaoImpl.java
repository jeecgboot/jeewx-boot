//package com.jeecg.p3.weixin.dao.impl;
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
//import com.jeecg.p3.weixin.dao.WeixinMenuDao;
//import com.jeecg.p3.weixin.entity.WeixinMenu;
//
///**
// * 描述：</b>微信菜单表<br>
// * @author：weijian.zhang
// * @since：2018年07月12日 13时58分38秒 星期四 
// * @version:1.0
// */
//@Repository("weixinMenuDao")
//public class WeixinMenuDaoImpl extends GenericDaoDefault<WeixinMenu> implements WeixinMenuDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinMenu> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinMenu> queryPageList(
//			PageQuery<WeixinMenu> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinMenu> wrapper = new PageQueryWrapper<WeixinMenu>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinMenu>)super.query("queryPageList",wrapper);
//	}
//
//	//update-begin--Author:zhangweijian Date:20181017 for：添加jwid参数
//	//根据orders获取父级id
//	@Override
//	public String getFatherIdByorders(String orders, String jwid) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("orders", orders);
//		param.put("jwid", jwid);
//		return (String) super.queryOne("getFatherIdByorders", param);
//	}
//
//	//根据orders查询菜单信息
//	@Override
//	public WeixinMenu queryByOrders(String orders, String jwid) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("orders", orders);
//		param.put("jwid", jwid);
//		return (WeixinMenu) super.queryOne("queryByOrders", param);
//	}
//	//update-end--Author:zhangweijian Date:20181017 for：添加jwid参数
//	//根据fatherId查询其子级菜单
//	@SuppressWarnings("unchecked")
//	@Override
//	public int getSonMenuByFatherId(String fatherId) {
//		return (Integer) super.queryOne("getSonMenuByFatherId", fatherId);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180723 for：获取一级菜单
//	//获取一级菜单
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinMenu> queryMenusByJwid(WeixinMenu query) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("query", query);
//		return super.query("queryMenusByJwid", param);
//	}
//	//update-end--Author:zhangweijian  Date: 20180723 for：获取一级菜单
//
//	//根据菜单KEY和JWID查询到菜单信息
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinMenu> queryMenuByKeyAndJwid(String key, String jwid) {
//		Map<String, Object> param = Maps.newConcurrentMap();
//		param.put("key", key);
//		param.put("jwid", jwid);
//		return (List<WeixinMenu>)super.query("queryMenuByKeyAndJwid", param);
//	}
//
//}
//
