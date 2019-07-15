//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinTexttemplateDao;
//import com.jeecg.p3.weixin.entity.WeixinTexttemplate;
//
///**
// * 描述：</b>文本模板表<br>
// * @author：weijian.zhang
// * @since：2018年07月13日 12时45分36秒 星期五 
// * @version:1.0
// */
//@Repository("weixinTexttemplateDao")
//public class WeixinTexttemplateDaoImpl extends GenericDaoDefault<WeixinTexttemplate> implements WeixinTexttemplateDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTexttemplate> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTexttemplate> queryPageList(
//			PageQuery<WeixinTexttemplate> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTexttemplate> wrapper = new PageQueryWrapper<WeixinTexttemplate>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTexttemplate>)super.query("queryPageList",wrapper);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTexttemplate> getAllTemplate(String jwid) {
//		return super.query("getAllTemplate",jwid);
//	}
//	//update-begin--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
//
//}
//
