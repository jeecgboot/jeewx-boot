package com.jeecg.p3.goldeneggs.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.baseApi.service.BaseApiActTxtService;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsAwardsDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsPrizesDao;
import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsRelationDao;
import com.jeecg.p3.goldeneggs.def.SystemGoldProperties;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggs;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;
import com.jeecg.p3.goldeneggs.service.WxActGoldeneggsService;


@Service("wxActGoldeneggsService")
public class WxActGoldeneggsServiceImpl implements WxActGoldeneggsService {
	@Resource
	private WxActGoldeneggsDao wxActGoldeneggsDao;
	@Autowired
	private BaseApiActTxtService baseApiActTxtService;
	@Autowired
	private WxActGoldeneggsRelationDao wxActGoldeneggsRelationDao;
	@Resource
	private WxActGoldeneggsAwardsDao wxActGoldeneggsAwardsDao;
	@Resource
	private WxActGoldeneggsPrizesDao wxActGoldeneggsPrizesDao;
	
	private static String defaultJwid = SystemGoldProperties.defaultJwid;
	private static String oldActCode = SystemGoldProperties.oldActCode;
	
	
	public final static Logger log = LoggerFactory.getLogger(WxActGoldeneggsServiceImpl.class);
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAdd(WxActGoldeneggs wxActGoldeneggs) {
		wxActGoldeneggs.setTemplateCode("hd0921");
		wxActGoldeneggs.setProjectCode("goldeneggs");
		wxActGoldeneggsDao.insert(wxActGoldeneggs);
		List<WxActGoldeneggsRelation> awardsList =wxActGoldeneggs.getAwarsList();
			if(awardsList!=null){
				for (WxActGoldeneggsRelation wxActGoldeneggsRelation : awardsList) {
					//--update-begin--date:2018-3-27 18:14:03 author:liwenhui for:判断奖项是否为空,为空则增加
					if(StringUtils.isEmpty(wxActGoldeneggsRelation.getAwardId())){
						wxActGoldeneggsRelation.setAwardId(saveAwards(wxActGoldeneggsRelation.getAwardName()));
					}else{
						WxActGoldeneggsAwards wxActGoldeneggsAwards = wxActGoldeneggsAwardsDao.get(wxActGoldeneggsRelation.getAwardId());
						//判断awardId和awardName是否是匹配的，不匹配则增加
						if(!wxActGoldeneggsAwards.getAwardsName().equals(wxActGoldeneggsRelation.getAwardName())){
							wxActGoldeneggsRelation.setAwardId(saveAwards(wxActGoldeneggsRelation.getAwardName()));
						}
					}
					//--update-end--date:2018-3-27 18:14:03 author:liwenhui for:判断奖项是否为空,为空则增加
					//--update-begin--date:2018-3-27 18:14:03 author:liwenhui for:判断奖品是否为空,为空则增加
					if(StringUtils.isEmpty(wxActGoldeneggsRelation.getPrizeId())){
						  wxActGoldeneggsRelation.setPrizeId(savePrizes(wxActGoldeneggsRelation.getPrizeName(),wxActGoldeneggsRelation.getAwardImg()));
					}else{
						  WxActGoldeneggsPrizes wxActGoldeneggsPrizes = wxActGoldeneggsPrizesDao.get(wxActGoldeneggsRelation.getPrizeId());
						//prizeId和prizeName是否是匹配的，不匹配则增加
						 log.info("------奖品名称----wxActGoldeneggsPrizes:" + wxActGoldeneggsPrizes.getName());
						 log.info("------奖项奖品名称----wxActGoldeneggsRelation:" + wxActGoldeneggsRelation.getPrizeName());
						if(!wxActGoldeneggsPrizes.getName().equals(wxActGoldeneggsRelation.getPrizeName())){
							wxActGoldeneggsRelation.setPrizeId(savePrizes(wxActGoldeneggsRelation.getPrizeName(),wxActGoldeneggsRelation.getAwardImg()));
						}
						 log.info("------奖品图片----wxActGoldeneggsPrizes:" + wxActGoldeneggsPrizes.getImg());
						 log.info("------奖项奖品图片----wxActGoldeneggsRelation:" + wxActGoldeneggsRelation.getAwardImg());
						//prizeId和prizeName以及img和AwardImg图片是否是匹配的，不匹配则增加
						if(StringUtils.isNotEmpty(wxActGoldeneggsRelation.getAwardImg()) && StringUtils.isNotEmpty(wxActGoldeneggsPrizes.getImg())){
							if(wxActGoldeneggsPrizes.getName().equals(wxActGoldeneggsRelation.getPrizeName())&& !wxActGoldeneggsPrizes.getImg().equals(wxActGoldeneggsRelation.getAwardImg())){
								wxActGoldeneggsRelation.setPrizeId(savePrizes(wxActGoldeneggsRelation.getPrizeName(),wxActGoldeneggsRelation.getAwardImg()));
							}
						}
					}
					//--update-end--date:2018-3-27 18:14:03 author:liwenhui for:判断奖品是否为空,为空则增加
					
					
					wxActGoldeneggsRelation.setActId(wxActGoldeneggs.getId());
					wxActGoldeneggsRelation.setRemainNum(wxActGoldeneggsRelation.getAmount());
					if(wxActGoldeneggsRelation.getProbability()==null){
						wxActGoldeneggsRelation.setProbability(new BigDecimal("0"));
						//update-begin-zhangweijian-----Date:20180830----for:概率转换
					}else{
						BigDecimal pencet=new BigDecimal("100");
						wxActGoldeneggsRelation.setProbability(wxActGoldeneggsRelation.getProbability().divide(pencet));
						//update-end-zhangweijian-----Date:20180830----for:概率转换
					}
				}
				for (WxActGoldeneggsRelation wxActGoldeneggsRelation : awardsList) {
					wxActGoldeneggsRelationDao.insert(wxActGoldeneggsRelation);
				}
			}
		
			baseApiActTxtService.copyActText(oldActCode, wxActGoldeneggs.getId());
		
	}
	
	/**
	 * @功能:保存奖项
	 * @作者:liwenhui 
	 * @时间:2018-3-28 上午09:56:47
	 * @修改：
	 * @param awardName
	 * @return
	 * @throws Exception  
	 */
	private String saveAwards(String awardName){
		WxActGoldeneggsAwards wxActGoldeneggsAwards=new WxActGoldeneggsAwards();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		String createBy = ContextHolderUtils.getSession().getAttribute("system_userid").toString();
		if(defaultJwid.equals(jwid)){
			//判断当前奖项是否存在
			List<WxActGoldeneggsAwards> queryAwardsByName = wxActGoldeneggsAwardsDao.queryAwardsByName(jwid, createBy, awardName);
			if (queryAwardsByName.size()>0) {
				return queryAwardsByName.get(0).getId();
			}
		}else{
			//判断当前奖项是否存在
			List<WxActGoldeneggsAwards> queryAwardsByName = wxActGoldeneggsAwardsDao.queryAwardsByName(jwid, "", awardName);
			if (queryAwardsByName.size()>0) {
				return queryAwardsByName.get(0).getId();
			}
		}
		wxActGoldeneggsAwards.setCreateBy(createBy);
		wxActGoldeneggsAwards.setJwid(jwid);
		wxActGoldeneggsAwards.setAwardsName(awardName);
		wxActGoldeneggsAwardsDao.insert(wxActGoldeneggsAwards);
		return  wxActGoldeneggsAwards.getId();
	}
	
	
	/**
	 * @功能:保存奖品
	 * @作者:liwenhui 
	 * @时间:2018-3-28 上午10:00:09
	 * @修改：
	 * @param prizeName
	 * @return  
	 */
	private String savePrizes(String prizeName,String prizeImg){
		WxActGoldeneggsPrizes wxActGoldeneggsPrizes=new WxActGoldeneggsPrizes();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		String createBy = ContextHolderUtils.getSession().getAttribute("system_userid").toString();
		//查询奖品是否已经存在
		List<WxActGoldeneggsPrizes> queryPrizesByName = wxActGoldeneggsPrizesDao.queryPrizesByName(jwid, createBy, prizeName);
		if (queryPrizesByName.size()>0) {
			queryPrizesByName.get(0).setImg(prizeImg);
			wxActGoldeneggsPrizesDao.update(queryPrizesByName.get(0));
			return queryPrizesByName.get(0).getId();
		}
		wxActGoldeneggsPrizes.setCreateBy(createBy);
		wxActGoldeneggsPrizes.setJwid(jwid);
		wxActGoldeneggsPrizes.setName(prizeName);
		wxActGoldeneggsPrizes.setImg(prizeImg);
		wxActGoldeneggsPrizesDao.insert(wxActGoldeneggsPrizes);
		return  wxActGoldeneggsPrizes.getId();
	}
	

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String doEdit(WxActGoldeneggs wxActGoldeneggs) {
		String msg="";
		try {
			wxActGoldeneggsDao.update(wxActGoldeneggs);
			List<WxActGoldeneggsRelation> newAwardsList= wxActGoldeneggs.getAwarsList();//新的明细配置集合
			List<String> ids=new ArrayList<String>();
			if(newAwardsList!=null){
				for (WxActGoldeneggsRelation relation : newAwardsList) {
					
					//--update-begin--date:2018-3-27 18:14:03 author:liwenhui for:判断奖项是否为空,为空则增加
					if(StringUtils.isEmpty(relation.getAwardId())){
						relation.setAwardId(saveAwards(relation.getAwardName()));
					}else{
						WxActGoldeneggsAwards wxActGoldeneggsAwards = wxActGoldeneggsAwardsDao.get(relation.getAwardId());
						//判断awardId和awardName是否是匹配的，不匹配则增加
						if(!wxActGoldeneggsAwards.getAwardsName().equals(relation.getAwardName())){
							relation.setAwardId(saveAwards(relation.getAwardName()));
						}
					}
					//--update-end--date:2018-3-27 18:14:03 author:liwenhui for:判断奖项是否为空,为空则增加
					//--update-begin--date:2018-3-27 18:14:03 author:liwenhui for:判断奖品是否为空,为空则增加
					if(StringUtils.isEmpty(relation.getPrizeId())){
						relation.setPrizeId(savePrizes(relation.getPrizeName(),relation.getAwardImg()));
					}else{
						WxActGoldeneggsPrizes wxActGoldeneggsPrizes = wxActGoldeneggsPrizesDao.get(relation.getPrizeId());
						//prizeId和prizeName是否是匹配的，不匹配则增加
						if(!wxActGoldeneggsPrizes.getName().equals(relation.getPrizeName())){
							relation.setPrizeId(savePrizes(relation.getPrizeName(),relation.getAwardImg()));
						}
						//prizeId和prizeName以及img和AwardImg图片是否是匹配的，不匹配则增加
						if(StringUtils.isNotEmpty(relation.getAwardImg()) && StringUtils.isNotEmpty(wxActGoldeneggsPrizes.getImg())){
							if(wxActGoldeneggsPrizes.getName().equals(relation.getPrizeName())&& !wxActGoldeneggsPrizes.getImg().equals(relation.getAwardImg())){
								relation.setPrizeId(savePrizes(relation.getPrizeName(),relation.getAwardImg()));
							}
						}
					}
					//--update-end--date:2018-3-27 18:14:03 author:liwenhui for:判断奖品是否为空,为空则增加
					
					if(StringUtils.isNotEmpty(relation.getId())){				
						ids.add(relation.getId());
					}
				}
				
				//批量修改奖品数量
				for (WxActGoldeneggsRelation wxActGoldeneggsRelation : newAwardsList) {
					//获取该活动该奖品已经被抽中的数量，如果修改后的奖品总数比抽中数量少，则抛出提示语
					if(org.jeecgframework.p3.core.utils.common.StringUtils.isNotEmpty(wxActGoldeneggsRelation.getId())){
						WxActGoldeneggsRelation relation = wxActGoldeneggsRelationDao.get(wxActGoldeneggsRelation.getId());
						Integer outNum = relation.getAmount()-relation.getRemainNum();
						if(wxActGoldeneggsRelation.getAmount()<outNum){
							WxActGoldeneggsAwards awards = wxActGoldeneggsAwardsDao.get(relation.getAwardId());
							msg = msg + awards.getAwardsName()+"奖品数量不得小于已抽出数量 ："+outNum+";</br>";
							return msg;
						}else{
							Integer newRemainNum = 0;
							if(wxActGoldeneggsRelation.getAmount()>relation.getAmount()){                 
								newRemainNum = wxActGoldeneggsRelation.getAmount()-relation.getAmount()+relation.getRemainNum();
							}else{
								newRemainNum = relation.getRemainNum()-(relation.getAmount()-wxActGoldeneggsRelation.getAmount());
							}
							wxActGoldeneggsRelation.setRemainNum(newRemainNum);
						}
					}
				}
				
				wxActGoldeneggsRelationDao.bactchDeleteOldAwards(ids,wxActGoldeneggs.getId());//批量删除不在新的明细配置集合的数据
				for (WxActGoldeneggsRelation wxActGoldeneggsRelation : newAwardsList) {
					//update-begin-zhangweijian-----Date:20180830----for:概率转换
					if(wxActGoldeneggsRelation.getProbability()==null){
						wxActGoldeneggsRelation.setProbability(new BigDecimal("0"));
					}else{
						BigDecimal pencet=new BigDecimal("100");
						wxActGoldeneggsRelation.setProbability(wxActGoldeneggsRelation.getProbability().divide(pencet));
					}
					//update-end-zhangweijian-----Date:20180830----for:概率转换
					if(StringUtils.isEmpty(wxActGoldeneggsRelation.getId())){
						wxActGoldeneggsRelation.setActId(wxActGoldeneggs.getId());
						wxActGoldeneggsRelation.setRemainNum(wxActGoldeneggsRelation.getAmount());
						wxActGoldeneggsRelationDao.insert(wxActGoldeneggsRelation);
					}else{
						wxActGoldeneggsRelationDao.update(wxActGoldeneggsRelation);
					}
				}
			}else{
				wxActGoldeneggsRelationDao.bactchDeleteOldAwards(ids,wxActGoldeneggs.getId());//批量删除不在新的明细配置集合的数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}


	@Override
	public void doDelete(String id) {
		wxActGoldeneggsDao.delete(id);
		wxActGoldeneggsRelationDao.batchDeleteByActId(id);//同步活动明细配置
		baseApiActTxtService.batchDeleteByActCode(id);//同步删除系统文本
	}

	@Override
	public WxActGoldeneggs queryById(String id) {
		WxActGoldeneggs wxActGoldeneggs  = wxActGoldeneggsDao.get(id);
		return wxActGoldeneggs;
	}

	@Override
	public PageList<WxActGoldeneggs> queryPageList(
		PageQuery<WxActGoldeneggs> pageQuery) {
		PageList<WxActGoldeneggs> result = new PageList<WxActGoldeneggs>();
		Integer itemCount = wxActGoldeneggsDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggs> wrapper = new PageQueryWrapper<WxActGoldeneggs>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggs> list = wxActGoldeneggsDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public void editShortUrl(String id, String shortUrl) {
		wxActGoldeneggsDao.editShortUrl(id,shortUrl);
	}

	
}
