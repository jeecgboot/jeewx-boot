package com.jeecg.p3.system.web.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.MD5Util;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.system.entity.JwSystemAuthMutex;
import com.jeecg.p3.system.entity.JwSystemRole;
import com.jeecg.p3.system.entity.JwSystemUser;
import com.jeecg.p3.system.service.JwSystemAuthMutexService;
import com.jeecg.p3.system.service.JwSystemRoleService;
import com.jeecg.p3.system.service.JwSystemUserService;
import com.jeecg.p3.system.vo.Menu;
import com.jeecg.p3.system.vo.MenuMutex;

 /**
 * 描述：</b>JwSystemUserController<br>运营用户表; InnoDB free: 9216 kB
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分29秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwSystemUser")
public class JwSystemUserController extends BaseController{
  @Autowired
  private JwSystemUserService jwSystemUserService;
  @Autowired
  private JwSystemRoleService jwSystemRoleService;
  @Autowired
  private JwSystemAuthMutexService jwSystemAuthMutexService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute JwSystemUser query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<JwSystemUser> pageQuery = new PageQuery<JwSystemUser>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("jwSystemUser",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemUserService.queryPageList(pageQuery)));
		String viewName = "system/back/jwSystemUser-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void jwSystemUserDetail(@RequestParam(required = true, value = "id" ) Long id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "system/back/jwSystemUser-detail.vm";
		JwSystemUser jwSystemUser = jwSystemUserService.queryById(id);
		velocityContext.put("jwSystemUser",jwSystemUser);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemUser-add.vm";
	//查询所有角色
	 List<JwSystemRole> roleList = jwSystemRoleService.queryAllRoleList();
	 velocityContext.put("roleList",roleList);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute JwSystemUser jwSystemUser,@RequestParam(required = false, value = "roleIds") List<String> roleIds){
	AjaxJson j = new AjaxJson();
	try {
		//根据userId验重用户编码
		Boolean repeat = jwSystemUserService.validReatUserId(jwSystemUser.getUserId(),null);
		if (repeat){
			j.setSuccess(false);
			j.setMsg("用户编码重复,重新输入!");
		} else {
			//检验用户角色中的权限是否有互斥权限
			MenuMutex menuMutex = checkAuthMutex(roleIds);
			if(menuMutex!=null){
				j.setSuccess(false);
				j.setMsg("用户赋角色失败：角色【"+menuMutex.getMenu().getRoleName()+"】:【"+menuMutex.getMenu().getAuthName()+"】\n 与 角色【"+menuMutex.getMutexMenu().getRoleName()+"】:【"+menuMutex.getMutexMenu().getAuthName()+"】权限冲突！");
				j.setObj(menuMutex);
				return j;
			}
			//保持用户信息并且为用户赋角色
			Date date = new Date();
			jwSystemUser.setCreateDt(date);
			if(!StringUtil.isEmpty(jwSystemUser.getPassword())){
				String passwordEncode = MD5Util.MD5Encode(jwSystemUser.getPassword(), "utf-8");
				jwSystemUser.setPassword(passwordEncode);
			}
			jwSystemUserService.doAdd(jwSystemUser,roleIds);
			j.setMsg("保存成功");
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) Long id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 JwSystemUser jwSystemUser = jwSystemUserService.queryById(id);
		 //查询用户角色
		 List<String> roleIds = jwSystemUserService.queryUserRoles(jwSystemUser.getUserId());
 		 //查询所有角色
		 List<JwSystemRole> allRolelist = jwSystemRoleService.queryAllRoleList();
		 List<JwSystemRole> checkedRoleList = new ArrayList<JwSystemRole>(); 
		 if(allRolelist != null && allRolelist.size()>0){
    		//check角色
        	for(JwSystemRole role : allRolelist){
        		if(roleIds.contains(role.getRoleId())){
        			role.setIsChecked(true);
        		}
        		checkedRoleList.add(role);
        	}
    	 }
		 velocityContext.put("roleList",checkedRoleList);
		 velocityContext.put("jwSystemUser",jwSystemUser);
		 String viewName = "system/back/jwSystemUser-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute JwSystemUser jwSystemUser,@RequestParam(required = false, value = "roleIds") List<String> roleIds){
	AjaxJson j = new AjaxJson();
	try {
		Boolean repeat = jwSystemUserService.validReatUserId(jwSystemUser.getUserId(),jwSystemUser.getId().intValue());
		if (repeat){
			j.setSuccess(false);
			j.setMsg("用户编码重复,重新输入!");
		} else {
			//检验用户角色中的权限是否有互斥权限
			MenuMutex menuMutex = checkAuthMutex(roleIds);
			if(menuMutex!=null){
				j.setSuccess(false);
				j.setMsg("用户赋角色失败：角色【"+menuMutex.getMenu().getRoleName()+"】:【"+menuMutex.getMenu().getAuthName()+"】\n 与 角色【"+menuMutex.getMutexMenu().getRoleName()+"】:【"+menuMutex.getMutexMenu().getAuthName()+"】权限冲突！");
				j.setObj(menuMutex);
				return j;
			}
			if(!StringUtil.isEmpty(jwSystemUser.getPassword())){
				jwSystemUser.setPassword(null);
			}
			jwSystemUserService.doEdit(jwSystemUser,roleIds);
			j.setMsg("编辑成功");
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}

/**
 * 跳转到修改密码页面
 * @return
 */
@RequestMapping(value = "/toChangePassword",method ={RequestMethod.GET, RequestMethod.POST})
public void toChangePassword(@RequestParam(required = true, value = "id" ) Long id,HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemUser-changepassword.vm";
	 velocityContext.put("id",id);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 修改密码
 * @return
 */
@RequestMapping(value = "/doChangePassword",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doChangePassword(@ModelAttribute JwSystemUser jwSystemUser,@RequestParam(required = false, value = "roleIds") List<String> roleIds){
	AjaxJson j = new AjaxJson();
	try {
		if(!StringUtil.isEmpty(jwSystemUser.getPassword())){
			String passwordEncode = MD5Util.MD5Encode(jwSystemUser.getPassword(), "utf-8");
			jwSystemUser.setPassword(passwordEncode);
		}else{
			j.setSuccess(false);
			j.setMsg("密码为空");
			return j;
		}
		jwSystemUserService.doChangePassword(jwSystemUser);
		j.setMsg("修改密码成功");
	} catch (Exception e) {
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("修改密码失败");
	}
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) Long id){
		AjaxJson j = new AjaxJson();
		try {
			jwSystemUserService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败:"+e.getMessage());
		}
		return j;
}

private MenuMutex checkAuthMutex(List<String> roleIds){
	if(roleIds==null||roleIds.size()<=0){
		return null;
	}
	//查询所有的互斥规则
	List<JwSystemAuthMutex> authMutexList = jwSystemAuthMutexService.queryAllAuthMutex();
	//根据角色编码查询相关角色下的所有权限
	List<Menu> menuList = jwSystemUserService.queryUserMenuAuth(roleIds);
	Map<String,Menu> map = new HashMap<String,Menu>();
	//检查互斥权限
	MenuMutex result = new MenuMutex();
	if(menuList!=null&&menuList.size()>0){
		for(Menu vo:menuList){
			map.put(vo.getAuthId(), vo);
		}
		for(JwSystemAuthMutex authMutex:authMutexList){
			if(map.containsKey(authMutex.getAuthId())&&map.containsKey(authMutex.getMutexAuthId())){
				Menu menu = map.get(authMutex.getAuthId());
				Menu menuMutex = map.get(authMutex.getMutexAuthId());
				result.setMenu(menu);
				result.setMutexMenu(menuMutex);
				return result;
			}
		}
	}
	return null;
}

/**
 * 分配公众号
 * @return
 * @throws Exception 
 */
@RequestMapping(value="toJwidTree",method = RequestMethod.GET)
public void toPrivilegeTree(@RequestParam(required = true, value = "id" ) Long id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 JwSystemUser jwSystemuser = jwSystemUserService.queryById(id);
		 velocityContext.put("jwSystemuser",jwSystemuser);
		 String viewName = "system/back/jwSystemUser-jwidtree.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}
}

