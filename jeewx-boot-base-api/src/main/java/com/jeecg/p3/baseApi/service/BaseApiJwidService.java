package com.jeecg.p3.baseApi.service;

import com.jeecg.p3.baseApi.vo.OpenAccountVo;

/**
 * 获取二维码
 * @author huangqingquan
 *
 */
public interface BaseApiJwidService {

	/**
	 * 获取微信公众号的二维码
	 * @param jwid
	 * @return
	 */
	public String getQrcodeUrl(String jwid);
	
	/**
	 * 获取api凭证
	 * @param jwid
	 * @return
	 */
	public String queryTicketByJwid(String jwid);

	/**
	 * 查询，通过appid查询，查询第三方平台账号配置
	 * @param appid
	 * @return
	 */
	public OpenAccountVo queryOneByAppid(String appid);
}

