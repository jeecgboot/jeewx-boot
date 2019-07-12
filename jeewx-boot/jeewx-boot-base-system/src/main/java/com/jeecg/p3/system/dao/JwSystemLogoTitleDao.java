package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemLogoTitle;

/**
 * 描述：</b>系统logo、title、head和footer设置表<br>
 * @author：liwenhui
 * @since：2017年08月30日 18时15分25秒 星期三 
 * @version:1.0
 */
public interface JwSystemLogoTitleDao extends GenericDao<JwSystemLogoTitle>{
	
	public Integer count(PageQuery<JwSystemLogoTitle> pageQuery);
	
	public List<JwSystemLogoTitle> queryPageList(PageQueryWrapper<JwSystemLogoTitle> wrapper);
	
	
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

