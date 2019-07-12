package com.jeecg.p3.weixin.web.back;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
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

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.util.Constants;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
import com.jeecg.p3.weixin.enums.WeixinMsgTypeEnum;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinNewstemplateService;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.util.WxErrCodeUtil;

import net.sf.json.JSONObject;

 /**
 * 描述：</b>图文模板表<br>
 * @author weijian.zhang
 * @since：2018年07月13日 12时46分13秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinNewstemplate")
public class WeixinNewstemplateController extends BaseController{

	//图文预览接口
	private static String message_preview_url="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	
	public final static Logger log = LoggerFactory.getLogger(WeixinNewstemplateController.class);
	@Autowired
	private WeixinNewstemplateService weixinNewstemplateService;
	@Autowired
	private WeixinGzuserService weixinGzuserService;
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinNewstemplate query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinNewstemplate> pageQuery = new PageQuery<WeixinNewstemplate>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	//update-begin--Author:zhangweijian  Date: 20180720 for：添加jwid查询条件
	 	String jwid =  request.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
	 	//update-begin--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	//判断是否有权限
		String systemUserid = request.getSession().getAttribute("system_userid").toString();
		//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
		//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		if(jw==null){
	 		query.setJwid("-");
	 	}
	 	//update-end--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	//update-end--Author:zhangweijian  Date: 20180720 for：添加jwid查询条件
		pageQuery.setQuery(query);
		velocityContext.put("weixinNewstemplate",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinNewstemplateService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinNewstemplate-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinNewstemplateDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "weixin/back/weixinNewstemplate-detail.vm";
		WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.queryById(id);
		velocityContext.put("weixinNewstemplate",weixinNewstemplate);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "weixin/back/weixinNewstemplate-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinNewstemplate weixinNewstemplate){
	AjaxJson j = new AjaxJson();
	try {
		//update-begin--Author:zhangweijian  Date: 20180807 for：新增默认未上传
		//'0':未上传；'1'：上传中；'2'：上传成功；'3'：上传失败
		weixinNewstemplate.setUploadType("0");
		//update-end--Author:zhangweijian  Date: 20180807 for：新增默认未上传
		weixinNewstemplate.setTemplateType(WeixinMsgTypeEnum.wx_msg_type_news.getCode());
		weixinNewstemplate.setCreateTime(new Date());
		weixinNewstemplateService.doAdd(weixinNewstemplate);
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
		 WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.queryById(id);
		 velocityContext.put("weixinNewstemplate",weixinNewstemplate);
		 String viewName = "weixin/back/weixinNewstemplate-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinNewstemplate weixinNewstemplate,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		weixinNewstemplate.setUpdateTime(new Date());
		String updateBy = (String)request.getSession().getAttribute(Constants.SYSTEM_USERID);
		weixinNewstemplate.setUpdateBy(updateBy);
		weixinNewstemplateService.doEdit(weixinNewstemplate);
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
			weixinNewstemplateService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

//update-begin--Author:zhangweijian  Date: 20180802 for：上传图文素材到微信
/**
 * 上传图文素材
 * @return
 */
@RequestMapping(value="uploadNewstemplate",method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson uploadNewstemplate(@RequestParam(required = true, value = "id") String id,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String jwid =request.getSession().getAttribute("jwid").toString();
		String message=weixinNewstemplateService.uploadNewstemplate(id,jwid);
		j.setMsg(message);
		j.setSuccess(true);
	} catch (Exception e) {
		j.setMsg("上传失败");
		j.setSuccess(false);
		log.error(e.getMessage());
	}
	return j;
}
//update-end--Author:zhangweijian  Date: 20180802 for：上传图文素材到微信

//update-begin--Author:zhangweijian  Date: 20180820 for：图文预览
/**
 * @功能:图文预览获取用户信息跳转的页面
 */
@RequestMapping(value="getUserForTemplatePreview",method = {RequestMethod.GET,RequestMethod.POST})
public void getUserForTemplatePreview(@ModelAttribute WeixinGzuser query,HttpServletResponse response,HttpServletRequest request,
		@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
		@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,@RequestParam String templateId) throws Exception{
	PageQuery<WeixinGzuser> pageQuery = new PageQuery<WeixinGzuser>();
	pageQuery.setPageNo(pageNo);
	pageQuery.setPageSize(pageSize);
	String jwid=request.getSession().getAttribute("jwid").toString();
	query.setJwid(jwid);
	VelocityContext velocityContext = new VelocityContext();
	pageQuery.setQuery(query);
	velocityContext.put("weixinGzuser",query);	
	velocityContext.put("templateId",templateId);
	velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGzuserService.queryPageList(pageQuery)));
	String viewName = "weixin/back/weixinGzuseTemplatePreview.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * @功能：图文手机端预览
 */
@RequestMapping(value="doPreview",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doPreview(@RequestParam String openid,@RequestParam String templateId,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			//获取群发消息信息
			WeixinNewstemplate newstemplate=weixinNewstemplateService.queryById(templateId);
			if(newstemplate!=null){
				String media_id=newstemplate.getMediaId();
				//获取accessToken
				String jwid=request.getSession().getAttribute("jwid").toString();
				String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
				//调用创建标签接口
				String name="{\"touser\":\""+openid+"\",\"mpnews\":{\"media_id\":\""+media_id+"\"},\"msgtype\":\"mpnews\"}";
				String requestUrl=message_preview_url.replace("ACCESS_TOKEN", accessToken);
				JSONObject jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
				//接口返回信息
				//update-begin--Author:zhangweijian  Date: 20180831 for：接口返回信息
				if(jsonObj.getString("errcode").equals("0")){
	  				j.setMsg("发送成功！");
	  			}else{
	  				String errcode=jsonObj.getString("errcode");
	  				String msg = WxErrCodeUtil.testErrCode(errcode);
			    	j.setMsg("发送失败！"+msg);
	  			}
				//update-end--Author:zhangweijian  Date: 20180831 for：接口返回信息
			}
		} catch (Exception e) {
			j.setSuccess(false);
		}
		return j;
}
//update-end--Author:zhangweijian  Date: 20180820 for：图文预览
}

