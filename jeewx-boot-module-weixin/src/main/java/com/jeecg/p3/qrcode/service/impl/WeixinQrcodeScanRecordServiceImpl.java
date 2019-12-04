package com.jeecg.p3.qrcode.service.impl;

import com.jeecg.p3.qrcode.dao.WeixinQrcodeScanRecordDao;
import com.jeecg.p3.qrcode.entity.WeixinQrcodeScanRecord;
import com.jeecg.p3.qrcode.service.WeixinQrcodeScanRecordService;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：</b>二维码扫码记录表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时28分08秒 星期四 
 * @version:1.0
 */
@Service("weixinQrcodeScanRecordService")
public class WeixinQrcodeScanRecordServiceImpl implements WeixinQrcodeScanRecordService {
	@Resource
	private WeixinQrcodeScanRecordDao weixinQrcodeScanRecordDao;

	@Override
	public void doAdd(WeixinQrcodeScanRecord weixinQrcodeScanRecord) {
		weixinQrcodeScanRecordDao.insert(weixinQrcodeScanRecord);
	}

	@Override
	public void doEdit(WeixinQrcodeScanRecord weixinQrcodeScanRecord) {
		weixinQrcodeScanRecordDao.update(weixinQrcodeScanRecord);
	}

	@Override
	public void doDelete(String id) {
		weixinQrcodeScanRecordDao.delete(id);
	}

	@Override
	public WeixinQrcodeScanRecord queryById(String id) {
		WeixinQrcodeScanRecord weixinQrcodeScanRecord  = weixinQrcodeScanRecordDao.get(id);
		return weixinQrcodeScanRecord;
	}

	@Override
	public PageList<WeixinQrcodeScanRecord> queryPageList(
		PageQuery<WeixinQrcodeScanRecord> pageQuery) {
		PageList<WeixinQrcodeScanRecord> result = new PageList<WeixinQrcodeScanRecord>();
		Integer itemCount = weixinQrcodeScanRecordDao.count(pageQuery);
		PageQueryWrapper<WeixinQrcodeScanRecord> wrapper = new PageQueryWrapper<WeixinQrcodeScanRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinQrcodeScanRecord> list = weixinQrcodeScanRecordDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WeixinQrcodeScanRecord> queryList(WeixinQrcodeScanRecord query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WeixinQrcodeScanRecord> queryByExcel(String sceneId, String jwid,String isScanSubscribe) {
		return weixinQrcodeScanRecordDao.queryByExcel(sceneId,jwid,isScanSubscribe);
	}

	@Override
	public void doDeleteScan(String sceneId) {weixinQrcodeScanRecordDao.doDeleteScan(sceneId);}

}
