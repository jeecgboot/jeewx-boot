package com.jeecg.p3.system.web.back;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.system.def.SystemProperties;
import com.jeecg.p3.system.entity.JwSystemProject;
import com.jeecg.p3.system.entity.JwSystemProjectClassify;
import com.jeecg.p3.system.service.JwSystemProjectClassifyService;
import com.jeecg.p3.system.service.JwSystemProjectService;
import com.jeecg.p3.system.util.ContextHolderUtils;
import com.jeecg.p3.system.util.PropertiesUtil;
import net.sf.json.JSONArray;
 /**
 * 描述：</b>JwSystemProjectController<br>活动项目管理表
 * @author pituo
 * @since：2015年12月21日 17时49分18秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/system/back/jwSystemProject")
public class JwSystemProjectController extends BaseController{
  public final static Logger logger = LoggerFactory.getLogger(JwSystemProjectController.class);
  @Autowired
  private JwSystemProjectService jwSystemProjectService;
  @Autowired
  private JwSystemProjectClassifyService jwSystemProjectClassifyService;
  /** 上传图片根路径 */
  @Value("${jeewx.path.upload}")
  private String upLoadPath;

/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute JwSystemProject query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<JwSystemProject> pageQuery = new PageQuery<JwSystemProject>();
	 	List<JwSystemProjectClassify> jwSystemProjectClassifys = jwSystemProjectClassifyService.queryListByAll();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("jwSystemProject",query);
		//author：sunkai--date：2018-10-12--for:读取本地配置文件的oAuthDomain
		//获取项目本地配置文件的绝对路径
		String oAuthDomain = oConvertUtils.getString(SystemProperties.domain);
		velocityContext.put("domain",oAuthDomain);

		//logger.debug("-----getPorjectPath()-------------"+getPorjectPath());
		logger.debug("-----oAuthDomain-------------"+oAuthDomain);
		//author：sunkai--date：2018-10-12--for:读取本地配置文件的oAuthDomain
		velocityContext.put("jwSystemProjectClassifys",jwSystemProjectClassifys);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(jwSystemProjectService.queryPageList(pageQuery)));
		String viewName = "system/back/jwSystemProject-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void jwSystemProjectDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "system/back/jwSystemProject-detail.vm";
		JwSystemProject jwSystemProject = jwSystemProjectService.queryById(id);
		velocityContext.put("jwSystemProject",jwSystemProject);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "system/back/jwSystemProject-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute JwSystemProject jwSystemProject){
	AjaxJson j = new AjaxJson();
	try {
		//项目编码验重
		Boolean repeat=jwSystemProjectService.validReat(jwSystemProject.getCode(),jwSystemProject.getId());	
		if(repeat){
			j.setSuccess(false);
			j.setMsg("项目编码重复，请重新设置");
		}else{
			jwSystemProject.setId(UUID.randomUUID().toString().replace("-", ""));
			if(jwSystemProject.getScType()!=null&&2==jwSystemProject.getScType()){
				jwSystemProject.setHdurl("${domain}/linksucai/link.do?linkid="+jwSystemProject.getId());
			}
			jwSystemProjectService.doAdd(jwSystemProject);
			j.setMsg("保存成功");
		}		
	} catch (Exception e) {
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
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 JwSystemProject jwSystemProject = jwSystemProjectService.queryById(id);
		 if(StringUtils.isNotEmpty(jwSystemProject.getProjectClassifyId())){
			 JwSystemProjectClassify jwSystemProjectClassify = jwSystemProjectClassifyService.queryById(jwSystemProject.getProjectClassifyId());
			 velocityContext.put("jwSystemProjectClassify",jwSystemProjectClassify);
		 }
		 velocityContext.put("jwSystemProject",jwSystemProject);
		 String viewName = "system/back/jwSystemProject-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute JwSystemProject jwSystemProject){
	AjaxJson j = new AjaxJson();
	try {
		//项目编码验重
		Boolean repeat=jwSystemProjectService.validReat(jwSystemProject.getCode(),jwSystemProject.getId());	
		if(repeat){
			j.setSuccess(false);
			j.setMsg("项目编码重复，请重新设置");
		}else{
			if(jwSystemProject.getScType()!=null&&2==jwSystemProject.getScType()){
				jwSystemProject.setHdurl("${domain}/linksucai/link.do?linkid="+jwSystemProject.getId());
			}
			jwSystemProjectService.doEdit(jwSystemProject);
			j.setMsg("编辑成功");
		}
	} catch (Exception e) {
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
			jwSystemProjectService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}
/**
 * 保存图片
 * @return
 */
@RequestMapping(value = "/doUpload",method ={RequestMethod.POST})
@ResponseBody
public AjaxJson doUpload(MultipartHttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		MultipartFile uploadify = request.getFile("file");
        byte[] bytes = uploadify.getBytes();  
        //String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/system");

		String uploadDir = upLoadPath + "/upload/img/system";
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {
            dirPath.mkdirs();  
        }  
        String sep = System.getProperty("file.separator");  
        File uploadedFile = new File(uploadDir + sep  
                + uploadify.getOriginalFilename());  
        FileCopyUtils.copy(bytes, uploadedFile);  
        //update-begin--Author:zhangweijian  Date: 20181205 for：返回文件名
        j.setObj(uploadify.getOriginalFilename());
        //update-end--Author:zhangweijian  Date: 20181205 for：返回文件名
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 取得项目展示列表
 * 
 * @return
 * @throws Exception
 */
@RequestMapping(value = "/getProjList", method = { RequestMethod.GET,
		RequestMethod.POST })
@ResponseBody
public AjaxJson recieveState(
		HttpServletRequest request) {
	AjaxJson j = new AjaxJson();
	try {
		PageQuery<JwSystemProject> pageQuery = new PageQuery<JwSystemProject>();
		JwSystemProject query = new JwSystemProject();
	 	pageQuery.setPageNo(1);
	 	pageQuery.setPageSize(100);
	 	pageQuery.setQuery(query);
	 	PageList<JwSystemProject> projectList = jwSystemProjectService.queryPageList(pageQuery);
		//将List转换成JSON存储
		JSONArray result = new JSONArray();
		if(projectList!=null && projectList.getValues()!=null && projectList.getValues().size()> 0){
			List<JwSystemProject> pList = projectList.getValues();
			for(int i=0;i<pList.size();i++){
    			JSONObject jsonParts = new JSONObject();
    			jsonParts.put("name", pList.get(i).getName());
    			jsonParts.put("bjurl", pList.get(i).getBjurl());
    			jsonParts.put("img", pList.get(i).getImg());
    			result.add(jsonParts);	
    		}
		}
		Map<String,Object> attrs = new HashMap<String, Object>();
		attrs.put("projectList", result);
		j.setAttributes(attrs);
		j.setSuccess(true);
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("访问异常！");
		log.error("recieveState error:{}", e.getMessage());
	}
	return j;
}
@RequestMapping(value="toIndex",method = {RequestMethod.GET,RequestMethod.POST})
public void toIndex(HttpServletRequest request,HttpServletResponse response) throws Exception{
	VelocityContext velocityContext = new VelocityContext();
	//分类数据
	List<JwSystemProjectClassify> jwSystemProjectClassifys = jwSystemProjectClassifyService.queryAllByType("1");
	velocityContext.put("jwSystemProjectClassifys", jwSystemProjectClassifys);
	//全部数据
 	velocityContext.put("jwSystemProjectRecommend", jwSystemProjectService.queryListByType("1"));
 	velocityContext.put("jwSystemProject", jwSystemProjectService.queryListByType("2"));
	String viewName="system/back/newhome.vm";
	ViewVelocity.view(request, response, viewName, velocityContext);
}
@RequestMapping(value="getRecommendDetail",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson getRecommendDetail(HttpServletRequest request){
	AjaxJson j=new AjaxJson();
	try {
		j.setSuccess(true);
		String projectClassifyId = request.getParameter("id");
		List<JwSystemProjectClassify> jwSystemProjectClassifys = jwSystemProjectClassifyService.queryAllByBaseId(projectClassifyId);
		Map<String, Object> param=new HashMap<String, Object>();
		if(jwSystemProjectClassifys==null||jwSystemProjectClassifys.size()<=0){
			List<JwSystemProject> jwSystemProjects = jwSystemProjectService.queryListByProjectClassifyId(projectClassifyId);
			if(jwSystemProjects==null||jwSystemProjects.size()<=0){
				j.setObj(null);
			}else{
				j.setObj(jwSystemProjects);
			}
			param.put("classify", null);
		}else{
			List<JwSystemProject> jwSystemProjects = jwSystemProjectService.queryListByProjectClassifyId(jwSystemProjectClassifys.get(0).getId());
			if(jwSystemProjects==null||jwSystemProjects.size()<=0){
				j.setObj(null);
			}else{
				j.setObj(jwSystemProjects);
			}
			param.put("classify", jwSystemProjectClassifys);
		}
		j.setAttributes(param);
	} catch (Exception e) {
		j.setSuccess(false);
		j.setObj(null);
	}
	return j;
}

@RequestMapping(value="toClassifyDetail",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson toClassifyDetail(HttpServletRequest request){
	AjaxJson j=new AjaxJson();
	try {
		String projectClassifyId = request.getParameter("id");
		List<JwSystemProject> jwSystemProjects = jwSystemProjectService.queryListByProjectClassifyId(projectClassifyId);
		if(jwSystemProjects==null||jwSystemProjects.size()<=0){
			j.setObj(null);
		}else{
			j.setObj(jwSystemProjects);
		}
		j.setSuccess(true);
	} catch (Exception e) {
		j.setSuccess(false);
	}
	return j;
}


@RequestMapping(value="actDetail",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson actDetail(HttpServletRequest request){
	AjaxJson j=new AjaxJson();
	try {
		JwSystemProject jwSystemProject = jwSystemProjectService.queryById(request.getParameter("id"));
		String projectClassifyId = jwSystemProject.getProjectClassifyId();
		if(StringUtils.isNotEmpty(projectClassifyId)){
			JwSystemProjectClassify jwSystemProjectClassify = jwSystemProjectClassifyService.queryById(projectClassifyId);
			if(jwSystemProjectClassify!=null){
				jwSystemProject.setProjectClassifyId(jwSystemProjectClassify.getName());
			}
		}
		j.setObj(jwSystemProject);
	} catch (Exception e) {
		j.setSuccess(false);
	}
	return j;
}
/**
 * 获取分类
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="/getClassifyTree")
@ResponseBody
public AjaxJson getClassifyTree(HttpServletRequest request, HttpServletResponse response) {
	AjaxJson j=new AjaxJson();
    try {
        //所有权限
        List<JwSystemProjectClassify> jwSystemProjectClassifys = jwSystemProjectClassifyService.queryListByAll();
        List<Map<String, String>> list=new ArrayList<Map<String, String>>();
        for(JwSystemProjectClassify jwSystemProjectClassify:jwSystemProjectClassifys){
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("id", jwSystemProjectClassify.getId());
        	if(StringUtils.isEmpty(jwSystemProjectClassify.getBaseId())){
        		map.put("pId", null);
        		map.put("open","true");
        	}else{
        		map.put("pId", jwSystemProjectClassify.getBaseId());
        		map.put("open","false");
        	}
        	map.put("name", jwSystemProjectClassify.getName());
        	list.add(map);
        }
        j.setObj(list);
        j.setSuccess(true);
    }catch (Exception e){
    	j.setSuccess(false);
    }
    return j;
}


/**
 * @功能：修改域名
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="/changeUrl",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson changeUrl(HttpServletRequest request, HttpServletResponse response) {
	AjaxJson j=new AjaxJson();
	try {
		log.info("changeUrl start --------------");
		long start = System.currentTimeMillis();
		//新的oAuthDomain
		String newdomain = request.getParameter("newdomain");
		String olddomain = request.getParameter("olddomain");
		//更新tomcat中的配置文件的oAuthDomain
		PropertiesUtil p2 = new PropertiesUtil("jeewx.properties");
		p2.writeProperty("oAuthDomain", newdomain);
		//=====================================================================================
		//获取项目本地配置文件的绝对路径
		String classPath = getClassPath();
		String realPath = null;
		if(classPath.indexOf("/WEB-INF")!=-1){
			realPath = classPath + "/jeewx.properties";
		}else{
			String projectSrc = JwSystemProjectController.getPorjectPath();
			classPath = classPath.replace("/target", "").replace("/classes", "");
			if(classPath.indexOf("Web")==-1){
				classPath = projectSrc;
			}
			realPath = classPath + "/src/main/resources/jeewx.properties";
		}
        
        logger.info("------realPath------"+realPath);
      //=====================================================================================
        
        //更新p3-web下配置文件的oAuthDomain
        p2.writeProperties(realPath, "oAuthDomain", newdomain);
        //重置项目域名缓存
		SystemProperties.setDomain(newdomain);
		//更新数据库中的域名
		boolean flag = jwSystemProjectService.changeUrl(newdomain,olddomain);
		if(flag){
			//从配置文件读取修改后的oAuthDomain，
			j.setMsg(p2.readValue(realPath, "oAuthDomain"));
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
		}
		log.info("changeUrl time={}ms.",
				new Object[] { System.currentTimeMillis() - start });
	} catch (Exception e) {
		j.setSuccess(false);
		e.printStackTrace();
	}
	return j;
}

/**
 * @功能：变更为本地素材
 * @param request
 * @param response
 * @return
 */
@RequestMapping(value="/changeType",method = {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public AjaxJson changeType(HttpServletRequest request, HttpServletResponse response) {
	AjaxJson j=new AjaxJson();
	try {
		logger.info("changeType start --------------");
		long start = System.currentTimeMillis();
		//获取项目本地配置文件的绝对路径
		boolean flag = jwSystemProjectService.changeType(SystemProperties.domain);
		if(flag){
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
		}
		logger.info("changeType time={}ms.", new Object[] { System.currentTimeMillis() - start });
	} catch (Exception e) {
		j.setSuccess(false);
		e.printStackTrace();
	}
	return j;
}
//update-end--Author:sunkai  Date: 20181011 for：修改配置文件和数据库的相关域名

	/**
	 * 获取项目根目录
	 * 
	 * @return
	 */
	private static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		
		return tempdir;
		
		
//		String path = getClassPath().replace("/classes", "").replace("/WEB-INF", "").replace("/WebRoot", "");
//		if(this.getProjectPath()!=null && !"".equals(this.getProjectPath())){
//			return this.getProjectPath();
//		}
//		String path = CodeResourceUtil.getProject_path()+"/";;
	}
	
	/*
	 * 得到Class目录
	 */
	public static String getClassPath() {
		String path = JwSystemProjectController.class.getResource("/").getPath();
		return path;
	}
	
}

