package com.jeecg.p3.open.web.back;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.core.common.WxstoreUtils;
import org.jeewx.api.third.JwThirdAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.commonweixin.util.AccessTokenUtil;
import com.jeecg.p3.commonweixin.util.Constants;
import com.jeecg.p3.commonweixin.util.ContextHolderUtils;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

import net.sf.json.JSONObject;


 /**
 * 描述：扫描授权公众号（依赖第三方平台）
  * 列表页面进入，采用单页面直接展示公众号的信息
 * @author pituo
 * @since：2015年12月21日 16时33分45秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/commonweixin/back/myJwWebJwid3")
public class MyJwWebJwid3Controller extends BaseController{
	private static final Logger LOG = LoggerFactory.getLogger(MyJwWebJwid3Controller.class);
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  @Autowired
  private WeixinOpenAccountService weixinOpenAccountService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute MyJwWebJwid query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonweixin/back/myJwWebJwid-list3.vm";
		try {
			String systemUserid = request.getSession().getAttribute("system_userid").toString();
			if(StringUtils.isEmpty(systemUserid)){
				throw new CommonweixinException("登录人不能为空！");
			}
		 	//自己公众号
			String jwid =  request.getSession().getAttribute("jwid").toString();
			MyJwWebJwid jwWebJwid = myJwWebJwidService.queryByCreateBy(systemUserid,jwid);
			velocityContext.put("jwidFlag","Y");
			if(jwWebJwid==null){
				//默认公众号
				jwWebJwid=myJwWebJwidService.queryByJwid(jwid);
				velocityContext.put("jwidFlag","N");
			}
			if(jwid.equals(CommonWeixinProperties.defaultJwid)){
				velocityContext.put("isDefaultJwid","1");
			}
			velocityContext.put("jwWebJwid",jwWebJwid);
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
public void jwWebJwidDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonweixin/back/myJwWebJwid-detail.vm";
		MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryById(id);
		velocityContext.put("myJwWebJwid",myJwWebJwid);
		 String jwid =  request.getSession().getAttribute("jwid").toString();
		 velocityContext.put("jwid",jwid);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "commonweixin/back/myJwWebJwid-add.vm";
	 String jwid = request.getSession().getAttribute("jwid").toString();
	 velocityContext.put("jwid",jwid);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute MyJwWebJwid myJwWebJwid,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		myJwWebJwid.setAuthType("1");
		Map<String, Object> map = AccessTokenUtil.getAccseeToken(myJwWebJwid.getWeixinAppId(), myJwWebJwid.getWeixinAppSecret());
		if(map.get("accessToken") != null){
			myJwWebJwid.setAccessToken(map.get("accessToken").toString());
			myJwWebJwid.setTokenGetTime((Date) map.get("accessTokenTime"));
			myJwWebJwid.setApiTicket(map.get("apiTicket").toString());
			myJwWebJwid.setApiTicketTime((Date) map.get("apiTicketTime"));
			myJwWebJwid.setJsApiTicket(map.get("jsApiTicket").toString());
			myJwWebJwid.setJsApiTicketTime((Date) map.get("jsApiTicketTime"));
			j.setMsg("公众号授权成功");
		}else{
			j.setMsg("AppId或 AppSecret配置不正确，请检查 ");
			j.setSuccess(true);
		}
		myJwWebJwid.setCreateBy((String)request.getSession().getAttribute(Constants.SYSTEM_USERID));
		MyJwWebJwid myJwWebJwid2 = myJwWebJwidService.queryByJwid(myJwWebJwid.getJwid());
		if(myJwWebJwid2!=null){
			j.setSuccess(false);
			j.setMsg("该微信公众号已存在!");
			return j;
		}
		myJwWebJwidService.doAdd(myJwWebJwid);
	} catch (Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
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
		 MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryById(id);
		 velocityContext.put("myJwWebJwid",myJwWebJwid);
		 String viewName = "commonweixin/back/myJwWebJwid-edit.vm";
		 String jwid = request.getSession().getAttribute("jwid").toString();
		 velocityContext.put("jwid",jwid);
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute MyJwWebJwid myJwWebJwid){
	AjaxJson j = new AjaxJson();
	try {
		Map<String, Object> map = AccessTokenUtil.getAccseeToken(myJwWebJwid.getWeixinAppId(), myJwWebJwid.getWeixinAppSecret());
		if(map.get("accessToken") != null){
			myJwWebJwid.setAccessToken(map.get("accessToken").toString());
			myJwWebJwid.setTokenGetTime((Date) map.get("accessTokenTime"));
			myJwWebJwid.setApiTicket(map.get("apiTicket").toString());
			myJwWebJwid.setApiTicketTime((Date) map.get("apiTicketTime"));
			myJwWebJwid.setJsApiTicket(map.get("jsApiTicket").toString());
			myJwWebJwid.setJsApiTicketTime((Date) map.get("jsApiTicketTime"));
			j.setMsg("公众号授权成功");
		}else{
			j.setMsg("AppId或 AppSecret配置不正确，请检查 ");
			j.setSuccess(false);
		}
		myJwWebJwidService.doEdit(myJwWebJwid);
	} catch (Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		try {
			String jwid = request.getParameter("jwid");
			if(jwid.equals(CommonWeixinProperties.defaultJwid)){
				j.setMsg("不能解绑默认公众号");
				j.setSuccess(false);
				return j;
			}
			myJwWebJwidService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 重置 AccessToken
 * @return
 * @throws Exception
 */
@RequestMapping(value="reset",method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson resetAccessToken(@RequestParam(required = true, value = "id") String id){
	AjaxJson json = new AjaxJson();
	try{
		String resetAccessToken = myJwWebJwidService.resetAccessToken(id);
		if(StringUtils.isNotEmpty(resetAccessToken)){
			if("success".equals(resetAccessToken)){
				json.setMsg("重置token成功");
			}else{
				json.setSuccess(false);
				json.setMsg("重置token失败："+resetAccessToken);
			}
		}else{
			json.setSuccess(false);
			json.setMsg("重置token失败：系统异常");
		}
	}catch(Exception e){
		e.printStackTrace();
		log.info(e.getMessage());
		json.setSuccess(false);
		json.setMsg("重置token失败：系统异常");
	}
	return json;
}
/**
 * 保存图片
 * @return
 */
@RequestMapping(value = "/doUpload",method ={RequestMethod.POST})
@ResponseBody
public AjaxJson doUpload(MultipartHttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		MultipartFile uploadify = request.getFile("file");
        byte[] bytes = uploadify.getBytes();  
        String realFilename=uploadify.getOriginalFilename();
        String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
        String filename=UUID.randomUUID().toString().replace("-", "")+fileExtension;
        String uploadDir = request.getSession().getServletContext().getRealPath("upload/img/commonweixin/");   
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        String sep = System.getProperty("file.separator");  
        File uploadedFile = new File(uploadDir + sep  + filename);  
        FileCopyUtils.copy(bytes, uploadedFile);  
        j.setObj(filename);
        j.setSuccess(true);
		j.setMsg("保存成功");
	} catch (Exception e) {
		e.printStackTrace();
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}
/**
 * 去扫码授权页面
 * @param request
 * @return
 */
@RequestMapping(value = "toSweepCodeAuthorization",method = {RequestMethod.GET,RequestMethod.POST})
public void toSweepCodeAuthorization(HttpServletRequest request,HttpServletResponse response) throws Exception {
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "open/back/myJwWebJwid3-sweepCodeAuthorization.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);
}
@ResponseBody
@RequestMapping(value = "getAuthhorizationUrl")
public AjaxJson getAuthhorizationUrl(HttpServletRequest request) {
	AjaxJson j=new AjaxJson();
	try {
		WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(CommonWeixinProperties.component_appid);
		if(weixinOpenAccount==null){
			throw new CommonweixinException("通过APPID获取WEIXINOPENACCOUNT为空!");
		}
		//获取ACCESSTOKEN
		if(StringUtils.isEmpty(weixinOpenAccount.getComponentAccessToken())){
			throw new CommonweixinException("未获取到第三方平台的ACCESSTOKEN");
		}
		//获取预授权码
		String preAuthCode = JwThirdAPI.getPreAuthCode(CommonWeixinProperties.component_appid, weixinOpenAccount.getComponentAccessToken());
		String authhorizationUrl = CommonWeixinProperties.authhorizationUrl.replace("PRE_AUTH_CODE", preAuthCode);
		String redirect_uri = URLEncoder.encode(CommonWeixinProperties.authhorizationCallBackUrl+"?userId="+request.getSession().getAttribute(Constants.SYSTEM_USERID),"UTF-8");
		authhorizationUrl = authhorizationUrl.replace("REDIRECT_URI", redirect_uri).replace("COMPONENT_APPID", CommonWeixinProperties.component_appid);
		log.info("===========拼接访问授权页面地址===地址为==="+authhorizationUrl+"============");
		j.setObj(authhorizationUrl);
	}catch (CommonweixinException e) {
		e.printStackTrace();
		j.setMsg(e.getMessage());
		j.setSuccess(false);
		log.error("getAuthhorizationUrl error={}",new Object[]{e.getMessage()});
	}catch (Exception e) {
		e.printStackTrace();
		log.error("getAuthhorizationUrl error={}",new Object[]{e});
		j.setMsg("系统异常，请稍后再试!");
		j.setSuccess(false);
	}
	return j;
}

/**
 * 授权回调地址
 * @param request
 * @return
 */
@RequestMapping(value = "callback")
public void callback(HttpServletRequest request,HttpServletResponse response) throws Exception {
	String message="授权成功！";
	VelocityContext velocityContext = new VelocityContext();
	String viewName = "open/back/myJwWebJwid-callback.vm";
	velocityContext.put("message",message);
	try {
		String authCode = request.getParameter("auth_code");
		WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(CommonWeixinProperties.component_appid);

		//调取接口获取平台ACCESSTOKEN
		String componentAccessToken = weixinOpenAccount.getComponentAccessToken();
		if(StringUtils.isEmpty(componentAccessToken)){
			throw new CommonweixinException("授权公共号回调时获取ACCESSTOKEN为空!");
		}

		//调取接口
		String urlFormat = CommonWeixinProperties.getApiQueryAuth.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
		JSONObject json = new JSONObject();
		json.put("component_appid", CommonWeixinProperties.component_appid);
		json.put("authorization_code", authCode);
		log.info("授权公共号回调后调取接口请求参数为：{}",new Object[]{json.toString()});
		JSONObject jsonObject = WxstoreUtils.httpRequest(urlFormat, "POST", json.toString());
		log.info("授权公共号回调后调取接口返回参数为：{}",new Object[]{jsonObject});
		if (jsonObject != null && !jsonObject.containsKey("errcode")) {
			MyJwWebJwid myJwWebJwid = new MyJwWebJwid();
			// 保存授权公众号的部分信息
			myJwWebJwid.setCreateBy(request.getParameter("userId"));
			save(jsonObject, myJwWebJwid);
			// 通过第三方token获取公众号信息
			String getAuthorizerInfoUrl = CommonWeixinProperties.getAuthorizerInfo.replace("COMPONENT_ACCESS_TOKEN", componentAccessToken);
			JSONObject j = new JSONObject();
			// 第三方平台appid
			j.put("component_appid", CommonWeixinProperties.component_appid);
			// 授权用户的appid
			j.put("authorizer_appid", myJwWebJwid.getWeixinAppId());
			JSONObject jsonObj = WxstoreUtils.httpRequest(getAuthorizerInfoUrl, "POST", j.toString());
			log.info("===========授权回调方法===获取授权公众号详细Info==="+jsonObj.toString()+"===========");
			if (jsonObj != null && !jsonObj.containsKey("errcode")) {
				update(jsonObj, myJwWebJwid);
			}
		}
	}catch (CommonweixinException e) {
		e.printStackTrace();
		message="授权失败";
		log.error("授权信息回调方法中，发生错误，错误信息={}",new Object[]{e.getMessage()});

	}catch (Exception e) {
		e.printStackTrace();
		log.error("授权信息回调方法中，发生错误，错误信息={}",new Object[]{e});
		message="授权失败";
	}
	/*PrintWriter pw = null;
	try {
		//response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		pw = response.getWriter();
		pw.write("<h1 style='text-align:center'>"+message+"</h1>");
		pw.flush();
	} finally{
		pw.close();
	}*/
	ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 更新内容
 * @param jsonObj
 * @param myJwWebJwid
 */
private void update(JSONObject jsonObj, MyJwWebJwid myJwWebJwid) {
	try {
		String authorizerInfoStr = jsonObj.getString("authorizer_info");
		String qrcodeUrl = null;
		JSONObject authorizerInfoJson = JSONObject.fromObject(authorizerInfoStr);
		if(authorizerInfoJson.containsKey("qrcode_url")){
			qrcodeUrl = authorizerInfoJson.getString("qrcode_url");
		}
		String nickName = authorizerInfoJson.getString("nick_name");
		String headImg = null;
		if(authorizerInfoJson.containsKey("head_img")&& StringUtils.isNotEmpty(authorizerInfoJson.getString("head_img"))){
			   headImg = authorizerInfoJson.getString("head_img");
			   myJwWebJwid.setHeadimgurl(headImg);
		}
		String serviceTypeInfo = authorizerInfoJson.getString("service_type_info");
		String verifyTypeInfo = authorizerInfoJson.getString("verify_type_info");
		String userName = authorizerInfoJson.getString("user_name");
		String businessInfo = authorizerInfoJson.getString("business_info");
		String alias="";
		if(authorizerInfoJson.containsKey("alias")){
			alias = authorizerInfoJson.getString("alias");
		}
		String authorizationInfoS = jsonObj.getString("authorization_info");
		JSONObject authorization_info_json = JSONObject.fromObject(authorizationInfoS);
		String func_info = authorization_info_json.getString("func_info");
		myJwWebJwid.setWeixinNumber(alias);
		myJwWebJwid.setBusinessInfo(businessInfo);
		myJwWebJwid.setFuncInfo(func_info);
		myJwWebJwid.setName(nickName);
		String fileName = UUID.randomUUID().toString().replace("-", "").toUpperCase()+".jpg";
		String uploadDir =ContextHolderUtils.getSession().getServletContext().getRealPath("upload/img/commonweixin/");
		download(qrcodeUrl, fileName, uploadDir);
		myJwWebJwid.setQrcodeimg(fileName);
		JSONObject json=JSONObject.fromObject(serviceTypeInfo);
		if(json!=null&&json.containsKey("id")){
			int accountType = json.getInt("id");
			if(2==accountType){
				myJwWebJwid.setAccountType("1");
			}else{
				myJwWebJwid.setAccountType("2");
			}
		}
		json=JSONObject.fromObject(verifyTypeInfo);
		if(json!=null&&json.containsKey("id")){
			int authStatus=json.getInt("id");
			if(authStatus==-1){
				myJwWebJwid.setAuthStatus("0");
			}else{
				myJwWebJwid.setAuthStatus("1");
			}
		}
		myJwWebJwid.setJwid(userName);
		//获取apiticket
		Map<String, String> apiTicket = AccessTokenUtil.getApiTicket(myJwWebJwid.getAccessToken());
		if("true".equals(apiTicket.get("status"))){
			myJwWebJwid.setApiTicket(apiTicket.get("apiTicket"));
			myJwWebJwid.setApiTicketTime(new Date());
			myJwWebJwid.setJsApiTicket(apiTicket.get("jsApiTicket"));
			myJwWebJwid.setJsApiTicketTime(new Date());
		}
		//没有授权就新增，授权过就修改
		MyJwWebJwid webJwid = myJwWebJwidService.queryByJwid(userName);
		if(webJwid==null){
			myJwWebJwidService.doAdd(myJwWebJwid);
		}else{
			myJwWebJwid.setId(webJwid.getId());
			myJwWebJwidService.doUpdate(myJwWebJwid);
		}
		//-------H5平台独立公众号，重置redis缓存,将token写到redis-------------------------------------------
		try {
			log.info("----------定时任务：H5平台独立公众号，重置redis缓存token开始-------------");
			WeixinAccount po = new WeixinAccount();
			po.setAccountappid(myJwWebJwid.getWeixinAppId());
			po.setAccountappsecret(myJwWebJwid.getWeixinAppSecret());
			po.setAccountaccesstoken(myJwWebJwid.getAccessToken());
			po.setAddtoekntime(myJwWebJwid.getTokenGetTime());
			po.setAccountnumber(myJwWebJwid.getWeixinNumber());
			po.setApiticket(myJwWebJwid.getApiTicket());
			po.setApiticketttime(myJwWebJwid.getApiTicketTime());
			po.setAccounttype(myJwWebJwid.getAccountType());
			po.setWeixinAccountid(myJwWebJwid.getJwid());//原始ID
			po.setJsapiticket(myJwWebJwid.getJsApiTicket());
			po.setJsapitickettime(myJwWebJwid.getJsApiTicketTime());
			JedisPoolUtil.putWxAccount(po);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("----------定时任务：H5平台独立公众号，重置redis缓存token失败-------------"+e.toString());
		}
		//--------H5平台独立公众号，重置redis缓存---------------------------------------
	} catch (Exception e) {
		e.printStackTrace();
		throw new CommonweixinException("解析授权信息==UPDATE时发生错误"+e.getMessage());
	}
}


/**
 * 保存内容
 * @param jsonObject
 * @param myJwWebJwid
 */
private void save(JSONObject jsonObject,MyJwWebJwid myJwWebJwid) {
	try {
		String authorizationInfoStr = jsonObject.getString("authorization_info");
		JSONObject authorizationInfoJson = JSONObject.fromObject(authorizationInfoStr);
		String authorizerAppid = null;
		if(authorizationInfoJson.containsKey("authorizer_appid")){
			authorizerAppid=authorizationInfoJson.getString("authorizer_appid");
		}else if(jsonObject.containsKey("authorizer_appid")){
			authorizerAppid = jsonObject.getString("authorizer_appid");
		}
		String authorizerAccessToken = authorizationInfoJson.getString("authorizer_access_token");
		String authorizerRefreshToken = authorizationInfoJson.getString("authorizer_refresh_token");
		String funcInfoStr ="";
		if(authorizationInfoJson.containsKey("func_info")){
			funcInfoStr= authorizationInfoJson.getString("func_info");
		}else if(jsonObject.containsKey("func_info")){
			funcInfoStr= jsonObject.getString("func_info");
		}
		myJwWebJwid.setAuthorizationInfo(authorizationInfoStr);
		myJwWebJwid.setAccessToken(authorizerAccessToken);
		myJwWebJwid.setTokenGetTime(new Date());
		myJwWebJwid.setWeixinAppId(authorizerAppid);
		myJwWebJwid.setAuthorizerRefreshToken(authorizerRefreshToken);
		myJwWebJwid.setFuncInfo(funcInfoStr);
		myJwWebJwid.setAuthType("2");
		//授权状态（1正常，2已取消）
		myJwWebJwid.setAuthorizationStatus("1");
	} catch (Exception e) {
		e.printStackTrace();
		throw new CommonweixinException("解析授权信息==DOADD时发生错误"+e.getMessage());
	}
	
}

/**
 * @param urlString
 * @param filename
 * @param savePath
 * @throws IOException
 */
private void download(String urlString, String filename, String savePath) throws IOException {
	OutputStream os=null;
	InputStream is=null;
	try {
		log.info("授权公共号的二维图片地址为：{},保存的文件名：{},保存的路径{}",new Object[]{urlString,filename,savePath});
		 // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    // 输入流
	    is = con.getInputStream();
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	    String sep = System.getProperty("file.separator");  
	    os= new FileOutputStream(savePath+sep+filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	} catch (Exception e) {
		e.printStackTrace();
		LOG.error("============下载图片时出现错误============,error={}",e);
	}finally{
	    if(os!=null){
	    	os.close();
	    }
	    if(is!=null){
	    	is.close();
	    }
	}
}
/**
 * @功能:切换默认公众号
 * @作者:LiuShaoQian
 * @时间:20181126
 * @param request
 * @return ajson
 */
@RequestMapping(value = "/switchDefaultOfficialAcco", method = {RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public AjaxJson switchDefaultOfficialAcco(HttpServletRequest request) {
	AjaxJson ajson = new AjaxJson();
	try {
		String systemUserid = request.getSession().getAttribute("system_userid").toString();
		String jwid = null;
		MyJwWebJwid jwWebJwid = myJwWebJwidService.queryByCreateBy(systemUserid,null);
		if (jwWebJwid==null) {
			jwid = CommonWeixinProperties.defaultJwid;
		}else{
			jwid=jwWebJwid.getJwid();
		}
		request.getSession().setAttribute(Constants.SYSTEM_JWID, jwid);
		MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByJwid(jwid);
		if(myJwWebJwid!=null){
			request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME,myJwWebJwid.getName()); 
		}
	} catch (Exception e) {
		e.printStackTrace();
		ajson.setSuccess(false);
	}
	return ajson;
}

}
