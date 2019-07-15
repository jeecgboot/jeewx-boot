package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>图文模板素材表<br>
 * @author weijian.zhang
 * @since：2018年07月13日 12时46分36秒 星期五 
 * @version:1.0
 */
public class WeixinNewsitem implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *序号	 */	private String id;	/**	 *图文id	 */	private String newstemplateId;
	//update-begin--Author:zhangweijian  Date: 20180802 for：新增media_id字段	/**	 *图文缩略图的media_id	 */	private String thumbMediaId;
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	/**	 *标题	 */
	//update-end--Author:zhangweijian  Date: 20180802 for：新增media_id字段	private String title;	/**	 *作者	 */	private String author;	/**	 *图片路径	 */	private String imagePath;	/**	 *内容	 */	private String content;	/**	 *	 */	private String description;	/**	 *素材顺序	 */	private String orderNo;
	//update-begin--Author:zhangweijian  Date: 20180726 for：新增图文类型，外部链接字段	/**	 *图文类型	 */	private String newType;
	/**	 *外部链接	 */	private String externalUrl;
	//update-begin--Author:zhangweijian Date:20181016 for：新增封面图是否展示字段
	/**	 *是否显示封面	 */	private String showCoverPic;
	
	public String getShowCoverPic() {
		return showCoverPic;
	}
	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	//update-end--Author:zhangweijian Date:20181016 for：新增封面图是否展示字段
	public String getNewType() {
		return newType;
	}
	public void setNewType(String newType) {
		this.newType = newType;
	}
	public String getExternalUrl() {
		return externalUrl;
	}
	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}
	/**	 *原文链接	 */
	//update-end--Author:zhangweijian  Date: 20180726 for：新增图文类型，外部链接字段	private String url;	/**	 *创建人名称	 */	private String createBy;	/**	 *创建时间	 */	private Date createTime;	/**	 *修改人名称	 */	private String updateBy;	/**	 *修改人时间	 */	private Date updateTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getNewstemplateId() {	    return this.newstemplateId;	}	public void setNewstemplateId(String newstemplateId) {	    this.newstemplateId=newstemplateId;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getAuthor() {	    return this.author;	}	public void setAuthor(String author) {	    this.author=author;	}	public String getImagePath() {	    return this.imagePath;	}	public void setImagePath(String imagePath) {	    this.imagePath=imagePath;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public String getOrderNo() {	    return this.orderNo;	}	public void setOrderNo(String orderNo) {	    this.orderNo=orderNo;	}	public String getUrl() {	    return this.url;	}	public void setUrl(String url) {	    this.url=url;	}	public String getCreateBy() {	    return this.createBy;	}	public void setCreateBy(String createBy) {	    this.createBy=createBy;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(String updateBy) {	    this.updateBy=updateBy;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}
}

