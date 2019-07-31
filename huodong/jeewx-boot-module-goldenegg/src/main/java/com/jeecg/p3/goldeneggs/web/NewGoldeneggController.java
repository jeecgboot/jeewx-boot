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
import org.jeecgframework.p3.core.util.PropertiesUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.baseApi.service.BaseApiJwidService;
import com.jeecg.p3.baseApi.service.BaseApiSystemService;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRecord;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsShareRecord;
import com.jeecg.p3.goldeneggs.exception.GoldeneggsException;
import com.jeecg.p3.goldeneggs.exception.GoldeneggsExceptionEnum;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsAwardsService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsPrizesService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRecordService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRegistrationService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsRelationService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsService;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsShareRecordService;
import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;
import com.jeecg.p3.goldeneggs.verify.service.WxActGoldeneggsVerifyService;

@Controller
@RequestMapping("/goldeneggs/new")
public class NewGoldeneggController {
	public final static Logger LOG = LoggerFactory
			.getLogger(NewGoldeneggController.class);
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
	@Autowired
	private WxActGoldeneggsShareRecordService wxActGoldeneggsShareRecordService;
	@Autowired
	private WxActGoldeneggsVerifyService wxActGoldeneggsVerifyService;
	@Autowired
	private static String VerificationUrl="";
	static {
	PropertiesUtil p=new PropertiesUtil("goldeneggs.properties");
		VerificationUrl=p.readProperty("VerificationUrl");
	}
	/**
	 * 砸金蛋首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toGoldenegg", method = { RequestMethod.GET,RequestMethod.POST })
	public void toGoldenegg(@ModelAttribute WeixinDto weixinDto,HttpServletRequest request, HttpServletResponse response)throws Exception {
			LOG.info("toGoldenegg parameter WeixinDto={}.",new Object[] { weixinDto });
			String viewName = "goldeneggs/vm/index.vm";
			VelocityContext velocityContext = new VelocityContext();
			try {
				validateWeixinDtoParam(weixinDto);
				String actId = weixinDto.getActId();
				String openid = weixinDto.getOpenid();
				String jwid = weixinDto.getJwid();
				String appid = weixinDto.getAppid();
				WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
				Integer joinNum = wxActGoldeneggsRegistrationService.queryCountByActId(actId);
				if (wxActGoldeneggs == null) {
					throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
				}
				long date = new Date().getTime();
				if(date<wxActGoldeneggs.getStarttime().getTime()){
					//throw new GoldeneggsException(GoldeneggsExceptionEnum.ACT_BARGAIN_NO_START, "活动还未开始");
					velocityContext.put("act_Status", false);
					velocityContext.put("act_Status_Msg", "活动未开始");
				}
				if(date>wxActGoldeneggs.getEndtime().getTime()){
					//throw new GoldeneggsException(GoldeneggsExceptionEnum.ACT_BARGAIN_END, "活动已结束");
					velocityContext.put("act_Status", false);
					velocityContext.put("act_Status_Msg", "活动已结束");
				}
				//validateActDate(wxActGoldeneggs);
				//用户关注状态 0：未关注不可参与  1：用户关注不限制
				if("0".equals(wxActGoldeneggs.getFoucsUserCanJoin())){
					setWeixinDto(weixinDto);		
					if(!"1".equals(weixinDto.getSubscribe())){
//						throw new GoldeneggsException(
//								GoldeneggsExceptionEnum.ARGUMENT_ERROR,"请关注后再参与活动");
						velocityContext.put("whetherSubscribe", "0");
					}else{
						velocityContext.put("whetherSubscribe", "1");
					}
				}else{
					velocityContext.put("whetherSubscribe", "1");
				}
				
				// 查询奖品明细表，展示所有奖品等级，奖品数量，奖品名字
				List<WxActGoldeneggsRelation> relationList = wxActGoldeneggsRelationService.queryPrizeAndAward(actId);
				// 查询奖品表
				List<WxActGoldeneggsPrizes> prizesList = wxActGoldeneggsPrizesService.queryByActId(actId);
				// 查询个人中奖名单
				List<WxActGoldeneggsRecord> personalWinList = wxActGoldeneggsRecordService.queryPersonalWin(openid,actId);
				// 查询所有中奖名单
				List<WxActGoldeneggsRecord> winList = wxActGoldeneggsRecordService.queryByWin();
				
				Integer count = null;//砸金蛋总抽奖次数  
				Integer numPerDay = null;//砸金蛋每日抽奖次数  
				Integer awardsNum = null;//个人总抽奖次数 
				Integer remainNumDay = null;//个人每日抽奖剩余次数 
				Integer shareNumflag = null;//分享次数已使用标记
				WxActGoldeneggsRegistration registration = wxActGoldeneggsRegistrationService.getOpenid(openid, actId);
				count = wxActGoldeneggs.getCount();
				numPerDay = wxActGoldeneggs.getNumPerDay();
				if(registration == null){
					//总共可以抽奖次数
					awardsNum = count;
					//每天可以抽奖次数
					remainNumDay = numPerDay;
				}
				if (registration != null) {
					SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
					String update = sb.format(new Date());
					if(update.equals(registration.getUpdateTime())){
						//每天可以抽奖次数
						remainNumDay = wxActGoldeneggs.getNumPerDay()-registration.getRemainNumDay();// 每天的剩余次数
					}else{	
						//每天可以抽奖次数
						remainNumDay = wxActGoldeneggs.getNumPerDay();
					}
					//总共可以抽奖次数
					awardsNum = count-registration.getAwardsNum();
				}
				if(awardsNum<1){
					awardsNum=0;
				}
				if(remainNumDay<1){
					remainNumDay=0;
				}
				if(count !=0){
					if(remainNumDay>awardsNum){
						remainNumDay=awardsNum;
					}
				}
				
				if(StringUtils.isNotEmpty(wxActGoldeneggs.getTemplateCode())){
					viewName = "goldeneggs/template/"+wxActGoldeneggs.getTemplateCode()+"/vm/index.vm";
				}
				
				//--update-begin---author:lsq---date:20181010-----for:分享得次数---------------
				//判断总次数是否已用完
				if(count!=0){
					if(registration != null && count<=registration.getAwardsNum()){
					  velocityContext.put("countFlag","1"); //总次数已用完
					}else{
					  velocityContext.put("countFlag","0"); //总次数未用完
					}
				}else{
					velocityContext.put("countFlag","0"); //总次数未用完
				}
				
				//int dayBargainRecordCount = wxActJiugonggeRecordService.queryBargainRecordCountByOpenidAndActidAndJwid(weixinDto.getOpenid(), weixinDto.getActId(), weixinDto.getJwid(), currDate);
				//判断是否分享得额外次数 0有，1没有
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				if("0".equals(wxActGoldeneggs.getExtraLuckyDraw())){
					//判断当前用户是否分享
					List<WxActGoldeneggsShareRecord> wxActGoldeneggsShareRecord=wxActGoldeneggsShareRecordService.queryShareRecordByDate(actId, openid, sd.format(new Date()), "");
					if(wxActGoldeneggsShareRecord.size()>0){
						//已分享
						velocityContext.put("shareFlag","0");
						if(registration !=null){
							shareNumflag=wxActGoldeneggs.getNumPerDay()-registration.getRemainNumDay();
							if(shareNumflag<0){
								velocityContext.put("shareNumflag",shareNumflag);
							}
						}
					}else{
						//未分享
						velocityContext.put("shareFlag","1");
					}
				}else{
					velocityContext.put("shareFlag","1");
				}
				//--update-end---author:lsq---date:20181010-----for:分享得次数---------------
				
				
				velocityContext.put("count", count);//砸金蛋总抽奖次数  
				velocityContext.put("numPerDay", numPerDay);//砸金蛋每日抽奖次数  
				velocityContext.put("awardsNum", awardsNum);//个人总抽奖次数 
				velocityContext.put("remainNumDay", remainNumDay);//个人每日抽奖剩余次数
				velocityContext.put("relationList", relationList);//查询奖品明细表 ,所有奖品等级，奖品数量，奖品名字
				velocityContext.put("prizesList", prizesList);// 查询奖品表
				velocityContext.put("personalWinList", personalWinList);// 查询个人中奖名单
				velocityContext.put("winList", winList);// 查询中奖名单
				velocityContext.put("goldeneggs", wxActGoldeneggs);//新模板-主表数据
				velocityContext.put("weixinDto", weixinDto);
				velocityContext.put("hdUrl", wxActGoldeneggs.getHdurl()); 
				velocityContext.put("appId", appid);// 必填，公众号的唯一标识
				velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
				velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
				velocityContext.put("signature", WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
				velocityContext.put("doMain", "/upload/img/goldeneggs");
				velocityContext.put("huodong_bottom_copyright", baseApiSystemService.getHuodongLogoBottomCopyright(wxActGoldeneggs.getCreateBy()));
				String qrcodeUrl = baseApiJwidService.getQrcodeUrl(weixinDto.getJwid());
				velocityContext.put("qrcodeUrl", qrcodeUrl);
				velocityContext.put("joinNum", joinNum);
			} catch (GoldeneggsException e) {
				e.printStackTrace();
				LOG.error("goldeneggs/new toGoldenegg error:{}", e.getMessage());
				velocityContext.put("errCode", e.getDefineCode());
				velocityContext.put("errMsg", e.getMessage());
				viewName=chooseErrorPage(e.getDefineCode());
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("goldeneggs/new toGoldenegg error:{}", e);
				velocityContext.put("errCode",GoldeneggsExceptionEnum.SYS_ERROR.getErrCode());
				velocityContext.put("errMsg",GoldeneggsExceptionEnum.SYS_ERROR.getErrChineseMsg());
				viewName= "system/vm/error.vm";
			}
			try {
				ViewVelocity.view(request, response, viewName, velocityContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		Map<String, Object> attributes = new HashMap<String, Object>();
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
			//判断活动开始结束时间
			Date starttime = wxActGoldeneggs.getStarttime();
			Date endtime = wxActGoldeneggs.getEndtime();
			if(new Date().before(starttime) || new Date().after(endtime)){
				j.setObj("5");
				j.setSuccess(false);
				return j;
			}
			//用户关注状态1：用户关注可参与0：用户关注不限制
			if("1".equals(wxActGoldeneggs.getFoucsUserCanJoin())){
				setWeixinDto(weixinDto);		
				if(!"1".equals(weixinDto.getSubscribe())){
					attributes.put("whetherSubscribe","0");
					j.setAttributes(attributes);
					j.setSuccess(false);	
				}else{
					attributes.put("whetherSubscribe","1");
					j.setAttributes(attributes);
				}
			}
			//判断是否分享得额外次数
			int extraCount=0;
			if("0".equals(wxActGoldeneggs.getExtraLuckyDraw())){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				List<WxActGoldeneggsShareRecord> wxActGoldeneggsShareRecord=wxActGoldeneggsShareRecordService.queryShareRecordByDate(actId, openid, sd.format(new Date()), "");
				if(wxActGoldeneggsShareRecord.size()>0){
					extraCount=1;
				}
			}
			WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationService
					.getOpenid(openid, actId);
			if(wxActGoldeneggs.getCount()!=0){
				if (wxActGoldeneggsRegistration != null) {
					Integer remainNumDays = null;
					Integer awardsNum = null;
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					String date = sdf.format(new Date());
					if(wxActGoldeneggsRegistration.getUpdateTime().equals(date)){
						remainNumDays = wxActGoldeneggs.getNumPerDay()+extraCount- wxActGoldeneggsRegistration
						.getRemainNumDay();// 个人每天砸的数量
						if (remainNumDays < 1) {
							j.setObj("3");
							attributes.put("title","今日抽奖次数已用完");
							j.setAttributes(attributes);
							j.setSuccess(false);
						}
					}
					awardsNum = wxActGoldeneggsRegistration.getAwardsNum();// 个人砸的总数量
					if (awardsNum >= wxActGoldeneggs.getCount()) {
						j.setObj("4");
						attributes.put("title","总抽奖次数已用完");
						j.setAttributes(attributes);
						j.setSuccess(false);
					}
				}
			}else{
			/*	if("1".equals(wxActGoldeneggs.getFoucsUserCanJoin())){
					setWeixinDto(weixinDto);		
					if(!"1".equals(weixinDto.getSubscribe())){
						//未关注
						attributes.put("whetherSubscribe","0");
						j.setAttributes(attributes);
						j.setSuccess(false);	
					}else{
						//已关注
						attributes.put("whetherSubscribe","1");
						j.setAttributes(attributes);
						j.setSuccess(false);	
					}
				}*/
				if (wxActGoldeneggsRegistration != null) {
					Integer remainNumDays = null;
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					String date = sdf.format(new Date());
					if(wxActGoldeneggsRegistration.getUpdateTime().equals(date)){
						remainNumDays = wxActGoldeneggs.getNumPerDay()+extraCount- wxActGoldeneggsRegistration
						.getRemainNumDay();// 个人每天砸的数量
						if (remainNumDays < 1) {
							j.setObj("3");
							attributes.put("title","今日抽奖次数已用完");
							j.setAttributes(attributes);
							j.setSuccess(false);
						}
					}
				}
			}
		} catch (GoldeneggsException e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setObj("4");
			attributes.put("title",e.getMessage());
			j.setAttributes(attributes);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setObj("4");
			attributes.put("title","系统异常!");
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
		Map<String, Object> attributes = new HashMap<String, Object>();
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
			//中奖参与状态
			String prizeStatus = queryById.getPrizeStatus();
			//0:中奖可继续参与;1中奖不可继续参与
			if("0".equals(prizeStatus)){
				    j = wxActGoldeneggsRegistrationService.prizeRecordNew(weixinDto, j);// 根据概率返回已用的信息
			}else{
				//判断有无中奖记录
				List<WxActGoldeneggsRecord> wxActGoldeneggsRecords=wxActGoldeneggsRecordService.queryByActidAndOpenidAndPrizesStatus(actId,openid,"1");
			    if(wxActGoldeneggsRecords !=null && wxActGoldeneggsRecords.size()>0){
			    	 j = wxActGoldeneggsRegistrationService.noPrizeRecordNew(weixinDto, j);// 不中奖返回已用的信息
				 }else{
					 j = wxActGoldeneggsRegistrationService.prizeRecordNew(weixinDto, j);// 根据概率返回已用的信息
				 }
			}

		} catch (GoldeneggsException e) {
			j.setSuccess(false);
			attributes.put("title",e.getMessage());
			j.setAttributes(attributes);
			j = wxActGoldeneggsRegistrationService.noPrizeRecordNew(weixinDto, j);
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			attributes.put("title","系统异常！");
			j = wxActGoldeneggsRegistrationService.noPrizeRecordNew(weixinDto, j);
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
			String code = request.getParameter("code");
			WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
					.queryByCode(code);
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

	@RequestMapping(value = "/toMyPrize", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toMyPrize(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info("toYaoqian parameter WeixinDto={}.",
				new Object[] { weixinDto });
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String actId = weixinDto.getActId();
		String code = request.getParameter("code");
		String viewName = "goldeneggs/vm/prizename.vm";
		VelocityContext velocityContext = new VelocityContext();
		String userAddress = null;
		String userName = null;
		String userMobile = null;
		List<WxActGoldeneggsRecord> queryLists = wxActGoldeneggsRecordService
				.queryMyList(openid,actId);
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
		velocityContext.put("weixinDto", weixinDto);
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
		velocityContext.put("signature",
				WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
		ViewVelocity.view(request, response, viewName, velocityContext);

	}

	/**
	 * 展示所有奖品记录
	 * 
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAllPrize", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toAllPrize(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info("toYaoqian parameter WeixinDto={}.",
				new Object[] { weixinDto });
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String actId = weixinDto.getActId();
		String viewName = "goldeneggs/vm/allprizename.vm";
		VelocityContext velocityContext = new VelocityContext();

		List<WxActGoldeneggsRecord> queryLists = wxActGoldeneggsRecordService
				.queryList(actId);
		List<WxActGoldeneggsRecord> queryByCodes = new ArrayList<WxActGoldeneggsRecord>();
		for (WxActGoldeneggsRecord list : queryLists) {
			String codes = list.getCode();
			if (codes != null) {
				WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
						.queryByCode(codes);
				queryByCodes.add(queryByCode);
			}
		}
		velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);// 必填，生成签名的随机串
		velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);// 必填，生成签名的时间戳
		velocityContext.put("signature",
				WeiXinHttpUtil.getRedisSignature(request, jwid));// 必填，签名，见附录1
		velocityContext.put("queryList", queryByCodes);
		velocityContext.put("weixinDto", weixinDto);
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
		LOG.info("toUpdateMessage parameter WeixinDto={}.",
				new Object[] { weixinDto });
		AjaxJson j = new AjaxJson();
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String actId = weixinDto.getActId();
		String code = request.getParameter("code");
		try {
			WxActGoldeneggsRecord queryByCode = wxActGoldeneggsRecordService
					.queryByCode(code);

			String userAddress = null;
			String userName = null;
			String userMobile = null;
			if (queryByCode != null) {
				userAddress = queryByCode.getAddress();
				userName = queryByCode.getRealname();
				userMobile = queryByCode.getPhone();
			}
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("userName", userName);
			mm.put("userAddress", userAddress);
			mm.put("userMobile", userMobile);
			j.setAttributes(mm);
			j.setObj("iscode");
		} catch (Exception e) {
			j.setSuccess(false);
		}
		return j;
	}
	/**
	 * 验证活动id和openid是否为空
	 * 
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
	/**
	 * 判断活动是否开始是否结束
	 */
	private void validateActDate(WxActGoldeneggs wxActGoldeneggs){
		Date date = new Date();
		if(wxActGoldeneggs == null){
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ACT_BARGAIN_END, "活动不存在");
		} else if (date.before(wxActGoldeneggs.getStarttime())) {
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ARGUMENT_ERROR, "活动未开始");
		} else if (date.after(wxActGoldeneggs.getEndtime())) {
			throw new GoldeneggsException(
					GoldeneggsExceptionEnum.ARGUMENT_ERROR,"活动已结束");
		}
	}
	
	/**
	 * 解析json字符串(得到关注状态subscribe  1.关注 剩下的都是不关注)
	 * @param weixinDto
	 * @return
	 */
	private Map<String,String> setWeixinDto(WeixinDto weixinDto) {
		LOG.info("setWeixinDto parameter weixinDto={}",new Object[] { weixinDto });
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (weixinDto.getOpenid() != null) {
				JSONObject jsonObj = WeiXinHttpUtil.getGzUserInfo(weixinDto.getOpenid(), weixinDto.getJwid());
				LOG.info("setWeixinDto Openid getGzUserInfo jsonObj={}",new Object[] { jsonObj });
				if (jsonObj != null && jsonObj.containsKey("subscribe")) {
					weixinDto.setSubscribe(jsonObj.getString("subscribe"));
				} else {
					weixinDto.setSubscribe("0");
				}
				if (jsonObj != null && jsonObj.containsKey("nickname")) {
					weixinDto.setNickname(jsonObj.getString("nickname"));
				} else {
					weixinDto.setNickname("");
				}
				if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
					map.put("headimgurl", jsonObj.getString("headimgurl"));
				} else {
					map.put("fxheadimgurl", "");
				}
			}
			if (StringUtils.isNotEmpty(weixinDto.getFxOpenid())) {
				JSONObject jsonObj = WeiXinHttpUtil.getGzUserInfo(
						weixinDto.getFxOpenid(), weixinDto.getJwid());
				LOG.info("setWeixinDto FxOpenid getGzUserInfo jsonObj={}",
						new Object[] { jsonObj });
				if (jsonObj != null && jsonObj.containsKey("nickname")) {
					weixinDto.setFxNickname(jsonObj.getString("nickname"));
				} else {
					weixinDto.setFxNickname("");
				}
				if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
					map.put("fxheadimgurl", jsonObj.getString("headimgurl"));
				} else {
					map.put("fxheadimgurl", "");
				}
			}
		} catch (Exception e) {
			LOG.error("setWeixinDto e={}",
					new Object[] { e });
		}
		return map;
	}
	/**
	* 获取中奖记录
	*/
	@RequestMapping("/queryWinList")
	@ResponseBody
	public AjaxJson queryWinList(HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "30") int pageSize ) throws Exception{
		   AjaxJson json = new AjaxJson();
		   try{
			 String actId = request.getParameter("actId");
			 Map<String,Object> attribute = new  HashMap<String, Object>();
			 // 查询所有中奖名单
			 int count = wxActGoldeneggsRecordService.queryCountByWin(actId);
			 List<WxActGoldeneggsRecord> winList = wxActGoldeneggsRecordService.queryByWinAndPage(actId,pageNo*pageSize,pageSize);
			 attribute.put("count",count-(pageNo*pageSize));
			 json.setObj(winList);
			 json.setAttributes(attribute);
		   }catch(Exception e ){
			   e.printStackTrace();
		   }
		  return json;
	}
	/**
	 * 查询中奖记录
	 */
	@RequestMapping("/queryGoldeneggsRecord")
	@ResponseBody
	public AjaxJson queryGoldeneggsRecord(HttpServletRequest request) throws Exception{
		AjaxJson json = new AjaxJson();
		try{
			String code = request.getParameter("code");
			WxActGoldeneggsRecord goldeneggsRecord = wxActGoldeneggsRecordService.queryByCode(code);
		    json.setSuccess(true);
		    json.setObj(goldeneggsRecord);
		}catch(Exception e ){
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * @功能：分享回调
	 * @param weixinDto
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/fxCallback", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public AjaxJson fxCallback(@ModelAttribute WeixinDto weixinDto,HttpServletRequest request, HttpServletResponse response){
		AjaxJson j=new AjaxJson();
		try {
			String actId = weixinDto.getActId();
			String openid = weixinDto.getOpenid();
			String type = request.getParameter("type");
			//插入信息到分享记录表中
			WxActGoldeneggsShareRecord shareRecord = new WxActGoldeneggsShareRecord();
			shareRecord.setActId(actId);
			shareRecord.setOpenid(openid);
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			shareRecord.setRelDate(sd.format(new Date()));
			shareRecord.setType(type);
			shareRecord.setCreateTime(new Date());
			wxActGoldeneggsShareRecordService.doAdd(shareRecord);
			//返回信息
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	/**
	 * 获取二维码地址
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getVerificationUrl", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson  getVerificationUrl(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxJson j=new AjaxJson();
		try{
			String cardPsd=request.getParameter("cardPsd");
			String hdurl= VerificationUrl;
			hdurl=hdurl.replace("STATE", cardPsd);
			//---update-begin-Alex---Date:20180827----for:兑奖二维码生成链接通用改造----
			String actId=request.getParameter("actId");
			String jwid=request.getParameter("jwid");
			hdurl = hdurl.replace("ACTID", actId);
			hdurl = hdurl.replace("JWID", jwid);
			PropertiesUtil properties=new PropertiesUtil("goldeneggs.properties");
			String shortUrl=WeiXinHttpUtil.getShortUrl(hdurl,properties.readProperty("defaultJwid"));
			//hdurl="http://192.168.1.146:8080/P3-Web/goldeneggs/new/toVerificationreview.do?actId="+actId+"&jwid="+jwid+"&openid=123456&appid=wx6596a35fea9085d4&awd="+cardPsd;
			LOG.info("二维码生成连接:" + hdurl);
			j.setSuccess(true);
			j.setObj(hdurl);
			if(shortUrl!=null){				
				j.setObj(shortUrl);
			}
			//---update-end-Alex---Date:20180827----for:兑奖二维码生成链接通用改造----
		}catch(Exception e){
			j.setSuccess(false);
		}
		return j;
	}
	/**
	 *去核销页面
	 * （需要检查扫码人是否有权限）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toVerificationreview", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody		
	public void getVerificationreview(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response){
		// 参数验证
		//http://192.168.1.146:8080/P3-Web/goldeneggs/new/toVerificationreview.do?actId=4028811266a3cdde0166a3f446f70008&jwid=gh_20419b74f848&openid=123456&appid=wx6596a35fea9085d4&awd=jWHBH9BT0raV
		validateWeixinDtoParam(weixinDto);
		String cardPsd=request.getParameter("awd");
		String actId=weixinDto.getActId();
		String openid=weixinDto.getOpenid();
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		String viewName="";
		VelocityContext velocityContext=new VelocityContext();
		try {
			if(StringUtils.isEmpty(wxActGoldeneggs.getTemplateCode())){
				throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "模板为空！");
			}
			if (StringUtils.isEmpty(cardPsd)) {
				throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "中奖码不能为空");
			}
			WxActGoldeneggsVerify verify=wxActGoldeneggsVerifyService.queryByOpenId(openid,actId);
			if(verify!=null&&"0".equals(verify.getStatus())){
				viewName = "goldeneggs/template/"+wxActGoldeneggs.getTemplateCode()+"/vm/coupon.vm";
				WxActGoldeneggsVerify veri=wxActGoldeneggsVerifyService.queryAllGoldeneggs(actId,cardPsd);
				if(veri!=null){
					velocityContext.put("veri", veri);
					velocityContext.put("verify", verify);
				}else{
					throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "中奖码不正确！请联系管理员！");
				}
			}else{
				throw new GoldeneggsException(GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR, "抱歉，您的权限不足！请联系管理员！");
			}
		} catch (GoldeneggsException e) {
			e.printStackTrace();
			LOG.error("goldeneggs/new toGoldenegg error:{}", e.getMessage());
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
			viewName=chooseErrorPage(e.getDefineCode());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("goldeneggs/new toGoldenegg error:{}", e);
			velocityContext.put("errCode",GoldeneggsExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",GoldeneggsExceptionEnum.SYS_ERROR.getErrChineseMsg());
			viewName= "system/vm/error.vm";
		}
		try {
			ViewVelocity.view(request, response, viewName, velocityContext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *核销
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doVerify", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody		
	public AjaxJson doVerify(@ModelAttribute WxActGoldeneggsRecord Record,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtils.isNotEmpty(Record.getActId())&&StringUtils.isNotEmpty(Record.getCode())&&StringUtils.isNotEmpty(Record.getOpenid())){
				WxActGoldeneggsRecord recor=wxActGoldeneggsRecordService.queryByActIdAndCode(Record.getActId(), Record.getCode());
				//获取核销员id
				WxActGoldeneggsVerify Verify=wxActGoldeneggsVerifyService.queryByOpenId(Record.getOpenid(),Record.getActId());
				if(Verify!=null&&"0".equals(Verify.getStatus())){
					recor.setVerifyId(Verify.getId());
					recor.setRecieveStatus("1");
					recor.setRecieveTime(new Date());
					wxActGoldeneggsRecordService.doEdit(recor);
					j.setSuccess(true);
					j.setObj(recor);
				}
			}else{
				j.setSuccess(false);
				j.setObj("审核失败,参数错误");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setObj("审核失败,请联系管理员");
		}
		return j;
	}
	
	/**
	 *搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doSearch", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody		
	public AjaxJson doSearch(@ModelAttribute WxActGoldeneggsRecord Record,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxJson j = new AjaxJson();
		String code=request.getParameter("search");
		try {
			WxActGoldeneggsVerify veri=wxActGoldeneggsVerifyService.queryAllGoldeneggs(Record.getActId(),code);
			if(veri!=null){
				j.setObj(veri);
				j.setSuccess(true);
			}else{
				j.setSuccess(false);
			}
		} catch (Exception e) {
			j.setSuccess(false);
		}
		return j;
	}
}
