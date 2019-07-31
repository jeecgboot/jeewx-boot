package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.system.dao.JwSystemUserDao;
import com.jeecg.p3.system.entity.JwSystemUser;
import com.jeecg.p3.system.service.JwSystemUserService;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.system.vo.Menu;

@Service("jwSystemUserService")
public class JwSystemUserServiceImpl implements JwSystemUserService {
	@Resource
	private JwSystemUserDao jwSystemUserDao;

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void doAdd(JwSystemUser jwSystemUser,List<String> roleIds) {
		jwSystemUserDao.insert(jwSystemUser);
		if(roleIds != null && roleIds.size()>0){
			for(String roleId:roleIds){
				jwSystemUserDao.insertUserRoleRel(jwSystemUser.getUserId(), roleId);
			}
		}
	}
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void doEdit(JwSystemUser jwSystemUser,List<String> roleIds) {
		jwSystemUserDao.update(jwSystemUser);
		jwSystemUserDao.deleteRolesByUserId(jwSystemUser.getUserId());
		if(roleIds != null && roleIds.size()>0){
			for(String roleId:roleIds){
				jwSystemUserDao.insertUserRoleRel(jwSystemUser.getUserId(), roleId);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void doDelete(Long id) {
		JwSystemUser jwSystemUser  = jwSystemUserDao.get(id);
		if("admin".equals(jwSystemUser.getUserId())){
			throw new RuntimeException("admin用户不能删除");
		}
		jwSystemUserDao.deleteRolesByUserId(jwSystemUser.getUserId());
		jwSystemUserDao.delete(id);
	}

	@Override
	public JwSystemUser queryById(Long id) {
		JwSystemUser jwSystemUser  = jwSystemUserDao.get(id);
		return jwSystemUser;
	}

	@Override
	public PageList<JwSystemUser> queryPageList(
		PageQuery<JwSystemUser> pageQuery) {
		PageList<JwSystemUser> result = new PageList<JwSystemUser>();
		Integer itemCount = jwSystemUserDao.count(pageQuery);
		PageQueryWrapper<JwSystemUser> wrapper = new PageQueryWrapper<JwSystemUser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemUser> list = jwSystemUserDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<String> queryUserRoles(String userId) {
		return jwSystemUserDao.queryUserRoles(userId);
	}

	@Override
	public List<Menu> queryUserMenuAuth(List<String> roleIds) {
		return jwSystemUserDao.queryUserMenuAuth(roleIds);
	}

	@Override
	public LoginUser queryUserByUserId(String userId) {
		return jwSystemUserDao.queryUserByUserId(userId);
	}

	@Override
	public void doChangePassword(JwSystemUser jwSystemUser) {
		jwSystemUserDao.update(jwSystemUser);
	}

	@Override
	public LoginUser queryUserByOpenid(String openid) {
		return jwSystemUserDao.queryUserByOpenid(openid);
	}
	//update--begin--author: gj_shaojc--date:20180503--------for:增加代理商管理-
	@Override
	public PageList<JwSystemUser> queryAgentPageList(
			PageQuery<JwSystemUser> pageQuery) {
		PageList<JwSystemUser> result = new PageList<JwSystemUser>();
		Integer itemCount = jwSystemUserDao.count(pageQuery);
		PageQueryWrapper<JwSystemUser> wrapper = new PageQueryWrapper<JwSystemUser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemUser> list = jwSystemUserDao.queryAgentPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	//update--end--author: gj_shaojc--date:20180503--------for:增加代理商管理-

	@Override
	public String getUserChargeState(String userid) {
		return jwSystemUserDao.getUserChargeState(userid);
	}

	@Override
	public Boolean validReatUserId(String userId, Integer id) {
		Integer i= jwSystemUserDao.validReatUserId(userId,id);
		return (i>0);
	}
	
}
