package com.jeecg.p3.weixin.vo.resp;

import java.util.List;

public class NewsMessageResp{
	//update-begin--Author:zhangweijian  Date: 20181105 for：改成不继承的
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
	//update-end--Author:zhangweijian  Date: 20181105 for：改成不继承的
	// 图文消息个数，限制为10条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

    public int getArticleCount() {
            return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
            ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
            return Articles;
    }

    public void setArticles(List<Article> articles) {
            Articles = articles;
    }
}
