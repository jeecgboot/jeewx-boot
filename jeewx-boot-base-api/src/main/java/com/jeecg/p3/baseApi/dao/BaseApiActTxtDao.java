package com.jeecg.p3.baseApi.dao;

import java.util.List;
import java.util.Map;

/**
 * 对系统文本操作
 * @author huangqingquan
 *
 */
public interface BaseApiActTxtDao{
	/**
	 * 查询活动文本
	 * @param actCode
	 * @return
	 */
	public List<Map<String, Object>> queryListByActCode(String actCode) ;
	/**
	 * 删除活动文本
	 * @param actCode
	 */
	public void batchDeleteByActCode(String actCode);
	
	/**
	 * 插入
	 * @param map
	 */
	public void insert(Map<String, Object> map);
}

