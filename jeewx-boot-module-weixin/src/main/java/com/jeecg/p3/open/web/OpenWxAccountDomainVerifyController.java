package com.jeecg.p3.open.web;

import com.jeecg.p3.core.annotation.SkipAuth;
import org.dom4j.DocumentException;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.mp.aes.AesException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信第三方平台（公众号开发域名验证方法）
 * 用法： 需要对接新的第三方，需要手工修改请求地址和返回的文本内容
 */
@Controller
@RequestMapping
@SkipAuth
public class OpenWxAccountDomainVerifyController extends BaseController{

    @RequestMapping(value = "/9Vv6f435be.txt")
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String str = "1fde1b450c7f2a727d0fdbed3fe38e2d";
        try {
            this.responseJson(response, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
