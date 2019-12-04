//package com.jeecg.p3.qrcode.dao.impl;
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
//import com.jeecg.p3.qrcode.dao.WeixinQrcodeScanRecordDao;
//import com.jeecg.p3.qrcode.entity.WeixinQrcode;
//import com.jeecg.p3.qrcode.entity.WeixinQrcodeScanRecord;
//
///**
// * 描述：</b>二维码扫码记录表<br>
// * @author：sunkai
// * @since：2018年08月30日 10时28分08秒 星期四 
// * @version:1.0
// */
//@Repository("weixinQrcodeScanRecordDao")
//public class WeixinQrcodeScanRecordDaoImpl extends GenericDaoDefault<WeixinQrcodeScanRecord> implements WeixinQrcodeScanRecordDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinQrcodeScanRecord> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinQrcodeScanRecord> queryPageList(
//			PageQuery<WeixinQrcodeScanRecord> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinQrcodeScanRecord> wrapper = new PageQueryWrapper<WeixinQrcodeScanRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinQrcodeScanRecord>)super.query("queryPageList",wrapper);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinQrcodeScanRecord> queryByExcel(String sceneId, String jwid,String isScanSubscribe) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("sceneId", sceneId);
//		param.put("jwid", jwid);
//		param.put("isScanSubscribe", isScanSubscribe);
//		return (List<WeixinQrcodeScanRecord>)super.query("queryByExcel",param);
//	}
//
//
//}
//
