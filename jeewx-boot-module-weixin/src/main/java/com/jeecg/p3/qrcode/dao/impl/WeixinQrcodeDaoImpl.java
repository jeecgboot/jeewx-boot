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
//import com.jeecg.p3.qrcode.dao.WeixinQrcodeDao;
//import com.jeecg.p3.qrcode.entity.WeixinQrcode;
//import com.jeecg.p3.qrcode.web.back.WeixinQrcodeController;
//
///**
// * 描述：</b>二维码表<br>
// * @author：sunkai
// * @since：2018年08月30日 10时29分25秒 星期四 
// * @version:1.0
// */
//@Repository("weixinQrcodeDao")
//public class WeixinQrcodeDaoImpl extends GenericDaoDefault<WeixinQrcode> implements WeixinQrcodeDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinQrcode> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinQrcode> queryPageList(
//			PageQuery<WeixinQrcode> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinQrcode> wrapper = new PageQueryWrapper<WeixinQrcode>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinQrcode>)super.query("queryPageList",wrapper);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public Integer getScene(WeixinQrcode weixinQrcode){
//		int maxScenekey = 0;
//		//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("jwid", weixinQrcode.getJwid());
//		param.put("actionName", weixinQrcode.getActionName());
//		Integer max = (Integer) super.queryOne("queryMaxSceneId",param);
//		synchronized (WeixinQrcodeController.class) {
//		    if(max == null){
//		    	maxScenekey = 1;
//		    }else{
//		    	maxScenekey = max.intValue()+1;
//		    }
//		    // 临时二维码Scenceid从10w以后开始
//	    	if(maxScenekey<=100000 && "QR_SCENE".equals(weixinQrcode.getActionName())){
//	    		maxScenekey = 100001;
//	    	}		    
//		}
//		return maxScenekey;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinQrcode> queryBySceneId(String sceneid,String toUserName) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("sceneid", sceneid);
//		param.put("toUserName", toUserName);
//		return (List<WeixinQrcode>)super.query("queryBySceneId",param);
//	}
//
//}
//
