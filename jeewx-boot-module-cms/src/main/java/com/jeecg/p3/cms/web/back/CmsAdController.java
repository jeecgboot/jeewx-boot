package com.jeecg.p3.cms.web.back;

import com.jeecg.p3.cms.entity.CmsAd;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.exception.BusinessException;
import com.jeecg.p3.cms.service.CmsAdService;
import com.jeecg.p3.cms.service.CmsSiteService;
import com.jeecg.p3.cms.util.CmsCommonUtil;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

 /**
 * 描述：</b>广告管理<br>
 * @author junfeng.zhou
 * @since：2018年09月25日 17时45分31秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/cms/back/cmsAd")
public class CmsAdController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(CmsAdController.class);
  @Autowired
  private CmsAdService cmsAdService;
  @Autowired
  private CmsSiteService cmsSiteService;
  /** 上传图片根路径 */
  @Value("${jeewx.path.upload}")
  private String upLoadPath;

/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute CmsAd query,HttpServletResponse response,HttpServletRequest request,
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
			PageQuery<CmsAd> pageQuery = new PageQuery<CmsAd>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			query.setMainId(mainId);
			pageQuery.setQuery(query);
			velocityContext.put("cmsAd",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(cmsAdService.queryPageList(pageQuery)));
			velocityContext.put("mainId", mainId);
			String viewName = "cms/back/cmsAd-list.vm";
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
public void cmsAdDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "cms/back/cmsAd-detail.vm";
		CmsAd cmsAd = cmsAdService.queryById(id);
		velocityContext.put("cmsAd",cmsAd);
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
	 String viewName = "cms/back/cmsAd-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute CmsAd cmsAd,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String createBy=(String) request.getSession().getAttribute("system_userid");
		cmsAd.setCreateBy(createBy);
		cmsAdService.doAdd(cmsAd);
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
		 CmsAd cmsAd = cmsAdService.queryById(id);
		 velocityContext.put("cmsAd",cmsAd);
		 velocityContext.put("mainId", request.getParameter("mainId"));
		 String viewName = "cms/back/cmsAd-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute CmsAd cmsAd){
	AjaxJson j = new AjaxJson();
	try {
		cmsAdService.doEdit(cmsAd);
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
			cmsAdService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

@RequestMapping(value = "delFile",method ={RequestMethod.POST,RequestMethod.GET})
@ResponseBody
public AjaxJson delFile(HttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		String filepath = request.getParameter("filepath");
		String rootDir = request.getSession().getServletContext().getRealPath("/");
		File file = new File(rootDir + File.separator +CmsCommonUtil.FILE_BIZ_PATH +File.separator+ filepath);
		if(file.exists()){
			file.delete();
		}
		j.setSuccess(true);
		j.setMsg("删除成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("操作失败"+e.getMessage());
	}
	return j;
}

@RequestMapping(value = "doUploadFile",method ={RequestMethod.POST,RequestMethod.GET})
@ResponseBody
public AjaxJson doUploadFile(MultipartHttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		MultipartFile uploadify = request.getFile("file");
        byte[] bytes = uploadify.getBytes();  
        //String uploadDir = request.getSession().getServletContext().getRealPath(CmsCommonUtil.FILE_BIZ_PATH);
		String uploadDir = upLoadPath + "/upload/img/cms";
        String sep = System.getProperty("file.separator");  
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }
        String realFilename=uploadify.getOriginalFilename();
        String filename = CmsCommonUtil.getUploadFileName(realFilename);
        File uploadedFile = new File(uploadDir + sep + filename);  
        FileCopyUtils.copy(bytes, uploadedFile);
        j.setObj(filename);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("操作失败"+e.getMessage());
	}
	return j;
}

}

