package com.jeecg.p3.system.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.system.entity.JwSystemActTxt;

/**
 * 描述：</b>JwSystemActTxtDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月11日 11时11分51秒 星期三 
 * @version:1.0
 */
public interface JwSystemActTxtDao extends GenericDao<JwSystemActTxt>{
	
	public Integer count(PageQuery<JwSystemActTxt> pageQuery);
	
	public List<JwSystemActTxt> queryPageList(PageQueryWrapper<JwSystemActTxt> wrapper);
	/**
	 * 通过活动ID查询文本
	 * @param actCode
	 * @return
	 */
	List<JwSystemActTxt> queryByActCode(String actCode);

}

