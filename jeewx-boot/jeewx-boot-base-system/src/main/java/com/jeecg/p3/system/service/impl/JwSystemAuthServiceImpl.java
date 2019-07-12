package com.jeecg.p3.system.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.system.dao.JwSystemAuthDao;
import com.jeecg.p3.system.entity.JwSystemAuth;
import com.jeecg.p3.system.service.JwSystemAuthService;
import com.jeecg.p3.system.vo.Auth;
import com.jeecg.p3.system.vo.Menu;
import com.jeecg.p3.system.vo.MenuFunction;

@Service("jwSystemAuthService")
public class JwSystemAuthServiceImpl implements JwSystemAuthService {
	@Resource
	private JwSystemAuthDao jwSystemAuthDao;

	@Override
	public void doAdd(JwSystemAuth jwSystemAuth) {
		jwSystemAuth.setDelStat("0");
		if(StringUtils.isEmpty(jwSystemAuth.getParentAuthId())){
			jwSystemAuth.setBizLevel("1");
		}else{
			JwSystemAuth jwSystemAuth2 = jwSystemAuthDao.queryOneByAuthId(jwSystemAuth.getParentAuthId());
			if(jwSystemAuth2!=null&&StringUtils.isNotEmpty(jwSystemAuth2.getBizLevel())){
				jwSystemAuth.setBizLevel(Integer.parseInt(jwSystemAuth2.getBizLevel())+1+"");
			}else{
				jwSystemAuth.setBizLevel("1");
			}
		}
		jwSystemAuthDao.insert(jwSystemAuth);
	}

	@Override
	public void doEdit(JwSystemAuth jwSystemAuth) {
		if(StringUtils.isEmpty(jwSystemAuth.getParentAuthId())){
			jwSystemAuth.setBizLevel("1");
		}else{
			JwSystemAuth jwSystemAuth2 = jwSystemAuthDao.queryOneByAuthId(jwSystemAuth.getParentAuthId());
			if(jwSystemAuth2!=null&&StringUtils.isNotEmpty(jwSystemAuth2.getBizLevel())){
				jwSystemAuth.setBizLevel(Integer.parseInt(jwSystemAuth2.getBizLevel())+1+"");
			}else{
				jwSystemAuth.setBizLevel("1");
			}
		}
		jwSystemAuthDao.update(jwSystemAuth);
	}

	@Override
	public void doDelete(Long id) {
		//update-begin--Author:zhangweijian  Date: 20181107 for：递归删除父子级元素
		JwSystemAuth jwSystemAuth = jwSystemAuthDao.get(id);
		String parentAuthId = jwSystemAuth.getAuthId();
		List<JwSystemAuth> lists = jwSystemAuthDao.queryByParentAuthId(parentAuthId);
		jwSystemAuthDao.delete(id);
		if(lists!=null&&lists.size()>0){
			for(JwSystemAuth list:lists){
				this.doDelete(list.getId());
			}
		}
		//update-end--Author:zhangweijian  Date: 20181107 for：递归删除父子级元素
	}
	
	@Override
	public JwSystemAuth queryById(Long id) {
		JwSystemAuth jwSystemAuth  = jwSystemAuthDao.get(id);
		return jwSystemAuth;
	}

	@Override
	public PageList<JwSystemAuth> queryPageList(
		PageQuery<JwSystemAuth> pageQuery) {
		PageList<JwSystemAuth> result = new PageList<JwSystemAuth>();
		Integer itemCount = jwSystemAuthDao.count(pageQuery);
		PageQueryWrapper<JwSystemAuth> wrapper = new PageQueryWrapper<JwSystemAuth>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemAuth> list = jwSystemAuthDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		LinkedList<JwSystemAuth> linklist = new LinkedList<JwSystemAuth>();
		//update-begin--Author:zhangweijian  Date: 20180809 for：菜单管理数据递归处理
		for(JwSystemAuth auth :list){
			if(StringUtils.isEmpty(auth.getParentAuthId())){
				linklist.add(auth);
				List<JwSystemAuth> clist=getAuth(auth,list);
				linklist.addAll(clist);
			}
		}
		//update-end--Author:zhangweijian  Date: 20180809 for：菜单管理数据递归处理
		result.setValues(linklist);
		return result;
	}
	
	//update-begin--Author:zhangweijian  Date: 20180809 for：菜单管理数据递归处理
	/**
	 * @功能：递归获取下级对象
	 * @param auth
	 * @param list
	 * @param linklist
	 * @return 
	 */
	private List<JwSystemAuth> getAuth(JwSystemAuth auth, List<JwSystemAuth> list) {
		List<JwSystemAuth> clist=new ArrayList<JwSystemAuth>();
		for(JwSystemAuth auth2:list){
			if(auth.getAuthId().equals(auth2.getParentAuthId())){
				clist.add(auth2);
				List<JwSystemAuth> clist2=getAuth(auth2,list);
				clist.addAll(clist2);
			}
		}
		return clist;
	}
	//update-end--Author:zhangweijian  Date: 20180809 for：菜单管理数据递归处理
	@Override
	public List<MenuFunction> queryMenuAndFuncAuth() {
		return jwSystemAuthDao.queryMenuAndFuncAuth();
	}

	@Override
	public List<MenuFunction> queryMenuAndFuncAuthByRoleId(String roleId) {
		return jwSystemAuthDao.queryMenuAndFuncAuthByRoleId(roleId);
	}

	@Override
	public Menu queryMenuByAuthId(String authId) {
		return jwSystemAuthDao.queryMenuByAuthId(authId);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void modifyOperateRoleAuthRel(String roleId,List<String> authIds) {
		this.jwSystemAuthDao.deleteRoleAuthRels(roleId);
		if(authIds!=null&&authIds.size()>0){
			for(String authId:authIds){
				this.jwSystemAuthDao.insertRoleAuthRels(roleId, authId);
			}
		}
	}

	@Override
	public LinkedHashMap<Menu, ArrayList<Menu>> getSubMenuTree(String userId, String parentAuthId) {
		/*所有子菜单*/
    	List<Menu> allSubMenuList = getAllSubMenuList(userId, parentAuthId, new ArrayList<Menu>());

    	LinkedHashMap<Menu, ArrayList<Menu>> result = new LinkedHashMap<Menu, ArrayList<Menu>>();
    	
    	for(Menu menu: allSubMenuList){
    		if(isParentMenu(menu, allSubMenuList)){
    			ArrayList<Menu> subMenuList = getSubMenuList(allSubMenuList, menu.getAuthId());
    			result.put(menu, subMenuList);
    		}else if(!isSonMenu(menu, allSubMenuList)) {
    			result.put(menu, null);
    		}
    	}
    	
    	return result;
	}
	
	
	@Override
	public List<Menu> getMenuTree(String userId) {
		/*所有子菜单*/
    	List<Menu> allSubMenuList = getMenuList(userId, null);
    	return allSubMenuList;
	}
	
	
	/**
     * 根据用户编码和父菜单编码获取当前父菜单下的所有子菜单
     * @param userId
     * @param parentAuthId
     * @param allSubMenu
     * @return List<Menu>
     */
	private List<Menu> getMenuList(String userId, String parentAuthId) {
		List<Menu> curSubMenu = jwSystemAuthDao.queryMenuByUserIdAndParentAuthId(userId, parentAuthId);
		/*叶子节点菜单*/
		for (Menu menu : curSubMenu) {
			List<Menu> allSubMenu = getSubMenuList(userId, menu.getAuthId());
			menu.setChildMenu(allSubMenu);
		}
		return curSubMenu;
	}
	
	/**
     * 根据用户编码和父菜单编码获取当前父菜单下的所有子菜单
     * @param userId
     * @param parentAuthId
     * @param allSubMenu
     * @return List<Menu>
     */
	private List<Menu> getSubMenuList(String userId, String parentAuthId) {
		List<Menu> curSubMenu = jwSystemAuthDao.queryMenuByUserIdAndParentAuthId(userId, parentAuthId);
		for (Menu menu : curSubMenu) {
			List<Menu> subMenu = getSubMenuList(userId, menu.getAuthId());
			menu.setChildMenu(subMenu);
		}
		return curSubMenu;
	}
	
	/**
     * 根据用户编码和父菜单编码获取当前父菜单下的所有子菜单
     * @param userId
     * @param parentAuthId
     * @param allSubMenu
     * @return List<Menu>
     */
	private List<Menu> getAllSubMenuList(String userId, String parentAuthId, List<Menu> allSubMenu) {
		List<Menu> curSubMenu = jwSystemAuthDao.queryMenuByUserIdAndParentAuthId(userId, parentAuthId);
		/*叶子节点菜单*/
		if(curSubMenu.size() == 0)
			return allSubMenu;
		for (Menu menu : curSubMenu) {
			allSubMenu.add(menu);
			int allNum = allSubMenu.size();
			allSubMenu = getAllSubMenuList(userId, menu.getAuthId(), allSubMenu);
			int tmpNum = allSubMenu.size();
			/*叶子节点*/
			if(allNum == tmpNum)
				continue;
		}
		return allSubMenu;
	}
	
	/**
     * 判断当前菜单是否属于父菜单方法
     * @param 
     * @return
     */
    private Boolean isParentMenu(Menu curMenu, List<Menu> subMenuList){
    	//菜单的父菜单id在list中存在，表示此菜单属于子菜单
    	for(Menu menu : subMenuList){
    		if(curMenu.getAuthId().equals(menu.getParentAuthId())){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
     * 判断当前菜单是否属于子菜单方法
     * @param curMenu
     * @param subMenuList
     * @return Boolean
     */
    private Boolean isSonMenu(Menu curMenu, List<Menu> subMenuList){
    	for(Menu menu : subMenuList){
    		if(menu.getAuthId().equals(curMenu.getParentAuthId())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * 根据父菜单id获取其子菜单列表方法
     * @param 
     * @return
     */
    private ArrayList<Menu> getSubMenuList(List<Menu> subMenuList, String parentId){
    	ArrayList<Menu> result = new ArrayList<Menu>();
    	for(Menu menu : subMenuList){
    		if(parentId.equals(menu.getParentAuthId())){
    			result.add(menu);
    		}
    	}
    	return result;
    }

	@Override
	public List<Auth> queryAuthByUserId(String userId) {
		return jwSystemAuthDao.queryAuthByUserId(userId);
	}

	@Override
	public List<Auth> queryAuthByAuthContr(String authContr) {
		return jwSystemAuthDao.queryAuthByAuthContr(authContr);
	}

	@Override
	public List<Auth> queryAuthByUserIdAndAuthContr(String userId,
			String authContr) {
		return jwSystemAuthDao.queryAuthByUserIdAndAuthContr(userId, authContr);
	}
	
}
