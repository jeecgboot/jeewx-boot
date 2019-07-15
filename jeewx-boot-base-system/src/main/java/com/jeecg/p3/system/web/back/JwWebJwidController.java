package com.jeecg.p3.system.web.back;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.system.service.JwWebJwidService;
import com.jeecg.p3.system.util.SystemUtil;
import com.jeecg.p3.system.vo.WeixinAccountDto;

 /**
 * 描述：</b>JwWebJwidController<br>微信公众号字典表
 * @author pituo
 * @since：2015年12月21日 16时33分45秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwWebJwid")
public class JwWebJwidController extends BaseController{
  @Autowired
  private JwWebJwidService jwWebJwidService;
  

/**
 * 初始化jwid
 */
@RequestMapping(value="/initJwid",produces="text/plain;charset=UTF-8")
@ResponseBody
public String initJwid(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "userId", required = true) String userId) {
	log.info("初始化公众号");
	String tree = "";
    try {
        //所有可用的权限
        List<WeixinAccountDto> allJwidList = jwWebJwidService.queryJwids();
        
        //当前角色的权限
        List<WeixinAccountDto> userJwidList = jwWebJwidService.queryJwWebJwidByUserId(userId);
       
        tree = SystemUtil.list2TreeWithCheckToJwid(allJwidList,userJwidList);
        log.info("初始化公众号: " + tree);
    }catch (Exception e){
    	log.info(e.getMessage());
    }
    return tree;
}

/**
 * 编辑用户公众号
 */
@RequestMapping(value = "/editUserJwid",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson editUserJwid(HttpServletRequest request, HttpServletResponse response,
		@RequestParam(value = "checkedNodes", required = true) String checkedNodes,
		@RequestParam(value = "userId", required = true) String userId) {
	AjaxJson j = new AjaxJson();
	log.info("编辑用户公众号");
	try {
		List<String> jwids = new ArrayList<String>();
		if(StringUtils.isNotEmpty(checkedNodes)){
			for (String jwid : checkedNodes.split(",")) {
				jwids.add(jwid);
			}
		}
		jwWebJwidService.modifyOperateUserJwidRel(userId, jwids);
		j.setSuccess(true);
		j.setMsg("分配成功");
		log.info("编辑用户公众号完成 ");
	} catch (Exception e) {
		log.info(e.getMessage());
		j.setSuccess(false);
		j.setMsg("分配失败");
	}
	return j;
}

}