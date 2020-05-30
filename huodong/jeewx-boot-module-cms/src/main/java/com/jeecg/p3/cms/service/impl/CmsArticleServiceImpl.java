package com.jeecg.p3.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.cms.dao.CmsArticleDao;
import com.jeecg.p3.cms.dao.CmsMenuDao;
import com.jeecg.p3.cms.entity.CmsArticle;
import com.jeecg.p3.cms.entity.CmsMenu;
import com.jeecg.p3.cms.service.CmsArticleService;

/**
 * 描述：</b>文章管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时49分51秒 星期二 
 * @version:1.0
 */
@Service("cmsArticleService")
public class CmsArticleServiceImpl implements CmsArticleService {
	@Resource
	private CmsArticleDao cmsArticleDao;
	
	@Resource
	private CmsMenuDao cmsMenuDao;

	@Override
	public void doAdd(CmsArticle cmsArticle) {
		cmsArticleDao.insert(cmsArticle);
	}

	@Override
	public void doEdit(CmsArticle cmsArticle) {
		cmsArticleDao.update(cmsArticle);
	}

	@Override
	public void doDelete(String id) {
		cmsArticleDao.delete(id);
	}

	@Override
	public CmsArticle queryById(String id) {
		CmsArticle cmsArticle  = cmsArticleDao.get(id);
		return cmsArticle;
	}

	@Override
	public PageList<CmsArticle> queryPageList(
		PageQuery<CmsArticle> pageQuery) {
		PageList<CmsArticle> result = new PageList<CmsArticle>();
		Integer itemCount = cmsArticleDao.count(pageQuery);
		PageQueryWrapper<CmsArticle> wrapper = new PageQueryWrapper<CmsArticle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsArticle> list = cmsArticleDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public PageList<CmsArticle> getPagesAllMenu(PageQuery<CmsArticle> pageQuery, String coulmnId, String ishref) {
		PageList<CmsArticle> result = new PageList<CmsArticle>();
		Integer itemCount = cmsArticleDao.newCount(coulmnId, ishref);
		pageQuery.setPageNo((pageQuery.getPageNo() -1) * pageQuery.getPageSize());
		PageQueryWrapper<CmsArticle> wrapper = new PageQueryWrapper<CmsArticle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsArticle> list = cmsArticleDao.getPagesAllMenu(wrapper.getStartRow(),wrapper.getPageSize(),wrapper.getQuery(),coulmnId, ishref);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Integer newCount(String columnId, String ishref) {
		return cmsArticleDao.newCount(columnId, ishref);
	}
	
	@Override
	public List<CmsMenu> queryAllMenus(String mainId) {
		return cmsMenuDao.queryAllMenus(mainId);
	}

	@Override
	public void updatePublish(String id, String publish) {
		cmsArticleDao.updatePublish(id,publish);
	}
	
}
