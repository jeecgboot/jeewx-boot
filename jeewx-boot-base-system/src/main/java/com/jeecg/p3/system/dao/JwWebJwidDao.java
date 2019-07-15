package com.jeecg.p3.system.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;
import com.jeecg.p3.system.vo.WeixinAccountDto;

/**
 * 描述：</b>JwWebJwidDao<br>
 * @author：pituo
 * @since：2015年12月17日 10时45分06秒 星期四 
 * @version:1.0
 */
public interface JwWebJwidDao extends GenericDao<WeixinAccountDto>{
	
	/**
	  * 查询全部公众号
	 * @return
	 */
	public List<WeixinAccountDto> queryJwids();
	
	/**
	  * 通过jwid查询公众号的信息（获取登录公众号名字）
	 * @param jwid
	 * @return
	 */
	public WeixinAccountDto queryJwidNameByJwid(String jwid);
	
	/**
	 * 根据jwid和用户id查询微信公众号（用于判断用户是否拥有当前公众号管理权限）
	 * @param jwid
	 * @param userId
	 * @return
	 */
	public WeixinAccountDto queryJwidByJwidAndUserId(@Param("jwid")String jwid,@Param("userId")String userId);
	
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
	
	/**
	 * 保存用户与公众号的授权关系
	 * @param userId
	 * @param jwid
	 */
	public void insertUserJwidRels(@Param("userId")String userId,@Param("jwid")String jwid);
	
	/**
	 * 根据用户编码删除用户和公众号的关联关系
	 * @param userId
	 */
	public void deleteUserJwidByUserid(String userId);
}

