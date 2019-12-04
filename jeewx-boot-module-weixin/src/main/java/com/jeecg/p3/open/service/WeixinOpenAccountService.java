package com.jeecg.p3.open.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.open.entity.WeixinOpenAccount;

/**
 * 描述：</b>WeixinOpenAccountService<br>
 * @author：huangqingquan
 * @since：2016年11月30日 15时05分20秒 星期三 
 * @version:1.0
 */
public interface WeixinOpenAccountService {
	
	
	public void doAdd(WeixinOpenAccount weixinOpenAccount);
	
	public void doEdit(WeixinOpenAccount weixinOpenAccount);
	
	public void doDelete(String id);
	
	public WeixinOpenAccount queryById(String id);
	
	/**
	 * 查询，通过appid查询，按照获取ticket时间倒叙
	 * @param appid
	 * @return
	 */
	public WeixinOpenAccount queryOneByAppid(String appid);
	
	public PageList<WeixinOpenAccount> queryPageList(PageQuery<WeixinOpenAccount> pageQuery);
}

