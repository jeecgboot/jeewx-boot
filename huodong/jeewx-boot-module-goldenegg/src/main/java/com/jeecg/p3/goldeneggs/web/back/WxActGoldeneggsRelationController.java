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

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;

 /**
 * 描述：</b>WxActGoldeneggsRelationController<br>活动奖品明细表
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分29秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsRelation")
public class WxActGoldeneggsRelationController extends BaseController{
  @Autowired
  private WxActGoldeneggsRelationService wxActGoldeneggsRelationService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsRelation query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggsRelation> pageQuery = new PageQuery<WxActGoldeneggsRelation>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  request.getSession().getAttribute("jwid").toString();
 		query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActGoldeneggsRelation",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsRelationService.queryPageList(pageQuery)));
		velocityContext.put("jwid",jwid);
		String viewName = "goldeneggs/back/wxActGoldeneggsPrizesSet.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * @功能：进入奖品数量编辑页面
 * @param response
 * @param request
 * @param id
 * @throws Exception
 */
@RequestMapping(value="/toSetCount",method = {RequestMethod.GET,RequestMethod.POST})
public void toSetCount(HttpServletResponse response,HttpServletRequest request,
		@RequestParam String id) throws Exception{
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "goldeneggs/back/wxActGoldeneggsPrizesSetCount.vm";
	try {
		WxActGoldeneggsRelation wxActGoldeneggsRelation=wxActGoldeneggsRelationService.queryById(id);
		velocityContext.put("wxActGoldeneggsRelation",wxActGoldeneggsRelation);
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
public void wxActGoldeneggsRelationDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsRelation-detail.vm";
		WxActGoldeneggsRelation wxActGoldeneggsRelation = wxActGoldeneggsRelationService.queryById(id);
		velocityContext.put("wxActGoldeneggsRelation",wxActGoldeneggsRelation);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsRelation-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsRelation wxActGoldeneggsRelation){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRelationService.doAdd(wxActGoldeneggsRelation);
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
		 WxActGoldeneggsRelation wxActGoldeneggsRelation = wxActGoldeneggsRelationService.queryById(id);
		 velocityContext.put("wxActGoldeneggsRelation",wxActGoldeneggsRelation);
		 String viewName = "goldeneggs/back/wxActGoldeneggsRelation-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsRelation wxActGoldeneggsRelation){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRelationService.doEdit(wxActGoldeneggsRelation);
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
			wxActGoldeneggsRelationService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

