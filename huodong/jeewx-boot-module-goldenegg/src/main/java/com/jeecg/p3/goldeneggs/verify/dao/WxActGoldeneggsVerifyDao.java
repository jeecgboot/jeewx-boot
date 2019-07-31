package com.jeecg.p3.goldeneggs.verify.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;

/**
 * 描述：</b>砸金蛋审核员表<br>
 * @author：junfeng.zhou
 * @since：2018年10月17日 09时53分08秒 星期三 
 * @version:1.0
 */
public interface WxActGoldeneggsVerifyDao extends GenericDao<WxActGoldeneggsVerify>{
	
	public Integer count(PageQuery<WxActGoldeneggsVerify> pageQuery);
	
	public List<WxActGoldeneggsVerify> queryPageList(PageQueryWrapper<WxActGoldeneggsVerify> wrapper);

	public WxActGoldeneggsVerify queryByOpenId(@Param("openid")String openid, @Param("actId")String actId);

	public WxActGoldeneggsVerify queryAllGoldeneggs(@Param("actId")String actId, @Param("cardPsd")String cardPsd);
	
}

