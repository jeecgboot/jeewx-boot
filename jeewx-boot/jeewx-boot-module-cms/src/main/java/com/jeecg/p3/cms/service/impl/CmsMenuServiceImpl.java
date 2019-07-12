package com.jeecg.p3.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.cms.service.CmsMenuService;
import com.jeecg.p3.cms.entity.CmsMenu;
import com.jeecg.p3.cms.dao.CmsMenuDao;

/**
 * 描述：</b>栏目管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时51分57秒 星期二 
 * @version:1.0
 */
@Service("cmsMenuService")
public class CmsMenuServiceImpl implements CmsMenuService {
	@Resource
	private CmsMenuDao cmsMenuDao;

	@Override
	public void doAdd(CmsMenu cmsMenu) {
		cmsMenuDao.insert(cmsMenu);
	}

	@Override
	public void doEdit(CmsMenu cmsMenu) {
		cmsMenuDao.update(cmsMenu);
	}

	@Override
	public void doDelete(String id) {
		cmsMenuDao.delete(id);
	}

	@Override
	public CmsMenu queryById(String id) {
		CmsMenu cmsMenu  = cmsMenuDao.get(id);
		return cmsMenu;
	}

	@Override
	public PageList<CmsMenu> queryPageList(
		PageQuery<CmsMenu> pageQuery) {
		PageList<CmsMenu> result = new PageList<CmsMenu>();
		Integer itemCount = cmsMenuDao.count(pageQuery);
		PageQueryWrapper<CmsMenu> wrapper = new PageQueryWrapper<CmsMenu>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<CmsMenu> list = cmsMenuDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<CmsMenu> getFirstMenu(String mainId, String ishref) {
		return cmsMenuDao.getFirstMenu(mainId,ishref);
	}

	@Override
	public List<CmsMenu> getChildNode(String id) {
		return cmsMenuDao.getChildNode(id);
	}

	@Override
	public List<CmsMenu> queryAllMenus(String mainId) {
		return cmsMenuDao.queryAllMenus(mainId);
	}
	
}
