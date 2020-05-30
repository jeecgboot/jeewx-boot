package com.jeecg.p3.cms.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecg.p3.cms.service.CmsSiteService;

/**
 * 描述：CMS
 * 
 * @author zhoujf
 * @version:1.0
 */
@Controller
@RequestMapping("/cms")
@SkipAuth(auth=SkipPerm.SKIP_SIGN)
public class CmsController extends BaseController {
	
	@Autowired
	private CmsSiteService cmsSiteService;
	
	/**
	 * 跳转页面
	 * @return
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/{page}", method = { RequestMethod.GET, RequestMethod.POST })
	public void page(@PathVariable("page") String page,HttpServletResponse response, HttpServletRequest request) throws Exception {
		try {
			VelocityContext velocityContext = new VelocityContext();
			//参数透传处理
			commonParam(request,velocityContext);
			//根据actId获取模板path
			String templatePath = cmsSiteService.getSiteTemplate(request.getParameter("mainId"));
			String viewName = "cms/vm/" + templatePath + "/html/"+page+".vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//参数透传处理
	@SuppressWarnings("unchecked")
	private void commonParam(HttpServletRequest request,VelocityContext velocityContext){
		Map<String,String[]> paramMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry:paramMap.entrySet()){   
			 String[] value = entry.getValue();
			 String valueStr = "";
			 if(value!=null&&value.length>0){
				 valueStr = value[0];
			 }
		     velocityContext.put(entry.getKey(), valueStr);
		}   
	}
}
