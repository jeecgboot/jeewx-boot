package com.jeecg.p3.goldeneggs.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;

/**
 * 描述：</b>WxActGoldeneggsService<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分24秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsService {
	
	
	public void doAdd(WxActGoldeneggs wxActGoldeneggs);
	
	public String doEdit(WxActGoldeneggs wxActGoldeneggs);
	
	public void doDelete(String id);
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public WxActGoldeneggs queryById(String id);
	
	public PageList<WxActGoldeneggs> queryPageList(PageQuery<WxActGoldeneggs> pageQuery);

	/**
	 * 修改短链接
	 * @param id
	 * @param shortUrl
	 */
	public void editShortUrl(String id, String shortUrl);



}

