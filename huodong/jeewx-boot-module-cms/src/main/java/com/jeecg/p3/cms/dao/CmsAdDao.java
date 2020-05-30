package com.jeecg.p3.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.cms.entity.CmsAd;

/**
 * 描述：</b>广告管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时45分31秒 星期二 
 * @version:1.0
 */
public interface CmsAdDao extends GenericDao<CmsAd>{
	
	public Integer count(PageQuery<CmsAd> pageQuery);
	
	public List<CmsAd> queryPageList(PageQueryWrapper<CmsAd> wrapper);
	
	public List<CmsAd> getAll(@Param("mainId")String mainId);
	
}

