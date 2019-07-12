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
//import com.jeecg.p3.weixin.dao.WeixinTagDao;
//import com.jeecg.p3.weixin.entity.WeixinTag;
//
///**
// * 描述：</b>粉丝标签表<br>
// * @author：weijian.zhang
// * @since：2018年08月13日 14时53分22秒 星期一 
// * @version:1.0
// */
//@Repository("weixinTagDao")
//public class WeixinTagDaoImpl extends GenericDaoDefault<WeixinTag> implements WeixinTagDao{
//
//	@Override
//	public Integer count(PageQuery<WeixinTag> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTag> queryPageList(
//			PageQuery<WeixinTag> pageQuery,Integer itemCount) {
//		PageQueryWrapper<WeixinTag> wrapper = new PageQueryWrapper<WeixinTag>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<WeixinTag>)super.query("queryPageList",wrapper);
//	}
//
//	/**
//	 * @功能：根据jwid清空该公众号创建的标签
//	 * @param jwid
//	 */
//	@Override
//	public void deleteTagsByJwid(String jwid) {
//		super.delete("deleteTagsByJwid", jwid);
//	}
//
//	/**
//	 * @功能：根据jwid获取该公众号创建的标签
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<WeixinTag> getAllTags(String jwid) {
//		return super.query("getAllTags", jwid);
//	}
//	
//	/**
//	 * @功能：根据tagId和jwid获取标签信息
//	 */
//	@Override
//	public WeixinTag queryByTagIdAndJwid(String tagId, String jwid) {
//		Map<String, Object> param=Maps.newConcurrentMap();
//		param.put("tagId", tagId);
//		param.put("jwid", jwid);
//		return (WeixinTag) super.queryOne("queryByTagIdAndJwid", param);
//	}
//
//
//}
//
