package com.jeecg.p3.weixin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinAutoresponse;

/**
 * 描述：</b>关键字表<br>
 * @author：LeeShaoQing
 * @since：2018年07月20日 10时11分12秒 星期五 
 * @version:1.0
 */
public interface WeixinAutoresponseService {
	
	
	public void doAdd(WeixinAutoresponse weixinAutoresponse);
	
	public void doEdit(WeixinAutoresponse weixinAutoresponse);
	
	public void doDelete(String id);
	
	public WeixinAutoresponse queryById(String id);
	
	public PageList<WeixinAutoresponse> queryPageList(PageQuery<WeixinAutoresponse> pageQuery);
	
	public List<WeixinAutoresponse> queryByJwid(String jwid);
}

