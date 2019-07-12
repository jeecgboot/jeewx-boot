package com.jeecg.p3.weixin.vo.resp;
/**
 * 回复消息基类
 * 开发账号->普通用户
 * @author Administrator
 *
 */
public class BaseMessageResp {
	// 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;
  
    public String getToUserName() {
        return ToUserName;
    }
    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
    public String getFromUserName() {
        return FromUserName;
    }
    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }
    public long getCreateTime() {
        return CreateTime;
    }
    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }
    public String getMsgType() {
        return MsgType;
    }
    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
   
}
