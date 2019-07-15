package com.jeecg.p3.system.web.dto;

import java.util.List;


import com.jeecg.p3.system.entity.JwSystemActTxt;

/**
 * 
 * @author 黄青全
 * 用于取前端的活动文本的数据
 *
 */
public class JwSystemActTxtDto{
	private static final long serialVersionUID = 1L;
	
	private  List<JwSystemActTxt> jwSystemActTxts;
	
	public List<JwSystemActTxt> getJwSystemActTxts() {
		return jwSystemActTxts;
	}
	public void setJwSystemActTxts(List<JwSystemActTxt> jwSystemActTxts) {
		this.jwSystemActTxts = jwSystemActTxts;
	}
}

