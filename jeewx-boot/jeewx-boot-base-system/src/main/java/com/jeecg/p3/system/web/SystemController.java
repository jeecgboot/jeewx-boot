package com.jeecg.p3.system.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.MD5Util;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.system.def.SystemProperties;
import com.jeecg.p3.system.entity.JwSystemLogoTitle;
import com.jeecg.p3.system.entity.JwSystemRegister;
import com.jeecg.p3.system.entity.JwSystemUser;
import com.jeecg.p3.system.entity.JwSystemUserJwid;
import com.jeecg.p3.system.service.JwSystemAuthService;
import com.jeecg.p3.system.service.JwSystemLogoTitleService;
import com.jeecg.p3.system.service.JwSystemRegisterService;
import com.jeecg.p3.system.service.JwSystemUserJwidService;
import com.jeecg.p3.system.service.JwSystemUserService;
import com.jeecg.p3.system.service.JwWebJwidService;
import com.jeecg.p3.system.util.Constants;
import com.jeecg.p3.system.util.SendMailUtil;
import com.jeecg.p3.system.vo.WeixinAccountDto;
import com.jeecg.p3.system.vo.LoginUser;
import com.jeecg.p3.system.vo.Menu;

/**
 * 后台系统接入认证
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	private final static Logger LOG = LoggerFactory.getLogger(SystemController.class);
	public final static String indexPath = "base/back/hplus/index.vm";
	private static String defaultJwid = SystemProperties.defaultJwid;

	@Autowired
	private JwWebJwidService jwidService;
	@Autowired
	private JwSystemAuthService jwSystemAuthService;
	@Autowired
	private JwSystemLogoTitleService jwSystemLogoTitleService;
	@Autowired
	private JwSystemUserService jwSystemUserService;
	@Autowired
	private JwSystemRegisterService jwSystemRegisterService;
	@Autowired
	private JwSystemUserJwidService jwSystemUserJwidService;

	/**
	 * 后台接入没有权限
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/noAuth", method = { RequestMethod.GET, RequestMethod.POST })
	public void noAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "base/back/common/error.vm";
		VelocityContext velocityContext = new VelocityContext();
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 调整到登录页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/toLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public void toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		LoginUser user = (LoginUser) request.getSession().getAttribute(Constants.OPERATE_WEB_LOGIN_USER);
		JwSystemLogoTitle logoTitle = jwSystemLogoTitleService.queryLogoTitle().get(0);
		velocityContext.put("logoTitle", logoTitle);
		// -----update--start--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
		String referer = request.getRequestURL().toString();
		if ((referer != null) && (referer.trim().startsWith("http://h5huodong.com"))) {
			response.sendRedirect("http://www.h5huodong.com" + request.getRequestURI());
			return;
		}
		// -----update--end--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
		// 验证用户信息
		if (user != null) {
			// 用户已登录
			velocityContext.put("jwidname", (String) request.getSession().getAttribute(Constants.SYSTEM_JWIDNAME));
			velocityContext.put("userid", user.getUserId());
			try {
				// 获取菜单
//				LinkedHashMap<Menu, ArrayList<Menu>> menuTree = jwSystemAuthService.getSubMenuTree(user.getUserId(),null);
				List<Menu> menuTree = jwSystemAuthService.getMenuTree(user.getUserId());
//				//检查用户是否欠费，进行欠费拦截，修改菜单
//				chargeAuthInterceptor(user,menuTree);
//				LOG.info("menuTree---->"+menuTree);
				velocityContext.put(Constants.OPERATE_WEB_MENU_TREE, menuTree);
				String viewName = indexPath;
				ViewVelocity.view(request, response, viewName, velocityContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		} else {
			String viewName = "base/back/common/login.vm";
			ViewVelocity.view(request, response, viewName, velocityContext);
		}
	}

	/**
	 * check用户登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUser", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson checkUser(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AjaxJson j = new AjaxJson();
		try {
			String randCode = request.getParameter("randCode");
			HttpSession session = request.getSession();
			Object yzm = session.getAttribute("randCode");
			if (StringUtils.isEmpty(randCode) || yzm == null || !randCode.equals(yzm.toString())) {
				LOG.info("验证码错误：" + randCode);
				j.setSuccess(false);
				j.setMsg("验证码错误,请重试！");
				return j;
			}
			// session.removeAttribute("randCode");暂时不移除，做二次校验
			// 验证用户是否存在
			LoginUser user = jwSystemUserService.queryUserByUserId(username);
			if (user == null) {
				j.setSuccess(false);
				j.setMsg("登录账号【" + username + "】，在系统中不存在！！");
				return j;
			}
			// 验证用户密码是否正确
			String passwordEncode = MD5Util.MD5Encode(password, "utf-8");
			if (passwordEncode != null && passwordEncode.equals(user.getPassword())
					&& Constants.USER_NORMAL_STATE.equals(user.getUserStat())) {
				LOG.info("登录验证成功：用户【" + username + "】权限验证通过");
				j.setSuccess(true);
				j.setMsg("登录验证成功");
				j.setObj("");
				return j;
			} else {
				LOG.info("登录账号【" + username + "】，密码错误！！");
				j.setMsg("登录账号【" + username + "】，密码错误！！");
				j.setSuccess(false);
				return j;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("登录验证失败：用户【" + username + "】" + e.getMessage());
			j.setSuccess(false);
			j.setMsg("登录验证失败");
		}
		return j;
	}

	/**
	 * 登录前验证并获取分配的微信公众号
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/preLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public void preLogin(String username, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "base/back/common/prelogin.vm";
		try {
			// 根据用户名查询系统用户
			LoginUser user = jwSystemUserService.queryUserByUserId(username);
			boolean isAuth = false;
			String passwordEncode = MD5Util.MD5Encode(password, "utf-8");
			// 验证用户信息
			if (user != null) {
				if (passwordEncode != null && passwordEncode.equals(user.getPassword())
						&& Constants.USER_NORMAL_STATE.equals(user.getUserStat())) {
					isAuth = true;
				} else {
					LOG.info("登录验证失败：用户【" + username + "】登录信息验证不通过");
				}
			}
			List<WeixinAccountDto> jwids = new ArrayList<WeixinAccountDto>();
			if (isAuth) {
				// 验证通过,根据userid查询捷微列表
				jwids = jwidService.queryJwWebJwidByUserId(username);
			} else {
				LOG.info("登录验证失败：用户【" + username + "】权限验证不通过");
			}
			velocityContext.put("jwids", jwids);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("登录验证失败：用户【" + username + "】" + e.getMessage());
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 选择公共号
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/chooseWeixinId", method = { RequestMethod.GET, RequestMethod.POST })
	public void chooseWeixinId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "base/back/common/prelogin.vm";
		try {
			String openid = request.getSession().getAttribute("openid").toString();
			// 根据用户名查询系统用户
			LoginUser user = jwSystemUserService.queryUserByOpenid(openid);
			boolean isAuth = false;
			// 验证用户信息
			if (user != null && Constants.USER_NORMAL_STATE.equals(user.getUserStat())) {
				isAuth = true;
			}
			List<WeixinAccountDto> jwids = new ArrayList<WeixinAccountDto>();
			if (isAuth) {
				// 验证通过,根据userid查询捷微列表
				jwids = jwidService.queryJwWebJwidByUserId(user.getUserId());
			}
			velocityContext.put("jwids", jwids);
		} catch (Exception e) {
			LOG.info("登录验证失败" + e.getMessage());
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	private void validateLoginParam(String jwid, String username, String password) {
		if (StringUtils.isEmpty(username)) {
			throw new RuntimeException("登录用户为空");
		}
		if (StringUtils.isEmpty(password)) {
			throw new RuntimeException("用户密码为空");
		}
		if (StringUtils.isEmpty(jwid)) {
			throw new RuntimeException("微信公众号为空");
		}
	}

	/**
	 * 退出
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public void logout(String jwid, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// -----update--start--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
		String referer = request.getRequestURL().toString();
		if ((referer != null) && (referer.trim().startsWith("http://h5huodong.com"))) {
			response.sendRedirect("http://www.h5huodong.com" + request.getRequestURI());
			return;
		}
		// -----update--end--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
		String viewName = "base/back/common/login.vm";
		VelocityContext velocityContext = new VelocityContext();
		JwSystemLogoTitle logoTitle = jwSystemLogoTitleService.queryLogoTitle().get(0);
		velocityContext.put("logoTitle", logoTitle);
		request.getSession().removeAttribute(Constants.SYSTEM_JWID);
		request.getSession().removeAttribute(Constants.SYSTEM_JWIDNAME);
		request.getSession().removeAttribute(Constants.SYSTEM_USERID);
		request.getSession().removeAttribute(Constants.OPERATE_WEB_LOGIN_USER);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 注册页面
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public void register(HttpServletRequest request, HttpServletResponse response, String mode, String email)
			throws Exception {
		String viewName = "base/back/common/register.vm";
		VelocityContext velocityContext = new VelocityContext();
		if (StringUtil.isEmpty(mode)) {
			mode = "1";
		} else if ("2".equalsIgnoreCase(mode)) {
			velocityContext.put("email", email);
		}
		velocityContext.put("mode", mode);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * @功能:查询网页的head和footer
	 * @作者:liwenhui
	 * @时间:2017-9-4 上午10:45:25 @修改：
	 * @return
	 */
	@RequestMapping("/queryHeadAndFooter")
	@ResponseBody
	public AjaxJson queryHeadAndFooter() {
		AjaxJson ajson = new AjaxJson();
		JwSystemLogoTitle jwSystemLogoTitle = jwSystemLogoTitleService.queryByProp(null);
		ajson.setObj(jwSystemLogoTitle);
		return ajson;
	}

	/**
	 * 注册处理
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/doRegister", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doRegister(JwSystemRegister register, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();
		String message = "";
		try {
			// ------update-begin-Alex-----Date:20180102---for:验证用户输入邮箱格式是否正确---
			// 邮箱验证规则
			// ------update-begin-zhangweijian-----Date:20180731---for:正则表达式修改
			String regEx = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
			// ------update-end-zhangweijian-----Date:20180731---for:正则表达式修改
			// 编译正则表达式
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(register.getEmail());
			// 字符串是否与正则表达式相匹配
			if (!matcher.matches()) {
				message = "用户: " + register.getEmail() + "邮箱格式不正确！";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			// ------update-end-Alex-----Date:20180102---for:验证用户输入邮箱格式是否正确---
			// 验证用户名是否已被注册
			LoginUser user = jwSystemUserService.queryUserByUserId(register.getEmail());
			if (user != null) {
				message = "用户: " + register.getEmail() + "已经存在。请直接登录。";
				j.setSuccess(false);
				j.setMsg(message);
			} else {
				JwSystemRegister property = new JwSystemRegister();
				property.setEmail(register.getEmail());
				List<JwSystemRegister> registerList = jwSystemRegisterService.queryByProperty(property);
				if (registerList != null && registerList.size() > 0) {
					JwSystemRegister r = registerList.get(0);
					Date thisDate = new Date();
					Calendar ca = Calendar.getInstance();
					ca.setTime(r.getRegistertime());
					ca.add(Calendar.HOUR_OF_DAY, 1);
					Date updateDate = ca.getTime();
					// 失效1小时重新生成记录
					if (thisDate.after(updateDate)) {
						Date addDate = new Date();
						register.setRegistertime(addDate);
						register.setChecksign(0);
						register.setAuthstring(UUID.randomUUID().toString());
						register.setPassword(MD5Util.MD5Encode(register.getPassword(), "utf-8"));
						this.jwSystemRegisterService.doAdd(register);
						j.setObj(register.getId());
						j.setSuccess(true);
					} else {
						// 1小时内发送记录不再新生成，更新密码
						r.setPassword(MD5Util.MD5Encode(register.getPassword(), "utf-8"));
						this.jwSystemRegisterService.doEdit(r);
						j.setObj(r.getId());
						j.setSuccess(true);
					}
				} else {
					Date addDate = new Date();
					register.setRegistertime(addDate);
					register.setChecksign(0);
					register.setAuthstring(UUID.randomUUID().toString());
					register.setPassword(MD5Util.MD5Encode(register.getPassword(), "utf-8"));
					this.jwSystemRegisterService.doAdd(register);
					j.setObj(register.getId());
					j.setSuccess(true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			message = "注册失败，请重新注册。";
			j.setMsg(message);
		}
		return j;
	}

	@RequestMapping(value = "/doSendMail", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doSendMail(String registerId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();
		try {
			JwSystemRegister register = jwSystemRegisterService.queryById(registerId);
			if (register != null) {
				if (register.getLastresendtime() == null) {
					// 发送验证邮件
					if (sendEmail(register)) {
						register.setLastresendtime(new Date());
						jwSystemRegisterService.doEdit(register);
					}
				} else {
					// 邮件已发送过
					Date thisDate = new Date();
					Calendar ca = Calendar.getInstance();
					ca.setTime(register.getLastresendtime());
					ca.add(Calendar.MINUTE, 2);
					Date updateDate = ca.getTime();
					// 同一个邮箱超过2分钟重发，否则不重发
					if (thisDate.after(updateDate)) {
						// 发送验证邮件
						if (sendEmail(register)) {
							register.setLastresendtime(new Date());
							jwSystemRegisterService.doEdit(register);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 发送验证邮件
	 * 
	 * @param register
	 */
	private boolean sendEmail(JwSystemRegister register) {
		String subject = "H5活动之家注册验证";// 邮件主题
		String baseurl = "http://www.h5huodong.com/system/check.do";
		// String baseUrl="http://localhost:8080";
		String content = "用户 " + register.getEmail() + ",您好！<br/><br/>您正在注册H5活动之家登录帐号，如非本人操作，请忽略此邮件.<br/><br/>"
				+ "<br/><a href='" + baseurl + "?authstring=" + register.getAuthstring() + "' target='_blank'>"
				+ baseurl + "?authstring=" + register.getAuthstring() + "</a>" + "<br/><br/>"
				+ "点击链接激活邮箱，验证信息24小时内有效！激活成功后请使用注册邮箱登录平台。<br/><br/>"
				+ "本邮件是系统自动发送的，请勿直接回复！感谢您的访问，祝您使用愉快！<br/><br/>H5活动之家团队<br/><br/>";

		String text = content;// 邮件内容
		return SendMailUtil.sendCommonMail(register.getEmail(), subject, text);
	}

	/**
	 * 邮箱验证
	 * 
	 * @return
	 */
	@RequestMapping(value = "/check", method = { RequestMethod.GET, RequestMethod.POST })
	public void doCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = "base/back/common/register.vm";
		VelocityContext velocityContext = new VelocityContext();
		String mode = "3";
		try {
			String authString = request.getParameter("authstring");
			if (!StringUtil.isEmpty(authString)) {
				JwSystemRegister property = new JwSystemRegister();
				property.setAuthstring(authString);
				List<JwSystemRegister> registerList = jwSystemRegisterService.queryByProperty(property);
				if (registerList != null && registerList.size() > 0) {
					JwSystemRegister register = registerList.get(0);
					// 如果验证信息超过24小时，验证失败
					Date thisDate = new Date();
					Calendar ca = Calendar.getInstance();
					ca.setTime(register.getRegistertime());
					ca.add(Calendar.HOUR_OF_DAY, 24);
					Date updateDate = ca.getTime();
					// 验证过期
					if (thisDate.after(updateDate)) {
						velocityContext.put("msg", "验证信息超过24个小时，请重新注册！");
						mode = "1";
					} else {
						// 验证用户名是否已被注册
						LoginUser loginUser = jwSystemUserService.queryUserByUserId(register.getEmail());
						if (loginUser != null) {
							mode = "3";
						} else {
							// 验证通过
							JwSystemUser user = new JwSystemUser();
							user.setUserId(register.getEmail());
							user.setPassword(register.getPassword());
							user.setEmail(register.getEmail());
							user.setCreateDt(new Date());
							user.setUserStat(Constants.USER_NORMAL_STATE);
							List<String> roleIds = new ArrayList<String>();
							roleIds.add("01");
							jwSystemUserService.doAdd(user, roleIds);
							mode = "3";
						}
					}
				} else {
					velocityContext.put("msg", "验证信息不正确，请重新注册！");
					mode = "1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			velocityContext.put("msg", "验证失败，请重新注册！");
			mode = "1";
		}
		velocityContext.put("mode", mode);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 用户登录时获取默认登录账号
	 * 
	 * @author 黄青全
	 * @since 2016-10-20 09:45:13
	 * @param 登录用户名
	 * @param 登录的密码
	 * @param request
	 * @param response
	 * @return 用户登录的jwid
	 */
	@RequestMapping(value = "/getDefaultJwid", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson getDefaultJwid(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			// 根据用户名查询系统用户
			LoginUser user = jwSystemUserService.queryUserByUserId(username);
			boolean isAuth = false;
			String passwordEncode = MD5Util.MD5Encode(password, "utf-8");
			// 验证用户信息
			if (user != null) {
				if (passwordEncode != null && passwordEncode.equals(user.getPassword())
						&& Constants.USER_NORMAL_STATE.equals(user.getUserStat())) {
					isAuth = true;
				} else {
					LOG.info("登录验证失败：用户【" + username + "】登录信息验证不通过");
				}
			}
			JwSystemUserJwid jwSystemUserJwid = null;
			if (isAuth) {
				jwSystemUserJwid = jwSystemUserJwidService.queryOneByUserIdAndDefaultFlag(username, "1");
				if (jwSystemUserJwid == null) {
					j.setObj(defaultJwid);
				} else {
					j.setObj(jwSystemUserJwid.getJwid());
				}
			} else {
				LOG.info("登录验证失败：用户【" + username + "】权限验证不通过");
			}
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			LOG.info("登录验证失败：用户【" + username + "】" + e.getMessage());
		}
		return j;
	}

	/**
	 * 微信登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public AjaxJson wxLogin(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String openid = request.getSession().getAttribute("openid").toString();
		String jwid = request.getParameter("jwid");
		try {
			LoginUser loginUser = jwSystemUserService.queryUserByOpenid(openid);
			if (loginUser != null) {
				request.getSession().setAttribute(Constants.SYSTEM_USERID, loginUser.getUserId());
				request.getSession().setAttribute(Constants.OPERATE_WEB_LOGIN_USER, loginUser);
				WeixinAccountDto jwWebJwid = jwidService.queryJwidNameByJwid(jwid);
				request.getSession().setAttribute(Constants.SYSTEM_JWID, jwid);
				request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME, jwWebJwid.getName());
				request.getSession().removeAttribute("openid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setObj("登录验证失败");
		}
		return j;
	}
//	 /**
//	  * 生成带参数的二维码
//	  * @author huangqingquan
//	  * @since 2016-10-20 09:47:44
//	  * @param jwid 微信ID
//	  * @param sceneId 场景Id
//	  * @return 返回生成带参数二维码的地址
//	  */
//	 private String doAddQrcodeRecord(String jwid,String sceneId){
//		 //生成带参数的二维码地址
//		 String qrcodeUrl = WeiXinQrcodeUtil.getTemporaryQrcode(jwid, sceneId, 300);
//		 if(StringUtils.isNotEmpty(qrcodeUrl)){
//			 //录入带参数的二维码记录
//			 JwSystemQrcodeRecord jwSystemQrcodeRecord=new JwSystemQrcodeRecord();
//			 jwSystemQrcodeRecord.setExpireSeconds(300);
//			 jwSystemQrcodeRecord.setCreateTime(new Date());
//			 jwSystemQrcodeRecord.setJwid(jwid);
//			 jwSystemQrcodeRecord.setQrcodeUrl(qrcodeUrl);
//			 jwSystemQrcodeRecord.setSceneId(sceneId);
//			 jwSystemQrcodeRecordService.doAdd(jwSystemQrcodeRecord);
//			 return qrcodeUrl;
//		 }
//		 return null;
//	 }
//	 /**
//	  * 获取微信二维码地址
//	  * @param request
//	  * @param response
//	  * @return
//	  */
//	 @RequestMapping(value = "/getQrcodeUrl",method ={RequestMethod.GET, RequestMethod.POST})
//	 @ResponseBody
//	 public AjaxJson getQrcodeUrl(HttpServletRequest request,HttpServletResponse response){
//		 AjaxJson j=new AjaxJson();
//		 String sceneId = WeiXinQrcodeUtil.getSceneId();
//		 try {
//			 String qrcodeUrl = doAddQrcodeRecord(defaultJwid,sceneId);
//			 if(StringUtils.isNotEmpty(qrcodeUrl)){
//				 Map<String, Object> param=new HashMap<String, Object>();
//				 param.put("sceneId", sceneId);
//				 param.put("qrcodeUrl", qrcodeUrl);
//				 j.setAttributes(param);
//			 }else{
//				 j.setSuccess(false);
//			 }
//		} catch (Exception e) {
//			j.setSuccess(false);
//		}
//		return j;
//	 }

	/**
	 * 登录 【复制login】
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public void login(String jwid, String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = "base/back/common/login.vm";
		VelocityContext velocityContext = new VelocityContext();
		try {
			String referer = request.getRequestURL().toString();
			if ((referer != null) && (referer.trim().startsWith("http://h5huodong.com"))) {
				response.sendRedirect("http://www.h5huodong.com" + request.getRequestURI());
				return;
			}
			LoginUser user = (LoginUser) request.getSession().getAttribute(Constants.OPERATE_WEB_LOGIN_USER);
			JwSystemLogoTitle logoTitle = jwSystemLogoTitleService.queryLogoTitle().get(0);
			velocityContext.put("logoTitle", logoTitle);
			// 验证用户信息
			if (user != null) {
				// 用户已登录
				viewName = indexPath;
				velocityContext.put("jwidname", (String) request.getSession().getAttribute(Constants.SYSTEM_JWIDNAME));
				velocityContext.put("userid", user.getUserId());
				try {
					// 获取菜单
					List<Menu> menuTree = jwSystemAuthService.getMenuTree(user.getUserId());
//					LOG.info("menuTree---->"+menuTree);
					velocityContext.put(Constants.OPERATE_WEB_MENU_TREE, menuTree);
					ViewVelocity.view(request, response, viewName, velocityContext);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				// -----------update begin Author:黄青全 date：2016-10-20 09:43:17
				// for:用户未登录时,校验验证码-------------------
				String randCode = request.getParameter("randCode");
				HttpSession session = request.getSession();
				Object yzm = session.getAttribute("randCode");
				if (StringUtils.isEmpty(randCode) || yzm == null || !randCode.equals(yzm.toString())) {
					throw new RuntimeException("验证码错误");
				}
				session.removeAttribute("randCode");
				// -----------update end Author:黄青全 date：2016-10-20 09:43:17
				// for:用户未登录时,校验验证码------------------
				validateLoginParam(jwid, username, password);
				// 用户未登录
				user = jwSystemUserService.queryUserByUserId(username);
				if (user != null) {
					String passwordEncode = MD5Util.MD5Encode(password, "utf-8");
					if (passwordEncode != null && passwordEncode.equals(user.getPassword())
							&& "NORMAL".equals(user.getUserStat())) {
						// 判断jwid是否属于该用户
						WeixinAccountDto jw = jwidService.queryJwidByJwidAndUserId(jwid, username);
						if (jw != null) {
							request.getSession().setAttribute(Constants.SYSTEM_JWID, jwid);
							request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME, jw.getName());
							request.getSession().setAttribute(Constants.SYSTEM_USERID, username);
							request.getSession().setAttribute(Constants.OPERATE_WEB_LOGIN_USER, user);
							velocityContext.put("jwidname", jw.getName());
							velocityContext.put("userid", user.getUserId());
							try {
								// 获取菜单
								List<Menu> menuTree = jwSystemAuthService.getMenuTree(user.getUserId());
								velocityContext.put(Constants.OPERATE_WEB_MENU_TREE, menuTree);
								viewName = indexPath;
								ViewVelocity.view(request, response, viewName, velocityContext);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return;
						} else {
							LOG.info("登录失败：jwid【" + jwid + "】不属于用户【" + username + "】");
						}
					}
				} else {
					LOG.info("登录失败：用户【" + username + "】不存在");
				}

			}
		} catch (Exception e) {
			LOG.info("登录失败：用户【" + username + "】" + e.getMessage());
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

//	 /**
//	   * 登录
//	 * @throws Exception 
//	   */
//	 @RequestMapping(value = "/login",method ={RequestMethod.GET, RequestMethod.POST})
//	 public void login(String jwid,String username,String password,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		 String viewName = "base/back/common/login.vm";
//		 VelocityContext velocityContext = new VelocityContext();
//		try {
//			//-----update--start--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
//			String referer=request.getRequestURL().toString();
//			if ((referer != null) && (referer.trim().startsWith("http://h5huodong.com"))) {
//				response.sendRedirect("http://www.h5huodong.com"+request.getRequestURI());
//				return;
//			}
//			//-----update--end--author:huangqingquan-----date:2017-3-24----for:如果是不带3w的请求，将重定向
//			 LoginUser user = (LoginUser)request.getSession().getAttribute(Constants.OPERATE_WEB_LOGIN_USER);
//			 JwSystemLogoTitle logoTitle = jwSystemLogoTitleService.queryLogoTitle().get(0);
//			 velocityContext.put("logoTitle",logoTitle);
//			 //验证用户信息
//			 if(user!=null){
//				 //用户已登录
//				 viewName = indexPath;
//				 velocityContext.put("jwidname", (String)request.getSession().getAttribute(Constants.SYSTEM_JWIDNAME));
//				 velocityContext.put("userid", user.getUserId());
//				 try {
//					//获取菜单
//					 List<Menu> menuTree = jwSystemAuthService.getMenuTree(user.getUserId());
//					LOG.info("menuTree---->"+menuTree);
//					velocityContext.put(Constants.OPERATE_WEB_MENU_TREE,menuTree);
//					ViewVelocity.view(request, response, viewName,velocityContext);
//				 } catch (Exception e) {
//					e.printStackTrace();
//				 }
//				 return;
//			 }else{
//				//-----------update begin Author:黄青全 date：2016-10-20 09:43:17 for:用户未登录时,校验验证码-------------------
//				String randCode = request.getParameter("randCode");
//				HttpSession session = request.getSession();
//				Object yzm = session.getAttribute("randCode");
//				if(StringUtils.isEmpty(randCode)||yzm==null||!randCode.equals(yzm.toString())){
//					throw new RuntimeException("验证码错误");
//				}
//				session.removeAttribute("randCode");
//				//-----------update  end   Author:黄青全 date：2016-10-20 09:43:17 for:用户未登录时,校验验证码------------------
//				 validateLoginParam(jwid,username,password);
//				 //用户未登录
//				 user  = jwSystemUserService.queryUserByUserId(username);
//				 if(user!=null){
//					 String passwordEncode = MD5Util.MD5Encode(password, "utf-8");
//					 if(passwordEncode!=null&&passwordEncode.equals(user.getPassword()) &&"NORMAL".equals(user.getUserStat())){
//						 // 判断jwid是否属于该用户
//						 JwWebJwid jw = jwidService.queryJwidByJwidAndUserId(jwid,username);
//						 if(jw!=null){
//							 request.getSession().setAttribute(Constants.SYSTEM_JWID, jwid);
//							 request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME, jw.getName());
//							 request.getSession().setAttribute(Constants.SYSTEM_USERID, username);
//							 request.getSession().setAttribute(Constants.OPERATE_WEB_LOGIN_USER, user);
//							 velocityContext.put("jwidname",jw.getName());
//							 velocityContext.put("userid",user.getUserId());							
//							 try {
//								//获取菜单
//								 List<Menu> menuTree = jwSystemAuthService.getMenuTree(user.getUserId());
//								velocityContext.put(Constants.OPERATE_WEB_MENU_TREE, menuTree);
//								viewName = indexPath;
//								ViewVelocity.view(request,response,viewName,velocityContext);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//							return;
//						 }else{
//							 LOG.info("登录失败：jwid【"+jwid+"】不属于用户【"+username+"】");
//						 }
//					 }
//				 }else{
//					 LOG.info("登录失败：用户【"+username+"】不存在");
//				 }
//				 
//			 }
//		} catch (Exception e) {
//			LOG.info("登录失败：用户【"+username+"】"+e.getMessage());
//		}
//		 ViewVelocity.view(request,response,viewName,velocityContext);
//	 }

	/**
	 * 登录之后切换公众号管理
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/back/preSelectJwid", method = { RequestMethod.GET, RequestMethod.POST })
	public void preSelectJwid(String username, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "base/back/common/preSelectJwid.vm";
		try {
			List<WeixinAccountDto> jwids = new ArrayList<WeixinAccountDto>();
			// 根据userid查询捷微列表
			jwids = jwidService.queryJwWebJwidByUserId(username);
			velocityContext.put("jwids", jwids);
		} catch (Exception e) {
			LOG.info("登录验证失败：用户【" + username + "】" + e.getMessage());
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * @功能:切换默认公众号
	 * @作者:LeeShaoQing
	 * @时间:20180726
	 * @param request
	 * @return ajson
	 */
	@RequestMapping(value = "/back/switchDefaultOfficialAcco", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson switchDefaultOfficialAcco(HttpServletRequest request) {
		AjaxJson ajson = new AjaxJson();
		try {
			String jwid = request.getParameter("jwid");
			if (StringUtils.isEmpty(jwid)) {
				jwid = defaultJwid;
			}

			request.getSession().setAttribute(Constants.SYSTEM_JWID, jwid);
			WeixinAccountDto myJwWebJwid = jwidService.queryJwidNameByJwid(jwid);
			if (myJwWebJwid != null) {
				request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME, myJwWebJwid.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			ajson.setSuccess(false);
		}
		return ajson;
	}
}
