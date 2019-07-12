package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinTagService;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.dao.WeixinTagDao;

/**
 * 描述：</b>粉丝标签表<br>
 * @author：weijian.zhang
 * @since：2018年08月13日 14时53分22秒 星期一 
 * @version:1.0
 */
@Service("weixinTagService")
public class WeixinTagServiceImpl implements WeixinTagService {
	@Resource
	private WeixinTagDao weixinTagDao;

	@Override
	public void doAdd(WeixinTag weixinTag) {
		weixinTagDao.insert(weixinTag);
	}

	@Override
	public void doEdit(WeixinTag weixinTag) {
		weixinTagDao.update(weixinTag);
	}

	@Override
	public void doDelete(String id) {
		weixinTagDao.delete(id);
	}

	@Override
	public WeixinTag queryById(String id) {
		WeixinTag weixinTag  = weixinTagDao.get(id);
		return weixinTag;
	}

	@Override
	public PageList<WeixinTag> queryPageList(
		PageQuery<WeixinTag> pageQuery) {
		PageList<WeixinTag> result = new PageList<WeixinTag>();
		Integer itemCount = weixinTagDao.count(pageQuery);
		PageQueryWrapper<WeixinTag> wrapper = new PageQueryWrapper<WeixinTag>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTag> list = weixinTagDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	/**
	 * @功能：根据jwid清空该公众号创建的标签
	 */
	@Override
	public void deleteTagsByJwid(String jwid) {
		weixinTagDao.deleteTagsByJwid(jwid);
	}

	/**
	 * @功能：根据jwid获取该公众号创建的标签
	 */
	@Override
	public List<WeixinTag> getAllTags(String jwid) {
		return weixinTagDao.getAllTags(jwid);
	}
	
}
