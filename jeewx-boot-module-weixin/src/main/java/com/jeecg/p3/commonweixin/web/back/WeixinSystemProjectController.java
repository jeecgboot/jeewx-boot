package com.jeecg.p3.commonweixin.web.back;

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
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.commonweixin.def.CommonWeixinProperties;
import com.jeecg.p3.commonweixin.entity.WeixinSystemProject;
import com.jeecg.p3.commonweixin.service.WeixinSystemProjectService;

/**
 * 
 * 批量修改项目的活动链接
 * @author huangqingquan
 *
 */
@Controller
@RequestMapping("/commonweixin/back/weixinSystemProject")
public class WeixinSystemProjectController extends BaseController{
	@Autowired
	private WeixinSystemProjectService weixinSystemProjectService;
	
	/**
	 * 查列表
	 * @param query
	 * @param request
	 * @param response
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void list(@ModelAttribute WeixinSystemProject query,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize)
			throws Exception {
		PageQuery<WeixinSystemProject> pageQuery = new PageQuery<WeixinSystemProject>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("query", query);
		velocityContext.put("pageInfos", SystemTools.convertPaginatedList(weixinSystemProjectService.queryPageList(pageQuery)));
		String viewName = "commonweixin/back/weixinSystemProject-list.vm";
		ViewVelocity.view(request, response, viewName, velocityContext);
	}
	
	/**
	 * 批量替换活动链接
	 * @param weixinSystemProject
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editHdurl", method = { RequestMethod.GET,RequestMethod.POST })
	public AjaxJson editHdurl(@ModelAttribute WeixinSystemProject weixinSystemProject,HttpServletRequest request) {
		AjaxJson j=new AjaxJson();
		try {
			String projectId = request.getParameter("projectId");
			String tableName = request.getParameter("tableName");
			String hdurlName = request.getParameter("hdurlName");
			String jwidName = request.getParameter("jwidName");
			String shortUrlName = request.getParameter("shortUrlName");
			String linksucai = request.getParameter("linksucai");
			weixinSystemProjectService.editHdurl(projectId,tableName, hdurlName, jwidName, shortUrlName, linksucai);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改活动的修改素材链接时出现错误！error={}",e);
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 重置活动的短链接
	 * @param weixinSystemProject
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetShortUrl", method = { RequestMethod.GET,RequestMethod.POST })
	public AjaxJson resetShortUrl(@ModelAttribute WeixinSystemProject weixinSystemProject,HttpServletRequest request) {
		AjaxJson j=new AjaxJson();
		try {
			StringBuffer sb=new StringBuffer();
			sb.append("eshop_main,");//综合商城
			sb.append("eshop_goods,");//综合商城商品
			sb.append("jwshop_goods,");//单品商城
			sb.append("wx_act_bargain,");//微砍价
			sb.append("wx_act_businesshall,");//主题评选
			sb.append("wx_act_commonvote,");//大赛投票
			sb.append("wx_act_countmoney,");//疯狂数钱
			sb.append("wx_act_divination,");//摇签祈福
			sb.append("wx_act_goldeneggs,");//砸金蛋
			sb.append("wx_act_jiugongge,");//九宫格
			sb.append("wx_act_luckyfruit,");//水果机
			sb.append("wx_act_luckyroulette,");//大转盘
			sb.append("wx_act_redpacket,");//抢红包
			sb.append("wx_act_scratchcards,");//刮刮乐
			sb.append("wx_act_shaketicket_home,");//摇一摇
			sb.append("wx_act_yyduobao");//一元夺宝
			final String[] tableNames = sb.toString().split(",");
			Thread t = new Thread(new Runnable() {
				public void run() {
					resetShortUrlsync(tableNames);
				}
			});
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("重置短链接时异常error={}",e);
		}
		return j;
	}
	
	public synchronized void resetShortUrlsync(String[] tableNames){
		long start = System.currentTimeMillis();
		for (String tableName:tableNames) {
			try {
				List<Map<String, String>> paramMaps = weixinSystemProjectService.queryAllActByTableName(tableName);
				for (Map<String, String> param:paramMaps) {
					try {
						String shortUrl = WeiXinHttpUtil.getShortUrl(param.get("hdurl"), CommonWeixinProperties.defaultJwid);
						weixinSystemProjectService.doEditShortByTableName(tableName, param.get("actId"), shortUrl);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("重置短链接时单条异常"+tableName+"：error={}",e);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("重置短链接时异常"+tableName+"：error={}",e);
			}
		}
		log.error("重置短链接时耗时{}ms.",new Object[]{System.currentTimeMillis()-start});
	}
	
	/**
	 * 设置短链接为空
	 * @param weixinSystemProject
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/resetShortUrlEmpty", method = { RequestMethod.GET,RequestMethod.POST })
	public AjaxJson resetShortUrlEmpty(@ModelAttribute WeixinSystemProject weixinSystemProject,HttpServletRequest request) {
		AjaxJson j=new AjaxJson();
		try {
			StringBuffer sb=new StringBuffer();
			sb.append("eshop_main,");//综合商城
			sb.append("eshop_goods,");//综合商城商品
			sb.append("jwshop_goods,");//单品商城
			sb.append("wx_act_bargain,");//微砍价
			sb.append("wx_act_businesshall,");//主题评选
			sb.append("wx_act_commonvote,");//大赛投票
			sb.append("wx_act_countmoney,");//疯狂数钱
			sb.append("wx_act_divination,");//摇签祈福
			sb.append("wx_act_goldeneggs,");//砸金蛋
			sb.append("wx_act_jiugongge,");//九宫格
			sb.append("wx_act_luckyfruit,");//水果机
			sb.append("wx_act_luckyroulette,");//大转盘
			sb.append("wx_act_redpacket,");//抢红包
			sb.append("wx_act_scratchcards,");//刮刮乐
			sb.append("wx_act_shaketicket_home,");//摇一摇
			sb.append("wx_act_yyduobao");//一元夺宝
			String[] tableNames = sb.toString().split(",");
			for (String tableName:tableNames) {
				try {
					weixinSystemProjectService.doEditShortEmpty(tableName);
				} catch (Exception e) {
					e.printStackTrace();
					log.error("设置表里的短链接单条数据为空时异常："+tableName+"error={}",e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("设置表里的短链接为空时异常：error={}",e);
		}
		return j;
	}
}

