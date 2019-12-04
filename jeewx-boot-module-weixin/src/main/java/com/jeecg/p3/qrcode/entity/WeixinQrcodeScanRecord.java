package com.jeecg.p3.qrcode.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>二维码扫码记录表<br>
 * @author sunkai
 * @since：2018年08月30日 10时28分08秒 星期四 
 * @version:1.0
 */
public class WeixinQrcodeScanRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *openid	 */	private String openid;	/**	 *扫码时间	 */	private Date scanTime;	/**	 *场景值ID	 */	private String sceneId;	/**	 *公众号ID	 */	private String jwid;	/**	 *是否扫码关注 0:非扫码关注,1:扫码关注	 */	private String isScanSubscribe;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public Date getScanTime() {	    return this.scanTime;	}	public void setScanTime(Date scanTime) {	    this.scanTime=scanTime;	}	public String getSceneId() {	    return this.sceneId;	}	public void setSceneId(String sceneId) {	    this.sceneId=sceneId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getIsScanSubscribe() {	    return this.isScanSubscribe;	}	public void setIsScanSubscribe(String isScanSubscribe) {	    this.isScanSubscribe=isScanSubscribe;	}
}

