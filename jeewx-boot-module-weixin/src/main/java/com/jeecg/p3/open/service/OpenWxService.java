package com.jeecg.p3.open.service;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonweixin.dao.MyJwWebJwidDao;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

@Service("openWxService")
public class OpenWxService {
	private static final Logger logger = LoggerFactory.getLogger(OpenWxService.class);
	//获取用户基本信息接口
	public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	@Resource
	private MyJwWebJwidDao myJwWebJwidDao;
	
	/**
	 * 通过微信原始ID，获取系统微信公众账号配置信息
	 * @param weixinId
	 * @return
	 */
	public WeixinAccount getWeixinAccountByWeixinOldId(String weixinId){
		WeixinAccount weixinAccount = null;
		if(oConvertUtils.isEmpty(weixinId)){
			return null;
		}
		weixinAccount = JedisPoolUtil.getWxAccount(weixinId);
		if(weixinAccount == null){
			//第一步：从缓存中取公众号信息
			MyJwWebJwid myJwWebJwid = myJwWebJwidDao.queryByJwid(weixinId);
			if(myJwWebJwid==null){
				return null;
			}else{
				//第二步：缓存中获取不到，则从数据库读取，同时保存缓存中
				weixinAccount = new WeixinAccount();
				weixinAccount.setAccountappid(myJwWebJwid.getWeixinAppId());
				weixinAccount.setAccountappsecret(myJwWebJwid.getWeixinAppSecret());
				weixinAccount.setAccountaccesstoken(myJwWebJwid.getAccessToken());
				weixinAccount.setAddtoekntime(myJwWebJwid.getTokenGetTime());
				weixinAccount.setAccountnumber(myJwWebJwid.getWeixinNumber());
				weixinAccount.setApiticket(myJwWebJwid.getApiTicket());
				weixinAccount.setApiticketttime(myJwWebJwid.getApiTicketTime());
				weixinAccount.setAccounttype(myJwWebJwid.getAccountType());
				weixinAccount.setWeixinAccountid(myJwWebJwid.getJwid());//原始ID
				weixinAccount.setJsapiticket(myJwWebJwid.getJsApiTicket());
				weixinAccount.setJsapitickettime(myJwWebJwid.getJsApiTicketTime());
				JedisPoolUtil.putWxAccount(weixinAccount);
				logger.info("---------------------读取数据库，获取公众号信息------------------------------------");
				return weixinAccount;
			}
		}else{
			logger.info("---------------------读取缓存，获取公众号信息------------------------------------");
			return weixinAccount;
		}
	}
	
}

