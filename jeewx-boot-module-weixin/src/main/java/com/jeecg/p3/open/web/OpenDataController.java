package com.jeecg.p3.open.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.commonweixin.util.DateUtils;
import com.jeecg.p3.open.service.OpenWxService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

/**
 * OPEN: 微信公众号信息对外接口
 * @author scott
 * @since：2016年11月14日
 * 
 */
@Controller
@RequestMapping("/openDataController")
public class OpenDataController extends BaseController {
	@Autowired
	private OpenWxService openWxService;
	private static final Logger logger = LoggerFactory.getLogger(OpenDataController.class);

	/**
	 * 获取微信公众账号信息
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "getWeixinToken")
	public void getWeixinToken(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			long startTime = System.currentTimeMillis();// 获取当前时间
			Map<String, String> map = new HashMap<String, String>();
			// ----update-start--------autor:zhoujf----------date:20150701---
			// for:IP访问限制
			String weixinId = request.getParameter("weixinId");
			if (oConvertUtils.isEmpty(weixinId)) {
				map.put("success", "false");
				map.put("error", "微信ID为空");
			} else {
				WeixinAccount po = openWxService.getWeixinAccountByWeixinOldId(weixinId);
				if (po != null) {
					// [1] 获取Token
					map.put("success", "true");
					map.put("accountname", po.getAccountname());
					map.put("accountaccesstoken", po.getAccountaccesstoken());
					if (po.getAddtoekntime() != null) {
						java.util.Date toekntime = new java.util.Date(po.getAddtoekntime().getTime());
						map.put("tokentime", DateUtils.date2Str(toekntime, DateUtils.datetimeFormat));
					}

					// [2] jssdk开发 - jsapi凭证
					map.put("jsapiticket", po.getJsapiticket());
					if (po.getJsapitickettime() != null) {
						java.util.Date jsapiticket_time = new java.util.Date(po.getJsapitickettime().getTime());
						map.put("jsapitickettime", DateUtils.date2Str(jsapiticket_time, DateUtils.datetimeFormat));
					}

					// [3] 微信卡券JS API的临时票据
					map.put("apiticket", po.getApiticket());
					if (po.getApiticketttime() != null) {
						java.util.Date apiticket_time = new java.util.Date(po.getApiticketttime().getTime());
						map.put("apiticketttime", DateUtils.date2Str(apiticket_time, DateUtils.datetimeFormat));
					}
				} else {
					map.put("success", "false");
					map.put("error", "微信ID无效");
				}
			}
			// ----update-end--------autor:zhoujf----------date:20150701---
			// for:IP访问限制
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter pw = response.getWriter();
				long endTime = System.currentTimeMillis();
				System.out.println("接口程序 getWeixinToken 运行时间：" + (endTime - startTime) + "ms");
				pw.write(JSONObject.toJSONString(map));
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-----获取Token接口异常----：" + e.getMessage());
		}
	}

	/**
	 * 捷微对外接口：getSignature
	 * 
	 * @return
	 */
	@RequestMapping(params = "getSignature")
	public void getSignature(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		try {
			long startTime = System.currentTimeMillis();// 获取当前时间
			String weixinId = request.getParameter("weixinId");
			String nonceStr = request.getParameter("nonceStr");
			String timestamp = request.getParameter("timestamp");
			String url = request.getParameter("url");// .replace("@", "&");

			// 解码
			url = URLDecoder.decode(url, "UTF-8");
			String signature = "";
			Map<String, String> returnmap = new HashMap<String, String>();

			WeixinAccount weixinAccountEntity = openWxService.getWeixinAccountByWeixinOldId(weixinId);
			if (weixinAccountEntity == null) {
				returnmap.put("success", "false");
				returnmap.put("error", "微信原始ID无效");
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter pw;
				try {
					pw = response.getWriter();
					pw.write(JSONObject.toJSONString(returnmap));
					pw.flush();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String jsapi_ticket = weixinAccountEntity.getJsapiticket();
			String need_make_string;
			// 注意这里参数名必须全部小写，且必须有序
			need_make_string = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
			returnmap.put("success", "true");
			logger.info("-----------捷微对外接口：getSignature-----------------need_make_string--------------：" + need_make_string);
			try {
				signature = DigestUtils.shaHex(need_make_string);
			} catch (Exception e) {
				e.printStackTrace();
				returnmap.put("success", "false");
				logger.error("---------------捷微对外接口：getSignature---------" + e.toString());
			}

			returnmap.put("url", url);
			returnmap.put("signature", signature);

			try {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter pw = response.getWriter();
				long endTime = System.currentTimeMillis();
				System.out.println("接口程序 getSignature 运行时间：" + (endTime - startTime) + "ms");
				pw.write(JSONObject.toJSONString(returnmap));
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("-----获取签名接口异常----：" + e.getMessage());
		}
	}
}
