package com.jeecg.p3.baseApi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeecg.p3.baseApi.dao.BaseApiActTxtDao;
import com.jeecg.p3.baseApi.service.BaseApiActTxtService;


@Service("baseApiActTxtService")
public class BaseApiActTxtServiceImpl implements BaseApiActTxtService {
	
	@Resource
	private BaseApiActTxtDao baseApiActTxtDao;
	
	@Override
	public void copyActText(String oldActCode, String newActCode) {
		List<Map<String, Object>> mapList = baseApiActTxtDao.queryListByActCode(oldActCode);
		for(Map<String, Object> map:mapList){
			map.put("id", UUID.randomUUID().toString().replace("-", "").toUpperCase());
			map.put("actCode", newActCode);
			baseApiActTxtDao.insert(map);
		}
	}

	@Override
	public void batchDeleteByActCode(String actCode) {
		baseApiActTxtDao.batchDeleteByActCode(actCode);
	}
	
}
