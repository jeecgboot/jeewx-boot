package com.jeecg.p3.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.jeecg.p3.system.def.SystemProperties;
import org.apache.commons.lang.StringUtil;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jeecg.p3.system.dao.JwSystemProjectDao;
import com.jeecg.p3.system.entity.JwSystemProject;
import com.jeecg.p3.system.service.JwSystemProjectService;

@Service("jwSystemProjectService")
public class JwSystemProjectServiceImpl implements JwSystemProjectService {
	 public final static Logger logger = LoggerFactory.getLogger(JwSystemProjectServiceImpl.class);
	 private static String domain = SystemProperties.domain;
	 
	@Resource
	private JwSystemProjectDao jwSystemProjectDao;

	@Override
	public void doAdd(JwSystemProject jwSystemProject) {
		jwSystemProjectDao.insert(jwSystemProject);
	}

	@Override
	public void doEdit(JwSystemProject jwSystemProject) {
		jwSystemProjectDao.update(jwSystemProject);
	}

	@Override
	public void doDelete(String id) {
		jwSystemProjectDao.delete(id);
	}

	@Override
	public JwSystemProject queryById(String id) {
		JwSystemProject jwSystemProject  = jwSystemProjectDao.get(id);
		if (jwSystemProject.getHdurl()!=null && !"${domain}".equals(jwSystemProject.getHdurl().substring(0,9))) {
			String hdurl = "${domain}" + jwSystemProject.getHdurl();
			jwSystemProject.setHdurl(hdurl);			
		}
		return jwSystemProject;
	}

	@Override
	public PageList<JwSystemProject> queryPageList(
		PageQuery<JwSystemProject> pageQuery) {
		PageList<JwSystemProject> result = new PageList<JwSystemProject>();
		Integer itemCount = jwSystemProjectDao.count(pageQuery);
		PageQueryWrapper<JwSystemProject> wrapper = new PageQueryWrapper<JwSystemProject>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		List<JwSystemProject> list = jwSystemProjectDao.queryPageList(wrapper);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public Boolean validReat(String code, String id) {
		int i= jwSystemProjectDao.validReat(code, id);
		return (i>0);
	}

	@Override
	public List<JwSystemProject> queryListByType(String type) {
		return jwSystemProjectDao.queryListByType(type);
	}

	@Override
	public List<JwSystemProject> queryListByProjectClassifyId(
			String projectClassifyId) {
		return jwSystemProjectDao.queryListByProjectClassifyId(projectClassifyId);
	}

	@Override
	public JwSystemProject queryByCode(String code) {
		
		return jwSystemProjectDao.queryByCode(code);
	}

	@Override
	public List<JwSystemProject> queryProjectCode() {
		return jwSystemProjectDao.queryProjectCode();
	}
	
	//update-begin--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
	/**
	 * @功能：获取所有系统项目信息
	 */
	@Override
	public List<JwSystemProject> getAllProject() {
		return jwSystemProjectDao.getAllProject();
	}
	//update-end--Author:zhangweijian  Date: 20180824 for：获取所有活动信息
	@Override
	public boolean changeUrl(String newdomain, String olddomain) {
		//第一步，更新peoject表中的域名
		jwSystemProjectDao.changeHdurl(olddomain,newdomain);
		//查询类型为活动的表名
		List<Map<String, String>> result = jwSystemProjectDao.getTableNames();
		for(int i=0;i<result.size();i++){
			//第二步，更新活动表中的活动链接，并清空短链接
			String tableName = result.get(i).get("table_name");
			jwSystemProjectDao.changeTabelHdurl(tableName);
			//第三步，重新生成短链接
			List<Map<String, String>> actList = jwSystemProjectDao.getAllAct(tableName);
			for(int j=0;j<actList.size();j++){
				String hdurl= actList.get(j).get("hdurl");
				hdurl = hdurl.replace("${domain}", domain);
				String shortUrl=WeiXinHttpUtil.getShortUrl(hdurl,actList.get(j).get("jwid"));
				if(StringUtil.isNotEmpty(shortUrl)){
					jwSystemProjectDao.updateShortUrl(result.get(i).get("table_name"),actList.get(j).get("id"),shortUrl);
				}
			}
		}
		return true;
	}

	@Override
	public boolean changeType(String domain) {
		//第一步，重新生成peoject中的入口地址
		logger.info("-------批量重新生成项目入口地址URL------------------domain-"+ domain);
		jwSystemProjectDao.changeType(domain);
		//查询类型为活动的表名
		List<Map<String, String>> result = jwSystemProjectDao.getTableNames();
		for(int i=0;i<result.size();i++){
			//第二步，更新活动表中的活动链接，并清空短链接
			logger.info("-------批量重新生成活动URL-------------------"+ result.get(i).get("table_name"));
			jwSystemProjectDao.changeTabelHdurl(result.get(i).get("table_name"));
			//第三步，重新生成短链接
			List<Map<String, String>> actList = jwSystemProjectDao.getAllAct(result.get(i).get("table_name"));
			for(int j=0;j<actList.size();j++){
				String shortUrl=WeiXinHttpUtil.getShortUrl(actList.get(j).get("hdurl"),actList.get(j).get("jwid"));
				if(StringUtil.isNotEmpty(shortUrl)){
					logger.info("-------重新生成活动短链接------------------shortUrl-"+ shortUrl);
					jwSystemProjectDao.updateShortUrl(result.get(i).get("table_name"),actList.get(j).get("id"),shortUrl);
				}
			}
		}
		return true;
	}

	@Override
	public String getOldHdurl() {
		String oldMain = null;
		List<String> strs = jwSystemProjectDao.getOldHdurl();
		if(strs!=null && strs.size()!=0){
			oldMain = strs.get(0);
			oldMain = oldMain.substring(0,oldMain.indexOf("/linksucai"));
		}
		return oldMain;
	}
}
