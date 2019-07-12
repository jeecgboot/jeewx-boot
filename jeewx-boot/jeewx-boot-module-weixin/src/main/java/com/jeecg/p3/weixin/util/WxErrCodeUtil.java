package com.jeecg.p3.weixin.util;

public class WxErrCodeUtil {
	
	
	public static String ERROR_42001 = "access_token 超时失效，请重置access_token";

	public static String ERROR_41001 = "缺少 access_token 参数";
	
	public static String ERROR_40137 = "不支持的图片格式";
	
	public static String ERROR_45001 = "多媒体文件大小超过限制";
	
	public static String ERROR_40164 = "调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置";
	
	public static String ERROR_40018 = "一级菜单的名称长度超过限制";
	
	public static String ERROR_40025 = "二级菜单的名称长度超过限制";
	
	public static String ERROR_45028 = "每个订阅号每天只能群发一次";
	
	public static String ERROR_40054 = "配置的菜单url地址不正确！";
	
	public static String ERROR_40055 = "配置的菜单url地址不正确！";
	
	/**
	 * 根据code获取相应的错误信息
	 */
	public static String testErrCode(String errcode){
		String msg = "";
		if(errcode.equals("42001")){
			msg = ERROR_42001;
		}else if(errcode.equals("41001")){
			msg = ERROR_41001;
		}else if(errcode.equals("40137")){
			msg = ERROR_40137;
		}else if(errcode.equals("45001")){
			msg = ERROR_45001;
		}else if(errcode.equals("40018")){
			msg = ERROR_40018;
		}else if(errcode.equals("40025")){
			msg = ERROR_40025;
		}else if(errcode.equals("45028")){
			msg = ERROR_45028;
		}else if(errcode.equals("40054")){
			msg = ERROR_40054;
		}else if(errcode.equals("40055")){
			msg = ERROR_40055;
		}else{
			msg = "错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>";
		}
		return msg;
	}
}
