package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;

/**
 * 描述：</b>WxActGoldeneggsPrizesDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分27秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsPrizesDao extends GenericDao<WxActGoldeneggsPrizes>{
	
	public Integer count(PageQuery<WxActGoldeneggsPrizes> pageQuery);
	
	public List<WxActGoldeneggsPrizes> queryPageList(PageQueryWrapper<WxActGoldeneggsPrizes> wrapper);

	public List<WxActGoldeneggsPrizes> queryPrizes(@Param("jwid")String jwid,@Param("createBy")String createBy);

	/**
	 * 根据活动id查询奖品表
	 * @param actId
	 * @return
	 */
	public List<WxActGoldeneggsPrizes> queryByActId(String actId);
	
	/**
	 * @功能:通过奖品名称查询奖品
	 * @作者:liwenhui 
	 * @时间:2018-3-28 下午02:45:54
	 * @修改：
	 * @param jwid
	 * @param createBy
	 * @param name
	 * @return  
	 */
	public List<WxActGoldeneggsPrizes> queryPrizesByName(@Param("jwid")String jwid,@Param("createBy")String createBy,@Param("name")String name);

	public WxActGoldeneggsPrizes queryByActIdAndAwardId(@Param("actId")String actId,
			@Param("awardsId")String awardsId);

	public WxActGoldeneggsPrizes queryByActIdAndAwardIdAndName(@Param("actId")String actId,
			@Param("awardsId")String awardsId, @Param("prizesName")String prizesName, @Param("relationId")String relationId);

	public WxActGoldeneggsPrizes queryByActIdAndCode(@Param("actId")String actId,
			@Param("cardPsd")String cardPsd);
	
}

