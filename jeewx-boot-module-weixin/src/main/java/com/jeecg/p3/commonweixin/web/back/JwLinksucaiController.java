package com.jeecg.p3.commonweixin.web.back;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
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

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.WeixinLinksucai;
import com.jeecg.p3.commonweixin.exception.CommonweixinException;
import com.jeecg.p3.commonweixin.service.impl.WeixinLinksucaiService;

/**
 * 作废了
 * 素材链接管理
 * 
 * @author chen
 * @since：2016年11月14日 10时16分24秒 星期一
 * @version:1.0
 */
@Controller
@RequestMapping("/commonweixin/back/JwLinksucai")
public class JwLinksucaiController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(JwLinksucaiController.class);
	@Autowired
	private WeixinLinksucaiService weixinLinksucaiService;

	/**
	 * 列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public void list(@ModelAttribute WeixinLinksucai query, HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception {
		PageQuery<WeixinLinksucai> pageQuery = new PageQuery<WeixinLinksucai>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("doMain", CommonWeixinProperties.domain);
		velocityContext.put("weixinLinksucai", query);
		velocityContext.put("pageInfos", SystemTools.convertPaginatedList(weixinLinksucaiService.queryPageList(pageQuery)));
		String viewName = "commonweixin/back/weixinLinksucai-list.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "toDetail", method = RequestMethod.GET)
	public void weixinLinksucaiDetail(@RequestParam(required = true, value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonweixin/back/weixinLinksucai-detail.vm";
		WeixinLinksucai weixinLinksucai = weixinLinksucaiService.queryById(id);
		velocityContext.put("weixinLinksucai", weixinLinksucai);
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public void toAddDialog(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonweixin/back/weixinLinksucai-add.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doAdd", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WeixinLinksucai weixinLinksucai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			// update begin Author:huangqingquan date:2016-11-30
			// for:生成素材链接-------------------------------
			String jwid = (String) request.getSession().getAttribute("jwid");
			if (StringUtils.isEmpty(jwid)) {
				throw new CommonweixinException("微信原始ID不能为空，请登录后在操作");
			}
			String link = CommonWeixinProperties.domain + "/weixinLinksucai/link.do?linkid=";
			weixinLinksucai.setInnerLink(link);
			// update end Author:huangqingquan date:2016-11-30
			// for:生成素材链接---------------------------------
			weixinLinksucaiService.doAdd(weixinLinksucai);
			j.setMsg("保存成功");
		} catch (CommonweixinException e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @return,
	 */
	@RequestMapping(value = "toEdit", method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
		VelocityContext velocityContext = new VelocityContext();
		WeixinLinksucai weixinLinksucai = weixinLinksucaiService.queryById(id);
		velocityContext.put("weixinLinksucai", weixinLinksucai);
		String viewName = "commonweixin/back/weixinLinksucai-edit.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doEdit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WeixinLinksucai weixinLinksucai) {
		AjaxJson j = new AjaxJson();
		try {
			weixinLinksucaiService.doEdit(weixinLinksucai);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "doDelete", method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id") String id) {
		AjaxJson j = new AjaxJson();
		try {
			weixinLinksucaiService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

}
