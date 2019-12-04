package com.jeecg.p3.tmessage.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.tmessage.entity.WeixinTmessage;
import com.jeecg.p3.tmessage.vo.TmessageSendVO;

import net.sf.json.JSONObject;

/**
 * 描述：</b>消息模板表<br>
 * @author：LeeShaoQing
 * @since：2018年11月21日 18时21分04秒 星期三 
 * @version:1.0
 */
public interface WeixinTmessageService {
	
	
	public void doAdd(WeixinTmessage weixinTmessage);
	
	public void doEdit(WeixinTmessage weixinTmessage);
	
	public void doDelete(String id);
	
	public WeixinTmessage queryById(String id);
	
	public PageList<WeixinTmessage> queryPageList(PageQuery<WeixinTmessage> pageQuery);
	
	/**
	 * 根据jwid查询模板
	 * @param jwid
	 * @return
	 */
	public List<WeixinTmessage> queryByJwid(String jwid);
	
	/**
	 * 根据templateId查询模板信息
	 * @param templateId
	 * @return
	 */
	public WeixinTmessage queryByTemplateId(String templateId);
	
	/**
	 * 调用发送模板消息接口
	 * @param jwid
	 * @return
	 */
	public JSONObject sendTemplateMsg(TmessageSendVO tmessageSendVO);
}

