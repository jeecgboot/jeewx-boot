package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemRegister;

/**
 * 描述：</b>JwSystemRegisterService<br>
 * @author：alex
 * @since：2016年03月23日 18时07分59秒 星期三 
 * @version:1.0
 */
public interface JwSystemRegisterService {
	
	
	public void doAdd(JwSystemRegister jwSystemRegister);
	
	public void doEdit(JwSystemRegister jwSystemRegister);
	
	public void doDelete(String id);
	
	public JwSystemRegister queryById(String id);
	
	public PageList<JwSystemRegister> queryPageList(PageQuery<JwSystemRegister> pageQuery);
	
	public List<JwSystemRegister> queryByProperty(JwSystemRegister property);
}

