package com.jeecg.p3.weixin.web.back;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
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

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.entity.WeixinGzuser;
import com.jeecg.p3.weixin.entity.WeixinTag;
import com.jeecg.p3.weixin.service.WeixinGzuserService;
import com.jeecg.p3.weixin.service.WeixinTagService;
import com.jeecg.p3.weixin.util.WeixinUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 /**
 * 描述：</b>粉丝标签表<br>
 * @author weijian.zhang
 * @since：2018年08月13日 14时53分22秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/weixin/back/weixinTag")
public class WeixinTagController extends BaseController{

	@Autowired
	private MyJwWebJwidService myJwWebJwidService;
	
  public final static Logger log = LoggerFactory.getLogger(WeixinTagController.class);
  @Autowired
  private WeixinTagService weixinTagService;
  @Autowired
  private WeixinGzuserService weixinGzuserService;
  //创建标签
  public final static String create_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
  //编辑标签
  public final static String update_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
  //删除标签
  public final static String delete_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";
  //获取公众号已创建的标签
  public final static String get_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
  //批量为用户打标签
  public final static String batchtagging_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
  //获取用户标签列表
  public final static String getidlist_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";
  //批量取消用户标签
  public final static String batchuntagging_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";
  
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WeixinTag query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WeixinTag> pageQuery = new PageQuery<WeixinTag>();
	 	//update-begin--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
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
	 	//update-end--Author:zhangweijian  Date: 20180928 for：无权限不能查看公众号数据
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("weixinTag",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(weixinTagService.queryPageList(pageQuery)));
		String viewName = "weixin/back/weixinTag-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void weixinTagDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "weixin/back/weixinTag-detail.vm";
		WeixinTag weixinTag = weixinTagService.queryById(id);
		velocityContext.put("weixinTag",weixinTag);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "weixin/back/weixinTag-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WeixinTag weixinTag,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
	JSONObject jsonObj=null;
	try {
		//获取accessToken
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		//调用创建标签接口
		String requestUrl=create_tag_url.replace("ACCESS_TOKEN", accessToken);
		String name="{\"tag\":{\"name\":\""+weixinTag.getName()+"\"}}";
		jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
		if(jsonObj.containsKey("tag")){
			JSONObject obj=jsonObj.getJSONObject("tag");
			//成功则将创建的标签添加到标签表
			weixinTag.setTagid(obj.getString("id"));
			weixinTag.setName(obj.getString("name"));
			weixinTag.setAddtime(new Date());
			weixinTagService.doAdd(weixinTag);
			j.setMsg("标签创建成功！");
			j.setSuccess(true);
		}else{
			if(jsonObj.getString("errcode").equals("45157")){
				j.setMsg("标签已存在，请勿重复创建！");
				j.setSuccess(false);
			}else{
				String errcode=jsonObj.getString("errcode");
				j.setMsg("标签创建失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
				j.setSuccess(false);
			}
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		String errcode=jsonObj.getString("errcode");
		j.setSuccess(false);
		j.setMsg("标签创建失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
		//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
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
		 WeixinTag weixinTag = weixinTagService.queryById(id);
		 velocityContext.put("weixinTag",weixinTag);
		 String viewName = "weixin/back/weixinTag-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WeixinTag weixinTag,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
	JSONObject jsonObj=null;
	try {
		//获取accessToken
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		//调用编辑标签接口
		String requestUrl=update_tag_url.replace("ACCESS_TOKEN", accessToken);
		String name="{\"tag\":{\"id\":\""+weixinTag.getTagid()+"\",\"name\":\""+weixinTag.getName()+"\"}}";
		jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
		//接口返回信息处理
		j=returnJson(jsonObj);
		//成功则将编辑的标签信息更新到标签表
		if(j.isSuccess()==true){
			weixinTagService.doEdit(weixinTag);
			j.setMsg("标签编辑成功！");
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		String errcode=jsonObj.getString("errcode");
		j.setSuccess(false);
		j.setMsg("标签编辑失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
	}
	//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
		JSONObject jsonObj=null;
		try {
			WeixinTag weixinTag=weixinTagService.queryById(id);
			//获取accessToken
			String jwid=request.getSession().getAttribute("jwid").toString();
			String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
			//调用删除标签接口
			String requestUrl=delete_tag_url.replace("ACCESS_TOKEN", accessToken);
			String name="{\"tag\":{\"id\":\""+weixinTag.getTagid()+"\"}}";
			jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
			//接口返回信息处理
			j=returnJson(jsonObj);
			//成功则删除数据库的标签
			if(j.isSuccess()==true){
				weixinTagService.doDelete(id);
				j.setMsg("标签删除成功");
			}
		} catch (Exception e) {
		    log.error(e.getMessage());
			String errcode=jsonObj.getString("errcode");
			j.setSuccess(false);
			j.setMsg("标签删除失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
		}
		//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
		return j;
}

/**
 * @功能：同步标签
 * @param response
 * @param request
 * @return
 */
@RequestMapping(value="syncTags",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson syncTags(HttpServletResponse response,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
	JSONObject jsonObj=null;
	try {
		//获取accessToken
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		//调用获取公众号标签接口
		String requestUrl=get_tag_url.replace("ACCESS_TOKEN", accessToken);
		jsonObj=WeixinUtil.httpRequest(requestUrl,"GET","");
		JSONArray Obj=(JSONArray) jsonObj.get("tags");
		//将获取公众号已创建的标签插入标签表
		if(Obj.size()>0){
			//根据jwid清空该公众号创建的标签
			weixinTagService.deleteTagsByJwid(jwid);
			for(int i=0;i<Obj.size();i++){
				JSONObject tag=(JSONObject)Obj.get(i);
				WeixinTag weixinTag=new WeixinTag();
				weixinTag.setJwid(jwid);
				weixinTag.setTagid(tag.getString("id"));
				weixinTag.setName(tag.getString("name"));
				weixinTag.setAddtime(new Date());
				weixinTag.setSynctime(new Date());
				weixinTagService.doAdd(weixinTag);
				j.setMsg("标签同步成功");
				j.setSuccess(true);
			}
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		String errcode=jsonObj.getString("errcode");
		j.setSuccess(false);
		j.setMsg("标签同步失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
	}
	//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
	return j;
}

/**
 * @功能：批量为粉丝打标签
 * @param response
 * @param request
 * @param tagid
 * @param openids
 * @return
 */
@RequestMapping(value="batchtagging",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson batchtagging(HttpServletResponse response,HttpServletRequest request,
		@RequestParam String tagid,@RequestParam String openids){
	AjaxJson j = new AjaxJson();
	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
	JSONObject jsonObj=null;
	try {
		//获取accessToken
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		String requestUrl=batchtagging_tag_url.replace("ACCESS_TOKEN", accessToken);
		//调用批量为用户打标签接口
		String[] openidList=openids.split(",");
		String openList="";
		for(int i=0;i<openidList.length;i++){
			if(i==openidList.length-1){
				openList+='"'+openidList[i]+'"';
			}else{
				openList+='"'+openidList[i]+'"'+",";
			}
		}
		String name="{\"openid_list\":["+openList+"],\"tagid\":"+tagid+"}";
		jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
		//接口返回信息处理
		j=returnJson(jsonObj);
		//将标签同步到本地数据库当中
		if(j.isSuccess()==true){
			for(int i=0;i<openidList.length;i++){
				WeixinGzuser gzUser=weixinGzuserService.queryByOpenId(openidList[i],jwid);
				//判断用户是否有无标签
				if(StringUtils.isEmpty(gzUser.getTagidList())){
					if(i==openidList.length-1){
						gzUser.setTagidList(tagid);
					}else{
						gzUser.setTagidList(tagid+",");
					}
				}else{
					String[] tags=gzUser.getTagidList().split(",");
					boolean flag=false;
					for(int t=0;t<tags.length;t++){
						//判断用户是否存在当前标签，存在不处理，不存在则添加
						if(!tagid.equals(tags[t])){
							flag=true;
						}else{
							flag=false;
							break;
						}
					}
					if(flag){
						gzUser.setTagidList(gzUser.getTagidList()+","+tagid);
					}
				}
				weixinGzuserService.doEdit(gzUser);
			}
			j.setMsg("批量打标签成功");
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		String errcode=jsonObj.getString("errcode");
		j.setSuccess(false);
		j.setMsg("批量打标签失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
	}
	//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
	return j;
}

/**
 * @功能：获取用户身上的标签列表
 * @param response
 * @param request
 * @param tagid
 * @return
 */
@RequestMapping(value="getTags",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson getTags(HttpServletResponse response,HttpServletRequest request,@RequestParam String openid){
	AjaxJson j=new AjaxJson();
	try {
		//获取用户身上的标签列表
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		String requestUrl=getidlist_tag_url.replace("ACCESS_TOKEN", accessToken);
		String name="{\"openid\":\""+openid+"\"}";
		JSONObject jsonObj=WeixinUtil.httpRequest(requestUrl,"POST",name);
		j.setObj(jsonObj);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return j;
}

/**
 * @功能：用户添加标签，取消标签
 * @param response
 * @param request
 * @param tags
 * @return
 */
@RequestMapping(value="addTags",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson addTags(HttpServletResponse response,HttpServletRequest request,
		@RequestParam String tags,@RequestParam String openid){
	AjaxJson j=new AjaxJson();
	//update-begin--Author:zhangweijian  Date: 20180903 for：提示信息优化
	JSONObject jsonObj=null;
	try {
		//获取accessToken
		String jwid=request.getSession().getAttribute("jwid").toString();
		String accessToken=WeiXinHttpUtil.getRedisWeixinToken(jwid);
		//调用用户新增或取消标签接口
		String requestchUrl=batchtagging_tag_url.replace("ACCESS_TOKEN", accessToken);
		String requestchunUrl=batchuntagging_tag_url.replace("ACCESS_TOKEN", accessToken);
		String[] newTag=tags.split(",");
		WeixinGzuser gzUser=weixinGzuserService.queryByOpenId(openid, jwid);
		//用户原来没有标签，直接新增
		if(StringUtils.isEmpty(gzUser.getTagidList())){
			for(int i=0;i<newTag.length;i++){
				String name="{\"openid_list\":[\""+openid+"\"],\"tagid\":"+newTag[i]+"}";
				jsonObj=WeixinUtil.httpRequest(requestchUrl,"POST",name);
			}
			gzUser.setTagidList(tags.substring(0,tags.length()-1));
			weixinGzuserService.doEdit(gzUser);
			j.setMsg("打标签成功！");
			return j;
		}
		String[] oldTag=gzUser.getTagidList().split(",");
		//获取需要新增的标签数组，需要取消的标签数组
		Map<String,List<String>> Tags=getTags(newTag,oldTag);
		List<String> newTagOther = Tags.get("newTagOther");//新增的标签数组
		List<String> oldTagOther = Tags.get("oldTagOther");//取消的标签数组
		//遍历给用户添加新的标签
		if(newTagOther.size()>0){
			for(int i=0;i<newTagOther.size();i++){
				String name="{\"openid_list\":[\""+openid+"\"],\"tagid\":"+newTagOther.get(i)+"}";
				jsonObj=WeixinUtil.httpRequest(requestchUrl,"POST",name);
			}
		}
		//遍历删除用户取消的标签
		if(oldTagOther.size()>0){
			for(int i=0;i<oldTagOther.size();i++){
				String name="{\"openid_list\":[\""+openid+"\"],\"tagid\":"+oldTagOther.get(i)+"}";
				jsonObj=WeixinUtil.httpRequest(requestchunUrl,"POST",name);
			}
		}
		System.out.println(jsonObj);
		j=returnJson(jsonObj);
		//修改用户标签的值
		if(j.isSuccess()==true){
			//去掉最后一个","号
			if(StringUtils.isEmpty(tags)){
				gzUser.setTagidList("");
			}else{
				gzUser.setTagidList(tags.substring(0,tags.length()-1));
			}
			weixinGzuserService.doEdit(gzUser);
			j.setMsg("打标签成功！");
		}
	} catch (Exception e) {
		log.error(e.getMessage());
		String errcode=jsonObj.getString("errcode");
		j.setSuccess(false);
		j.setMsg("打标签失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
	}
	//update-end--Author:zhangweijian  Date: 20180903 for：提示信息优化
	return j;
}

/**
 * @功能：接口返回信息处理
 * @param jsonObj
 * @return 
 */
private AjaxJson returnJson(JSONObject jsonObj) {
	AjaxJson j=new AjaxJson();
	if(jsonObj==null){
		j.setMsg("微信服务器访问异常，请稍候重试。");
		j.setSuccess(false);
		return j;
	}
	if(jsonObj.containsKey("errmsg")&&jsonObj.getString("errmsg").equals("ok")){
		j.setMsg(jsonObj.getString("errmsg"));
		j.setSuccess(true);
		return j;
	}
	if(jsonObj.containsKey("errmsg")&&!jsonObj.getString("errmsg").equals("ok")){
		j.setMsg(jsonObj.getString("errmsg"));
		j.setSuccess(false);
		return j;
	}else{
		j.setObj(jsonObj);
		j.setSuccess(true);
		return j;
	}
}

/**
 * @功能：获取新增或取消的用户标签数组
 * @param newTag
 * @param oldTags
 * @return
 */
public static Map<String,List<String>> getTags(String[] newTag,String[] oldTags){
	Map<String,List<String>> Tans = new HashMap<String,List<String>>();
	List<String> same = new ArrayList<String>();
	List<String> newTagOther = new ArrayList<String>();//获取新增的标签元素
	List<String> oldTagOther= new ArrayList<String>();//获取取消的标签元素
	Map<String,Integer> map = new HashMap<String,Integer>();
	for (String i : newTag) {
		map.put(i,1);
		newTagOther.add(i);
	}
	for (String i : oldTags) {
		if(map.get(i)==null){
			oldTagOther.add(i);
			newTagOther.remove(i);
		}else if(map.get(i)==1){
			same.add(i);
			newTagOther.remove(i);
		}
	}
	Tans.put("same", same);
	Tans.put("newTagOther", newTagOther);
	Tans.put("oldTagOther", oldTagOther);
	return Tans;
}

}

