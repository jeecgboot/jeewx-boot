package com.jeecg.p3.goldeneggs.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRegistrationService;

 /**
 * 描述：</b>WxActGoldeneggsRegistrationController<br>新人大礼活动参与人员表
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分28秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsRegistration")
public class WxActGoldeneggsRegistrationController extends BaseController{
  @Autowired
  private WxActGoldeneggsRegistrationService wxActGoldeneggsRegistrationService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsRegistration query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggsRegistration> pageQuery = new PageQuery<WxActGoldeneggsRegistration>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("wxActGoldeneggsRegistration",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsRegistrationService.queryPageList(pageQuery)));
		String viewName = "goldeneggs/back/wxActGoldeneggsRegistration-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsRegistrationDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsRegistration-detail.vm";
		WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationService.queryById(id);
		velocityContext.put("wxActGoldeneggsRegistration",wxActGoldeneggsRegistration);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsRegistration-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsRegistration wxActGoldeneggsRegistration){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRegistrationService.doAdd(wxActGoldeneggsRegistration);
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
		 WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationService.queryById(id);
		 velocityContext.put("wxActGoldeneggsRegistration",wxActGoldeneggsRegistration);
		 String viewName = "goldeneggs/back/wxActGoldeneggsRegistration-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsRegistration wxActGoldeneggsRegistration){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRegistrationService.doEdit(wxActGoldeneggsRegistration);
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
			wxActGoldeneggsRegistrationService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

