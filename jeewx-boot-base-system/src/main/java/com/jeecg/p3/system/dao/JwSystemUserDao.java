package com.jeecg.p3.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemUser;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.system.vo.Menu;

/**
 * 描述：</b>JwSystemUserDao<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分29秒 星期一 
 * @version:1.0
 */
public interface JwSystemUserDao extends GenericDao<JwSystemUser>{
	
	public Integer count(PageQuery<JwSystemUser> pageQuery);
	
	public List<JwSystemUser> queryPageList(PageQueryWrapper<JwSystemUser> wrapper);
	
	/**
	 * 根据用户编码查询用户角色编码
	 * @param userId 用户编码
	 * @return
	 */
	public List<String> queryUserRoles(String userId);
	
	/**
     * 根据角色编码查询相关角色下的所有权限
     * @param <pre>
     *             <li>roleIds : 角色编码list</li>
     *        </pre>
     *             
     * @return  <pre>List<Menu>:所有的权限
     *             <li>authId:        权限编码</li>
     *             <li>authNam:       权限名称</li>
     *             <li>roleId:        角色编码</li>
     *             <li>roleName:      角色名称</li>
     *          </pre>
     */
	public List<Menu> queryUserMenuAuth(List<String> roleIds);
	
	public void insertUserRoleRel(@Param("userId")String userId,@Param("roleId")String roleId);
	
	public void deleteRolesByUserId(String userId);
	
	/**
	 * 根据userId查询用户登录信息
	 * @param userId
	 * @return
	 */
	public LoginUser queryUserByUserId(String userId);
	/**
	 * 根据userId验重用户编码
	 * @param userId
	 * @return
	 */
	public Integer validReatUserId(@Param("userId")String userId,@Param("id")Integer id);
	/**
	 * 通过openidId查询是否注册过
	 */
	public LoginUser queryUserByOpenid(String openid);
	//update--begin--author: gj_shaojc--date:20180503--------for:增加代理商管理-
	public List<JwSystemUser> queryAgentPageList(PageQueryWrapper<JwSystemUser> wrapper);
	//update--end--author: gj_shaojc--date:20180503--------for:增加代理商管理-
	
	/**
	 * 查询用户欠费状态
	 */
	public String getUserChargeState(String userid);
}

