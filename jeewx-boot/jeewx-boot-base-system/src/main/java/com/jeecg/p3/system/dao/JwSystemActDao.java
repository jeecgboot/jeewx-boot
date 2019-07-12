package com.jeecg.p3.system.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemAct;

/**
 * 描述：</b>平台活动表<br>
 * @author：Alex
 * @since：2017年09月30日 10时05分08秒 星期六 
 * @version:1.0
 */
public interface JwSystemActDao extends GenericDao<JwSystemAct>{
	
	public Integer count(PageQuery<JwSystemAct> pageQuery);
	
	public List<JwSystemAct> queryPageList(PageQueryWrapper<JwSystemAct> wrapper);
    /**
     * 更新活动的参与分享总数
     */
	public void updateJoinShareNum(String actId);

	//update-begin--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
	/**
	 * 查询参与人数>100的活动信息
	 * @param joinNum 
	 * @param date 
	 * @return
	 */
	public List<JwSystemAct> queryByJoinNum(@Param("joinNum")int joinNum, @Param("date")Date date);
	//update-end--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息

	
}

