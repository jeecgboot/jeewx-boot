package com.jeecg.p3.weixin.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinGzuser;

/**
 * 描述：</b>粉丝表<br>
 * @author：weijian.zhang
 * @since：2018年07月26日 15时38分40秒 星期四 
 * @version:1.0
 */
public interface WeixinGzuserService {
	
	
	public void doAdd(WeixinGzuser weixinGzuser);
	
	public void doEdit(WeixinGzuser weixinGzuser);
	
	public void doDelete(String id);
	
	public WeixinGzuser queryById(String id);
	
	public PageList<WeixinGzuser> queryPageList(PageQuery<WeixinGzuser> pageQuery);

	/**
	 * @功能：获取公众号的关注粉丝
	 * @param next_openid
	 * @param jwid
	 * @return
	 */
	public String getFansListTask(String next_openid, String jwid);
	
	/**
	 * 根据JWID分页查询粉丝信息
	 * @param jwid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<WeixinGzuser> queryNumberByJwid(String jwid, int pageNo, int pageSize);
	
	
	/**
	 * 根据微信用户openid和微信公众号，获取微信用户的昵称等信息
	 * @param openId
	 * @param accountId
	 * @return
     * @throws GetWeixinUserException 
     * @throws Weixin48001Exception 
	 */
	public List<WeixinGzuser> batchGetGzUserInfo(List<Map<String, String>> user_list, String accessToken) throws Exception;
	
	//update-begin--Author:zhangweijian  Date: 20180820 for：根据OpenId查询粉丝信息
	/**
	 * 根据OpenId查询粉丝信息
	 * @param jwid
	 * @param openId
	 * @return
	 */
	public WeixinGzuser queryByOpenId(String openId,String jwid);
	//update-end--Author:zhangweijian  Date: 20180820 for：根据OpenId查询粉丝信息
	
	/**
	 * 根据微信用户OpenId和微信公众号，获取微信用户的昵称等信息
	 * @param openId
	 * @param jwid
	 * @param accessToken
	 * @return
	 */
    public WeixinGzuser getGzUserInfo(String openId,String jwid,String accessToken);
    
    //update-begin-Author:LiShaoQing Date:20181206 for:根据tagId模糊查询用户信息
	/**
	 * 根据tagId模糊查询用户信息
	 */
	public List<WeixinGzuser> queryVagurByTagId(String tagId);
	//update-end-Author:LiShaoQing Date:20181206 for:根据tagId模糊查询用户信息
}

