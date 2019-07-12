package com.jeecg.p3.commonweixin.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

 /**
 * 描述：</b>BackController<br>系统欢迎页
 * @author Alex
 * @since：2015年12月23日 12时04分42秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping
public class MpVerifyController extends BaseController{

	/**
	 * 跳转到欢迎页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value= "/MP_verify_{code}.txt")
	public void mpVerify(@PathVariable("code") String code,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		 try {
			this.responseJson(response, code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

