package com.jeecg.p3.weixin.enums;

/**
 * 微信菜单类型：0‘网页授权’,1‘消息响应’,2‘小程序’
 */
public enum WeixinMsgTypeEnum {
	
	wx_msg_type_text("text","文本"),
	wx_msg_type_news("news","图文");
	
	private WeixinMsgTypeEnum(String code, String value) {
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
		for (WeixinMsgTypeEnum enums : WeixinMsgTypeEnum.values()) {
			if (enums.getCode().equals(code)) {
				result=enums.getValue();
			}
		}
		return result;
	}
	
	
}
