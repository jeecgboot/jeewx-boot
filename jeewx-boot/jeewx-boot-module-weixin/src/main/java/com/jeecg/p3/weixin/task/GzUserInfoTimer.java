package com.jeecg.p3.weixin.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.util.EmojiFilter;
import com.jeecg.p3.weixin.util.WeixinUtil;

/**
 * 更新用户粉丝数据定时任务
 * @author LeeShaoQing
 *
 */
@Service("GzUserInfoTimer")
public class GzUserInfoTimer {
	
	public final static Logger log = LoggerFactory.getLogger(GzUserInfoTimer.class);
	
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
	@Autowired
	private WeixinGzuserService weixinGzuserService;
	
	/**
    * 这个定时任务的主要作用是根据用户用户openId微信服务器活动用户基本信息并更新，因为这个接口的活动速度较慢，所以没有和获得列表的接口整合在一块
    * */
	public void run(){
		//TODO 未根据条件查询公众号
		List<MyJwWebJwid> myJwWebJwids = myJwWebJwidService.queryAll();
		if(myJwWebJwids != null && myJwWebJwids.size() > 0) {
			for (MyJwWebJwid myJwWebJwid : myJwWebJwids) {
				
				//批量同步公众号粉丝昵称相关数据
				batchInitAccountFensi(myJwWebJwid);
			}
		}
	}
	
	/**
	 * 执行单独某个公众号粉丝批量同步
	 * @param myJwWebJwid
	 */
	public void batchInitAccountFensi(MyJwWebJwid myJwWebJwid) {
		boolean flag = true;
		try {
			//防止token失效，重新获取
			myJwWebJwid = myJwWebJwidService.queryByJwid(myJwWebJwid.getJwid());
			//update-begin-zhangweijian-----Date:20180809---for:accessToken获取修改
			String accessToken=WeiXinHttpUtil.getRedisWeixinToken(myJwWebJwid.getJwid());
			//update-end-zhangweijian-----Date:20180809---for:accessToken获取修改
			int count = 0;
			int pageNo = 0;
			int pageSize = 100;
		    while (flag && count < 10000) {
		    	count++;
				// 用于调用微信接口时 封装openid
				List<Map<String, String>> user_list = new ArrayList<Map<String, String>>();
				// 保存用户信息，同于调用接口后同步本地
				List<WeixinGzuser> infoList = new ArrayList<WeixinGzuser>();
				// 每次获取100条数据
				List<WeixinGzuser> kvList = weixinGzuserService.queryNumberByJwid(myJwWebJwid.getJwid(), pageNo, pageSize);
				// 数据为空时跳出循环
				if (kvList == null || kvList.size() <= 0) {
					flag = false;
					log.info("-----------循环获取粉丝昵称----未获得昵称为空的数据!!!-------JWID-----" + myJwWebJwid.getJwid());
				} else {
					log.info("-------[同步微信粉丝数据]------公众账号：" + myJwWebJwid.getName() + "------------获得昵称为空的数据---------" + kvList.size());
					for (WeixinGzuser maps : kvList) {
						WeixinGzuser weixinGzuser = new WeixinGzuser();
						weixinGzuser.setId(maps.getId());
						weixinGzuser.setOpenid(maps.getOpenid());
						if(oConvertUtils.isNotEmpty(maps.getCreateTime())) {
							//update-begin--Author:zhangweijian  Date: 20180807 for：创建时间
							weixinGzuser.setCreateTime(maps.getCreateTime());
							//update-end--Author:zhangweijian  Date: 20180807 for：创建时间
						}
						Map<String, String> map = new HashMap<String, String>();
						map.put("openid", weixinGzuser.getOpenid());
						map.put("lang", "zh_CN");
						user_list.add(map);
						infoList.add(weixinGzuser);
					}
					List<WeixinGzuser> userInfoList = null;
					try {
						// 调用微信获取用户信息的接口，每次批量100条
						userInfoList = weixinGzuserService.batchGetGzUserInfo(user_list,accessToken);
					} catch (Exception e) {
						log.info("=====同步关注粉丝昵定时任务========异常" + e.toString());
					}
					if (userInfoList == null || userInfoList.size() <= 0) {
						log.info("--公众账号：" + myJwWebJwid.getName() +"-------------------------微信获取粉丝获取失败，不再抓取!!!");
						return;
					}
					// 同步本地与线上的数据
					synchUserInfo(infoList, userInfoList, myJwWebJwid.getJwid());
				}
		    }
		} catch (Exception e) {
			flag = false;
			log.info("=====同步关注粉丝昵定时任务========异常" + e.toString());
		}
	}
	
	/**
	 * 批量同步关注用户
	 * @param gzUserInfoList
	 * @param userInfoList
	 * @param jwid
	 */
	private void synchUserInfo(List<WeixinGzuser> gzUserInfoList, List<WeixinGzuser> userInfoList, String jwid) {
		if (userInfoList == null || userInfoList.size() <= 0) {
			log.info("---------------------------微信获取粉丝获取失败!!!");
			return;
		}
		for (int i = 0; i < gzUserInfoList.size(); i++) {
			// 通过下标值获取同下标对应数据
			WeixinGzuser infoYw = gzUserInfoList.get(i);
			WeixinGzuser info = userInfoList.get(i);
			if (info.getOpenid().equals(infoYw.getOpenid())) {
				try {
					//TODO 处理用户标签数据
					// 用户未关注，只获取 关注状态及openid
					if ("0".equals(info.getSubscribe())) {
						infoYw.setOpenid(info.getOpenid());
						infoYw.setSubscribe(info.getSubscribe());
						infoYw.setTagidList("");
						infoYw.setJwid(jwid);
						this.weixinGzuserService.doEdit(infoYw);
					}else{
						// 用户已关注，获取所有信息
						infoYw.setCity(EmojiFilter.filterEmoji(info.getCity()));
						infoYw.setCountry(EmojiFilter.filterEmoji(info.getCountry()));//增加表情过滤
						infoYw.setHeadimgurl(info.getHeadimgurl());
						infoYw.setNickname(WeixinUtil.encode(info.getNickname().getBytes()));
						infoYw.setNicknameTxt(EmojiFilter.filterEmoji(info.getNickname()));
						infoYw.setOpenid(info.getOpenid());
						infoYw.setProvince(EmojiFilter.filterEmoji(info.getProvince()));//增加表情过滤
						infoYw.setSex(info.getSex());
						infoYw.setSubscribe(info.getSubscribe());
						infoYw.setSubscribeTime(info.getSubscribeTime());
						infoYw.setSubscribeScene(info.getSubscribeScene());
						infoYw.setQrScene(info.getQrScene());
						infoYw.setQrSceneStr(info.getQrSceneStr());
						infoYw.setGroupid(info.getGroupid());
						infoYw.setLanguage(info.getLanguage());
						infoYw.setBzname(info.getBzname());
						infoYw.setTagidList(info.getTagidList());
						infoYw.setJwid(jwid);
						//update-begin--Author:zhangweijian  Date: 20180807 for：添加unionId字段
						infoYw.setUnionid(info.getUnionid());
						//update-end--Author:zhangweijian  Date: 20180807 for：添加unionId字段
						this.weixinGzuserService.doEdit(infoYw);
					}
					log.info("------成功处理昵称------(" + i + ")条--------openid:" + info.getOpenid());
				} catch (Exception e) {
					log.info("-----同步微信用户信息报错-----openid------" + info.getOpenid()+"-----error:"+e.toString());
				}
			}
		}
	}
	
}
