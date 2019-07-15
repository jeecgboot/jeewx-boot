package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinAutoresponseDefault;

/**
 * 描述：</b>未识别回复语<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时11分50秒 星期五 
 * @version:1.0
 */
public interface WeixinAutoresponseDefaultDao extends GenericDao<WeixinAutoresponseDefault>{
	
	public Integer count(PageQuery<WeixinAutoresponseDefault> pageQuery);
	
	public List<WeixinAutoresponseDefault> queryPageList(PageQueryWrapper<WeixinAutoresponseDefault> wrapper);
	
	/**
	 * 根据触发类型查询记录是否存在
	 * @param msgType
	 * @return
	 */
	public WeixinAutoresponseDefault queryBymsgTriggerType(@Param("msgTriggerType")String msgTriggerType,@Param("jwid")String jwid);
}

