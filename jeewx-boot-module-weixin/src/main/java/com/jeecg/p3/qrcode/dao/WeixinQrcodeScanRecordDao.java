package com.jeecg.p3.qrcode.dao;

import com.jeecg.p3.qrcode.entity.WeixinQrcodeScanRecord;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>二维码扫码记录表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时28分08秒 星期四 
 * @version:1.0
 */
public interface WeixinQrcodeScanRecordDao extends GenericDao<WeixinQrcodeScanRecord>{
	
	public Integer count(PageQuery<WeixinQrcodeScanRecord> pageQuery);
	
	public List<WeixinQrcodeScanRecord> queryPageList(PageQueryWrapper<WeixinQrcodeScanRecord> wrapper);

	public List<WeixinQrcodeScanRecord> queryByExcel(@Param("sceneId")String sceneId, @Param("jwid")String jwid, @Param("isScanSubscribe")String isScanSubscribe);

	public void doDeleteScan(@Param("sceneId") String sceneId);
}

