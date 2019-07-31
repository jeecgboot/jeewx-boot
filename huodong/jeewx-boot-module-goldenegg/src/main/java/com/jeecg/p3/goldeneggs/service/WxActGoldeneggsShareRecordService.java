package com.jeecg.p3.goldeneggs.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;

/**
 * 描述：</b>分享记录表<br>
 * @author：junfeng.zhou
 * @since：2018年10月10日 10时27分14秒 星期三 
 * @version:1.0
 */
public interface WxActGoldeneggsShareRecordService {
	
	
	public void doAdd(WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord);
	
	public void doEdit(WxActGoldeneggsShareRecord wxActGoldeneggsShareRecord);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsShareRecord queryById(String id);
	
	public PageList<WxActGoldeneggsShareRecord> queryPageList(PageQuery<WxActGoldeneggsShareRecord> pageQuery);

	public List<WxActGoldeneggsShareRecord> queryShareRecordByDate(
			String actId, String openid, String format, String string);
}

