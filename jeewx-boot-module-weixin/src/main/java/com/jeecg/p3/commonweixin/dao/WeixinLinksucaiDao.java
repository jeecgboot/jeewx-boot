package com.jeecg.p3.commonweixin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonweixin.entity.WeixinLinksucai;


/**
 * 描述：</b>WeixinLinksucaiDao<br>
 * @author：chen
 * @since：2016年11月14日 10时16分24秒 星期一 
 * @version:1.0
 */
public interface WeixinLinksucaiDao extends GenericDao<WeixinLinksucai>{
	
	public Integer count(PageQuery<WeixinLinksucai> pageQuery);
	
	public List<WeixinLinksucai> queryPageList(PageQueryWrapper<WeixinLinksucai> wrapper);
	
}

