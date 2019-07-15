package com.jeecg.p3.system.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import weixin.popular.util.XMLConverUtil;
//
//import com.unisk.weixin.js.common.base.MyException;
//import com.unisk.weixin.js.pay.bean.pack.Sendpack;


@SuppressWarnings("deprecation")
public class HttpUtils {
	private static final Logger  log =LoggerFactory.getLogger(HttpUtils.class);
/**
    * post提交json数据
    * @param url
    * @param jsonStr
    * @return
    */
   public  static String doPostJson(String url, String jsonStr) {
       String result = null;
       HttpPost post = new HttpPost(url);

       try {
    	   CloseableHttpClient client = HttpClients.createDefault();
    	   RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
    	   post.setConfig(requestConfig);
           post.addHeader("content-type", "application/json");
           StringEntity myEntity = new StringEntity(jsonStr,"UTF-8");
           post.setEntity(myEntity);
          
           HttpResponse response = client.execute(post);
           System.out.println(response.getStatusLine().getStatusCode());
           HttpEntity resEntity = response.getEntity();
           if (resEntity != null) {
//        	   String respBody = new String(EntityUtils.toString(resEntity).getBytes("ISO_8859_1"),"GBK"); 
//               String respBody = new String(EntityUtils.toString(resEntity,"UTF-8"));
        	   String respBody = new String(EntityUtils.toString(resEntity));
               try {
                   result = respBody;
                   System.out.println(result);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           post.releaseConnection();
       }
       return result;
   }
   
   /** 
    * HttpClient连接SSL 
    */  
   public void ssl(String url) {  
       CloseableHttpClient httpclient = null;  
       try {  
           KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
           FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));  
           try {  
               // 加载keyStore d:\\tomcat.keystore    
               trustStore.load(instream, "123456".toCharArray());  
           } catch (CertificateException e) {  
               e.printStackTrace();  
           } finally {  
               try {  
                   instream.close();  
               } catch (Exception ignore) {  
            	   ignore.printStackTrace();
               }  
           }  
           // 相信自己的CA和所有自签名的证书  
           SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();  
           // 只允许使用TLSv1协议  
           SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
                   SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
           httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
           // 创建http请求(get方式)  
           HttpGet httpget = new HttpGet(url);  
    	   log.debug("executing request" + httpget.getRequestLine());  
           CloseableHttpResponse response = httpclient.execute(httpget);  
           try {  
               HttpEntity entity = response.getEntity();  
               System.out.println("----------------------------------------");  
               System.out.println(response.getStatusLine());  
               if (entity != null) {  
            	   log.debug("Response content length: " + entity.getContentLength());  
            	   log.debug(EntityUtils.toString(entity));  
                   EntityUtils.consume(entity);  
               }  
           } finally {  
               response.close();  
           }  
       } catch (ParseException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       } catch (KeyManagementException e) {  
           e.printStackTrace();  
       } catch (NoSuchAlgorithmException e) {  
           e.printStackTrace();  
       } catch (KeyStoreException e) {  
           e.printStackTrace();  
       } finally {  
           if (httpclient != null) {  
               try {  
                   httpclient.close();  
               } catch (IOException e) {  
                   e.printStackTrace();  
               }  
           }  
       }  
   }  
 
   /** 
    * post方式提交表单（模拟用户登录请求） 
    */  
   //BasicNameValuePair
   public static String postForm(String url,List<NameValuePair> formparams) {  
       // 创建默认的httpClient实例.    
       CloseableHttpClient httpclient = HttpClients.createDefault();  
       // 创建httppost    
       HttpPost httppost = new HttpPost(url);  
       RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
       httppost.setConfig(requestConfig);
       // 创建参数队列    
       //List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
       //formparams.add(new BasicNameValuePair("username", "admin"));  
       //formparams.add(new BasicNameValuePair("password", "123456"));  
       UrlEncodedFormEntity uefEntity;  
       try {  
           uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
           httppost.setEntity(uefEntity);  
    	   log.debug("executing request " + httppost.getURI());  
           CloseableHttpResponse response = httpclient.execute(httppost);  
           try {  
               HttpEntity entity = response.getEntity();  
               if (entity != null) {  
            	   String result = EntityUtils.toString(entity, "UTF-8");
            	   log.debug("--------------------------------------");  
            	   log.debug("Response content: " + result);  
            	   log.debug("--------------------------------------");
                   return result;
               }  
           } finally {  
               response.close();  
           }  
       } catch (ClientProtocolException e) {  
           e.printStackTrace();  
       } catch (UnsupportedEncodingException e1) {  
           e1.printStackTrace();
       } catch (IOException e) {  
           e.printStackTrace(); 
       } finally {  
           // 关闭连接,释放资源    
           try {  
               httpclient.close();  
           } catch (IOException e) {  
               e.printStackTrace();  
           }  
       }
	return null;  
   }  
 
   /** 
    * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
 * @return 
    */  
   public static String post(String url,HashMap<String, String> map ) {  
	   String result ="";
       // 创建默认的httpClient实例.    
       CloseableHttpClient httpclient = HttpClients.createDefault();  
       // 创建httppost    
       HttpPost httppost = new HttpPost(url);  
       //设置请求和传输超时时间
       RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
       httppost.setConfig(requestConfig);
       // 创建参数队列    
       List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
       Iterator<String> it =map.keySet().iterator();
       while(it.hasNext()){
    	   Object key=it.next();
    	   formparams.add(new BasicNameValuePair(key.toString(), map.get(key)));  
       }
       UrlEncodedFormEntity uefEntity;  
       try {  
           uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
           httppost.setEntity(uefEntity);  
 //          System.out.println("executing request " + httppost.getURI());  
           CloseableHttpResponse response = httpclient.execute(httppost);  
           try {  
               HttpEntity entity = response.getEntity();  
               if (entity != null) {  
//                   System.out.println("--------------------------------------");  
                   result=EntityUtils.toString(entity, "UTF-8");
                  log.info("Response content: " +result );  
//                   System.out.println("--------------------------------------");  
               }  
           } finally {  
               response.close();  
           }  
       } catch (ClientProtocolException e) {  
           e.printStackTrace();  
       } catch (UnsupportedEncodingException e1) {  
           e1.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       } finally {  
           // 关闭连接,释放资源    
           try {  
               httpclient.close();  
           } catch (IOException e) {  
               e.printStackTrace();  
           }  
       }
       return result;
   }  
 
   /** 
    * 发送 get请求 
 * @return 
    */  
   public static String get(String url) {  
	   String result=null;
       CloseableHttpClient httpclient = HttpClients.createDefault();  
       try {  
           // 创建httpget.    
           HttpGet httpget = new HttpGet(url);  
           //设置请求和传输超时时间
           RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();
           httpget.setConfig(requestConfig);
    	   log.debug("executing request " + httpget.getURI());  
           // 执行get请求.    
           CloseableHttpResponse response = httpclient.execute(httpget);  
           try {  
               // 获取响应实体    
               HttpEntity entity = response.getEntity();  
               System.out.println("--------------------------------------");  
               // 打印响应状态    
               System.out.println(response.getStatusLine());  
               if (entity != null) {  
            	   result=EntityUtils.toString(entity, "UTF-8");
                   log.info("Response content: " +result );  
                   // 打印响应内容长度    
//                   System.out.println("Response content length: " + entity.getContentLength());  
//                   // 打印响应内容    
//                   System.out.println("Response content: " + EntityUtils.toString(entity));  
               }  
               System.out.println("------------------------------------");  
           } finally {  
               response.close();  
           }  
       } catch (ClientProtocolException e) {  
           e.printStackTrace();  
       } catch (ParseException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       } finally {  
           // 关闭连接,释放资源    
           try {  
               httpclient.close();  
           } catch (IOException e) {  
               e.printStackTrace();  
           }  
       }
       return result;
   }  
 
   
 /*
  *  get json数据
    * @param url
  */
	   public static String dogetJson(String urlString){
		   StringBuilder sb = new StringBuilder();
			try {

				URL url = new URL(urlString); 
			HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();
			httpUrlConnection.setRequestMethod("GET"); 
			httpUrlConnection.setConnectTimeout(30000);
			httpUrlConnection.setReadTimeout(30000); 
			httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			httpUrlConnection.connect(); 
			InputStream inStream = httpUrlConnection.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(inStream));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			} 
			br.close();	
		}catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	
   }
}
