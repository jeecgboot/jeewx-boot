package com.jeecg.p3.weixin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinNewsitem;

/**
 * 描述：</b>图文模板素材表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时46分36秒 星期五 
 * @version:1.0
 */
public interface WeixinNewsitemService {
	
	
	public void doAdd(WeixinNewsitem weixinNewsitem);
	
	public void doEdit(WeixinNewsitem weixinNewsitem);
	
	public void doDelete(String id);
	
	public WeixinNewsitem queryById(String id);
	
	public PageList<WeixinNewsitem> queryPageList(PageQuery<WeixinNewsitem> pageQuery);

	/**
	 * @功能：根据newstemplateId获取模板素材
	 * @param newstemplateId
	 * @return
	 */
	public List<WeixinNewsitem> queryByNewstemplateId(String newstemplateId);

	//update-begin--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
	/**
	 * @功能：调整素材序号
	 * @param id
	 * @param passiveNewsId
	 */
	public void changeOrder(String id, String passiveNewsId,String state);

	/**
	 * @功能：获取素材最大序号
	 * @param newstemplateId
	 * @return
	 */
	public String getMaxOrderNo(String newstemplateId);
	//update-end--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
}

