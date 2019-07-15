package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.system.dao.JwSystemUserJwidDao;
import com.jeecg.p3.system.dao.JwWebJwidDao;
import com.jeecg.p3.system.service.JwWebJwidService;
import com.jeecg.p3.system.vo.WeixinAccountDto;

@Service("jwWebJwidService")
public class JwWebJwidServiceImpl implements JwWebJwidService {
	@Resource
	private JwWebJwidDao jwWebJwidDao;
	@Resource
	private JwSystemUserJwidDao jwSystemUserJwidDao;

	@Override
	public List<WeixinAccountDto> queryJwids() {
		return jwWebJwidDao.queryJwids();
	}
	
	@Override
	public WeixinAccountDto queryJwidNameByJwid(String jwid) {
		return jwWebJwidDao.queryJwidNameByJwid(jwid);
	}

	@Override
	public List<WeixinAccountDto> queryJwWebJwidByUserId(String userId) {
		return jwWebJwidDao.queryJwWebJwidByUserId(userId);
	}

	@Override
	public WeixinAccountDto queryJwidByJwidAndUserId(String jwid, String userId) {
		return jwWebJwidDao.queryJwidByJwidAndUserId(jwid, userId);
	}

	@Override
	public List<WeixinAccountDto> queryJwidsNotInUser(String userId) {
		return jwWebJwidDao.queryJwidsNotInUser(userId);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void modifyOperateUserJwidRel(String userId, List<String> jwids) {
		this.jwWebJwidDao.deleteUserJwidByUserid(userId);
		if(jwids!=null&&jwids.size()>0){
			for(String jwid:jwids){
				this.jwWebJwidDao.insertUserJwidRels(userId, jwid);
			}
		}
	}
}
