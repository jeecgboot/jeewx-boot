package com.jeecg.p3.commonweixin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonweixin.entity.JwSystemUserJwidVo;
import com.jeecg.p3.commonweixin.entity.JwSystemUserVo;

public interface MyJwSystemUserDao extends GenericDao<JwSystemUserVo>{

	/**
	 * @功能：根据手机号查询管理员信息
	 * @param phone
	 * @return
	 */
	List<JwSystemUserVo> queryByPhone(String phone);

	/**
	 * @功能：分配管理员权限
	 * @param jwSystemUserJwid
	 */
	void authManager(JwSystemUserJwidVo jwSystemUserJwid);

	/**
	 * @功能：根据jwid获取已分配管理员信息
	 * @param jwid
	 * @return
	 */
	List<JwSystemUserJwidVo> queryByJwid(String jwid);

	/**
	 * @功能：根据id删除用户公众号关联表信息
	 * @param id
	 */
	void deleteById(String id);
	
}

