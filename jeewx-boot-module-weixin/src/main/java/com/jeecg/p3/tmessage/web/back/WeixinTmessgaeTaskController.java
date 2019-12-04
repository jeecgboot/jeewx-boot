package com.jeecg.p3.tmessage.web.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.core.exception.WexinReqException;
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

import com.jeecg.p3.tmessage.entity.WeixinTmessage;
import com.jeecg.p3.tmessage.entity.WeixinTmessageSendLog;
import com.jeecg.p3.tmessage.entity.WeixinTmessgaeTask;
import com.jeecg.p3.tmessage.service.WeixinTmessageSendLogService;
import com.jeecg.p3.tmessage.service.WeixinTmessageService;
import com.jeecg.p3.tmessage.service.WeixinTmessgaeTaskService;
import com.jeecg.p3.tmessage.vo.TmessageSendVO;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinTagService;

import net.sf.json.JSONObject;

 /**
 * 描述：</b>发送模板消息表<br>
 * @author LeeShaoQing
 * @since：2018年11月21日 18时23分28秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/tmessage/back/weixinTmessgaeTask")
public class WeixinTmessgaeTaskController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinTmessgaeTaskController.class);
  @Autowired
  private WeixinTmessgaeTaskService weixinTmessgaeTaskService;
  @Autowired
  private WeixinTmessageService weixinTmessageService;
  @Autowired
  private WeixinGzuserService weixinGzuserService;
  @Autowired
  private WeixinTmessageSendLogService weixinTmessageSendLogService;
  @Autowired
  private WeixinTagService weixinTagService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinTmessgaeTask query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		String jwid = request.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
	 	PageQuery<WeixinTmessgaeTask> pageQuery = new PageQuery<WeixinTmessgaeTask>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinTmessgaeTask",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinTmessgaeTaskService.queryPageList(pageQuery)));
		List<WeixinTmessage> tmessages = weixinTmessageService.queryByJwid(jwid);
		velocityContext.put("tmessages", tmessages);
		List<WeixinTag> weixinTags = weixinTagService.getAllTags(jwid);
		 velocityContext.put("weixinTags", weixinTags);
		String viewName = "tmessage/back/weixinTmessgaeTask-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinTmessgaeTaskDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "tmessage/back/weixinTmessgaeTask-detail.vm";
		WeixinTmessgaeTask weixinTmessgaeTask = weixinTmessgaeTaskService.queryById(id);
		velocityContext.put("weixinTmessgaeTask",weixinTmessgaeTask);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String jwid = request.getSession().getAttribute("jwid").toString();
	 List<WeixinTmessage> tmessages = weixinTmessageService.queryByJwid(jwid);
	 velocityContext.put("tmessages", tmessages);
	 List<WeixinTag> weixinTags = weixinTagService.getAllTags(jwid);
	 velocityContext.put("weixinTags", weixinTags);
	 String viewName = "tmessage/back/weixinTmessgaeTask-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinTmessgaeTask weixinTmessgaeTask){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessgaeTask.setTaskSendStatus("0");
		weixinTmessgaeTaskService.doAdd(weixinTmessgaeTask);
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
		 WeixinTmessgaeTask weixinTmessgaeTask = weixinTmessgaeTaskService.queryById(id);
		 velocityContext.put("weixinTmessgaeTask",weixinTmessgaeTask);
		 String jwid = request.getSession().getAttribute("jwid").toString();
		 List<WeixinTmessage> tmessages = weixinTmessageService.queryByJwid(jwid);
		 velocityContext.put("tmessages", tmessages);
		 List<WeixinTag> weixinTags = weixinTagService.getAllTags(jwid);
		 velocityContext.put("weixinTags", weixinTags);
		 String viewName = "tmessage/back/weixinTmessgaeTask-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinTmessgaeTask weixinTmessgaeTask){
	AjaxJson j = new AjaxJson();
	try {
		weixinTmessgaeTaskService.doEdit(weixinTmessgaeTask);
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
			weixinTmessgaeTaskService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * @功能：打标签获取用户跳转的页面
 */
@RequestMapping(value="getUser",method = {RequestMethod.GET,RequestMethod.POST})
public void getUser(@ModelAttribute WeixinGzuser query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinGzuser> pageQuery = new PageQuery<WeixinGzuser>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	//获取jwid
		String jwid=request.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinGzuser",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGzuserService.queryPageList(pageQuery)));
		String viewName = "tmessage/back/weixinGzuser.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 发送模板消息
 * @param industryTemplateMessageSend
 * @return
 * @throws WexinReqException
 */
@RequestMapping(value="sendTemplateMsg", method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson sendTemplateMsg(final HttpServletRequest request) throws WexinReqException {
	AjaxJson j = new AjaxJson();
	String msg = "";
	try {
		final String taskId = request.getParameter("taskId");
		final WeixinTmessgaeTask tmessgaeTask = weixinTmessgaeTaskService.queryById(taskId);
		List<String> openIds = new ArrayList<String>();
		if("0".equals(tmessgaeTask.getSendType())) {
			//标签模式
			List<WeixinGzuser> weixinGzusers = weixinGzuserService.queryVagurByTagId(tmessgaeTask.getTagId());
			if(weixinGzusers != null && weixinGzusers.size() > 0) {
				for (int i = 0; i < weixinGzusers.size(); i++) {
					WeixinGzuser gzuser = weixinGzusers.get(i);
					openIds.add(gzuser.getOpenid());
				}
			}
		} else {
			//列表模式
			String[] openId = tmessgaeTask.getOpenid().split(",");
			openIds = Arrays.asList(openId);
		}
		final String[] openIds2 = openIds.toArray(new String[openIds.size()]);
		if(openIds2.length > 0) {
			final String jwid =  request.getSession().getAttribute("jwid").toString();
			Runnable run = new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < openIds2.length; i++) {
						TmessageSendVO tmessageSendVO = new TmessageSendVO();
						tmessageSendVO.setTouser(openIds2[i]);//OPENID
						tmessageSendVO.setTemplateId(tmessgaeTask.getTemplateId());//模板ID
						tmessageSendVO.setData(tmessgaeTask.getDataJson());//JSON数据
						tmessageSendVO.setUrl(tmessgaeTask.getUrl());//跳转链接
						tmessageSendVO.setMiniProgram(tmessgaeTask.getPagepath());//小程序跳转链接
						tmessageSendVO.setAppId(tmessgaeTask.getMiniAppid());//小程序APPID
						tmessageSendVO.setJwid(jwid);//JWID
						JSONObject result = weixinTmessageService.sendTemplateMsg(tmessageSendVO);
						//调用接口返回结果，保存消息日志表中
						WeixinTmessageSendLog tmessageSendLog = new WeixinTmessageSendLog();
						tmessageSendLog.setOpenid(openIds2[i]);
						tmessageSendLog.setTaskId(taskId);
						tmessageSendLog.setCreateDate(new Date());
						tmessageSendLog.setDataJson(tmessgaeTask.getDataJson());
						String errcode = result.getString("errcode");
						if ("0".equals(errcode)) {
							tmessageSendLog.setMsgId(result.getString("msgid"));
							tmessageSendLog.setStatus("0");
							tmessgaeTask.setTaskSendStatus("1");//已发送
						} else {
							tmessageSendLog.setStatus("1");
							tmessgaeTask.setTaskSendStatus("2");//发送失败
						}
						weixinTmessageSendLogService.doAdd(tmessageSendLog);
						//更新发送状态
						weixinTmessgaeTaskService.doEdit(tmessgaeTask);
					}
				}
			};
			new Thread(run).start();
			WeixinTmessgaeTask task = tmessgaeTask; 
			task.setTaskSendStatus("3");//发送中
			//更新发送状态
			weixinTmessgaeTaskService.doEdit(task);
			msg = "消息发送已启动，请稍后刷新";
		}
		j.setMsg(msg);
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("发送模板消息失败");
	}
	return j;
}

/**
 * 发送模板消息获取模板内容解析返回前台
 * @param templateId
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="getTemplateInfo", method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson getTemplateInfo(String templateId, HttpServletRequest request,HttpServletResponse response) {
	AjaxJson j = new AjaxJson();
	try {
		WeixinTmessage tmessage = weixinTmessageService.queryByTemplateId(templateId);
		String content = tmessage.getContent();
		if(content.indexOf(".DATA") > 0) {
			List<String> list = new ArrayList<String>();
			//循环所有的.DATA
			while(content.indexOf(".DATA") > 0) {
				//添加截取的key到list中
				list.add(content.substring(content.indexOf("{{")+2, content.indexOf(".DATA")));
				//截取content去掉已经添加到list中的key
				content = content.substring(content.indexOf(".DATA")+5, content.length());
			}
			j.setObj(list);
			j.setSuccess(true);
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("获取模板消息异常");
	}
	return j;
}

/**
 * 获取dataJson数据
 * @param taskId
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="getDataJson", method = {RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson getDataJson(String taskId, HttpServletRequest request,HttpServletResponse response) {
	AjaxJson j = new AjaxJson();
	try {
		WeixinTmessgaeTask weixinTmessgaeTask = weixinTmessgaeTaskService.queryById(taskId);
		j.setObj(weixinTmessgaeTask.getDataJson());
		j.setSuccess(true);
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("获取模板消息异常");
	}
	return j;
}


}

