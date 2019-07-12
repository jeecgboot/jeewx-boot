//package com.jeecg.p3.system.dao.impl;
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
//import com.alibaba.druid.util.StringUtils;
//import com.google.common.collect.Maps;
//import com.jeecg.p3.system.dao.JwSystemProjectDao;
//import com.jeecg.p3.system.entity.JwSystemProject;
//
///**
// * 描述：</b>JwSystemProjectDaoImpl<br>
// * @author：pituo
// * @since：2015年12月21日 17时49分18秒 星期一 
// * @version:1.0
// */
//@Repository("jwSystemProjectDao")
//public class JwSystemProjectDaoImpl extends GenericDaoDefault<JwSystemProject> implements JwSystemProjectDao{
//
//	@Override
//	public Integer count(PageQuery<JwSystemProject> pageQuery) {
//		return (Integer) super.queryOne("count",pageQuery);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemProject> queryPageList(
//			PageQuery<JwSystemProject> pageQuery,Integer itemCount) {
//		PageQueryWrapper<JwSystemProject> wrapper = new PageQueryWrapper<JwSystemProject>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
//		return (List<JwSystemProject>)super.query("queryPageList",wrapper);
//	}
//
//	@Override
//	public Boolean validReat(String code, String id) {
//		Map<String,String> param = Maps.newConcurrentMap();
//		param.put("code", code);
//		if(StringUtils.isEmpty(id)){
//			
//		}else{			
//			param.put("id", id);
//		}
//		JwSystemProject project=(JwSystemProject)super.queryOne("validReat",param);
//		if(project==null){			
//			return false;
//		}else{
//			return true;
//		}
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemProject> queryListByType(String type) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("type", type);
//		return (List<JwSystemProject>)super.query("queryListByType",param);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<JwSystemProject> queryListByProjectClassifyId(
//			String projectClassifyId) {
//		Map<String, String> param=Maps.newConcurrentMap();
//		param.put("projectClassifyId", projectClassifyId);
//		return (List<JwSystemProject>)super.query("queryListByProjectClassifyId",param);
//	}
//
//	@Override
//	public JwSystemProject queryByCode(String code) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("code", code);
//		return (JwSystemProject) super.queryOne("queryByCode", map);
//	}
//	//update-begin--Author:zhangweijian  Date: 20171215 for：查询活动名称和编码
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemProject> queryProjectCode() {
//		return super.query("queryProjectCode");
//	}
//	//update-end--Author:zhangweijian  Date: 20171215 for：查询活动名称和编码
//
//	//update-begin--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
//	/**
//	 * @功能：获取所有系统项目信息
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemProject> getAllProject() {
//		return super.query("getAllProject");
//	}
//	//update-end--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
//
//	@Override
//	public void changeHdurl(String newdomain, String olddomain) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("newdomain", newdomain);
//		map.put("olddomain", olddomain);
//		super.update("changeHdurl", map);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Map<String, String>> getTableNames() {
//		return (List<Map<String, String>>)super.query("getTableNames");
//	}
//
//	@Override
//	public void changeTabelHdurl(String tableName) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("tableName", tableName);
//		super.update("changeTabelHdurl", map);
//	}
//
//	@Override
//	public void changeType(String domain) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("domain", domain);
//		super.update("changeType", map);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Map<String, String>> getAllAct(String tableName) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("tableName", tableName);
//		return (List<Map<String, String>>)super.query("getAllAct",map);
//	}
//
//	@Override
//	public void updateShortUrl(String tableName, String id, String shortUrl) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("tableName", tableName);
//		map.put("id", id);
//		map.put("shortUrl", shortUrl);
//		super.update("updateShortUrl",map);
//	}
//
//}
//
