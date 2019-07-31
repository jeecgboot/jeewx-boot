package com.jeecg.p3.commonweixin.def;

import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众号模块配置文件
 * 
 * @date 2019-06-17
 * @author qinfeng
 * 
 */
public class CommonWeixinProperties {
	private static final Logger logger = LoggerFactory.getLogger(CommonWeixinProperties.class);
	public final static String authhorizationUrl;
	public final static String authhorizationCallBackUrl;
	public final static String getAuthorizerInfo;
	public final static String getApiQueryAuth;
	public final static String defaultJwid;
	public final static String domain;
	public final static String oAuthSignKey;

	public final static String component_appid;
	public final static String COMPONENT_TOKEN;
	public final static String COMPONENT_ENCODINGAESKEY;

	static {
		logger.info("========================Init==============CommonWeixin==============Properties====================");
		PropertiesUtil globalp = new PropertiesUtil("jeewx.properties");
		defaultJwid = globalp.readProperty("defaultJwid");
		domain = globalp.readProperty("oAuthDomain");
		oAuthSignKey = globalp.readProperty("oAuthSignKey");
		component_appid = globalp.readProperty("COMPONENT_APPID");
		COMPONENT_TOKEN = globalp.readProperty("COMPONENT_TOKEN");
		COMPONENT_ENCODINGAESKEY = globalp.readProperty("COMPONENT_ENCODINGAESKEY");
		
		
		
		PropertiesUtil p = new PropertiesUtil("commonweixin.properties");
		authhorizationUrl = p.readProperty("authhorizationUrl");
		authhorizationCallBackUrl = p.readProperty("authhorizationCallBackUrl").replace("${domain}", domain);
		getAuthorizerInfo = p.readProperty("getAuthorizerInfo");
		getApiQueryAuth = p.readProperty("getApiQueryAuth");
		

		logger.debug("-----defaultJwid------ = " + defaultJwid);
		logger.debug("-----domain------ = " + domain);
		logger.debug("-----authhorizationUrl------ = " + authhorizationUrl);
		logger.debug("-----authhorizationCallBackUrl------ = " + authhorizationCallBackUrl);
		logger.debug("-----getAuthorizerInfo------ = " + getAuthorizerInfo);
		logger.debug("-----COMPONENT_APPID------ = " + component_appid);
		logger.debug("-----COMPONENT_TOKEN------ = " + COMPONENT_TOKEN);
		logger.debug("-----COMPONENT_ENCODINGAESKEY------ = " + COMPONENT_ENCODINGAESKEY);
		logger.debug("========================Init==============CommonWeixin==============Properties====================");
	}

}
