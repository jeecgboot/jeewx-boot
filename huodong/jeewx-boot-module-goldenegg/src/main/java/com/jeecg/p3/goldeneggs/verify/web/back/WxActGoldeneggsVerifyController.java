package com.jeecg.p3.goldeneggs.verify.web.back;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.jeecgframework.p3.core.util.SignatureUtil;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
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

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsService;
import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;
import com.jeecg.p3.goldeneggs.verify.service.WxActGoldeneggsVerifyService;

 /**
 * 描述：</b>砸金蛋审核员表<br>
 * @author junfeng.zhou
 * @since：2018年10月17日 09时53分08秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/verify/back/wxActGoldeneggsVerify")
public class WxActGoldeneggsVerifyController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WxActGoldeneggsVerifyController.class);
  @Autowired
  private WxActGoldeneggsVerifyService wxActGoldeneggsVerifyService;
  @Autowired
  private WxActGoldeneggsService wxActGoldeneggsService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggsVerify query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggsVerify> pageQuery = new PageQuery<WxActGoldeneggsVerify>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("wxActGoldeneggsVerify",query);
		velocityContext.put("ActId",query.getActId());
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsVerifyService.queryPageList(pageQuery)));
		String viewName = "goldeneggs/verify/back/wxActGoldeneggsVerify-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsVerifyDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/verify/back/wxActGoldeneggsVerify-detail.vm";
		WxActGoldeneggsVerify wxActGoldeneggsVerify = wxActGoldeneggsVerifyService.queryById(id);
		velocityContext.put("wxActGoldeneggsVerify",wxActGoldeneggsVerify);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "goldeneggs/verify/back/wxActGoldeneggsVerify-add.vm";
	 String actId=request.getParameter("actId");
	 String jwid = wxActGoldeneggsService.queryById(actId).getJwid();
	 velocityContext.put("ActId",actId);
	 velocityContext.put("jwid",jwid);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggsVerify wxActGoldeneggsVerify){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsVerifyService.doAdd(wxActGoldeneggsVerify);
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
		 WxActGoldeneggsVerify wxActGoldeneggsVerify = wxActGoldeneggsVerifyService.queryById(id);
		 velocityContext.put("wxActGoldeneggsVerify",wxActGoldeneggsVerify);
		 String actId=request.getParameter("actId");
		 velocityContext.put("ActId",actId);
		 String jwid = wxActGoldeneggsService.queryById(actId).getJwid();
		 velocityContext.put("jwid",jwid);
		 String viewName = "goldeneggs/verify/back/wxActGoldeneggsVerify-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggsVerify wxActGoldeneggsVerify){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsVerifyService.doEdit(wxActGoldeneggsVerify);
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
			wxActGoldeneggsVerifyService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}
/**
 *启用停用
 * @return
 */
@RequestMapping(value="doEditStatus",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEditStatus(@ModelAttribute WxActGoldeneggsVerify wxActGoldeneggsVerify){
	AjaxJson j = new AjaxJson();
	try {
		wxActGoldeneggsVerifyService.doEdit(wxActGoldeneggsVerify);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}
/**
 *获取接口信息
 * @return
 */
@RequestMapping(value="getScanCodeList",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson getScanCodeList(@RequestParam String ewmCode,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String jwid = request.getParameter("jwid");
		PropertiesUtil util = new PropertiesUtil("goldeneggs.properties");
		String defaultJwid = util.readProperty("defaultJwid");
		JSONObject json = getScanCodeList();
		log.info("===扫描二维码返回结果==="+json);
		com.alibaba.fastjson.JSONArray jsonA = json.getJSONArray("scanRecordList");
		Map<String,Object> map=new HashMap<String,Object>();
		String openid = null;
		String nickname = null;
		String headimgurl = null;
		if(jsonA != null && jsonA.size() > 0) {
			for (int i = 0; i < jsonA.size(); i++) {
				com.alibaba.fastjson.JSONObject obj =  jsonA.getJSONObject(i);
				if(obj != null) {
					if(obj.containsKey("validCode") && obj.getString("validCode").equals(ewmCode)) {
						openid=obj.getString("openid");
					}
				}
			}
		}
		if(openid!=null){
			map.put("openid", openid);
			JSONObject userobj = WeiXinHttpUtil.getGzUserInfo(openid, defaultJwid);
			if (userobj != null && userobj.containsKey("nickname")) {
				nickname = userobj.getString("nickname");
				map.put("nickname", nickname);
			}
			if (userobj != null && userobj.containsKey("headimgurl")) {
				headimgurl = userobj.getString("headimgurl");
				map.put("headimgurl", headimgurl);
			}
		}
		j.setAttributes(map);
		j.setObj(openid);
		j.setMsg("查询成功");
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("查询失败");
	}
	return j;
}

/**
 * 接口获取二维码信息
 * @return
 * @throws UnsupportedEncodingException
 */
public static com.alibaba.fastjson.JSONObject getScanCodeList() throws UnsupportedEncodingException {
	PropertiesUtil util = new PropertiesUtil("goldeneggs.properties");
	Map<String, String> paramMap = new HashMap<String, String>();
	paramMap.put("weixinId", util.readProperty("weixinId"));
	paramMap.put("channel", util.readProperty("channel"));
	paramMap.put("sceneId", util.readProperty("sceneId"));
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	Date afterDate = new Date(now.getTime() + 60000);
	Date beforeDate = new Date(now.getTime() - 120000);
	paramMap.put("fromDate", sdf.format(beforeDate));
	paramMap.put("endDate", sdf.format(afterDate));
	String sign = SignatureUtil.sign(paramMap, util.readProperty("scanRecordKey"));
	String h = util.readProperty("validCodeUrl")+"&weixinId="+util.readProperty("weixinId")+"&channel="+util.readProperty("channel")+"&sceneId="+util.readProperty("sceneId")+"&fromDate="+URLEncoder.encode(sdf.format(beforeDate),"utf-8")+"&endDate="+URLEncoder.encode(sdf.format(afterDate),"utf-8")+"&signature="+sign;
	com.alibaba.fastjson.JSONObject object = WeiXinHttpUtil.sendGet(h);
	return object;
}

}

