package com.jeecg.p3.goldeneggs.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.baseApi.service.BaseApiJwidService;
import com.jeecg.p3.baseApi.service.BaseApiSystemService;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.exception.GoldeneggsException;
import com.jeecg.p3.goldeneggs.exception.GoldeneggsExceptionEnum;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsAwardsService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsPrizesService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRecordService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRegistrationService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsService;

@Controller
@RequestMapping("/goldeneggs")
public class GoldeneggController {
	public final static Logger LOG = LoggerFactory
			.getLogger(GoldeneggController.class);
	@Autowired
	private WxActGoldeneggsRegistrationService wxActGoldeneggsRegistrationService;
	@Autowired
	private WxActGoldeneggsService wxActGoldeneggsService;
	@Autowired
	private WxActGoldeneggsRelationService wxActGoldeneggsRelationService;
	@Autowired
	private WxActGoldeneggsPrizesService wxActGoldeneggsPrizesService;
	@Autowired
	private WxActGoldeneggsAwardsService wxActGoldeneggsAwardsService;
	@Autowired
	private WxActGoldeneggsRecordService wxActGoldeneggsRecordService;
	@Autowired
	private BaseApiJwidService baseApiJwidService;
	@Autowired
	private BaseApiSystemService baseApiSystemService;
	/**
	 * 砸金蛋首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toGoldenegg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toGoldenegg(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info("toYaoqian parameter WeixinDto={}.",
				new Object[] { weixinDto });
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "goldeneggs/vm/Index.vm";
		WxActGoldeneggs queryById = null;
		try {
			// 验证参数
			validateWeixinDtoParam(weixinDto);
			String jwid = weixinDto.getJwid();
			String openid = weixinDto.getOpenid();
			String actId = weixinDto.getActId();
			String appid = weixinDto.getAppid();
			//获取活动信息
			queryById = wxActGoldeneggsService.queryById(actId);
			if (queryById == null) {
				throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			long date = new Date().getTime();
			if(date<queryById.getStarttime().getTime()){
				//throw new GoldeneggsException(GoldeneggsExceptionEnum.ACT_BARGAIN_NO_START, "活动还未开始");
				velocityContext.put("act_Status", false);
				velocityContext.put("act_Status_Msg", "活动未开始");
			}
			if(date>queryById.getEndtime().getTime()){
				//throw new GoldeneggsException(GoldeneggsExceptionEnum.ACT_BARGAIN_END, "活动已结束");
				velocityContext.put("act_Status", false);
				velocityContext.put("act_Status_Msg", "活动已结束");
			}
			
			//--update-begin---author:huangqingquan---date:20161125-----for:是否关注可参加---------------
			if("1".equals(queryById.getFoucsUserCanJoin())){//如果活动设置了需要关注用户才能参加	
				velocityContext.put("qrcodeUrl", baseApiJwidService.getQrcodeUrl(jwid));
				JSONObject userobj = WeiXinHttpUtil.getGzUserInfo(weixinDto.getOpenid(),weixinDto.getJwid());
				if(userobj!=null&&userobj.containsKey("subscribe")){
					if(!"1".equals(userobj.getString("subscribe"))){
						velocityContext.put("subscribeFlage", "1");
					}
				}else{
					velocityContext.put("subscribeFlage", "1");
				}
			}
			//--update-end---author:huangqingquan---date:20161125-----for:是否关注可参加---------------
			
			// 展示所有奖品等级，奖品数量，奖品名字
			List<WxActGoldeneggsRelation> list = wxActGoldeneggsRelationService
					.queryPrizeAndAward(actId);
			Integer count = null;// 总次数
			Integer awardsNum = null;// 剩余次数
			Integer remainNumDay = null;// 每天剩余次数
			WxActGoldeneggsRegistration registration = wxActGoldeneggsRegistrationService
					.getOpenid(openid, actId);

			count = queryById.getCount();// 总数量
			if (registration == null) {// 第一次进活动显示所有次数
				awardsNum = count;// 个人可以砸金蛋的剩余次数
				remainNumDay = queryById.getNumPerDay();// 每天的剩余次数
			}
			if (registration != null) {
				SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
				String update = sb.format(new Date());
				if(update.equals(registration.getUpdateTime())){
					remainNumDay = queryById.getNumPerDay()-registration.getRemainNumDay();// 每天的剩余次数
				}else{				
					remainNumDay = registration.getRemainNumDay();
				}
				awardsNum = count-registration.getAwardsNum();
			}
			if(awardsNum<1){
				awardsNum=0;
			}
			if(remainNumDay<1){
				remainNumDay=0;
			}
			if(remainNumDay>awardsNum){
				remainNumDay=awardsNum;
			}
			velocityContext.put("count", count.toString());
			velocityContext.put("awardsNum", awardsNum.toString());
			velocityContext.put("remainNumDay", remainNumDay.toString());
			velocityContext.put("list", list);
			velocityContext.put("goldeneggs", queryById);
			velocityContext.put("weixinDto", weixinDto);
			velocityContext.put("hdUrl", queryById.getHdurl());
			velocityContext.put("appId", appid);// 必填，公众号的唯一标识
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
			velocityContext.put("signature",WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
			//--update-begin-author:liwenhui date:2018-3-13 for：宣传语配置化
			velocityContext.put("huodong_bottom_copyright", baseApiSystemService.getHuodongLogoBottomCopyright(queryById.getCreateBy()));
			//--update-end-author:liwenhui date:2018-3-13 for：宣传语配置化
		}catch (GoldeneggsException e) {
			e.printStackTrace();
			LOG.error("goldeneggs toGoldenegg error:{}", e.getMessage());
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
			viewName=chooseErrorPage(e.getDefineCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("goldeneggs toGoldenegg error:{}", e);
			velocityContext.put("errCode",GoldeneggsExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",GoldeneggsExceptionEnum.SYS_ERROR.getErrChineseMsg());
			viewName= "system/vm/error.vm";
		}
		ViewVelocity.view(request, response, viewName, velocityContext);
	}
	/**
	 * 错误页面
	 * @param errorCode
	 * @return
	 */
	private String chooseErrorPage(String errorCode){
		if(errorCode.equals("02007")){
			return "system/vm/before.vm";
		}else if(errorCode.equals("02008")){
			return "system/vm/over.vm";
		}else{
			return"system/vm/error.vm";
		}
	}
	/**
	 * 砸金蛋抽奖次数是否用完
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toCheck", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson toCheck(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		AjaxJson j = new AjaxJson();
		try {
			String actId = request.getParameter("actId");
			String jwid = request.getParameter("jwid");
			String openid = request.getParameter("openid");
			WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
			if (wxActGoldeneggs == null) {
				throw new GoldeneggsException(
						GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			if (!jwid.equals(wxActGoldeneggs.getJwid())) {
				throw new GoldeneggsException(
						GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}
			//参与人员记录
			WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationService
					.getOpenid(openid, actId);
			if (wxActGoldeneggsRegistration != null) {
				//每日剩余抽奖次数
				Integer remainNumDays = null;
				//抽奖总次数
				Integer awardsNum = null;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				String date = sdf.format(new Date());
				if(wxActGoldeneggsRegistration.getUpdateTime().equals(date)){
					remainNumDays = wxActGoldeneggs.getNumPerDay()- wxActGoldeneggsRegistration
					.getRemainNumDay();// 个人每天砸的数量
					if (remainNumDays < 1) {
						j.setObj("3");
					}
				}
				awardsNum = wxActGoldeneggsRegistration.getAwardsNum();// 个人砸的总数量
				if (awardsNum >= wxActGoldeneggs.getCount()) {
					j.setObj("2");
					j.setSuccess(false);
				}
			}
		} catch (GoldeneggsException e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("系统异常！");
			LOG.info(e.toString());
		}
		return j;
	}

	/**
	 * 砸金蛋中奖页面
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */

	@RequestMapping(value = "/toAward", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson toAward(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		AjaxJson j = new AjaxJson();
		try {
			String actId = request.getParameter("actId");
			String jwid = request.getParameter("jwid");
			String openid = request.getParameter("openid");
			WxActGoldeneggs queryById = wxActGoldeneggsService.queryById(actId);
			if (queryById == null) {
				throw new GoldeneggsException(
						GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			if (!jwid.equals(queryById.getJwid())) {
				throw new GoldeneggsException(
						GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}
			j = wxActGoldeneggsRegistrationService.prizeRecord(weixinDto, j);// 根据概率返回已用的信息
			if(j.getAttributes()!=null){
				WxActGoldeneggsRecord wxActGoldeneggsRecord = wxActGoldeneggsRecordService.queryByCode(j.getAttributes().get("code").toString());
				j.getAttributes().put("recordId", wxActGoldeneggsRecord.getId());
			}
		} catch (GoldeneggsException e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("系统异常！");
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 用户领取奖品详细信息
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveGoldEggPrize", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson saveGoldEggPrize(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		AjaxJson j = new AjaxJson();
		try {
			String mobile = request.getParameter("mobile");
			String username = request.getParameter("username");
			String address = request.getParameter("address");
			String recordId = request.getParameter("recordId");
			WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
					.queryById(recordId);
			queryByCode.setPhone(mobile);
			queryByCode.setAddress(address);
			queryByCode.setRealname(username);
			wxActGoldeneggsRecordService.doEdit(queryByCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 展示我的奖品记录
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */

	@RequestMapping(value = "/toMyPrize", method = { RequestMethod.GET,RequestMethod.POST })
	public void toMyPrize(@ModelAttribute WeixinDto weixinDto,HttpServletRequest request, HttpServletResponse response)throws Exception {
		LOG.info("toYaoqian parameter WeixinDto={}.",new Object[] { weixinDto });
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String actId = weixinDto.getActId();
		String code = request.getParameter("code");
		String viewName = "goldeneggs/vm/prizename.vm";
		VelocityContext velocityContext = new VelocityContext();
		String userAddress = null;
		String userName = null;
		String userMobile = null;
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		long date = new Date().getTime();
		if(date<wxActGoldeneggs.getStarttime().getTime()){
			velocityContext.put("act_Status", false);
			velocityContext.put("act_Status_Msg", "活动未开始");
		}
		if(date>wxActGoldeneggs.getEndtime().getTime()){
			velocityContext.put("act_Status", false);
			velocityContext.put("act_Status_Msg", "活动已结束");
		}
		List<WxActGoldeneggsRecord> queryLists = wxActGoldeneggsRecordService.queryMyList(openid,actId);
		List<WxActGoldeneggsRecord> queryByCodes = new ArrayList<WxActGoldeneggsRecord>();
		for (WxActGoldeneggsRecord list : queryLists) {
			String codes = list.getCode();
			if (codes != null) {
				WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
						.queryByCode(codes);
				userAddress = queryByCode.getAddress();
				userName = queryByCode.getRealname();
				userMobile = queryByCode.getPhone();
				queryByCodes.add(queryByCode);
			}
		}
		velocityContext.put("code", code);
		velocityContext.put("queryList", queryByCodes);
		velocityContext.put("goldeneggs", wxActGoldeneggs);
		velocityContext.put("weixinDto", weixinDto);
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
		velocityContext.put("signature",WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
		//--update-begin-author:liwenhui date:2018-3-13 for：宣传语配置化
		velocityContext.put("huodong_bottom_copyright", baseApiSystemService.getHuodongLogoBottomCopyright(wxActGoldeneggs.getCreateBy()));
		//--update-end-author:liwenhui date:2018-3-13 for：宣传语配置化
		ViewVelocity.view(request, response, viewName, velocityContext);

	}

	/**
	 * 展示所有奖品记录
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAllPrize", method = { RequestMethod.GET,RequestMethod.POST })
	public void toAllPrize(@ModelAttribute WeixinDto weixinDto,HttpServletRequest request, HttpServletResponse response)throws Exception {
		LOG.info("toYaoqian parameter WeixinDto={}.",new Object[] { weixinDto });
		String jwid = weixinDto.getJwid();
		String actId = weixinDto.getActId();
		String viewName = "goldeneggs/vm/allprizename.vm";
		VelocityContext velocityContext = new VelocityContext();
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		long date = new Date().getTime();
		if(date<wxActGoldeneggs.getStarttime().getTime()){
			velocityContext.put("act_Status", false);
			velocityContext.put("act_Status_Msg", "活动未开始");
		}
		if(date>wxActGoldeneggs.getEndtime().getTime()){
			velocityContext.put("act_Status", false);
			velocityContext.put("act_Status_Msg", "活动已结束");
		}
		List<WxActGoldeneggsRecord> queryLists = wxActGoldeneggsRecordService.queryList(actId);
		List<WxActGoldeneggsRecord> queryByCodes = new ArrayList<WxActGoldeneggsRecord>();
		for (WxActGoldeneggsRecord list : queryLists) {
			String codes = list.getCode();
			if (codes != null) {
				WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService.queryByCode(codes);
				queryByCodes.add(queryByCode);
			}
		}
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
		velocityContext.put("signature",WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
		velocityContext.put("queryList", queryByCodes);
		velocityContext.put("goldeneggs", wxActGoldeneggs);
		velocityContext.put("weixinDto", weixinDto);
		//--update-begin-author:liwenhui date:2018-3-13 for：宣传语配置化
		velocityContext.put("huodong_bottom_copyright", baseApiSystemService.getHuodongLogoBottomCopyright(wxActGoldeneggs.getCreateBy()));
		//--update-end-author:liwenhui date:2018-3-13 for：宣传语配置化
		ViewVelocity.view(request, response, viewName, velocityContext);

	}

	/**
	 * 更新用户信息，兑奖换奖品
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toUpdateMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson toUpdateMessage(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response) {
		LOG.info("toYaoqian parameter WeixinDto={}.",
				new Object[] { weixinDto });
		AjaxJson j = new AjaxJson();
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String actId = weixinDto.getActId();
		String id = request.getParameter("id");
		try {
			WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
					.queryById(id);

			String userAddress = null;
			String userName = null;
			String userMobile = null;
			String awdCode=null;//新加中奖码
			if (queryByCode != null) {
				userAddress = queryByCode.getAddress();
				userName = queryByCode.getRealname();
				userMobile = queryByCode.getPhone();
				//update-begin--Author:liuyuchong  Date:20180503 for：获取兑换码
				awdCode=queryByCode.getCode();
				//update-end--Author:liuyuchong  Date:20180503 for：获取兑换码
			}
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("userName", userName);
			mm.put("userAddress", userAddress);
			mm.put("userMobile", userMobile);
			mm.put("recordId", id);
			//update-begin--Author:liuyuchong  Date:20180503 for：把兑换码传入前台
			mm.put("awdCode",awdCode);
			//update-end--Author:liuyuchong  Date:20180503 for：把兑换码传入前台
			j.setAttributes(mm);
			j.setObj("iscode");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
		}
		return j;
	}
	/**
	 * 校验参数
	 * @param weixinDto
	 */
	private void validateWeixinDtoParam(WeixinDto weixinDto) {
		
		if (StringUtils.isEmpty(weixinDto.getActId())) {
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ARGUMENT_ERROR, "活动ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getOpenid())) {
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ARGUMENT_ERROR, "参与人openid不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getJwid())) {
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ARGUMENT_ERROR, "微信ID不能为空");
		}
	}
	
}
