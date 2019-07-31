//package com.jeecg.p3.goldeneggs.dao.impl;
//
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
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRecordDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;
//
///**
// * 描述：</b>WxActGoldeneggsRecordDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年06月13日 10时55分39秒 星期一 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsRecordDao")
//public class WxActGoldeneggsRecordDaoImpl extends GenericDaoDefault<WxActGoldeneggsRecord> implements WxActGoldeneggsRecordDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsRecord> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsRecord> queryPageList(
//			PageQuery<WxActGoldeneggsRecord> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsRecord> wrapper = new PageQueryWrapper<WxActGoldeneggsRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsRecord>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public WxActGoldeneggsRecord queryByCode(String code) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("code", code);
//		return(WxActGoldeneggsRecord)super.queryOne("queryByCode",param);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsRecord> queryList(String actId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		return(List<WxActGoldeneggsRecord>)super.query("queryList",param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> queryMyList(String openid,String actId) {
//		Map<String,String> param=Maps.newConcurrentMap();
//		param.put("openid", openid);
//		param.put("actId", actId);
//		return(List<WxActGoldeneggsRecord>)super.query("queryMyList",param);
//	}
//
//	@Override
//	public Integer getMaxAwardsSeq(String actId) {
//		Integer maxAwardsSeq = (Integer) super.queryOne("getMaxSeq",actId);
//		return maxAwardsSeq==null?0:maxAwardsSeq;
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> queryByWin() {
//		return super.query("queryByWin");
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> queryPersonalWin(String openid,String actId) {
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("openid", openid);
//		param.put("actId", actId);
//		return super.query("queryPersonalWin", param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> exportRecordListByActidAndJwid(
//			String actId, String jwid,String prizesStateFlag) {
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("jwid", jwid);
//		if(StringUtils.isNotEmpty(prizesStateFlag)){
//			param.put("prizesStateFlag", prizesStateFlag);
//		}
//		return (List<WxActGoldeneggsRecord>)super.query("exportRecordListByActidAndJwid",param);
//	}
//
//	@Override
//	public int queryCountByWin(String actId) {
//		Integer count = (Integer) super.queryOne("queryCountByWin",actId);
//		return count==null?0:count;
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> queryByWinAndPage(String actId,int pageNo, int pageSize) {
//		Map<String,Object> param = Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("pageNo", pageNo);
//		param.put("pageSize", pageSize);
//		return (List<WxActGoldeneggsRecord>)super.query("queryByWinAndPage",param);
//	}
//
//	@Override
//	public List<WxActGoldeneggsRecord> queryByActidAndOpenidAndPrizesStatus(
//			String actId, String openid, String prizesStatus) {
//		Map<String,Object> param = Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("openid", openid);
//		param.put("prizesStatus", prizesStatus);
//		return (List<WxActGoldeneggsRecord>)super.query("queryByActidAndOpenidAndPrizesStatus",param);
//	}
//
//	@Override
//	public WxActGoldeneggsRecord queryByActIdAndCode(String actId, String code) {
//		Map<String,Object> param = Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("code", code);
//		return(WxActGoldeneggsRecord)super.queryOne("queryByActIdAndCode",param);
//	}
//
//
//}
//
