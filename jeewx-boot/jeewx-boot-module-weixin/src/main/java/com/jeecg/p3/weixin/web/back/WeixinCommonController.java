package com.jeecg.p3.weixin.web.back;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinNewsitem;
import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
import com.jeecg.p3.weixin.entity.WeixinTexttemplate;
import com.jeecg.p3.weixin.enums.WeixinMsgTypeEnum;
import com.jeecg.p3.weixin.service.WeixinNewsitemService;
import com.jeecg.p3.weixin.service.WeixinNewstemplateService;
import com.jeecg.p3.weixin.service.WeixinTexttemplateService;

@Controller
@RequestMapping("/weixin/back/weixinCommon")
public class WeixinCommonController {
	@Autowired
	private WeixinTexttemplateService weixinTexttemplateService;
	@Autowired
	private WeixinNewstemplateService weixinNewstemplateService;
	@Autowired
	private WeixinNewsitemService weixinNewsitemService;
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
	  
	/**
	 * @author zhangweijian
	 * @功能：获取微信素材
	 * @param msgType
	 * @return
	 */
	@RequestMapping(value="getsucaiList",method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getsucaiList(@RequestParam String msgType,HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			String jwid =  request.getSession().getAttribute("jwid").toString();
			try {
				//获取文本素材
				if("text".equals(msgType)){
					List<WeixinTexttemplate> templates=weixinTexttemplateService.getAllTemplate(jwid);
					j.setObj(templates);
					j.setSuccess(true);
				}
				//获取图文素材
				if("news".equals(msgType)){
					//update-begin--Author:zhangweijian  Date: 20180820 for：添加一个上传状态字段
					List<WeixinNewstemplate> templates=weixinNewstemplateService.getAllItems(jwid,"");
					//update-end--Author:zhangweijian  Date: 20180820 for：添加一个上传状态字段
					j.setObj(templates);
					j.setSuccess(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return j;
	}
	
	
	/**
	 * 根据消息类型返回预览内容
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "showMoalMessage", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson showMoalMessage(HttpServletResponse response, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String msgType = request.getParameter("msgType");
		String templateId = request.getParameter("templateId");
		try {
			if(oConvertUtils.isNotEmpty(msgType) && oConvertUtils.isNotEmpty(templateId)) {
				//判断消息类型
				if(msgType.equals(WeixinMsgTypeEnum.wx_msg_type_text.getCode())) {
					WeixinTexttemplate weixinTextTemplate =  weixinTexttemplateService.queryById(templateId);
					j.setObj(weixinTextTemplate.getTemplateContent());
				} else {
					WeixinNewstemplate weixinNewsTemplate = weixinNewstemplateService.queryById(templateId);
					j.setObj(weixinNewsTemplate.getId());
				}
				j.setSuccess(true);
			} else {
				j.setMsg("消息类型或模板ID为空,请验证数据是否正确");
				j.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("获取预览内容失败");
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * @功能：图文素材编辑
	 * @param newstemplateId
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "goMessage", method = RequestMethod.GET)
	public void goMessage(@RequestParam(required = true, value = "newstemplateId") String newstemplateId,
			HttpServletResponse response, HttpServletRequest request)	throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		// 获取当前模板的素材
		List<WeixinNewsitem> newsItems = weixinNewsitemService.queryByNewstemplateId(newstemplateId);
		if (newsItems.size() > 0) {
			velocityContext.put("newsItem", newsItems.get(0));
			if (newsItems.size() > 1) {
				ArrayList list = new ArrayList(newsItems);
				list.remove(0);
				velocityContext.put("newsItems", list);
			}
		}
		// 获取模板信息
		WeixinNewstemplate wxNewstemplate = weixinNewstemplateService.queryById(newstemplateId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		if (wxNewstemplate.getCreateTime() != null) {
			velocityContext.put("addtime",sdf.format(wxNewstemplate.getCreateTime()));
		}
		String viewName = "weixin/back/weixinNewsitemsPreview.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}
	
	/**
	 * 转向信息页面（此方法废弃，移动到WeixinNewsController）
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Deprecated
	@RequestMapping(value = "goContent", method = {RequestMethod.GET,RequestMethod.POST})
	public void goContent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String id = request.getParameter("id");
		WeixinNewsitem newsItem = this.weixinNewsitemService.queryById(id);
		WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.queryById(newsItem.getNewstemplateId());
		velocityContext.put("newsItem", newsItem);
		String jwid = weixinNewstemplate.getJwid();
		velocityContext.put("jwid",jwid);
		MyJwWebJwid myJwWeb = myJwWebJwidService.queryByJwid(jwid);
		velocityContext.put("myJwWeb", myJwWeb);
		//设置分享后用户点击的url
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		String url = basePath + "/weixin/back/weixinCommon/goContent.do?id="+id+"&jwid="+jwid;
		velocityContext.put("url", url);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = sdf.format(newsItem.getCreateTime());
		velocityContext.put("createTime", createTime);
		//图文分享
		velocityContext.put("domain", basePath);
		velocityContext.put("appid", myJwWeb.getWeixinAppId());
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
		velocityContext.put("signature",WeiXinHttpUtil.getRedisSignature(request, jwid));
		String viewName = "weixin/back/newsContent.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	//update-begin--Author:zhangweijian  Date: 20180928 for：验证用户是否有权限操作此公众号
	/**
	 * 验证用户是否有权限操作此公众号
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="checkPermission",method = {RequestMethod.GET,RequestMethod.POST})
	public AjaxJson checkPermission(HttpServletRequest request, HttpServletResponse response){
		AjaxJson j=new AjaxJson();
		try {
			String systemUserid = request.getSession().getAttribute("system_userid").toString();
		 	String jwid =  request.getSession().getAttribute("jwid").toString();
		 	//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		 	MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
		 	//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		 	if(jw==null){
		 		j.setSuccess(false);
		 	}
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	//update-end--Author:zhangweijian  Date: 20180928 for：验证用户是否有权限操作此公众号
}
