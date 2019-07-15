//package com.jeecg.p3.weixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.common.PageQuery;
//import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//import com.jeecg.p3.weixin.dao.WeixinReceptMsgDao;
//import com.jeecg.p3.weixin.entity.WeixinReceptMsg;
//
///**
// * 描述：</b>客服消息记录表<br>
// * @author：junfeng.zhou
// * @since：2018年10月18日 19时35分31秒 星期四 
// * @version:1.0
// */
//@Repository("weixinReceptMsgDao")
//public class WeixinReceptMsgDaoImpl extends GenericDaoDefault<WeixinReceptMsg> implements WeixinReceptMsgDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinReceptMsg> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinReceptMsg> queryPageList(
//			PageQuery<WeixinReceptMsg> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinReceptMsg> wrapper = new PageQueryWrapper<WeixinReceptMsg>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinReceptMsg>)super.query("queryPageList",wrapper);
//	}
//
//
//}
//
