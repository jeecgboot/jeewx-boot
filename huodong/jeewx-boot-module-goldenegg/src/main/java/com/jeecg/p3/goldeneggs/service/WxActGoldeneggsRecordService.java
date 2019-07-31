package com.jeecg.p3.goldeneggs.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;

/**
 * 描述：</b>WxActGoldeneggsRecordService<br>
 * @author：junfeng.zhou
 * @since：2016年06月13日 10时55分39秒 星期一 
 * @version:1.0
 */
public interface WxActGoldeneggsRecordService {
	
	
	public void doAdd(WxActGoldeneggsRecord wxActGoldeneggsRecord);
	
	public void doEdit(WxActGoldeneggsRecord wxActGoldeneggsRecord);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsRecord queryById(String id);
	
	public PageList<WxActGoldeneggsRecord> queryPageList(PageQuery<WxActGoldeneggsRecord> pageQuery);

	public WxActGoldeneggsRecord queryByCode(String code);

	public List<WxActGoldeneggsRecord> queryList(String actId);

	public List<WxActGoldeneggsRecord> queryMyList(String openid,String actId);

	/**
	 * 查询所有中奖名单
	 * @param string
	 * @return
	 */
	public List<WxActGoldeneggsRecord> queryByWin();

	/**
	 * 查询个人中奖名单
	 * @param openid
	 * @param actId 
	 * @return
	 */
	public List<WxActGoldeneggsRecord> queryPersonalWin(String openid, String actId);

	/**
	 * 导出中奖纪录
	 * @param actId
	 * @param jwid
	 * @param prizesStateFlag 
	 * @return
	 * @throws FileNotFoundException 
	 */
	public List<WxActGoldeneggsRecord> exportExcel(String actId, String jwid, String prizesStateFlag);

	public int queryCountByWin(String actId);

	public List<WxActGoldeneggsRecord> queryByWinAndPage(String actId, int i, int pageSize);

	public List<WxActGoldeneggsRecord> queryByActidAndOpenidAndPrizesStatus(
			String actId, String openid, String string);

	public WxActGoldeneggsRecord queryByActIdAndCode(String actId, String code);

	
}

