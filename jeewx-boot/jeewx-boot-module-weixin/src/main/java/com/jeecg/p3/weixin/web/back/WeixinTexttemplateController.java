package com.jeecg.p3.weixin.web.back;

import java.util.Date;

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
import com.jeecg.p3.commonweixin.util.Constants;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinTexttemplate;
import com.jeecg.p3.weixin.service.WeixinTexttemplateService;

 /**
 * 描述：</b>文本模板表<br>
 * @author weijian.zhang
 * @since：2018年07月13日 12时45分36秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinTexttemplate")
public class WeixinTexttemplateController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinTexttemplateController.class);
  @Autowired
  private WeixinTexttemplateService weixinTexttemplateService;
  @Autowired
	private MyJwWebJwidService myJwWebJwidService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinTexttemplate query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinTexttemplate> pageQuery = new PageQuery<WeixinTexttemplate>();
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
		velocityContext.put("weixinTexttemplate",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinTexttemplateService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinTexttemplate-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinTexttemplateDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "weixin/back/weixinTexttemplate-detail.vm";
		WeixinTexttemplate weixinTexttemplate = weixinTexttemplateService.queryById(id);
		velocityContext.put("weixinTexttemplate",weixinTexttemplate);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "weixin/back/weixinTexttemplate-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinTexttemplate weixinTexttemplate){
	AjaxJson j = new AjaxJson();
	try {
		weixinTexttemplate.setCreateTime(new Date());
		weixinTexttemplateService.doAdd(weixinTexttemplate);
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
		 WeixinTexttemplate weixinTexttemplate = weixinTexttemplateService.queryById(id);
		 velocityContext.put("weixinTexttemplate",weixinTexttemplate);
		 String viewName = "weixin/back/weixinTexttemplate-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinTexttemplate weixinTexttemplate,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		weixinTexttemplate.setUpdateTime(new Date());
		String updateBy = (String)request.getSession().getAttribute(Constants.SYSTEM_USERID);
		weixinTexttemplate.setUpdateBy(updateBy);
		weixinTexttemplateService.doEdit(weixinTexttemplate);
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
			weixinTexttemplateService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

