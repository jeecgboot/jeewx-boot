package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;

/**
 * 描述：</b>WxActGoldeneggsRelationDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分29秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsRelationDao extends GenericDao<WxActGoldeneggsRelation>{
	
	public Integer count(PageQuery<WxActGoldeneggsRelation> pageQuery);
	
	public List<WxActGoldeneggsRelation> queryPageList(PageQueryWrapper<WxActGoldeneggsRelation> wrapper);

	public List<WxActGoldeneggsRelation> queryByActId(String actId);
	public List<WxActGoldeneggsRelation> queryPrizeAndAward(String actId);

	public WxActGoldeneggsRelation queryByprizeIdAndAwardId(@Param("prizeId")String prizeId,
			@Param("awardId")String awardId);

	public List<WxActGoldeneggsRelation> queryByActIdAndJwid(@Param("actId")String actId,
			@Param("jwid")String jwid);

	public void batchDeleteByActId( String actid) ;
	public void bactchDeleteOldAwards(@Param("ids")List<String> ids,@Param("actId")String actId) ;

	public Integer validUsed(@Param("awardId")String awardId,@Param("prizeId")String prizeId);
	
}

