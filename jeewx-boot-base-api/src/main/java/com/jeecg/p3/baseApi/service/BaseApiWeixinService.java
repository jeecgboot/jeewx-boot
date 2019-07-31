package com.jeecg.p3.baseApi.service;

import net.sf.json.JSONObject;

import com.jeecg.p3.baseApi.vo.TmessageSendVO;


/**
 * 微信共通方法
 * @author huangqingquan
 *
 */
public interface BaseApiWeixinService {

	/**
	 * 调用发送模板消息接口
	 * @param jwid
	 * @return
	 */
	public JSONObject SendTemplateMsg(TmessageSendVO tmessageSendVO);
}

