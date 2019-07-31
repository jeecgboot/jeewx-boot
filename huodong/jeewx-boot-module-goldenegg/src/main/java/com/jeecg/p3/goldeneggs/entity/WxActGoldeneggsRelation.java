package com.jeecg.p3.goldeneggs.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActGoldeneggsRelation:活动奖品明细表<br>
 * 
 * @author junfeng.zhou
 * @since：2016年06月07日 18时00分29秒 星期二
 * @version:1.0
 */
public class WxActGoldeneggsRelation implements Entity<String> {
	private static final long serialVersionUID = 1L;
	/**	 *	 */
	private String id;
	/**	 *	 */
	private String prizeId;
	/**
	 * 奖品的名字
	 */
	private String prizeName;
	/**	 *	 */
	private String actId;
	/**	 *	 */
	private String jwid;
	/**	 *	 */
	private String awardId;
	/**
	 * 奖品等级的名字
	 */
	private String awardName;
	/**
	 * 数量
	 */
	private Integer amount;
	/**
	 * 剩余数量
	 */
	private Integer remainNum;
	/**
	 * 概率
	 */
	private BigDecimal probability;
	//update-begin-zhangweijian-----Date:20180830----for:新增一个String类型的概率引用
	private String probabilitys;
	public String getProbabilitys() {
		return probabilitys;
	}
	public void setProbabilitys(String probabilitys) {
		this.probabilitys = probabilitys;
	}
	//update-end-zhangweijian-----Date:20180830----for:新增一个String类型的概率引用
	/**
	 * 添加图片引用
	 */
    private String awardImg;
    /**
	 *开始时间
	 */
	private Date starttime;
	/**
	 *结束时间
	 */
	private Date endtime;
	
	public String getAwardImg() {
		return awardImg;
	}
	public void setAwardImg(String awardImg) {
		this.awardImg = awardImg;
	}
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrizeId() {
		return this.prizeId;
	}

	public void setPrizeId(String prizeId) {
		this.prizeId = prizeId;
	}

	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getJwid() {
		return this.jwid;
	}

	public void setJwid(String jwid) {
		this.jwid = jwid;
	}

	public String getAwardId() {
		return this.awardId;
	}

	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getRemainNum() {
		return this.remainNum;
	}

	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

	public BigDecimal getProbability() {
		return this.probability;
	}

	public void setProbability(BigDecimal probability) {
		this.probability = probability;
	}
	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
}
