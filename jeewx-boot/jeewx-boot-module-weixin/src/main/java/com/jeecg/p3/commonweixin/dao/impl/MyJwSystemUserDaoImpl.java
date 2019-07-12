//package com.jeecg.p3.commonweixin.dao.impl;
//
//import java.util.List;
//
//import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
//import org.springframework.stereotype.Repository;
//
//import com.jeecg.p3.commonweixin.dao.MyJwSystemUserDao;
//import com.jeecg.p3.commonweixin.entity.JwSystemUserJwidVo;
//import com.jeecg.p3.commonweixin.entity.JwSystemUserVo;
//
//@Repository("myJwSystemUserDao")
//public class MyJwSystemUserDaoImpl extends GenericDaoDefault<JwSystemUserVo> implements MyJwSystemUserDao{
//
//	/**
//	 * @功能：根据手机号查询管理员信息
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemUserVo> queryByPhone(String phone) {
//		return super.query("queryByPhone", phone);
//	}
//
//	/**
//	 * @功能：分配管理员权限
//	 */
//	@Override
//	public void authManager(JwSystemUserJwidVo jwSystemUserJwid) {
//		super.getSqlSession().insert("authManager",jwSystemUserJwid);
//	}
//
//	/**
//	 * @功能：根据jwid获取已分配管理员信息
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<JwSystemUserJwidVo> queryByJwid(String jwid) {
//		return super.query("queryByJwid", jwid);
//	}
//
//	/**
//	 * @功能：根据id删除用户公众号关联表信息
//	 */
//	@Override
//	public void deleteById(String id) {
//		super.getSqlSession().delete("deleteById", id);
//	}
//
//}
//
