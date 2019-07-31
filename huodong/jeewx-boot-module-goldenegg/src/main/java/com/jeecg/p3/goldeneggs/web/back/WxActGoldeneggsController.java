package com.jeecg.p3.goldeneggs.web.back;

import com.jeecg.p3.baseApi.util.WxActReplaceUtil;
import com.jeecg.p3.goldeneggs.def.SystemGoldProperties;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.service.*;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

 /**
 * 描述：</b>WxActGoldeneggsController<br>h5页面彩蛋
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分24秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/goldeneggs/back/wxActGoldeneggs")
public class WxActGoldeneggsController extends BaseController{
  @Autowired
  private WxActGoldeneggsService wxActGoldeneggsService;
  @Autowired
  private WxActGoldeneggsRelationService wxActGoldeneggsRelationService;
  @Autowired
  private WxActGoldeneggsAwardsService wxActGoldeneggsAwardsService;
  @Autowired
  private WxActGoldeneggsPrizesService wxActGoldeneggsPrizesService;
  @Autowired
  private WxActGoldeneggsRegistrationService wxActGoldeneggsRegistrationService;
  private static String defaultJwid = SystemGoldProperties.defaultJwid;
  private static String domain = SystemGoldProperties.domain;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActGoldeneggs query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActGoldeneggs> pageQuery = new PageQuery<WxActGoldeneggs>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		if(defaultJwid.equals(jwid)){
			String createBy= ContextHolderUtils.getSession().getAttribute("system_userid").toString();
			query.setCreateBy(createBy);
		}
		query.setJwid(jwid); 	
		pageQuery.setQuery(query);
		velocityContext.put("wxActGoldeneggs",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActGoldeneggsService.queryPageList(pageQuery)));
		String viewName = "goldeneggs/back/wxActGoldeneggs-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActGoldeneggsDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/back/wxActGoldeneggs-detail.vm";
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(id);
		velocityContext.put("wxActGoldeneggs",wxActGoldeneggs);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString().trim();
	 String createBy ="";
	 createBy= ContextHolderUtils.getSession().getAttribute("system_userid").toString();
	 List<WxActGoldeneggsAwards> awards = wxActGoldeneggsAwardsService.queryAwards(jwid,createBy);//查询奖项的集合
	 velocityContext.put("awards",awards);
	 List<WxActGoldeneggsPrizes> prizes = wxActGoldeneggsPrizesService.queryPrizes(jwid,createBy);//查询奖品的集合
     velocityContext.put("prizes",prizes);
     velocityContext.put("date",new Date().getTime());
     velocityContext.put("jwid",jwid);
	 String viewName = "goldeneggs/back/wxActGoldeneggs-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActGoldeneggs wxActGoldeneggs){
	AjaxJson j = new AjaxJson();
	try {
		//update-being-alex-----Date:2017-2-24----for:替换活动说明中非法代码------
		wxActGoldeneggs.setDescription(WxActReplaceUtil.replace(wxActGoldeneggs.getDescription()));
		//update-end-alex-----Date:2017-2-24----for:替换活动说明中非法代码------
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		if(defaultJwid.equals(jwid)){
			String createBy = ContextHolderUtils.getSession().getAttribute("system_userid").toString();
			wxActGoldeneggs.setCreateBy(createBy);
		}
		wxActGoldeneggs.setJwid(jwid.trim());
		wxActGoldeneggsService.doAdd(wxActGoldeneggs);
		j.setMsg("保存成功");
	} catch (Exception e) {
		e.printStackTrace();
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
		 WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(id);
		 velocityContext.put("wxActGoldeneggs",wxActGoldeneggs);
		 //添加修改概率
		 String	createBy= ContextHolderUtils.getSession().getAttribute("system_userid").toString();
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActGoldeneggsRelation> awarsDetailList=wxActGoldeneggsRelationService.queryByActIdAndJwid(id,jwid);
		//update-begin-zhangweijian-----Date:20180830----for:概率转换
		 //将小数转为整数
		 for(WxActGoldeneggsRelation list:awarsDetailList){
			 BigDecimal probability = list.getProbability();
			 probability=probability.divide(new BigDecimal("0.01"));
			 list.setProbabilitys(probability.stripTrailingZeros().toPlainString());
		 }
		//update-end-zhangweijian-----Date:20180830----for:概率转换
		 velocityContext.put("awarsDetailList",awarsDetailList);
		 List<WxActGoldeneggsAwards> awards = wxActGoldeneggsAwardsService.queryAwards(jwid,createBy);
		 velocityContext.put("awards",awards);
		 List<WxActGoldeneggsPrizes> prizes = wxActGoldeneggsPrizesService.queryPrizes(jwid,createBy);
		 velocityContext.put("prizes",prizes);	
		 Integer count=wxActGoldeneggsRegistrationService.queryCountByActId(id);
		 velocityContext.put("count",count);	
		 String viewName = "goldeneggs/back/wxActGoldeneggs-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActGoldeneggs wxActGoldeneggs){
	AjaxJson j = new AjaxJson();
	try {
		//update-being-alex-----Date:2017-2-24----for:替换活动说明中非法代码------
		wxActGoldeneggs.setDescription(WxActReplaceUtil.replace(wxActGoldeneggs.getDescription()));
		//update-end-alex-----Date:2017-2-24----for:替换活动说明中非法代码------
		String msg = wxActGoldeneggsService.doEdit(wxActGoldeneggs);
		if(msg.equals("")){
			j.setMsg("编辑成功");
		}else{
			j.setMsg(msg);
			j.setSuccess(false);
		}
	} catch (Exception e) {
		e.printStackTrace();
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
			wxActGoldeneggsService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


/**
 * 获取shortUrl
 * @param id
 * @return
 */
@RequestMapping(value="getShortUrl",method = RequestMethod.POST)
@ResponseBody
public AjaxJson getShortUrl(@RequestParam(required = true, value = "id" ) String id){
	AjaxJson j=new AjaxJson();
	try {
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(id);
		String shortUrl = wxActGoldeneggs.getShortUrl();
		if(StringUtils.isEmpty(shortUrl)){
			String hdurl=wxActGoldeneggs.getHdurl();
			hdurl = hdurl.replace("${domain}", domain);
			PropertiesUtil properties=new PropertiesUtil("goldeneggs.properties");
			shortUrl=WeiXinHttpUtil.getShortUrl(hdurl,properties.readProperty("defaultJwid"));
			if(StringUtils.isEmpty(shortUrl)){
				shortUrl=hdurl;
			}else{
				wxActGoldeneggsService.editShortUrl(wxActGoldeneggs.getId(),shortUrl);
			}
		}
		if(StringUtils.isEmpty(shortUrl)){
			j.setMsg("获取地址失败！");
			j.setSuccess(false);
		}else{
			j.setObj(shortUrl);
			j.setSuccess(true);
			j.setMsg("获取地址成功！");
		}
	} catch (Exception e) {
		e.printStackTrace();
		j.setMsg("获取地址失败！");
		j.setSuccess(false);
	}
	return j;
}


}

