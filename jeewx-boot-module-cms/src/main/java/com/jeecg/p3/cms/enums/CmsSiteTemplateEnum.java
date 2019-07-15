package com.jeecg.p3.cms.enums;

import java.util.HashMap;
import java.util.Map;

public enum CmsSiteTemplateEnum {

	/** 经典模版 */
	DEFAULT("default", "经典模版"),
	/** 微信模版 */
	WXHOME("wxhome", "微信模版"),
	// update-begin--Author:gj_duzy Date:20181113 for：新增酒店模板
	/** 酒店模版 */
	JDHOME("jdhome", "酒店模版");
	// update-end--Author:gj_duzy Date:20181113 for：新增酒店模板

	/**
	 * 站点样式code
	 */
	private String style;
	/**
	 * 样式描述
	 */
	private String description;

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	CmsSiteTemplateEnum(String style, String description) {
		this.style = style;
		this.description = description;
	}

	public static Map<String, String> getAllEnumData() {
		Map<String, String> map = new HashMap<String, String>();
		for (CmsSiteTemplateEnum e : CmsSiteTemplateEnum.values()) {
			map.put(e.style, e.description);
		}
		return map;

	}

}
