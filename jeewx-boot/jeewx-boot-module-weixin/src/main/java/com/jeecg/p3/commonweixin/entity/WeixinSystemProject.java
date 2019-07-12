package com.jeecg.p3.commonweixin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>JwSystemProject:活动项目管理表<br>
 * @author huangqingquan
 * @since：2016年08月08日 18时09分26秒 星期一 
 * @version:1.0
 */
public class WeixinSystemProject implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *项目编码	 */	private String code;	/**	 *项目名称	 */	private String name;	/**	 *项目图片	 */	private String img;
	/**
	 * 项目图片
	 */	private String hdurl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeixinSystemProject [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append(", img=");
		builder.append(img);
		builder.append(", hdurl=");
		builder.append(hdurl);
		builder.append("]");
		return builder.toString();
	}}

