package com.jeecg.p3.goldeneggs.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/**
 * 不同概率抽奖工具包
 *
 * @author Shunli
 */
public class LotteryUtil {
    /**
     * 抽奖
     *
     * @param orignalRates
     *            原始的概率列表，保证顺序和实际物品对应
     * @return
     *         物品的索引
     */
    public static int lottery(List<Double> orignalRates) {
        if (orignalRates == null || orignalRates.isEmpty()) {
            return -1;
        }
 
        int size = orignalRates.size();
 
        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }
        //不中奖的概率
        double noLuky = 0d;
        //如果各奖项的总概率大于1，则必然产生其中一个奖项，如果各奖项的总概率小于1，则包括不中奖的概率
        if(sumRate<1){
        	//计算不中奖的概率
        	noLuky=(1-sumRate)/1;
        }      
        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate / sumRate);
        }

        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        //如果区块值在不中奖的概率范围内
        if (nextDouble < noLuky) {
            return  -1;
        }
        //取随机数判断中哪个奖品
        double awardDouble = Math.random();
        
        int awardIndex =0;//中奖下标 
        for(Double rate : sortOrignalRates){
        	//循环中奖概率，如比随机数大，则落在本区间
        	if(rate > awardDouble){
        		break;
        	}
        	awardIndex +=1;
        }
        return awardIndex;
 
    }
}