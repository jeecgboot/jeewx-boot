package com.jeecg.p3.weixin.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.jeewx.api.core.common.MyX509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * 公众平台通用接口工具类
* 
 * @author LeeShaoQing
 * @date 20180727
 */
public class WeixinUtil {
	public final static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //客服接口地址
    public static String send_message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    //创建二维码ticket请求
    public static String qrcode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    //通过ticket换取二维码
    public static String get_qrcode_url =  "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    //微信网页授权获取CODE
    public static String web_oauth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    //微信网页授权获取网页accesstoken和OPENID
    public static String web_oauth_accesstoken_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code"; 
    //微信网页授权获取用户信息
    public static String web_oauth_userinfo_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID"; 
	//--update-begin---author:张肖江 ---date:20150321 ---for:验证token是否有效------------------------------------------------------------------------
    //获取微信服务器IP地址
    public static String get_callbackip_url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	//--update-end-----author:张肖江 ---date:20150321 ---for:验证token是否有效------------------------------------------------------------------------
    // jsapi调用接口临时凭证的接口地址（GET） 限200（次/天）
 	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    
    /**
     * 发起https请求并获取结果
     * 
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        HttpsURLConnection httpUrlConn =null;
        OutputStream outputStream =null;
        InputStream inputStream =null;
        InputStreamReader inputStreamReader =null;
        BufferedReader bufferedReader =null;
        try {
                // 创建SSLContext对象，并使用我们指定的信任管理器初始化
                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();

                URL url = new URL(requestUrl);
                httpUrlConn = (HttpsURLConnection) url.openConnection();
                httpUrlConn.setSSLSocketFactory(ssf);

                httpUrlConn.setDoOutput(true);
                httpUrlConn.setDoInput(true);
                httpUrlConn.setUseCaches(false);
			    //设置网络超时
                httpUrlConn.setConnectTimeout(6000);  
                httpUrlConn.setReadTimeout(6000);
                // 设置请求方式（GET/POST）
                httpUrlConn.setRequestMethod(requestMethod);

                if ("GET".equalsIgnoreCase(requestMethod))
                        httpUrlConn.connect();

                // 当有数据需要提交时
                if (null != outputStr) {
                        outputStream = httpUrlConn.getOutputStream();
                        // 注意编码格式，防止中文乱码
                        outputStream.write(outputStr.getBytes("UTF-8"));
                        outputStream.close();
                }

                // 将返回的输入流转换成字符串
                inputStream = httpUrlConn.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);

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
                jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
        	log.info("Weixin server connection timed out.");
        } catch (Exception e) {
        	log.info("https request error:{}"+e.getMessage());
        }finally{
        	try {
				if(inputStreamReader!=null){
					inputStreamReader.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
				if(bufferedReader!=null){
					bufferedReader.close();
				}
				if(outputStream!=null){
					outputStream.close();
				}
				if(httpUrlConn!=null){
					httpUrlConn.disconnect();
				}
			} catch (Exception e) {
			}
        }
        
        return jsonObject;
    }
    
    /** 
     * 编码 
     * @param bstr 
     * @return String 
     */  
    public static String encode(byte[] bstr){  
    	return new sun.misc.BASE64Encoder().encode(bstr);  
    } 

    //update-begin--Author:zhangweijian  Date: 20180802 for：上传多媒体文件
    /**
     * 上传多媒体文件
     * @param fileType
     * @param filePath
     * @param token
     * @return
     */
    public static JSONObject sendMedia(String fileType,String filePath,String token) {
  		String result = null;
  		JSONObject jsonobject = new JSONObject();
  		jsonobject = null;
  		File file = new File(filePath);
  		if(!file.exists()||!file.isFile()){
  			jsonobject = null;
  			log.info("------------文件不存在------------------------");
  		}else{
  			HttpURLConnection con =null;
  			OutputStream out =null;
  			DataInputStream in = null;
  			try{
  				String requestUrl="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+ token + "&type="+fileType;  
  				log.info("------------------requestUr------------"+requestUrl);
  				URL urlObj = new URL(requestUrl);  
  				con = (HttpURLConnection) urlObj.openConnection();  
  				con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
  			    con.setDoInput(true);  
  			    con.setDoOutput(true);  
  			    con.setUseCaches(false); // post方式不能使用缓存  
  			    //--update-begin---author：zhoujf-------date:20170112--------for:设置网络超时时间-------------------------
  			    //设置网络超时
                  con.setConnectTimeout(12000);  
                  con.setReadTimeout(12000);
                  //--update-begin---author：zhoujf-------date:20170112--------for:设置网络超时时间-------------------------
  			    con.setRequestProperty("Connection", "Keep-Alive");// 设置请求头信息
  			    con.setRequestProperty("Charset", "UTF-8");  
  			    String BOUNDARY = "----------" + System.currentTimeMillis();// 设置边界  
  			    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
  			    // 请求正文信息  
  			    // 第一部分：  
  			    StringBuilder sb = new StringBuilder();  
  			    sb.append("--"); // 必须多两道线  
  			    sb.append(BOUNDARY);  
  			    sb.append("\r\n");  
  			    sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");  
  			    sb.append("Content-Type:application/octet-stream\r\n\r\n");  
  			    byte[] head = sb.toString().getBytes("utf-8");  
  			    // 获得输出流  
  			    out = new DataOutputStream(con.getOutputStream());  
  			    // 输出表头  
  			    out.write(head);  
  			    // 文件正文部分  
  			    // 把文件已流文件的方式 推入到url中  
  			    in = new DataInputStream(new FileInputStream(file));  
  			    int bytes = 0;  
  			    byte[] bufferOut = new byte[1024];  
  			    while ((bytes = in.read(bufferOut)) != -1) {  
  			    	out.write(bufferOut, 0, bytes);  
  			    }  
  			    in.close();  
  			    // 结尾部分  
  			    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
  			    out.write(foot);  
  			    out.flush();  
  			    out.close();  
  			    StringBuffer buffer = new StringBuffer();  
  			    BufferedReader reader = null;  
  			    try {  
  			        // 定义BufferedReader输入流来读取URL的响应  
  			        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
  			        String line = null;  
  			        while ((line = reader.readLine()) != null) {  
  			        	buffer.append(line);  
  			        }  
  			        if(result==null){  
  			        	result = buffer.toString();  
  			        }  
  		        } catch (IOException e) {  
  		        	log.info("发送POST请求出现异常！" + e);  
  			        e.printStackTrace();  
  			        throw new IOException("数据读取异常");  
  		        } finally {  
  			        if(reader!=null){  
  			        reader.close();  
  		        }  
  			  }  
  		      jsonobject = JSONObject.fromObject(result);  
  			}catch(Exception e){
  				e.printStackTrace();
  			}finally{
  	        	try {
  	        		if(in!=null){
  	        			in.close();
  					}
  					if(out!=null){
  						out.close();
  					}
  					if(con!=null){
  						con.disconnect();
  					}
  				} catch (Exception e) {
  				}
  	        }
  		}
  		return jsonobject;
  	}
  //update-end--Author:zhangweijian  Date: 20180802 for：上传图文素材到微信
}