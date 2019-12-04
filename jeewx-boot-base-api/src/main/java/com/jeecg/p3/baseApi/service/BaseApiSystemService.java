package com.jeecg.p3.baseApi.service;


/**
 * 系统表相关API
 * @author huangqingquan
 *
 */
public interface BaseApiSystemService {

	/**
	 * 获取站点活动底部广告位数据
	 * @return
	 */
	public String getHuodongLogoBottomCopyright(String jwid);

	/**
	 * 查询project表中的链接
	 * @param code
	 * @return
	 */
	public String getProjectHdurlByCode(String code);

}

