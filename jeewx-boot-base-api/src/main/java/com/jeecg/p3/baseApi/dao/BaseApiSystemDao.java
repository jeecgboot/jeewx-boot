package com.jeecg.p3.baseApi.dao;

import java.util.Map;

/**
 * 描述：BaseApiSystemDao
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
public interface BaseApiSystemDao{
	
	
	/**
	 * 获取站点活动底部广告位数据
	 * @return
	 */
	public String getHuodongLogoBottomCopyright();
	/**
	 * 查询用户是否可以个性化设置logo
	 * @param jwid
	 * @return
	 */
	public int isUserLogSet(String userId);
	
	/**
	 * 查询用户授权状态
	 * @param jwid
	 * @return
	 */
	public Map<String,Object> getUserAuthorized(String userId);
	
	/**
	 * 查询是否刷票用户 【报名投票】
	 * @param registrationId
	 * @return
	 */
	public boolean isBrushTicket(String registrationId);
}

