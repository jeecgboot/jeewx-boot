package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.system.entity.JwSystemRole;

/**
 * 描述：</b>JwSystemRoleService<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分28秒 星期一 
 * @version:1.0
 */
public interface JwSystemRoleService {
	
	
	public void doAdd(JwSystemRole jwSystemRole);
	
	public void doEdit(JwSystemRole jwSystemRole);
	
	public void doDelete(Long id,String roleId);
	
	public JwSystemRole queryById(Long id);
	
	public PageList<JwSystemRole> queryPageList(PageQuery<JwSystemRole> pageQuery);
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	public List<JwSystemRole> queryAllRoleList();
}

