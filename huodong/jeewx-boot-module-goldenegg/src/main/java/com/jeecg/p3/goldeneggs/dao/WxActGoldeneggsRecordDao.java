package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;

/**
 * 描述：</b>WxActGoldeneggsRecordDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月13日 10时55分39秒 星期一 
 * @version:1.0
 */
public interface WxActGoldeneggsRecordDao extends GenericDao<WxActGoldeneggsRecord>{
	
	public Integer count(PageQuery<WxActGoldeneggsRecord> pageQuery);
	
	public List<WxActGoldeneggsRecord> queryPageList(PageQueryWrapper<WxActGoldeneggsRecord> wrapper);

	public WxActGoldeneggsRecord queryByCode(String code);

	public List<WxActGoldeneggsRecord> queryList(String actId);

	public List<WxActGoldeneggsRecord> queryMyList(@Param("openid")String openid,@Param("actId")String actId);

	public Integer getMaxAwardsSeq(String actId);

	/**
	 * 查询所有中奖名单
	 * @param state
	 * @return
	 */
	public List<WxActGoldeneggsRecord> queryByWin();

	/**
	 * 查询个人中奖名单
	 * @param openid
	 * @param actId 
	 * @return
	 */
	public List<WxActGoldeneggsRecord> queryPersonalWin(@Param("openid")String openid, @Param("actId")String actId);

	/**
	 * 导出砸金蛋中奖名单
	 * @param actId
	 * @param jwid
	 * @param prizesStateFlag 
	 * @return
	 */
	public List<WxActGoldeneggsRecord> exportRecordListByActidAndJwid(
			@Param("actId")String actId, @Param("jwid")String jwid, @Param("prizesStateFlag")String prizesStateFlag);

	public int queryCountByWin(String actId);

	public List<WxActGoldeneggsRecord> queryByWinAndPage(@Param("actId")String actId, @Param("page")int page, @Param("pageSize")int pageSize);

	public List<WxActGoldeneggsRecord> queryByActidAndOpenidAndPrizesStatus(
			@Param("actId")String actId, @Param("openid")String openid, @Param("prizesStatus")String prizesStatus);

	public WxActGoldeneggsRecord queryByActIdAndCode(@Param("actId")String actId, @Param("code")String code);
	
}

