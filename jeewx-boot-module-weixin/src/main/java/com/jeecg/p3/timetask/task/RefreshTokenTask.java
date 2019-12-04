package com.jeecg.p3.timetask.task;

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.commonweixin.util.AccessTokenUtil;
import com.jeecg.p3.commonweixin.util.HttpUtil;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重置第三方平台 AccessToken
 * 重置公众号 AccessToken
 * @author Administrator
 */
@Service
public class RefreshTokenTask {
	public final static Logger LOG = LoggerFactory.getLogger(RefreshTokenTask.class);
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
	@Autowired
	private WeixinOpenAccountService weixinOpenAccountService;
	
	//获取（刷新）授权公众号的接口调用凭据（令牌）
	private static String GET_AUTHORIZER_ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
	//重置第三方平台AccessToken接口
	private static String GET_COMPONENT_ACCESS_ACTOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	//第三方平台APPID
	private static String COMPONENT_APPID =CommonWeixinProperties.component_appid;

	/**
	 * 定时刷新TOKEN
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void run() {
		LOG.info("===================重置公众号AccseeToken定时任务开启==========================");
		long start = System.currentTimeMillis();
		try {
			
			//1.重置第三方平台AccessTOKEN
			resetComponentAccessToken();
			
			//2.重置公众号的Token
			Date date = new Date();
			long time= date.getTime()-1000*60*90;
			Date refDate = new Date(time);
			List<MyJwWebJwid> myJwWebJwids = myJwWebJwidService.queryResetTokenList(refDate);
			for(MyJwWebJwid myJwWebJwid:myJwWebJwids){
				try {
					if("2".equals(myJwWebJwid.getAuthType())){
						//第三方平台
						resetAccessTokenByType2(myJwWebJwid);
					}else{
						resetAccessTokenByType1(myJwWebJwid);
					}
				} catch (Exception e) {
					LOG.info("重置AccseeToken定时任务异常e={}",new Object[]{e});
				}
			}
		} catch (Exception e) {
			LOG.info("重置AccseeToken定时任务异常e={}",new Object[]{e});
		}
		LOG.info("===================重置AccseeToken定时任务结束，用时={}ms.==========================",new Object[]{System.currentTimeMillis()-start});
	}
	
	
	/**
	 * 重置第三方平台AccessTOKEN
	 * @param myJwWebJwid
	 * @return
	 */
	private void resetComponentAccessToken(){
		try {
			Date date = new Date();
			long time= date.getTime()-1000*60*90;
			Date refDate = new Date(time);
			//根据APPID从数据库中获取基本信息（包括秘钥）
			if(oConvertUtils.isEmpty(COMPONENT_APPID)){
				return;
			}
			WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(COMPONENT_APPID);
			//第三方平台账号上次获取token时间超过1个半小时，则进行重置
			if(weixinOpenAccount!=null && weixinOpenAccount.getGetAccessTokenTime().before(refDate)){
				Map<String, Object> param=new HashMap<String, Object>();
				param.put("component_appid", COMPONENT_APPID);
				param.put("component_appsecret", weixinOpenAccount.getAppsecret());//从数据库中获取秘钥
				param.put("component_verify_ticket", weixinOpenAccount.getTicket());
				com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject(param);
				com.alibaba.fastjson.JSONObject jsonObj = HttpUtil.httpRequest(GET_COMPONENT_ACCESS_ACTOKEN_URL, "POST", jsonObject.toString());
				LOG.info("重置第三方平台ACCESSTOKEN时返回的报文"+jsonObj);
				if(jsonObj!=null&&jsonObj.containsKey("component_access_token")){
					weixinOpenAccount.setComponentAccessToken(jsonObj.getString("component_access_token"));
					weixinOpenAccount.setGetAccessTokenTime(new Date());
					weixinOpenAccountService.doEdit(weixinOpenAccount);
					
					//---------------------第三方平台账号重置token后写入redis缓存-----------------------------
					try {
						WeixinAccount po = new WeixinAccount();
						po.setAccountname("第三方平台账号-H5");
						po.setAccountappid(weixinOpenAccount.getAppid());
						po.setAccountappsecret(weixinOpenAccount.getAppsecret());
						po.setAccountaccesstoken(weixinOpenAccount.getComponentAccessToken());
						po.setAddtoekntime(weixinOpenAccount.getGetAccessTokenTime());
						po.setWeixinAccountid(weixinOpenAccount.getAppid());//APPID
						JedisPoolUtil.putWxAccount(po);
					} catch (Exception e) {
						LOG.error("----------第三方平台账号重置TOKEN错误-------------"+e.toString());
						e.printStackTrace();
					}
					//---------------------第三方平台账号重置token后写入redis缓存-----------------------------
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据APPID和秘钥 获取ACCESSTOKEN
	 * @param myJwWebJwid
	 * @return
	 */
	private String resetAccessTokenByType1(MyJwWebJwid myJwWebJwid){
		//根据APPID和秘钥 获取ACCESSTOKEN，并获取apiticket和jsapiticket
		Map<String, Object> data = AccessTokenUtil.getAccseeToken(myJwWebJwid.getWeixinAppId(), myJwWebJwid.getWeixinAppSecret());
		if(data!=null && "success".equals(data.get("status").toString())){
			myJwWebJwid.setAccessToken(data.get("accessToken").toString());
			myJwWebJwid.setTokenGetTime(new Date());
			myJwWebJwid.setApiTicket(data.get("apiTicket").toString());
			myJwWebJwid.setApiTicketTime(new Date());
			myJwWebJwid.setJsApiTicket(data.get("jsApiTicket").toString());
			myJwWebJwid.setJsApiTicketTime(new Date());
			myJwWebJwidService.doEdit(myJwWebJwid);
			
			//-------H5平台独立公众号，重置redis缓存-------------------------------------------
			try {
				WeixinAccount po = new WeixinAccount();
				po.setAccountname(oConvertUtils.getString(myJwWebJwid.getName())+"-H5");
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
				LOG.error("----------定时任务：H5平台独立公众号，重置redis缓存token失败-------------"+e.toString());
				e.printStackTrace();
				return " 没有配置Redis 缓存！";
			}
			//--------H5平台独立公众号，重置redis缓存---------------------------------------
			return "success";
		}else if("responseErr".equals(data.get("status").toString())){
			return data.get("msg").toString();
		}else{
			return null;
		}
	}
	/**
	 * 扫码授权,获取ACCESSTOKEN
	 * @param myJwWebJwid
	 * @return
	 */
	private String resetAccessTokenByType2(MyJwWebJwid myJwWebJwid){
		try {
			WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(COMPONENT_APPID);
			if(weixinOpenAccount==null){
				throw new CommonweixinException("重置accessToken时获取WEIXINOPENACCOUNT为空");
			}
			//重置授权公众号或小程序的接口调用凭据
			String getAuthorizerTokenUrl = GET_AUTHORIZER_ACCESS_TOKEN_URL;
			getAuthorizerTokenUrl = getAuthorizerTokenUrl.replace("COMPONENT_ACCESS_TOKEN", weixinOpenAccount.getComponentAccessToken());
			// 拼装参数
			JSONObject js = new JSONObject();
			// 第三方平台appid
			js.put("component_appid", COMPONENT_APPID);
			// 授权用户的appid
			js.put("authorizer_appid", myJwWebJwid.getWeixinAppId());
			// 刷新令牌
			js.put("authorizer_refresh_token", myJwWebJwid.getAuthorizerRefreshToken());
			JSONObject jsonObj = WxstoreUtils.httpRequest(getAuthorizerTokenUrl, "POST", js.toString());
			if (jsonObj != null && !jsonObj.containsKey("errcode")) {
				String authorizerAccessToken = jsonObj.getString("authorizer_access_token");
				String authorizerRefreshToken = jsonObj.getString("authorizer_refresh_token");
				myJwWebJwid.setAccessToken(authorizerAccessToken);
				myJwWebJwid.setTokenGetTime(new Date());
				myJwWebJwid.setAuthorizerRefreshToken(authorizerRefreshToken);
				//update jsapiticket
				Map<String, String> apiTicket = AccessTokenUtil.getApiTicket(myJwWebJwid.getAccessToken());
				if("true".equals(apiTicket.get("status"))){
					myJwWebJwid.setApiTicket(apiTicket.get("apiTicket"));
					myJwWebJwid.setApiTicketTime(new Date());
					myJwWebJwid.setJsApiTicket(apiTicket.get("jsApiTicket"));
					myJwWebJwid.setJsApiTicketTime(new Date());
				}
				myJwWebJwidService.doEdit(myJwWebJwid);
				
				//-------H5平台独立公众号，重置redis缓存-------------------------------------------
				try {
					WeixinAccount po = new WeixinAccount();
					po.setAccountname(oConvertUtils.getString(myJwWebJwid.getName())+"-H5");
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
					LOG.error("----------定时任务：H5平台独立公众号，重置redis缓存token失败-------------"+e.toString());
				}
				//--------H5平台独立公众号，重置redis缓存---------------------------------------
			} else {
				throw new CommonweixinException("重置Token失败！");
			}
			return "success";
		}catch (CommonweixinException e) {
			e.printStackTrace();
			return e.getMessage();
		}catch (Exception e) {
			e.printStackTrace();
			return "重置accessToken时发生异常:"+e.getMessage();
		}
	}
}
