/**
 * 文件名：CommonController.java
 * 创建人：liwenhui
 * 日期：2018-3-19 下午04:12:25
 * 修改人：
 * 描述：
 */
package com.jeecg.p3.system.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.jeecg.p3.system.util.DySmsHelper;
import com.jeecg.p3.util.MatrixToImageWriter;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Random;

/**
 * 用途：
 */
@RequestMapping("/CommonController")
@Controller
public class CommonController {
	
	public final static Logger LOG = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * @功能:下载二维码
	 * @作者:liwenhui 
	 * @时间:2018-3-19 下午04:18:59
	 * @修改：
	 * @param url 生成二维码的URL
	 * @param fileName 生成文件的名字
	 * @param response
	 * @throws Exception  
	 */
	@SkipAuth(auth= SkipPerm.SKIP_SIGN)
	@RequestMapping(value = "downloadQRCode", method ={RequestMethod.GET,RequestMethod.POST})
	public void downloadQRCode(@RequestParam(required = true, value = "url") String url,@RequestParam(required=true,value="fileName")String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String text = url;
		int width = 500; // 二维码图片宽度
        int height = 500; // 二维码图片高度
        String format = "jpg";// 二维码的图片格式
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints);
        //文件名
        fileName+=".jpg";
		// 制定浏览器头
		// 在下载的时候这里是英文是没有问题的
		// 如果图片名称是中文需要设置转码
		response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
		OutputStream out = response.getOutputStream();;
		try {
			// 读取文件
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	//update-begin--Author:zhangweijian  Date: 20180821 for：发送手机验证码
	/**
	 * 发送手机验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="sendMsgCode",method = {RequestMethod.GET,RequestMethod.POST})
	public AjaxJson sendMsg(HttpServletRequest request, HttpServletResponse response){
		long start = System.currentTimeMillis();
		AjaxJson j=new AjaxJson();
		try {
			String randCode=request.getParameter("randCode");
			//update-begin--Author:zhangweijian Date:20181009 for：添加图形码的非空判断
			if(StringUtils.isNotEmpty(randCode)){
				HttpSession session = request.getSession();
				Object yzm = session.getAttribute("randCode");
				if(StringUtils.isEmpty(randCode)||yzm==null||!randCode.equals(yzm.toString())){
					j.setSuccess(true);
					j.setObj("codeErr");
					return j;
				}
			}
			//update-end--Author:zhangweijian Date:20181009 for：添加图形码的非空判断
			String relPhone = request.getParameter("relPhone");
			//生成验证码
			Random ne=new Random();//实例化一个random的对象ne
			int code=ne.nextInt(9999-1000+1)+1000;
			String validCode=Integer.toString(code);
			JSONObject json =new JSONObject();
			json.put("code", validCode);
			DySmsHelper.sendSms(relPhone,json);
			request.getSession().setAttribute("yzmCode", validCode);
			LOG.info("sendMsg param:relPhone={}",new Object[] {relPhone});
			j.setSuccess(true);
			j.setObj(validCode);
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("sendMsg error={}",new Object[] {e});
		}
		LOG.info("sendMsg time={}ms.",new Object[] {System.currentTimeMillis() - start});
		return j;
	}
	//update-end--Author:zhangweijian  Date: 20180821 for：发送手机验证码
	
	
	
}
