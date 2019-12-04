package com.jeecg.p3.tmessage.web.back;

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

import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;
import com.jeecg.p3.tmessage.service.WeixinTmessageSendLogService;

 /**
 * 描述：</b>发送模板消息日志表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时24分48秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/tmessage/back/weixinTmessageSendLog")
public class WeixinTmessageSendLogController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinTmessageSendLogController.class);
  @Autowired
  private WeixinTmessageSendLogService weixinTmessageSendLogService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinTmessageSendLog query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinTmessageSendLog> pageQuery = new PageQuery<WeixinTmessageSendLog>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinTmessageSendLog",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinTmessageSendLogService.queryPageList(pageQuery)));
		String viewName = "tmessage/back/weixinTmessageSendLog-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinTmessageSendLogDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "tmessage/back/weixinTmessageSendLog-detail.vm";
		WeixinTmessageSendLog weixinTmessageSendLog = weixinTmessageSendLogService.queryById(id);
		velocityContext.put("weixinTmessageSendLog",weixinTmessageSendLog);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "tmessage/back/weixinTmessageSendLog-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinTmessageSendLog weixinTmessageSendLog){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessageSendLogService.doAdd(weixinTmessageSendLog);
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
		 WeixinTmessageSendLog weixinTmessageSendLog = weixinTmessageSendLogService.queryById(id);
		 velocityContext.put("weixinTmessageSendLog",weixinTmessageSendLog);
		 String viewName = "tmessage/back/weixinTmessageSendLog-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinTmessageSendLog weixinTmessageSendLog){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessageSendLogService.doEdit(weixinTmessageSendLog);
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
			weixinTmessageSendLogService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

