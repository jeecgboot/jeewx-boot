package com.jeecg.p3.open.web.back;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.commonweixin.util.HttpUtil;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

 /**
 * 描述：</b>WeixinOpenAccountController<br>
 * @author huangqingquan
 * @since：2016年12月05日 17时50分49秒 星期一
 * @version:1.0
 */
@Controller
@RequestMapping("/commonweixin/back/weixinOpenAccount")
public class WeixinOpenAccountController extends BaseController{
  @Autowired
  private WeixinOpenAccountService weixinOpenAccountService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  //重置第三方平台AccessToken接口
  private static String GET_COMPONENT_ACCESS_ACTOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_component_token";
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinOpenAccount query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	VelocityContext velocityContext = new VelocityContext();
	 	String viewName = "open/back/weixinOpenAccount-list.vm";
	 	try {
		 	PageQuery<WeixinOpenAccount> pageQuery = new PageQuery<WeixinOpenAccount>();
		 	pageQuery.setPageNo(pageNo);
		 	pageQuery.setPageSize(pageSize);
			pageQuery.setQuery(query);
			velocityContext.put("weixinOpenAccount",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinOpenAccountService.queryPageList(pageQuery)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 重置accessToken
 * @param response
 * @param request
 * @return
 */
@ResponseBody
@RequestMapping(value="resetAccessToken",method = {RequestMethod.GET,RequestMethod.POST})
public AjaxJson resetAccessToken(HttpServletResponse response,HttpServletRequest request){
	AjaxJson j=new AjaxJson();
	try {
		//String componentAppsecret = p.readProperty("component_appsecret");
		String url=GET_COMPONENT_ACCESS_ACTOKEN_URL;
		WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(CommonWeixinProperties.component_appid);
		if(weixinOpenAccount!=null){
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("component_appid", CommonWeixinProperties.component_appid);
			param.put("component_appsecret", weixinOpenAccount.getAppsecret());//从数据库中获取秘钥
			param.put("component_verify_ticket", weixinOpenAccount.getTicket());
			JSONObject jsonObject = new JSONObject(param);
			JSONObject jsonObj = HttpUtil.httpRequest(url, "POST", jsonObject.toString());
			log.info("重置第三方平台ACCESSTOKEN时返回的报文"+jsonObj);
			if(jsonObj!=null&&jsonObj.containsKey("component_access_token")){
				weixinOpenAccount.setComponentAccessToken(jsonObj.getString("component_access_token"));
				weixinOpenAccount.setGetAccessTokenTime(new Date());
				weixinOpenAccountService.doEdit(weixinOpenAccount);

				//---------------------第三方平台账号重置token后写入redis缓存-----------------------------
				try {
					WeixinAccount po = new WeixinAccount();
					po.setAccountappid(weixinOpenAccount.getAppid());
					po.setAccountappsecret(weixinOpenAccount.getAppsecret());
					po.setAccountaccesstoken(weixinOpenAccount.getComponentAccessToken());
					po.setAddtoekntime(weixinOpenAccount.getGetAccessTokenTime());
					po.setWeixinAccountid(weixinOpenAccount.getAppid());//APPID
					JedisPoolUtil.putWxAccount(po);
				} catch (Exception e) {
					log.error("----------第三方平台账号重置TOKEN错误-------------"+e.toString());
					e.printStackTrace();
				}
				//---------------------第三方平台账号重置token后写入redis缓存-----------------------------

				j.setMsg("重置ACCESSTOKEN成功");
			}else{
				j.setMsg("重置ACCESSTOKEN失败");
			}
		}else {
			j.setMsg("重置ACCESSTOKEN失败，未配置第三方平台账号！");
		}
	} catch (Exception e) {
		e.printStackTrace();
		j.setSuccess(false);
		j.setMsg("重置ACCESSTOKEN失败");
	}
	return j;
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddaccessToken(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "open/back/weixinOpenAccount-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinOpenAccount weixinOpenAccount){
	AjaxJson j = new AjaxJson();
	try {
		weixinOpenAccountService.doAdd(weixinOpenAccount);
		j.setMsg("保存成功");
	} catch (Exception e) {
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
		 WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryById(id);
		 velocityContext.put("weixinOpenAccount",weixinOpenAccount);
		 String viewName = "open/back/weixinOpenAccount-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinOpenAccount weixinOpenAccount){
	AjaxJson j = new AjaxJson();
	try {
		weixinOpenAccountService.doEdit(weixinOpenAccount);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}
}

