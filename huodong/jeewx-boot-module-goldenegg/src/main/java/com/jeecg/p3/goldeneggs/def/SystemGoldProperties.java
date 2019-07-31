package com.jeecg.p3.goldeneggs.def;

import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemGoldProperties {
	public static String domain;
	public final static String defaultJwid;
	/**签名密钥*/
	public final static String SIGN_KEY;
	/**活动文本的活动ID*/
	public final static String oldActCode;


	static {
		PropertiesUtil globalp = new PropertiesUtil("jeewx.properties");
		domain = globalp.readProperty("oAuthDomain");
		defaultJwid = globalp.readProperty("defaultJwid");
		SIGN_KEY = globalp.readProperty("oAuthSignKey");
	}
	
	static {
		PropertiesUtil globalp = new PropertiesUtil("goldeneggs.properties");
		oldActCode= globalp.readProperty("txtActId");
	}
	
	

	
}
