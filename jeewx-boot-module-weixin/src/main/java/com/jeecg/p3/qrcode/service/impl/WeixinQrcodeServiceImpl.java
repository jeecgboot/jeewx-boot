package com.jeecg.p3.qrcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;

import com.jeecg.p3.qrcode.service.WeixinQrcodeService;
import com.jeecg.p3.qrcode.web.back.WeixinQrcodeController;
import com.jeecg.p3.qrcode.entity.WeixinQrcode;
import com.jeecg.p3.qrcode.dao.WeixinQrcodeDao;

/**
 * 描述：</b>二维码表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时29分25秒 星期四 
 * @version:1.0
 */
@Service("weixinQrcodeService")
public class WeixinQrcodeServiceImpl implements WeixinQrcodeService {
	@Resource
	private WeixinQrcodeDao weixinQrcodeDao;

	@Override
	public void doAdd(WeixinQrcode weixinQrcode) {
		weixinQrcodeDao.insert(weixinQrcode);
	}

	@Override
	public void doEdit(WeixinQrcode weixinQrcode) {
		weixinQrcodeDao.update(weixinQrcode);
	}

	@Override
	public void doDelete(String id) {
		weixinQrcodeDao.delete(id);
	}

	@Override
	public WeixinQrcode queryById(String id) {
		WeixinQrcode weixinQrcode  = weixinQrcodeDao.get(id);
		return weixinQrcode;
	}

	@Override
	public PageList<WeixinQrcode> queryPageList(
		PageQuery<WeixinQrcode> pageQuery) {
		PageList<WeixinQrcode> result = new PageList<WeixinQrcode>();
		Integer itemCount = weixinQrcodeDao.count(pageQuery);
		PageQueryWrapper<WeixinQrcode> wrapper = new PageQueryWrapper<WeixinQrcode>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<WeixinQrcode> list = weixinQrcodeDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Integer getScene(WeixinQrcode weixinQrcode) {
		int maxScenekey = 0;
		//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
		Integer max =weixinQrcodeDao.queryMaxSceneId(weixinQrcode);
		synchronized (WeixinQrcodeController.class) {
		    if(max == null){
		    	maxScenekey = 1;
		    }else{
		    	maxScenekey = max.intValue()+1;
		    }
		    // 临时二维码Scenceid从10w以后开始
	    	if(maxScenekey<=100000 && "QR_SCENE".equals(weixinQrcode.getActionName())){
	    		maxScenekey = 100001;
	    	}
		}
		return maxScenekey;
	}

	@Override
	public List<WeixinQrcode> queryBySceneId(String sceneid,String toUserName) {
		return weixinQrcodeDao.queryBySceneId(sceneid,toUserName);
	}

}
