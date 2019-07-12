package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemProjectClassify;

/**
 * 描述：</b>JwSystemProjectClassifyService<br>
 * @author：huangqingquan
 * @since：2016年08月08日 09时33分31秒 星期一 
 * @version:1.0
 */
public interface JwSystemProjectClassifyService {
	
	public void doAdd(JwSystemProjectClassify jwSystemProjectClassify);
	
	public void doEdit(JwSystemProjectClassify jwSystemProjectClassify);
	
	public void doDelete(String id);
	
	public JwSystemProjectClassify queryById(String id);
	
	public PageList<JwSystemProjectClassify> queryPageList(PageQuery<JwSystemProjectClassify> pageQuery);
	/**
	 * 查询分类通用方法
	 * @param type
	 * @return
	 */
	public List<JwSystemProjectClassify> queryAllByType(String type);
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

