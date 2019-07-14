package com.jeecg.p3.system.service;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;


/**
 * 描述：</b>JwWebJwidService<br>
 * @author：pituo
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
public interface MyJwWebJwidService {
	
	
	public void doAdd(MyJwWebJwid myJwWebJwid);
	
	public void doEdit(MyJwWebJwid myJwWebJwid);
	
	public void doDelete(String id);
	
	public MyJwWebJwid queryById(String id);
	
	public PageList<MyJwWebJwid> queryPageList(PageQuery<MyJwWebJwid> pageQuery);
	

	/**
	 * 重置AccessToken
	 * @return 
	 */
	public String resetAccessToken(String id);

	/**
	 * 定时重置Token
	 * P3-Biz-timetask定时任务使用
	 */
	public List<MyJwWebJwid> queryResetTokenList(Date refDate);

	/**
	 * 根据jwid查询用户信息
	 * @param jwid
	 */
	public MyJwWebJwid queryByJwid(String jwid);
	
	public void doAddSystemUserJwid(String id,String jwid,String createBy);
	
	/**
	 * 查询创建人
	 * @param createBy
	 * @return
	 */
	public MyJwWebJwid queryOneByCreateBy(String createBy);
	
	/**
	 * 查询所有微信公众号信息
	 * @return
	 */
	public List<MyJwWebJwid> queryAll();

	//update-begin-zhangweijian-----Date:20180808---for:变更公众号原始ID
	/**
	 * @功能：变更公众号原始ID
	 * @param jwid
	 * @param newJwid
	 * @return
	 */
	public void switchDefaultOfficialAcco(String jwid, String newJwid);
	//update-end-zhangweijian-----Date:20180808---for:变更公众号原始ID

	//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	/**
	 * @功能:根据jwid和用户id查询公众号信息
	 */
	public MyJwWebJwid queryJwidByJwidAndUserId(String jwid, String systemUserid);
	//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	
	public Integer queryGzuserCount(String jwid,String refDate);
	
	public Integer queryMsgCount(String jwid,String refDate);

	public void doUpdate(MyJwWebJwid myJwWebJwid);

	public MyJwWebJwid queryByCreateBy(String systemUserid, String jwid);

	public MyJwWebJwid queryByAppid(String appid);
}

