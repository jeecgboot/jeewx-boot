//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinAutoresponseDefaultDao;
//import com.jeecg.p3.weixin.entity.WeixinAutoresponseDefault;
//
///**
// * 描述：</b>未识别回复语<br>
// * @author：LeeShaoQing
// * @since：2018年07月20日 10时11分50秒 星期五 
// * @version:1.0
// */
//@Repository("weixinAutoresponseDefaultDao")
//public class WeixinAutoresponseDefaultDaoImpl extends GenericDaoDefault<WeixinAutoresponseDefault> implements WeixinAutoresponseDefaultDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinAutoresponseDefault> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinAutoresponseDefault> queryPageList(
//			PageQuery<WeixinAutoresponseDefault> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinAutoresponseDefault> wrapper = new PageQueryWrapper<WeixinAutoresponseDefault>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinAutoresponseDefault>)super.query("queryPageList",wrapper);
//	}
//
//	/**
//	 * 根据触发类型查询记录是否存在
//	 * @param msgType
//	 * @return
//	 */
//	@Override
//	public WeixinAutoresponseDefault queryBymsgTriggerType(String msgTriggerType, String jwid) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("msgTriggerType", msgTriggerType);
//		map.put("jwid", jwid);
//		return (WeixinAutoresponseDefault) super.queryOne("queryBymsgTriggerType",map);
//	}
//
//
//}
//
