package com.jeecg.p3.weixin.enums;

/**
 * 微信菜单类型：0‘网页授权’,1‘消息响应’,2‘小程序’
 */
public enum WeixinMenuTypeEnum {
	
	wx_menu_type_click("click","消息触发类"),
	wx_menu_type_view("view","网页链接类"),
	wx_menu_type_app("miniprogram","小程序类");
	
	private WeixinMenuTypeEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	private String code;
	private String value;
	public String getCode() {
		return code;
	}
	public String getValue() {
		return value;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static String toEnum(String code){
		String result=null;
		for (WeixinMenuTypeEnum enums : WeixinMenuTypeEnum.values()) {
			if (enums.getCode().equals(code)) {
				result=enums.getValue();
			}
		}
		return result;
	}
	
	
}
