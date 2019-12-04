package com.jeecg.p3.baseApi.util;


import com.alibaba.fastjson.JSONObject;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeixinUserUtil {

    private static Logger logger= LoggerFactory.getLogger(WeixinUserUtil.class);

    /**
     * 微信获取个人信息util
     *
     * @param weixinDto
     * @return 获取头像 Map两个键headimgurl,fxheadimgurl
     */
    public static void setWeixinDto(WeixinDto weixinDto, String utoken) {
        logger.info("setWeixinDto parameter weixinDto={}",
                new Object[] { weixinDto });
        try {
            if (weixinDto.getOpenid() != null) {
                JSONObject jsonObj =null;
                if(StringUtils.isEmpty(utoken)){
                    jsonObj = WeiXinHttpUtil.getGzUserInfo(weixinDto.getOpenid(), weixinDto.getJwid());
                }else{
                    jsonObj = WeiXinHttpUtil.getGzUserInfo2(weixinDto.getOpenid(), weixinDto.getJwid(),utoken);
                }
                logger.info("setWeixinDto Openid getGzUserInfo jsonObj={}",
                        new Object[] { jsonObj });
                if (jsonObj != null && jsonObj.containsKey("subscribe")) {
                    weixinDto.setSubscribe(jsonObj.getString("subscribe"));
                } else {
                    weixinDto.setSubscribe("0");
                }
                if (jsonObj != null && jsonObj.containsKey("nickname")) {
                    weixinDto.setNickname(jsonObj.getString("nickname"));
                } else {
                    weixinDto.setNickname("匿名");
                }
                if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
                    weixinDto.setHeadimgurl(jsonObj.getString("headimgurl"));
                } else {
                    weixinDto.setHeadimgurl("http://static.h5huodong.com/upload/common/defaultHeadImg.png");
                }
            }
            if (StringUtils.isNotEmpty(weixinDto.getFxOpenid())) {
                JSONObject jsonObj =null;
                if(StringUtils.isEmpty(utoken)){
                    jsonObj = WeiXinHttpUtil.getGzUserInfo(weixinDto.getFxOpenid(), weixinDto.getJwid());
                }else{
                    jsonObj = WeiXinHttpUtil.getGzUserInfo2(weixinDto.getFxOpenid(),weixinDto.getJwid(),utoken);
                }
                logger.info("setWeixinDto FxOpenid getGzUserInfo jsonObj={}",
                        new Object[] { jsonObj });
                if (jsonObj != null && jsonObj.containsKey("nickname")) {
                    weixinDto.setFxNickname(jsonObj.getString("nickname"));
                } else {
                    weixinDto.setFxNickname("匿名");
                }
                if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
                    weixinDto.setFxHeadimgurl(jsonObj.getString("headimgurl"));
                } else {
                    weixinDto.setFxHeadimgurl("http://static.h5huodong.com/upload/common/defaultHeadImg.png");
                }
            }
        } catch (Exception e) {
            logger.error("setWeixinDto e={}",
                    new Object[] { e });
        }
    }

    /**
     * 获取用户信息
     * @param weixinDto
     * @return
     */
    public static void setWeixinDtoAuthToken(WeixinDto weixinDto, String webAuthToken) {
        logger.info("setWeixinDto parameter weixinDto={}",new Object[] { weixinDto });
        try {
            if (weixinDto.getOpenid() != null) {
                JSONObject jsonObj = null;
                if(StringUtils.isNotEmpty(webAuthToken)){
                    jsonObj=WebAuthWeixinApi.getWebAuthUserInfo(weixinDto.getOpenid(), webAuthToken);
                    logger.info("===========================主动授权获取用户信息==============================");
                }else{
                    jsonObj=WeiXinHttpUtil.getGzUserInfo(weixinDto.getOpenid(), weixinDto.getJwid());
                    logger.info("===========================静默授权获取用户信息==============================");
                }
                logger.info("setWeixinDto Openid getGzUserInfo jsonObj={}",new Object[] { jsonObj });
                if (jsonObj != null && jsonObj.containsKey("subscribe")) {
                    weixinDto.setSubscribe(jsonObj.getString("subscribe"));
                } else {
                    weixinDto.setSubscribe("0");
                }
                if (jsonObj != null && jsonObj.containsKey("nickname")) {
                    weixinDto.setNickname(jsonObj.getString("nickname"));
                } else {
                    weixinDto.setNickname("匿名");
                }
                if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
                    weixinDto.setHeadimgurl(jsonObj.getString("headimgurl"));
                } else {
                    weixinDto.setHeadimgurl("http://static.h5huodong.com/upload/common/defaultHeadImg.png");
                }
            }
            if (StringUtils.isNotEmpty(weixinDto.getFxOpenid())) {
                JSONObject jsonObj = WeiXinHttpUtil.getGzUserInfo(weixinDto.getFxOpenid(), weixinDto.getJwid());
                logger.info("setWeixinDto FxOpenid getGzUserInfo jsonObj={}",new Object[] { jsonObj });
                if (jsonObj != null && jsonObj.containsKey("nickname")) {
                    weixinDto.setFxNickname(jsonObj.getString("nickname"));
                } else {
                    weixinDto.setFxNickname("匿名");
                }
                if (jsonObj != null && jsonObj.containsKey("headimgurl")) {
                    weixinDto.setFxHeadimgurl(jsonObj.getString("headimgurl"));
                } else {
                    weixinDto.setFxHeadimgurl("http://static.h5huodong.com/upload/common/defaultHeadImg.png");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("setWeixinDto e={}",
                    new Object[] { e });
        }
    }
}