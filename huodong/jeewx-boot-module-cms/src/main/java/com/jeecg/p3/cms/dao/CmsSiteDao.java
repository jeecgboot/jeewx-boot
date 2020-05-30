package com.jeecg.p3.cms.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.cms.entity.CmsSite;

public interface CmsSiteDao extends GenericDao<CmsSite> {
	public Integer count(PageQuery<CmsSite> pageQuery);
	
	public List<CmsSite> queryPageList(PageQueryWrapper<CmsSite> wrapper);
	
	public List<CmsSite> getAll();
	
	/**
	 * 根据Jwid和创建人判断是否创建过站点
	 * @param jwid
	 * @param createBy
	 * @return
	 */
	public Integer queryByJwidAndCreateBy(@Param("jwid")String jwid, @Param("createBy")String createBy);
	
	/**
	 * 根据JWID和CreateBy查询站点信息
	 * @param jwid
	 * @param createBy
	 * @return
	 */
	public CmsSite querySiteByJwidAndCreateBy(@Param("jwid")String jwid, @Param("createBy")String createBy);
}
