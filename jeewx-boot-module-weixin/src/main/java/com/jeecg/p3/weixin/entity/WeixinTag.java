package com.jeecg.p3.weixin.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>粉丝标签表<br>
 * @author weijian.zhang
 * @since：2018年08月13日 14时53分22秒 星期一 
 * @version:1.0
 */
public class WeixinTag implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *标签id	 */	private String tagid;	/**	 *标签名称	 */	private String name;	/**	 *标签归属公众号原始id	 */	private String jwid;	/**	 *添加时间	 */	private Date addtime;	/**	 *同步时间	 */	private Date synctime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTagid() {	    return this.tagid;	}	public void setTagid(String tagid) {	    this.tagid=tagid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public Date getAddtime() {	    return this.addtime;	}	public void setAddtime(Date addtime) {	    this.addtime=addtime;	}	public Date getSynctime() {	    return this.synctime;	}	public void setSynctime(Date synctime) {	    this.synctime=synctime;	}
}

