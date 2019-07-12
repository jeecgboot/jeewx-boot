package com.jeecg.p3.weixin.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinAutoresponseDefault;

/**
 * 描述：</b>未识别回复语<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时11分50秒 星期五 
 * @version:1.0
 */
public interface WeixinAutoresponseDefaultService {
	
	
	public void doAdd(WeixinAutoresponseDefault weixinAutoresponseDefault);
	
	public void doEdit(WeixinAutoresponseDefault weixinAutoresponseDefault);
	
	public void doDelete(String id);
	
	public WeixinAutoresponseDefault queryById(String id);
	
	public PageList<WeixinAutoresponseDefault> queryPageList(PageQuery<WeixinAutoresponseDefault> pageQuery);
	
	/**
	 * 根据触发类型查询记录是否存在
	 * @param msgType
	 * @return
	 */
	public WeixinAutoresponseDefault queryBymsgTriggerType(String msgTriggerType,String jwid);
}

