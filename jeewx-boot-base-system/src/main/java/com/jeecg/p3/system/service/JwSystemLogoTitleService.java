package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemLogoTitle;

/**
 * 描述：</b>JwSystemLogoTitleService<br>
 * @author：pituo
 * @since：2015年12月29日 12时04分49秒 星期二 
 * @version:1.0
 */
public interface JwSystemLogoTitleService {
	
	
	public void doAdd(JwSystemLogoTitle jwSystemLogoTitle);
	
	public void doEdit(JwSystemLogoTitle jwSystemLogoTitle);
	
	public void doDelete(String id);
	
	public JwSystemLogoTitle queryById(String id);
	
	public PageList<JwSystemLogoTitle> queryPageList(PageQuery<JwSystemLogoTitle> pageQuery);
	
	public List<JwSystemLogoTitle> queryLogoTitle();
	
	
	/**
	 * @功能:通过实体属性查询
	 * @作者:liwenhui 
	 * @时间:2017-9-4 上午10:57:41
	 * @修改：
	 * @param query
	 * @return  
	 */
	public JwSystemLogoTitle queryByProp(JwSystemLogoTitle query);
}

