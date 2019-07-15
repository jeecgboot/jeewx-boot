package com.jeecg.p3.system.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.system.entity.JwSystemUserJwid;

/**
 * 描述：</b>JwSystemUserJwidService<br>
 * @author：junfeng.zhou
 * @since：2015年12月23日 16时02分42秒 星期三 
 * @version:1.0
 */
public interface JwSystemUserJwidService {
	
	
	public void doAdd(JwSystemUserJwid jwSystemUserJwid);
	
	public void doEdit(JwSystemUserJwid jwSystemUserJwid);
	
	public void doDelete(String id);
	
	public JwSystemUserJwid queryById(String id);
	
	public PageList<JwSystemUserJwid> queryPageList(PageQuery<JwSystemUserJwid> pageQuery);
	
	/**
	 * 2016-10-18 14:09:19
	 * 查询默认登录使用的jwid
	 */
	public JwSystemUserJwid queryOneByUserIdAndDefaultFlag(String userId,String defaultFlag);
}

