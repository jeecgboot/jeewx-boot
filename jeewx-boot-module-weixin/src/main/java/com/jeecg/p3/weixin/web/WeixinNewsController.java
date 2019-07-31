package com.jeecg.p3.weixin.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinNewsitem;
import com.jeecg.p3.weixin.entity.WeixinNewstemplate;
import com.jeecg.p3.weixin.service.WeixinNewsitemService;
import com.jeecg.p3.weixin.service.WeixinNewstemplateService;

 /**
 * 描述：微信文章访问
 * @author weijian.zhang
 * @since：2018年07月13日 12时46分36秒 星期五 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixinNewsController")
public class WeixinNewsController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinNewsController.class);
  @Autowired
  private WeixinNewsitemService weixinNewsitemService;
  @Autowired
  private WeixinNewstemplateService weixinNewstemplateService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  
  /**
	 * 转向微信文章信息页面
	 * @param request
	 * @return
	 * @throws Exception 
	 */
  	@SkipAuth(auth=SkipPerm.SKIP_SIGN)
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
		StringBuffer requestURL = request.getRequestURL();  
		StringBuffer tempContextUrl = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).append("");
		String basePath = tempContextUrl.toString()+request.getContextPath();
		String url = basePath + "/weixinNewsController/goContent.do?id="+id+"&jwid="+jwid;
		velocityContext.put("url", url);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = sdf.format(newsItem.getCreateTime());
		velocityContext.put("createTime", createTime);
		//图文分享.
		velocityContext.put("domain", basePath);
		velocityContext.put("appid", myJwWeb.getWeixinAppId());
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
		velocityContext.put("signature",WeiXinHttpUtil.getRedisSignature(request, jwid));
		String viewName = "weixin/back/newsContent.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
}

