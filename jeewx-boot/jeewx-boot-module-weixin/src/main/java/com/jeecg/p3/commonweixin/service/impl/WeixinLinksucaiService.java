package com.jeecg.p3.commonweixin.service.impl;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonweixin.entity.WeixinLinksucai;

/**
 * 描述：</b>WeixinLinksucaiService<br>
 * @author：chen
 * @since：2016年11月14日 10时16分24秒 星期一 
 * @version:1.0
 */
public interface WeixinLinksucaiService {
	
	
	public void doAdd(WeixinLinksucai weixinLinksucai);
	
	public void doEdit(WeixinLinksucai weixinLinksucai);
	
	public void doDelete(String id);
	
	public WeixinLinksucai queryById(String id);
	
	public PageList<WeixinLinksucai> queryPageList(PageQuery<WeixinLinksucai> pageQuery);
}

