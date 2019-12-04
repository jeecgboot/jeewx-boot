package com.jeecg.p3.message.web.back;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtil;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendDetail;
import com.jeecg.p3.message.entity.WeixinGroupMessageSendLog;
import com.jeecg.p3.message.model.Filter;
import com.jeecg.p3.message.model.Media;
import com.jeecg.p3.message.model.SendGroupMessageNews;
import com.jeecg.p3.message.model.SendGroupMessageText;
import com.jeecg.p3.message.model.Text;
import com.jeecg.p3.message.service.SendGroupMessageService;
import com.jeecg.p3.message.service.WeixinGroupMessageSendDetailService;
import com.jeecg.p3.message.service.WeixinGroupMessageSendLogService;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.service.WeixinNewstemplateService;
import com.jeecg.p3.weixin.service.WeixinTagService;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.util.WxErrCodeUtil;

import net.sf.json.JSONObject;

 /**
 * 描述：</b>群发消息日志表<br>
 * @author weijian.zhang
 * @since：2018年08月03日 14时43分17秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/message/back/sendGroupMessageController")
public class SendGroupMessageController extends BaseController{
	private final static Logger log = LoggerFactory.getLogger(SendGroupMessageController.class);
	
	//群发消息预览
	private static String message_preview_url="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	
	@Autowired
	private WeixinGroupMessageSendLogService weixinGroupMessageSendLogService;
	@Autowired
	private WeixinGroupMessageSendDetailService weixinGroupMessageSendDetailService;
	@Autowired
	private WeixinTagService weixinTagService;
	@Autowired
	private SendGroupMessageService sendGroupMessageService;
	@Autowired
	private WeixinNewstemplateService weixinNewstemplateService;
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
  
	/**
	 * @功能：进入群发功能页面
	 * @return
	 */
	@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WeixinGroupMessageSendLog query,HttpServletResponse response,HttpServletRequest request) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
 		String viewName = "message/back/groupMessageSend.vm";
 		String jwid=request.getSession().getAttribute("jwid").toString();
 		//获取当前公众号所有标签
 		List<WeixinTag> tagList=weixinTagService.getAllTags(jwid);
 		velocityContext.put("tagList",tagList);
 		ViewVelocity.view(request,response,viewName,velocityContext);
	}
 
	/**
	 * @功能：群发消息功能
	 * @param weixinGroupMessageSendLog
	 * @return
	 */
	@RequestMapping(value="sendGroupMessage",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson sendGroupMessage(WeixinGroupMessageSendLog weixinGroupMessageSendLog,HttpServletRequest request){
  		AjaxJson j = new AjaxJson();
  		try {
  	 		String jwid=request.getSession().getAttribute("jwid").toString();
  	 		//update-begin--Author:zhangweijian Date:20181015 for：传入jwid名称
  	 		weixinGroupMessageSendLog.setSendJwid(jwid);
  	 		MyJwWebJwid jwWebJwid = myJwWebJwidService.queryByJwid(jwid);
  	 		weixinGroupMessageSendLog.setSendJwidName(jwWebJwid.getName());
  	 		//update-end--Author:zhangweijian Date:20181015 for：传入jwid名称
  			//默认待审核状态：'0'待审核,'1'审核通过,'2'审核未通过
  	 		weixinGroupMessageSendLog.setAuditStatus("0");
  			weixinGroupMessageSendLog.setCreateTime(new Date());
  			weixinGroupMessageSendLogService.doAdd(weixinGroupMessageSendLog);
  			j.setSuccess(true);
  			j.setMsg("已成功提交群发申请，请等待管理员审核！");
  		} catch (Exception e) {
  			j.setMsg("群发申请失败！");
  			j.setSuccess(false);
  		}
  		return j;
	}
  
	/**
	 * @功能：审核群发消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="doAuditGroupMessage",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAuditGroupMessage(WeixinGroupMessageSendLog groupMessage,HttpServletRequest request){
  		final AjaxJson j = new AjaxJson();
  		try {
  			String id=groupMessage.getId();
  			String auditStatus=groupMessage.getAuditStatus();
  			final WeixinGroupMessageSendLog groupMessageSendLog=weixinGroupMessageSendLogService.queryById(id);
  			//update-begin--Author:zhangweijian Date:20181015 for：审核信息完善
  			String createBy = request.getSession().getAttribute("system_userid").toString();
  			groupMessageSendLog.setAuditName(createBy);
  			groupMessageSendLog.setAuditDate(new Date());
  			groupMessageSendLog.setAuditRemark("");
  			groupMessageSendLog.setAuditStatus(auditStatus);
  			//groupMessageSendLog.setSendType(groupMessage.getSendType());  暂时未用到：发送方式
  			groupMessageSendLog.setSendIgnoreReprint(groupMessage.getSendIgnoreReprint());
  			//‘1’：表示通过，‘2’：表示未通过
  			//update-end--Author:zhangweijian Date:20181015 for：审核信息完善
  		    //update-begin--Author:zhangweijian Date:20181016 for：审核刷新
  			weixinGroupMessageSendLogService.doEdit(groupMessageSendLog);
  		    //update-end--Author:zhangweijian Date:20181016 for：审核刷新
  			if(auditStatus.equals("2")){
  				j.setMsg("审核未通过，已取消发送此信息！");
  				j.setObj(true);
  				return j;
  			}else{
				//审核通过开启线程，群发消息
				Thread t = new Thread(new Runnable(){  
					public void run(){  
						try {
							JSONObject json=doSendGroupMessage(groupMessageSendLog);
							if(json.getString("errmsg").equals("0")){
								j.setMsg("发送成功！");
							}else{
								String errcode=json.getString("errcode");
								//author:sunkai--date:2018-09-26--for:错误返回码信息转义
								String msg = WxErrCodeUtil.testErrCode(errcode);
								j.setMsg("发送失败！"+msg);
								//author:sunkai--date:2018-09-26--for:错误返回码信息转义
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}});
				t.start();
			}
  		} catch (Exception e) {
  			j.setSuccess(false);
  		}
  		return j;
	}
  
	/**
	 * @功能：群发消息
	 * @param groupMessage
	 * @return 
	 */
	private JSONObject doSendGroupMessage(WeixinGroupMessageSendLog groupMessage) {
		//群发参数数据
		String jwid=groupMessage.getJwid();
		String msgtype =groupMessage.getMsgType();
		String groupId=groupMessage.getGroupId();
		String isToAll=groupMessage.getIsToAll();
		String tagId=groupMessage.getTagId();
		String templateId=groupMessage.getTemplateId();
		JSONObject messageJson = null;
		//群发消息调用微信接口
		JSONObject jsonObj=null;
		if(StringUtil.isNotEmpty(groupMessage.getJwid())){
			//设置图文消息的接收者
			Filter filter = new Filter();
			if("false".equals(isToAll)){
				filter.setIs_to_all(false);
				filter.setGroup_id(groupId);
			}else if("tag".equals(isToAll)){
				filter.setIs_to_all(false);
				filter.setTag_id(tagId);
			}else{
				filter.setIs_to_all(true);
			}
			//获取媒体id
			String newparams = null;
			WeixinNewstemplate newstemplate=weixinNewstemplateService.queryById(templateId);
			if("text".equals(msgtype)){
				newparams=groupMessage.getParam();
			}else{
				newparams = newstemplate.getMediaId();
			}
			//群发图文增加原创校验
			if(StringUtil.isNotEmpty(newparams)){
				if("mpnews".equals(msgtype)){
					SendGroupMessageNews messageNews=new SendGroupMessageNews();
					messageNews.setFilter(filter);
					Media media = new Media();
					media.setMedia_id(newparams);
					messageNews.setMpnews(media);
					messageNews.setMsgtype("mpnews");
					messageNews.setSend_ignore_reprint(Integer.parseInt(groupMessage.getSendIgnoreReprint()));
					messageJson = JSONObject.fromObject(messageNews);
				}else if("text".equals(msgtype)){
					Text text = new Text();
					text.setContent(newparams);
					SendGroupMessageText messageText = new SendGroupMessageText();
					messageText.setMsgtype("text");
					messageText.setText(text);
					messageText.setFilter(filter);
					messageText.setSend_ignore_reprint(Integer.parseInt(groupMessage.getSendIgnoreReprint()));
					messageJson = JSONObject.fromObject(messageText);
				}else if("image".equals(msgtype)){
					
				}else if("voice".equals(msgtype)){
					
				}else if("video".equals(msgtype)){
					
				}
			}
			try {
				if("false".equals(isToAll)){
					Object object = messageJson.get("filter");
					JSONObject obj = JSONObject.fromObject(object);
					obj.remove("tag_id");
					messageJson.put("filter", obj);
				}else{
					Object object = messageJson.get("filter");
					JSONObject obj = JSONObject.fromObject(object);
					obj.remove("group_id");
					messageJson.put("filter", obj);
				}
				log.info("-----群发消息调用微信接口参数：------------------"+messageJson.toString());
				jsonObj = sendGroupMessageService.sendGroupMessage(messageJson,jwid);
				log.info("-----群发消息微信返回结果：------------------"+jsonObj);
			} catch (Exception e) {
				log.info("-----公众号【ID="+jwid+"】素材【ID="+templateId+"】群发API调用异常-----"+e.toString());
			}finally{
				//记录群发返回日志
				WeixinGroupMessageSendDetail sendDetail=new WeixinGroupMessageSendDetail();
				sendDetail.setSendLogId(groupMessage.getId());
				sendDetail.setSendJwid(jwid);
				sendDetail.setSendJwidName(groupMessage.getSendJwidName());
				sendDetail.setMsgType(msgtype);
				if(!"text".equals(msgtype)){
					sendDetail.setTemplateId(templateId);
					sendDetail.setTemplateName(newstemplate.getTemplateName());
				}
				sendDetail.setMediaId(newparams);
				sendDetail.setSendTime(new Date());
				if(jsonObj!=null){
					if(jsonObj.containsKey("errcode")&&jsonObj.getInt("errcode")==0){		
						sendDetail.setSendStatus("1");//发送成功
						sendDetail.setReturnErrcode(jsonObj.getString("errcode"));
						sendDetail.setReturnErrmsg(jsonObj.getString("errmsg"));
						sendDetail.setReturnMsgId(jsonObj.getString("msg_id"));
						sendDetail.setReturnMsgDataId(jsonObj.containsKey("msg_data_id")?jsonObj.getString("msg_data_id"):null);
						//修改发送状态
					}else{
						sendDetail.setSendStatus("0");//发送失败
						sendDetail.setReturnErrcode(jsonObj.getString("errcode"));
						sendDetail.setReturnErrmsg(jsonObj.getString("errmsg"));
					}
				}else{
					sendDetail.setSendStatus("0");//发送失败
					sendDetail.setReturnErrmsg("微信无响应");
				}
				weixinGroupMessageSendDetailService.doAdd(sendDetail);
			}
		}
		return jsonObj;
	}
	
	/**
	 * @功能：群发预览
	 * @param openid
	 * @param logid
	 * @return
	 */
	@RequestMapping(value="doPreview",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doPreview(@RequestParam String openid,@RequestParam String logid,HttpServletRequest request){
  		AjaxJson j = new AjaxJson();
  		try {
  			//获取群发消息信息
  			WeixinGroupMessageSendLog messageSendLog=weixinGroupMessageSendLogService.queryById(logid);
  			String msgType=messageSendLog.getMsgType();
  			String param=messageSendLog.getParam();
  			String name="";
  			if(messageSendLog!=null){
  				//判断发送的消息类型
  				if(messageSendLog.getMsgType().equals("mpnews")){
  					//获取媒体id
  					WeixinNewstemplate newstemplate=weixinNewstemplateService.queryById(messageSendLog.getTemplateId());
  					String media_id=newstemplate.getMediaId();
  					name="{\"touser\":\""+openid+"\",\"mpnews\":{\"media_id\":\""+media_id+"\"},\"msgtype\":\""+msgType+"\"}";
  				}
  				if(messageSendLog.getMsgType().equals("text")){
  					name="{\"touser\":\""+openid+"\",\"text\":{\"content\":\""+param+"\"},\"msgtype\":\""+msgType+"\"}";
  				}
  				//TODO 还有voice，iamge，myvedio等
  			}
  			//获取accessToken
  			String jwid=request.getSession().getAttribute("jwid").toString();
  			String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
  			//调用创建标签接口
  			String requestUrl=message_preview_url.replace("ACCESS_TOKEN", accessToken);
  			JSONObject jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
  			//接口返回信息
  			if(jsonObj.getString("errcode").equals("0")){
  				j.setMsg("发送成功！");
  			}else{
  				String errcode=jsonObj.getString("errcode");
  				//author:sunkai--date:2018-09-26--for:错误返回码信息转义
  				String msg = WxErrCodeUtil.testErrCode(errcode);
				j.setMsg("发送失败！"+msg);
				//author:sunkai--date:2018-09-26--for:错误返回码信息转义
  			}
  		} catch (Exception e) {
  			j.setSuccess(false);
  		}
  		return j;
	}

}

