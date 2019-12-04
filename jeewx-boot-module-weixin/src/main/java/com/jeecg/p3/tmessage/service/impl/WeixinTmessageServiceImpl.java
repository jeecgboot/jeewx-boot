package com.jeecg.p3.tmessage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jeecg.p3.tmessage.service.WeixinTmessageService;
import com.jeecg.p3.tmessage.vo.TmessageSendVO;

import net.sf.json.JSONObject;

import com.jeecg.p3.tmessage.entity.WeixinTmessage;
import com.jeecg.p3.tmessage.dao.WeixinTmessageDao;

/**
 * 描述：</b>消息模板表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时21分04秒 星期三 
 * @version:1.0
 */
@Service("weixinTmessageService")
public class WeixinTmessageServiceImpl implements WeixinTmessageService {
	private final static Logger log = LoggerFactory.getLogger(WeixinTmessageServiceImpl.class);
	private static String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	@Resource
	private WeixinTmessageDao weixinTmessageDao;

	@Override
	public void doAdd(WeixinTmessage weixinTmessage) {
		weixinTmessageDao.insert(weixinTmessage);
	}

	@Override
	public void doEdit(WeixinTmessage weixinTmessage) {
		weixinTmessageDao.update(weixinTmessage);
	}

	@Override
	public void doDelete(String id) {
		weixinTmessageDao.delete(id);
	}

	@Override
	public WeixinTmessage queryById(String id) {
		WeixinTmessage weixinTmessage  = weixinTmessageDao.get(id);
		return weixinTmessage;
	}

	@Override
	public PageList<WeixinTmessage> queryPageList(
		PageQuery<WeixinTmessage> pageQuery) {
		PageList<WeixinTmessage> result = new PageList<WeixinTmessage>();
		Integer itemCount = weixinTmessageDao.count(pageQuery);
		PageQueryWrapper<WeixinTmessage> wrapper = new PageQueryWrapper<WeixinTmessage>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinTmessage> list = weixinTmessageDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WeixinTmessage> queryByJwid(String jwid) {
		return weixinTmessageDao.queryByJwid(jwid);
	}

	@Override
	public WeixinTmessage queryByTemplateId(String templateId) {
		return weixinTmessageDao.queryByTemplateId(templateId);
	}
	
	/**
	 * 调用发送模板消息接口
	 * @param request
	 * @param tmessageSendVO
	 * @return
	 */
	public JSONObject sendTemplateMsg(TmessageSendVO tmessageSendVO) {
		JSONObject result = null;
		if(oConvertUtils.isEmpty(tmessageSendVO.getTouser())) {
			log.info("发送模板消息---接收者openid为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getTemplateId())) {
			log.info("发送模板消息---模板ID为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getJwid())) {
			log.info("发送模板消息---JWID为空");
			return result;
		}
		if(oConvertUtils.isEmpty(tmessageSendVO.getData())) {
			log.info("发送模板消息---模板数据为空");
			return result;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		//接收者openid
		param.put("touser", tmessageSendVO.getTouser());
		//模板ID
		param.put("template_id", tmessageSendVO.getTemplateId());
		//模板数据
		JSONObject dataJson = JSONObject.fromObject(tmessageSendVO.getData());
		param.put("data",(Map) dataJson);
		//模板跳转链接
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getUrl())) {
			param.put("url", tmessageSendVO.getUrl());
		}
		//跳小程序所需数据
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getMiniProgram())) {
			param.put("miniprogram", tmessageSendVO.getMiniProgram());
		}
		//所需跳转到的小程序appid
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getAppId())) {
			param.put("appid", tmessageSendVO.getAppId());
		}
		//所需跳转到小程序的具体页面路径
		if(oConvertUtils.isNotEmpty(tmessageSendVO.getPagePath())) {
			param.put("pagepath", tmessageSendVO.getPagePath());
		}
		String accessToken = WeiXinHttpUtil.getRedisWeixinToken(tmessageSendVO.getJwid());
		if (accessToken != null) {
			String requestUrl = SEND_TEMPLATE_MSG_URL;
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
			JSONObject obj = JSONObject.fromObject(param);
			log.info("发送模板消息方法执行前json参数 :{obj :" + obj.toString() + "}");
			result = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			log.info("发送模板消息方法执行后json参数 :{result :" + result.toString() + "}");
		}
		return result;
	}
	
}
