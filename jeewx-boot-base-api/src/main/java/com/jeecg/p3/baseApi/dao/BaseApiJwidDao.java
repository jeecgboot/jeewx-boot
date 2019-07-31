package com.jeecg.p3.baseApi.dao;

import com.jeecg.p3.baseApi.vo.OpenAccountVo;

/**
 * 描述：</b>JwWebJwidDao<br>
 * @author：pituo
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
public interface BaseApiJwidDao{
	
	
	/**
	 * 获取公共号的二维码
	 * @param jwid
	 * @return
	 */
	public String queryOneByJwid(String jwid);
	
	/**
	 * 获取api凭证
	 * @param jwid
	 * @return
	 */
	public String queryTicketByJwid(String jwid);

	/**
	 *  查询，通过appid查询，查询第三方平台账号配置
	 * @param appid
	 * @return
	 */
	public OpenAccountVo queryOneByAppid(String appid);
}

