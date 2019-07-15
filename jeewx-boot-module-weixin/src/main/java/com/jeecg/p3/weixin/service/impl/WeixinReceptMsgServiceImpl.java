package com.jeecg.p3.weixin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeecgframework.p3.core.utils.common.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.weixin.service.WeixinReceptMsgService;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.entity.WeixinReceptMsg;
import com.jeecg.p3.weixin.dao.WeixinReceptMsgDao;

/**
 * 描述：</b>客服消息记录表<br>
 * @author：junfeng.zhou
 * @since：2018年10月18日 19时35分31秒 星期四 
 * @version:1.0
 */
@Service("weixinReceptMsgService")
public class WeixinReceptMsgServiceImpl implements WeixinReceptMsgService {
	//客服接口地址
	public  String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	@Resource
	private WeixinReceptMsgDao weixinReceptMsgDao;

	@Override
	public void doAdd(WeixinReceptMsg weixinReceptMsg) {
		weixinReceptMsgDao.insert(weixinReceptMsg);
	}

	@Override
	public void doEdit(WeixinReceptMsg weixinReceptMsg) {
		weixinReceptMsgDao.update(weixinReceptMsg);
	}

	@Override
	public void doDelete(String id) {
		weixinReceptMsgDao.delete(id);
	}

	@Override
	public WeixinReceptMsg queryById(String id) {
		WeixinReceptMsg weixinReceptMsg  = weixinReceptMsgDao.get(id);
		return weixinReceptMsg;
	}

	@Override
	public PageList<WeixinReceptMsg> queryPageList(
		PageQuery<WeixinReceptMsg> pageQuery) {
		PageList<WeixinReceptMsg> result = new PageList<WeixinReceptMsg>();
		Integer itemCount = weixinReceptMsgDao.count(pageQuery);
		PageQueryWrapper<WeixinReceptMsg> wrapper = new PageQueryWrapper<WeixinReceptMsg>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinReceptMsg> list = weixinReceptMsgDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAddAnswer(WeixinReceptMsg weixinReceptMsg) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("touser", weixinReceptMsg.getToUsername());
		map.put("msgtype", "text");
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("content", weixinReceptMsg.getContent());
		map.put("text", m);
		JSONObject jsonObj = new JSONObject(map);
		net.sf.json.JSONObject sendMessage=sendMessage(jsonObj.toString(),weixinReceptMsg.getJwid());
		if(sendMessage!=null && "0".equals(sendMessage.getString("errcode"))){
			weixinReceptMsg.setSendStauts("1");
		}else{
			weixinReceptMsg.setSendStauts("2");
		}
		weixinReceptMsgDao.insert(weixinReceptMsg);
	}
	
	public  net.sf.json.JSONObject sendMessage(String json,String jwid){
		// 调用接口获取access_token
		String accessTocken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
	    if(StringUtils.isNotEmpty(accessTocken)){
	    	String url = send_message_url.replace("ACCESS_TOKEN",accessTocken);
	    	net.sf.json.JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json);
	    	return jsonObject;
	    }
	    return null;
	}

	@Override
	public String sendAnswerAgain(WeixinReceptMsg weixinReceptMsg) {
		String flag=null;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("touser", weixinReceptMsg.getToUsername());
		map.put("msgtype", "text");
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("content", weixinReceptMsg.getContent());
		map.put("text", m);
		JSONObject jsonObj = new JSONObject(map);
		net.sf.json.JSONObject sendMessage=sendMessage(jsonObj.toString(),weixinReceptMsg.getJwid());
		if(sendMessage!=null && "0".equals(sendMessage.getString("errcode"))){
			weixinReceptMsg.setSendStauts("1");
			flag="1";
			weixinReceptMsgDao.update(weixinReceptMsg);
		}else{
			weixinReceptMsg.setSendStauts("2");
			flag="2";
		}
		return flag;
	}
}
