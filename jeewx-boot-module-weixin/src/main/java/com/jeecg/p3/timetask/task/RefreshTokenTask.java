package com.jeecg.p3.timetask.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.commonweixin.util.AccessTokenUtil;
import com.jeecg.p3.commonweixin.util.HttpUtil;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

import net.sf.json.JSONObject;

/**
 * 重置第三方平台AccessToken
 * 重置公众号AccessToken
 * @author Administrator
 *
 */
@Service
public class RefreshTokenTask {
	public final static Logger LOG = LoggerFactory.getLogger(RefreshTokenTask.class);
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;

	//获取（刷新）授权公众号的接口调用凭据（令牌）
	private static String GET_AUTHORIZER_ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
	//重置第三方平台AccessToken接口
	private static String GET_COMPONENT_ACCESS_ACTOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_component_token";
	//第三方平台APPID
	private static String COMPONENT_APPID ="";
	static{
		PropertiesUtil p=new PropertiesUtil("commonweixin.properties");
		COMPONENT_APPID = p.readProperty("component_appid");
	}
	
	/**
	 * 定时刷新TOKEN
	 */
	@Scheduled(cron="0 0/10 * * * ?")
	public void run() {
		LOG.info("===================重置公众号AccseeToken定时任务开启==========================");
		long start = System.currentTimeMillis();
		try {
			//2.重置公众号的Token
			Date date = new Date();
			long time= date.getTime()-1000*60*90;
			Date refDate = new Date(time);
			List<MyJwWebJwid> myJwWebJwids = myJwWebJwidService.queryResetTokenList(refDate);
			for(MyJwWebJwid myJwWebJwid:myJwWebJwids){
				try {
						resetAccessTokenByType1(myJwWebJwid);
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
}
