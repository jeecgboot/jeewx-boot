package com.jeecg.p3.system.web;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.baseApi.service.BaseApiJwidService;
import com.jeecg.p3.baseApi.vo.OpenAccountVo;
import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.system.exception.BusinessException;
import com.jeecg.weibo.util.HttpUtil;
import org.jeecgframework.p3.core.util.SignatureUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.system.def.SystemProperties;
import com.jeecg.p3.system.service.JwSystemProjectService;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;

/**
 * OPEN: oAuth2.0 基于项目配置表的转发中心
 * @author scott
 * @since：2016年11月14日
 * @version:1.0
 */
@Controller
@RequestMapping("/linksucai")
public class LinksucaiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LinksucaiController.class);
    private static final String SCOPE = "snsapi_base"; //网页授权类型静默授权（只能获取到openid）
    //	private static final String SCOPE = "snsapi_userinfo";//网页授权类型非静默授权（可获取到openid、头像、昵称）

    @Autowired
    private JwSystemProjectService jwSystemProjectService;
    @Autowired
    private BaseApiJwidService baseApiJwidService;

    /**
     * 链接跳转
     */
    @SkipAuth
    @RequestMapping(value = "link", method = {RequestMethod.GET, RequestMethod.POST})
    public void link(HttpServletRequest request, HttpServletResponse response) {
        try {
            String jwid = request.getParameter("jwid");
            String state = request.getParameter("state");
            //获取公众号缓存信息
            WeixinAccount weixinAccount = JedisPoolUtil.getWxAccount(jwid);
            //类型：1手动授权，2扫码授权（扫描授权的公众号秘钥为空）
            if (oConvertUtils.isEmpty(weixinAccount.getAccountappsecret())) {
                redirectByAuthType2(request, response, SCOPE);
            } else {
                redirectByAuthType1(request, response, SCOPE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手工授权公众号
     * @param request
     * @param response
     * @param SCOPE
     * @throws IOException
     */
    private void redirectByAuthType1(HttpServletRequest request, HttpServletResponse response, String SCOPE) throws IOException {
        long start = System.currentTimeMillis();
        logger.info("-------[LINKSTARTIME]--------------开始时间戳------------>" + start);
        try {
            // 链接素材ID
            String linkid = request.getParameter("linkid");
            // 微信原始ID
            String jwid = request.getParameter("jwid");
            String state = request.getParameter("state");
            //获取公众号信息
            WeixinAccount weixinAccount = JedisPoolUtil.getWxAccount(jwid);
            if (weixinAccount == null) {
                logger.error("------[异常]----------OpenWX----------微信原始ID参数异常,查询公众号失败，原始ID: ------" + jwid);
                return;
            }
            String code = request.getParameter("code");
            String APPID = weixinAccount.getAccountappid();
            String APPSECRET = weixinAccount.getAccountappsecret();

            if (code == null || code.length() == 0) {
                logger.info("-------[LINKWXSTEP1_START]-----------距离开始时间戳耗时--------------->" + (System.currentTimeMillis() - start) + "ms");
                String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
                String REDIRECT_URI = request.getRequestURI();
                if (REDIRECT_URI.indexOf(SystemProperties.domain) == -1) {
                    REDIRECT_URI = SystemProperties.domain.replace(request.getContextPath(), "") + REDIRECT_URI;
                }
                logger.info("------------------REDIRECT_URI--------1---------" + REDIRECT_URI);
                REDIRECT_URI += "?" + request.getQueryString();
                logger.info("------------------REDIRECT_URI--------2---------" + REDIRECT_URI);
                REDIRECT_URI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
                String weixinOauthURL = web_oauth_url.replace("APPID", APPID).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE", SCOPE);
                if (oConvertUtils.isNotEmpty(state)) {
                    weixinOauthURL = weixinOauthURL.replace("STATE", state);
                }
                logger.info("------------------weixinOauthURL-----------------" + weixinOauthURL);
                logger.info("-------[LINKWXSTEP1_END]--------------距离开始时间戳耗时------------>" + (System.currentTimeMillis() - start) + "ms");
                response.sendRedirect(weixinOauthURL);
            } else {
                logger.info("-------[LINKWXSTEP2_START]------------距离开始时间戳耗时-------------->" + (System.currentTimeMillis() - start));
                // Step.2 通过code，调用微信接口，获取openid
                String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
                requestUrl = requestUrl.replace("APPID", APPID);
                requestUrl = requestUrl.replace("SECRET", APPSECRET);
                requestUrl = requestUrl.replace("CODE", code);
                logger.info("------------------code-----------------" + code);

                logger.info("-------[LINKWXSTEP2_REQUEST]调用微信oauth2接口获取openid请求-------------------------->" + (System.currentTimeMillis() - start) + "ms");
                com.alibaba.fastjson.JSONObject json2 = httpRequest(requestUrl, "POST", null);
                logger.info("-------[LINKWXSTEP2_RESPONSE]调用微信oauth2接口获取openid响应-------------------------->" + (System.currentTimeMillis() - start) + "ms");
                //update-end--author:qinfeng------date:20160202--------------------for:更换Http工具类，提高效率---------------

                String openid = (String) json2.get("openid");
                logger.info("----------------get--openid-----------------" + openid);

                String jeewxUrl = SystemProperties.domain + "/" + jwSystemProjectService.queryById(linkid).getHdzsUrl();
                if (oConvertUtils.isEmpty(jeewxUrl)) {
                    logger.error("------[异常]----------OpenWX----------链接素材ID参数异常,查询数据失败，Link ID: ------" + linkid);
                    return;
                }
                //--update-end----authro:scott-----------date:20160129-----------------for:获取素材链接通过缓存读取，降低数据库访问频率------------------------------

                //========================================================================================================
                logger.info("----------------outerLink------1-----------" + jeewxUrl);
                if (oConvertUtils.isNotEmpty(jeewxUrl)) {
                    jeewxUrl = jeewxUrl.replace("${openid}", openid); // 粉丝openid
                    jeewxUrl = jeewxUrl.replace("${wxid}", weixinAccount.getWeixinAccountid()); // 公众号原始ID
                    jeewxUrl = jeewxUrl.replace("${wxcode}", weixinAccount.getAccountnumber()); // 公众微信号
                    jeewxUrl = jeewxUrl.replace("${appid}", weixinAccount.getAccountappid());
                    jeewxUrl = jeewxUrl.replace("${appsecret}", weixinAccount.getAccountappsecret());
                    jeewxUrl = jeewxUrl.replace("${accesstoken}", weixinAccount.getAccountaccesstoken());
                    //---update-begin-Alex---Date:20180914-----for:增加用户网页授权换取的AccessToken参数------
                    String utoken = oConvertUtils.getString(json2.get("access_token"));
                    jeewxUrl = jeewxUrl.replace("${utoken}", utoken);
                    //---update-end-Alex---Date:20180914-----for:增加用户网页授权换取的AccessToken参数------

                }
                logger.info("----------------request.getQueryString()-------2----------" + request.getQueryString());
                String queryParam = request.getQueryString();
                if (oConvertUtils.isNotEmpty(queryParam)) {
                    jeewxUrl = jeewxUrl + "&" + queryParam;

                }
                logger.info("----------------outerLink-------3----------" + jeewxUrl);
                //========================================================================================================
                String sign = SignatureUtil.sign(getSignMap(jeewxUrl), SystemProperties.SIGN_KEY);
                logger.info("-------[LINKWXSTEP2_END]--------------距离开始时间戳耗时------------>" + (System.currentTimeMillis() - start) + "ms");
                response.sendRedirect(jeewxUrl + "&sign=" + sign);
            }
        } catch (Exception e) {

        }
    }

    /**
     * 扫描公众号逻辑（第三方授权）
     * @param request
     * @param response
     * @throws IOException
     */
    private void redirectByAuthType2(HttpServletRequest request,HttpServletResponse response,String SCOPE) throws IOException{
        long start = System.currentTimeMillis();
        logger.info("-------[LINKSTARTIME]--------------开始时间戳------------>"+start);
        // 链接素材ID
        String linkid = request.getParameter("linkid");
        String jwid = request.getParameter("jwid");
        WeixinAccount weixinAccount = JedisPoolUtil.getWxAccount(jwid);
        if (weixinAccount == null) {
            logger.error("------[异常]----------OpenWX----------微信原始ID参数异常,查询公众号失败，原始ID: ------" + jwid);
            return;
        }
        // Step.1 获取微信author2.0 code
        // 用户同意授权后，能获取到code

        String code = request.getParameter("code");
        String APPID = weixinAccount.getAccountappid();
        //String SCOPE = "snsapi_userinfo";
        if (code == null || code.length() == 0) {
            logger.info("-------[LINKWXSTEP1_START]-----------距离开始时间戳耗时--------------->"+(System.currentTimeMillis()-start)+"ms");
            String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE&component_appid=COMPONENT_appid#wechat_redirect";
            String REDIRECT_URI = request.getRequestURI();
            if (REDIRECT_URI.indexOf(SystemProperties.domain) == -1) {
                REDIRECT_URI = SystemProperties.domain + REDIRECT_URI;
            }
            logger.info("------------------REDIRECT_URI--------1---------" + REDIRECT_URI);
            REDIRECT_URI += "?" + request.getQueryString();
            logger.info("------------------REDIRECT_URI--------2---------" + REDIRECT_URI);
            REDIRECT_URI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
            String weixinOauthURL = web_oauth_url.replace("APPID", APPID).replace("REDIRECT_URI", REDIRECT_URI).replace("SCOPE", SCOPE).replace("COMPONENT_appid",SystemProperties.component_appid);
            logger.info("------------------weixinOauthURL-----------------" + weixinOauthURL);
            logger.info("-------[LINKWXSTEP1_END]--------------距离开始时间戳耗时------------>"+(System.currentTimeMillis()-start)+"ms");
            response.sendRedirect(weixinOauthURL);
        } else {
            logger.info("-------[LINKWXSTEP2_START]------------距离开始时间戳耗时-------------->"+(System.currentTimeMillis()-start));
            // Step.2 通过code，调用微信接口，获取openid
            String requestUrl = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=APPID&code=CODE&grant_type=authorization_code&component_appid=COMPONENT_appid&component_access_token=COMPONENT_ACCESS_TOKEN";
            requestUrl = requestUrl.replace("APPID", APPID);
            //requestUrl = requestUrl.replace("SECRET", APPSECRET);
            requestUrl = requestUrl.replace("CODE", code);
            requestUrl = requestUrl.replace("COMPONENT_appid", SystemProperties.component_appid);
            //TODO weixinOpenAccountService需优化
            OpenAccountVo weixinOpenAccount = baseApiJwidService.queryOneByAppid(SystemProperties.component_appid);
            if(weixinOpenAccount==null){
                throw new BusinessException("重置accessToken时获取WEIXINOPENACCOUNT为空");
            }
            requestUrl = requestUrl.replace("COMPONENT_ACCESS_TOKEN",weixinOpenAccount.getComponentAccessToken());;
            logger.info("------------------code-----------------" + code);

            //update-begin--author:qinfeng------date:20160202--------------------for:更换Http工具类，提高效率---------------
            logger.info("-------[LINKWXSTEP2_REQUEST]调用微信oauth2接口获取openid请求-------------------------->"+(System.currentTimeMillis()-start)+"ms");
            logger.info("-------[LINKWXSTEP2_REQUEST]调用微信oauth2接口获取openid请求地址-------------------------->"+requestUrl);
            com.alibaba.fastjson.JSONObject json2 = HttpUtil.httpRequest(requestUrl,"POST",null);
            logger.info("-------[LINKWXSTEP2_RESPONSE]调用微信oauth2接口获取openid响应-------------------------->"+(System.currentTimeMillis()-start)+"ms");
            //update-end--author:qinfeng------date:20160202--------------------for:更换Http工具类，提高效率---------------

            String openid = (String) json2.get("openid");
            logger.info("----------------get--openid-----------------" + openid);
            // Step.3 openid拼接到对外url
            String jeewxUrl = SystemProperties.domain + "/" + jwSystemProjectService.queryById(linkid).getHdzsUrl();
            if(oConvertUtils.isEmpty(jeewxUrl)){
                logger.error("------[异常]----------OpenWX----------链接素材ID参数异常,查询数据失败，Link ID: ------" + linkid);
                return;
            }

            logger.info("----------------outerLink------1-----------" + jeewxUrl);
            if(oConvertUtils.isNotEmpty(jeewxUrl)){
                jeewxUrl = jeewxUrl.replace("${openid}", openid); // 粉丝openid
                jeewxUrl = jeewxUrl.replace("${appid}", APPID);
                jeewxUrl = jeewxUrl.replace("${webAuthToken}", (String) json2.get("access_token"));
            }
            //-----------------------------------------------------
            String str="";
            Map<?, ?> parameterMap = request.getParameterMap();
            Set<?> es = parameterMap.entrySet();
            Iterator<?> it = es.iterator();
            while (it.hasNext()) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) it.next();
                String k = (String) entry.getKey();
                Object ov = entry.getValue();
                String v = "";
                if ((ov instanceof String[])) {
                    String[] value = (String[]) ov;
                    v = value[0];
                } else {
                    v = ov.toString();
                }
                if(!"appid".equals(k)){
                    str+="&"+k+"="+v;
                }
            }
            logger.info("----------------request.getQueryString()-------2----------" + str);
            //-----------------------------------------------------
            String queryParam = request.getQueryString();
            if(oConvertUtils.isNotEmpty(queryParam)){
                queryParam = queryParam.replace("link&", "");
                jeewxUrl = jeewxUrl +str;

            }
            logger.info("----------------outerLink-------3----------" + jeewxUrl);
            String sign = SignatureUtil.sign(getSignMap(jeewxUrl), SystemProperties.SIGN_KEY);
            logger.info("-------[LINKWXSTEP2_END]--------------距离开始时间戳耗时------------>"+(System.currentTimeMillis()-start)+"ms");
            response.sendRedirect(jeewxUrl+"&sign="+sign);
        }
    }

    /**
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    private JSONObject httpRequest(String requestUrl,
                                   String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpUrlConn = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            //HttpURLConnection设置网络超时
            httpUrlConn.setConnectTimeout(4500);
            httpUrlConn.setReadTimeout(4500);

//			httpUrlConn.setRequestProperty("content-type", "text/html");
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
            // jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
        } catch (Exception e) {
        } finally {
            try {
                httpUrlConn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     *
     * @param url
     * @return
     */
    private Map<String, String> getSignMap(String url) {
        Map<String, String> paramMap = new HashMap<String, String>();
        url = url.substring(url.indexOf("?") + 1);
        String[] params = url.split("&");
        for (int i = 0; i < params.length; i++) {
            String param = params[i];
            if (param.indexOf("=") != -1) {
                String[] values = param.split("=");
                if (values != null && values.length == 2) {
                    //update----begin---author:scott----date:20160115----for:昵称转码，签名问题处理----
                    if ("nickname".equals(values[0])) {
                        paramMap.put(values[0], URLDecoder.decode(values[1]));
                    } else {
                        paramMap.put(values[0], values[1]);
                    }
                    //update----begin---author:scott----date:20160115----for:昵称转码，签名问题处理----
                }
            }
        }
        return paramMap;
    }
}

