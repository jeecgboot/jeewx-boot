package com.jeecg.p3.goldeneggs.web.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
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

import com.jeecg.p3.goldeneggs.def.SystemGoldProperties;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsAwardsService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRecordService;
import com.jeecg.p3.goldeneggs.util.ExcelUtil;

 /**
 * 描述：</b>WxActGoldeneggsRecordController<br>砍价帮砍记录表
 * @author junfeng.zhou
 * @since：2016年06月13日 10时55分39秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsRecord")
public class WxActGoldeneggsRecordController extends BaseController{
  @Autowired
  private WxActGoldeneggsRecordService wxActGoldeneggsRecordService;
  @Autowired
  private WxActGoldeneggsAwardsService WxActGoldeneggsAwardsService;
  private static String defaultJwid = SystemGoldProperties.defaultJwid;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggsRecord> pageQuery = new PageQuery<WxActGoldeneggsRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String prizesStateFlag = request.getParameter("prizesStateFlag");
	 	if(StringUtils.isNotEmpty(prizesStateFlag)){
	 		query.setPrizesState(prizesStateFlag);
	 		velocityContext.put("prizesStateFlag",prizesStateFlag);
	 	}
		pageQuery.setQuery(query);
		
		String jwid = ContextHolderUtils.getSession().getAttribute("jwid").toString();
		
		List<WxActGoldeneggsAwards> awards = null;
		if (defaultJwid.equals(jwid)) {
			String createBy = ContextHolderUtils.getSession()
					.getAttribute("system_userid").toString();
			awards = WxActGoldeneggsAwardsService.queryAwards(jwid,createBy);//奖项
		}else{
			awards = WxActGoldeneggsAwardsService.queryAwards(jwid);//奖项
		}
		velocityContext.put("awards",awards);
		velocityContext.put("wxActGoldeneggsRecord",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsRecordService.queryPageList(pageQuery)));
		String viewName = "goldeneggs/back/wxActGoldeneggsRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsRecord-detail.vm";
		WxActGoldeneggsRecord wxActGoldeneggsRecord = wxActGoldeneggsRecordService.queryById(id);
		velocityContext.put("wxActGoldeneggsRecord",wxActGoldeneggsRecord);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsRecord wxActGoldeneggsRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRecordService.doAdd(wxActGoldeneggsRecord);
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
		 WxActGoldeneggsRecord wxActGoldeneggsRecord = wxActGoldeneggsRecordService.queryById(id);
		 velocityContext.put("wxActGoldeneggsRecord",wxActGoldeneggsRecord);
		 String viewName = "goldeneggs/back/wxActGoldeneggsRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsRecord wxActGoldeneggsRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsRecordService.doEdit(wxActGoldeneggsRecord);
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
			wxActGoldeneggsRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}
/**
 * 导出中奖纪录
 */
@RequestMapping(value = "/exportExcel")
public void exportExcel(HttpServletRequest request,HttpServletResponse response){
    String fileName = "砸金蛋活动抽奖记录";  
    try { 
    	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
    	String actId =  request.getParameter("actId");//返回时的url
    	String prizesStateFlag = request.getParameter("prizesStateFlag");
    	if(StringUtils.isNotEmpty(prizesStateFlag)){
    		fileName = "砸金蛋活动中奖记录";	
    	}
    	List<WxActGoldeneggsRecord> listUser = wxActGoldeneggsRecordService.exportExcel(actId, jwid,prizesStateFlag);
    	ExcelUtil.exportExcel(request, response, listUser, WxActGoldeneggsRecord.class, fileName);
    } catch (Exception e) {  
        e.printStackTrace();  
    } 
}



}

