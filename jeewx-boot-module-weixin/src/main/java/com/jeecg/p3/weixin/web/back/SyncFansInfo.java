package com.jeecg.p3.weixin.web.back;

import java.util.Date;
import java.util.concurrent.Callable;

import org.jeecgframework.p3.core.utils.common.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecg.p3.weixin.dao.WeixinGzuserDao;
import com.jeecg.p3.weixin.entity.WeixinGzuser;

import net.sf.json.JSONArray;

/**
 * @功能：同步粉丝信息
 */
public class SyncFansInfo implements Callable<Boolean> {
	private final static Logger log = LoggerFactory.getLogger(SyncFansInfo.class);
	private WeixinGzuserDao weixinGzuserDao = ApplicationContextUtil.getContext().getBean(WeixinGzuserDao.class);
	
	//公众号ID
	private String jwid;
	//粉丝列表
	private JSONArray openIdArr;
	
	public SyncFansInfo(String jwid,JSONArray openIdArr){
		this.jwid = jwid;
		this.openIdArr = openIdArr;
	}

	@Override
	public Boolean call() throws Exception {
		boolean flag = false;
		try {
			log.info("--------syncFansInfoTask--------公众号【"+this.jwid+"】-获取新粉丝任务开始");
			log.info("--------SyncUserInfoTask--------公众号【"+this.jwid+"】-获取新粉丝任务从微信取得粉丝数："+openIdArr.size());
			//1.获取token
			for(int i=0;i<openIdArr.size();i++){
				//2.将粉丝列表信息存入数据库
				String openId=openIdArr.get(i).toString();
				//3.判断当前粉丝在表中是否存在
				//update-begin--Author:zhangweijian  Date: 20180820 for：添加jwid查询条件
				WeixinGzuser gzuserInfo=weixinGzuserDao.queryByOpenId(openId,jwid);
				//update-end--Author:zhangweijian  Date: 20180820 for：添加jwid查询条件
				//4.不存在，添加
				if(gzuserInfo==null){
					WeixinGzuser newGzuser=new WeixinGzuser();
					newGzuser.setOpenid(openId);
					//update-begin--Author:zhangweijian  Date: 20180806 for：设置默认关注状态，jwid
					newGzuser.setJwid(jwid);
					newGzuser.setSubscribe("1");//默认关注
					//update-end--Author:zhangweijian  Date: 20180806 for：设置默认关注状态，jwid
					newGzuser.setCreateTime(new Date());
					weixinGzuserDao.insert(newGzuser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("--------SyncUserInfoTask--------公众号【"+this.jwid+"】-获取新粉丝任务失败："+e.toString());
		}
		return flag;
	}

}