package com.jeecg.p3.commonweixin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.commonweixin.dao.WeixinSystemProjectDao;
import com.jeecg.p3.commonweixin.entity.WeixinSystemProject;
import com.jeecg.p3.commonweixin.service.WeixinSystemProjectService;

@Service("weixinSystemProjectService")
public class WeixinSystemProjectServiceImpl implements WeixinSystemProjectService {
	@Resource
	private WeixinSystemProjectDao weixinSystemProjectDao;

	@Override
	public void doEdit(WeixinSystemProject weixinSystemProject) {
		weixinSystemProjectDao.update(weixinSystemProject);
	}

	@Override
	public WeixinSystemProject queryById(String id) {
		WeixinSystemProject jwSystemProject  = weixinSystemProjectDao.get(id);
		return jwSystemProject;
	}

	@Override
	public PageList<WeixinSystemProject> queryPageList(
		PageQuery<WeixinSystemProject> pageQuery) {
		PageList<WeixinSystemProject> result = new PageList<WeixinSystemProject>();
		Integer itemCount = weixinSystemProjectDao.count(pageQuery);
		PageQueryWrapper<WeixinSystemProject> wrapper = new PageQueryWrapper<WeixinSystemProject>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinSystemProject> list = weixinSystemProjectDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void editHdurl(String projectId,String tableName, String hdurlName, String jwidName,
			String shortUrlName, String linksucai) {
		WeixinSystemProject weixinSystemProject=new WeixinSystemProject();
		weixinSystemProject.setId(projectId);
		weixinSystemProject.setHdurl(linksucai);
		this.doEdit(weixinSystemProject);
		weixinSystemProjectDao.editHdurl(tableName, hdurlName, jwidName, shortUrlName, linksucai);
	}

	@Override
	public List<Map<String,String>> queryAllActByTableName(String tableName) {
		return weixinSystemProjectDao.queryAllActByTableName(tableName);
	}

	@Override
	public void doEditShortByTableName(String tableName, String actId,String shortUrl) {
		weixinSystemProjectDao.doEditShortByTableName(tableName, actId, shortUrl);
	}

	@Override
	public void doEditShortEmpty(String tableName) {
		weixinSystemProjectDao.doEditShortEmpty(tableName);
	}
}
