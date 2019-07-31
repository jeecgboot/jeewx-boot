package com.jeecg.p3.goldeneggs.web.back;

import com.jeecg.p3.goldeneggs.def.SystemGoldProperties;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsAwardsService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 /**
 * 描述：</b>WxActGoldeneggsAwardsController<br>奖项表
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分26秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggsAwards")
public class WxActGoldeneggsAwardsController extends BaseController{
  @Autowired
  private WxActGoldeneggsAwardsService wxActGoldeneggsAwardsService;
  @Autowired
  private WxActGoldeneggsRelationService wxActGoldeneggsRelationService;
  private static String defaultJwid = SystemGoldProperties.defaultJwid;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsAwards query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsAwards-list.vm";
		try {
		 	PageQuery<WxActGoldeneggsAwards> pageQuery = new PageQuery<WxActGoldeneggsAwards>();
		 	pageQuery.setPageNo(pageNo);
		 	pageQuery.setPageSize(pageSize);
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			if(defaultJwid.equals(jwid)){
				String createBy ="";
				createBy= ContextHolderUtils.getSession().getAttribute("system_userid").toString();
				query.setCreateBy(createBy);
			}
			
			//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
			String showReturnFlag = request.getParameter("showReturnFlag");
			if(StringUtils.isNotEmpty(showReturnFlag)){
				velocityContext.put("showReturnFlag", showReturnFlag);
			}
			//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
			
			//update-begin-alex Date:20170316 for:检索列表加入当前jwid的条件
			query.setJwid(jwid);
			//update-end-alex Date:20170316 for:检索列表加入当前jwid的条件
			pageQuery.setQuery(query);
			velocityContext.put("wxActGoldeneggsAwards",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsAwardsService.queryPageList(pageQuery)));
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
public void wxActGoldeneggsAwardsDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggsAwards-detail.vm";
		WxActGoldeneggsAwards wxActGoldeneggsAwards = wxActGoldeneggsAwardsService.queryById(id);
		velocityContext.put("wxActGoldeneggsAwards",wxActGoldeneggsAwards);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/back/wxActGoldeneggsAwards-add.vm";
	//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
		String showReturnFlag = request.getParameter("showReturnFlag");
		if(StringUtils.isNotEmpty(showReturnFlag)){
			velocityContext.put("showReturnFlag", showReturnFlag);
		}
		//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsAwards wxActGoldeneggsAwards){
	AjaxJson j = new AjaxJson();
	try {
		//update-begin-alex Date:20170316 for:保存奖品奖项时记录创建人和当前jwid
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		String createBy = ContextHolderUtils.getSession().getAttribute("system_userid").toString();
		
		List<WxActGoldeneggsAwards> queryAwardsByName = wxActGoldeneggsAwardsService.queryAwardsByName(jwid, createBy, wxActGoldeneggsAwards.getAwardsName());
		if (queryAwardsByName.size()>0) {
			j.setMsg("奖项已存在，无需重复增加");
			return j;
		}
		wxActGoldeneggsAwards.setCreateBy(createBy);
		//update-end-alex Date:20170316 for:保存奖品奖项时记录创建人和当前jwid
		wxActGoldeneggsAwards.setJwid(jwid);
		wxActGoldeneggsAwardsService.doAdd(wxActGoldeneggsAwards);
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
		 WxActGoldeneggsAwards wxActGoldeneggsAwards = wxActGoldeneggsAwardsService.queryById(id);
		 velocityContext.put("wxActGoldeneggsAwards",wxActGoldeneggsAwards);
		 String viewName = "goldeneggs/back/wxActGoldeneggsAwards-edit.vm";
		//update-begin--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
			String showReturnFlag = request.getParameter("showReturnFlag");
			if(StringUtils.isNotEmpty(showReturnFlag)){
				velocityContext.put("showReturnFlag", showReturnFlag);
			}
			//update-end--liwenhui Date:2018-3-19 13:40:32 for:增加返回按钮是否显示标识
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsAwards wxActGoldeneggsAwards){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsAwardsService.doEdit(wxActGoldeneggsAwards);
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
			   Boolean used=wxActGoldeneggsRelationService.validUsed(id,null);
			   if(used){
					j.setSuccess(false);
					j.setMsg("该奖项已经被活动使用，不能删除");
				}else{
					wxActGoldeneggsAwardsService.doDelete(id);
					j.setMsg("删除成功");
				}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

