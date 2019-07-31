package com.jeecg.p3.goldeneggs.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRelationDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;

@Service("wxActGoldeneggsRelationService")
public class WxActGoldeneggsRelationServiceImpl implements WxActGoldeneggsRelationService {
	@Resource
	private WxActGoldeneggsRelationDao wxActGoldeneggsRelationDao;

	@Override
	public void doAdd(WxActGoldeneggsRelation wxActGoldeneggsRelation) {
		wxActGoldeneggsRelationDao.insert(wxActGoldeneggsRelation);
	}

	@Override
	public void doEdit(WxActGoldeneggsRelation wxActGoldeneggsRelation) {
		wxActGoldeneggsRelationDao.update(wxActGoldeneggsRelation);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsRelationDao.delete(id);
	}

	@Override
	public WxActGoldeneggsRelation queryById(String id) {
		WxActGoldeneggsRelation wxActGoldeneggsRelation  = wxActGoldeneggsRelationDao.get(id);
		return wxActGoldeneggsRelation;
	}

	@Override
	public PageList<WxActGoldeneggsRelation> queryPageList(
		PageQuery<WxActGoldeneggsRelation> pageQuery) {
		PageList<WxActGoldeneggsRelation> result = new PageList<WxActGoldeneggsRelation>();
		Integer itemCount = wxActGoldeneggsRelationDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsRelation> wrapper = new PageQueryWrapper<WxActGoldeneggsRelation>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsRelation> list = wxActGoldeneggsRelationDao.queryPageList(wrapper);
		if(list.size()>0){
			 for(int i=0;i<list.size();i++){
				 BigDecimal precent = new BigDecimal("0.01");
				 list.get(i).setProbabilitys(list.get(i).getProbability().divide(precent).stripTrailingZeros().toPlainString());
			 }
		}
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActGoldeneggsRelation> queryByActId(String actId) {
	return wxActGoldeneggsRelationDao.queryByActId(actId);
		
	}

	@Override
	public List<WxActGoldeneggsRelation> queryPrizeAndAward(String actId) {
		return wxActGoldeneggsRelationDao.queryPrizeAndAward(actId);
	}

	@Override
	public WxActGoldeneggsRelation queryByprizeIdAndAwardId(String prizeId,
			String awardId) {
		
		return wxActGoldeneggsRelationDao.queryByprizeIdAndAwardId(prizeId,awardId);
	}

	@Override
	public List<WxActGoldeneggsRelation> queryByActIdAndJwid(String actId,
			String jwid) {
		return wxActGoldeneggsRelationDao.queryByActIdAndJwid(actId, jwid);
	}

	@Override
	public Boolean validUsed(String awardId,String prizeId) {
		int n = wxActGoldeneggsRelationDao.validUsed(awardId,prizeId);
		return (n>0);
	}

}
