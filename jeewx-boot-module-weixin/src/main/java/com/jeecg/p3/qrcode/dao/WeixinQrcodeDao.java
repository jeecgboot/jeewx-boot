package com.jeecg.p3.qrcode.dao;

import com.jeecg.p3.qrcode.entity.WeixinQrcode;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>二维码表<br>
 * @author：sunkai
 * @since：2018年08月30日 10时29分25秒 星期四 
 * @version:1.0
 */
public interface WeixinQrcodeDao extends GenericDao<WeixinQrcode>{
	
	public Integer count(PageQuery<WeixinQrcode> pageQuery);
	
	public List<WeixinQrcode> queryPageList(PageQueryWrapper<WeixinQrcode> wrapper);

	Integer queryMaxSceneId(@Param("weixinQrcode") WeixinQrcode weixinQrcode);

	public List<WeixinQrcode> queryBySceneId(@Param("sceneid")String sceneid, @Param("toUserName")String toUserName);

}

