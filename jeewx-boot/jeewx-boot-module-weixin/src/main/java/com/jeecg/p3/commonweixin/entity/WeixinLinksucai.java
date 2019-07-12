package com.jeecg.p3.commonweixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WeixinLinksucai:素材链接管理<br>
 * @author chen
 * @since：2016年11月14日 10时16分24秒 星期一 
 * @version:1.0
 */
public class WeixinLinksucai implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *创建人名称	 */	private String createName;	/**	 *创建日期	 */	private Date createDate;	/**	 *修改人名称	 */	private String updateName;	/**	 *修改日期	 */	private Date updateDate;	/**	 *链接名称	 */	private String name;	/**	 *外部链接	 */	private String outerLink;	/**	 *功能描述	 */	private String content;	/**	 *内部链接	 */	private String innerLink;	/**	 *转换标志	 */	private Integer transferSign;	/**	 *微信账户id	 */	private String accountid;	/**	 *账号邮编	 */	private String postCode;	/**	 *分享状态	 */	private String shareStatus;	/**	 *是否加密（0：不加密，1：加密）	 */	private Integer isEncrypt;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getCreateName() {	    return this.createName;	}	public void setCreateName(String createName) {	    this.createName=createName;	}	public Date getCreateDate() {	    return this.createDate;	}	public void setCreateDate(Date createDate) {	    this.createDate=createDate;	}	public String getUpdateName() {	    return this.updateName;	}	public void setUpdateName(String updateName) {	    this.updateName=updateName;	}	public Date getUpdateDate() {	    return this.updateDate;	}	public void setUpdateDate(Date updateDate) {	    this.updateDate=updateDate;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getOuterLink() {	    return this.outerLink;	}	public void setOuterLink(String outerLink) {	    this.outerLink=outerLink;	}	public String getContent() {	    return this.content;	}	public void setContent(String content) {	    this.content=content;	}	public String getInnerLink() {	    return this.innerLink;	}	public void setInnerLink(String innerLink) {	    this.innerLink=innerLink;	}	public Integer getTransferSign() {	    return this.transferSign;	}	public void setTransferSign(Integer transferSign) {	    this.transferSign=transferSign;	}	public String getAccountid() {	    return this.accountid;	}	public void setAccountid(String accountid) {	    this.accountid=accountid;	}	public String getPostCode() {	    return this.postCode;	}	public void setPostCode(String postCode) {	    this.postCode=postCode;	}	public String getShareStatus() {	    return this.shareStatus;	}	public void setShareStatus(String shareStatus) {	    this.shareStatus=shareStatus;	}	public Integer getIsEncrypt() {	    return this.isEncrypt;	}	public void setIsEncrypt(Integer isEncrypt) {	    this.isEncrypt=isEncrypt;	}
}

