package com.jeecg.p3.baseApi.service;

/**
 * 对系统文本操作
 * @author huangqingquan
 *
 */
public interface BaseApiActTxtService {
	
	/**
	 * 复制系统文本
	 * @param arg0 需要拷贝的活动原来ID
	 * @param arg1 新活动ID
	 * @return
	 */
	public void copyActText(String oldActCode, String newActCode);
	/**
	 * 删除活动文本
	 * @param actCode
	 */
	public void batchDeleteByActCode(String actCode);
}

