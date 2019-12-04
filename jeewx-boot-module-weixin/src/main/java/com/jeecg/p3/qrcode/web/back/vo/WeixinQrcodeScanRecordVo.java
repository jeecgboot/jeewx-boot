package com.jeecg.p3.qrcode.web.back.vo;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;
import org.jeewx.api.core.util.DateUtils;

import com.jeecg.p3.qrcode.util.Excel;

/**
 * 描述：</b>WeixinQrcodeScanRecord:扫码记录表<br>
 * 
 * @author sunkai
 * @since：2018年09月03日 14时09分15秒 星期一
 * @version:1.0
 */
public class WeixinQrcodeScanRecordVo implements Entity<String> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	@Excel(exportName="二维码标题", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String actionInfo;
	@Excel(exportName="二维码场景值", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String sceneId;
	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String openid;
	@Excel(exportName="是否扫码关注", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String isScanSubscribe;
	@Excel(exportName="扫码时间", exportConvertSign = 1, exportFieldWidth = 30, importConvertSign = 0)
	private Date createTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionInfo() {
		return actionInfo;
	}

	public void setActionInfo(String actionInfo) {
		this.actionInfo = actionInfo;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIsScanSubscribe() {
		return isScanSubscribe;
	}

	public void setIsScanSubscribe(String isScanSubscribe) {
		this.isScanSubscribe = isScanSubscribe;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeConvert() {
		 return DateUtils.formatDate(this.createTime,"yyyy-MM-dd HH:mm:ss");
	}
}
