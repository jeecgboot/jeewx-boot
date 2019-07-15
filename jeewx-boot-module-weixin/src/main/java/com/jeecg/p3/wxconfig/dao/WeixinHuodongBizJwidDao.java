package com.jeecg.p3.wxconfig.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.wxconfig.entity.WeixinHuodongBizJwid;

/**
 * 描述：</b>微信活动jwid表<br>
 * @author：weijian.zhang
 * @since：2018年08月08日 09时32分33秒 星期三 
 * @version:1.0
 */
public interface WeixinHuodongBizJwidDao extends GenericDao<WeixinHuodongBizJwid>{
	
	public Integer count(PageQuery<WeixinHuodongBizJwid> pageQuery);
	
	public List<WeixinHuodongBizJwid> queryPageList(PageQueryWrapper<WeixinHuodongBizJwid> wrapper);

	//update-begin-zhangweijian-----Date:20180808---for:变更微信活动jwid表公众号原始ID
	/**
	 * @功能：变更微信活动jwid表公众号原始ID
	 * @param tableName
	 * @param jwid
	 * @param newJwid
	 */
	public void updateTable(@Param("tableName")String tableName, @Param("jwid")String jwid, @Param("newJwid")String newJwid);
	//update-end-zhangweijian-----Date:20180808---for:变更微信活动jwid表公众号原始ID

	//update-begin-zhangweijian-----Date:20180808---for:获取所有微信活动jwid表数据
	/**
	 * @功能：获取所有微信活动jwid表数据
	 * @return
	 */
	public List<WeixinHuodongBizJwid> queryAll();
	//update-end-zhangweijian-----Date:20180808---for:获取所有微信活动jwid表数据

	/**
	 * @功能：根据表名和jwid查询hdurl
	 */
	public List<Map<String, Object>> queryHdurls(@Param("tableName")String tableName,@Param("jwid")String jwid);
	
	//update-begin--Author:zhangweijian Date:20181011 for：更新活动长短链接
	/**
	 * @功能：更新活动长短链接
	 */
	public void updateShortUrl(@Param("tableName")String tableName, @Param("id")String id, @Param("jwid")String jwid, @Param("newJwid")String newJwid, @Param("shortUrl")String shortUrl);
	//update-end--Author:zhangweijian Date:20181011 for：更新活动长短链接
}

