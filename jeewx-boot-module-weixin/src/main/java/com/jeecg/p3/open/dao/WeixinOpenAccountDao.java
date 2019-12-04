package com.jeecg.p3.open.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.open.entity.WeixinOpenAccount;

/**
 * 描述：</b>WeixinOpenAccountDao<br>
 * @author：huangqingquan
 * @since：2016年11月30日 15时05分20秒 星期三 
 * @version:1.0
 */
public interface WeixinOpenAccountDao extends GenericDao<WeixinOpenAccount>{
	
	/**
	 * 查询，通过appid查询，按照获取ticket时间倒叙
	 * @param appid
	 * @return
	 */
	public WeixinOpenAccount queryOneByAppid(@Param("appid") String appid);
	/**
	 * 添加，按照获取WeixinOpenAccount添加
	 * @param WeixinOpenAccount
	 * @return
	 */
	public void insert(@Param("weixinOpenAccount") WeixinOpenAccount weixinOpenAccount);
	/**
	 * 修改，按照获取WeixinOpenAccount修改
	 * @param WeixinOpenAccount
	 * @return
	 */
	public void update(@Param("weixinOpenAccount") WeixinOpenAccount weixinOpenAccount);
	
	public Integer count(PageQuery<WeixinOpenAccount> pageQuery);
	
	public List<WeixinOpenAccount> queryPageList(PageQueryWrapper<WeixinOpenAccount> wrapper);
}