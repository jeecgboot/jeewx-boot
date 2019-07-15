//package com.jeecg.p3.system.dao.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import java.util.Map;
//import com.google.common.collect.Maps;
//import com.jeecg.p3.system.dao.JwSystemActDao;
//import com.jeecg.p3.system.entity.JwSystemAct;
//
///**
// * 描述：</b>平台活动表<br>
// * @author：Alex
// * @since：2017年09月30日 10时05分08秒 星期六 
// * @version:1.0
// */
//@Repository("jwSystemActDao")
//public class JwSystemActDaoImpl extends GenericDaoDefault<JwSystemAct> implements JwSystemActDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemAct> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemAct> queryPageList(
//			PageQuery<JwSystemAct> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemAct> wrapper = new PageQueryWrapper<JwSystemAct>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemAct>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public void updateJoinShareNum(String actId){
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		super.update("updateJoinShareNum",param);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
//	@Override
//	public List<JwSystemAct> queryByJoinNum(int joinNum,Date date) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("date", date);
//		param.put("joinNum", joinNum);
//		return super.query("queryByJoinNum",param);
//	}
//	//update-end--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
//}
//
