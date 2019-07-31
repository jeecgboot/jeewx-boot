package com.jeecg.p3.baseApi.service.impl;


import javax.annotation.Resource;

import com.jeecg.p3.baseApi.vo.OpenAccountVo;
import org.springframework.stereotype.Service;
import com.jeecg.p3.baseApi.dao.BaseApiJwidDao;
import com.jeecg.p3.baseApi.service.BaseApiJwidService;

@Service("baseApiJwidService")
public class BaseApiJwidServiceImpl implements BaseApiJwidService {
	
	@Resource
	private BaseApiJwidDao baseApiJwidDao;
	
	@Override
	public String getQrcodeUrl(String jwid) {
		String jwWebJwid = baseApiJwidDao.queryOneByJwid(jwid);
		return jwWebJwid==null?null:"/upload/img/commonweixin/"+jwWebJwid;
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

}
