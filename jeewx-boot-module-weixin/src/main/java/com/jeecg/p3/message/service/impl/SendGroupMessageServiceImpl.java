package com.jeecg.p3.message.service.impl;

import net.sf.json.JSONObject;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.springframework.stereotype.Service;
import com.jeecg.p3.message.service.SendGroupMessageService;
import com.jeecg.p3.weixin.util.WeixinUtil;

/**
 * @author：weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
@Service("SendGroupMessageService")
public class SendGroupMessageServiceImpl implements SendGroupMessageService {

	//根据标签进行群发
	private static String group_message_send_url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	
	/**
	 * @功能：根据标签进行群发
	 */
	@Override
	public JSONObject sendGroupMessage(JSONObject messageJson, String jwid) {
		//获取accessToken
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		if(accessToken!=null){
			String requestUrl = group_message_send_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", messageJson.toString());
			return result;
		}
		return null;
	}

}
