package com.jeecg.p3.goldeneggs.service;

import java.text.ParseException;
import java.util.List;

import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRegistration;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsRelation;

/**
 * 描述：</b>WxActGoldeneggsRegistrationService<br>
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分28秒 星期二 
 * @version:1.0
 */
public interface WxActGoldeneggsRegistrationService {
	
	
	public void doAdd(WxActGoldeneggsRegistration wxActGoldeneggsRegistration);
	
	public void doEdit(WxActGoldeneggsRegistration wxActGoldeneggsRegistration);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsRegistration queryById(String id);
	
	public PageList<WxActGoldeneggsRegistration> queryPageList(PageQuery<WxActGoldeneggsRegistration> pageQuery);

	public WxActGoldeneggsRegistration getOpenid(String openid, String actId);
	public  WxActGoldeneggsRelation calcOtherPrizePercentage(List<WxActGoldeneggsRelation> otherPrizeList);

	public AjaxJson prizeRecord(WeixinDto weixinDto,AjaxJson j)throws ParseException;

	public AjaxJson prizeRecordNew(WeixinDto weixinDto, AjaxJson j) throws Exception;

	public AjaxJson noPrizeRecordNew(WeixinDto weixinDto, AjaxJson j);

	public Integer queryCountByActId(String id);
	
}

