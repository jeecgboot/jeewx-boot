package com.jeecg.p3.baseApi.vo;

/**
 * 发送模板消息通用实体
 * @author LeeShaoQing
 * 2018-11-23
 */
public class TmessageSendVO {
	
	/**
	 * 接收者openid
	 */
	private String touser;
	
	/**
	 * 模板ID
	 */
	private String templateId;
	
	/**
	 * 模板跳转链接（海外帐号没有跳转能力）
	 */
	private String url;
	
	/**
	 * 跳小程序所需数据，不需跳小程序可不用传该数据
	 */
	private String miniProgram;
	
	/**
	 * 所需跳转到的小程序appid
	 */
	private String appId;
	
	/**
	 * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
	 */
	private String pagePath;
	
	/**
	 * 模板数据
	 */
	private String data;
	
	/**
	 * JWID
	 */
	private String jwid;
	
	

	public TmessageSendVO() {
		super();
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMiniProgram() {
		return miniProgram;
	}

	public void setMiniProgram(String miniProgram) {
		this.miniProgram = miniProgram;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getJwid() {
		return jwid;
	}

	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	
	
}
