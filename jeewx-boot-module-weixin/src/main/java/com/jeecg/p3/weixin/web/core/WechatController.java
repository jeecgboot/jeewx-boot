package com.jeecg.p3.weixin.web.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.service.WechatService;
import com.jeecg.p3.weixin.util.MessageUtil;
import com.jeecg.p3.weixin.util.SignUtil;
import com.jeecg.p3.weixin.vo.resp.TextMessageResp;

/**
 * 微信客户端，请求处理核心类
 * @author zhangdaihao
 *
 */
@Controller
@RequestMapping("/wechatController")
public class WechatController {
	public final static Logger log = LoggerFactory.getLogger(WechatController.class);
	
	@Autowired
	private WechatService wechatService;
	@Autowired
	private MyJwWebJwidService webJwidService;

	@RequestMapping(params="wechat", method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) throws IOException {
		
		log.info("-------------------微信公众号响应消息------------wechat------");
		List<MyJwWebJwid> myJwWebJwids = webJwidService.queryAll();
		
		if(myJwWebJwids != null && myJwWebJwids.size() > 0) {
			log.info("--------------myJwWebJwids------------size------" + myJwWebJwids.size());
			for (MyJwWebJwid myJwWebJwid : myJwWebJwids) {
				if(SignUtil.checkSignature(myJwWebJwid.getToken(), signature, timestamp, nonce)) {
					try {
						response.getWriter().print(echostr);
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@RequestMapping(params = "wechat", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		log.info("------------------微信公众号响应消息-------------wechatPost-----");
		String respMessage = null;
		try {
			respMessage = wechatService.coreService(request);
		} catch (Exception e) {
			e.printStackTrace();
			if(oConvertUtils.isEmpty(respMessage)){
				respMessage = getWeixinMsg(request);
			}
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	
	
	/**
	 * 异常情况下，回复此消息
	 * @param request
	 * @return
	 */
	private String getWeixinMsg(HttpServletRequest request){
		// 异常情况下，回复此文本消息
		TextMessageResp textMessage = new TextMessageResp();
		try {
			// 默认返回的文本消息内容
			String respContent = "系统网络繁忙，请稍后再试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			//消息id
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			//多媒体ID
			String mediaId = requestMap.get("MediaId");
			
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(respContent);
		} catch (Exception e) {
		}
		// 将文本消息对象转换成xml字符串
		return MessageUtil.textMessageToXml(textMessage);
	}

}
