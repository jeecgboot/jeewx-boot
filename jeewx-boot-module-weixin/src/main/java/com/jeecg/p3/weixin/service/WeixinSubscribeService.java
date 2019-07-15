package com.jeecg.p3.weixin.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinSubscribe;

/**
 * 描述：</b>关注欢迎语<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时12分09秒 星期五 
 * @version:1.0
 */
public interface WeixinSubscribeService {
	
	
	public void doAdd(WeixinSubscribe weixinSubscribe);
	
	public void doEdit(WeixinSubscribe weixinSubscribe);
	
	public void doDelete(String id);
	
	public WeixinSubscribe queryById(String id);
	
	public PageList<WeixinSubscribe> queryPageList(PageQuery<WeixinSubscribe> pageQuery);
	
	public WeixinSubscribe querySubscribeByJwid(String jwid);
}

