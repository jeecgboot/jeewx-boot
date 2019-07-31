//package com.jeecg.p3.goldeneggs.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.common.StringUtils;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.google.common.collect.Maps;
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRelationDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
//
///**
// * 描述：</b>WxActGoldeneggsRelationDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月07日 18时00分29秒 星期二 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsRelationDao")
//public class WxActGoldeneggsRelationDaoImpl extends GenericDaoDefault<WxActGoldeneggsRelation> implements WxActGoldeneggsRelationDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsRelation> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsRelation> queryPageList(
//			PageQuery<WxActGoldeneggsRelation> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsRelation> wrapper = new PageQueryWrapper<WxActGoldeneggsRelation>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsRelation>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<WxActGoldeneggsRelation> queryByActId(String actId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		return (List<WxActGoldeneggsRelation>)super.query("queryByActId", param);
//		
//	}
//
//	@Override
//	public List<WxActGoldeneggsRelation> queryPrizeAndAward(String actId) {
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("actId", actId);
//		return(List<WxActGoldeneggsRelation>)super.query("queryPrizeAndAward",param);
//	}
//
//	@Override
//	public WxActGoldeneggsRelation queryByprizeIdAndAwardId(String prizeId,
//			String awardId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("prizeId", prizeId);
//		param.put("awardId", awardId);
//		return(WxActGoldeneggsRelation)super.queryOne("queryByprizeIdAndAwardId",param);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsRelation> queryByActIdAndJwid(String actId,
//			String jwid) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("jwid", jwid);
//		return (List<WxActGoldeneggsRelation>)super.query("queryByActIdAndJwid",param);
//	}
//
//	@Override
//	public void batchDeleteByActId(String actid) {
//		// TODO Auto-generated method stub
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("actId", actid);
//		super.delete("batchDeleteByActId", param);
//	}
//	@Override
//	public void bactchDeleteOldAwards(List<String> ids,String actId) {
//		Map<String,Object> param = Maps.newConcurrentMap();
//		param.put("ids", ids);
//		param.put("actId", actId);
//		super.delete("bactchDeleteOldAwards",param);
//	}
//	@Override
//	public Boolean validUsed(String awardId,String prizeId) {
//		 Map<String,Object> map=new HashMap<String,Object>();
//		 if(StringUtils.isNotEmpty(prizeId)){
//			map.put("prizeId", prizeId);
//		 }
//		 if(StringUtils.isNotEmpty(awardId)){
//			 map.put("awardId", awardId);
//		 }
//		 List<WxActGoldeneggsRelation> relation=super.query("validUsed",map);
//		 if(relation !=null && relation.size()>0){
//		     return true;
//		 }else{
//			 return false;
//		 }
//	}
//}
//
