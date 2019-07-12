package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinTag;

/**
 * 描述：</b>粉丝标签表<br>
 * @author：weijian.zhang
 * @since：2018年08月13日 14时53分22秒 星期一 
 * @version:1.0
 */
public interface WeixinTagDao extends GenericDao<WeixinTag>{
	
	public Integer count(PageQuery<WeixinTag> pageQuery);
	
	public List<WeixinTag> queryPageList(PageQueryWrapper<WeixinTag> wrapper);

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

	/**
	 * @功能：根据tagId和jwid获取标签信息
	 * @param tagId
	 * @param jwid
	 * @return
	 */
	public WeixinTag queryByTagIdAndJwid(@Param("tagId")String tagId, @Param("jwid")String jwid);
	
}

