package com.jeecg.p3.goldeneggs.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.core.util.SystemTools;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsShareRecordService;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 /**
 * 描述：</b>分享记录表<br>
 * @author junfeng.zhou
 * @since：2018年10月10日 10时27分14秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsShareRecord")
public class WxActGoldeneggsShareRecordController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WxActGoldeneggsShareRecordController.class);
  @Autowired
  private WxActGoldeneggsShareRecordService wxActGoldeneggsShareRecordService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsShareRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggsShareRecord> pageQuery = new PageQuery<WxActGoldeneggsShareRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("wxActGoldeneggsShareRecord",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsShareRecordService.queryPageList(pageQuery)));
		String viewName = "goldeneggs/back/wxActGoldeneggsShareRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsShareRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsShareRecord-detail.vm";
		WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord = wxActGoldeneggsShareRecordService.queryById(id);
		velocityContext.put("wxActGoldeneggsShareRecord",wxActGoldeneggsShareRecord);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsShareRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsShareRecordService.doAdd(wxActGoldeneggsShareRecord);
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
		 WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord = wxActGoldeneggsShareRecordService.queryById(id);
		 velocityContext.put("wxActGoldeneggsShareRecord",wxActGoldeneggsShareRecord);
		 String viewName = "goldeneggs/back/wxActGoldeneggsShareRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsShareRecordService.doEdit(wxActGoldeneggsShareRecord);
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
			wxActGoldeneggsShareRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

