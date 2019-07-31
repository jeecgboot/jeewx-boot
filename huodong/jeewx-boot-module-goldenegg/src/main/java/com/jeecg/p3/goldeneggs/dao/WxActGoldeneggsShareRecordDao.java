package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;

/**
 * 描述：</b>分享记录表<br>
 * @author：junfeng.zhou
 * @since：2018年10月10日 10时27分14秒 星期三 
 * @version:1.0
 */
public interface WxActGoldeneggsShareRecordDao extends GenericDao<WxActGoldeneggsShareRecord>{
	
	public Integer count(PageQuery<WxActGoldeneggsShareRecord> pageQuery);
	
	public List<WxActGoldeneggsShareRecord> queryPageList(PageQueryWrapper<WxActGoldeneggsShareRecord> wrapper);

	public List<WxActGoldeneggsShareRecord> queryShareRecordByDate(
			@Param("actId")String actId, @Param("openid")String openid, @Param("refDate")String refDate, @Param("type")String type);
	
}

