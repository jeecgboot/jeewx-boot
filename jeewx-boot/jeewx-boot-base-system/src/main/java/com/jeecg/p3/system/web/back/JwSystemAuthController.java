package com.jeecg.p3.system.web.back;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.system.entity.JwSystemAuth;
import com.jeecg.p3.system.entity.JwSystemAuthMutex;
import com.jeecg.p3.system.service.JwSystemAuthMutexService;
import com.jeecg.p3.system.service.JwSystemAuthService;
import com.jeecg.p3.system.util.SystemUtil;
import com.jeecg.p3.system.vo.Menu;
import com.jeecg.p3.system.vo.MenuFunction;
import com.jeecg.p3.system.vo.MenuMutex;

 /**
 * 描述：</b>JwSystemAuthController<br>运营系统权限表; InnoDB free: 9216 kB
 * @author junfeng.zhou
 * @since：2015年12月21日 10时28分27秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwSystemAuth")
public class JwSystemAuthController extends BaseController{
  @Autowired
  private JwSystemAuthService jwSystemAuthService;
  @Autowired
  private JwSystemAuthMutexService jwSystemAuthMutexService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute JwSystemAuth query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "1000") int pageSize) throws Exception{
	 	PageQuery<JwSystemAuth> pageQuery = new PageQuery<JwSystemAuth>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("jwSystemAuth",query);
		try {
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemAuthService.queryPageList(pageQuery)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String viewName = "system/back/jwSystemAuth-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void jwSystemAuthDetail(@RequestParam(required = true, value = "id" ) Long id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "system/back/jwSystemAuth-detail.vm";
		JwSystemAuth jwSystemAuth = jwSystemAuthService.queryById(id);
		velocityContext.put("jwSystemAuth",jwSystemAuth);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemAuth-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute JwSystemAuth jwSystemAuth){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemAuthService.doAdd(jwSystemAuth);
		j.setMsg("保存成功");
	} catch (Exception e) {
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
		 JwSystemAuth jwSystemAuth = jwSystemAuthService.queryById(id);
		 velocityContext.put("jwSystemAuth",jwSystemAuth);
		 String viewName = "system/back/jwSystemAuth-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute JwSystemAuth jwSystemAuth){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemAuthService.doEdit(jwSystemAuth);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("编辑失败");
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
			jwSystemAuthService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 初始化权限
 * @author：junfeng.zhou
 */
@RequestMapping(value="/initAuth",produces="text/plain;charset=UTF-8")
@ResponseBody
public String initAuth(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "roleId", required = true) String roleId) {
	log.info("初始化权限");
	String tree = "";
    try {
        //所有可用的权限
        List<MenuFunction> allAuthList = jwSystemAuthService.queryMenuAndFuncAuth();
        
        //当前角色的权限
        List<MenuFunction> roleAuthList = jwSystemAuthService.queryMenuAndFuncAuthByRoleId(roleId);
       
        tree = SystemUtil.list2TreeWithCheck(allAuthList,roleAuthList);
        log.info("初始化权限: " + tree);
    }catch (Exception e){
    	log.info(e.getMessage());
    }
    return tree;
}

/**
 * 编辑角色权限
 * @author：junfeng.zhou
 */
@RequestMapping(value = "/editRoleAuth",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson editRoleAuth(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "checkedNodes", required = true) String checkedNodes,
		@RequestParam(value = "roleId", required = true) String roleId) {
	AjaxJson j = new AjaxJson();
	log.info("编辑角色权限");
	try {
		List<String> authIds = new ArrayList<String>();
		if(StringUtils.isNotEmpty(checkedNodes)){
			for (String auth : checkedNodes.split(",")) {
				authIds.add(auth);
			}
		}
		MenuMutex menuMutex =  checkAuthMutex(authIds);
		if(menuMutex!=null){
			j.setSuccess(false);
			j.setMsg("角色授权失败：【"+menuMutex.getMenu().getAuthName()+"】与【"+menuMutex.getMutexMenu().getAuthName()+"】权限冲突！");
			j.setObj(menuMutex);
			return j;
		}
		jwSystemAuthService.modifyOperateRoleAuthRel(roleId, authIds);
		j.setSuccess(true);
		j.setMsg("角色授权成功");
		log.info("编辑角色权限完成 ");
	} catch (Exception e) {
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("角色授权失败");
	}
	return j;
}

private MenuMutex checkAuthMutex(List<String> authIds){
	if(authIds==null||authIds.size()<=0){
		return null;
	}
	List<JwSystemAuthMutex> authMutexList = jwSystemAuthMutexService.queryAllAuthMutex();
	//检查互斥权限
	MenuMutex result = new MenuMutex();
	for(JwSystemAuthMutex authMutex:authMutexList){
		if(authIds.contains(authMutex.getAuthId())&&authIds.contains(authMutex.getMutexAuthId())){
			Menu menu = jwSystemAuthService.queryMenuByAuthId(authMutex.getAuthId());
			Menu menuMutex = jwSystemAuthService.queryMenuByAuthId(authMutex.getMutexAuthId());
			result.setMenu(menu);
			result.setMutexMenu(menuMutex);
			return result;
		}
	}
	return null;
}
/**
 * 得到权限树
 * @return
 */
//@RequestMapping(value = "/getAuthTree",method ={RequestMethod.GET, RequestMethod.POST})
//@ResponseBody
//public AjaxJson getAuthTree(@ModelAttribute JwSystemAuth jwSystemAuth){
//	AjaxJson j = new AjaxJson();
//	try {
//		String s1 = "{id:1, pId:0, name:\"test1\" , open:true}";  
//        String s2 = "{id:2, pId:1, name:\"test2\" , open:true}";  
//        String s3 = "{id:3, pId:1, name:\"test3\" , open:true}";  
//        String s4 = "{id:4, pId:2, name:\"test4\" , open:true}";  
//        List<String> lstTree = new ArrayList<String>();  
//        lstTree.add(s1);  
//        lstTree.add(s2);  
//        lstTree.add(s3);  
//        lstTree.add(s4);  
//        //利用Json插件将Array转换成Json格式  
//        JSONArray.fromObject(lstTree).toString();  
//		j.setMsg("编辑成功");
//	} catch (Exception e) {
//		log.info(e.getMessage());
//		j.setSuccess(false);
//		j.setMsg("获取权限失败");
//	}
//	return j;
//}
@RequestMapping(value="/getAuthTree",produces="text/plain;charset=UTF-8")
@ResponseBody
public String getAuthTree(HttpServletRequest request, HttpServletResponse response) {
	String tree = "";
    try {
    	String authId = request.getParameter("authId");
        //所有权限
        List<MenuFunction> allAuthList = jwSystemAuthService.queryMenuAndFuncAuth();
        tree = SystemUtil.listTreeToAuth(allAuthList,authId);
        log.info("权限树: " + tree);
    }catch (Exception e){
    	log.info(e.getMessage());
    }
    return tree;
}
}

