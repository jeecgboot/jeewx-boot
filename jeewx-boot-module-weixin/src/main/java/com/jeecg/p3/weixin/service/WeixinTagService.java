package com.jeecg.p3.weixin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinTag;

/**
 * 描述：</b>粉丝标签表<br>
 * @author：weijian.zhang
 * @since：2018年08月13日 14时53分22秒 星期一 
 * @version:1.0
 */
public interface WeixinTagService {
	
	
	public void doAdd(WeixinTag weixinTag);
	
	public void doEdit(WeixinTag weixinTag);
	
	public void doDelete(String id);
	
	public WeixinTag queryById(String id);
	
	public PageList<WeixinTag> queryPageList(PageQuery<WeixinTag> pageQuery);

	/**
	 * @功能：根据jwid清空该公众号创建的标签
	 * @param jwid
	 */
	public void deleteTagsByJwid(String jwid);

	/**
	 * @功能：根据jwid获取该公众号创建的标签
	 * @param jwid
	 * @return
	 */
	public List<WeixinTag> getAllTags(String jwid);
}

