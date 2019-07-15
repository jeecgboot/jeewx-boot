package com.jeecg.p3.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemUserJwid;

/**
 * 描述：</b>JwSystemUserJwidDao<br>
 * @author：junfeng.zhou
 * @since：2015年12月23日 16时02分42秒 星期三 
 * @version:1.0
 */
public interface JwSystemUserJwidDao extends GenericDao<JwSystemUserJwid>{
	
	public Integer count(PageQuery<JwSystemUserJwid> pageQuery);
	
	public List<JwSystemUserJwid> queryPageList(PageQueryWrapper<JwSystemUserJwid> wrapper);
	
	/**
	 * 2016-10-18 14:09:19
	 * 查询默认登录使用的jwid
	 */
	public JwSystemUserJwid queryOneByUserIdAndDefaultFlag(@Param("userId")String userId,@Param("defaultFlag")String defaultFlag);
}

