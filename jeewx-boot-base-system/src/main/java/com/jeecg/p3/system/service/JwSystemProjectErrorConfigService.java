package com.jeecg.p3.system.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemProjectErrorConfig;

/**
 * 描述：</b>JwSystemProjectErrorConfigService<br>
 * @author：junfeng.zhou
 * @since：2016年02月24日 10时21分06秒 星期三 
 * @version:1.0
 */
public interface JwSystemProjectErrorConfigService {
	
	
	public void doAdd(JwSystemProjectErrorConfig jwSystemProjectErrorConfig);
	
	public void doEdit(JwSystemProjectErrorConfig jwSystemProjectErrorConfig);
	
	public void doDelete(String id);
	
	public JwSystemProjectErrorConfig queryById(String id);
	
	public PageList<JwSystemProjectErrorConfig> queryPageList(PageQuery<JwSystemProjectErrorConfig> pageQuery);
}

