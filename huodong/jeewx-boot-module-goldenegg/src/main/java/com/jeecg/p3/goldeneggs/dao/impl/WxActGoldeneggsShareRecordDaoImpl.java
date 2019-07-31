//package com.jeecg.p3.goldeneggs.dao.impl;
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
//import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsShareRecordDao;
//import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;
//
///**
// * 描述：</b>分享记录表<br>
// * @author：junfeng.zhou
// * @since：2018年10月10日 10时27分14秒 星期三 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsShareRecordDao")
//public class WxActGoldeneggsShareRecordDaoImpl extends GenericDaoDefault<WxActGoldeneggsShareRecord> implements WxActGoldeneggsShareRecordDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsShareRecord> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsShareRecord> queryPageList(
//			PageQuery<WxActGoldeneggsShareRecord> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsShareRecord> wrapper = new PageQueryWrapper<WxActGoldeneggsShareRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsShareRecord>)super.query("queryPageList",wrapper);
//	}
//
//	/**
//	 * @功能：查询用户当天的分享记录
//	 */
//	@Override
//	public List<WxActGoldeneggsShareRecord> queryShareRecordByDate(
//			String actId, String openid, String refDate, String type) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("actId", actId);
//		param.put("openid", openid);
//		param.put("refDate", refDate);
//		param.put("type", type);
//		return super.query("queryShareRecordByDate", param);
//	}
//}
//
