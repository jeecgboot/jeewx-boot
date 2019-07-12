package com.jeecg.p3.open.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.commonweixin.service.impl.WeixinLinksucaiService;
import com.jeecg.p3.commonweixin.util.HttpUtil;
import com.jeecg.p3.commonweixin.util.SignatureUtil;
import com.jeecg.p3.open.service.OpenWxService;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

 /**
 * OPEN: oAuth2.0 素材链接转发中心（支持第三方平台授权）
 * @author scott
 * @since：2016年11月14日
 * @version:1.0
 */
@Controller
@RequestMapping("/weixinLinksucai")
public class WeixinLinksucaiController extends BaseController{
	
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;

  /**
  * 签名密钥key
  */
  private static final String SIGN_KEY = "4B6CAED6F7B19126F72780372E839CC47B1912B6CAED753F";
  private static final Logger logger = LoggerFactory.getLogger(WeixinLinksucaiController.class);
  
  @Autowired
  private WeixinLinksucaiService weixinLinksucaiService;
  @Autowired
  private OpenWxService openWxService;

	/**
	 * 链接跳转
	 */
	@RequestMapping(value = "link" ,method ={RequestMethod.GET, RequestMethod.POST})
	public void link(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jwid = request.getParameter("jwid");
			MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByJwid(jwid);
			//类型：1手动授权，2扫码授权
			if("2".equals(myJwWebJwid.getAuthType())){
				redirectByAuthType2(request, response,"snsapi_base");
			}else{
				redirectByAuthType1(request, response,"snsapi_base");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 链接跳转
	 */
	@RequestMapping(value = "webAuthLink" ,method ={RequestMethod.GET, RequestMethod.POST})
	public void webAuthLink(HttpServletRequest request, HttpServletResponse response) {
		try {
			String jwid = request.getParameter("jwid");
			MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByJwid(jwid);
			if("2".equals(myJwWebJwid.getAuthType())){
				redirectByAuthType2(request, response,"snsapi_userinfo");
			}else{
				redirectByAuthType1(request, response,"snsapi_userinfo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void redirectByAuthType1(HttpServletRequest request,HttpServletResponse response,String SCOPE) throws IOException{
		long start = System.currentTimeMillis();
		logger.info("-------[LINKSTARTIME]--------------开始时间戳------------>"+start);
		// 链接素材ID
		String linkid = request.getParameter("linkid");
		// 微信原始ID
		String jwid = request.getParameter("jwid");
		WeixinAccount weixinAccount= openWxService.getWeixinAccountByWeixinOldId(jwid);
		if (weixinAccount == null) {
			logger.error("------[异常]----------OpenWX----------微信原始ID参数异常,查询公众号失败，原始ID: ------" + jwid);
			return;
		}
		// Step.1 获取微信author2.0 code
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String APPID = weixinAccount.getAccountappid();
		String APPSECRET = weixinAccount.getAccountappsecret();
//		String SCOPE = "snsapi_base";
//		String SCOPE = "snsapi_userinfo";
		if (code == null || code.length() == 0) {
			logger.info("-------[LINKWXSTEP1_START]-----------距离开始时间戳耗时--------------->"+(System.currentTimeMillis()-start)+"ms");
			String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
			String REDIRECT_URI = request.getRequestURI();
			if (REDIRECT_URI.indexOf(CommonWeixinProperties.domain) == -1) {
				REDIRECT_URI = CommonWeixinProperties.domain + REDIRECT_URI;
			}
			logger.info("------------------REDIRECT_URI--------1---------" + REDIRECT_URI);
			REDIRECT_URI += "?" + request.getQueryString();
			logger.info("------------------REDIRECT_URI--------2---------" + REDIRECT_URI);
			REDIRECT_URI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
			String weixinOauthURL = web_oauth_url.replace("APPID", APPID).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE", SCOPE);
			logger.info("------------------weixinOauthURL-----------------" + weixinOauthURL);
			logger.info("-------[LINKWXSTEP1_END]--------------距离开始时间戳耗时------------>"+(System.currentTimeMillis()-start)+"ms");
			response.sendRedirect(weixinOauthURL);
		} else {
			logger.info("-------[LINKWXSTEP2_START]------------距离开始时间戳耗时-------------->"+(System.currentTimeMillis()-start));
			// Step.2 通过code，调用微信接口，获取openid
			String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
			requestUrl = requestUrl.replace("APPID", APPID);
			requestUrl = requestUrl.replace("SECRET", APPSECRET);
			requestUrl = requestUrl.replace("CODE", code);
			logger.info("------------------code-----------------" + code);

			//update-begin--author:qinfeng------date:20160202--------------------for:更换Http工具类，提高效率---------------
//			String jsonStr = HttpClientUtil.post(requestUrl, null);
//			logger.info("------------------jsonStr-----------------" + jsonStr);
//			JSONObject json2 = JSONObject.fromObject(jsonStr);
			logger.info("-------[LINKWXSTEP2_REQUEST]调用微信oauth2接口获取openid请求-------------------------->"+(System.currentTimeMillis()-start)+"ms");
			com.alibaba.fastjson.JSONObject json2 = HttpUtil.httpRequest(requestUrl,"POST",null);
			logger.info("-------[LINKWXSTEP2_RESPONSE]调用微信oauth2接口获取openid响应-------------------------->"+(System.currentTimeMillis()-start)+"ms");
			//update-end--author:qinfeng------date:20160202--------------------for:更换Http工具类，提高效率---------------
			
			String openid = (String) json2.get("openid");
			logger.info("----------------get--openid-----------------" + openid);
			// Step.3 openid拼接到对外url
			
			//--update-begin----authro:scott-----------date:20160129-----------------for:获取素材链接通过缓存读取，降低数据库访问频率------------------------------
//			WeixinLinksucai weixinLinksucai = weixinLinksucaiService.queryById(id);
//			if(weixinLinksucai == null){
//				logger.error("------[异常]----------OpenWX----------链接素材ID参数异常,查询数据失败，Link ID: ------" + id);
//			}
//			String jeewxUrl = weixinLinksucai.getOuterLink();
			String jeewxUrl = weixinLinksucaiService.queryById(linkid).getOuterLink();
			if(oConvertUtils.isEmpty(jeewxUrl)){
				logger.error("------[异常]----------OpenWX----------链接素材ID参数异常,查询数据失败，Link ID: ------" + linkid);
				return;
			}
			//--update-end----authro:scott-----------date:20160129-----------------for:获取素材链接通过缓存读取，降低数据库访问频率------------------------------
			
			//========================================================================================================
			logger.info("----------------outerLink------1-----------" + jeewxUrl);
			if(oConvertUtils.isNotEmpty(jeewxUrl)){
				jeewxUrl = jeewxUrl.replace("${openid}", openid); // 粉丝openid
				jeewxUrl = jeewxUrl.replace("${wxid}", weixinAccount.getWeixinAccountid()); // 公众号原始ID
				jeewxUrl = jeewxUrl.replace("${wxcode}", weixinAccount.getAccountnumber()); // 公众微信号
				jeewxUrl = jeewxUrl.replace("${appid}", weixinAccount.getAccountappid());
				jeewxUrl = jeewxUrl.replace("${appsecret}", weixinAccount.getAccountappsecret());
				jeewxUrl = jeewxUrl.replace("${accesstoken}", weixinAccount.getAccountaccesstoken());
				jeewxUrl = jeewxUrl.replace("${webAuthToken}", (String) json2.get("access_token"));
				
				//设置粉丝信息
				//TODO 为了效率，暂时取消这个赋值
//				Map<String,String> userMap = openWxService.getUserInfoByOpenid(openid, jwid);
//				if(userMap!=null){
//					jeewxUrl = jeewxUrl.replace("${telphone}", userMap.containsKey(userMap)?userMap.get("phone"):""); //绑定手机号
//					jeewxUrl = jeewxUrl.replace("${subscribe}", userMap.containsKey("subscribe")?userMap.get("subscribe"):""); //粉丝关注状态
//					jeewxUrl = jeewxUrl.replace("${nickname}", userMap.containsKey("nickname")?userMap.get("nickname"):""); //粉丝昵称
//				}
			}
			logger.info("----------------request.getQueryString()-------2----------" + request.getQueryString());
			String queryParam = request.getQueryString();
			if(oConvertUtils.isNotEmpty(queryParam)){
				queryParam = queryParam.replace("link&", "");
				jeewxUrl = jeewxUrl +"&"+ queryParam;
				
			}
			logger.info("----------------outerLink-------3----------" + jeewxUrl);
			//========================================================================================================
			String sign = SignatureUtil.sign(SignatureUtil.getSignMap(jeewxUrl), SIGN_KEY);
			logger.info("-------[LINKWXSTEP2_END]--------------距离开始时间戳耗时------------>"+(System.currentTimeMillis()-start)+"ms");
			response.sendRedirect(jeewxUrl+"&sign="+sign);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirectByAuthType2(HttpServletRequest request,HttpServletResponse response,String SCOPE) throws IOException{
		long start = System.currentTimeMillis();
		logger.info("-------[LINKSTARTIME]--------------开始时间戳------------>"+start);
		// 链接素材ID
		String linkid = request.getParameter("linkid");
		String jwid = request.getParameter("jwid");
		MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByJwid(jwid);
		if (myJwWebJwid == null) {
			logger.error("------[异常]----------OpenWX----------微信原始ID参数异常,查询公众号失败，原始ID: ------" + jwid);
			return;
		}
		// Step.1 获取微信author2.0 code
		// 用户同意授权后，能获取到code
		
		String code = request.getParameter("code");
		String APPID = myJwWebJwid.getWeixinAppId();
		//String SCOPE = "snsapi_userinfo";
		if (code == null || code.length() == 0) {
			logger.info("-------[LINKWXSTEP1_START]-----------距离开始时间戳耗时--------------->"+(System.currentTimeMillis()-start)+"ms");
			String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE&component_appid=COMPONENT_appid#wechat_redirect";
			String REDIRECT_URI = request.getRequestURI();
			if (REDIRECT_URI.indexOf(CommonWeixinProperties.domain) == -1) {
				REDIRECT_URI = CommonWeixinProperties.domain + REDIRECT_URI;
			}
			logger.info("------------------REDIRECT_URI--------1---------" + REDIRECT_URI);
			REDIRECT_URI += "?" + request.getQueryString();
			logger.info("------------------REDIRECT_URI--------2---------" + REDIRECT_URI);
			REDIRECT_URI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
			String weixinOauthURL = web_oauth_url.replace("APPID", APPID).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE", SCOPE).replace("COMPONENT_appid",CommonWeixinProperties.component_appid);
			logger.info("------------------weixinOauthURL-----------------" + weixinOauthURL);
			logger.info("-------[LINKWXSTEP1_END]--------------距离开始时间戳耗时------------>"+(System.currentTimeMillis()-start)+"ms");
			response.sendRedirect(weixinOauthURL);
		}
	}
}

