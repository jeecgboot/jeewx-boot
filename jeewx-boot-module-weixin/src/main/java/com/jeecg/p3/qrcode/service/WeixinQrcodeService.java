package com.jeecg.p3.qrcode.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.qrcode.entity.WeixinQrcode;

/**
 * 描述：</b>二维码表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时29分25秒 星期四 
 * @version:1.0
 */
public interface WeixinQrcodeService {
	
	
	public void doAdd(WeixinQrcode weixinQrcode);
	
	public void doEdit(WeixinQrcode weixinQrcode);
	
	public void doDelete(String id);
	
	public WeixinQrcode queryById(String id);
	
	public PageList<WeixinQrcode> queryPageList(PageQuery<WeixinQrcode> pageQuery);

	public Integer getScene(WeixinQrcode weixinQrcode);

	public List<WeixinQrcode> queryBySceneId(String sceneid, String toUserName);

}

