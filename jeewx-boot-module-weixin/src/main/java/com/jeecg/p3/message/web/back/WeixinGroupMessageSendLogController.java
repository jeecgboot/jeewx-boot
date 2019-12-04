package com.jeecg.p3.message.web.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
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
import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;
import com.jeecg.p3.message.service.WeixinGroupMessageSendLogService;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinNewsitem;
import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinNewsitemService;
import com.jeecg.p3.weixin.service.WeixinNewstemplateService;

 /**
 * 描述：</b>群发消息日志表<br>
 * @author weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/message/back/weixinGroupMessageSendLog")
public class WeixinGroupMessageSendLogController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinGroupMessageSendLogController.class);
  @Autowired
  private WeixinGroupMessageSendLogService weixinGroupMessageSendLogService;
  @Autowired
  private WeixinNewstemplateService weixinNewstemplateService;
  @Autowired
  private WeixinNewsitemService weixinNewsitemService;
  @Autowired
  private WeixinGzuserService weixinGzuserService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinGroupMessageSendLog query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinGroupMessageSendLog> pageQuery = new PageQuery<WeixinGroupMessageSendLog>();
	 	//update-begin--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	String jwid =  request.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
		//判断是否有权限
		String systemUserid = request.getSession().getAttribute("system_userid").toString();
		//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
		//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		if(jw==null){
			query.setJwid("-");
		}
		//update-end--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinGroupMessageSendLog",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGroupMessageSendLogService.queryPageList(pageQuery)));
		String viewName = "message/back/weixinGroupMessageSendLog-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinGroupMessageSendLogDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "message/back/weixinGroupMessageSendLog-detail.vm";
		WeixinGroupMessageSendLog weixinGroupMessageSendLog = weixinGroupMessageSendLogService.queryById(id);
		velocityContext.put("weixinGroupMessageSendLog",weixinGroupMessageSendLog);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "message/back/weixinGroupMessageSendLog-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinGroupMessageSendLog weixinGroupMessageSendLog){
	AjaxJson j = new AjaxJson();
	try {
		weixinGroupMessageSendLogService.doAdd(weixinGroupMessageSendLog);
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
		 WeixinGroupMessageSendLog weixinGroupMessageSendLog = weixinGroupMessageSendLogService.queryById(id);
		 velocityContext.put("weixinGroupMessageSendLog",weixinGroupMessageSendLog);
		 String viewName = "message/back/weixinGroupMessageSendLog-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinGroupMessageSendLog weixinGroupMessageSendLog){
	AjaxJson j = new AjaxJson();
	try {
		weixinGroupMessageSendLogService.doEdit(weixinGroupMessageSendLog);
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
			weixinGroupMessageSendLogService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * @功能：获取群发日志记录
 * @param id
 * @return
 */
@RequestMapping(value="showMoalMessage",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson showMoalMessage(@RequestParam(required = true, value = "id" ) String id){
	AjaxJson j = new AjaxJson();
	try {
		WeixinGroupMessageSendLog groupMessageSendLog=weixinGroupMessageSendLogService.queryById(id);
		j.setObj(groupMessageSendLog);
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
	}
	return j;
}

/**
 * 获取素材信息
 * @return
 */
@RequestMapping(value="getAllUploadNewsTemplate",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson getAllUploadNewsTemplate(@RequestParam String type,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		/*//图片素材
		if(type.equals("image")){
			
		}
		//视频素材
		if(type.equals("video")){
			
		}
		//音频素材
		if(type.equals("voice")){
			
		}*/
		//模板素材
		if(type.equals("mpnews")){
			String jwid =  request.getSession().getAttribute("jwid").toString();
			String uploadType="2";
			List<WeixinNewstemplate> templates=weixinNewstemplateService.getAllItems(jwid,uploadType);
			Map<String,List<WeixinNewsitem>> newsitem = new HashMap<String,List<WeixinNewsitem>>();
			for(int i=0;i<templates.size();i++){
				newsitem.put(templates.get(i).getId(),weixinNewsitemService.queryByNewstemplateId(templates.get(i).getId()));
			}
			j.setObj(newsitem);
			j.setSuccess(true);
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("删除失败");
	}
	return j;
}

/**
 * @功能：群发预览跳转页面
 */
@RequestMapping(value="getUserForPreview",method = {RequestMethod.GET,RequestMethod.POST})
public void getUserForPreview(@ModelAttribute WeixinGzuser query,HttpServletResponse response,HttpServletRequest request,
		@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
		@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,@RequestParam String logid) throws Exception{
	PageQuery<WeixinGzuser> pageQuery = new PageQuery<WeixinGzuser>();
	pageQuery.setPageNo(pageNo);
	pageQuery.setPageSize(pageSize);
	String jwid=request.getSession().getAttribute("jwid").toString();
	query.setJwid(jwid);
	VelocityContext velocityContext = new VelocityContext();
	pageQuery.setQuery(query);
	velocityContext.put("weixinGzuser",query);	
	velocityContext.put("logid",logid);
	velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGzuserService.queryPageList(pageQuery)));
	String viewName = "weixin/back/weixinGzuserForPreview.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);
}


}

