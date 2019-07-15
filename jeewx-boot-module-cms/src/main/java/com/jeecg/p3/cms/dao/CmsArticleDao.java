package com.jeecg.p3.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.cms.entity.CmsArticle;

/**
 * 描述：</b>文章管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
public interface CmsArticleDao extends GenericDao<CmsArticle>{
	
	public Integer count(PageQuery<CmsArticle> pageQuery);
	
	public List<CmsArticle> queryPageList(PageQueryWrapper<CmsArticle> wrapper);
	
	/**
	 * 小程序调用分页接口
	 */
	public List<CmsArticle> getPagesAllMenu(@Param("startRow") int startRow,@Param("pageSize") int pageSize,@Param("query") CmsArticle query, @Param("columnId")String columnId, @Param("ishref")String ishref);
	
	/**
	 * 根据ColumnID查询条数
	 * @param columnId
	 * @return
	 */
	public Integer newCount(@Param("columnId")String columnId, @Param("ishref")String ishref);
	
	/**
	 * 更新发布状态
	 * @author taoYan
	 * @since 2018年9月26日
	 */
	public void updatePublish(@Param("id")String id, @Param("publish")String publish);
}

