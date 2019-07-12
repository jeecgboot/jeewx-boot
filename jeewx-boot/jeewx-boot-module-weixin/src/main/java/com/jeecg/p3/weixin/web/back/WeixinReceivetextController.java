package com.jeecg.p3.weixin.web.back;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinReceivetext;
import com.jeecg.p3.weixin.entity.WeixinReceptMsg;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinReceivetextService;
import com.jeecg.p3.weixin.service.WeixinReceptMsgService;

 /**
 * 描述：</b>消息存储<br>
 * @author LeeShaoQing
 * @since：2018年07月25日 16时02分13秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinReceivetext")
public class WeixinReceivetextController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinReceivetextController.class);
  @Autowired
  private WeixinReceivetextService weixinReceivetextService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  @Autowired
  private WeixinReceptMsgService weixinReceptMsgService;
  @Autowired
  private WeixinGzuserService weixinGzuserService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinReceivetext query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinReceivetext> pageQuery = new PageQuery<WeixinReceivetext>();
	 	String jwid =  request.getSession().getAttribute("jwid").toString();
	 	if(query.getQueryTime()==null){
	 		query.setQueryTime("twoHour");
	 	}
	 	query.setToUserName(jwid);
	 	//update-begin--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	//判断是否有权限
	 	String systemUserid = request.getSession().getAttribute("system_userid").toString();
	 	//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	 	MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
	 	//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
	 	if(jw==null){
	 		query.setJwid("-");
	 	}
	 	//update-end--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinReceivetext",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinReceivetextService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinReceivetext-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinReceivetextDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "weixin/back/weixinReceivetext-detail.vm";
		WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
		velocityContext.put("weixinReceivetext",weixinReceivetext);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "weixin/back/weixinReceivetext-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinReceivetext weixinReceivetext){
	AjaxJson j = new AjaxJson();
	try {
		weixinReceivetextService.doAdd(weixinReceivetext);
		j.setMsg("保存成功");
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
		 velocityContext.put("weixinReceivetext",weixinReceivetext);
		 String viewName = "weixin/back/weixinReceivetext-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinReceivetext weixinReceivetext){
	AjaxJson j = new AjaxJson();
	try {
		weixinReceivetextService.doEdit(weixinReceivetext);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			weixinReceivetextService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}
/**
 * @feature 显示聊天记录模态框
 * @param request
 * @param response
 * @throws Exception
 */
@RequestMapping(value="chatLog")
public void chatLog(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
	 	try {
	 		String systemUserid = request.getSession().getAttribute("system_userid").toString();
	 		WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("id",id);
			velocityContext.put("weixinReceivetext",weixinReceivetext);
			String jwid=request.getSession().getAttribute("jwid").toString();
			WeixinGzuser gzuserInfo=weixinGzuserService.queryByOpenId(weixinReceivetext.getFromUserName(),jwid);
			if(gzuserInfo !=null){
				if(gzuserInfo.getHeadimgurl() !=null && gzuserInfo.getHeadimgurl() !=""){
					velocityContext.put("headimgUrl",gzuserInfo.getHeadimgurl());
				}else{
					velocityContext.put("headimgUrl","");
				}
				if(gzuserInfo.getNickname() !=null && gzuserInfo.getNickname() !=""){
					velocityContext.put("nickname",gzuserInfo.getNicknameTxt());
				}else{
					velocityContext.put("nickname",weixinReceivetext.getFromUserName());
				}
			}else{
				velocityContext.put("headimgUrl","");
				velocityContext.put("nickname",weixinReceivetext.getFromUserName());
			}
			velocityContext.put("systemUserid",systemUserid);
			String viewName = "weixin/back/weixinChat-list.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
}

/**
 * 查询聊天记录
 * @return
 */
@RequestMapping(value="queryChatLog",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson queryChatLog(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
		List<Map<String,Object>> chats=weixinReceivetextService.queryAllChatLog(weixinReceivetext);
		//遍历查询结果，获取消息类型
		for(Map<String,Object> m:chats){
			String msgtype = (String) m.get("msgtype");
			String content = (String) m.get("content");
			if("image".equals(msgtype)){
				JSONObject json = (JSONObject) JSONObject.parse(content);
				m.put("content", json.getString("PicUrl"));
			}
		}
		j.setObj(chats);
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("获取消息失败");
	}
	return j;
}
/**
 *回复信息
 * @return
 */
@RequestMapping(value="sendAnswer",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson sendAnswer(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String systemUserid = request.getSession().getAttribute("system_userid").toString();
		String jwid=request.getSession().getAttribute("jwid").toString();
		String sendAnswer = request.getParameter("sendAnswer");
		if(StringUtils.isEmpty(sendAnswer)){
			j.setSuccess(false);
			j.setMsg("回复消息不能为空");
			return j;
		}
		String toUserName = request.getParameter("toUserName");
		WeixinReceptMsg weixinReceptMsg=new WeixinReceptMsg();
		weixinReceptMsg.setToUsername(toUserName);
		weixinReceptMsg.setFromUsername(systemUserid);
		weixinReceptMsg.setContent(sendAnswer);
		weixinReceptMsg.setMsgtype("text");
		weixinReceptMsg.setJwid(jwid);
		weixinReceptMsg.setCreateTime(new Date());
		weixinReceptMsgService.doAddAnswer(weixinReceptMsg);
		
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("回复消息失败");
	}
	return j;
}
/**
 *更新聊天信息
 * @return
 */
@RequestMapping(value="updateChatLog",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson updateChatLog(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String inRecentTime = request.getParameter("inRecentTime");
		WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
		weixinReceivetext.setInRecentTime(inRecentTime);
		List<Map<String,Object>> chats=weixinReceivetextService.queryAllChatLog(weixinReceivetext);
		//遍历查询结果，获取消息类型
		for(Map<String,Object> m:chats){
			String msgtype = (String) m.get("msgtype");
			String content = (String) m.get("content");
			if("image".equals(msgtype)){
				JSONObject json = (JSONObject) JSONObject.parse(content);
				m.put("content", json.getString("PicUrl"));
			}
		}
		j.setObj(chats);
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("获取消息失败");
	}
	return j;
}
/**
 * 查询聊天记录
 * @return
 */
@RequestMapping(value="queryMoreChatLog",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson queryMoreChatLog(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		String firstRecordTime = request.getParameter("firstRecordTime");
		WeixinReceivetext weixinReceivetext = weixinReceivetextService.queryById(id);
		List<Map<String,Object>> chats=weixinReceivetextService.queryMoreChatLog(weixinReceivetext,firstRecordTime);
		//遍历查询结果，获取消息类型
		for(Map<String,Object> m:chats){
			String msgtype = (String) m.get("msgtype");
			String content = (String) m.get("content");
			if("image".equals(msgtype)){
				JSONObject json = (JSONObject) JSONObject.parse(content);
				m.put("content", json.getString("PicUrl"));
			}
		}
		j.setObj(chats);
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("获取消息失败");
	}
	return j;
}
/**
 *重新回复信息
 * @return
 */
@RequestMapping(value="sendAnswerAgain",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson sendAnswerAgain(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		WeixinReceptMsg weixinReceptMsg = weixinReceptMsgService.queryById(id);
		String flag=weixinReceptMsgService.sendAnswerAgain(weixinReceptMsg);
		j.setObj(flag);
		j.setSuccess(true);
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("重新回复消息失败");
	}
	return j;
}
}

