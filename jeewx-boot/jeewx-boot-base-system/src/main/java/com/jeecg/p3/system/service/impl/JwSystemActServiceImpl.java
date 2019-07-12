package com.jeecg.p3.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.system.dao.JwSystemActDao;
import com.jeecg.p3.system.entity.JwSystemAct;
import com.jeecg.p3.system.service.JwSystemActService;

/**
 * 描述：</b>平台活动表<br>
 * @author：Alex
 * @since：2017年09月30日 10时05分08秒 星期六 
 * @version:1.0
 */
@Service("jwSystemActService")
public class JwSystemActServiceImpl implements JwSystemActService {
	@Resource
	private JwSystemActDao jwSystemActDao;

	@Override
	public void doAdd(JwSystemAct jwSystemAct) {
		jwSystemActDao.insert(jwSystemAct);
	}

	@Override
	public void doEdit(JwSystemAct jwSystemAct) {
		jwSystemActDao.update(jwSystemAct);
	}

	@Override
	public void doDelete(String id) {
		jwSystemActDao.delete(id);
	}

	@Override
	public JwSystemAct queryById(String id) {
		JwSystemAct jwSystemAct  = jwSystemActDao.get(id);
		return jwSystemAct;
	}

	@Override
	public PageList<JwSystemAct> queryPageList(PageQuery<JwSystemAct> pageQuery) {
		PageList<JwSystemAct> result = new PageList<JwSystemAct>();
		Integer itemCount = jwSystemActDao.count(pageQuery);
		PageQueryWrapper<JwSystemAct> wrapper = new PageQueryWrapper<JwSystemAct>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemAct> list = jwSystemActDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
	@Override
	public void updateJoinShareNum(String actId){
		jwSystemActDao.updateJoinShareNum(actId);
	}

	//update-begin--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
	//查询参与人数>100的活动信息
	@Override
	public List<JwSystemAct> queryByJoinNum(int joinNum,Date date) {
		return jwSystemActDao.queryByJoinNum(joinNum,date);
	}
	//update-end--Author:zhangweijian  Date: 20180629 for：查询参与人数>100的活动信息
	
}
