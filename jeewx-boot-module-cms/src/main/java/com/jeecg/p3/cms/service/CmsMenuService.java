package com.jeecg.p3.cms.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.cms.entity.CmsMenu;

/**
 * 描述：</b>栏目管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时51分57秒 星期二 
 * @version:1.0
 */
public interface CmsMenuService {
	
	
	public void doAdd(CmsMenu cmsMenu);
	
	public void doEdit(CmsMenu cmsMenu);
	
	public void doDelete(String id);
	
	public CmsMenu queryById(String id);
	
	public PageList<CmsMenu> queryPageList(PageQuery<CmsMenu> pageQuery);
	
	/**
	 * 返回栏目数据
	 * @return
	 */
	public List<CmsMenu> getFirstMenu(String mainId, String ishref);
	
	/**
	 * 根据ColumnId查询子数据
	 * @param id
	 * @return
	 */
	public List<CmsMenu> getChildNode(String id);
	
	public List<CmsMenu> queryAllMenus(String mainId);
}

