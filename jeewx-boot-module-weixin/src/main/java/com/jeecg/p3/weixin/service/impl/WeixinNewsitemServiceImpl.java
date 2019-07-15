package com.jeecg.p3.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.weixin.service.WeixinNewsitemService;
import com.jeecg.p3.weixin.entity.WeixinNewsitem;
import com.jeecg.p3.weixin.dao.WeixinNewsitemDao;

/**
 * 描述：</b>图文模板素材表<br>
 * @author：weijian.zhang
 * @since：2018年07月13日 12时46分36秒 星期五 
 * @version:1.0
 */
@Service("weixinNewsitemService")
public class WeixinNewsitemServiceImpl implements WeixinNewsitemService {
	@Resource
	private WeixinNewsitemDao weixinNewsitemDao;

	@Override
	public void doAdd(WeixinNewsitem weixinNewsitem) {
		weixinNewsitemDao.insert(weixinNewsitem);
	}

	@Override
	public void doEdit(WeixinNewsitem weixinNewsitem) {
		weixinNewsitemDao.update(weixinNewsitem);
	}

	@Override
	public void doDelete(String id) {
		weixinNewsitemDao.delete(id);
	}

	@Override
	public WeixinNewsitem queryById(String id) {
		WeixinNewsitem weixinNewsitem  = weixinNewsitemDao.get(id);
		return weixinNewsitem;
	}

	@Override
	public PageList<WeixinNewsitem> queryPageList(
		PageQuery<WeixinNewsitem> pageQuery) {
		PageList<WeixinNewsitem> result = new PageList<WeixinNewsitem>();
		Integer itemCount = weixinNewsitemDao.count(pageQuery);
		PageQueryWrapper<WeixinNewsitem> wrapper = new PageQueryWrapper<WeixinNewsitem>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinNewsitem> list = weixinNewsitemDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	//根据newstemplateId获取模板素材
	@Override
	public List<WeixinNewsitem> queryByNewstemplateId(String newstemplateId) {
		return weixinNewsitemDao.queryByNewstemplateId(newstemplateId);
	}

	//update-begin--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
	@Transactional(rollbackFor = {Exception.class})
	//调整素材序号
	@Override
	public void changeOrder(String id, String passiveNewsId,String state) {
		//获取调整的素材信息
		WeixinNewsitem newsItems=weixinNewsitemDao.get(id);
		//获取被动调整的素材信息
		WeixinNewsitem passiveItems=weixinNewsitemDao.get(passiveNewsId);
		if("1".equals(state)){
			newsItems.setOrderNo(String.valueOf(Integer.parseInt(newsItems.getOrderNo())-1));
			passiveItems.setOrderNo(String.valueOf(Integer.parseInt(newsItems.getOrderNo())+1));
		}
		if("0".equals(state)){
			newsItems.setOrderNo(String.valueOf(Integer.parseInt(newsItems.getOrderNo())+1));
			passiveItems.setOrderNo(String.valueOf(Integer.parseInt(newsItems.getOrderNo())-1));
		}
		weixinNewsitemDao.update(newsItems);
		weixinNewsitemDao.update(passiveItems);
	}

	//获取素材最大序号
	@Override
	public String getMaxOrderNo(String newstemplateId) {
		return weixinNewsitemDao.getMaxOrderNo(newstemplateId);
	}
	//update-end--Author:zhangweijian  Date: 20180724 for：获取素材最大序号
	
}
