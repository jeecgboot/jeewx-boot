//package com.jeecg.p3.goldeneggs.dao.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.OptimisticLockingException;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRegistrationDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
//
///**
// * 描述：</b>WxActGoldeneggsRegistrationDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月07日 18时00分28秒 星期二 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsRegistrationDao")
//public class WxActGoldeneggsRegistrationDaoImpl extends GenericDaoDefault<WxActGoldeneggsRegistration> implements WxActGoldeneggsRegistrationDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsRegistration> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsRegistration> queryPageList(
//			PageQuery<WxActGoldeneggsRegistration> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsRegistration> wrapper = new PageQueryWrapper<WxActGoldeneggsRegistration>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsRegistration>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public WxActGoldeneggsRegistration getOpenid(String openid,String actId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("openid", openid);
//		param.put("actId", actId);
//		return(WxActGoldeneggsRegistration)super.queryOne("getOpenid",param);
//	}
//
//	
//	@Override
//	public void updateNumSameDay(Integer count, Integer numPerDay, String date,String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("id", id);
//		param.put("count", count);
//		param.put("numPerDay", numPerDay);
//		param.put("date", date);
//		int row = super.getSqlSession().update(getStatementId("updateNumSameDay"), param);
//		if (row == 0) {
//			throw new OptimisticLockingException("乐观锁异常");
//		}
//	}
//
//	@Override
//	public void updateNumDistinctDay(Integer count, Integer numPerDay,String date,String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("id", id);
//		param.put("count", count);
//		param.put("numPerDay", numPerDay);
//		param.put("date", date);
//		int row = super.getSqlSession().update(getStatementId("updateNumDistinctDay"), param);
//		if (row == 0) {
//			throw new OptimisticLockingException("乐观锁异常");
//		}
//	}
//	
//	@Override
//	public void updateZeroCountNumSameDay(Integer count, Integer numPerDay, String date,String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("id", id);
//		param.put("numPerDay", numPerDay);
//		param.put("date", date);
//		int row = super.getSqlSession().update(getStatementId("updateZeroCountNumSameDay"), param);
//		if (row == 0) {
//			throw new OptimisticLockingException("乐观锁异常");
//		}
//	}
//	
//	@Override
//	public void updateZeroCountNumDistinctDay(Integer numPerDay,String date,String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("id", id);
//		param.put("numPerDay", numPerDay);
//		param.put("date", date);
//		int row = super.getSqlSession().update(getStatementId("updateZeroCountNumDistinctDay"), param);
//		if (row == 0) {
//			throw new OptimisticLockingException("乐观锁异常");
//		}
//	}
//
//	@Override
//	public List<WxActGoldeneggsRegistration> queryByActId(String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("actId", id);
//		return (List<WxActGoldeneggsRegistration>)super.query("queryByActId",param);
//	}
//
//	@Override
//	public Integer queryCountByActId(String id) {
//		Map<String,Object> param=Maps.newConcurrentMap();
//		param.put("actId", id);
//		return (Integer)super.queryOne("queryCountByActId",param);
//	}
//}
//
