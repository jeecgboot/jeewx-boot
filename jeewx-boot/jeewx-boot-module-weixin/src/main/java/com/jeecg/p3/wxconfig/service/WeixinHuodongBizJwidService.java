package com.jeecg.p3.wxconfig.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.wxconfig.entity.WeixinHuodongBizJwid;

/**
 * 描述：</b>微信活动jwid表<br>
 * @author：weijian.zhang
 * @since：2018年08月08日 09时32分33秒 星期三 
 * @version:1.0
 */
public interface WeixinHuodongBizJwidService {
	
	
	public void doAdd(WeixinHuodongBizJwid weixinHuodongBizJwid);
	
	public void doEdit(WeixinHuodongBizJwid weixinHuodongBizJwid);
	
	public void doDelete(String id);
	
	public WeixinHuodongBizJwid queryById(String id);
	
	public PageList<WeixinHuodongBizJwid> queryPageList(PageQuery<WeixinHuodongBizJwid> pageQuery);
}

