package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;

/**
 * 描述：</b>WxActGoldeneggsRegistrationDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分28秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsRegistrationDao extends GenericDao<WxActGoldeneggsRegistration>{
	
	public Integer count(PageQuery<WxActGoldeneggsRegistration> pageQuery);
	
	public List<WxActGoldeneggsRegistration> queryPageList(PageQueryWrapper<WxActGoldeneggsRegistration> wrapper);

	public WxActGoldeneggsRegistration getOpenid(@Param("openid")String openid,@Param("actId")String actId);

	/**
	 * 更新相同天数的个人数量
	 */
	public Integer updateNumSameDay(@Param("count")Integer count,@Param("perDayNum")Integer perDayNum,@Param("date")String date,@Param("id")String id);
	/**
	 * 更新不同天数的个人数量
	 */
	public Integer updateNumDistinctDay(@Param("count")Integer count,@Param("perDayNum")Integer perDayNum,@Param("date")String date,@Param("id")String id);
	/**
	 * 更新不同天数的0抽奖次数的个人数量
	 */
	public Integer updateZeroCountNumDistinctDay(@Param("numPerDay")Integer numPerDay, @Param("dd")String dd,
			@Param("id")String id);

	public Integer updateZeroCountNumSameDay(@Param("count")Integer count, @Param("numPerDay")Integer numPerDay,
			@Param("dd")String dd, @Param("id")String id);

	public List<WxActGoldeneggsRegistration> queryByActId(String id);

	public Integer queryCountByActId(String id);

	
	
}

