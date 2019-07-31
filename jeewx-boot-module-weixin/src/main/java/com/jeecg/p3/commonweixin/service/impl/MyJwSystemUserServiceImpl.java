package com.jeecg.p3.commonweixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeecg.p3.commonweixin.dao.MyJwSystemUserDao;
import com.jeecg.p3.commonweixin.entity.JwSystemUserJwidVo;
import com.jeecg.p3.commonweixin.entity.JwSystemUserVo;
import com.jeecg.p3.commonweixin.service.MyJwSystemUserService;

@Service("myJwSystemUserService")
public class MyJwSystemUserServiceImpl implements MyJwSystemUserService {

	@Resource
	private MyJwSystemUserDao myJwSystemUserDao;
	
	/**
	 * @功能：根据手机号查询管理员信息
	 */
	@Override
	public List<JwSystemUserVo> queryByPhone(String phone) {
		return myJwSystemUserDao.queryByPhone(phone);
	}

	/**
	 * @功能：分配管理员权限
	 */
	@Override
	public void authManager(JwSystemUserJwidVo jwSystemUserJwid) {
		myJwSystemUserDao.authManager(jwSystemUserJwid);
	}

	/**
	 * @功能：根据jwid获取已分配管理员信息
	 */
	@Override
	public List<JwSystemUserJwidVo> queryByJwid(String jwid) {
		return myJwSystemUserDao.queryByJwid(jwid);
	}

	/**
	 * @功能：根据id删除用户公众号关联表信息
	 */
	@Override
	public void deleteById(String id) {
		myJwSystemUserDao.deleteById(id);
	}
}
