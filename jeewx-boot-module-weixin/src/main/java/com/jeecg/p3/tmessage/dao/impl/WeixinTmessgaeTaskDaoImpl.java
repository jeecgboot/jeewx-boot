//package com.jeecg.p3.tmessage.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.tmessage.dao.WeixinTmessgaeTaskDao;
//import com.jeecg.p3.tmessage.entity.WeixinTmessgaeTask;
//
///**
// * 描述：</b>发送模板消息表<br>
// * @author：LeeShaoQing
// * @since：2018年11月21日 18时23分28秒 星期三 
// * @version:1.0
// */
//@Repository("weixinTmessgaeTaskDao")
//public class WeixinTmessgaeTaskDaoImpl extends GenericDaoDefault<WeixinTmessgaeTask> implements WeixinTmessgaeTaskDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTmessgaeTask> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTmessgaeTask> queryPageList(
//			PageQuery<WeixinTmessgaeTask> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTmessgaeTask> wrapper = new PageQueryWrapper<WeixinTmessgaeTask>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTmessgaeTask>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
