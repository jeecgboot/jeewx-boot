package com.jeecg.p3.baseApi.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信接口调用
 * @author huangqingquan
 *
 */
public class WebAuthWeixinApi{
	private static Logger logger=LoggerFactory.getLogger(WebAuthWeixinApi.class);
	
	private final static String getUserInfoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	private final static String refreshTokenUrl="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN"; 
	
	public final static String webAuthTokenConstant="webAuthToken";
	/**
	 * 获取用户信息
	 * @param openid
	 * @param accessToken
	 * @return
	 */
	public static JSONObject getWebAuthUserInfo(String openid,String accessToken){
		try {
			if(StringUtils.isEmpty(openid)){
				logger.info("openid为空");
				return null;
			}
			if(StringUtils.isEmpty(accessToken)){
				logger.info("accessToken为空");
				return null;
			}
			return httpRequest(getUserInfoUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid), "GET", null);
		} catch (Exception e) {
			logger.error("获取用户信息异常",e);
		}
		return null;
	}
		
	/**
	 * https请求用户数据
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	private static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		HttpURLConnection httpUrlConn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			// HttpURLConnection设置网络超时
			httpUrlConn.setConnectTimeout(4500);
			httpUrlConn.setReadTimeout(4500);

			// httpUrlConn.setRequestProperty("content-type", "text/html");
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
		} finally {
			try {
				httpUrlConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
}
