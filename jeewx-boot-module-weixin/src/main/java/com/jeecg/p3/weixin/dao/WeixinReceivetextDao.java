package com.jeecg.p3.weixin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinReceivetext;

/**
 * 描述：</b>消息存储<br>
 * @author：LeeShaoQing
 * @since：2018年07月25日 16时02分13秒 星期三 
 * @version:1.0
 */
public interface WeixinReceivetextDao extends GenericDao<WeixinReceivetext>{
	
	public Integer count(PageQuery<WeixinReceivetext> pageQuery);
	
	public List<WeixinReceivetext> queryPageList(PageQueryWrapper<WeixinReceivetext> wrapper);

	public List<Map<String, Object>> queryAllChatLog(
			WeixinReceivetext weixinReceivetext);

	public List<Map<String, Object>> queryMoreChatLog(
			@Param("fromUserName")String fromUserName,@Param("toUserName")String toUserName, @Param("firstRecordTime")String firstRecordTime);
	
}

