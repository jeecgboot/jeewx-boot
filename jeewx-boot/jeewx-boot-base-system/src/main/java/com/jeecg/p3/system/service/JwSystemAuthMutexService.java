package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.system.entity.JwSystemAuthMutex;

/**
 * 描述：</b>JwSystemAuthMutexService<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public interface JwSystemAuthMutexService {
	
	
	public void doAdd(JwSystemAuthMutex jwSystemAuthMutex);
	
	public void doEdit(JwSystemAuthMutex jwSystemAuthMutex);
	
	public void doDelete(String id);
	
	public JwSystemAuthMutex queryById(String id);
	
	public PageList<JwSystemAuthMutex> queryPageList(PageQuery<JwSystemAuthMutex> pageQuery);
	
	/**查询所有的权限互斥规则
	 * @param 
	 * @return
	 * @author：junfeng.zhou
	 */
	public List<JwSystemAuthMutex> queryAllAuthMutex();
}

