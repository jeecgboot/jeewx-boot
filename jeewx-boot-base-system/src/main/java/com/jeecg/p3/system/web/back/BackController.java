package com.jeecg.p3.system.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 /**
 * 描述：</b>BackController<br>系统欢迎页
 * @author Alex
 * @since：2015年12月23日 12时04分42秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back")
public class BackController extends BaseController{

	/**
	 * 跳转到欢迎页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/home",method ={RequestMethod.GET, RequestMethod.POST})
	public void home(HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		 try {
			log.info(" back home");
			 VelocityContext velocityContext = new VelocityContext();
			 String viewName = "system/back/home.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

