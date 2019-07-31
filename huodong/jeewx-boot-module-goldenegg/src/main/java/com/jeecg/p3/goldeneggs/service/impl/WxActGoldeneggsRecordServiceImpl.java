package com.jeecg.p3.goldeneggs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsPrizesDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRecordDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRecordService;

@Service("wxActGoldeneggsRecordService")
public class WxActGoldeneggsRecordServiceImpl implements WxActGoldeneggsRecordService {
	@Resource
	private WxActGoldeneggsRecordDao wxActGoldeneggsRecordDao;
	@Resource
	private WxActGoldeneggsPrizesDao wxActGoldeneggsPrizesDao;

	@Override
	public void doAdd(WxActGoldeneggsRecord wxActGoldeneggsRecord) {
		wxActGoldeneggsRecordDao.insert(wxActGoldeneggsRecord);
	}

	@Override
	public void doEdit(WxActGoldeneggsRecord wxActGoldeneggsRecord) {
		wxActGoldeneggsRecordDao.update(wxActGoldeneggsRecord);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsRecordDao.delete(id);
	}

	@Override
	public WxActGoldeneggsRecord queryById(String id) {
		WxActGoldeneggsRecord wxActGoldeneggsRecord  = wxActGoldeneggsRecordDao.get(id);
		return wxActGoldeneggsRecord;
	}

	@Override
	public PageList<WxActGoldeneggsRecord> queryPageList(
		PageQuery<WxActGoldeneggsRecord> pageQuery) {
		PageList<WxActGoldeneggsRecord> result = new PageList<WxActGoldeneggsRecord>();
		Integer itemCount = wxActGoldeneggsRecordDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsRecord> wrapper = new PageQueryWrapper<WxActGoldeneggsRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsRecord> list = wxActGoldeneggsRecordDao.queryPageList(wrapper);
		for (WxActGoldeneggsRecord wxActGoldeneggsRecord : list) {
			if("1".equals(wxActGoldeneggsRecord.getPrizesState())){
				WxActGoldeneggsPrizes wxActGoldeneggsPrizes=wxActGoldeneggsPrizesDao.queryByActIdAndAwardIdAndName(wxActGoldeneggsRecord.getActId(),wxActGoldeneggsRecord.getAwardsId(),wxActGoldeneggsRecord.getPrizesName(),wxActGoldeneggsRecord.getRelationId());
				if(wxActGoldeneggsPrizes!=null){
					wxActGoldeneggsRecord.setImg(wxActGoldeneggsPrizes.getImg());
				}
			}
		}
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActGoldeneggsRecord queryByCode(String code) {
		
		return wxActGoldeneggsRecordDao.queryByCode(code);
	}

	@Override
	public List<WxActGoldeneggsRecord> queryList(String actId) {
	
		return wxActGoldeneggsRecordDao.queryList(actId);
	}

	@Override
	public List<WxActGoldeneggsRecord> queryMyList(String openid,String actId) {
		return wxActGoldeneggsRecordDao.queryMyList(openid,actId);
	}

	@Override
	public List<WxActGoldeneggsRecord> queryByWin() {
		return wxActGoldeneggsRecordDao.queryByWin();
	}

	@Override
	public List<WxActGoldeneggsRecord> queryPersonalWin(String openid,String actId) {
		return wxActGoldeneggsRecordDao.queryPersonalWin(openid,actId);
	}

	@Override
	public List<WxActGoldeneggsRecord> exportExcel(String actId, String jwid,String prizesStateFlag) {
		List<WxActGoldeneggsRecord> listUser = wxActGoldeneggsRecordDao.exportRecordListByActidAndJwid(actId, jwid,prizesStateFlag);  
	    return listUser;
	}

	@Override
	public int queryCountByWin(String actId) {
		return  wxActGoldeneggsRecordDao.queryCountByWin(actId);
	}

	@Override
	public List<WxActGoldeneggsRecord> queryByWinAndPage(String actId,int page, int pageSize) {
		return wxActGoldeneggsRecordDao.queryByWinAndPage(actId,page,pageSize);
	}


	@Override
	public List<WxActGoldeneggsRecord> queryByActidAndOpenidAndPrizesStatus(
			String actId, String openid, String prizesStatus) {
		return wxActGoldeneggsRecordDao.queryByActidAndOpenidAndPrizesStatus(
				 actId,  openid,  prizesStatus) ;
	}

	@Override
	public WxActGoldeneggsRecord queryByActIdAndCode(String actId, String code) {
		return wxActGoldeneggsRecordDao.queryByActIdAndCode(actId,code);
	}
	
}
