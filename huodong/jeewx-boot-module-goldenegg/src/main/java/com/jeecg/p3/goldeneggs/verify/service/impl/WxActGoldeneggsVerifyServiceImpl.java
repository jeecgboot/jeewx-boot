package com.jeecg.p3.goldeneggs.verify.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.goldeneggs.dao.WxActGoldeneggsPrizesDao;
import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsPrizes;
import com.jeecg.p3.goldeneggs.verify.service.WxActGoldeneggsVerifyService;
import com.jeecg.p3.goldeneggs.verify.entity.WxActGoldeneggsVerify;
import com.jeecg.p3.goldeneggs.verify.dao.WxActGoldeneggsVerifyDao;

/**
 * 描述：</b>砸金蛋审核员表<br>
 * @author：junfeng.zhou
 * @since：2018年10月17日 09时53分08秒 星期三 
 * @version:1.0
 */
@Service("wxActGoldeneggsVerifyService")
public class WxActGoldeneggsVerifyServiceImpl implements WxActGoldeneggsVerifyService {
	@Resource
	private WxActGoldeneggsVerifyDao wxActGoldeneggsVerifyDao;
	@Resource
	private WxActGoldeneggsPrizesDao wxActGoldeneggsPrizesDao;

	@Override
	public void doAdd(WxActGoldeneggsVerify wxActGoldeneggsVerify) {
		wxActGoldeneggsVerifyDao.insert(wxActGoldeneggsVerify);
	}

	@Override
	public void doEdit(WxActGoldeneggsVerify wxActGoldeneggsVerify) {
		wxActGoldeneggsVerifyDao.update(wxActGoldeneggsVerify);
	}

	@Override
	public void doDelete(String id) {
		wxActGoldeneggsVerifyDao.delete(id);
	}

	@Override
	public WxActGoldeneggsVerify queryById(String id) {
		WxActGoldeneggsVerify wxActGoldeneggsVerify  = wxActGoldeneggsVerifyDao.get(id);
		return wxActGoldeneggsVerify;
	}

	@Override
	public PageList<WxActGoldeneggsVerify> queryPageList(
		PageQuery<WxActGoldeneggsVerify> pageQuery) {
		PageList<WxActGoldeneggsVerify> result = new PageList<WxActGoldeneggsVerify>();
		Integer itemCount = wxActGoldeneggsVerifyDao.count(pageQuery);
		PageQueryWrapper<WxActGoldeneggsVerify> wrapper = new PageQueryWrapper<WxActGoldeneggsVerify>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WxActGoldeneggsVerify> list = wxActGoldeneggsVerifyDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActGoldeneggsVerify queryByOpenId(String openid, String actId) {
		return wxActGoldeneggsVerifyDao.queryByOpenId(openid,actId);
	}

	@Override
	public WxActGoldeneggsVerify queryAllGoldeneggs(String actId, String cardPsd) {
		WxActGoldeneggsVerify verify = wxActGoldeneggsVerifyDao.queryAllGoldeneggs(actId,cardPsd);
		WxActGoldeneggsPrizes wxActGoldeneggsPrizes=wxActGoldeneggsPrizesDao.queryByActIdAndCode(actId,cardPsd);
		if(wxActGoldeneggsPrizes!=null && wxActGoldeneggsPrizes.getImg()!=null){
			verify.setImg(wxActGoldeneggsPrizes.getImg());
		}
		return verify;
	}
	
}
