package com.jeecg.p3.weixin.service;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinReceivetext;

/**
 * 描述：</b>消息存储<br>
 * @author：LeeShaoQing
 * @since：2018年07月25日 16时02分13秒 星期三 
 * @version:1.0
 */
public interface WeixinReceivetextService {
	
	
	public void doAdd(WeixinReceivetext weixinReceivetext);
	
	public void doEdit(WeixinReceivetext weixinReceivetext);
	
	public void doDelete(String id);
	
	public WeixinReceivetext queryById(String id);
	
	public PageList<WeixinReceivetext> queryPageList(PageQuery<WeixinReceivetext> pageQuery);

	public List<Map<String, Object>> queryAllChatLog(
			WeixinReceivetext weixinReceivetext);

	public List<Map<String, Object>> queryMoreChatLog(
			WeixinReceivetext weixinReceivetext, String firstRecordTime);
}

