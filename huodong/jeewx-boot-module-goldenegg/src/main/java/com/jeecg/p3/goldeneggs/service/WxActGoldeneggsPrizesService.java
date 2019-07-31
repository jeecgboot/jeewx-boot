package com.jeecg.p3.goldeneggs.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;

/**
 * 描述：</b>WxActGoldeneggsPrizesService<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分27秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsPrizesService {
	
	
	public void doAdd(WxActGoldeneggsPrizes wxActGoldeneggsPrizes);
	
	public void doEdit(WxActGoldeneggsPrizes wxActGoldeneggsPrizes);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsPrizes queryById(String id);
	
	public PageList<WxActGoldeneggsPrizes> queryPageList(PageQuery<WxActGoldeneggsPrizes> pageQuery);

	public List<WxActGoldeneggsPrizes> queryPrizes(String jwid,String createBy);

	/**
	 * 根据活动id查询奖品表
	 * @param actId
	 * @return
	 */
	public List<WxActGoldeneggsPrizes> queryByActId(String actId);
	
	/**
	 * @功能:通过奖品名称查询奖品
	 * @作者:liwenhui 
	 * @时间:2018-3-28 下午02:45:54
	 * @修改：
	 * @param jwid
	 * @param createBy
	 * @param name
	 * @return  
	 */
	public List<WxActGoldeneggsPrizes> queryPrizesByName(String jwid,String createBy,String name);
}

