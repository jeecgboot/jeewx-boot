package com.jeecg.p3.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemProject;

/**
 * 描述：</b>JwSystemProjectDao<br>
 * @author：pituo
 * @since：2015年12月21日 17时49分18秒 星期一 
 * @version:1.0
 */
public interface JwSystemProjectDao extends GenericDao<JwSystemProject>{
	
	public Integer count(PageQuery<JwSystemProject> pageQuery);
	
	public List<JwSystemProject> queryPageList(PageQueryWrapper<JwSystemProject> wrapper);
	
	public Integer validReat(@Param("code")String code,@Param("id")String id);
	/**
	 * 查询推荐不推荐的
	 * @param type
	 * @return
	 */
	public List<JwSystemProject> queryListByType(String type);
	/**
	 * 按照分类ID查询
	 * @param projectClassifyId
	 * @return
	 */
	public List<JwSystemProject> queryListByProjectClassifyId(String projectClassifyId);
	
	/**
	 * @功能:通过code查询单条记录
	 * @作者:liwenhui 
	 * @时间:2017-8-30 上午11:44:59
	 * @修改：
	 * @param code
	 * @return  
	 */
	public JwSystemProject queryByCode(String code);
	//update-begin--Author:zhangweijian  Date: 20171215 for：查询所有的活动名称和编码
	/**
	 * 查询活动名称和编码
	 * */
	public List<JwSystemProject> queryProjectCode();
	//update-end--Author:zhangweijian  Date: 20171215 for：查询所有的活动名称和编码

	//update-begin--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
	/**
	 * @功能：获取所有系统项目信息
	 */
	public List<JwSystemProject> getAllProject();
	//update-end--Author:zhangweijian  Date: 20180824 for：获取所有活动信息

	public void changeHdurl(@Param("olddomain")String olddomain,@Param("newdomain")String newdomain);

	public List<Map<String, String>> getTableNames();
	
	public List<String> getOldHdurl();

	public void changeTabelHdurl(@Param("tableName")String tableName);

	public void changeType(String domain);

	public List<Map<String, String>> getAllAct(@Param("tableName")String tableName);

	public void updateShortUrl(@Param("tableName")String tableName, @Param("id")String id, @Param("shortUrl")String shortUrl);
}

