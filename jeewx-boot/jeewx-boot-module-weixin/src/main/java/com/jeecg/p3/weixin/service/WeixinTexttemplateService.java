package com.jeecg.p3.weixin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinTexttemplate;

/**
 * 描述：</b>文本模板表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时45分36秒 星期五 
 * @version:1.0
 */
public interface WeixinTexttemplateService {
	
	
	public void doAdd(WeixinTexttemplate weixinTexttemplate);
	
	public void doEdit(WeixinTexttemplate weixinTexttemplate);
	
	public void doDelete(String id);
	
	public WeixinTexttemplate queryById(String id);
	
	public PageList<WeixinTexttemplate> queryPageList(PageQuery<WeixinTexttemplate> pageQuery);

	//update-begin--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
	/**
	 * @功能：获取所有文本素材
	 * @param jwid 
	 * @return
	 */
	public List<WeixinTexttemplate> getAllTemplate(String jwid);
	//update-end--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
}

