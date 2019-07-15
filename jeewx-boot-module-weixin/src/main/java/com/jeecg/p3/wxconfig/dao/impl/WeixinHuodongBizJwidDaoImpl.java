//package com.jeecg.p3.wxconfig.dao.impl;
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
//import com.jeecg.p3.wxconfig.dao.WeixinHuodongBizJwidDao;
//import com.jeecg.p3.wxconfig.entity.WeixinHuodongBizJwid;
//
///**
// * 描述：</b>微信活动jwid表<br>
// * @author：weijian.zhang
// * @since：2018年08月08日 09时32分33秒 星期三 
// * @version:1.0
// */
//@Repository("weixinHuodongBizJwidDao")
//public class WeixinHuodongBizJwidDaoImpl extends GenericDaoDefault<WeixinHuodongBizJwid> implements WeixinHuodongBizJwidDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinHuodongBizJwid> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinHuodongBizJwid> queryPageList(
//			PageQuery<WeixinHuodongBizJwid> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinHuodongBizJwid> wrapper = new PageQueryWrapper<WeixinHuodongBizJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinHuodongBizJwid>)super.query("queryPageList",wrapper);
//	}
//
//	//update-begin-zhangweijian-----Date:20180808---for:变更微信活动jwid表公众号原始ID
//	/**
//	 * @功能:变更微信活动jwid表公众号原始ID
//	 */
//	@Override
//	public void updateTable(String tableName, String jwid, String newJwid) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("tableName", tableName);
//		param.put("jwid", jwid);
//		param.put("newJwid", newJwid);
//		super.update("updateTable", param);
//	}
//	//update-end-zhangweijian-----Date:20180808---for:变更微信活动jwid表公众号原始ID
//
//	//update-begin-zhangweijian-----Date:20180808---for:获取所有微信活动jwid表数据
//	/**
//	 * @功能:获取所有微信活动jwid表数据
//	 */
//	@Override
//	public List<WeixinHuodongBizJwid> queryAll() {
//		return super.query("queryAll");
//	}
//	//update-end-zhangweijian-----Date:20180808---for:获取所有微信活动jwid表数据
//
//	//update-begin--Author:zhangweijian Date:20181011 for：更新活动长短链接
//	@SuppressWarnings("unchecked")
//	/**
//	 * @功能：根据表名和jwid查询hdurl
//	 */
//	@Override
//	public List<Map<String, Object>> queryHdurls(String tableName,String jwid) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("tableName", tableName);
//		param.put("jwid", jwid);
//		return super.query("queryHdurls",param);
//	}
//
//	/**
//	 * @功能：更新活动长短链接
//	 */
//	@Override
//	public void updateShortUrl(String tableName, String id, String jwid, String newJwid,String shortUrl) {
//		Map<String, String> param=new HashMap<String, String>();
//		param.put("tableName", tableName);
//		param.put("id", id);
//		param.put("jwid", jwid);
//		param.put("newJwid", newJwid);
//		param.put("shortUrl", shortUrl);
//		super.update("updateShortUrl", param);
//	}
//	//update-end--Author:zhangweijian Date:20181011 for：更新活动长短链接
//}
//
