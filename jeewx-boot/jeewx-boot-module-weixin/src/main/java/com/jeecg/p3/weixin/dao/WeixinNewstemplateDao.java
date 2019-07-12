package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinNewstemplate;

/**
 * 描述：</b>图文模板表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时46分13秒 星期五 
 * @version:1.0
 */
public interface WeixinNewstemplateDao extends GenericDao<WeixinNewstemplate>{
	
	public Integer count(PageQuery<WeixinNewstemplate> pageQuery);
	
	public List<WeixinNewstemplate> queryPageList(PageQueryWrapper<WeixinNewstemplate> wrapper);

	//update-begin--Author:zhangweijian  Date: 20180820 for：获取所有图文素材
	/**
	 * @param jwid 
	 * @param uploadType 
	 * @功能：获取所有图文素材
	 * @return
	 */
	public List<WeixinNewstemplate> getAllItems(@Param("jwid")String jwid, @Param("uploadType")String uploadType);
	//update-end--Author:zhangweijian  Date: 20180820 for：获取所有图文素材
	
}

