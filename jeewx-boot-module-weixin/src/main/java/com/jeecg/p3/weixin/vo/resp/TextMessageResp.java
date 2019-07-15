package com.jeecg.p3.weixin.vo.resp;


public class TextMessageResp{
	// 回复的消息内容
    private String Content;
    public String getContent() {
            return Content;
    }
    public void setContent(String content) {
            Content = content;
    }
    //update-begin--Author:zhangweijian  Date: 20180928 for：转化xml继承BaseMessageResp只能转化content问题
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
    //update-end--Author:zhangweijian  Date: 20180928 for：转化xml继承BaseMessageResp只能转化content问题
}
