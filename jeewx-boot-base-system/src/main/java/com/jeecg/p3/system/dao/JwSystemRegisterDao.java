package com.jeecg.p3.system.dao;

import com.jeecg.p3.system.entity.JwSystemRegister;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>JwSystemRegisterDao<br>
 * @author：alex
 * @since：2016年03月23日 18时07分59秒 星期三 
 * @version:1.0
 */
public interface JwSystemRegisterDao extends GenericDao<JwSystemRegister>{
	
	public Integer count(PageQuery<JwSystemRegister> pageQuery);
	
	public List<JwSystemRegister> queryPageList(PageQueryWrapper<JwSystemRegister> wrapper);
	
	public List<JwSystemRegister> queryByProperty(@Param("property") JwSystemRegister property);
}

