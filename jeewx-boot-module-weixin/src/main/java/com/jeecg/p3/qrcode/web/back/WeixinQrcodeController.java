package com.jeecg.p3.qrcode.web.back;

import com.jeecg.p3.baseApi.util.OSSBootUtil;
import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.qrcode.entity.WeixinQrcode;
import com.jeecg.p3.qrcode.service.WeixinQrcodeService;
import com.jeecg.p3.qrcode.service.impl.WeixinQrcodeScanRecordServiceImpl;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.core.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

 /**
 * 描述：</b>二维码表<br>
 * @author sunkai
 * @since：2018年08月30日 10时29分25秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qrcode/back/weixinQrcode")
public class WeixinQrcodeController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(WeixinQrcodeController.class);
  //创建二维码ticket请求
  public static String qrcode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
  //通过ticket换取二维码
  public static String get_qrcode_url =  "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

  @Autowired
  private WeixinQrcodeService weixinQrcodeService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
  @Autowired
  private WeixinQrcodeScanRecordServiceImpl weixinQrcodeScanRecordService;

	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WeixinQrcode query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		 	PageQuery<WeixinQrcode> pageQuery = new PageQuery<WeixinQrcode>();
		 	String jwid =  request.getSession().getAttribute("jwid").toString();
		 	query.setJwid(jwid);
		 	//判断是否有权限
		 	String systemUserid = request.getSession().getAttribute("system_userid").toString();
		 	//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		 	MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
		 	//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		 	if(jw==null){
		 		query.setJwid("-");
		 	}
		 	pageQuery.setPageNo(pageNo);
		 	pageQuery.setPageSize(pageSize);
		 	VelocityContext velocityContext = new VelocityContext();
			pageQuery.setQuery(query);
			velocityContext.put("weixinQrcode",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinQrcodeService.queryPageList(pageQuery)));
			String viewName = "qrcode/back/weixinQrcode-list.vm";
			ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(value="toDetail",method = RequestMethod.GET)
	public void weixinQrcodeDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "qrcode/back/weixinQrcode-detail.vm";
			WeixinQrcode weixinQrcode = weixinQrcodeService.queryById(id);
			velocityContext.put("weixinQrcode",weixinQrcode);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "qrcode/back/weixinQrcode-add.vm";
		 String jwid = ContextHolderUtils.getSession().getAttribute("jwid")
					.toString();
		 velocityContext.put("jwid", jwid);
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WeixinQrcode weixinQrcode){
		AjaxJson j = new AjaxJson();
		Integer sceneId = weixinQrcodeService.getScene(weixinQrcode);
		weixinQrcode.setSceneId(sceneId);
		weixinQrcode.setCreateTime(new Date());
		try {
			weixinQrcodeService.doAdd(weixinQrcode);
			j.setMsg("保存成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}
	
	/**
	 * 生成二维码
	 * @return
	 */
	@RequestMapping(value = "/generateQrcode",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson generateQrcode(WeixinQrcode weixinQrcode){
		AjaxJson j = new AjaxJson();
		j.setSuccess(false);
		//获取token方法
		String accessToken =WeiXinHttpUtil.getRedisWeixinToken(weixinQrcode.getJwid());
		if(oConvertUtils.isEmpty(accessToken)){
			j.setSuccess(false);
			j.setMsg("未获取到公众号accessToken");
			return j;
		}
		 String ticketurl = WeixinUtil.qrcode_ticket_url.replace("ACCESS_TOKEN",accessToken);
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 HashMap<String, String> sceneMap = new HashMap<String, String>();
		 sceneMap.put("scene_id", weixinQrcode.getSceneId().toString());
		 map.put("scene", sceneMap);
		 JSONObject jsonQrcode = new JSONObject();
		 jsonQrcode.put("action_name", weixinQrcode.getActionName());
		 jsonQrcode.put("action_info", JSONObject.fromObject(map));
		 if(weixinQrcode.getActionName().equals("QR_SCENE")){
			 jsonQrcode.put("expire_seconds", weixinQrcode.getExpireSeconds());  //有效期
		 }
		 JSONObject ticketjson  = WeixinUtil.httpRequest(ticketurl, "POST", jsonQrcode.toString());
		 //判断是否执行成功
		 if(!ticketjson.containsKey("errcode")){
			 //取到ticket
			 String ticket = ticketjson.getString("ticket");
			 weixinQrcode.setTicket(ticket);
			 //通过ticket获取图片
			 String qrcodeimgurl =  WeixinUtil.get_qrcode_url.replace("TICKET",ticket);
			 weixinQrcode.setQrcodeUrl(qrcodeimgurl);
			 if(weixinQrcode.getActionName().equals("QR_SCENE")){
				 weixinQrcode.setExpireSeconds(weixinQrcode.getExpireSeconds());
				 Calendar cal = Calendar.getInstance(); 
				 cal.add(Calendar.SECOND,weixinQrcode.getExpireSeconds()); 
				 weixinQrcode.setExpireDate(cal.getTime());
			 }
			 weixinQrcodeService.doEdit(weixinQrcode);
			 j.setObj(qrcodeimgurl);
			 if(weixinQrcode.getExpireDate()!=null){
				 j.setMsg(DateUtils.formatDate(weixinQrcode.getExpireDate(),"yyyy-MM-dd HH:mm:ss"));
			 }
			 j.setSuccess(true);
		 }else{
			 j.setSuccess(false);
		 }
		 return j;
		 
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(value="toEdit",method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 WeixinQrcode weixinQrcode = weixinQrcodeService.queryById(id);
			 velocityContext.put("weixinQrcode",weixinQrcode);
			 String jwid = ContextHolderUtils.getSession().getAttribute("jwid")
						.toString();
			 velocityContext.put("jwid", jwid);
			 String viewName = "qrcode/back/weixinQrcode-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WeixinQrcode weixinQrcode){
		AjaxJson j = new AjaxJson();
		try {
			weixinQrcodeService.doEdit(weixinQrcode);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value="doDelete",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id,@RequestParam(required = true, value = "sceneId" ) String sceneId){
			AjaxJson j = new AjaxJson();
			try {
				weixinQrcodeService.doDelete(id);
				//update--begin--author: zhaofei--date:20191024--------for:添加删除二维码的时候删除对应的扫码记录-
				if (sceneId != null){
					weixinQrcodeScanRecordService.doDeleteScan(sceneId);
				}
				//update--end--author: zhaofei--date:20191024--------for:添加删除二维码的时候删除对应的扫码记录-
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.error(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}
	
	/**
	 * 图片上传
	 * @return
	 */
	@RequestMapping(value = "/doUpload",method ={RequestMethod.POST})
	@ResponseBody
	public AjaxJson doUpload(MultipartHttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try {
			MultipartFile uploadify = request.getFile("file");
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			/*String realFilename=uploadify.getOriginalFilename();
			String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
			String filename=UUID.randomUUID().toString().replace("-", "")+fileExtension;
	        //String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/qrcode/"+jwid);
			String uploadDir = upLoadPath + "/upload/img/qrcode/"+jwid;
	        File dirPath = new File(uploadDir);  
	        if (!dirPath.exists()) {  
	            dirPath.mkdirs();  
	        }  
	        String sep = System.getProperty("file.separator");  
	        OutputStream out = new FileOutputStream(uploadDir + sep+ filename);
			//ImageZipUtil.zipImageFile(uploadify.getInputStream(), uploadedFile, 0, 0, 0.7f);
	        ImgUtil.scale(uploadify.getInputStream(), out, 0.7f);
	        log.info("--------------------上传图片成功------------------------"+uploadDir + sep+ filename);*/
			String filename = OSSBootUtil.upload(uploadify , "upload/img/qrcode/"+jwid);
	        j.setObj(filename);
	        //out.close();
	        j.setSuccess(true);
			j.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}


}

