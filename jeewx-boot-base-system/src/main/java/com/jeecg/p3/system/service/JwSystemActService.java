package com.jeecg.p3.system.service;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.system.entity.JwSystemAct;

/**
 * 描述：</b>平台活动表<br>
 * @author：Alex
 * @since：2017年09月30日 10时05分08秒 星期六 
 * @version:1.0
 */
public interface JwSystemActService {
	
	
	public void doAdd(JwSystemAct jwSystemAct);
	
	public void doEdit(JwSystemAct jwSystemAct);
	
	public void doDelete(String id);
	
	public JwSystemAct queryById(String id);
	
	public PageList<JwSystemAct> queryPageList(PageQuery<JwSystemAct> pageQuery);
	/**
	 * 更新活动的参与分享总次数
	 */
	public void updateJoinShareNum(String actId);

	//update-begin--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
	/**
	 * 查询参与人数>100的活动信息
	 * @param joinNum 
	 * @param date 
	 * @return
	 */
	public List<JwSystemAct> queryByJoinNum(int joinNum, Date date);
	//update-end--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
}

