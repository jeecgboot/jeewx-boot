package com.jeecg.p3.baseApi.service.impl;


import com.jeecg.p3.baseApi.dao.BaseApiJwidDao;
import com.jeecg.p3.baseApi.service.BaseApiJwidService;
import com.jeecg.p3.baseApi.vo.OpenAccountVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("baseApiJwidService")
public class BaseApiJwidServiceImpl implements BaseApiJwidService {
	
	@Resource
	private BaseApiJwidDao baseApiJwidDao;
	
	@Override
	public String getQrcodeUrl(String jwid) {
		String qrcodeUrl = baseApiJwidDao.queryOneByJwid(jwid);
		return qrcodeUrl;
	}
	
	@Override
	public String queryTicketByJwid(String jwid) {
		return baseApiJwidDao.queryTicketByJwid(jwid);
	}

	/**
	 * 查询，通过appid查询，查询第三方平台账号配置
	 * @param appid
	 * @return
	 */
	public OpenAccountVo queryOneByAppid(String appid){
		return baseApiJwidDao.queryOneByAppid(appid);
	}

	@Override
	public String queryNameByJwid(String jwid) {
		return baseApiJwidDao.queryNameByJwid(jwid);
	}

}
