package com.jeecg.p3.weixin.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jeecg.p3.weixin.dao.WeixinGzuserDao;
import com.jeecg.p3.weixin.dao.WeixinTagDao;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.web.back.SyncFansInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 描述：</b>粉丝表<br>
 * @author：weijian.zhang
 * @since：2018年07月26日 15时38分40秒 星期四 
 * @version:1.0
 */
@Service("weixinGzuserService")
public class WeixinGzuserServiceImpl implements WeixinGzuserService {
	
	public final static Logger log = LoggerFactory.getLogger(WeixinGzuserServiceImpl.class);
	
	//获取用户列表
	public final static String user_List_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	//获取用户基本信息
	public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//批量获取用户基本信息接口
	public final static String batch_user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	
	@Resource
	private WeixinGzuserDao weixinGzuserDao;
	@Resource
	private WeixinTagDao weixinTagDao;

	@Override
	public void doAdd(WeixinGzuser weixinGzuser) {
		weixinGzuserDao.insert(weixinGzuser);
	}

	@Override
	public void doEdit(WeixinGzuser weixinGzuser) {
		weixinGzuserDao.update(weixinGzuser);
	}

	@Override
	public void doDelete(String id) {
		weixinGzuserDao.delete(id);
	}

	@Override
	public WeixinGzuser queryById(String id) {
		WeixinGzuser weixinGzuser  = weixinGzuserDao.get(id);
		return weixinGzuser;
	}

	@Override
	public PageList<WeixinGzuser> queryPageList(
		PageQuery<WeixinGzuser> pageQuery) {
		PageList<WeixinGzuser> result = new PageList<WeixinGzuser>();
		Integer itemCount = weixinGzuserDao.count(pageQuery);
		PageQueryWrapper<WeixinGzuser> wrapper = new PageQueryWrapper<WeixinGzuser>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinGzuser> list = weixinGzuserDao.queryPageList(wrapper);
		//update-begin--Author:zhangweijian  Date: 20180820 for：获取用户标签名称
		//获取用户的标签名称
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(!StringUtils.isEmpty(list.get(i).getTagidList())){
					String[] tags=list.get(i).getTagidList().split(",");
					String tagName="";
					for(int j=0;j<tags.length;j++){
						String jwid=pageQuery.getQuery().getJwid();
						WeixinTag tag=weixinTagDao.queryByTagIdAndJwid(tags[j],jwid);
						if(tag!=null){
							if(j==tags.length-1){
								tagName+=tag.getName();
							}else{
								tagName+=tag.getName()+",";
							}
						}
					}
					list.get(i).setTagidList(tagName);
				}
			}
		}
		//update-end--Author:zhangweijian  Date: 20180820 for：获取用户标签名称
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WeixinGzuser> queryNumberByJwid(String jwid, int pageNo,
			int pageSize) {
		return weixinGzuserDao.queryNumberByJwid(jwid, pageNo, pageSize);
	}

	@Override
	public List<WeixinGzuser> batchGetGzUserInfo(List<Map<String, String>> user_list, String accessToken)
			throws Exception {
		if(accessToken == null) {
			return null;
		}
		// 调用微信接口
		JSONObject jsonList = batchGetGzUserInfoAPI(user_list, accessToken);

		List<WeixinGzuser> infoList = new ArrayList<WeixinGzuser>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 返回正常数据
		if (jsonList != null && !jsonList.containsKey("errcode")) {
			JSONArray jsonArray = jsonList.getJSONArray("user_info_list");
			List list = JSONArray.toList(jsonArray);
			for (Object object : list) {
				JSONObject jsonObj = JSONObject.fromObject(object);
				String openid = jsonObj.getString("openid");
				if (oConvertUtils.isEmpty(openid)) {
					return null;
				}
				String subscribe = jsonObj.getString("subscribe");
				//未关注
				if ("0".equals(subscribe)) {
					WeixinGzuser userInfo = new WeixinGzuser();
					userInfo.setSubscribe(subscribe);
					userInfo.setOpenid(openid);
					infoList.add(userInfo);
				} else {
					//关注
					WeixinGzuser userInfo = new WeixinGzuser();
					if (jsonObj.containsKey("nickname")) {
						userInfo.setNickname(jsonObj.getString("nickname"));
					}
					if(jsonObj.containsKey("sex")) {
						userInfo.setSex(jsonObj.getString("sex"));
					}
					if(jsonObj.containsKey("city")) {
						userInfo.setCity(jsonObj.getString("city"));
					}
					if(jsonObj.containsKey("province")) {
						userInfo.setProvince(jsonObj.getString("province"));
					}
					if(jsonObj.containsKey("country")) {
						userInfo.setCountry(jsonObj.getString("country"));
					}
					if(jsonObj.containsKey("headimgurl")) {
						userInfo.setHeadimgurl(jsonObj.getString("headimgurl"));
					}
					if(jsonObj.containsKey("subscribe_time")) {
						//update-begin--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
						String subscribeTime = sdf.format(new Date(jsonObj.getLong("subscribe_time")*1000));
						//update-end--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
						userInfo.setSubscribeTime(subscribeTime);
					}
					if(jsonObj.containsKey("groupid")) {
						userInfo.setGroupid(jsonObj.getString("groupid"));
					}
					if(jsonObj.containsKey("qr_scene")) {
						userInfo.setQrScene(jsonObj.getString("qr_scene"));
					}
					if(jsonObj.containsKey("qr_scene_str")) {
						userInfo.setQrSceneStr(jsonObj.getString("qr_scene_str"));
					}
					if(jsonObj.containsKey("language")) {
						userInfo.setLanguage(jsonObj.getString("language"));
					}
					if(jsonObj.containsKey("openid")) {
						userInfo.setOpenid(jsonObj.getString("openid"));
					}
					if(jsonObj.containsKey("subscribe")) {
						userInfo.setSubscribe(jsonObj.getString("subscribe"));
					}
					if(jsonObj.containsKey("subscribe_scene")) {
						userInfo.setSubscribeScene(jsonObj.getString("subscribe_scene"));
					}
 					if(jsonObj.containsKey("remark")) {
						userInfo.setBzname(jsonObj.getString("remark"));
					}
					if(jsonObj.containsKey("unionid")) {
						userInfo.setUnionid(jsonObj.getString("unionid"));
					}
					if (jsonObj.containsKey("tagid_list")) {
						JSONArray tagJsonArr = jsonObj.getJSONArray("tagid_list");
						List<Integer> tagid_list = JSONArray.toList(tagJsonArr);
						if(tagid_list != null && tagid_list.size() > 0) {
							String tags = "";
							for (int i = 0; i < tagid_list.size(); i++) {
								tags += "," +tagid_list.get(i);
							}
							tags = tags.substring(1);
							userInfo.setTagidList(tags);
						}
					}
					infoList.add(userInfo);
				}
			}
			return infoList;
		} else {
			if (jsonList.containsKey("errcode")) {
				log.info("------------获取粉丝数据接口错误码 errcode :  " + jsonList.get("errcode"));
				log.info("-----------------getGzUserInfo--获取粉丝失败--------------------" + jsonList.toString());
			}
		}
		return null;
	}
	
    /**
     * 调用微信接口获取关注用户信息，每次获取最多100条
     * @param user_list
     * @param accessToken
     * @return
     * @throws Exception
     */
    public JSONObject batchGetGzUserInfoAPI(List<Map<String,String>> user_list,String accessToken) throws Exception{
    	String requestUrl = batch_user_info_url.replace("ACCESS_TOKEN", accessToken);
    	JSONObject obj = new JSONObject();
    	obj.put("user_list", user_list);
    	log.info("------------批量获取粉丝数据接口 调用前参数:  "+obj.toString());
    	JSONObject jsonList = WeixinUtil.httpRequest(requestUrl, "POST", obj.toString());
    	log.info("------------批量获取粉丝数据接口 调用后参数:  "+jsonList.toString());
    	return jsonList;
    }

    //update-begin--Author:zhangweijian  Date: 20180820 for：根据openId和jwid查询粉丝信息
    /**
     * @功能：根据openId和jwid查询粉丝信息
     * @param openId
     * @param jwid
     */
	@Override
	public WeixinGzuser queryByOpenId(String openId,String jwid) {
		return weixinGzuserDao.queryByOpenId(openId,jwid);
	}
	//update-end--Author:zhangweijian  Date: 20180820 for：根据openId和jwid查询粉丝信息
	
	/**
	 * 根据微信用户OpenId和微信公众号，获取微信用户的昵称等信息
	 * @param openId
	 * @param jwid
	 * @param accessToken
	 * @return
	 */
	@Override
	public WeixinGzuser getGzUserInfo(String openId, String jwid, String accessToken) {
		if(accessToken == null) {
			return null;
		}
		String requestUrl = user_info_url.replace("ACCESS_TOKEN", accessToken);
		requestUrl = requestUrl.replace("OPENID", openId);
		JSONObject jsonObj = WeixinUtil.httpRequest(requestUrl, "GET", requestUrl);
		if(jsonObj != null && !jsonObj.containsKey("errcode")){
			System.out.println("-======jsonObj =+++" + jsonObj);
			WeixinGzuser userInfo = new WeixinGzuser();
			if (jsonObj.containsKey("nickname")) {
				userInfo.setNickname(jsonObj.getString("nickname"));
			}
			if(jsonObj.containsKey("sex")) {
				userInfo.setSex(jsonObj.getString("sex"));
			}
			if(jsonObj.containsKey("city")) {
				userInfo.setCity(jsonObj.getString("city"));
			}
			if(jsonObj.containsKey("province")) {
				userInfo.setProvince(jsonObj.getString("province"));
			}
			if(jsonObj.containsKey("country")) {
				userInfo.setCountry(jsonObj.getString("country"));
			}
			if(jsonObj.containsKey("headimgurl")) {
				userInfo.setHeadimgurl(jsonObj.getString("headimgurl"));
			}
			if(jsonObj.containsKey("subscribe_time")) {
				//update-begin--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String subscribeTime = sdf.format(new Date(jsonObj.getLong("subscribe_time")*1000));
				userInfo.setSubscribeTime(subscribeTime);
				//update-end--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
			}
			if(jsonObj.containsKey("groupid")) {
				userInfo.setGroupid(jsonObj.getString("groupid"));
			}
			if(jsonObj.containsKey("qr_scene")) {
				userInfo.setQrScene(jsonObj.getString("qr_scene"));
			}
			if(jsonObj.containsKey("qr_scene_str")) {
				userInfo.setQrSceneStr(jsonObj.getString("qr_scene_str"));
			}
			if(jsonObj.containsKey("language")) {
				userInfo.setLanguage(jsonObj.getString("language"));
			}
			if(jsonObj.containsKey("openid")) {
				userInfo.setOpenid(jsonObj.getString("openid"));
			}
			if(jsonObj.containsKey("subscribe")) {
				userInfo.setSubscribe(jsonObj.getString("subscribe"));
			}
			if(jsonObj.containsKey("subscribe_scene")) {
				userInfo.setSubscribeScene(jsonObj.getString("subscribe_scene"));
			}
			if(jsonObj.containsKey("remark")) {
				userInfo.setBzname(jsonObj.getString("remark"));
			}
			if(jsonObj.containsKey("unionid")) {
				userInfo.setUnionid(jsonObj.getString("unionid"));
			}
			if (jsonObj.containsKey("tagid_list")) {
				JSONArray tagJsonArr = jsonObj.getJSONArray("tagid_list");
				List<Integer> tagid_list = JSONArray.toList(tagJsonArr);
				//update-begin-zhangweijian-----Date:20180809---for:tagid_list非空判断
				if(tagid_list != null && tagid_list.size() > 0) {
					String tags = "";
					for (int i = 0; i < tagid_list.size(); i++) {
						tags += "," + tagid_list.get(i);
					}
					tags = tags.substring(1);
					userInfo.setTagidList(tags);
					//update-end-zhangweijian-----Date:20180809---for:tagid_list非空判断
				}
			}
			return userInfo;
		} else {
			log.info("-----------------getGzUserInfo--获取粉丝失败--------------------"+jsonObj.toString());
		}
		return null;
	}


	//update-begin-zhangweijian-----Date:20180807---for:线程移到controller中
	//获取公众号的关注粉丝
	@Override
	public String getFansListTask(String next_openid, String jwid) {
		//获取微信公众账号的关注粉丝(同步openid)
			String returnMsg;
			int total=0;
			try {
				returnMsg = "粉丝同步成功，同步粉丝条数：";
				//获取token
				String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
				if(StringUtils.isNotEmpty(accessToken)){
					//多线程处理数据
					ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1,TimeUnit.SECONDS,new LinkedBlockingQueue());  
					List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>(2000);
					int k=0;
					//获取粉丝列表信息
					String requestUrl=user_List_url.replace("NEXT_OPENID", "").replace("ACCESS_TOKEN", accessToken);
					while(oConvertUtils.isNotEmpty(next_openid) && k<2000){
						k++;
						//调用接口获取粉丝列表(一次最多拉取10000)
						JSONObject jsonObj=WeixinUtil.httpRequest(requestUrl, "GET", "");
						next_openid=null; //防止死循环
						if(jsonObj==null){
					    	continue;
					    }
						if(!jsonObj.containsKey("errmsg")){
							total = jsonObj.getInt("total");
							int count=jsonObj.getInt("count");
							if(count!=0){
								//获取拉取的粉丝的openid
								JSONArray openIdArr = jsonObj.getJSONObject("data").getJSONArray("openid");
								//将粉丝信息存到数据库
								futures.add(executor.submit(new SyncFansInfo(jwid,openIdArr)));
							}
							next_openid = jsonObj.getString("next_openid");
							//使用next_openid继续获取下一页粉丝数据[循环]
							//update-begin--Author:zhangweijian Date:20181015 for：同步粉丝问题
							requestUrl=user_List_url.replace("ACCESS_TOKEN",accessToken).replace("NEXT_OPENID", next_openid);
							//update-end--Author:zhangweijian Date:20181015 for：同步粉丝问题
						}
					}
					executor.shutdown();
					//update-begin-zhangweijian-----Date:20180809---for:线程池结束判断
					while (true) {  
						if (executor.isTerminated()) {  
							break;  
						}  
						Thread.sleep(200);  
					}  
					//update-end-zhangweijian-----Date:20180809---for:线程池结束判断
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "同步任务已启动，请稍候刷新。公众号粉丝总数:"+total;
	}
	//update-end-zhangweijian-----Date:20180807---for:线程移到controller中

	@Override
	public List<WeixinGzuser> queryVagurByTagId(String tagId) {
		return weixinGzuserDao.queryVagurByTagId(tagId);
	}
}
