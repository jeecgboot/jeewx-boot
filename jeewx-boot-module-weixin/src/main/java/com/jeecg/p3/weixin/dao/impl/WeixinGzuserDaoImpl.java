//package com.jeecg.p3.weixin.dao.impl;
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
//import com.jeecg.p3.weixin.dao.WeixinGzuserDao;
//import com.jeecg.p3.weixin.entity.WeixinGzuser;
//
///**
// * 描述：</b>粉丝表<br>
// * @author：weijian.zhang
// * @since：2018年07月26日 15时38分40秒 星期四 
// * @version:1.0
// */
//@Repository("weixinGzuserDao")
//public class WeixinGzuserDaoImpl extends GenericDaoDefault<WeixinGzuser> implements WeixinGzuserDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinGzuser> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinGzuser> queryPageList(
//			PageQuery<WeixinGzuser> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinGzuser> wrapper = new PageQueryWrapper<WeixinGzuser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinGzuser>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public List<WeixinGzuser> queryNumberByJwid(String jwid, int pageNo,
//			int pageSize) {
//		Map<String,Object> map = Maps.newConcurrentMap();
//		map.put("jwid", jwid);
//		map.put("pageNo", pageNo);
//		map.put("pageSize", pageSize);
//		return (List<WeixinGzuser>)super.query("queryNumberByJwid", map);
//	}
//
//	//update-begin--Author:zhangweijian  Date: 20180820 for：根据OpenId查询粉丝信息
//	/**
//	 * @功能：根据openId查询粉丝信息
//	 */
//	@Override
//	public WeixinGzuser queryByOpenId(String openId,String jwid) {
//		Map<String,Object> map = Maps.newConcurrentMap();
//		map.put("openId", openId);
//		map.put("jwid", jwid);
//		return (WeixinGzuser)super.queryOne("queryByOpenId", map);
//	}
//	//update-end--Author:zhangweijian  Date: 20180820 for：根据OpenId查询粉丝信息
//
//	//update-begin-Author:LiShaoQing Date:20181206 for:根据tagId模糊查询用户信息
//	@Override
//	public List<WeixinGzuser> queryVagurByTagId(String tagId) {
//		Map<String,Object> map = Maps.newConcurrentMap();
//		map.put("tagId", tagId);
//		return (List<WeixinGzuser>)super.query("queryVagurByTagId", map);
//	}
//	//update-end-Author:LiShaoQing Date:20181206 for:根据tagId模糊查询用户信息
//
//
//}
//
