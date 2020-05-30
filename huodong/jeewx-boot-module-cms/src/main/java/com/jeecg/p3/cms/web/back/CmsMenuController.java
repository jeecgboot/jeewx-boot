package com.jeecg.p3.cms.web.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.cms.dao.CmsMenuDao;
import com.jeecg.p3.cms.entity.CmsMenu;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.exception.BusinessException;
import com.jeecg.p3.cms.service.CmsMenuService;
import com.jeecg.p3.cms.service.CmsSiteService;
import com.jeecg.p3.cms.util.SimpleTreeIdBuild;

 /**
 * 描述：</b>栏目管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时51分57秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/cms/back/cmsMenu")
public class CmsMenuController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(CmsMenuController.class);
  @Autowired
  private CmsMenuService cmsMenuService;
  @Autowired
  private CmsMenuDao cmsMenuDao;
  @Autowired
  private CmsSiteService cmsSiteService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute CmsMenu query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		try {
			String mainId = request.getParameter("mainId");
			//获取微信公众号
			String jwid =  (String) request.getSession().getAttribute("jwid");
			if(oConvertUtils.isEmpty(jwid)){
				 throw new BusinessException("获取微信公众号失败！");
			}
			String createBy=(String) request.getSession().getAttribute("system_userid");
			if(oConvertUtils.isEmpty(createBy)){
				throw new BusinessException("登录信息获取失败");
			}
			//mainId为空根据jwid和createBy查询站点信息
			if(oConvertUtils.isEmpty(mainId)) {
				CmsSite cmsSite = cmsSiteService.querySiteByJwidAndCreateBy(jwid, createBy);
				if(cmsSite != null) {
					mainId = cmsSite.getId();
				}
			}
			if(oConvertUtils.isEmpty(mainId)) {
				throw new BusinessException("请先创建站点信息");
			}
			PageQuery<CmsMenu> pageQuery = new PageQuery<CmsMenu>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			query.setMainId(mainId);
			pageQuery.setQuery(query);
			velocityContext.put("cmsMenu",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(cmsMenuService.queryPageList(pageQuery)));
			velocityContext.put("mainId", mainId);
			String viewName = "cms/back/cmsMenu-list.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			String viewName = "cms/back/error.vm";
			velocityContext.put("info",  e.getMessage());
			ViewVelocity.view(request,response,viewName,velocityContext);
		}
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void cmsMenuDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/back/cmsMenu-detail.vm";
		CmsMenu cmsMenu = cmsMenuService.queryById(id);
		velocityContext.put("cmsMenu",cmsMenu);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 velocityContext.put("mainId", request.getParameter("mainId"));
	 String viewName = "cms/back/cmsMenu-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute CmsMenu cmsMenu, HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		if (StringUtil.isEmpty(cmsMenu.getParentCode())) {//无上级
	    	cmsMenu.setId(new SimpleTreeIdBuild().getId(this.cmsMenuDao,null));
	    	cmsMenu.setParentCode(null);
		}else{//有上级
			cmsMenu.setId(new SimpleTreeIdBuild().getId(this.cmsMenuDao,cmsMenu.getParentCode()));
		}
		String createBy=(String) request.getSession().getAttribute("system_userid");
		cmsMenu.setCreateBy(createBy);
		cmsMenuService.doAdd(cmsMenu);
		j.setMsg("保存成功");
	} catch (Exception e) {
		log.error(e.getMessage());
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
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 CmsMenu cmsMenu = cmsMenuService.queryById(id);
		 velocityContext.put("cmsMenu",cmsMenu);
		 velocityContext.put("mainId", request.getParameter("mainId"));
		 String viewName = "cms/back/cmsMenu-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute CmsMenu cmsMenu){
	AjaxJson j = new AjaxJson();
	try {
		cmsMenuService.doEdit(cmsMenu);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		log.error(e.getMessage());
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			cmsMenuService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

	/**
	 * 获取栏目信息树形结构
	 * @return
	 */
	@RequestMapping(value = "tree")
	@ResponseBody
	public List<CmsMenu> tree(HttpServletRequest request) {
		String mainId = request.getParameter("mainId");
		List<CmsMenu> list = cmsMenuService.queryAllMenus(mainId);
		return list;
	}

}

