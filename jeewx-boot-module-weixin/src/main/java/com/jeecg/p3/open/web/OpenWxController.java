package com.jeecg.p3.open.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.mp.aes.AesException;
import org.jeewx.api.mp.aes.WXBizMsgCrypt;
import org.jeewx.api.third.JwThirdAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.open.entity.WeixinOpenAccount;
import com.jeecg.p3.open.service.WeixinOpenAccountService;
import com.jeecg.p3.system.service.MyJwWebJwidService;


/**
 * 微信公众账号第三方平台全网发布源码（java）
 * @author： jeewx开源社区
 * @网址：www.jeewx.com
 * @论坛：www.jeecg.org
 * @date 20150801
 */
@Controller
@RequestMapping("/rest/openwx")
@SkipAuth
public class OpenWxController extends BaseController{
	
	@Autowired
	private WeixinOpenAccountService weixinOpenAccountService;
	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
	
	 /**
     * 授权事件接收
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    @RequestMapping(value = "/event/authorize")
    public void acceptAuthorizeEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {
    	 log.info("微信第三方平台---------微信推送Ticket消息10分钟一次-----------"+new Date());
    	 processAuthorizeEvent(request);
         output(response, "success"); // 输出响应的内容。
    }
    
    
    /**
     * 一键授权功能（仅支持公众号）
     * @param request
     * @param response
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    @RequestMapping(value = "/goAuthor")
    public void goAuthor(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {
    	//从数据库获取ticket
    	WeixinOpenAccount  weixinOpenAccount = getWeixinOpenAccount(CommonWeixinProperties.component_appid);
    	try {
			String componentAccessToken = weixinOpenAccount.getComponentAccessToken();
			//预授权码
			String preAuthCode = JwThirdAPI.getPreAuthCode(CommonWeixinProperties.component_appid, componentAccessToken);
			//auth_type:要授权的帐号类型， 1则商户扫码后，手机端仅展示公众号、2表示仅展示小程序，3表示公众号和小程序都展示。
			String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid="+CommonWeixinProperties.component_appid+"&pre_auth_code="+preAuthCode+"&auth_type=1"+"&redirect_uri="+CommonWeixinProperties.domain+"/rest/openwx/authorCallback";
			response.sendRedirect(url);
		} catch (WexinReqException e) {
			e.printStackTrace();
		}
    }
    
    
    @RequestMapping(value = "{appid}/callback")
    public void acceptMessageAndEvent(HttpServletRequest request, HttpServletResponse response) throws IOException, AesException, DocumentException {
        String msgSignature = request.getParameter("msg_signature");
        //LogUtil.info("第三方平台全网发布-------------{appid}/callback-----------验证开始。。。。msg_signature="+msgSignature);
        if (StringUtils.isEmpty(msgSignature))
            return;// 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
 
        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
 
        String xml = sb.toString();
 
        checkWeixinAllNetworkCheck(request,response,xml);
    }
    
    /**
     * 处理授权事件的推送
     * 
     * @param request
     * @throws IOException
     * @throws AesException
     * @throws DocumentException
     */
    public void processAuthorizeEvent(HttpServletRequest request) throws IOException, DocumentException, AesException {
        try {
        	String nonce = request.getParameter("nonce");
            String timestamp = request.getParameter("timestamp");
            String signature = request.getParameter("signature");
            String msgSignature = request.getParameter("msg_signature");
     
            if (!StringUtils.isNotBlank(msgSignature))
                return;// 微信推送给第三方开放平台的消息一定是加过密的，无消息加密无法解密消息
            boolean isValid = checkSignature(CommonWeixinProperties.COMPONENT_TOKEN, signature, timestamp, nonce);
            log.info("第三方平台校验签名时验证是否正确"+isValid);
            if (isValid) {
                StringBuilder sb = new StringBuilder();
                BufferedReader in = request.getReader();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                String xml = sb.toString();
                String encodingAesKey = CommonWeixinProperties.COMPONENT_ENCODINGAESKEY;// 第三方平台组件加密密钥
                WXBizMsgCrypt pc = new WXBizMsgCrypt(CommonWeixinProperties.COMPONENT_TOKEN, encodingAesKey, CommonWeixinProperties.component_appid);
                xml = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
                processAuthorizationEvent(xml);
            }
		} catch (Exception e) {
			e.printStackTrace();
			log.error("processAuthorizeEvent error={}.",new Object[]{e});
		}
    }
    
    /**
     * 保存Ticket
     * @param xml
     */
    void processAuthorizationEvent(String xml){
    	Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			String ticket = rootElt.elementText("ComponentVerifyTicket");
			if(StringUtils.isNotEmpty(ticket)){
				WeixinOpenAccount  entity = getWeixinOpenAccount(CommonWeixinProperties.component_appid);
				if(entity==null){
					entity=new WeixinOpenAccount();
					entity.setTicket(ticket);
					entity.setAppid(CommonWeixinProperties.component_appid);
					entity.setGetTicketTime(new Date());
					weixinOpenAccountService.doAdd(entity);
					log.info("微信第三方添加TICKET成功！TICKET={}.",new Object[]{ticket});
				}else{
					entity.setTicket(ticket);
					entity.setAppid(CommonWeixinProperties.component_appid);
					entity.setGetTicketTime(new Date());
					log.info("===================={}=======================",new Object[]{entity});
					weixinOpenAccountService.doEdit(entity);
					log.info("微信第三方重置TICKET成功！TICKET={}.",new Object[]{ticket});
				}
			}
			
			//----------取消公众号授权事件接收处理--------------------------
			if("unauthorized".equals(rootElt.elementText("InfoType"))){
                String appid = rootElt.elementText("AuthorizerAppid");
                MyJwWebJwid myJwWebJwid = myJwWebJwidService.queryByAppid(appid);
                if(myJwWebJwid != null){
                    //公众号取消授权，设置相应的状态标志
                	myJwWebJwid.setAuthorizationStatus("2");
                	myJwWebJwidService.doEdit(myJwWebJwid);
                    log.info("微信第三方接收公众号取消授权消息，更新公众号成功！名称={}.appid={}",new Object[]{myJwWebJwid.getName(),appid});
                }
            }
			//----------取消公众号授权事件接收处理--------------------------

			
		} catch (DocumentException e) {
			e.printStackTrace();
			log.error("微信第三方重置TICKET失败！");
			e.printStackTrace();
		}
    }
    
    /**
     * 获取授权账号信息
     * @param appid
     * @return
     */
    WeixinOpenAccount getWeixinOpenAccount(String appid){
    	return weixinOpenAccountService.queryOneByAppid(appid);
    }
    
    /**
     * 获取授权的Appid
     * @param xml
     * @return
     */
	String getAuthorizerAppidFromXml(String xml) {
		Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement();
			String toUserName = rootElt.elementText("ToUserName");
			return toUserName;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
   
    
    public void checkWeixinAllNetworkCheck(HttpServletRequest request, HttpServletResponse response,String xml) throws DocumentException, IOException, AesException{
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String msgSignature = request.getParameter("msg_signature");
 
        WXBizMsgCrypt pc = new WXBizMsgCrypt(CommonWeixinProperties.COMPONENT_TOKEN, CommonWeixinProperties.COMPONENT_ENCODINGAESKEY, CommonWeixinProperties.component_appid);
        xml = pc.decryptMsg(msgSignature, timestamp, nonce, xml);
 
        Document doc = DocumentHelper.parseText(xml);
        Element rootElt = doc.getRootElement();
        String msgType = rootElt.elementText("MsgType");
        String toUserName = rootElt.elementText("ToUserName");
        String fromUserName = rootElt.elementText("FromUserName");
 
        if("event".equals(msgType)){
        	 String event = rootElt.elementText("Event");
	         replyEventMessage(request,response,event,toUserName,fromUserName);
        }else if("text".equals(msgType)){
        	 String content = rootElt.elementText("Content");
	         processTextMessage(request,response,content,toUserName,fromUserName);
        }
    }
    
    
    public void replyEventMessage(HttpServletRequest request, HttpServletResponse response, String event, String toUserName, String fromUserName) throws DocumentException, IOException {
        String content = event + "from_callback";
        replyTextMessage(request,response,content,toUserName,fromUserName);
    }
 
    public void processTextMessage(HttpServletRequest request, HttpServletResponse response,String content,String toUserName, String fromUserName) throws IOException, DocumentException{
        if("TESTCOMPONENT_MSG_TYPE_TEXT".equals(content)){
            String returnContent = content+"_callback";
            replyTextMessage(request,response,returnContent,toUserName,fromUserName);
        }else if(StringUtils.startsWithIgnoreCase(content, "QUERY_AUTH_CODE")){
            output(response, "");
            WeixinOpenAccount weixinOpenAccount = this.getWeixinOpenAccount(CommonWeixinProperties.component_appid);
            //接下来客服API再回复一次消息
            replyApiTextMessage(weixinOpenAccount.getComponentAccessToken(),content.split(":")[1],fromUserName);
        }
    }
 
    public void replyApiTextMessage(String componentAccessToken,String authCode, String fromUserName) throws DocumentException, IOException {
        // 得到微信授权成功的消息后，应该立刻进行处理！！相关信息只会在首次授权的时候推送过来
        System.out.println("------step.1----使用客服消息接口回复粉丝----逻辑开始-------------------------");
        try {
        	System.out.println("------step.2----使用客服消息接口回复粉丝------- component_access_token = "+componentAccessToken + "---------authorization_code = "+authCode);
        	net.sf.json.JSONObject authorizationInfoJson = JwThirdAPI.getApiQueryAuthInfo(CommonWeixinProperties.component_appid, authCode, componentAccessToken);
        	System.out.println("------step.3----使用客服消息接口回复粉丝-------------- 获取authorizationInfoJson = "+authorizationInfoJson);
        	net.sf.json.JSONObject infoJson = authorizationInfoJson.getJSONObject("authorization_info");
        	String authorizer_access_token = infoJson.getString("authorizer_access_token");
        	
        	
        	Map<String,Object> obj = new HashMap<String,Object>();
        	Map<String,Object> msgMap = new HashMap<String,Object>();
        	String msg = authCode + "_from_api";
        	msgMap.put("content", msg);
        	
        	obj.put("touser", fromUserName);
        	obj.put("msgtype", "text");
        	obj.put("text", msgMap);
        	JwThirdAPI.sendMessage(obj, authorizer_access_token);
		} catch (WexinReqException e) {
			e.printStackTrace();
		}
    }   
    
    /**
     * 回复微信服务器"文本消息"
     * @param request
     * @param response
     * @param content
     * @param toUserName
     * @param fromUserName
     * @throws DocumentException
     * @throws IOException
     */
    public void replyTextMessage(HttpServletRequest request, HttpServletResponse response, String content, String toUserName, String fromUserName) throws DocumentException, IOException {
        Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+fromUserName+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+toUserName+"]]></FromUserName>");
		sb.append("<CreateTime>"+createTime+"</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA["+content+"]]></Content>");
		sb.append("</xml>");
		String replyMsg = sb.toString();
        
        String returnvaleue = "";
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(CommonWeixinProperties.COMPONENT_TOKEN, CommonWeixinProperties.COMPONENT_ENCODINGAESKEY, CommonWeixinProperties.component_appid);
            returnvaleue = pc.encryptMsg(replyMsg, createTime.toString(), "easemob");
        } catch (AesException e) {
            e.printStackTrace();
        }
        //TODO 暂时去掉第三方回复消息
        output(response, "");
    }
    
    
    public static void main(String[] args) {
    	 Long createTime = Calendar.getInstance().getTimeInMillis() / 1000;
    	 String replyMsg = "LOCATIONfrom_callback";
    	 
         String returnvaleue = "";
         try {
             WXBizMsgCrypt pc = new WXBizMsgCrypt(CommonWeixinProperties.COMPONENT_TOKEN, CommonWeixinProperties.COMPONENT_ENCODINGAESKEY, CommonWeixinProperties.component_appid);
             returnvaleue = pc.encryptMsg(replyMsg, createTime.toString(), "easemob");
             System.out.println(returnvaleue);
         } catch (AesException e) {
             e.printStackTrace();
         }
	}
    /**
     * 工具类：回复微信服务器"文本消息"
     * @param response
     * @param returnvaleue
     */
    public void output(HttpServletResponse response,String returnvaleue){
		try {
			PrintWriter pw = response.getWriter();
			pw.write(returnvaleue);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 判断是否加密
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String token,String signature,String timestamp,String nonce){
        System.out.println("###token:"+token+";signature:"+signature+";timestamp:"+timestamp+"nonce:"+nonce);
    	   boolean flag = false;
    	   if(signature!=null && !signature.equals("") && timestamp!=null && !timestamp.equals("") && nonce!=null && !nonce.equals("")){
    	      String sha1 = "";
    	      String[] ss = new String[] { token, timestamp, nonce }; 
              Arrays.sort(ss);  
              for (String s : ss) {  
               sha1 += s;  
              }  
     
              sha1 = AddSHA1.SHA1(sha1);  
     
              if (sha1.equals(signature)){
        	   flag = true;
              }
    	   }
    	   return flag;
       }
}


class AddSHA1 {
    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5
            byte[] digest = md.digest(inStr.getBytes());       //返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        }
        catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }
    
    
    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";
        
        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            }
            else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
    
}
