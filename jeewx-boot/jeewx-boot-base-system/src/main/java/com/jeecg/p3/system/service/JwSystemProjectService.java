package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemProject;

/**
 * 描述：</b>JwSystemProjectService<br>
 * @author：pituo
 * @since：2015年12月21日 17时49分18秒 星期一 
 * @version:1.0
 */
public interface JwSystemProjectService {
	
	
	public void doAdd(JwSystemProject jwSystemProject);
	
	public void doEdit(JwSystemProject jwSystemProject);
	
	public void doDelete(String id);
	
	public JwSystemProject queryById(String id);
	
	public PageList<JwSystemProject> queryPageList(PageQuery<JwSystemProject> pageQuery);
	
	public Boolean validReat(String code,String id);
	
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

	public List<JwSystemProject> queryProjectCode();

	//update-begin--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
	/**
	 * @功能：获取所有系统项目信息
	 * @return
	 */
	public List<JwSystemProject> getAllProject();
	//update-end--Author:zhangweijian  Date: 20180824 for：获取所有活动信息

	public boolean changeUrl(String newUrl, String jwid);

	public boolean changeType(String domain);
	
	public String getOldHdurl();
}

