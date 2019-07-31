package com.jeecg.p3.system.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jeecg.p3.system.service.JwSystemAuthService;
import com.jeecg.p3.system.util.Constants;
import com.jeecg.p3.system.vo.Auth;
import com.jeecg.p3.system.vo.LoginUser;

/**
 * 登录后台拦截器
 * 
 * @author zhoujf
 * @serial 后台请求都需要带着/back/
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	private JwSystemAuthService jwSystemAuthService;

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 注解排除权限拦截机制
        if (this.skipPermission(object)) {
            return true;
        }
        // 用户访问的资源地址
		String requestPath = getRequestPath(request);
		String basePath = request.getContextPath();
		request.setAttribute("basePath", basePath);
		if (oConvertUtils.isNotEmpty(requestPath)) {
			// 扩展拦截cms网站、企业微信功能模块
			if (requestPath.indexOf("/back/") != -1) {
				LoginUser user = (LoginUser) request.getSession().getAttribute(Constants.OPERATE_WEB_LOGIN_USER);
				if (user == null) {
					logger.info("---------------AuthInterceptor--------------登录用户信息获取失败！");
					String url = basePath + "/system/toLogin.do";
					response.sendRedirect(url);
					return false;
				}
				// 检查权限
				if (!checkUriAuth(requestPath, user.getUserId())) {
					logger.info("---------------AuthInterceptor--------------无操作权限！");
					response.setStatus(401);
					return false;
				}
				return true;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

    /**
     * 是否排除拦截
     *
     * @param handler
     * @return
     */
    private boolean skipPermission(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            SkipAuth jauthType = handlerMethod.getBean().getClass().getAnnotation(SkipAuth.class);
            if (jauthType != null && (jauthType.auth() == SkipPerm.SKIP_ALL || jauthType.auth() == SkipPerm.SKIP_BACK)) {
                return true;
            } else {
                SkipAuth jauthMethod = handlerMethod.getMethod().getAnnotation(SkipAuth.class);
                if (jauthMethod != null && (jauthMethod.auth() == SkipPerm.SKIP_ALL || jauthMethod.auth() == SkipPerm.SKIP_BACK)) {
                    return true;
                }
            }
        }
        return false;
    }

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	private String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	/**
	 * 检查权限
	 */
	private boolean checkUriAuth(String requestPath, String userId) {
		requestPath = "/" + requestPath;
		List<Auth> list = jwSystemAuthService.queryAuthByAuthContr(requestPath);
		if (list == null || list.size() <= 0) {
			return true;
		}
		List<Auth> authList = jwSystemAuthService.queryAuthByUserIdAndAuthContr(userId, requestPath);
		if (authList != null && authList.size() > 0) {
			return true;
		}
		return false;
	}
}
