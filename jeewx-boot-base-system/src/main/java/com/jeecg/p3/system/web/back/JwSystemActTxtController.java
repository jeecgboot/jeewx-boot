package com.jeecg.p3.system.web.back;

import cn.hutool.core.img.ImgUtil;
import com.jeecg.p3.system.entity.JwSystemActTxt;
import com.jeecg.p3.system.service.JwSystemActTxtService;
import com.jeecg.p3.system.util.ContextHolderUtils;
import com.jeecg.p3.system.web.dto.JwSystemActTxtDto;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

 /**
 * 描述：</b>JwSystemActTxtController<br>系统文本表
 * @author junfeng.zhou
 * @since：2015年11月11日 11时11分51秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwSystemActTxt")
public class JwSystemActTxtController extends BaseController{
  @Autowired
  private JwSystemActTxtService jwSystemActTxtService;
  /** 上传图片根路径 */
  @Value("${jeewx.path.upload}")
  private String upLoadPath;

/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute JwSystemActTxt query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<JwSystemActTxt> pageQuery = new PageQuery<JwSystemActTxt>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		pageQuery.setQuery(query);
		velocityContext.put("jwSystemActTxt",query);
		velocityContext.put("backurl",backurl);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemActTxtService.queryPageList(pageQuery)));
		String viewName = "system/back/jwSystemActTxt-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void jwSystemActTxtDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "system/back/jwSystemActTxt-detail.vm";
		JwSystemActTxt jwSystemActTxt = jwSystemActTxtService.queryById(id);
		velocityContext.put("jwSystemActTxt",jwSystemActTxt);
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(@ModelAttribute JwSystemActTxt query,HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemActTxt-add.vm";
	 velocityContext.put("jwSystemActTxt",query);
	 String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute JwSystemActTxt jwSystemActTxt){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemActTxtService.doAdd(jwSystemActTxt);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败:"+e.getMessage());
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 JwSystemActTxt jwSystemActTxt = jwSystemActTxtService.queryById(id);
		 velocityContext.put("jwSystemActTxt",jwSystemActTxt);
		 String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
			velocityContext.put("backurl",backurl);
		 String viewName = "system/back/jwSystemActTxt-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody 
public AjaxJson doEdit(@ModelAttribute JwSystemActTxt jwSystemActTxt){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemActTxtService.doEdit(jwSystemActTxt);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败:"+e.getMessage());
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
			jwSystemActTxtService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败:"+e.getMessage());
		}
		return j;
}
	@RequestMapping(value="seniorActText",method = {RequestMethod.GET,RequestMethod.POST})
	public void seniorList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String viewName="";
		VelocityContext velocityContext = new VelocityContext();
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");
		String actCode = request.getParameter("actCode");
		List<JwSystemActTxt> jwSystemActTxts = jwSystemActTxtService.queryListByActCode(actCode);
		velocityContext.put("backurl",backurl);
		velocityContext.put("jwSystemActTxts",jwSystemActTxts);
		viewName = "system/back/jwSystemSeniorActTxt.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
	//@RequestParam(required = true, value = "id" ) String id
	@RequestMapping(value="doAddSeniorActText",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAddSeniorActText(@ModelAttribute JwSystemActTxtDto JwSystemActTxtDto,HttpServletRequest request,HttpServletResponse response) throws Exception{
		AjaxJson j=new AjaxJson();
		try {
			List<JwSystemActTxt> jwSystemActTxts = JwSystemActTxtDto.getJwSystemActTxts();
			if(jwSystemActTxts!=null&&jwSystemActTxts.size()>0){
				for(JwSystemActTxt jwSystemActTxt:jwSystemActTxts){
					jwSystemActTxtService.doEdit(jwSystemActTxt);
				}
			}
			j.setMsg("操作成功");
		} catch (Exception e) {
			j.setMsg("操作失败");
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * 上传图片
	 * 
	 */
	@RequestMapping(value = "doUpload", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public AjaxJson doUpload(MultipartHttpServletRequest request,
	    HttpServletResponse response) {
	  AjaxJson j = new AjaxJson();
	  try {
	    String randomSeed = UUID.randomUUID().toString()
	        .replaceAll("-", "").toUpperCase();
	    MultipartFile uploadify = request.getFile("file");
	    String realFilename = uploadify.getOriginalFilename();
	    String subsimage = realFilename.substring(realFilename.lastIndexOf("."));
	    Date date = new Date();
	    SimpleDateFormat formater = new SimpleDateFormat();
	    formater.applyPattern("yyyy/MM/dd");
	    String dt = formater.format(date).toString();
	    String imgurl ="/"+  dt + "/";
	    String filename = randomSeed + subsimage;
		//String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/fx");
		String uploadDir = upLoadPath + "/upload/img/fx";

		File dir = new File(uploadDir +imgurl);// 定义文件存储路径
	    //log.info("doUpload 图片存储路径:{}",new Object[]{dir});
	    if (!dir.exists()) {
	      dir.mkdirs();
	    }
	    String sep = System.getProperty("file.separator");
	    OutputStream out = new FileOutputStream(dir + sep+ filename);
	    ImgUtil.scale(uploadify.getInputStream(), out, 0.7f);
	    //uploadify.transferTo(dir);// 上传文件到存储路径
	    StringBuffer url = request.getRequestURL();
	    StringBuffer tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("");
	    j.setObj(tempContextUrl+ request.getContextPath()+"/"+"upload/img/fx"+imgurl + filename);
	    j.setSuccess(true);
	    j.setMsg("保存成功");
	  } catch (Exception e) {
	    j.setSuccess(false);
	    j.setMsg("保存失败");
	    e.printStackTrace();
	  }
	  return j;
	}
}

