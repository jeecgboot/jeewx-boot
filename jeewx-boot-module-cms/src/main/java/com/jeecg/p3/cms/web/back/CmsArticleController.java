package com.jeecg.p3.cms.web.back;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
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

import com.jeecg.p3.cms.entity.CmsArticle;
import com.jeecg.p3.cms.entity.CmsMenu;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.exception.BusinessException;
import com.jeecg.p3.cms.service.CmsArticleService;
import com.jeecg.p3.cms.service.CmsSiteService;

 /**
 * 描述：</b>文章管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/cms/back/cmsArticle")
public class CmsArticleController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(CmsArticleController.class);
  @Autowired
  private CmsArticleService cmsArticleService;
  @Autowired
  private CmsSiteService cmsSiteService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute CmsArticle query,HttpServletResponse response,HttpServletRequest request,
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
			PageQuery<CmsArticle> pageQuery = new PageQuery<CmsArticle>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			query.setMainId(mainId);
			pageQuery.setQuery(query);
			velocityContext.put("cmsArticle",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(cmsArticleService.queryPageList(pageQuery)));
			
			List<CmsMenu> menuList = cmsArticleService.queryAllMenus(mainId);
			velocityContext.put("menuList",menuList);
			velocityContext.put("mainId", mainId);
			String viewName = "cms/back/cmsArticle-list.vm";
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
public void cmsArticleDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/back/cmsArticle-detail.vm";
		CmsArticle cmsArticle = cmsArticleService.queryById(id);
		velocityContext.put("cmsArticle",cmsArticle);
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
	 String viewName = "cms/back/cmsArticle-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute CmsArticle cmsArticle, HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		cmsArticle.setCode(getRandomCode());
		if("1".equals(cmsArticle.getPublish())){
			cmsArticle.setPublishDate(new Date());
		}
		if(oConvertUtils.isEmpty(cmsArticle.getSummary())) {
	    	cmsArticle.setSummary("");
	    }
		String createBy=(String) request.getSession().getAttribute("system_userid");
		cmsArticle.setCreateBy(createBy);
		cmsArticleService.doAdd(cmsArticle);   
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
		 CmsArticle cmsArticle = cmsArticleService.queryById(id);
		 velocityContext.put("cmsArticle",cmsArticle);
		 velocityContext.put("mainId", request.getParameter("mainId"));
		 String viewName = "cms/back/cmsArticle-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute CmsArticle cmsArticle){
	AjaxJson j = new AjaxJson();
	try {
		System.out.println(11111);
		System.out.println(cmsArticle.getImageHref());
		if("1".equals(cmsArticle.getPublish())){
			cmsArticle.setPublishDate(new Date());
		}
		if(oConvertUtils.isEmpty(cmsArticle.getSummary())) {
	    	cmsArticle.setSummary("");
	    }
		cmsArticleService.doEdit(cmsArticle);
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
			cmsArticleService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 随机生成文章编码
 * @return
 */
public static String getRandomCode() {
	Random rd = new Random();
	int code = rd.nextInt(999 - 100 + 1) + 100;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	return sdf.format(new Date()) + code;
}

/**
 * 更新发布、取消发布操作
 * @param articleId 文章ID
 */
@RequestMapping(value="doUpdatePublish",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson doUpdatePublish(@RequestParam(required = true, value = "articleId" ) String articleId, HttpServletResponse response,HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	try {
		String publish = request.getParameter("publish");
		cmsArticleService.updatePublish(articleId, publish);
		j.setSuccess(true);
	} catch (Exception e) {
		e.printStackTrace();
		j.setSuccess(false);
	}
	return j;
}

@RequestMapping(value = "loadAllMenu")
@ResponseBody
public List<CmsMenu> loadAllMenu(HttpServletRequest request) {
	String mainId = request.getParameter("mainId");
	List<CmsMenu> cmsMenuList = cmsArticleService.queryAllMenus(mainId);
	return cmsMenuList;
}

}

