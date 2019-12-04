package com.jeecg.p3.tmessage.util;

import java.util.HashMap;
import java.util.Map;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecg.p3.tmessage.vo.TmessageSendVO;

import net.sf.json.JSONObject;

/**
 * 发送模板消息工具类
 * @author LeeShaoQing
 * 2018-11-23
 */
public class SendTemplateMsgUtil {
	
	public final static Logger log = LoggerFactory.getLogger(SendTemplateMsgUtil.class);
	
	private static String send_template_msg = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/**
	 * 调用发送模板消息接口
	 * @param request
	 * @param tmessageSendVO
	 * @return
	 */
	public static JSONObject SendTemplateMsg(TmessageSendVO tmessageSendVO) {
		JSONObject result = null;
		if(oConvertUtils.isEmpty(tmessageSendVO.getTouser())) {
			log.info("发送模板消息---接收者openid为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getTemplateId())) {
			log.info("发送模板消息---模板ID为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getJwid())) {
			log.info("发送模板消息---JWID为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getData())) {
			log.info("发送模板消息---模板数据为空");
			return result;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		//接收者openid
		param.put("touser", tmessageSendVO.getTouser());
		//模板ID
		param.put("template_id", tmessageSendVO.getTemplateId());
		//模板数据
		JSONObject dataJson = JSONObject.fromObject(tmessageSendVO.getData());
		Map dataMap = dataJson;
		param.put("data", dataMap);
		//模板跳转链接
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getUrl())) {
			param.put("url", tmessageSendVO.getUrl());
		}
		//跳小程序所需数据
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getMiniProgram())) {
			param.put("miniprogram", tmessageSendVO.getMiniProgram());
		}
		//所需跳转到的小程序appid
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getAppId())) {
			param.put("appid", tmessageSendVO.getAppId());
		}
		//所需跳转到小程序的具体页面路径
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getPagePath())) {
			param.put("pagepath", tmessageSendVO.getPagePath());
		}
		String accessToken = WeiXinHttpUtil.getRedisWeixinToken(tmessageSendVO.getJwid());
		if (accessToken != null) {
			String requestUrl = send_template_msg.replace("ACCESS_TOKEN", accessToken);
			JSONObject obj = JSONObject.fromObject(param);
			log.info("发送模板消息方法执行前json参数 :{obj :" + obj.toString() + "}");
			result = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			log.info("发送模板消息方法执行后json参数 :{result :" + result.toString() + "}");
		}
		return result;
	}
}
