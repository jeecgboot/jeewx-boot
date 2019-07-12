package com.jeecg.p3.weixin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinTemplate;

/**
 * 描述：</b>图文样式库表<br>
 * @author：weijian.zhang
 * @since：2018年07月24日 20时01分05秒 星期二 
 * @version:1.0
 */
public interface WeixinTemplateService {
	
	
	public void doAdd(WeixinTemplate weixinTemplate);
	
	public void doEdit(WeixinTemplate weixinTemplate);
	
	public void doDelete(String id);
	
	public WeixinTemplate queryById(String id);
	
	public PageList<WeixinTemplate> queryPageList(PageQuery<WeixinTemplate> pageQuery);

	/**
	 * @功能：获取单个样式库
	 * @param type
	 * @return
	 */
	public List<WeixinTemplate> queryByType(String type);
}

