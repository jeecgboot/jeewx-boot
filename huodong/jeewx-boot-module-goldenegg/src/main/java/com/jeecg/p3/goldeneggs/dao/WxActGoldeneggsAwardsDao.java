package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;

/**
 * 描述：</b>WxActGoldeneggsAwardsDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分26秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsAwardsDao extends GenericDao<WxActGoldeneggsAwards>{
	
	public Integer count(PageQuery<WxActGoldeneggsAwards> pageQuery);
	
	public List<WxActGoldeneggsAwards> queryPageList(PageQueryWrapper<WxActGoldeneggsAwards> wrapper);

	public List<WxActGoldeneggsAwards> queryAwards(@Param("jwid")String jwid,@Param("createBy")String createBy);

	/**
	 * 根据jwid查询奖项
	 * @param jwid
	 * @return
	 */
	public List<WxActGoldeneggsAwards> queryAwards(String jwid);
	
	/**
	 * @功能:通过奖项名称查询奖项
	 * @作者:liwenhui 
	 * @时间:2018-3-28 下午02:38:36
	 * @修改：
	 * @param jwid
	 * @param createBy
	 * @param name
	 * @return  
	 */
	public List<WxActGoldeneggsAwards> queryAwardsByName(@Param("jwid")String jwid,@Param("createBy")String createBy,@Param("name")String name);
}

