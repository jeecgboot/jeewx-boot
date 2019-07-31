//package com.jeecg.p3.goldeneggs.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsPrizesDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
//
///**
// * 描述：</b>WxActGoldeneggsPrizesDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月07日 18时00分27秒 星期二 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsPrizesDao")
//public class WxActGoldeneggsPrizesDaoImpl extends GenericDaoDefault<WxActGoldeneggsPrizes> implements WxActGoldeneggsPrizesDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsPrizes> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsPrizes> queryPageList(
//			PageQuery<WxActGoldeneggsPrizes> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsPrizes> wrapper = new PageQueryWrapper<WxActGoldeneggsPrizes>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsPrizes>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<WxActGoldeneggsPrizes> queryPrizes(String jwid,String createBy) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("jwid", jwid);
//		param.put("createBy", createBy);
//		return (List<WxActGoldeneggsPrizes>)super.query("queryPrizes", param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsPrizes> queryByActId(String actId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		return super.query("queryByActId", param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsPrizes> queryPrizesByName(String jwid, String createBy, String name) {
//		Map<String, String>param=new HashMap<String, String>();
//		param.put("jwid", jwid);
//		param.put("createBy", createBy);
//		param.put("name", name);
//		return super.query("queryPrizesByName", param);
//	}
//
//	@Override
//	public WxActGoldeneggsPrizes queryByActIdAndAwardId(String actId,
//			String awardsId) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("actId", actId);
//		param.put("awardsId", awardsId);
//		return (WxActGoldeneggsPrizes) super.queryOne("queryByActIdAndAwardId", param);
//	}
//
//	@Override
//	public WxActGoldeneggsPrizes queryByActIdAndAwardIdAndName(String actId,
//			String awardsId, String prizesName, String relationId) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("actId", actId);
//		param.put("awardsId", awardsId);
//		param.put("prizesName", prizesName);
//		if(relationId!=null && relationId!=""){
//			param.put("relationId", relationId);
//		}
//		return (WxActGoldeneggsPrizes) super.queryOne("queryByActIdAndAwardIdAndName", param);
//	}
//	@Override
//	public WxActGoldeneggsPrizes queryByActIdAndCode(String actId,String code) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("actId", actId);
//		param.put("code", code);
//		return (WxActGoldeneggsPrizes) super.queryOne("queryByActIdAndCode", param);
//	}
//
//
//}
//
