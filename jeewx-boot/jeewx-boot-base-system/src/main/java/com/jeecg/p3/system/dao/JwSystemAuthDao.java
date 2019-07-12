package com.jeecg.p3.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemAuth;
import com.jeecg.p3.system.vo.Auth;
import com.jeecg.p3.system.vo.Menu;
import com.jeecg.p3.system.vo.MenuFunction;

/**
 * 描述：</b>JwSystemAuthDao<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分27秒 星期一 
 * @version:1.0
 */
public interface JwSystemAuthDao extends GenericDao<JwSystemAuth>{
	
	public Integer count(PageQuery<JwSystemAuth> pageQuery);
	
	public List<JwSystemAuth> queryPageList(PageQueryWrapper<JwSystemAuth> wrapper);
	
	/**
     * 查询所有的权限（菜单权限和按钮功能权限）
     * @return  <pre>List<MenuFunction>:所有的权限
     *             <li>authNam:       权限名称</li>
     *             <li>authDesc:      权限说明</li>
     *             <li>authContr:     权限控制</li>
     *             <li>authId:        权限编码</li>
     *             <li>authType:      菜单类型</li>
     *             <li>parentAuthId:  上一级菜单编码</li>
     */
	public List<MenuFunction> queryMenuAndFuncAuth();
	
	/**
     * 查询所有的权限（菜单权限和按钮功能权限）
     * @param <pre>
     *             <li>roleId : 角色编码</li>
     *        </pre>
     *             
     * @return  <pre>List<MenuFunction>:所有的权限
     *             <li>authNam:       权限名称</li>
     *             <li>authDesc:      权限说明</li>
     *             <li>authContr:     权限控制</li>
     *             <li>authId:        权限编码</li>
     *             <li>authType:      菜单类型</li>
     *             <li>parentAuthId:  上一级菜单编码</li>
     *          </pre>
     */
	public List<MenuFunction> queryMenuAndFuncAuthByRoleId(String roleId);
	
	/**根据权限编码查询权限菜单
     * @param 
     * @return
     * @author：junfeng.zhou
     */
    public Menu queryMenuByAuthId(String authId);
	
    /**
     * 删除角色下所有关联的权限
     * @param <pre>
     *             <li>roleId : 角色编码</li>
     *        </pre>
     *             
     * @return  
     * @author：junfeng.zhou
     */
    public void deleteRoleAuthRels(String roleId);
    
    /**
     * 删除角色下所有关联的权限
     * @param <pre>
     *             <li>roleId : 角色编码</li>
     *             <li>authId : 权限编码</li>
     *        </pre>
     *             
     * @return  
     * @author：junfeng.zhou
     */
    public void insertRoleAuthRels(@Param("roleId")String roleId,@Param("authId")String authId);
    
    public List<Menu> queryMenuByUserIdAndParentAuthId(@Param("userId")String userId, @Param("parentAuthId")String parentAuthId);
    
    /**
     * 查询所有的权限（菜单权限和按钮功能权限）
     * @param <pre>
     *             <li>userId : 用户编码</li>
     *        </pre>
     *             
     * @return  <pre>List<Auth>:所有的权限
     *             <li>userId:        用户编码</li>
     *             <li>authContr:     权限控制</li>
     *          </pre>
     */
	public List<Auth> queryAuthByUserId(String userId);
	
	
	/**
	 * 根据authContr查询权限
	 * @param authContr
	 * @return
	 */
	public List<Auth> queryAuthByAuthContr(String authContr);
	
	/**
     * 查询所有的权限（菜单权限和按钮功能权限）
     * @param <pre>
     *             <li>userId : 用户编码</li>
     *        </pre>
     *             
     * @return  <pre>List<Auth>:所有的权限
     *             <li>userId:        用户编码</li>
     *             <li>authContr:     权限控制</li>
     *          </pre>
     */
	public List<Auth> queryAuthByUserIdAndAuthContr(@Param("userId")String userId,@Param("authContr")String authContr);
	/**
	 * 通过权限id查询层级
	 * @param AuthId 权限Id
	 * @return
	 */
	public JwSystemAuth queryOneByAuthId(String authId);

	//update-begin--Author:zhangweijian  Date: 20181107 for：查询父级下面的所有子集元素
	/**
	 * @功能：查询父级下面的所有子集元素
	 * @param id
	 * @return
	 */
	public List<JwSystemAuth> queryByParentAuthId(String parentAuthId);
	//update-end--Author:zhangweijian  Date: 20181107 for：查询父级下面的所有子集元素
}

