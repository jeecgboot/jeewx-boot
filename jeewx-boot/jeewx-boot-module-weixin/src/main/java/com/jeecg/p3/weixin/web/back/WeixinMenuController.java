package com.jeecg.p3.weixin.web.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.model.menu.WeixinButton;
import org.jeewx.api.wxmenu.JwMenuAPI;
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

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.commonweixin.util.Constants;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinMenu;
import com.jeecg.p3.weixin.enums.WeixinMenuTypeEnum;
import com.jeecg.p3.weixin.enums.WeixinMsgTypeEnum;
import com.jeecg.p3.weixin.service.WeixinMenuService;
import com.jeecg.p3.weixin.util.WxErrCodeUtil;

import net.sf.json.JSONObject;

 /**
 * 描述：</b>微信菜单表<br>
 * @author weijian.zhang
 * @since：2018年07月12日 13时58分38秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinMenu")
public class WeixinMenuController extends BaseController{

  public final static Logger log = LoggerFactory.getLogger(WeixinMenuController.class);
  @Autowired
  private WeixinMenuService weixinMenuService;
  @Autowired
  private MyJwWebJwidService myJwWebJwidService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinMenu query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		pageSize = 20;//自定义菜单树形结构，默认展示全部
		PageQuery<WeixinMenu> pageQuery = new PageQuery<WeixinMenu>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	//update-begin--Author:zhangweijian  Date: 20180720 for：添加jwid查询条件
	 	String jwid =  request.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
	 	//update-begin--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	//判断是否有权限
		String systemUserid = request.getSession().getAttribute("system_userid").toString();
		//update-begin--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		MyJwWebJwid jw = myJwWebJwidService.queryJwidByJwidAndUserId(jwid,systemUserid);
		//update-end--Author:zhangweijian  Date: 20181008 for：根据jwid和用户id查询公众号信息
		if(jw==null){
	 		query.setJwid("-");
	 	}
	 	//update-end--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	//update-end--Author:zhangweijian  Date: 20180720 for：添加jwid查询条件
		pageQuery.setQuery(query);
		WeixinMenuTypeEnum[] menuTypeEnums=WeixinMenuTypeEnum.values();
		WeixinMsgTypeEnum[] msgTypeEnums=WeixinMsgTypeEnum.values();
		velocityContext.put("menuTypeEnums",menuTypeEnums);
		velocityContext.put("msgTypeEnums",msgTypeEnums);
		velocityContext.put("weixinMenu",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinMenuService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinMenu-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinMenuDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "weixin/back/weixinMenu-detail.vm";
		WeixinMenu weixinMenu = weixinMenuService.queryById(id);
		velocityContext.put("weixinMenu",weixinMenu);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "weixin/back/weixinMenu-add.vm";
	 //获取菜单类型
	 WeixinMenuTypeEnum[] menuTypeEnums=WeixinMenuTypeEnum.values();
	 //获取响应消息类型
	 WeixinMsgTypeEnum[] msgTypeEnums=WeixinMsgTypeEnum.values();
	 velocityContext.put("menuTypeEnums",menuTypeEnums);
	 velocityContext.put("msgTypeEnums",msgTypeEnums);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinMenu weixinMenu,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		//添加创建时间
		weixinMenu.setCreateTime(new Date());
		//1.判断当前级是否存在
		//update-begin--Author:zhangweijian Date:20181017 for：添加jwid参数
		String jwid =  request.getSession().getAttribute("jwid").toString();
		WeixinMenu otherMenu=weixinMenuService.queryByOrders(weixinMenu.getOrders(),jwid);
		//update-end--Author:zhangweijian Date:20181017 for：添加jwid参数
		if(otherMenu!=null){
			j.setSuccess(false);
			j.setMsg("当前菜单位置已存在");
		}else{
			//获取fatherId
			String fatherId= weixinMenuService.getFatherIdByorders(weixinMenu.getOrders(),jwid);
			//2.判断是不是父级
			if(weixinMenu.getOrders().length()>1&&!StringUtils.isEmpty(fatherId)||weixinMenu.getOrders().length()==1){
				//2.1不是父级，有父级，直接添加/是父级，直接添加
				weixinMenu.setFatherId(fatherId);
				//菜单KEY改为时间戳
				weixinMenu.setMenuKey(String.valueOf(System.currentTimeMillis()));
				weixinMenuService.doAdd(weixinMenu);
				j.setSuccess(true);
				j.setMsg("保存成功");
			}else{
				//2.2不是父级，无父级，不能添加
				j.setSuccess(false);
				j.setMsg("当前菜单无上级菜单，请先添加上级菜单");
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
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
		 WeixinMenu weixinMenu = weixinMenuService.queryById(id);
		 WeixinMenuTypeEnum[] menuTypeEnums=WeixinMenuTypeEnum.values();
		 WeixinMsgTypeEnum[] msgTypeEnums=WeixinMsgTypeEnum.values();
		 velocityContext.put("menuTypeEnums",menuTypeEnums);
		 velocityContext.put("msgTypeEnums",msgTypeEnums);
		 velocityContext.put("weixinMenu",weixinMenu);
		 String viewName = "weixin/back/weixinMenu-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinMenu weixinMenu,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	try {
		//添加修改时间
		weixinMenu.setUpdateTime(new Date());
		String updateBy = (String)request.getSession().getAttribute(Constants.SYSTEM_USERID);
		weixinMenu.setUpdateBy(updateBy);
		//查询被编辑菜单的信息
		WeixinMenu oldMenu=weixinMenuService.queryById(weixinMenu.getId());
		//查询子集的个数
		int sonMenu=weixinMenuService.getSonMenuByFatherId(weixinMenu.getId());
		//1.当前级是父级且存在子级
		if(oldMenu.getOrders().length()==1&&sonMenu>0){
			//父级，存在子级
			if(weixinMenu.getOrders().equals(oldMenu.getOrders())){
				//1.1位置未变化，能编辑
				weixinMenuService.doEdit(weixinMenu);
				j.setSuccess(true);
				j.setMsg("编辑成功");
			}else{
				//1.2位置发生变化，不能编辑
				j.setSuccess(false);
				j.setMsg("当前菜单存在下级菜单，不能被编辑");
			}
			
		}else{
		//2.当前级是父级但不存在子级(或者当前级不是父级)
			//update-begin--Author:zhangweijian Date:20181017 for：添加jwid参数
			String jwid =  request.getSession().getAttribute("jwid").toString();
			// 查询新位置上原来是否存在菜单
			WeixinMenu otherMenu=weixinMenuService.queryByOrders(weixinMenu.getOrders(),jwid);
			//update-end--Author:zhangweijian Date:20181017 for：添加jwid参数
			//判断当前级是否存在
			if(otherMenu!=null&&weixinMenu.getId().equals(otherMenu.getId())||otherMenu==null){
				//2.1存在，是本身，能编辑；不存在，能编辑
				String fatherId=weixinMenuService.getFatherIdByorders(weixinMenu.getOrders(),jwid);
				//update-begin--Author:zhangweijian Date:20181017 for：当前菜单位置的父级不存在，请先添加一级菜单
				if(weixinMenu.getOrders().length()>1&&StringUtils.isEmpty(fatherId)){
					j.setSuccess(false);
					j.setMsg("当前菜单位置的父级不存在，请先添加一级菜单");
				}else{
					if(StringUtils.isEmpty(fatherId)){
						fatherId="";
					}
					weixinMenu.setFatherId(fatherId);
					weixinMenuService.doEdit(weixinMenu);
					j.setSuccess(true);
					j.setMsg("编辑成功");
				}
				//update-end--Author:zhangweijian Date:20181017 for：当前菜单位置的父级不存在，请先添加一级菜单
			}else{
				//2.2存在，不是本身，不能编辑
				j.setSuccess(false);
				j.setMsg("当前菜单位置已存在不能被编辑");
			}
		}
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
			//查询被删除的菜单信息
			WeixinMenu oldMenu=weixinMenuService.queryById(id);
			//查询当前菜单的子级个数
			int sonMenu=weixinMenuService.getSonMenuByFatherId(id);
			//1.判断是否为父级
			if(oldMenu.getOrders().length()==1&&sonMenu>0){
				//1.1是父级并且存在子级，不能够被删除
				j.setSuccess(false);
				j.setMsg("当前菜单存在下级菜单，不能删除");
			}else{
				//1.2是父级不存在子级(或者不是父级)
				weixinMenuService.doDelete(id);
				j.setMsg("删除成功");
			}
		} catch (Exception e) {
		    log.error(e.getMessage());
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

	@RequestMapping(value="/doSyncMenu",method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doSyncMenu(HttpServletRequest request){
		AjaxJson j=new AjaxJson();
		//获取jwid
		String jwid =  request.getSession().getAttribute("jwid").toString();
		//根据jwid获取一级菜单
		WeixinMenu queryFristMenu=new WeixinMenu();
		queryFristMenu.setFatherId("");
		queryFristMenu.setJwid(jwid);
		List<WeixinMenu> firstMenus=weixinMenuService.queryMenusByJwid(queryFristMenu);
		//获取token方法替换
		String accessToken =WeiXinHttpUtil.getRedisWeixinToken(jwid);
		if(oConvertUtils.isEmpty(accessToken)){
			j.setSuccess(false);
			j.setMsg("未获取到公众号accessToken");
			return j;
		}
		//判断如果菜单为空的话，则调用删除菜单的接口
		if(firstMenus.size()==0){
			try {
				JwMenuAPI.deleteMenu(accessToken);
				j.setSuccess(true);
				j.setMsg("同步微信菜单成功！");
				return j;
			} catch (WexinReqException e) {
				e.printStackTrace();
				//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
				JSONObject code=JSONObject.fromObject(e.getMessage());
				String errcode=code.getString("errcode");
				j.setSuccess(false);
				//author:sunkai--date:2018-09-26--for:菜单同步错误返回码信息转义
				String msg = WxErrCodeUtil.testErrCode(errcode);
				j.setMsg("微信菜单同步失败！"+msg);
				//author:sunkai--date:2018-09-26--for:菜单同步错误返回码信息转义
				//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
				return j;
			}
		}
		//获取二级菜单
		List<WeixinButton> resultList=new ArrayList<WeixinButton>();
		for(int i=0;i<firstMenus.size();i++){
			WeixinMenu queryChildMenu=new WeixinMenu();
			queryChildMenu.setJwid(jwid);
			queryChildMenu.setFatherId(firstMenus.get(i).getId());
			List<WeixinMenu> childMenus=weixinMenuService.queryMenusByJwid(queryChildMenu);
			if(childMenus.size()==0){
				//组装菜单接口的参数结构体
				resultList.add(combineBtn(firstMenus.get(i)));
			}else{
				//组装一级菜单名称
				WeixinButton wxButton = new WeixinButton();
				wxButton.setName(firstMenus.get(i).getMenuName());
				//组装二级菜单接口的参数结构体
				List<WeixinButton> childlist=new ArrayList<WeixinButton>();
				for(int m=0;m<childMenus.size();m++){
					childlist.add(combineBtn(childMenus.get(m)));
				}
				wxButton.setSub_button(childlist);
				resultList.add(wxButton);
			}
		}
		try {
			JwMenuAPI.createMenu(accessToken, resultList);
			j.setMsg("同步微信菜单成功！");
			j.setSuccess(true);
		} catch (WexinReqException e) {
			//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
			JSONObject code=JSONObject.fromObject(e.getMessage());
			String errcode=code.getString("errcode");
			j.setSuccess(false);
			//author:sunkai--date:2018-09-26--for:菜单同步错误返回码信息转义
			String msg = WxErrCodeUtil.testErrCode(errcode);
			j.setMsg("微信菜单同步失败！"+msg);
			//author:sunkai--date:2018-09-26--for:菜单同步错误返回码信息转义
			//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * @功能：组装菜单接口的参数结构体
	 * @param weixinMenu
	 * @param listBtnSub
	 */
	private WeixinButton combineBtn(WeixinMenu weixinMenu) {
		WeixinButton wxButton=new WeixinButton();
		//网页链接类
		if("view".equals(weixinMenu.getMenuType())){
			wxButton.setName(weixinMenu.getMenuName());
			wxButton.setType(weixinMenu.getMenuType());
			wxButton.setUrl(weixinMenu.getUrl());
		}
		//消息触发类
		if("click".equals(weixinMenu.getMenuType())){
			wxButton.setName(weixinMenu.getMenuName());
			wxButton.setType(weixinMenu.getMenuType());
			wxButton.setKey(weixinMenu.getMenuKey());
		}
		//小程序类
		if("miniprogram".equals(weixinMenu.getMenuType())){
			wxButton.setName(weixinMenu.getMenuName());
			wxButton.setType(weixinMenu.getMenuType());
			wxButton.setUrl(weixinMenu.getUrl());
			wxButton.setAppid(weixinMenu.getMiniprogramAppid());
			wxButton.setPagepath(weixinMenu.getMiniprogramPagepath());
		}
		return wxButton;
	}

}

