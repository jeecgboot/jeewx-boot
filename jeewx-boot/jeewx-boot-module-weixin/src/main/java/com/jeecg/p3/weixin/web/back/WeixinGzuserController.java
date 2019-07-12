package com.jeecg.p3.weixin.web.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinTagService;
import com.jeecg.p3.weixin.task.GzUserInfoTimer;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.util.WxErrCodeUtil;

import net.sf.json.JSONObject;

 /**
 * 描述：</b>粉丝表<br>
 * @author weijian.zhang
 * @since：2018年07月26日 15时38分40秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinGzuser")
public class WeixinGzuserController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinGzuserController.class);
  @Autowired
  private WeixinGzuserService weixinGzuserService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  @Autowired
  private GzUserInfoTimer gzUserInfoTimer;
  @Autowired
  private WeixinTagService weixinTagService;
  //获取用户列表
	public final static String user_List_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinGzuser query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinGzuser> pageQuery = new PageQuery<WeixinGzuser>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	//update-begin--Author:zhangweijian  Date: 20180806 for：添加jwid查询条件
	 	//获取jwid
		String jwid=request.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
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
		//update-begin--Author:zhangweijian  Date: 20180806 for：添加jwid查询条件
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		//update-begin--Author:zhangweijian  Date: 20180820 for：获取当前公众号的所有标签
		//获取当前公众号的所有标签
		List<WeixinTag> Tags=weixinTagService.getAllTags(jwid);
		velocityContext.put("Tags",Tags);
		//update-end--Author:zhangweijian  Date: 20180820 for：获取当前公众号的所有标签
		velocityContext.put("weixinGzuser",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGzuserService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinGzuser-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * @功能：粉丝同步
 */
@ResponseBody
@RequestMapping(value="syncFans",method = {RequestMethod.GET,RequestMethod.POST})
public AjaxJson syncFans(HttpServletResponse response,HttpServletRequest request) throws Exception{
	AjaxJson j=new AjaxJson();
	try {
		//update-begin-zhangweijian-----Date:20180807---for:线程移到controller中
		//获取jwid
		final String jwid=request.getSession().getAttribute("jwid").toString();
		String message="";
		//获取token
		int total=0;
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		//调用一次测试微信接口以验证服务接口是否正常，并返回粉丝总数
		if(StringUtils.isNotEmpty(accessToken)){
			String requestUrl=user_List_url.replace("NEXT_OPENID", "").replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObj=WeixinUtil.httpRequest(requestUrl, "GET", "");
			if(jsonObj==null){
				message= "微信服务器访问异常，请稍候重试。";
				//update-begin-zhangweijian-----Date:20180809---for:添加返回内容
				j.setMsg(message);
				//update-end-zhangweijian-----Date:20180809---for:添加返回内容
				return j;
			}
			if(!jsonObj.containsKey("errmsg")){
		    	 total = jsonObj.getInt("total");
		    }else{
		    	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
		    	String errcode=jsonObj.getString("errcode");
		    	String msg = WxErrCodeUtil.testErrCode(errcode);
				j.setMsg("同步粉丝列表失败! "+msg);
		    	//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
		    	return j;
		    }
		}
		message="同步粉丝任务已启动,请稍候刷新。关注用户总数："+total;
		//开启线程，同步粉丝数据
		Thread t=new Thread(new Runnable() {
			@Override
			public void run() {
				weixinGzuserService.getFansListTask("0",jwid);
				MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByJwid(jwid);
				gzUserInfoTimer.batchInitAccountFensi(myJwWebJwid);
			}
		});
		t.start();
		//update-end-zhangweijian-----Date:20180807---for:线程移到controller中
		j.setMsg(message);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return j;
}
	//update-begin--Author:zhangweijian  Date: 20180820 for：增加不同页面模态框获取用户信息的跳转
	/**
	 * @功能：打标签获取用户跳转的页面
	 */
	@RequestMapping(value="getUser",method = {RequestMethod.GET,RequestMethod.POST})
	public void getUser(@ModelAttribute WeixinGzuser query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,@RequestParam String tagid) throws Exception{
		 	PageQuery<WeixinGzuser> pageQuery = new PageQuery<WeixinGzuser>();
		 	pageQuery.setPageNo(pageNo);
		 	pageQuery.setPageSize(pageSize);
		 	//update-begin--Author:zhangweijian  Date: 20180806 for：添加jwid查询条件
		 	//获取jwid
			String jwid=request.getSession().getAttribute("jwid").toString();
			query.setJwid(jwid);
			//update-begin--Author:zhangweijian  Date: 20180806 for：添加jwid查询条件
		 	VelocityContext velocityContext = new VelocityContext();
			pageQuery.setQuery(query);
			velocityContext.put("weixinGzuser",query);
			velocityContext.put("tagid",tagid);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinGzuserService.queryPageList(pageQuery)));
			String viewName = "weixin/back/weixinGzuser.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
	}
	//update-end--Author:zhangweijian  Date: 20180820 for：增加不同页面模态框获取用户信息的跳转
}

