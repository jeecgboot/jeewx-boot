package com.jeecg.p3.system.def;

import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemProperties {
	public static String domain;
	public final static String defaultJwid;
	/**签名密钥*/
	public final static String SIGN_KEY;

	/**
	 * 邮箱配置
	 */
	@Value("${jeewx.mail.smtpHost}")
	private String smtpHost;
	@Value("${jeewx.mail.sender}")
	private String sender;
	@Value("${jeewx.mail.user}")
	private String user;
	@Value("${jeewx.mail.pwd}")
	private String pwd;

	/**
	 * 短信接口
	 */
	@Value("${jeewx.sms.accessKeyId}")
	private String accessKeyId;
	@Value("${jeewx.sms.accessKeySecret}")
	private String accessKeySecret;

	/**
	 * 第三方平台
	 */
	public final static String component_appid;
	public final static String COMPONENT_TOKEN;
	public final static String COMPONENT_ENCODINGAESKEY;

	static {
		PropertiesUtil globalp = new PropertiesUtil("jeewx.properties");
		domain = globalp.readProperty("oAuthDomain");
		defaultJwid = globalp.readProperty("defaultJwid");
		SIGN_KEY = globalp.readProperty("oAuthSignKey");

		component_appid = globalp.readProperty("COMPONENT_APPID");
		COMPONENT_TOKEN = globalp.readProperty("COMPONENT_TOKEN");
		COMPONENT_ENCODINGAESKEY = globalp.readProperty("COMPONENT_ENCODINGAESKEY");
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getSender() {
		return sender;
	}

	public String getUser() {
		return user;
	}

	public static void setDomain(String domain) {
		SystemProperties.domain = domain;
	}

	public String getPwd() {
		return pwd;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}
}
