package com.jeecg.p3.weixin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.weixin.service.WeixinReceivetextService;
import com.jeecg.p3.weixin.entity.WeixinReceivetext;
import com.jeecg.p3.weixin.dao.WeixinReceivetextDao;

/**
 * 描述：</b>消息存储<br>
 * @author：LeeShaoQing
 * @since：2018年07月25日 16时02分13秒 星期三 
 * @version:1.0
 */
@Service("weixinReceivetextService")
public class WeixinReceivetextServiceImpl implements WeixinReceivetextService {
	@Resource
	private WeixinReceivetextDao weixinReceivetextDao;

	@Override
	public void doAdd(WeixinReceivetext weixinReceivetext) {
		weixinReceivetextDao.insert(weixinReceivetext);
	}

	@Override
	public void doEdit(WeixinReceivetext weixinReceivetext) {
		weixinReceivetextDao.update(weixinReceivetext);
	}

	@Override
	public void doDelete(String id) {
		weixinReceivetextDao.delete(id);
	}

	@Override
	public WeixinReceivetext queryById(String id) {
		WeixinReceivetext weixinReceivetext  = weixinReceivetextDao.get(id);
		return weixinReceivetext;
	}

	@Override
	public PageList<WeixinReceivetext> queryPageList(
		PageQuery<WeixinReceivetext> pageQuery) {
		PageList<WeixinReceivetext> result = new PageList<WeixinReceivetext>();
		Integer itemCount = weixinReceivetextDao.count(pageQuery);
		PageQueryWrapper<WeixinReceivetext> wrapper = new PageQueryWrapper<WeixinReceivetext>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinReceivetext> list = weixinReceivetextDao.queryPageList(wrapper);
		//遍历查询结果，获取消息类型
		for(WeixinReceivetext w:list){
			if(w.getMsgType().equals("text")){					//文本消息
				continue;
			}else if(w.getMsgType().equals("news")){			//图文消息
				continue;
			}else if(w.getMsgType().equals("voice")){			//音频消息
				continue;
			}else if(w.getMsgType().equals("video")){			//视频消息
				continue;
			}else if(w.getMsgType().equals("image")){			//图片消息
				JSONObject json = (JSONObject) JSONObject.parse(w.getContent());
				w.setContent(json.getString("PicUrl"));
				continue;
			}else if(w.getMsgType().equals("event")){			//事件消息
				continue;
			}else if(w.getMsgType().equals("shortvideo")){		//小视频消息
				continue;
			}else if(w.getMsgType().equals("link")){			//链接消息
				continue;
			}else if(w.getMsgType().equals("location")){		//地理位置消息
				continue;
			}
		}
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<Map<String, Object>> queryAllChatLog(WeixinReceivetext weixinReceivetext) {
		
		return weixinReceivetextDao.queryAllChatLog(
				 weixinReceivetext);
	}

	@Override
	public List<Map<String, Object>> queryMoreChatLog(
			WeixinReceivetext weixinReceivetext, String firstRecordTime) {
		return weixinReceivetextDao.queryMoreChatLog(weixinReceivetext.getFromUserName(),weixinReceivetext.getToUserName(),firstRecordTime);
	}
	
}
