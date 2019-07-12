package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemRole;

/**
 * 描述：</b>JwSystemRoleDao<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public interface JwSystemRoleDao extends GenericDao<JwSystemRole>{
	
	public Integer count(PageQuery<JwSystemRole> pageQuery);
	
	public List<JwSystemRole> queryPageList(PageQueryWrapper<JwSystemRole> wrapper);
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	public List<JwSystemRole> queryAllRoleList();
}

