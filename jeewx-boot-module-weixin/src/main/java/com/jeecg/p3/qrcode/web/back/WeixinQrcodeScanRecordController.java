package com.jeecg.p3.qrcode.web.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.qrcode.entity.WeixinQrcode;
import com.jeecg.p3.qrcode.entity.WeixinQrcodeScanRecord;
import com.jeecg.p3.qrcode.service.WeixinQrcodeScanRecordService;
import com.jeecg.p3.qrcode.service.WeixinQrcodeService;
import com.jeecg.p3.qrcode.util.ExcelUtilOwn;
import com.jeecg.p3.qrcode.web.back.vo.WeixinQrcodeScanRecordVo;

 /**
 * 描述：</b>二维码扫码记录表<br>
 * @author sunkai
 * @since：2018年08月30日 10时28分08秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/qrcode/back/weixinQrcodeScanRecord")
public class WeixinQrcodeScanRecordController extends BaseController{
  public final static Logger log = LoggerFactory.getLogger(WeixinQrcodeScanRecordController.class);
  @Autowired	
  private WeixinQrcodeScanRecordService weixinQrcodeScanRecordService;
  @Autowired
  private WeixinQrcodeService weixinQrcodeService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinQrcodeScanRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinQrcodeScanRecord> pageQuery = new PageQuery<WeixinQrcodeScanRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinQrcodeScanRecord",query);
		String sceneId = request.getParameter("sceneId");
		String sceneStr = request.getParameter("sceneStr");
		String jwid = request.getParameter("jwid");
		velocityContext.put("sceneId",sceneId);
		velocityContext.put("sceneStr",sceneStr);
		velocityContext.put("jwid",jwid);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinQrcodeScanRecordService.queryPageList(pageQuery)));
		String viewName = "qrcode/back/weixinQrcodeScanRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinQrcodeScanRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "qrcode/back/weixinQrcodeScanRecord-detail.vm";
		WeixinQrcodeScanRecord weixinQrcodeScanRecord = weixinQrcodeScanRecordService.queryById(id);
		velocityContext.put("weixinQrcodeScanRecord",weixinQrcodeScanRecord);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "qrcode/back/weixinQrcodeScanRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinQrcodeScanRecord weixinQrcodeScanRecord){
	AjaxJson j = new AjaxJson();
	try {
		weixinQrcodeScanRecordService.doAdd(weixinQrcodeScanRecord);
		j.setMsg("保存成功");
	} catch (Exception e) {
		log.error(e.getMessage());
		j.setSuccess(false);
		j.setMsg("保存失败");
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
		 WeixinQrcodeScanRecord weixinQrcodeScanRecord = weixinQrcodeScanRecordService.queryById(id);
		 velocityContext.put("weixinQrcodeScanRecord",weixinQrcodeScanRecord);
		 String viewName = "qrcode/back/weixinQrcodeScanRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinQrcodeScanRecord weixinQrcodeScanRecord){
	AjaxJson j = new AjaxJson();
	try {
		weixinQrcodeScanRecordService.doEdit(weixinQrcodeScanRecord);
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
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			weixinQrcodeScanRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

@RequestMapping(value="exportExcel",method={RequestMethod.GET})
@ResponseBody
public void exportExcel(@ModelAttribute WeixinQrcode query,HttpServletRequest request,HttpServletResponse response){
	try {
		List<WeixinQrcode> w = null;
		List<WeixinQrcodeScanRecord> values = null;
		WeixinQrcodeScanRecordVo weixinQrcodeScanRecordVo=null;
		List<WeixinQrcodeScanRecordVo> list=new ArrayList<WeixinQrcodeScanRecordVo>();
		String isScanSubscribe = request.getParameter("isScanSubscribe");
		if(null != query.getSceneId() && !query.getSceneId().equals("")){
			w = weixinQrcodeService.queryBySceneId(query.getSceneId().toString(),query.getJwid());
			values = weixinQrcodeScanRecordService.queryByExcel(query.getSceneId().toString(),query.getJwid(),isScanSubscribe);
		}else{
			//TODO	导出字符串二维码扫码记录
		}
		if(null!=w && w.size()>0 && null!=values && values.size()>0){
			WeixinQrcode weixinQrcode = w.get(0);
			Map<String, Map<String, String>> map=new HashMap<String, Map<String, String>>();
			for(WeixinQrcodeScanRecord weixinQrcodeScanRecord:values){
				weixinQrcodeScanRecordVo=new WeixinQrcodeScanRecordVo();
				weixinQrcodeScanRecordVo.setId(weixinQrcodeScanRecord.getId());
				weixinQrcodeScanRecordVo.setActionInfo(weixinQrcode.getActionInfo());
				weixinQrcodeScanRecordVo.setOpenid(weixinQrcodeScanRecord.getOpenid());
				weixinQrcodeScanRecordVo.setSceneId(weixinQrcode.getSceneId().toString());
				if(weixinQrcodeScanRecord.getIsScanSubscribe().equals("0")){
					weixinQrcodeScanRecordVo.setIsScanSubscribe("否");
				}else{
					weixinQrcodeScanRecordVo.setIsScanSubscribe("是");
				}
				weixinQrcodeScanRecordVo.setCreateTime(weixinQrcodeScanRecord.getScanTime());
				list.add(weixinQrcodeScanRecordVo);
			}
			ExcelUtilOwn.exportExcel(request, response,list,WeixinQrcodeScanRecordVo.class, "扫码记录");
		}else{
			//无数据是导出空的excel
			ExcelUtilOwn.exportExcel(request, response,list,WeixinQrcodeScanRecordVo.class, "扫码记录");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}


}

