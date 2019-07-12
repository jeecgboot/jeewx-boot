package com.jeecg.p3.system.service;

import java.util.List;

import com.jeecg.p3.system.vo.WeixinAccountDto;

/**
 * 描述：</b>JwWebJwidService<br>
 * @author：pituo
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
public interface JwWebJwidService {
	
	public WeixinAccountDto queryJwidNameByJwid(String jwid);
	
	public List<WeixinAccountDto> queryJwids();
	
	/**
	 * 根据jwid和用户id查询微信公众号信息
	 * @param jwid
	 * @param userId
	 * @return
	 */
	public WeixinAccountDto queryJwidByJwidAndUserId(String jwid,String userId);
	
	/**
	 * 根据用户编码查询用户分配的公众号
	 * @param userId
	 * @return
	 */
	public List<WeixinAccountDto> queryJwWebJwidByUserId(String userId);
	
	
	/**
	 * 查询没有分配过用户的公众号
	 * @param userId
	 * @return
	 */
	public List<WeixinAccountDto> queryJwidsNotInUser(String userId);
	
	public void modifyOperateUserJwidRel(String userId,List<String> list);
	
}

