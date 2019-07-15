package com.jeecg.p3.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemProjectClassify;

/**
 * 描述：</b>JwSystemProjectClassifyDao<br>
 * @author：huangqingquan
 * @since：2016年08月08日 09时33分31秒 星期一 
 * @version:1.0
 */
public interface JwSystemProjectClassifyDao extends GenericDao<JwSystemProjectClassify>{
	
	public Integer count(PageQuery<JwSystemProjectClassify> pageQuery);
	
	public List<JwSystemProjectClassify> queryPageList(PageQueryWrapper<JwSystemProjectClassify> wrapper);
	/**
	 * 查询分类通用方法
	 * @param type
	 * @return
	 */
	public List<JwSystemProjectClassify> queryAllByType(@Param("type") String type);
	
	/**
	 * 查询子分类
	 * @param baseId
	 * @return
	 */
	public List<JwSystemProjectClassify> queryAllByBaseId(String baseId);
	
	/**
	 * 查询所有拼接树
	 * @return
	 */
	public List<JwSystemProjectClassify> queryListByAll();
}

