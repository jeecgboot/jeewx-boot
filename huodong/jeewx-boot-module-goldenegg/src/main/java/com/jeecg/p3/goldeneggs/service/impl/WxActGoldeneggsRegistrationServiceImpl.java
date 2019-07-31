package com.jeecg.p3.goldeneggs.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.StringUtil;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.jeecgframework.p3.core.utils.persistence.OptimisticLockingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRecordDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRegistrationDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsShareRecordDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
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
import com.jeecg.p3.goldeneggs.util.EmojiFilter;
import com.jeecg.p3.goldeneggs.util.LotteryUtil;

@Service("wxActGoldeneggsRegistrationService")
public class WxActGoldeneggsRegistrationServiceImpl implements
		WxActGoldeneggsRegistrationService {
	@Resource
	private WxActGoldeneggsRecordDao wxActGoldeneggsRecordDao;
	@Resource
	private WxActGoldeneggsShareRecordDao wxActGoldeneggsShareRecordDao;

	@Resource
	private WxActGoldeneggsRegistrationDao wxActGoldeneggsRegistrationDao;
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

	@Override
	public void doAdd(WxActGoldeneggsRegistration wxActGoldeneggsRegistration) {
		wxActGoldeneggsRegistrationDao.insert(wxActGoldeneggsRegistration);
	}

	@Override
	public void doEdit(WxActGoldeneggsRegistration wxActGoldeneggsRegistration) {
		wxActGoldeneggsRegistrationDao.update(wxActGoldeneggsRegistration);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsRegistrationDao.delete(id);
	}

	@Override
	public WxActGoldeneggsRegistration queryById(String id) {
		WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationDao
				.get(id);
		return wxActGoldeneggsRegistration;
	}

	@Override
	public PageList<WxActGoldeneggsRegistration> queryPageList(
			PageQuery<WxActGoldeneggsRegistration> pageQuery) {
		PageList<WxActGoldeneggsRegistration> result = new PageList<WxActGoldeneggsRegistration>();
		Integer itemCount = wxActGoldeneggsRegistrationDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsRegistration> wrapper = new PageQueryWrapper<WxActGoldeneggsRegistration>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsRegistration> list = wxActGoldeneggsRegistrationDao
				.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(),
				itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActGoldeneggsRegistration getOpenid(String openid, String actId) {

		return wxActGoldeneggsRegistrationDao.getOpenid(openid, actId);
	}

	// 抽中奖品
	public WxActGoldeneggsRelation calcOtherPrizePercentage(
			List<WxActGoldeneggsRelation> otherPrizeList) {// 通过活动id得到表里面的所有集合传过去
		if (otherPrizeList == null || otherPrizeList.size() == 0) {
			return null;
		}
		// 得到各奖品的概率列表
		List<Double> orignalRates = new ArrayList<Double>(otherPrizeList.size());
		for (int i = 0; i < otherPrizeList.size(); i++) {
			Integer remainNum = otherPrizeList.get(i).getRemainNum();
			if(remainNum==null){
				remainNum=0;
			}
			double probability = otherPrizeList.get(i).getProbability().doubleValue();
			if (remainNum <= 0) {// 剩余数量为零，需使它不能被抽到
				probability = 0;
			}
			orignalRates.add(probability);
		}
		int index = LotteryUtil.lottery(orignalRates);
		WxActGoldeneggsRelation wxActGoldeneggsRelation = null;
		if (index >= 0) {
			wxActGoldeneggsRelation = otherPrizeList.get(index);
		}
		if (index < 0) {
			wxActGoldeneggsRelation = null;
		}
		return wxActGoldeneggsRelation;
	}

	@Transactional(rollbackFor = Exception.class)
	public AjaxJson prizeRecord(WeixinDto weixinDto, AjaxJson j) {
		String actId = weixinDto.getActId();
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String nickname = "";
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		
		List<WxActGoldeneggsRelation> otherPrizeList = wxActGoldeneggsRelationService
				.queryByActId(actId);
		List<Double> list=new ArrayList<Double>();
		List<WxActGoldeneggsRelation> newPrizeList=new ArrayList<WxActGoldeneggsRelation>();
		for(WxActGoldeneggsRelation wxActGoldeneggsRelation:otherPrizeList){
			if(wxActGoldeneggsRelation.getRemainNum()!=null&&wxActGoldeneggsRelation.getRemainNum()>0){
				if(wxActGoldeneggsRelation.getProbability()!=null){
					list.add(wxActGoldeneggsRelation.getProbability().doubleValue());
					newPrizeList.add(wxActGoldeneggsRelation);
				}
			}
		}
		WxActGoldeneggsRelation relation=null;
		int lottery = LotteryUtil.lottery(list);
		if(lottery>=0){
			relation=newPrizeList.get(lottery);
		}
		/*int index=-1;
		for(WxActGoldeneggsRelation wxActGoldeneggsRelation:otherPrizeList){
			if(wxActGoldeneggsRelation.getRemainNum()!=null&&wxActGoldeneggsRelation.getRemainNum()>0){
				if(wxActGoldeneggsRelation.getProbability()!=null){
					index++;
					if(index==lottery&&wxActGoldeneggsRelation.getProbability().compareTo(BigDecimal.valueOf(list.get(lottery)))==0){
						relation=wxActGoldeneggsRelation;
						break;
					}
				}
			}
		}*/
		String name = null;// 奖品的名字
		String awardsName = null;// 奖项的名字
		Map<String, Object> mm = new HashMap<String, Object>();
		String prizeId = null;
		String awardId = null;
		String code = null;
		if (relation == null || "".equals(relation)) {
			name = null;// 奖品的名字
			awardsName = null;// 奖项的名字
			j.setObj("noPrizes");
		} else {
			prizeId = relation.getPrizeId();
			awardId = relation.getAwardId();
			WxActGoldeneggsPrizes prizes = wxActGoldeneggsPrizesService
					.queryById(prizeId);
			WxActGoldeneggsAwards awards = wxActGoldeneggsAwardsService
					.queryById(awardId);
			if (prizeId != null) {
				name = prizes.getName();// 奖品的名字
			}
			if (awards != null) {
				awardsName = awards.getAwardsName();// 奖品的等级
			}
			Integer rnamer = relation.getRemainNum();// 的到剩余数量的值
			if (rnamer >= 1) {// 逻辑控制
				rnamer = rnamer - 1;
				relation.setRemainNum(rnamer);
				wxActGoldeneggsRelationService.doEdit(relation);// 修改商品的数量
			} else {
				j.setObj("noPrizes");
			}
			code = getCoupon();
			mm.put("awardsName", awardsName);
			mm.put("code", code);
			mm.put("name", name);
			j.setAttributes(mm);
			j.setObj("isPrizes");
		}
		// 插入中奖记录表的数据
		WxActGoldeneggsRecord wxActGoldeneggsRecord = new WxActGoldeneggsRecord();
		JSONObject userobj = WeiXinHttpUtil.getGzUserInfo(openid, jwid);
		if (userobj != null && userobj.containsKey("nickname")) {
			nickname = userobj.getString("nickname");
		}
		if (StringUtil.isEmpty(nickname)) {
			nickname = "匿名";
		}
		wxActGoldeneggsRecord.setActId(actId);
		wxActGoldeneggsRecord.setOpenid(openid);
		wxActGoldeneggsRecord.setNickname(EmojiFilter.filterEmoji(nickname));
		wxActGoldeneggsRecord.setCreateTime(new Date());
		wxActGoldeneggsRecord.setAwardsName(awardsName);
		if (name == null && awardsName == null) {
			wxActGoldeneggsRecord.setPrizesState("0");
		} else {
			wxActGoldeneggsRecord.setPrizesState("1");
			wxActGoldeneggsRecord.setCode(code);
			// 设置中奖序号
			Integer maxAwardsSeq = wxActGoldeneggsRecordDao
					.getMaxAwardsSeq(wxActGoldeneggsRecord.getActId());
			Integer nextAwardsSeq = oConvertUtils.getInt(maxAwardsSeq)  + 1;
			wxActGoldeneggsRecord.setSeq(nextAwardsSeq);
		}
		wxActGoldeneggsRecord.setPrizesName(name);
		wxActGoldeneggsRecord.setJwid(jwid);
		wxActGoldeneggsRecord.setNickname(nickname);
		wxActGoldeneggsRecord.setAwardsId(awardId);
		wxActGoldeneggsRecordService.doAdd(wxActGoldeneggsRecord);// 插入中奖记录

		// 查询是openid是否存在，插入用户参与记录
		WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationDao
				.getOpenid(openid, actId);
		Date date = new Date();
		SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
		String update = sb.format(date);
		if (wxActGoldeneggsRegistration == null) {
			wxActGoldeneggsRegistration = new WxActGoldeneggsRegistration();
			wxActGoldeneggsRegistration.setActId(actId);
			wxActGoldeneggsRegistration.setOpenid(openid);
			wxActGoldeneggsRegistration.setAwardsNum(1);
			wxActGoldeneggsRegistration.setCreateTime(date);
			wxActGoldeneggsRegistration.setAwardsStatus("1");
			wxActGoldeneggsRegistration.setJwid(jwid);
			wxActGoldeneggsRegistration.setUpdateTime(update);
			wxActGoldeneggsRegistration.setRemainNumDay(1);
			wxActGoldeneggsRegistration.setNickname(EmojiFilter
					.filterEmoji(nickname));
			wxActGoldeneggsRegistrationDao.insert(wxActGoldeneggsRegistration);// 插入参与者的记录表
			// 返回奖品的名字和奖项的 名字
		} else {// 如果存在openid更新操作
			String updatetime = wxActGoldeneggsRegistration.getUpdateTime();
			String dd = sb.format(date);
			Integer awardsNum = null;// 抽奖剩余次数
			if (dd.equals(updatetime)) {// 如果是当前时间
				Integer remainnumdays = wxActGoldeneggsRegistration
						.getRemainNumDay();// 每天的剩余数量
				Integer awardsnums = wxActGoldeneggsRegistration.getAwardsNum();// 剩余的总数量
				if (remainnumdays >= wxActGoldeneggs.getNumPerDay()) {
					throw new GoldeneggsException(
							GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
							"今日抽奖次数已用完!");
				}
				if (awardsnums >= wxActGoldeneggs.getCount()) {
					throw new GoldeneggsException(
							GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
							"总抽奖次数已用完！");
				}
				int row = wxActGoldeneggsRegistrationDao.updateNumSameDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay(),dd, wxActGoldeneggsRegistration.getId());
				if (row == 0) {
					throw new OptimisticLockingException("乐观锁异常");
				}
			} else {// 当前时间在更新时间之后
				//update---begin------fanpengcheng---------20160928----------for:个人抽奖总次数和个人每日抽奖次数的修改
				awardsNum = wxActGoldeneggsRegistration.getAwardsNum();// 抽奖剩余次数
				if (awardsNum >= wxActGoldeneggs.getCount()) {
					throw new GoldeneggsException(
							GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
							"总抽奖次数已用完！");
				} 
				int row = wxActGoldeneggsRegistrationDao.updateNumDistinctDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay(), dd, wxActGoldeneggsRegistration.getId());
				if (row == 0) {
					throw new OptimisticLockingException("乐观锁异常");
				}
			}
		}
		return j;
	}
	/**
	 * 新模板抽奖
	 * @throws Exception 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson prizeRecordNew(WeixinDto weixinDto, AjaxJson j) throws Exception {
		String actId = weixinDto.getActId();
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String nickname = "";
		String headimgurl = "";
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		//砸金蛋奖品配置
		List<WxActGoldeneggsRelation> otherPrizeList = wxActGoldeneggsRelationService
		.queryByActId(actId);
		List<Double> list=new ArrayList<Double>();
		List<WxActGoldeneggsRelation> newPrizeList=new ArrayList<WxActGoldeneggsRelation>();
		//砸金蛋奖品概率
		for(WxActGoldeneggsRelation wxActGoldeneggsRelation:otherPrizeList){
			if(wxActGoldeneggsRelation.getRemainNum()!=null&&wxActGoldeneggsRelation.getRemainNum()>0){
				if(wxActGoldeneggsRelation.getProbability()!=null){
					list.add(wxActGoldeneggsRelation.getProbability().doubleValue());
					newPrizeList.add(wxActGoldeneggsRelation);
				}
			}
		}
		//抽奖
		int lottery = LotteryUtil.lottery(list);
		WxActGoldeneggsRelation relation=null;
		if(lottery>=0){
			relation=newPrizeList.get(lottery);
		}
		/*int index1=-1;
		for(WxActGoldeneggsRelation wxActGoldeneggsRelation:otherPrizeList){
			if(wxActGoldeneggsRelation.getRemainNum()!=null&&wxActGoldeneggsRelation.getRemainNum()>0){
				if(wxActGoldeneggsRelation.getProbability()!=null){
					index++;
					if(index==lottery&&wxActGoldeneggsRelation.getProbability().compareTo(BigDecimal.valueOf(list.get(lottery)))==0){
						relation=wxActGoldeneggsRelation;
						break;
					}
				}
			}
		}*/
		String name = null;// 奖品的名字
		String awardsName = null;// 奖项的名字
		Map<String, Object> mm = new HashMap<String, Object>();
		String relationId = null;
		String prizeId = null;
		String awardId = null;
		String code = null;
		if (relation == null || "".equals(relation)) {
			name = null;// 奖品的名字
			awardsName = null;// 奖项的名字
			j.setObj("2");
			mm.put("title","很遗憾您未中奖，请再接再励!");
			j.setAttributes(mm);
		} else {
			//奖项配置id
			relationId=relation.getId();
			//奖品id
			prizeId = relation.getPrizeId();
			//奖项id
			awardId = relation.getAwardId();
			WxActGoldeneggsPrizes prizes = wxActGoldeneggsPrizesService
			.queryById(prizeId);
			WxActGoldeneggsAwards awards = wxActGoldeneggsAwardsService
			.queryById(awardId);
			if (prizeId != null) {
				name = prizes.getName();// 奖品的名字
			}
			if (awards != null) {
				awardsName = awards.getAwardsName();// 奖品的等级
			}
			Integer rnamer = relation.getRemainNum();// 获取到剩余数量的值
			if (rnamer >= 1) {// 逻辑控制
				rnamer = rnamer - 1;
				relation.setRemainNum(rnamer);
				wxActGoldeneggsRelationService.doEdit(relation);// 修改商品的数量
			} else {
				//奖品已领取完
				j.setObj("2");
				mm.put("title","谢谢参与");
				j.setAttributes(mm);
			}
			//兑奖券
			code = getCoupon();
			mm.put("c_type", awardsName);
			mm.put("code", code);
			mm.put("name", name);
			mm.put("c_name", name);
			//图片
			mm.put("img", prizes.getImg());
			mm.put("c_img",prizes.getImg() );
			mm.put("title","恭喜您，中奖啦！请填写领奖信息");
			j.setAttributes(mm);
			j.setObj("1");
		}
		
		// 插入中奖记录表的数据
		WxActGoldeneggsRecord wxActGoldeneggsRecord = new WxActGoldeneggsRecord();
		JSONObject userobj = WeiXinHttpUtil.getGzUserInfo(openid, jwid);
		if (userobj != null && userobj.containsKey("nickname")) {
			nickname = userobj.getString("nickname");
		}
		if (StringUtil.isEmpty(nickname)) {
			nickname = "匿名";
		}
		if (userobj != null && userobj.containsKey("headimgurl")) {
			headimgurl = userobj.getString("headimgurl");
		}
		if (StringUtil.isEmpty(headimgurl)) {
			headimgurl = "/content/goldeneggs/img/youke.png";
		}
		wxActGoldeneggsRecord.setActId(actId);
		wxActGoldeneggsRecord.setOpenid(openid);
		wxActGoldeneggsRecord.setNickname(EmojiFilter.filterEmoji(nickname));
		wxActGoldeneggsRecord.setHeadimgurl(headimgurl);
		wxActGoldeneggsRecord.setCreateTime(new Date());
		wxActGoldeneggsRecord.setAwardsName(awardsName);
		if (name == null && awardsName == null) {
			wxActGoldeneggsRecord.setPrizesState("0");
		} else {
			wxActGoldeneggsRecord.setPrizesState("1");
			wxActGoldeneggsRecord.setCode(code);
			// 设置中奖序号
			Integer maxAwardsSeq = wxActGoldeneggsRecordDao
			.getMaxAwardsSeq(wxActGoldeneggsRecord.getActId());
			Integer nextAwardsSeq = oConvertUtils.getInt(maxAwardsSeq) + 1;
			wxActGoldeneggsRecord.setSeq(nextAwardsSeq);
		}
		wxActGoldeneggsRecord.setPrizesName(name);
		wxActGoldeneggsRecord.setRelationId(relationId);
		wxActGoldeneggsRecord.setJwid(jwid);
		wxActGoldeneggsRecord.setNickname(nickname);
		wxActGoldeneggsRecord.setAwardsId(awardId);
		wxActGoldeneggsRecordService.doAdd(wxActGoldeneggsRecord);// 插入中奖记录
		
		// 查询是openid是否存在，插入用户参与记录
		WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationDao
		.getOpenid(openid, actId);
		Date date = new Date();
		SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
		String update = sb.format(date);
		if (wxActGoldeneggsRegistration == null) {
			wxActGoldeneggsRegistration = new WxActGoldeneggsRegistration();
			wxActGoldeneggsRegistration.setActId(actId);
			wxActGoldeneggsRegistration.setOpenid(openid);
			wxActGoldeneggsRegistration.setAwardsNum(1);
			wxActGoldeneggsRegistration.setCreateTime(date);
			wxActGoldeneggsRegistration.setAwardsStatus("1");
			wxActGoldeneggsRegistration.setJwid(jwid);
			wxActGoldeneggsRegistration.setUpdateTime(update);
			wxActGoldeneggsRegistration.setRemainNumDay(1);
			wxActGoldeneggsRegistration.setNickname(EmojiFilter
					.filterEmoji(nickname));
			wxActGoldeneggsRegistrationDao.insert(wxActGoldeneggsRegistration);// 插入参与者的记录表
			// 返回奖品的名字和奖项的 名字
		} else {
			// 如果存在openid更新操作 判断是否分享得额外次数
			int extraCount=0;
			if("0".equals(wxActGoldeneggs.getExtraLuckyDraw())){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				List<WxActGoldeneggsShareRecord> wxActGoldeneggsShareRecord=wxActGoldeneggsShareRecordDao.queryShareRecordByDate(actId, openid, sd.format(new Date()), "");
				if(wxActGoldeneggsShareRecord.size()>0){
					extraCount=1;
				}
			}
			if(wxActGoldeneggs.getCount() !=0){
				String updatetime = wxActGoldeneggsRegistration.getUpdateTime();
				String dd = sb.format(date);
				Integer awardsNum = null;// 抽奖剩余次数
				if (dd.equals(updatetime)) {// 如果是当前时间
					Integer remainnumdays = wxActGoldeneggsRegistration
					.getRemainNumDay();// 每天的剩余数量
					Integer awardsnums = wxActGoldeneggsRegistration.getAwardsNum();// 剩余的总数量
					if (remainnumdays >= wxActGoldeneggs.getNumPerDay()+extraCount) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"今日抽奖次数已用完!");
					}
					if (awardsnums >= wxActGoldeneggs.getCount()) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"总抽奖次数已用完！");
					}
					int row = wxActGoldeneggsRegistrationDao.updateNumSameDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay()+extraCount,dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				} else {// 当前时间在更新时间之后
					//update---begin------fanpengcheng---------20160928----------for:个人抽奖总次数和个人每日抽奖次数的修改
					awardsNum = wxActGoldeneggsRegistration.getAwardsNum();// 抽奖剩余次数
					if (awardsNum >= wxActGoldeneggs.getCount()) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"总抽奖次数已用完！");
					} 
					int row = wxActGoldeneggsRegistrationDao.updateNumDistinctDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay()+extraCount, dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				}
			}else{
				String updatetime = wxActGoldeneggsRegistration.getUpdateTime();
				String dd = sb.format(date);
				Integer awardsNum = null;// 抽奖剩余次数
				if (dd.equals(updatetime)) {// 如果是当前时间
					Integer remainnumdays = wxActGoldeneggsRegistration
					.getRemainNumDay();// 每天的剩余数量
					Integer awardsnums = wxActGoldeneggsRegistration.getAwardsNum();// 剩余的总数量
					if (remainnumdays >= wxActGoldeneggs.getNumPerDay()+extraCount) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"今日抽奖次数已用完!");
					}
					int row = wxActGoldeneggsRegistrationDao.updateZeroCountNumSameDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay()+extraCount,dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				} else {
					int row = wxActGoldeneggsRegistrationDao.updateZeroCountNumDistinctDay(wxActGoldeneggs.getNumPerDay()+extraCount, dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				}
				
			}
		}
		return j;
	}
	/**
	 * 新模板抽奖不中奖
	 */
	@Transactional(rollbackFor = Exception.class)
	public AjaxJson noPrizeRecordNew(WeixinDto weixinDto, AjaxJson j) {
		String actId = weixinDto.getActId();
		String jwid = weixinDto.getJwid();
		String openid = weixinDto.getOpenid();
		String nickname = "";
		WxActGoldeneggs wxActGoldeneggs = wxActGoldeneggsService.queryById(actId);
		String name = null;// 奖品的名字
		String awardsName = null;// 奖项的名字
		Map<String, Object> mm = new HashMap<String, Object>();
		String prizeId = null;
		String awardId = null;
		j.setObj("2");
		mm.put("title","很遗憾您未中奖，请再接再励!");
		j.setAttributes(mm);
		// 插入中奖记录表的数据
		WxActGoldeneggsRecord wxActGoldeneggsRecord = new WxActGoldeneggsRecord();
		JSONObject userobj = WeiXinHttpUtil.getGzUserInfo(openid, jwid);
		if (userobj != null && userobj.containsKey("nickname")) {
			nickname = userobj.getString("nickname");
		}
		if (StringUtil.isEmpty(nickname)) {
			nickname = "匿名";
		}
		wxActGoldeneggsRecord.setActId(actId);
		wxActGoldeneggsRecord.setOpenid(openid);
		wxActGoldeneggsRecord.setNickname(EmojiFilter.filterEmoji(nickname));
		wxActGoldeneggsRecord.setCreateTime(new Date());
		wxActGoldeneggsRecord.setAwardsName(awardsName);
		wxActGoldeneggsRecord.setPrizesState("0");
		wxActGoldeneggsRecord.setPrizesName(name);
		wxActGoldeneggsRecord.setJwid(jwid);
		wxActGoldeneggsRecord.setNickname(nickname);
		wxActGoldeneggsRecord.setAwardsId(awardId);
		wxActGoldeneggsRecordService.doAdd(wxActGoldeneggsRecord);// 插入中奖记录
		// 查询是openid是否存在，插入用户参与记录
		WxActGoldeneggsRegistration wxActGoldeneggsRegistration = wxActGoldeneggsRegistrationDao
		.getOpenid(openid, actId);
		Date date = new Date();
		SimpleDateFormat sb = new SimpleDateFormat("yyyyMMdd");
		String update = sb.format(date);
		//判断是否存在
		if (wxActGoldeneggsRegistration == null) {
			wxActGoldeneggsRegistration = new WxActGoldeneggsRegistration();
			wxActGoldeneggsRegistration.setActId(actId);
			wxActGoldeneggsRegistration.setOpenid(openid);
			wxActGoldeneggsRegistration.setAwardsNum(1);
			wxActGoldeneggsRegistration.setCreateTime(date);
			wxActGoldeneggsRegistration.setAwardsStatus("1");
			wxActGoldeneggsRegistration.setJwid(jwid);
			wxActGoldeneggsRegistration.setUpdateTime(update);
			wxActGoldeneggsRegistration.setRemainNumDay(1);
			wxActGoldeneggsRegistration.setNickname(EmojiFilter
					.filterEmoji(nickname));
			wxActGoldeneggsRegistrationDao.insert(wxActGoldeneggsRegistration);// 插入参与者的记录表
		}else{
			//存在openid更新操作，判断抽奖总次数是否为0
			//判断是否分享得额外次数
			int extraCount=0;
			if("0".equals(wxActGoldeneggs.getExtraLuckyDraw())){
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				List<WxActGoldeneggsShareRecord> wxActGoldeneggsShareRecord=wxActGoldeneggsShareRecordDao.queryShareRecordByDate(actId, openid, sd.format(new Date()), "");
				if(wxActGoldeneggsShareRecord.size()>0){
					extraCount=1;
				}
			}
			if(wxActGoldeneggs.getCount() !=0){
				String updatetime = wxActGoldeneggsRegistration.getUpdateTime();
				String dd = sb.format(date);
				Integer awardsNum = null;// 抽奖剩余次数
				if (dd.equals(updatetime)) {// 如果是当前时间
					Integer remainnumdays = wxActGoldeneggsRegistration
					.getRemainNumDay();// 每天的剩余数量
					Integer awardsnums = wxActGoldeneggsRegistration.getAwardsNum();// 剩余的总数量
					if (remainnumdays >= wxActGoldeneggs.getNumPerDay()+extraCount) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"今日抽奖次数已用完!");
					}
					if (awardsnums >= wxActGoldeneggs.getCount()) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"总抽奖次数已用完！");
					}
					int row = wxActGoldeneggsRegistrationDao.updateNumSameDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay()+extraCount,dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				} else {// 当前时间在更新时间之后
					//update---begin------fanpengcheng---------20160928----------for:个人抽奖总次数和个人每日抽奖次数的修改
					awardsNum = wxActGoldeneggsRegistration.getAwardsNum();// 抽奖剩余次数
					if (awardsNum >= wxActGoldeneggs.getCount()) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"总抽奖次数已用完！");
					} 
					int row = wxActGoldeneggsRegistrationDao.updateNumDistinctDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay(), dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				}
			}else{
				String updatetime = wxActGoldeneggsRegistration.getUpdateTime();
				String dd = sb.format(date);
				Integer awardsNum = null;// 抽奖剩余次数
				if (dd.equals(updatetime)) {// 如果是当前时间
					Integer remainnumdays = wxActGoldeneggsRegistration
					.getRemainNumDay();// 每天的剩余数量
					Integer awardsnums = wxActGoldeneggsRegistration.getAwardsNum();// 剩余的总数量
					if (remainnumdays >= wxActGoldeneggs.getNumPerDay()+extraCount) {
						throw new GoldeneggsException(
								GoldeneggsExceptionEnum.DATA_NOT_EXIST_ERROR,
						"今日抽奖次数已用完!");
					}
					int row = wxActGoldeneggsRegistrationDao.updateZeroCountNumSameDay(wxActGoldeneggs.getCount(), wxActGoldeneggs.getNumPerDay()+extraCount,dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				} else {
					int row = wxActGoldeneggsRegistrationDao.updateZeroCountNumDistinctDay(wxActGoldeneggs.getNumPerDay()+extraCount, dd, wxActGoldeneggsRegistration.getId());
					if (row == 0) {
						throw new OptimisticLockingException("乐观锁异常");
					}
				}
				
			}
		}
		return j;
	}
	/**
	 * 生成12位数字,大写字母,小写字母随机的券码，拼接100到999的随机数，共20位
	 * @return 生成后的券码
	 */
	private synchronized static String getCoupon(){
		char ch[]=new char[]{'0','1','2','3','4','5','6','7','8','9'
				,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				};
		Random rand = new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<12;i++){
			sb.append(ch[rand.nextInt(62)]);
		}
		return sb.toString();
	}

	@Override
	public Integer queryCountByActId(String id) {
		return wxActGoldeneggsRegistrationDao.queryCountByActId(id);
	}
}
