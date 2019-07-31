package com.jeecg.p3.goldeneggs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;

/**
 * 描述：</b>WxActGoldeneggsDao<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分24秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsDao extends GenericDao<WxActGoldeneggs>{
	
	public Integer count(PageQuery<WxActGoldeneggs> pageQuery);
	
	public List<WxActGoldeneggs> queryPageList(PageQueryWrapper<WxActGoldeneggs> wrapper);

	/**
	 * 修改短链接
	 * @param id
	 * @param shortUrl
	 */
	public void editShortUrl(@Param("id")String id, @Param("shortUrl")String shortUrl);

	
}

