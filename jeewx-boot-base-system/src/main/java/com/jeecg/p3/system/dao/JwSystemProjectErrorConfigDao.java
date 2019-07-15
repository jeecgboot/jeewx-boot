package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemProjectErrorConfig;

/**
 * 描述：</b>JwSystemProjectErrorConfigDao<br>
 * @author：junfeng.zhou
 * @since：2016年02月24日 10时21分06秒 星期三 
 * @version:1.0
 */
public interface JwSystemProjectErrorConfigDao extends GenericDao<JwSystemProjectErrorConfig>{
	
	public Integer count(PageQuery<JwSystemProjectErrorConfig> pageQuery);
	
	public List<JwSystemProjectErrorConfig> queryPageList(PageQueryWrapper<JwSystemProjectErrorConfig> wrapper);
	
}

