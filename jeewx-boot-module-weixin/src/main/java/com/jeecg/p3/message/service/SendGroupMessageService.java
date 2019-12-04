package com.jeecg.p3.message.service;

import net.sf.json.JSONObject;

/**
 * @author：weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
public interface SendGroupMessageService {

	/**
	 * @功能：群发消息
	 * @param messageJson
	 * @param jwid
	 * @return
	 */
	JSONObject sendGroupMessage(JSONObject messageJson, String jwid);
	
}

