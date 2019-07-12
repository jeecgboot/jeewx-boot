package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinTemplate;

/**
 * 描述：</b>图文样式库表<br>
 * @author：weijian.zhang
 * @since：2018年07月24日 20时01分05秒 星期二 
 * @version:1.0
 */
public interface WeixinTemplateDao extends GenericDao<WeixinTemplate>{
	
	public Integer count(PageQuery<WeixinTemplate> pageQuery);
	
	public List<WeixinTemplate> queryPageList(PageQueryWrapper<WeixinTemplate> wrapper);

	/**
	 * @功能：获取单个样式库
	 * @param type
	 * @return
	 */
	public List<WeixinTemplate> queryByType(String type);
	
}

