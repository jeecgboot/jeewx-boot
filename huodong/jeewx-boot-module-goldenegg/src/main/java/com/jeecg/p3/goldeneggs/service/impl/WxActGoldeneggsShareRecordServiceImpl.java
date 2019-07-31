package com.jeecg.p3.goldeneggs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsShareRecordService;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsShareRecordDao;

/**
 * 描述：</b>分享记录表<br>
 * @author：junfeng.zhou
 * @since：2018年10月10日 10时27分14秒 星期三 
 * @version:1.0
 */
@Service("wxActGoldeneggsShareRecordService")
public class WxActGoldeneggsShareRecordServiceImpl implements WxActGoldeneggsShareRecordService {
	@Resource
	private WxActGoldeneggsShareRecordDao wxActGoldeneggsShareRecordDao;

	@Override
	public void doAdd(WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord) {
		wxActGoldeneggsShareRecordDao.insert(wxActGoldeneggsShareRecord);
	}

	@Override
	public void doEdit(WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord) {
		wxActGoldeneggsShareRecordDao.update(wxActGoldeneggsShareRecord);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsShareRecordDao.delete(id);
	}

	@Override
	public WxActGoldeneggsShareRecord queryById(String id) {
		WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord  = wxActGoldeneggsShareRecordDao.get(id);
		return wxActGoldeneggsShareRecord;
	}

	@Override
	public PageList<WxActGoldeneggsShareRecord> queryPageList(
		PageQuery<WxActGoldeneggsShareRecord> pageQuery) {
		PageList<WxActGoldeneggsShareRecord> result = new PageList<WxActGoldeneggsShareRecord>();
		Integer itemCount = wxActGoldeneggsShareRecordDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsShareRecord> wrapper = new PageQueryWrapper<WxActGoldeneggsShareRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsShareRecord> list = wxActGoldeneggsShareRecordDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActGoldeneggsShareRecord> queryShareRecordByDate(
			String actId, String openid, String refDate, String type) {
		return wxActGoldeneggsShareRecordDao.queryShareRecordByDate(actId,openid,refDate,type);
	}
	
}
