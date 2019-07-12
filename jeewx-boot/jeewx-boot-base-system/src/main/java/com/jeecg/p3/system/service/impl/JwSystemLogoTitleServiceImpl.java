package com.jeecg.p3.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.system.service.JwSystemLogoTitleService;
import com.jeecg.p3.system.entity.JwSystemLogoTitle;
import com.jeecg.p3.system.dao.JwSystemLogoTitleDao;

/**
 * 描述：</b>系统logo、title、head和footer设置表<br>
 * @author：liwenhui
 * @since：2017年08月30日 18时15分25秒 星期三 
 * @version:1.0
 */
@Service("jwSystemLogoTitleService")
public class JwSystemLogoTitleServiceImpl implements JwSystemLogoTitleService {
	@Resource
	private JwSystemLogoTitleDao jwSystemLogoTitleDao;
	@Override
	public void doAdd(JwSystemLogoTitle jwSystemLogoTitle) {
		jwSystemLogoTitleDao.insert(jwSystemLogoTitle);
	}

	@Override
	public void doEdit(JwSystemLogoTitle jwSystemLogoTitle) {
		jwSystemLogoTitleDao.update(jwSystemLogoTitle);
	}

	@Override
	public void doDelete(String id) {
		jwSystemLogoTitleDao.delete(id);
	}

	@Override
	public JwSystemLogoTitle queryById(String id) {
		JwSystemLogoTitle jwSystemLogoTitle  = jwSystemLogoTitleDao.get(id);
		return jwSystemLogoTitle;
	}

	@Override
	public PageList<JwSystemLogoTitle> queryPageList(
		PageQuery<JwSystemLogoTitle> pageQuery) {
		PageList<JwSystemLogoTitle> result = new PageList<JwSystemLogoTitle>();
		Integer itemCount = jwSystemLogoTitleDao.count(pageQuery);
		PageQueryWrapper<JwSystemLogoTitle> wrapper = new PageQueryWrapper<JwSystemLogoTitle>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemLogoTitle> list = jwSystemLogoTitleDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<JwSystemLogoTitle> queryLogoTitle() {
		// TODO Auto-generated method stub
		return jwSystemLogoTitleDao.getAll();
	}

	@Override
	public JwSystemLogoTitle queryByProp(JwSystemLogoTitle query) {
		
		return jwSystemLogoTitleDao.queryByProp(query);
	}
	
}
