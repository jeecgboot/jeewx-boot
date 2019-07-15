package com.jeecg.p3.cms.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.cms.entity.CmsSite;

/**
 * 描述：</b>网站管理<br>
 * @author：junfeng.zhou
 * @since：2018年09月25日 17时53分26秒 星期二 
 * @version:1.0
 */
public interface CmsSiteService {
	
	
	public void doAdd(CmsSite cmsSite);
	
	public void doEdit(CmsSite cmsSite);
	
	public void doDelete(String id);
	
	public CmsSite queryById(String id);
	
	public PageList<CmsSite> queryPageList(PageQuery<CmsSite> pageQuery);
	
	public List<CmsSite> getAll();
	
	/**
	 * 根据Jwid和创建人判断是否创建过站点
	 * @param jwid
	 * @param createBy
	 * @return
	 */
	public Integer queryByJwidAndCreateBy(String jwid, String createBy);
	
	/**
	 * 根据JWID和CreateBy查询站点信息
	 * @param jwid
	 * @param createBy
	 * @return
	 */
	public CmsSite querySiteByJwidAndCreateBy(String jwid, String createBy);
	

	/**
	 * 根据站点ID查询模板名称
	 * 默认返回 wxhome
	 * @author taoYan
	 * @since 2018年9月29日
	 */
	public String getSiteTemplate(String id);
}

