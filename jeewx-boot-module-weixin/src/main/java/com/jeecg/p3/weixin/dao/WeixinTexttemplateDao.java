package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinTexttemplate;

/**
 * 描述：</b>文本模板表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时45分36秒 星期五 
 * @version:1.0
 */
public interface WeixinTexttemplateDao extends GenericDao<WeixinTexttemplate>{
	
	public Integer count(PageQuery<WeixinTexttemplate> pageQuery);
	
	public List<WeixinTexttemplate> queryPageList(PageQueryWrapper<WeixinTexttemplate> wrapper);

	//update-begin--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
	/**
	 * @param jwid 
	 * @功能：获取所有文本素材
	 * @return
	 */
	public List<WeixinTexttemplate> getAllTemplate(String jwid);
	//update-end--Author:zhangweijian  Date: 20180720 for：获取所有文本素材
	
}

