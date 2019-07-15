package com.jeecg.p3.system.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.system.entity.JwSystemAuth;
import com.jeecg.p3.system.vo.Auth;
import com.jeecg.p3.system.vo.Menu;
import com.jeecg.p3.system.vo.MenuFunction;

/**
 * 描述：</b>JwSystemAuthService<br>
 * @author：junfeng.zhou
 * @since：2015年12月21日 10时28分27秒 星期一 
 * @version:1.0
 */
public interface JwSystemAuthService {
	
	
	public void doAdd(JwSystemAuth jwSystemAuth);
	
	public void doEdit(JwSystemAuth jwSystemAuth);
	
	public void doDelete(Long id);
	
	public JwSystemAuth queryById(Long id);
	
	public PageList<JwSystemAuth> queryPageList(PageQuery<JwSystemAuth> pageQuery);
	
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
     * 更新角色权限
     * @param <pre>
     *             <li>roleId : 角色编码</li>
     *             
     *             <li>List<String> authIds
     *             	   authIds : 权限编码List</li>
     *        </pre>
     *             
     * @return  <pre>List<Auth>:所有的权限
     *             <li>userId:        用户编码</li>
     *             <li>authContr:     权限控制</li>
     *          </pre>
     */
	public void modifyOperateRoleAuthRel(String roleId,List<String> authIds);
	
	/**
     * 根据用户编码和父菜单编码获取当前父菜单下的所有子菜单树
     * @param 
     * @return
     */
    public LinkedHashMap<Menu,ArrayList<Menu>> getSubMenuTree(String userId, String parentAuthId);
    
    
    /**
     * 根据用户编码获取菜单树
     * @param 
     * @return
     */
    public List<Menu> getMenuTree(String userId);
    
    
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
	public List<Auth> queryAuthByUserIdAndAuthContr(String userId,String authContr);
}

