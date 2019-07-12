package com.jeecg.p3.commonweixin.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonweixin.entity.WeixinSystemProject;

/**
 * 描述：</b>JwSystemProjectService<br>
 * @author：pituo
 * @since：2015年12月21日 17时49分18秒 星期一 
 * @version:1.0
 */
public interface WeixinSystemProjectService{
	
	
	public void doEdit(WeixinSystemProject weixinSystemProject);
	
	public WeixinSystemProject queryById(String id);
	
	public PageList<WeixinSystemProject> queryPageList(PageQuery<WeixinSystemProject> pageQuery);
	
	/**
	 * 修改某表的全部url
	 * @param tableName表名
	 * @param hdurlName 字段名
	 * @param jwidName 字段名
	 * @param shortUrlName 短链接
	 * @param linksucai 素材链接
	 */
	public void editHdurl(String projectId,String tableName,String hdurlName,String jwidName,String shortUrlName,String linksucai);
	
	/**
	 * 根据表名查询长链接
	 * @param tableName
	 * @return
	 */
	public List<Map<String,String>> queryAllActByTableName(String tableName);
	
	/**
	 * 修改表中的短链接
	 * @param tableName表名
	 * @param actId 活动名称
	 */
	public void doEditShortByTableName(String tableName,String actId,String shortUrl);
	
	/**
	 * 设置表的短链接为空
	 * @param tableName
	 */
	public void doEditShortEmpty(String tableName);
}

