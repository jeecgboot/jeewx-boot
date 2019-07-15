package com.jeecg.p3.commonweixin.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

/**
 * 重置accseeToken,api,jsApi
 * @author 18534
 *
 */
public class AccessTokenUtil {
	private final static Logger LOG = LoggerFactory.getLogger(AccessTokenUtil.class);
	private final static String requestUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String api_ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
	private static final String jsapi_ticket_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	public static Map<String,Object> getAccseeToken(String appid,String appsecret){
		Map<String,Object> data = new HashMap<String,Object>();
		try {
			String url = requestUrl.replace("APPID", appid).replace("APPSECRET", appsecret);
			LOG.info("AccseeToken request url={}.", new Object[]{url});
			JSONObject jsonObj = WeiXinHttpUtil.sendGet(url);
			LOG.info("AccseeToken response jsonStr={}.", new Object[]{jsonObj});
			if(null != jsonObj){
				if(jsonObj.containsKey("access_token")){
					String accessToken=jsonObj.getString("access_token");
					data.put("accessToken", accessToken);
					data.put("accessTokenTime", new Date());
					//获取api(卡券用)
					String apiUrl = api_ticket_url.replace("ACCESS_TOKEN", accessToken);
					JSONObject jsonObjApi = WeiXinHttpUtil.sendGet(apiUrl);
					LOG.info("AccseeToken response jsonObjApi={}.", new Object[]{jsonObjApi});
					if(jsonObjApi != null){
						String apiTicket = jsonObjApi.getString("ticket");
						data.put("apiTicket", apiTicket);
						data.put("apiTicketTime", new Date());
					}
					//获取jsapi(JS-SDK用)
					String jsApiUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
					JSONObject jsonObjJsApi = WeiXinHttpUtil.sendGet(jsApiUrl);
					LOG.info("AccseeToken response jsonObjJsApi={}.", new Object[]{jsonObjJsApi});
					if(jsonObjJsApi != null){
						String jsApiTicket = jsonObjJsApi.getString("ticket");
						data.put("jsApiTicket", jsApiTicket);
						data.put("jsApiTicketTime", new Date());
					}
					data.put("status", "success");
				}else{
					if(jsonObj.containsKey("errcode")){
						LOG.error("AccseeToken request error={}.", new Object[]{jsonObj.getString("errmsg")});
						data.put("status", "responseErr");
						//author:sunkai  date:2018-09-26  for:全局返回码说明
						data.put("errcode", jsonObj.getString("errcode"));
						String msg = "";
						if(jsonObj.getString("errcode").equals("40164")){
							msg = "当前平台的IP未添加到微信公众号IP白名单中，请前往微信公众平台配置";
						}else{
							msg = "AppID或AppSecret不正确，请认真检查您的 AppID和AppSecret";
						}
						data.put("msg",msg);
						//author:sunkai  date:2018-09-26  for:全局返回码说明
					}
				}
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			data.put("status", "sysError");
			LOG.error("AccseeToken error={}.", new Object[]{e});
			return data;
		}
		
	} 
	
	/**
	 * 获取jsapiTicket和apiTicket
	 * @param accessToken
	 * @return
	 */
	public static Map<String,String> getApiTicket(String accessToken) {
		Map<String,String> data = new HashMap<String,String>();
		//获取api(卡券用)
		try {
			if(accessToken == null) {
				return null;
			}
			String apiUrl = api_ticket_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObjApi = WeiXinHttpUtil.sendGet(apiUrl);
			LOG.info("AccseeToken response jsonObjApi={}.", new Object[]{jsonObjApi});
			if(jsonObjApi != null){
				String apiTicket = jsonObjApi.getString("ticket");
				data.put("apiTicket", apiTicket);
			}
			//获取jsapi(JS-SDK用)
			String jsApiUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObjJsApi = WeiXinHttpUtil.sendGet(jsApiUrl);
			LOG.info("AccseeToken response jsonObjJsApi={}.", new Object[]{jsonObjJsApi});
			if(jsonObjJsApi != null){
				String jsApiTicket = jsonObjJsApi.getString("ticket");
				data.put("jsApiTicket", jsApiTicket);
			}
			data.put("status", "true");
		} catch (Exception e) {
			e.printStackTrace();
			data.put("status", "false");
		}
		return data;
	}
}
