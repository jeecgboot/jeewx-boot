package com.jeecg.p3.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.cms.entity.CmsMenu;

/**
 * 描述：</b>栏目管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时51分57秒 星期二 
 * @version:1.0
 */
public interface CmsMenuDao extends GenericDao<CmsMenu>{
	
	public Integer count(PageQuery<CmsMenu> pageQuery);
	
	public List<CmsMenu> queryPageList(PageQueryWrapper<CmsMenu> wrapper);
	
	/**
	 * 返回栏目数据
	 * @return
	 */
	public List<CmsMenu> getFirstMenu(@Param("mainId")String mainId, @Param("ishref")String ishref);
	
	/**
	 * 根据ColumnId查询子数据
	 * @param id
	 * @return
	 */
	public List<CmsMenu> getChildNode(@Param("id")String id);
	
	/**
	 * 查询所有栏目
	 */
	public List<CmsMenu> queryAllMenus(@Param("mainId")String mainId);
	
	public String getMaxLocalCode(@Param("localCodeLength")String localCodeLength, @Param("parentCode")String parentCode);
}

