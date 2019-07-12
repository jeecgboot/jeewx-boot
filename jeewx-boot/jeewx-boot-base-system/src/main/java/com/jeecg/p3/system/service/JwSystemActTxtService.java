package com.jeecg.p3.system.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.system.entity.JwSystemActTxt;

/**
 * 描述：</b>JwSystemActTxtService<br>
 * @author：junfeng.zhou
 * @since：2015年11月11日 11时11分51秒 星期三 
 * @version:1.0
 */
public interface JwSystemActTxtService {
	
	
	public void doAdd(JwSystemActTxt jwSystemActTxt);
	
	public void doEdit(JwSystemActTxt jwSystemActTxt);
	
	public void doDelete(String id);
	
	public JwSystemActTxt queryById(String id);
	
	public PageList<JwSystemActTxt> queryPageList(PageQuery<JwSystemActTxt> pageQuery);
	
	/**
	 * 复制文本
	 * @param actCode
	 * @param actId
	 * @return
	 */
	public void copyActText(String actCode,String actId);
	
	/**
	 * 查询actId下的活动文本
	 * @param actId
	 */
	public List<JwSystemActTxt> queryListByActCode(String actCode);
	/**
	 * 删除活动文本
	 * @param actId
	 */
	public void batchDeleteByActCode(String actCode);
}

