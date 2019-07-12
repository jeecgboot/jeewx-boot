package com.jeecg.p3.system.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jeecgframework.p3.core.util.MD5Util;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.system.def.SystemProperties;

public class WeiXinQrcodeUtil {
	private final static Logger logger = LoggerFactory.getLogger(WeiXinQrcodeUtil.class);
	
	/**
	 * 调取微信接口获取临时的二维码地址
	 * @param jwid
	 * @param sceneId
	 * @param expireSeconds
	 * @return
	 */
	public static String getTemporaryQrcode(String jwid, String sceneId, Integer expireSeconds) {
		//TODO 获取accessTolken
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		
		try {
			logger.info("生成带参数二维码请求参数：jwid={},sceneId={},expireSeconds={}.",new Object[]{jwid,sceneId,expireSeconds});
			//请求参数
			StringBuffer requestStr = new StringBuffer();
			requestStr.append("{\"action_info\":{\"scene\":{\"scene_id\":")
					  .append(sceneId)
					  .append("}},\"action_name\":\"QR_SCENE\",\"expire_seconds\":")
					  .append(expireSeconds).append("}");
			logger.info("生成带参数二维码接口请求参数:{}.",new Object[]{requestStr.toString()});
			//调取微信接口
			String jsonStr = HttpUtils.doPostJson("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ accessToken, requestStr.toString());
			logger.info("生成带参数二维码接口返回参数:{}.",new Object[]{jsonStr});
			//解析返回JSON
			if (jsonStr != null) {
				JSONObject jsonObject = JSONObject.parseObject(jsonStr);
				if (jsonObject.containsKey("ticket")) {
					String ticket = jsonObject.getString("ticket");
					String encode = URLEncoder.encode(ticket);
					return "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ encode;
				}
//				if(jsonObject.containsKey("url")){
//					return  jsonObject.getString("url").replace("\\/", "/");
//				}
			}
		} catch (Exception e) {
			logger.info("生成带参数二维码接口错误:{}.",new Object[]{e});
			return null;
		}
		return null;
	}
	
//	/**
//	 * 获取平台的扫码记录
//	 * @param jwid 微信id
//	 * @param sceneId 场景ID
//	 * @param channel 渠道
//	 * @return 登陆者openid
//	 */
//	public static String getScanCodeRecord(String jwid,String sceneId,String channel,String fromDate,String endDate){
//		//获取最先扫码人时间和当前时间作比较
//		Long st=new Date().getTime()+1;
//		//获取最开始扫码人的openid
//		String opneid=null;
//		try {
//			//模拟form表单提交
//			List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
//			nameValuePairs.add(new BasicNameValuePair("weixinId", jwid));
//			nameValuePairs.add(new BasicNameValuePair("sceneId", sceneId));
//			nameValuePairs.add(new BasicNameValuePair("channel", channel));
//			if (StringUtils.isNotEmpty(fromDate)) {
//				nameValuePairs.add(new BasicNameValuePair("fromDate", fromDate));
//			}
//			if(StringUtils.isNotEmpty(endDate)){
//				nameValuePairs.add(new BasicNameValuePair("endDate", endDate));
//			}
//			//生成签名字符串
//			
//			String str="channel="+channel;
//			if(StringUtils.isNotEmpty(endDate)){
//				str=str+"&endDate="+endDate;
//			}
//			if(StringUtils.isNotEmpty(fromDate)){
//				str=str+"&fromDate="+fromDate;
//			}
//			str=str+"&sceneId="+sceneId+"&weixinId="+jwid+"&key="+SystemProperties.scanRecordKey;
//			//生成MD5加密密钥
//			String md5Encode = MD5Util.MD5Encode(str, "utf-8").toUpperCase();
//			//签名加入请求参数里
//			nameValuePairs.add(new BasicNameValuePair("signature", md5Encode));
//			//调取接口
//			logger.debug("获取扫码记录接口请求参数：{}",new Object[]{str});
//			String jsonStr = HttpUtils.postForm(SystemProperties.getScanRecordUrl, nameValuePairs);
//			logger.debug("获取扫码记录接口返回JSONSTR={}",new Object[]{jsonStr});
//			//解析JSON串
//			if(jsonStr!=null){
//				JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//				if(jsonObject.containsKey("scanRecordList")){
//					JSONArray jsonArray=JSONArray.parseArray(jsonObject.getString("scanRecordList"));
//					//遍历JSON数组
//					for (Object obj:jsonArray) {
//						JSONObject jsonObj=(JSONObject)obj;
//						if(jsonObj.containsKey("scantime")){
//							Long scantime = jsonObj.getLong("scantime");
//							if(jsonObj.containsKey("openid")){
//								if(scantime<=st){
//									st=scantime;
//									opneid=jsonObj.getString("openid");
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			logger.info("获取扫码记录接口异常{}",new Object[]{e});
//		}
//		return opneid;
//	}
	
	
	/**
	 * 随机生成场景Id
	 * @return 随机k位，整形的Id
	 */
	public static String getSceneId(){
		 int temp=0;
		 Random rd=new Random();
		 StringBuffer sb=new StringBuffer();
		 temp=rd.nextInt(4294)+1;
		 sb.append(temp);
		 if(temp==4294){
			 temp=rd.nextInt(967295);
			 for(int i=0;i<6-(temp+"").length();i++){
				 sb.append("0");
			 }
			 sb.append(temp);
		 }else{
			 temp=rd.nextInt(1000000);
			 for(int i=0;i<6-(temp+"").length();i++){
				 sb.append("0");
			 }
			 sb.append(temp);
		 }
		 return sb.toString();
	 }
	/**
	 * @param k
	 * @return
	 */
	public  static String getUserId(int k){
		 Random rd=new Random();
		 StringBuffer sb=new StringBuffer();
		 sb.append(rd.nextInt(9)+1);
		 for(int i=0;i<k-1;i++){
			 sb.append(rd.nextInt(10));
		 }
		 return sb.toString();
	 }
}
