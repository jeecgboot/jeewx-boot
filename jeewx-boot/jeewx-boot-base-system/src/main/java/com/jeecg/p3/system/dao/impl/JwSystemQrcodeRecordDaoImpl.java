//package com.jeecg.p3.system.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.system.dao.JwSystemQrcodeRecordDao;
//import com.jeecg.p3.system.entity.JwSystemQrcodeRecord;
//
///**
// * 描述：</b>JwSystemQrcodeRecordDaoImpl<br>
// * @author：junfeng.zhou
// * @since：2016年10月19日 20时24分19秒 星期三 
// * @version:1.0
// */
//@Repository("jwSystemQrcodeRecordDao")
//public class JwSystemQrcodeRecordDaoImpl extends GenericDaoDefault<JwSystemQrcodeRecord> implements JwSystemQrcodeRecordDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemQrcodeRecord> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemQrcodeRecord> queryPageList(
//			PageQuery<JwSystemQrcodeRecord> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemQrcodeRecord> wrapper = new PageQueryWrapper<JwSystemQrcodeRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemQrcodeRecord>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
