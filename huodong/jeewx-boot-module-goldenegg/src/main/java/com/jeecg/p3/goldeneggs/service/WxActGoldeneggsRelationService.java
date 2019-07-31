package com.jeecg.p3.goldeneggs.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;

/**
 * 描述：</b>WxActGoldeneggsRelationService<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分29秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsRelationService {
	
	
	public void doAdd(WxActGoldeneggsRelation wxActGoldeneggsRelation);
	
	public void doEdit(WxActGoldeneggsRelation wxActGoldeneggsRelation);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsRelation queryById(String id);
	
	public PageList<WxActGoldeneggsRelation> queryPageList(PageQuery<WxActGoldeneggsRelation> pageQuery);

	public List<WxActGoldeneggsRelation> queryByActId(String actId);

	public  List<WxActGoldeneggsRelation> queryPrizeAndAward(String actId);

	public WxActGoldeneggsRelation queryByprizeIdAndAwardId(String prizeId, String awardId);

	public List<WxActGoldeneggsRelation> queryByActIdAndJwid(String actId,
			String jwid);

	public Boolean validUsed(String id, String prizeId);
}

