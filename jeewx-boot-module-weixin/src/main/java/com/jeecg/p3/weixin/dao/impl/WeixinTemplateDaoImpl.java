//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinTemplateDao;
//import com.jeecg.p3.weixin.entity.WeixinTemplate;
//
///**
// * 描述：</b>图文样式库表<br>
// * @author：weijian.zhang
// * @since：2018年07月24日 20时01分05秒 星期二 
// * @version:1.0
// */
//@Repository("weixinTemplateDao")
//public class WeixinTemplateDaoImpl extends GenericDaoDefault<WeixinTemplate> implements WeixinTemplateDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTemplate> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTemplate> queryPageList(
//			PageQuery<WeixinTemplate> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTemplate> wrapper = new PageQueryWrapper<WeixinTemplate>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTemplate>)super.query("queryPageList",wrapper);
//	}
//
//	//获取单个样式库
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTemplate> queryByType(String type) {
//		return super.query("queryByType", type);
//	}
//
//
//}
//
