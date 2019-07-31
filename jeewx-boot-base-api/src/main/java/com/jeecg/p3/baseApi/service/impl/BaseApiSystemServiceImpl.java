package com.jeecg.p3.baseApi.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.springframework.stereotype.Service;

import com.jeecg.p3.baseApi.dao.BaseApiSystemDao;
import com.jeecg.p3.baseApi.service.BaseApiSystemService;


@Service("baseApiSystemService")
public class BaseApiSystemServiceImpl implements BaseApiSystemService {
	@Resource
	private BaseApiSystemDao baseApiSystemDao;
	@Override
	public String getHuodongLogoBottomCopyright(String userId) {
		String huodongLogoBottomCopyright = "";
		//第一步： 先通过userId，判断用户是否有个性化logo的权限；没有直接读取系统标准logo内容；如果有则通过用户授权的公众号获取logo配置信息，判断log状态是否应用
		int logset = baseApiSystemDao.isUserLogSet(userId);
		if(logset == 1){
			Map<String,Object> mp = baseApiSystemDao.getUserAuthorized(userId);
			int logo_cancel_flag = (Integer) mp.get("logo_cancel_flag");
			//活动底部logo取消状态位 0：不取消  1：取消
			if(logo_cancel_flag==1){
				return "";
			}else{
				huodongLogoBottomCopyright = oConvertUtils.getString(mp.get("logo_set_content"));
				return huodongLogoBottomCopyright;
			}
		}else{
			//第二步：如果用户没有个性化设置logo，则调用系统标准的logo内容
			huodongLogoBottomCopyright = baseApiSystemDao.getHuodongLogoBottomCopyright();
			return huodongLogoBottomCopyright;
		}
		
	}
}
