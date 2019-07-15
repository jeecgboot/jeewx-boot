package com.jeecg.p3.cms.def;

import org.jeecgframework.p3.core.util.PropertiesUtil;

public class CmsProperties {
	public final static String defaultJwid;
	public final static String domain;
	
	static{
		PropertiesUtil globalp = new PropertiesUtil("jeewx.properties");
		defaultJwid = globalp.readProperty("defaultJwid");
		domain = globalp.readProperty("oAuthDomain");
	}
}
