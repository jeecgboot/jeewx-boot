package com.jeecg.p3.goldeneggs.web.back;

import cn.hutool.core.img.ImgUtil;
import com.jeecg.p3.goldeneggs.def.SystemGoldProperties;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsPrizesService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


 /**
 * 描述：</b>WxActGoldeneggsPrizesController<br>奖品表
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分27秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsPrizes")
public class WxActGoldeneggsPrizesController extends BaseController{
  @Autowired
  private WxActGoldeneggsPrizesService wxActGoldeneggsPrizesService;
  @Autowired
  private WxActGoldeneggsRelationService wxActGoldeneggsRelationService;

  private static String filePath="upload/img/goldeneggs";
  private static String defaultJwid = SystemGoldProperties.defaultJwid;
  /** 上传图片根路径 */
  @Value("${jeewx.path.upload}")
  private String upLoadPath;

/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsPrizes query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsPrizes-list.vm";
	 	try {
	 		PageQuery<WxActGoldeneggsPrizes> pageQuery = new PageQuery<WxActGoldeneggsPrizes>();
		 	pageQuery.setPageNo(pageNo);
		 	pageQuery.setPageSize(pageSize);
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			if(defaultJwid.equals(jwid)){
				String createBy ="";
				createBy= ContextHolderUtils.getSession().getAttribute("system_userid").toString();
				query.setCreateBy(createBy);
			}
			
			//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
			String showReturnFlag = request.getParameter("showReturnFlag");
			if(StringUtils.isNotEmpty(showReturnFlag)){
				velocityContext.put("showReturnFlag", showReturnFlag);
			}
			//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
			
			//update-begin-alex Date:20170316 for:检索列表加入当前jwid的条件
			query.setJwid(jwid);
			//update-end-alex Date:20170316 for:检索列表加入当前jwid的条件		pageQuery.setQuery(query);
			velocityContext.put("wxActGoldeneggsPrizes",query);
			velocityContext.put("doMain", "/upload/img/goldeneggs");
			pageQuery.setQuery(query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsPrizesService.queryPageList(pageQuery)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsPrizesDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsPrizes-detail.vm";
		WxActGoldeneggsPrizes wxActGoldeneggsPrizes = wxActGoldeneggsPrizesService.queryById(id);
		velocityContext.put("wxActGoldeneggsPrizes",wxActGoldeneggsPrizes);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsPrizes-add.vm";
	//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
	String showReturnFlag = request.getParameter("showReturnFlag");
	if(StringUtils.isNotEmpty(showReturnFlag)){
		velocityContext.put("showReturnFlag", showReturnFlag);
	}
	//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
	velocityContext.put("doMain", "/upload/img/goldeneggs");
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsPrizes wxActGoldeneggsPrizes){
	AjaxJson j = new AjaxJson();
	try {
		//update-begin-alex Date:20170316 for:保存奖品奖项时记录创建人和当前jwid
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		String createBy = ContextHolderUtils.getSession().getAttribute("system_userid").toString();
		List<WxActGoldeneggsPrizes> queryPrizesByName = wxActGoldeneggsPrizesService.queryPrizesByName(jwid, createBy, wxActGoldeneggsPrizes.getName());
		if(queryPrizesByName.size()>0){
			j.setMsg("奖品已存在，无需重复增加");
			return j;
		}
		wxActGoldeneggsPrizes.setCreateBy(createBy);
		//update-end-alex Date:20170316 for:保存奖品奖项时记录创建人和当前jwid
		wxActGoldeneggsPrizes.setJwid(jwid);
	
		wxActGoldeneggsPrizesService.doAdd(wxActGoldeneggsPrizes);
		j.setMsg("保存成功");
	} catch (Exception e) {
		e.printStackTrace();
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
		 WxActGoldeneggsPrizes wxActGoldeneggsPrizes = wxActGoldeneggsPrizesService.queryById(id);
		 velocityContext.put("wxActGoldeneggsPrizes",wxActGoldeneggsPrizes);
		 String viewName = "goldeneggs/back/wxActGoldeneggsPrizes-edit.vm";
		//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
		String showReturnFlag = request.getParameter("showReturnFlag");
		if(StringUtils.isNotEmpty(showReturnFlag)){
			velocityContext.put("showReturnFlag", showReturnFlag);
		}
		//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
		velocityContext.put("doMain", "/upload/img/goldeneggs");
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsPrizes wxActGoldeneggsPrizes){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsPrizesService.doEdit(wxActGoldeneggsPrizes);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		e.printStackTrace();
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
			//判断奖品是否被使用
			Boolean used=wxActGoldeneggsRelationService.validUsed(null,id);
			if(used){
				j.setSuccess(false);
				j.setMsg("该奖项已经被活动使用，不能删除");
			}else{
				wxActGoldeneggsPrizesService.doDelete(id);
				j.setMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
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
    String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
    Date date = new Date();
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyy/MM/dd");
    String dt = formater.format(date).toString();
    String imgurl ="/"+  dt + "/" + randomSeed + subsimage;
    String filename = randomSeed + subsimage;
	//String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath(filePath);
	String uploadDir = upLoadPath + "/upload/img/goldeneggs";
	//原始上传方式
	/*File dir = new File(uploadDir +imgurl);// 定义文件存储路径
    log.info("doUpload 图片存储路径:{}",new Object[]{dir});
    if (!dir.exists()) {
      dir.mkdirs();
    }
    uploadify.transferTo(dir);// 上传文件到存储路径*/
	//压缩上传方式
	File dirPath = new File(uploadDir+"/"+  dt + "/");
    if (!dirPath.exists()) {
          dirPath.mkdirs();
      }
    String sep = System.getProperty("file.separator");
    OutputStream out = new FileOutputStream(dirPath + sep+ filename);
    ImgUtil.scale(uploadify.getInputStream(), out, 0.7f);

    j.setObj(imgurl);
    j.setSuccess(true);
    j.setMsg("保存成功");
  } catch (Exception e) {
    j.setSuccess(false);
    j.setMsg("保存失败");
    e.printStackTrace();
  }
  return j;
}
/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doUploadPrize",method ={RequestMethod.POST})
@ResponseBody
public AjaxJson doUploadPrize(MultipartHttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		MultipartFile uploadify = request.getFile("file");
        String realFilename=uploadify.getOriginalFilename();
        String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
        String filename=UUID.randomUUID().toString().replace("-", "")+fileExtension;
        String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString().trim();
        //String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/goldeneggs/"+jwid);
		String uploadDir = upLoadPath + "/upload/img/goldeneggs" +jwid;
        File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        String sep = System.getProperty("file.separator");
        OutputStream out = new FileOutputStream(uploadDir + sep+ filename);
        ImgUtil.scale(uploadify.getInputStream(), out, 0.7f);
        j.setObj(filename);
        j.setSuccess(true);
		j.setMsg("保存成功");
	} catch (Exception e) {
		e.printStackTrace();
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 获取图片流
 * @param imgurl
 * @param response
 * @param request
 * @throws Exception
 */
@RequestMapping(value="getImgUrl",method = RequestMethod.GET)
public void getImgUrl(HttpServletResponse response,HttpServletRequest request) throws Exception{
	response.setContentType("image/jpeg;charset=utf-8");
	InputStream inputStream = null;
	OutputStream outputStream=null;
	try {
		String imgurl =ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath(filePath)+request.getParameter("imgurl");
		inputStream = new BufferedInputStream(new FileInputStream(imgurl));
		outputStream = response.getOutputStream();
		byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        response.flushBuffer();
	} catch (Exception e) {
		e.printStackTrace();
		log.info("通过流的方式获取图片异常={}.",new Object[]{e});
	}finally{
		if(inputStream!=null){
			inputStream.close();
		}
		if(outputStream!=null){
			outputStream.close();
		}
	}
}

}

