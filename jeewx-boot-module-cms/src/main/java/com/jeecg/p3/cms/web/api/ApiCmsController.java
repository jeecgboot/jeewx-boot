package com.jeecg.p3.cms.web.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.jeecg.p3.cms.def.CmsProperties;
import com.jeecg.p3.cms.entity.CmsAd;
import com.jeecg.p3.cms.entity.CmsArticle;
import com.jeecg.p3.cms.entity.CmsMenu;
import com.jeecg.p3.cms.entity.CmsSite;
import com.jeecg.p3.cms.service.CmsAdService;
import com.jeecg.p3.cms.service.CmsArticleService;
import com.jeecg.p3.cms.service.CmsMenuService;
import com.jeecg.p3.cms.service.CmsSiteService;


@Controller
@RequestMapping("/api/cms")
@SkipAuth(auth=SkipPerm.SKIP_SIGN)
public class ApiCmsController extends BaseController{
	private final static Logger logger = LoggerFactory.getLogger(ApiCmsController.class);
	
	@Autowired
	private CmsAdService cmsAdService;
	@Autowired
	private CmsArticleService cmsArticleService;
	@Autowired
	private CmsMenuService cmsMenuService;
	@Autowired
	private CmsSiteService cmsSiteService;
	
	/**
	 * 返回栏目数据
	 * @param query
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/menu", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String menu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//根据MainId过滤数据
		String mainId = request.getParameter("mainId");
		String ishref = request.getParameter("ishref");
		List<CmsMenu> list = cmsMenuService.getFirstMenu(mainId, ishref);
		return JSONArray.toJSONString(list);
	}
	
	
	/**
	 * 返回文章数据
	 * URL: http://localhost/P3-Web/api/cms/articles.do?columnId=A01
	 * 
	 * @return
	 */
	@RequestMapping("/articles")
	public @ResponseBody AjaxJson articles(@ModelAttribute CmsMenu query, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "6") int pageSize) throws Exception {
		AjaxJson j = new AjaxJson();
		Map<String,Object> res = new HashMap<String,Object>();
		String columnId = request.getParameter("columnId");
		String ishref = request.getParameter("ishref");
		if(!oConvertUtils.isEmpty(columnId)){
			//分页数据
			PageQuery<CmsArticle> pageQuery = new PageQuery<CmsArticle>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			CmsArticle articleQuery = new CmsArticle();
			pageQuery.setQuery(articleQuery);
			PageList<CmsArticle> list =  cmsArticleService.getPagesAllMenu(pageQuery, columnId, ishref);
			List<CmsMenu> li = cmsMenuService.getChildNode(columnId);
			int count = cmsArticleService.newCount(columnId, ishref);
			int result_count = (int)(count % pageSize);
			res.put("count", result_count);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("results", list.getValues());
			res.put("list", map);
			res.put("li", li);
			j.setObj(res);
			j.setSuccess(true);
			return j;//JSONArray.toJSONString(list.getResults()).toString();
		}else if(!oConvertUtils.isEmpty(query.getId())){
			//分页数据
			CmsArticle cms = cmsArticleService.queryById(query.getId());
			j.setObj(cms);
			return j;//JSONArray.toJSONString(list.getResults()).toString();
		}else{
			res.put("code", "-1");
			res.put("msg", "栏目ID不能为空");
			j.setObj(res);
			j.setSuccess(false);
			return j;
		}
	}
	
	/**
	 * 根据ID返回文章数据
	 * URL: http://localhost/P3-Web/api/cms/queryOneArticles.do?articlesId=4A15730AC99A408D8CEB4142C7831BC5
	 */
	@RequestMapping("/queryOneArticles")
	public @ResponseBody AjaxJson queryOneArticles(String articlesId) {
		AjaxJson j = new AjaxJson();
		try {
			if(oConvertUtils.isNotEmpty(articlesId)) {
				CmsArticle cmsArticle = cmsArticleService.queryById(articlesId);
				j.setObj(cmsArticle);
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * 小程序获取广告位接口API
	 * URL:http://localhost/P3-Web/api/cms/queryAllAdImages.do
	 */
	@RequestMapping("/queryAllAdImages")
	public @ResponseBody AjaxJson queryAllAdImages(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String mainId = request.getParameter("mainId");
			List<CmsAd> ads = cmsAdService.getAll(mainId);
			j.setObj(ads);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * 小程序获取站点信息接口
	 * URL: http://localhost/P3-Web/api/cms/querySiteInfo.do
	 */
	@RequestMapping("/querySiteInfo")
	public @ResponseBody AjaxJson querySiteInfo(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String mainId = request.getParameter("mainId");
			CmsSite cmsSite =  cmsSiteService.queryById(mainId);
			List<CmsSite> list = new ArrayList<CmsSite>();
			list.add(cmsSite);
			j.setObj(list);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("获取站点信息失败！");
		}
		return j;
	}
	/**
	 * 获取网站信息 obj返回一个实体
	 */
	@RequestMapping("/getSiteInfo")
	public @ResponseBody AjaxJson queryMainSite(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			String mainId = request.getParameter("mainId");
			CmsSite cmsSite =  cmsSiteService.queryById(mainId);
			j.setObj(cmsSite);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("获取站点信息失败！");
		}
		return j;
	}

	/**
	 * 获取分享配置
	 * @author taoYan
	 * @since 2018年10月15日
	 */
	@RequestMapping("/getWxShareConfig")
	public @ResponseBody AjaxJson getWxShareConfig(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		try {
			Map<String,Object> attributes = new HashMap<String, Object>();
			String mainId = request.getParameter("mainId");
			String pageUrl = request.getParameter("pageUrl");
			CmsSite cmsSite =  cmsSiteService.queryById(mainId);
			attributes.put("nonceStr", WeiXinHttpUtil.nonceStr);
			attributes.put("timestamp", WeiXinHttpUtil.timestamp);
			attributes.put("jwid", cmsSite.getJwid());
			attributes.put("appId", cmsSite.getAppid());
			attributes.put("mainId", mainId);
			String signature = WeiXinHttpUtil.getRedisSignature(request, cmsSite.getJwid());
			attributes.put("signature",signature);
			
			String articleId = request.getParameter("articlesId");
			if(oConvertUtils.isEmpty(articleId)){
				attributes.put("shareLogo", CmsProperties.domain+"/content/cms/img/sharelogo.png");
				j.setObj(cmsSite);
			}else{
				CmsArticle cmsArticle = cmsArticleService.queryById(articleId);
				if(oConvertUtils.isEmpty(cmsArticle.getImageHref())){
					attributes.put("shareLogo", CmsProperties.domain+"/content/cms/img/sharelogo.png");
				}else{
					attributes.put("shareLogo", CmsProperties.domain+"/upload/img/cms/"+cmsArticle.getImageHref());
				}
				j.setObj(cmsArticle);
			}
			j.setAttributes(attributes);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("获取站点信息失败！");
		}
		return j;
	}
}
