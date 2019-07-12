package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemAuthMutex;

/**
 * 描述：</b>JwSystemAuthMutexDao<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public interface JwSystemAuthMutexDao extends GenericDao<JwSystemAuthMutex>{
	
	public Integer count(PageQuery<JwSystemAuthMutex> pageQuery);
	
	public List<JwSystemAuthMutex> queryPageList(PageQueryWrapper<JwSystemAuthMutex> wrapper);
	
	/**查询所有的权限互斥规则
	 * @param 
	 * @return
	 * @author：junfeng.zhou
	 */
	public List<JwSystemAuthMutex> queryAllAuthMutex();
}

