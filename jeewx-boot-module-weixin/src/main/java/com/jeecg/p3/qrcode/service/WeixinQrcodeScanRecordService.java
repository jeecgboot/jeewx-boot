package com.jeecg.p3.qrcode.service;

import com.jeecg.p3.qrcode.entity.WeixinQrcodeScanRecord;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import java.util.List;

/**
 * 描述：</b>二维码扫码记录表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时28分08秒 星期四 
 * @version:1.0
 */
public interface WeixinQrcodeScanRecordService {
	
	
	public void doAdd(WeixinQrcodeScanRecord weixinQrcodeScanRecord);
	
	public void doEdit(WeixinQrcodeScanRecord weixinQrcodeScanRecord);
	
	public void doDelete(String id);
	
	public WeixinQrcodeScanRecord queryById(String id);
	
	public PageList<WeixinQrcodeScanRecord> queryPageList(PageQuery<WeixinQrcodeScanRecord> pageQuery);

	public List<WeixinQrcodeScanRecord> queryList(WeixinQrcodeScanRecord query);

	public List<WeixinQrcodeScanRecord> queryByExcel(String sceneId, String jwid, String isScanSubscribe);

	public void doDeleteScan(String sceneId);
}

