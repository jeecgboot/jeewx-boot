package com.jeecg.p3.cms.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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

import com.jeecg.p3.cms.def.CmsProperties;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.enums.CmsSiteTemplateEnum;
import com.jeecg.p3.cms.exception.BusinessException;
import com.jeecg.p3.cms.service.CmsSiteService;

 /**
 * 描述：</b>网站管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时53分26秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/cms/back/cmsSite")
public class CmsSiteController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(CmsSiteController.class);
  @Autowired
  private CmsSiteService cmsSiteService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute CmsSite query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		try {
			VelocityContext velocityContext = new VelocityContext();
			//获取微信公众号
			String jwid =  (String) request.getSession().getAttribute("jwid");
			if(oConvertUtils.isEmpty(jwid)){
				 throw new BusinessException("获取微信公众号失败！");
			}
			
			query.setJwid(jwid);
			String createBy=(String) request.getSession().getAttribute("system_userid");
			if(oConvertUtils.isEmpty(createBy)){
				throw new BusinessException("登录信息获取失败");
			}
			if(StringUtils.isNotEmpty(createBy)){
				query.setCreateName(createBy);
			}
			
			Integer count = 1;
			String defaultJwid = CmsProperties.defaultJwid;
			if(defaultJwid.equals(jwid)){
				count = cmsSiteService.queryByJwidAndCreateBy(jwid, createBy);
			}else{
				count = cmsSiteService.queryByJwidAndCreateBy(jwid, null);
			}
			if(count > 0) {
				velocityContext.put("flag",false);
			} else {
				velocityContext.put("flag",true);
			}
			PageQuery<CmsSite> pageQuery = new PageQuery<CmsSite>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			pageQuery.setQuery(query);
			velocityContext.put("cmsSite",query);
			velocityContext.put("templateStyle", CmsSiteTemplateEnum.getAllEnumData());
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(cmsSiteService.queryPageList(pageQuery)));
			String viewName = "cms/back/cmsSite-list.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void cmsSiteDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/back/cmsSite-detail.vm";
		CmsSite cmsSite = cmsSiteService.queryById(id);
		velocityContext.put("cmsSite",cmsSite);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "cms/back/cmsSite-add.vm";
	//update-begin-author:taoYan date:20181018 for：cms默认appid设置---
	 String jwid =  (String) request.getSession().getAttribute("jwid");
	 String defaultJwid = CmsProperties.defaultJwid;
	 if(defaultJwid.equals(jwid)){
		 velocityContext.put("isDefaultJwid",1);
	 }
	//update-end-author:taoYan date:20181018 for：cms默认appid设置---
	 velocityContext.put("templateStyle", CmsSiteTemplateEnum.getAllEnumData());
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute CmsSite cmsSite,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String createBy = (String) request.getSession().getAttribute("system_userid");
		cmsSite.setCreateName(createBy);
		cmsSite.setProjectCode("cms");
//		//update-begin-author:taoYan date:20181018 for：cms默认appid设置---
//		String jwid =  (String) request.getSession().getAttribute("jwid");
//		String defaultJwid = CmsProperties.defaultJwid;
//		if(defaultJwid.equals(jwid)){
//			String appid = WeiXinHttpUtil.getLocalValue("cms", "defaultAppid");
//			cmsSite.setAppid(appid);
//		}
//		//update-end-author:taoYan date:20181018 for：cms默认appid设置---
		cmsSiteService.doAdd(cmsSite);
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
		 CmsSite cmsSite = cmsSiteService.queryById(id);
		 velocityContext.put("cmsSite",cmsSite);
		 velocityContext.put("templateStyle", CmsSiteTemplateEnum.getAllEnumData());
		//update-begin-author:taoYan date:20181018 for：cms默认appid设置---
		 String jwid =  (String) request.getSession().getAttribute("jwid");
		 String defaultJwid = CmsProperties.defaultJwid;
		 if(defaultJwid.equals(jwid)){
			 velocityContext.put("isDefaultJwid",1);
		 }
		//update-end-author:taoYan date:20181018 for：cms默认appid设置---
		 String viewName = "cms/back/cmsSite-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute CmsSite cmsSite,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
//		//update-begin-author:taoYan date:20181018 for：cms默认appid设置---
//		String jwid =  (String) request.getSession().getAttribute("jwid");
//		String defaultJwid = CmsProperties.defaultJwid;
////		if(defaultJwid.equals(jwid)){
////			//TODO 不知道为什么用appid
////			String appid = WeiXinHttpUtil.getLocalValue("cms", "defaultAppid");
////			cmsSite.setAppid(appid);
////		}
//		//update-end-author:taoYan date:20181018 for：cms默认appid设置---
		cmsSiteService.doEdit(cmsSite);
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
@RequestMapping(value="doDelete", method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			cmsSiteService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 生成短链接
 * @param id
 * @return
 */
@RequestMapping(value="getShortUrl",method = RequestMethod.GET)
@ResponseBody
public AjaxJson getShortUrl(@RequestParam(required = true, value = "id" ) String id){
	AjaxJson j=new AjaxJson();
	try {
		CmsSite cmsSite = cmsSiteService.queryById(id);
		String shortUrl = cmsSite.getShortUrl();
		if(StringUtils.isEmpty(shortUrl)){
			String hdurl = cmsSite.getHdurl();
			shortUrl = WeiXinHttpUtil.getShortUrl(hdurl,cmsSite.getJwid());
			if(StringUtils.isEmpty(shortUrl)){
				shortUrl=hdurl;
			}else{
				cmsSite.setShortUrl(shortUrl);
				cmsSiteService.doEdit(cmsSite);
			}
		}
		if(StringUtils.isEmpty(shortUrl)){
			j.setMsg("获取地址失败！");
			j.setSuccess(false);
		}else{
			j.setObj(shortUrl);
			j.setSuccess(true);
			j.setMsg("获取地址成功！");
		}
	} catch (Exception e) {
		j.setMsg("获取地址失败！");
		j.setSuccess(false);
	}
	return j;
}

}

