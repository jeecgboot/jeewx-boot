package com.jeecg.p3.message.web.back;

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

import com.jeecg.p3.message.entity.WeixinGroupMessageSendDetail;
import com.jeecg.p3.message.service.WeixinGroupMessageSendDetailService;

 /**
 * 描述：</b>群发日志明细表<br>
 * @author weijian.zhang
 * @since：2018年08月20日 17时49分30秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/message/back/weixinGroupMessageSendDetail")
public class WeixinGroupMessageSendDetailController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinGroupMessageSendDetailController.class);
  @Autowired
  private WeixinGroupMessageSendDetailService weixinGroupMessageSendDetailService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinGroupMessageSendDetail query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinGroupMessageSendDetail> pageQuery = new PageQuery<WeixinGroupMessageSendDetail>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinGroupMessageSendDetail",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGroupMessageSendDetailService.queryPageList(pageQuery)));
		String viewName = "message/back/weixinGroupMessageSendDetail-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinGroupMessageSendDetailDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "message/back/weixinGroupMessageSendDetail-detail.vm";
		WeixinGroupMessageSendDetail weixinGroupMessageSendDetail = weixinGroupMessageSendDetailService.queryById(id);
		velocityContext.put("weixinGroupMessageSendDetail",weixinGroupMessageSendDetail);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "message/back/weixinGroupMessageSendDetail-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinGroupMessageSendDetail weixinGroupMessageSendDetail){
	AjaxJson j = new AjaxJson();
	try {
		weixinGroupMessageSendDetailService.doAdd(weixinGroupMessageSendDetail);
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
		 WeixinGroupMessageSendDetail weixinGroupMessageSendDetail = weixinGroupMessageSendDetailService.queryById(id);
		 velocityContext.put("weixinGroupMessageSendDetail",weixinGroupMessageSendDetail);
		 String viewName = "message/back/weixinGroupMessageSendDetail-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinGroupMessageSendDetail weixinGroupMessageSendDetail){
	AjaxJson j = new AjaxJson();
	try {
		weixinGroupMessageSendDetailService.doEdit(weixinGroupMessageSendDetail);
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
			weixinGroupMessageSendDetailService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

}

