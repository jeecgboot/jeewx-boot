package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinReceptMsg;

/**
 * 描述：</b>客服消息记录表<br>
 * @author：junfeng.zhou
 * @since：2018年10月18日 19时35分31秒 星期四 
 * @version:1.0
 */
public interface WeixinReceptMsgDao extends GenericDao<WeixinReceptMsg>{
	
	public Integer count(PageQuery<WeixinReceptMsg> pageQuery);
	
	public List<WeixinReceptMsg> queryPageList(PageQueryWrapper<WeixinReceptMsg> wrapper);
	
}

