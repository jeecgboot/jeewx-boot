package com.jeecg.p3.cms.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.cms.entity.CmsArticle;
import com.jeecg.p3.cms.entity.CmsMenu;

/**
 * 描述：</b>文章管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
public interface CmsArticleService {
	
	
	public void doAdd(CmsArticle cmsArticle);
	
	public void doEdit(CmsArticle cmsArticle);
	
	public void doDelete(String id);
	
	public CmsArticle queryById(String id);
	
	public PageList<CmsArticle> queryPageList(PageQuery<CmsArticle> pageQuery);
	
	public PageList<CmsArticle> getPagesAllMenu(PageQuery<CmsArticle> pageQuery, String coulmnId, String ishref);
	
	/**
	 * 根据ColumnID查询条数
	 * @param columnId
	 * @return
	 */
	public Integer newCount(String columnId, String ishref);
	
	public List<CmsMenu> queryAllMenus(String mainId);
	
	public void updatePublish(String id,String publish);
	
}

