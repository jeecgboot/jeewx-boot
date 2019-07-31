package com.jeecg.p3.goldeneggs.util;

import java.util.List;


public class ProbabilityUtil {
	/**
	 * 通过奖品剩余数量计算概率，百分百中奖
	 * @param otherPrizeList
	 * @return 返回的是原始的list的下标
	 */
	public static int getPrizeSignByTotal(List<ProbabilityDto> probabilityDtoList) {//通过活动id得到表里面的所有集合传过去
		if (probabilityDtoList == null || probabilityDtoList.size() <= 0) {
			return -1;
		}
		double randomNumber = Math.random();
		int index = 0;
		double minRate = 0;
		double maxRate = 0;
		ProbabilityDto prize = null;
		int remaindTotalNum = getRemaindTotalNum(probabilityDtoList);
		for (int i = 0; i < probabilityDtoList.size(); i++) {
			prize = probabilityDtoList.get(i);
			maxRate += getRate(prize.getRemainNum(), remaindTotalNum);
			if (randomNumber >= minRate && randomNumber <= maxRate) {
				index = i;
				break;
			} else {
				minRate += getRate(prize.getRemainNum(), remaindTotalNum);
			}
		}
		return probabilityDtoList.get(index).getIndex();	
	}

	// 计算剩余数量
	private static int getRemaindTotalNum(List<ProbabilityDto> otherPrizeList) {//计算所有的剩余数量
		int total = 0;
		for (ProbabilityDto prize : otherPrizeList) {
			total += prize.getRemainNum();
		}
		return total;
	}

	// 计算奖品概率
	private static double getRate(int prizeRemainNum, int remaindTotalNum) {//剩余剩余的总数量，通过所有的总数量，谁的多就得到谁的id
		double rate = 0;
		rate = prizeRemainNum * 1.00 / remaindTotalNum;
		return rate;
	}
	public static int getPrizeSignByProbability(List<ProbabilityDto> probabilityDtoList){
		if (probabilityDtoList == null || probabilityDtoList.size() <= 0) {
			return -1;
		}
		return 0;
	}
	private static double calculateRate(double rate) {
		double randomNumber = Math.random();
		return randomNumber;
	}
}
