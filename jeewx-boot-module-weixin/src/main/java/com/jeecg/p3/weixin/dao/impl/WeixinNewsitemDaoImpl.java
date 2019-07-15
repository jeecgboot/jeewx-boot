//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinNewsitemDao;
//import com.jeecg.p3.weixin.entity.WeixinNewsitem;
//
///**
// * 描述：</b>图文模板素材表<br>
// * @author：weijian.zhang
// * @since：2018年07月13日 12时46分36秒 星期五 
// * @version:1.0
// */
//@Repository("weixinNewsitemDao")
//public class WeixinNewsitemDaoImpl extends GenericDaoDefault<WeixinNewsitem> implements WeixinNewsitemDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinNewsitem> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinNewsitem> queryPageList(
//			PageQuery<WeixinNewsitem> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinNewsitem> wrapper = new PageQueryWrapper<WeixinNewsitem>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinNewsitem>)super.query("queryPageList",wrapper);
//	}
//
//	//根据newstemplateId获取模板素材
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinNewsitem> queryByNewstemplateId(String newstemplateId) {
//		return super.query("queryByNewstemplateId", newstemplateId);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
//	//获取素材最大序号
//	@Override
//	public String getMaxOrderNo(String newstemplateId) {
//		return (String) super.queryOne("getMaxOrderNo", newstemplateId);
//	}
//	//update-end--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
//
//}
//
