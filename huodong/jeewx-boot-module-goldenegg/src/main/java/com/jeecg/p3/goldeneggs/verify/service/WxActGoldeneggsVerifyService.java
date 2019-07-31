package com.jeecg.p3.goldeneggs.verify.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;

/**
 * 描述：</b>砸金蛋审核员表<br>
 * @author：junfeng.zhou
 * @since：2018年10月17日 09时53分08秒 星期三 
 * @version:1.0
 */
public interface WxActGoldeneggsVerifyService {
	
	
	public void doAdd(WxActGoldeneggsVerify wxActGoldeneggsVerify);
	
	public void doEdit(WxActGoldeneggsVerify wxActGoldeneggsVerify);
	
	public void doDelete(String id);
	
	public WxActGoldeneggsVerify queryById(String id);
	
	public PageList<WxActGoldeneggsVerify> queryPageList(PageQuery<WxActGoldeneggsVerify> pageQuery);

	public WxActGoldeneggsVerify queryByOpenId(String openid, String actId);

	public WxActGoldeneggsVerify queryAllGoldeneggs(String actId, String cardPsd);
}

