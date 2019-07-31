package com.jeecg.p3.system.interceptors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import org.jeecgframework.p3.core.common.utils.DataDictTool;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.SignatureUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.jeecg.p3.dict.entity.ProjectErrorConfig;
import com.jeecg.p3.system.def.SystemProperties;

/**
 * 签名拦截器 - H5网页访问
 * 
 * @author zhoujf
 */
@Component
public class AccessSignInterceptor implements HandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(AccessSignInterceptor.class);
	private static final String SIGN_PARAM_NAME = "sign";
	private static final String SESSION_OPENID = "openid";
	private static final String SESSION_NICKNAME = "nickname";

	/**
	 * 活动访问签名拦截，防止串改数据
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 注解排除权限拦截机制
        if (this.skipPermission(object)) {
            return true;
        }

        // 用户访问的资源地址
		String requestPath = getRequestPath(request);
		String requestUrl = getRequestUrl(request);
		logger.debug("-------------requestUrl-------------" + requestUrl);
		String basePath = request.getContextPath();
		request.setAttribute("basePath", basePath);
		if (oConvertUtils.isNotEmpty(requestPath)) {
			if (requestPath.indexOf("/back/") > -1) {
				return true;
			} else if (requestUrl.indexOf(SIGN_PARAM_NAME + "=") != -1) {
				String openid = request.getParameter("openid");
				String nickname = request.getParameter("nickname");
				String sign = request.getParameter(SIGN_PARAM_NAME);
				logger.debug(
						"----openid--------" + openid + "----nickname--------" + nickname + "----Sign--------" + sign);
				if (StringUtil.notEmpty(sign)) {
					Map<String, String> paramMap = getSignMap(request);
					boolean check = SignatureUtil.checkSign(paramMap, SystemProperties.SIGN_KEY, sign);
					logger.debug("--------------SignatureUtil.checkSign---------check--" + check);
					if (check) {
						if (StringUtil.isEmpty(openid)) {
							logger.info("---------------SignInterceptor--------------openid为空");
							return false;
						}
						request.getSession().setAttribute(SESSION_OPENID, openid);
						request.getSession().setAttribute(SESSION_NICKNAME, nickname);
						response.sendRedirect(getRedirectUrl(basePath + "/" + requestPath, paramMap));
						logger.info("---------------SignInterceptor--------------签名验证失败");
						return false;
					}
				}
			} else {
				String session_openid = (String) request.getSession().getAttribute(SESSION_OPENID);
				logger.debug("----------session_openid------------------------" + session_openid);
				if (StringUtil.notEmpty(session_openid)) {
					String openid = request.getParameter("openid");
					if (StringUtil.notEmpty(openid)) {
						if (session_openid.equals(openid)) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
			String defaultUrl = basePath + "/system/noAuth.do";
			logger.info("---------------SignInterceptor--------------没有权限---requestPath=" + requestPath);
			redirectUrl404(requestPath, defaultUrl, response);
			return false;
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
            if (jauthType != null && (jauthType.auth() == SkipPerm.SKIP_ALL || jauthType.auth() == SkipPerm.SKIP_SIGN)) {
                return true;
            } else {
                SkipAuth jauthMethod = handlerMethod.getMethod().getAnnotation(SkipAuth.class);
                if (jauthMethod != null && (jauthMethod.auth() == SkipPerm.SKIP_ALL || jauthMethod.auth() == SkipPerm.SKIP_SIGN)) {
                    return true;
                }
            }
        }
        return false;
    }

	private Map<String, String> getSignMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> es = map.entrySet();
		Iterator<Entry<String, String[]>> it = es.iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object ov = entry.getValue();
			String v = "";
			if (ov instanceof String[]) {
				String[] value = (String[]) ov;
				v = value[0];
			} else {
				v = ov.toString();
			}
			paramMap.put(k, v);
		}
		return paramMap;
	}

	private String getRedirectUrl(String requestPath, Map<String, String> paramMap) {
		Set<Entry<String, String>> es = paramMap.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append(requestPath + "?");
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"null".equals(v) && !"sign".equals(k) && !"nickname".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		String redirectUrl = sb.toString();
		redirectUrl = redirectUrl.substring(0, redirectUrl.length() - 1);
		logger.info("---------------redirectUrl--------------" + redirectUrl);
		return redirectUrl;
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

	private String getRequestUrl(HttpServletRequest request) {
		String param = request.getQueryString();
		String requestUrl = request.getRequestURI();
		if (param != null) {
			requestUrl = requestUrl + "?" + request.getQueryString();
		}
		if (requestUrl.indexOf("#") != -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("#"));
		}
		return requestUrl;
	}

	private String getProjectPath(String requestPath) {
		String projectPath = "";
		if (StringUtils.isNotEmpty(requestPath)) {
			requestPath.replace("\\", "/");
			int index = requestPath.lastIndexOf("/");
			if (index != -1) {
				projectPath = requestPath.substring(0, index);
				logger.info("---------------projectPath--------------" + projectPath);
			}
		}
		return projectPath;
	}

	private void redirectUrl404(String requestPath, String defaultUrl, HttpServletResponse response)
			throws IOException {
		String projectPath = getProjectPath(requestPath);
		try {
			if (StringUtils.isNotEmpty(projectPath)) {
				// 根据projectPath查询对应的404页面
				DataDictTool tool = new DataDictTool();
				ProjectErrorConfig config = tool.getSysErrorConfig(projectPath);
				String url = defaultUrl;
				if (config != null) {
					if (StringUtils.isNotEmpty(config.getRedirectUrl())) {
						url = config.getRedirectUrl();
					}
				}
				logger.info("---------------redirectUrl404--------------" + url);
				response.sendRedirect(url);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("---------------redirectUrl404----异常----------" + defaultUrl);
			response.sendRedirect(defaultUrl);
		}
	}
}
