package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemRegister;

/**
 * 描述：</b>JwSystemRegisterDao<br>
 * @author：alex
 * @since：2016年03月23日 18时07分59秒 星期三 
 * @version:1.0
 */
public interface JwSystemRegisterDao extends GenericDao<JwSystemRegister>{
	
	public Integer count(PageQuery<JwSystemRegister> pageQuery);
	
	public List<JwSystemRegister> queryPageList(PageQueryWrapper<JwSystemRegister> wrapper);
	
	public List<JwSystemRegister> queryByProperty(JwSystemRegister property);
}

