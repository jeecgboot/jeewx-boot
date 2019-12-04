package com.jeecg.p3.system.impl;

import com.jeecg.p3.commonweixin.dao.MyJwWebJwidDao;
import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.commonweixin.util.AccessTokenUtil;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import com.jeecg.p3.wxconfig.dao.WeixinHuodongBizJwidDao;
import com.jeecg.p3.wxconfig.entity.WeixinHuodongBizJwid;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.util.UUIDGenerator;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("myJwWebJwidService")
public class MyJwWebJwidServiceImpl implements MyJwWebJwidService {
	private static final Logger logger = LoggerFactory.getLogger(MyJwWebJwidServiceImpl.class);
	
	@Resource
	private MyJwWebJwidDao myJwWebJwidDao;
	@Resource
	private WeixinHuodongBizJwidDao weixinHuodongBizJwidDao;
	@Autowired
	private WeixinOpenAccountService weixinOpenAccountService;
	
	//获取（刷新）授权公众号的接口调用凭据（令牌）
	private static String GET_AUTHORIZER_ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=COMPONENT_ACCESS_TOKEN";
	//第三方平台APPID
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAdd(MyJwWebJwid myJwWebJwid) {
		myJwWebJwid.setCreateTime(new Date());
		if(!StringUtils.isEmpty(myJwWebJwid.getJwid())){
			myJwWebJwidDao.doAddSystemUserJwid(UUIDGenerator.generate(),myJwWebJwid.getJwid(),myJwWebJwid.getCreateBy());
		}
		myJwWebJwidDao.insert(myJwWebJwid);
	}
	@Override
	public void doEdit(MyJwWebJwid myJwWebJwid) {
		myJwWebJwidDao.update(myJwWebJwid);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doDelete(String id) {
		//update-begin-alex-----Date:20170316---for:删除jwid数据时，同步删除该jwid与用户的关联关系---
		MyJwWebJwid myJwWebJwid = myJwWebJwidDao.get(id);
		if(myJwWebJwid!=null && myJwWebJwid.getJwid()!=null){
			myJwWebJwidDao.doDelSystemUserJwid(myJwWebJwid.getJwid());
		}
		//update-end-alex-----Date:20170316---for:删除jwid数据时，同步删除该jwid与用户的关联关系---
		myJwWebJwidDao.delete(id);
	}

	@Override
	public MyJwWebJwid queryById(String id) {
		// TODO Auto-generated method stub
		return myJwWebJwidDao.get(id);
	}

	@Override
	public PageList<MyJwWebJwid> queryPageList(PageQuery<MyJwWebJwid> pageQuery) {
		// TODO Auto-generated method stub
		PageList<MyJwWebJwid> result = new PageList<MyJwWebJwid>();
		Integer itemCount = myJwWebJwidDao.count(pageQuery);
		PageQueryWrapper<MyJwWebJwid> wrapper = new PageQueryWrapper<MyJwWebJwid>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<MyJwWebJwid> list = myJwWebJwidDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(),itemCount,pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
	@Override
	public String resetAccessToken(String id) {
		MyJwWebJwid myJwWebJwid = myJwWebJwidDao.get(id);
		if("2".equals(myJwWebJwid.getAuthType())){
			//第三方平台
			logger.info("------------第三方平台扫描授权公众号-----------myJwWebJwid-------"+myJwWebJwid);
			return resetAccessTokenByType2(myJwWebJwid);
		}else{
			logger.info("------------本地授权公众号--------myJwWebJwid-------"+myJwWebJwid);
			return resetAccessTokenByType1(myJwWebJwid);
		}
		
	}
	/**
	 * 手动录入,获取ACCESSTOKEN
	 * @param myJwWebJwid
	 * @return
	 */
	private String resetAccessTokenByType1(MyJwWebJwid myJwWebJwid){
		Map<String, Object> data = AccessTokenUtil.getAccseeToken(myJwWebJwid.getWeixinAppId(), myJwWebJwid.getWeixinAppSecret());
		if(data!=null && "success".equals(data.get("status").toString())){
			myJwWebJwid.setAccessToken(data.get("accessToken").toString());
			myJwWebJwid.setTokenGetTime(new Date());
			myJwWebJwid.setApiTicket(data.get("apiTicket").toString());
			myJwWebJwid.setApiTicketTime(new Date());
			myJwWebJwid.setJsApiTicket(data.get("jsApiTicket").toString());
			myJwWebJwid.setJsApiTicketTime(new Date());
			myJwWebJwidDao.update(myJwWebJwid);
			
			//-------H5平台独立公众号，重置redis缓存-------------------------------------------
			try {
				WeixinAccount po = new WeixinAccount();
				po.setAccountappid(myJwWebJwid.getWeixinAppId());
				po.setAccountappsecret(myJwWebJwid.getWeixinAppSecret());
				po.setAccountaccesstoken(myJwWebJwid.getAccessToken());
				po.setAddtoekntime(myJwWebJwid.getTokenGetTime());
				po.setAccountnumber(myJwWebJwid.getWeixinNumber());
				po.setApiticket(myJwWebJwid.getApiTicket());
				po.setApiticketttime(myJwWebJwid.getApiTicketTime());
				po.setAccounttype(myJwWebJwid.getAccountType());
				po.setWeixinAccountid(myJwWebJwid.getJwid());//原始ID
				po.setJsapiticket(myJwWebJwid.getJsApiTicket());
				po.setJsapitickettime(myJwWebJwid.getJsApiTicketTime());
				JedisPoolUtil.putWxAccount(po);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("----------重置redis缓存token失败-------------"+e.toString());
				return " 没有配置Redis 缓存！";
			}
			//--------H5平台独立公众号，重置redis缓存---------------------------------------
			return "success";
		}else if("responseErr".equals(data.get("status").toString())){
			return data.get("msg").toString();
		}else{
			return null;
		}
	}
	/**
	 * 扫码授权,获取ACCESSTOKEN
	 * @param myJwWebJwid
	 * @return
	 */
	private String resetAccessTokenByType2(MyJwWebJwid myJwWebJwid){
		try {
			WeixinOpenAccount weixinOpenAccount = weixinOpenAccountService.queryOneByAppid(CommonWeixinProperties.component_appid);
			if(weixinOpenAccount==null){
				throw new CommonweixinException("重置accessToken时获取WEIXINOPENACCOUNT为空");
			}

			String getAuthorizerTokenUrl = GET_AUTHORIZER_ACCESS_TOKEN_URL;
			getAuthorizerTokenUrl = getAuthorizerTokenUrl.replace("COMPONENT_ACCESS_TOKEN", weixinOpenAccount.getComponentAccessToken());
			// 拼装参数
			JSONObject js = new JSONObject();
			// 第三方平台appid
			js.put("component_appid", CommonWeixinProperties.component_appid);
			// 授权用户的appid
			js.put("authorizer_appid", myJwWebJwid.getWeixinAppId());
			// 刷新令牌
			js.put("authorizer_refresh_token", myJwWebJwid.getAuthorizerRefreshToken());
			JSONObject jsonObj = WxstoreUtils.httpRequest(getAuthorizerTokenUrl, "POST", js.toString());
			if (jsonObj != null && !jsonObj.containsKey("errcode")) {
				String authorizerAccessToken = jsonObj.getString("authorizer_access_token");
				String authorizerRefreshToken = jsonObj.getString("authorizer_refresh_token");
				myJwWebJwid.setAccessToken(authorizerAccessToken);
				myJwWebJwid.setTokenGetTime(new Date());
				myJwWebJwid.setAuthorizerRefreshToken(authorizerRefreshToken);
				//update jsapiticket
				Map<String, String> apiTicket = AccessTokenUtil.getApiTicket(myJwWebJwid.getAccessToken());
				if("true".equals(apiTicket.get("status"))){
					myJwWebJwid.setApiTicket(apiTicket.get("apiTicket"));
					myJwWebJwid.setApiTicketTime(new Date());
					myJwWebJwid.setJsApiTicket(apiTicket.get("jsApiTicket"));
					myJwWebJwid.setJsApiTicketTime(new Date());
				}
				doEdit(myJwWebJwid);
				
				//-------H5平台独立公众号，重置redis缓存-------------------------------------------
				try {
					WeixinAccount po = new WeixinAccount();
					po.setAccountappid(myJwWebJwid.getWeixinAppId());
					po.setAccountappsecret(myJwWebJwid.getWeixinAppSecret());
					po.setAccountaccesstoken(myJwWebJwid.getAccessToken());
					po.setAddtoekntime(myJwWebJwid.getTokenGetTime());
					po.setAccountnumber(myJwWebJwid.getWeixinNumber());
					po.setApiticket(myJwWebJwid.getApiTicket());
					po.setApiticketttime(myJwWebJwid.getApiTicketTime());
					po.setAccounttype(myJwWebJwid.getAccountType());
					po.setWeixinAccountid(myJwWebJwid.getJwid());//原始ID
					po.setJsapiticket(myJwWebJwid.getJsApiTicket());
					po.setJsapitickettime(myJwWebJwid.getJsApiTicketTime());
					JedisPoolUtil.putWxAccount(po);
				} catch (Exception e) {
					//TODO
					System.out.println("----------定时任务：H5平台独立公众号，重置redis缓存token失败-------------"+e.toString());
				}
				//--------H5平台独立公众号，重置redis缓存---------------------------------------
			} else {
				throw new CommonweixinException("重置Token失败！");
			}
			return "success";
		}catch (CommonweixinException e) {
			e.printStackTrace();
			return e.getMessage();
		}catch (Exception e) {
			e.printStackTrace();
			return "重置accessToken时发生异常:"+e.getMessage();
		}
	}
	@Override
	public List<MyJwWebJwid> queryResetTokenList(Date refDate) {
		
		return myJwWebJwidDao.queryResetTokenList(refDate);
	}

	@Override
	public MyJwWebJwid queryByJwid(String jwid) {
		return myJwWebJwidDao.queryByJwid(jwid);
	}
	
	@Override
	public void doAddSystemUserJwid(String id,String jwid,String createBy){
		myJwWebJwidDao.doAddSystemUserJwid(id,jwid,createBy);
	}
	@Override
	public MyJwWebJwid queryOneByCreateBy(String createBy) {
		return myJwWebJwidDao.queryOneByCreateBy(createBy);
	}
	@Override
	public List<MyJwWebJwid> queryAll() {
		return myJwWebJwidDao.queryAll();
	}
	
	//update-begin-zhangweijian-----Date:20180808---for:变更公众号原始ID
	/**
	 * @功能：变更公众号原始ID
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void switchDefaultOfficialAcco(String dataid,String jwid, String newJwid) {
			//update-begin--Author:zhangweijian  Date: 20180809 for：加try catch
			logger.info("---[变更jwid]------------开始！！！---------------newJwid--"+ newJwid+"----------oldjwid--"+ jwid+"----------dataid--"+ dataid);
			//1.更新系统公众号表
			myJwWebJwidDao.updateWebJwid(jwid,newJwid);
			logger.info("---[变更jwid]------------变更公众号表的 jwid---------------tableName--jw_web_jwid");
			//2.更新系统用户公众号关联表
			myJwWebJwidDao.updateUserJwid(jwid,newJwid);
			logger.info("---[变更jwid]------------更新系统用户公众号关联表----------------tableName--jw_system_user_jwid");
			//3.更新所有业务表
			List<WeixinHuodongBizJwid> jwSystemTables=weixinHuodongBizJwidDao.queryAll();
			for(int i=0;i<jwSystemTables.size();i++){
				String tableName=jwSystemTables.get(i).getTableName();
				//update-begin--Author:zhangweijian Date:20181011 for：更新活动长短链接
				String tableType=jwSystemTables.get(i).getTableType();
				if(tableType.equals("2")){
					//3.1 更新活动表的jwid,hdurl和shorturl
					logger.info("---[变更jwid]------------更新所有业务表（jwid、活动地址）---------------tableName--"+tableName);
					List<Map<String,Object>> tableList=weixinHuodongBizJwidDao.queryHdurls(tableName,jwid);
					Map<String, Object> tableMap=new HashMap<String, Object>();
					String id="";
					String hdurl="";
					if(tableList.size()>0){
						for(int j=0;j<tableList.size();j++){
							tableMap=tableList.get(j);
							for(Map.Entry<String, Object> entry : tableMap.entrySet()){
								if ("id".equals(entry.getKey())) {  
									id = (String) entry.getValue();  
								}else if ("hdurl".equals(entry.getKey())) {  
									hdurl = (String) entry.getValue();  
									hdurl=hdurl.replace(jwid, newJwid);
								}  
							}
							//短连接置空，用户点击链接的时候，再生成短连接，这样可以提高变更jwid速度
							//String shortUrl=WeiXinHttpUtil.getShortUrl(hdurl,newJwid);
							weixinHuodongBizJwidDao.updateShortUrl(tableName,id,jwid,newJwid,null);
							//logger.info("---[变更jwid]------------更新所有业务表（jwid、活动地址）---------------tableName--"+tableName);
						}
					}
				}else{
					//3.2更新微信表的jwid
					logger.info("---[变更jwid]------------更新所有业务表（jwid）--- tableName : " + tableName);
					weixinHuodongBizJwidDao.updateTable(tableName,jwid,newJwid);
				}
				//update-end--Author:zhangweijian Date:20181011 for：更新活动长短链接
			}
			//4.重置公众号的token
			resetAccessToken(dataid);
			logger.info("---[变更jwid]------------重置公众号的token----------- " );
			logger.info("---[变更jwid]------------结束！！！-----------------");
		//update-end--Author:zhangweijian  Date: 20180809 for：加try catch
	}
	//update-end-zhangweijian-----Date:20180808---for:变更公众号原始ID
	
	//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	/**
	 * @功能：根据jwid和用户id查询公众号信息
	 */
	@Override
	public MyJwWebJwid queryJwidByJwidAndUserId(String jwid, String systemUserid) {
		return myJwWebJwidDao.queryJwidByJwidAndUserId(jwid,systemUserid);
	}
	//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	@Override
	public Integer queryGzuserCount(String jwid, String refDate) {
		return myJwWebJwidDao.queryGzuserCount(jwid,refDate);
	}
	
	@Override
	public Integer queryMsgCount(String jwid, String refDate) {
		return myJwWebJwidDao.queryMsgCount(jwid,refDate);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doUpdate(MyJwWebJwid myJwWebJwid) {
		    MyJwWebJwid jwWebJwid = myJwWebJwidDao.queryJwidByJwidAndUserId(myJwWebJwid.getJwid(),myJwWebJwid.getCreateBy());
			//没有就新增
		    if(jwWebJwid ==null){
				myJwWebJwidDao.doAddSystemUserJwid(UUIDGenerator.generate(),myJwWebJwid.getJwid(),myJwWebJwid.getCreateBy());
			}
		    myJwWebJwidDao.update(myJwWebJwid);
	}
	@Override
	public MyJwWebJwid queryByCreateBy(String systemUserid,String jwid) {
		return myJwWebJwidDao.queryByCreateBy(systemUserid,jwid);
	}
	@Override
	public MyJwWebJwid queryByAppid(String appid) {
		return myJwWebJwidDao.queryByAppid(appid);
	}
}
