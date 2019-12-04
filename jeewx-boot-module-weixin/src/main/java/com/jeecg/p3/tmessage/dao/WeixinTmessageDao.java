package com.jeecg.p3.tmessage.dao;

import com.jeecg.p3.tmessage.entity.WeixinTmessage;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>消息模板表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时21分04秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessageDao extends GenericDao<WeixinTmessage>{
	
	public Integer count(PageQuery<WeixinTmessage> pageQuery);
	
	public List<WeixinTmessage> queryPageList(PageQueryWrapper<WeixinTmessage> wrapper);
	
	/**
	 * 根据jwid查询模板
	 * @param jwid
	 * @return
	 */
	public List<WeixinTmessage> queryByJwid(@Param("jwid") String jwid);
	
	/**
	 * 根据templateId查询模板信息
	 * @param templateId
	 * @return
	 */
	public WeixinTmessage queryByTemplateId(@Param("templateId") String templateId);
	
}

