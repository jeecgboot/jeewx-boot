package com.jeecg.p3.goldeneggs.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;

/**
 * 描述：</b>WxActGoldeneggsAwardsService<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分26秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsAwardsService {
	
	
	public void doAdd(WxActGoldeneggsAwards wxActGoldeneggsAwards);
	
	public void doEdit(WxActGoldeneggsAwards wxActGoldeneggsAwards);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsAwards queryById(String id);
	
	public PageList<WxActGoldeneggsAwards> queryPageList(PageQuery<WxActGoldeneggsAwards> pageQuery);

	public List<WxActGoldeneggsAwards> queryAwards(String jwid,String createBy);
	/**
	 * 根据jwid查询奖项
	 * @param jwid
	 */
	public List<WxActGoldeneggsAwards> queryAwards(String jwid);
	
	/**
	 * @功能:通过奖项名称查询奖项
	 * @作者:liwenhui 
	 * @时间:2018-3-28 下午02:38:36
	 * @修改：
	 * @param jwid
	 * @param createBy
	 * @param name
	 * @return  
	 */
	public List<WxActGoldeneggsAwards> queryAwardsByName(String jwid,String createBy,String name);
}

