package com.jeecg.p3.commonweixin.web.back;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;

 /**
 * 描述：首页
 * @version:1.0
 */
@Controller
@RequestMapping("/commonweixin/back/index")
public class WeixinIndexController extends BaseController{
	
    @Autowired
    private MyJwWebJwidService myJwWebJwidService;
	/**
	 * 跳转到欢迎页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public void index(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		 try {
			 VelocityContext velocityContext = new VelocityContext();
			 String viewName = "commonweixin/back/main/index.vm";
			 String jwid = request.getParameter("jwid");
			 if(oConvertUtils.isEmpty(jwid)){
				 //throw new BusinessException("商城信息异常");
			 }else{
				 MyJwWebJwid myJwWebJwid= myJwWebJwidService.queryByJwid(jwid);
				 if(myJwWebJwid==null){
					 //throw new BusinessException("商城信息异常");
				 }
				 String userid = (String) request.getSession().getAttribute("system_userid");
				 if(oConvertUtils.isEmpty(userid)){
					 String path = request.getContextPath();
					 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					 System.out.println("----basePath------"+basePath);
					 response.sendRedirect(basePath+"/system/login.do");
				 }
				 if(userid!=null && myJwWebJwid!=null && !userid.equals(myJwWebJwid.getCreateBy())){
					 //throw new BusinessException("商城信息异常");
				 }
				 velocityContext.put("jwid", jwid);
				 velocityContext.put("userid", userid);
				 velocityContext.put("myJwWebJwid", myJwWebJwid);
				 ViewVelocity.view(request,response,viewName,velocityContext);
				 return;
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(value="toHome",method = RequestMethod.GET)
	public void eshopMainDetail(@RequestParam(required = true, value = "jwid" ) String jwid,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "commonweixin/back/main/home.vm";
			Integer gzuserTotalcount =myJwWebJwidService.queryGzuserCount(jwid,"");
			velocityContext.put("gzuserTotalcount",gzuserTotalcount);//累计粉丝量
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1);
			Date yesterday = calendar.getTime();
			Integer yesterdayMsgCount =myJwWebJwidService.queryMsgCount(jwid,sdf1.format(yesterday));
			velocityContext.put("yesterdayMsgCount",yesterdayMsgCount);//消息发送量
			MyJwWebJwid myJwWebJwid= myJwWebJwidService.queryByJwid(jwid);
			if(myJwWebJwid==null){
				//throw new BusinessException("商城信息异常");
			}
			 velocityContext.put("jwid", jwid);
			 velocityContext.put("myJwWebJwid", myJwWebJwid);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * beatcheck
	 * @return
	 */
	@RequestMapping(value = "/beatCheck",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson beatCheck(HttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try {
			String jwid = request.getParameter("jwid");
			String sessionjwid =  (String) request.getSession().getAttribute("jwid");
			if(oConvertUtils.isEmpty(sessionjwid)){
				//session过期
				j.setSuccess(false);
				j.setObj("sessiontimeout");
			}else if(!sessionjwid.equals(jwid)){
				//商城公众号已切换
				j.setSuccess(false);
				j.setObj("eshopchanged");
			}
		} catch (Exception e) {
			j.setObj("error");
			j.setSuccess(false);
		}
		return j;
	}
	
}

