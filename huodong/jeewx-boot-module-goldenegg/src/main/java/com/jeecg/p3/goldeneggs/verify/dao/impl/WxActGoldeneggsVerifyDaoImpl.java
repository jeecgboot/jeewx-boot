//package com.jeecg.p3.goldeneggs.verify.dao.impl;
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
//import com.jeecg.p3.goldeneggs.verify.dao.WxActGoldeneggsVerifyDao;
//import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;
//
///**
// * 描述：</b>砸金蛋审核员表<br>
// * @author：junfeng.zhou
// * @since：2018年10月17日 09时53分08秒 星期三 
// * @version:1.0
// */
//@Repository("wxActGoldeneggsVerifyDao")
//public class WxActGoldeneggsVerifyDaoImpl extends GenericDaoDefault<WxActGoldeneggsVerify> implements WxActGoldeneggsVerifyDao{
//
//	@Override
//	public Integer count(PageQuery<WxActGoldeneggsVerify> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WxActGoldeneggsVerify> queryPageList(
//			PageQuery<WxActGoldeneggsVerify> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WxActGoldeneggsVerify> wrapper = new PageQueryWrapper<WxActGoldeneggsVerify>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WxActGoldeneggsVerify>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public WxActGoldeneggsVerify queryByOpenId(String openid, String actId) {
//		Map<String,Object> m=new HashMap<String,Object>();
//		m.put("openid", openid);
//		m.put("actId", actId);
//		return (WxActGoldeneggsVerify) super.queryOne("queryByOpenId", m);
//	}
//
//	@Override
//	public WxActGoldeneggsVerify queryAllGoldeneggs(String actId, String cardPsd) {
//		Map<String,Object> m=new HashMap<String,Object>();
//		m.put("cardPsd", cardPsd);
//		m.put("actId", actId);
//		return (WxActGoldeneggsVerify) super.queryOne("queryAllGoldeneggs", m);
//	}
//
//
//}
//
