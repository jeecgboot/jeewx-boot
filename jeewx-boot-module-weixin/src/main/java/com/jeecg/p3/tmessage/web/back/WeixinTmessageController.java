package com.jeecg.p3.tmessage.web.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageList;
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

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.commonweixin.util.HttpUtil;
import com.jeecg.p3.tmessage.entity.WeixinTmessage;
import com.jeecg.p3.tmessage.service.WeixinTmessageService;

 /**
 * 描述：</b>消息模板表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时21分04秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/tmessage/back/weixinTmessage")
public class WeixinTmessageController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinTmessageController.class);
  @Autowired
  private WeixinTmessageService weixinTmessageService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinTmessage query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		String jwid = request.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
	 	PageQuery<WeixinTmessage> pageQuery = new PageQuery<WeixinTmessage>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinTmessage",query);
		PageList<WeixinTmessage> pageList = weixinTmessageService.queryPageList(pageQuery);
		List<WeixinTmessage> tmessgaes = pageList.getValues();
		if(tmessgaes != null && tmessgaes.size() > 0) {
			for (WeixinTmessage tmessgae : tmessgaes) {
				tmessgae.setExample(tmessgae.getExample().replaceAll("\n", "<br/>"));
			}
		}
		pageList.setValues(tmessgaes);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(pageList));
		String viewName = "tmessage/back/weixinTmessage-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinTmessageDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "tmessage/back/weixinTmessage-detail.vm";
		WeixinTmessage weixinTmessage = weixinTmessageService.queryById(id);
		velocityContext.put("weixinTmessage",weixinTmessage);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "tmessage/back/weixinTmessage-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinTmessage weixinTmessage){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessageService.doAdd(weixinTmessage);
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
		 WeixinTmessage weixinTmessage = weixinTmessageService.queryById(id);
		 velocityContext.put("weixinTmessage",weixinTmessage);
		 String viewName = "tmessage/back/weixinTmessage-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinTmessage weixinTmessage){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessageService.doEdit(weixinTmessage);
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
			weixinTmessageService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

	/**
	 * 同步消息模板
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "doSyncTmessages", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doSyncTmessages(HttpServletResponse response,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String jwid =  request.getSession().getAttribute("jwid").toString();
			String accessToken = WeiXinHttpUtil.getRedisWeixinToken(jwid);
			String httpUrl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="+accessToken+"";
			JSONObject jsonObject = HttpUtil.httpRequest(httpUrl, "GET", null);
			log.info("同步消息模板JSONObject：" + jsonObject);
			if(jsonObject.containsKey("template_list")) {
				com.alibaba.fastjson.JSONArray jsonArray = jsonObject.getJSONArray("template_list");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					String templateId = jsonObj.getString("template_id");
					WeixinTmessage weixinTmessage = weixinTmessageService.queryByTemplateId(templateId);
					if(weixinTmessage == null) {
						weixinTmessage = new WeixinTmessage();
					}
					weixinTmessage.setTemplateId(jsonObj.getString("template_id"));	//模板ID
					weixinTmessage.setJwid(jwid);	//公众号ID
					weixinTmessage.setContent(jsonObj.getString("content"));//内容
					weixinTmessage.setIndustry(jsonObj.getString("primary_industry"));//模板所属行业的一级行业
					weixinTmessage.setSubIndustry(jsonObj.getString("deputy_industry"));//模板所属行业的二级行业
					weixinTmessage.setTitle(jsonObj.getString("title"));	//模板标题
					weixinTmessage.setExample(jsonObj.getString("example"));	//模板示例
					weixinTmessage.setCreateDate(new Date());	//创建时间
					if(weixinTmessage != null && oConvertUtils.isNotEmpty(weixinTmessage.getId())) {
						weixinTmessageService.doEdit(weixinTmessage);
					} else {
						weixinTmessageService.doAdd(weixinTmessage);
					}
				}
			} else {
				j.setSuccess(false);
				j.setMsg("同步消息模板失败");
				return j;
			}
			j.setMsg("同步消息模板成功");
			j.setSuccess(true);
		} catch (Exception e) {
			log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("同步消息模板失败");
		}
		return j;
	}


}

