package com.jeecg.p3.system.web.back;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.jeecg.p3.system.entity.JwSystemAct;
import com.jeecg.p3.system.entity.JwSystemProject;
import com.jeecg.p3.system.enums.ActJoinNumEnum;
import com.jeecg.p3.system.exception.BusinessException;
import com.jeecg.p3.system.service.JwSystemActService;
import com.jeecg.p3.system.service.JwSystemProjectService;
import com.jeecg.p3.util.MatrixToImageWriter;

 /**
 * 描述：</b>平台活动表<br>
 * @author Alex
 * @since：2017年09月30日 10时05分08秒 星期六 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwSystemAct")
public class JwSystemActController extends BaseController{
  @Autowired
  private JwSystemActService jwSystemActService;
  @Autowired
  private JwSystemProjectService jwSystemProjectService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute JwSystemAct query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<JwSystemAct> pageQuery = new PageQuery<JwSystemAct>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	//update-begin--Author:zhangweijian  Date: 20180914 for：参与数量查询
	 	//参与人数
	 	if(query.getJoinNum()!=null){
	 		if(query.getJoinNum()==5){
	 			query.setJoinMinNum(5000);
	 		}else{
	 			for(ActJoinNumEnum num:ActJoinNumEnum.values()){
	 				if(Integer.parseInt(num.getCode())==query.getJoinNum()){
	 					String minNum=num.getValue().substring(0,num.getValue().indexOf("~"));
	 					String maxNum=num.getValue().substring(num.getValue().indexOf("~")+1,num.getValue().length());
	 					query.setJoinMinNum(Integer.parseInt(minNum));
	 					query.setJoinMaxNum(Integer.parseInt(maxNum));
	 				}
	 			}
	 		}
	 	}
	 	//update-end--Author:zhangweijian  Date: 20180914 for：参与数量查询
		pageQuery.setQuery(query);
		velocityContext.put("joinNumEnum",ActJoinNumEnum.values());
		velocityContext.put("jwSystemAct",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemActService.queryPageList(pageQuery)));
		String viewName = "system/back/jwSystemAct-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void jwSystemActDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "system/back/jwSystemAct-detail.vm";
		JwSystemAct jwSystemAct = jwSystemActService.queryById(id);
		velocityContext.put("jwSystemAct",jwSystemAct);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemAct-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute JwSystemAct jwSystemAct){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemActService.doAdd(jwSystemAct);
		j.setMsg("保存成功");
	} catch (Exception e) {
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
		 JwSystemAct jwSystemAct = jwSystemActService.queryById(id);
		 velocityContext.put("jwSystemAct",jwSystemAct);
		 String viewName = "system/back/jwSystemAct-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute JwSystemAct jwSystemAct){
	AjaxJson j = new AjaxJson();
	try {
		jwSystemActService.doEdit(jwSystemAct);
		j.setSuccess(true);
		j.setMsg("编辑成功");
	} catch (Exception e) {
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
			jwSystemActService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

private void validateParam(String param,String exMsg){
	if(StringUtils.isEmpty(param)){
		throw new BusinessException(exMsg);
	}
}

//update-begin--Author:zhangweijian  Date: 20171214 for：根据用户获取其活动，下载二维码，获取短链接
/**
 * @author 一个人的城
 * @功能：获取当前用户的活动列表
 */
@RequestMapping(value="/toIndividual",method = {RequestMethod.GET,RequestMethod.POST})
public void toIndividual(@ModelAttribute JwSystemAct query, HttpServletRequest request,HttpServletResponse response,
		@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
		@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "system/back/jwSystemAct-individual.vm";
	try {
		String userId = (String)request.getSession().getAttribute("system_userid");
		if(StringUtils.isEmpty(userId)){
			throw new BusinessException("用户未登录");
		}
		PageQuery<JwSystemAct> pageQuery = new PageQuery<JwSystemAct>();
		query.setCreateName(userId);
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
		pageQuery.setQuery(query);
		List<JwSystemProject> JwSystemProject=new ArrayList<JwSystemProject>();
		JwSystemProject=jwSystemProjectService.queryProjectCode();
		velocityContext.put("JwSystemProject", JwSystemProject);
		velocityContext.put("jwSystemAct",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemActService.queryPageList(pageQuery)));
	} catch (Exception e) {
		e.printStackTrace();
	}
	ViewVelocity.view(request,response,viewName,velocityContext);
}
/**
 * 获取shortUrl
 * @param id
 * @return
 */
@RequestMapping(value="getShortUrl",method = RequestMethod.POST)
@ResponseBody
public AjaxJson getShortUrl(@RequestParam(required = true, value = "id" ) String id){
	AjaxJson j=new AjaxJson();
	try {
		JwSystemAct jwSystemAct = jwSystemActService.queryById(id);
		 String shortUrl = jwSystemAct.getShortUrl();
			if(StringUtils.isEmpty(shortUrl)){
				shortUrl=jwSystemAct.getHdurl();
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
/**
 *  活动二维码下载 （通用方法）
 * @param url  :二维码网址
 */
@RequestMapping(value = "downMatrix", method ={RequestMethod.GET,RequestMethod.POST})
public void downMatrix(@RequestParam(required = true, value = "qrCodeName") String qrCodeName,@RequestParam(required = true, value = "url") String url, HttpServletResponse response, HttpServletRequest request) throws Exception {
	String text = url;
	int width = 500; // 二维码图片宽度
    int height = 500; // 二维码图片高度
    String format = "jpg";// 二维码的图片格式
    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
    hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
    BitMatrix bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
        
        
	// 获取文件名
    String fileName = "";
    if(qrCodeName!=null)
    {
    	fileName = qrCodeName+".jpg";
    }else{
    	fileName = "活动二维码.jpg";
    }
	// 制定浏览器头
	// 在下载的时候这里是英文是没有问题的
	// 如果图片名称是中文需要设置转码
	response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
	OutputStream out = response.getOutputStream();;
	try {
		// 读取文件
		MatrixToImageWriter.writeToStream(bitMatrix, format, out);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (out != null)
			out.close();
	}
}
//update-end--Author:zhangweijian  Date: 20171214 for：根据用户获取其活动，下载二维码，获取短链接

}