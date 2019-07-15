package com.jeecg.p3.weixin.util;

public class WeiXinConstants {

	/**
	 * 微信用户请求的微信公众账号信息，保存用户Session会话中
	 */
	public static final String WEIXIN_ACCOUNT = "WEIXIN_ACCOUNT";
	/**
	 * 微信用户 OPENID
	 */
	public static final String USER_OPENID = "USER_OPENID";
	/**
	 * 微信用户请求的微信公众账号ID，保存用户Session会话中
	 */
	public static final String WEIXIN_QIANTAI_ACCOUNTID ="WEIXIN_QIANTAI_ACCOUNTID";
	
	
	
	//微信微调研题目状态 0：未发布 1：已发布 2:已结束。
	/**微调研题目默认未发布状态**/
	public static final String WEIXIN_SURVEY_DEFAULT_STATEMENT = "0";
	/**微调研题目发布状态**/
	public static final String WEIXIN_SURVEY_DEPLOY_STATEMENT = "1";
	/**微调研题目结束状态**/
	public static final String WEIXIN_SURVEY_OVER_STATEMENT = "2";
	
	//微信调研题目类型 1:单选 2：多选 3：填空
	/**单选**/
	public static final String WEIXIN_SURVEY_TYPE_SIGLE_SELECT ="1";
	/**多选**/
	public static final String WEIXIN_SURVEY_TYPE_MULTI_SELECT ="2";
	/**填空选**/
	public static final String WEIXIN_SURVEY_TYPE_TEXT="3";
	
	//微信微投票题目状态 0：未发布 1：已发布 2:已结束。
	/**微调研题目默认未发布状态**/
	public static final String WEIXIN_VOTE_DEFAULT_STATEMENT = "0";
	/**微调研题目发布状态**/
	public static final String WEIXIN_VOTE_DEPLOY_STATEMENT = "1";
	/**微调研题目结束状态**/
	public static final String WEIXIN_VOTE_OVER_STATEMENT = "2";
	
	
	//支付宝常量
	//物流类型快->快递
	public static final String WEIXIN_PAY_LOGISTICS_EXPRESS = "EXPRESS";
	//物流类型快->平邮
	public static final String WEIXIN_PAY_LOGISTICS_POST = "POST";
	//物流类型快->EMS
	public static final String WEIXIN_PAY_LOGISTICS_EMS = "EMS";
	
	//快递付费类型->卖家承担运费
	public static final String WEIXIN_PAY_PAYMENT_SELLER = "SELLER_PAY";
	//快递付费类型->买家承担运费
	public static final String WEIXIN_PAY_PAYMENT_BUYER = "BUYER_PAY";
	
	//成功付费后的状态
	public static final String TRADE_FINISHED = "TRADE_FINISHED";
	//快递付费类型->买家承担运费
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
	
	
	
	//微信投票PK 投票类型：二维码扫描，直接投票，朋友圈分享。
	/**普通投票**/
	public static final String WEIXIN_VOTEPK_TYPE_NORMAL = "1";
	/**二维码**/
	public static final String WEIXIN_VOTEPK_TYPE_QRCODE = "2";
	/**朋友圈或好友分享**/
	public static final String WEIXIN_VOTEPK_TYPE_SHARE = "3";
	
	//微信关注用户 关注状态  0：已经取消关注 1：关注
	/**关注**/
	public static final String WEIXIN_SUBSCRIBE_TYPE = "1";
	/**取消关注**/
	public static final String WEIXIN_UNSUBSCRIBE_TYPE = "0";
	
	//微信投票PK活动相关常量
	/**默认平台投票数量**/
	public static final int WEIXIN_VOTEPK_NORMAL_VOTECOUNT= 1;
	/**默认扫描二维码投票数量**/
	public static final int WEIXIN_VOTEPK_SCAN_VOTECOUNT= 1;
	/**默认分享朋友圈投票数量**/
	public static final int WEIXIN_VOTEPK_SHARE_VOTECOUNT= 4;
	
	/*投票用户校验相关常量*/
	/**投票者为空，数据异常**/
	public static final String WEIXIN_VOTEPK_MSG_DATAEXCEION = "0";
	/**用户已经关注**/
	public static final String WEIXIN_VOTEPK_MSG_SUBSCRIBE = "1";
	
	/*投票活动弹出关注Model标识常量*/
	/**无需弹窗，为其他原因**/
	public static final String WEIXIN_VOTEPK_UNSHOW_SUBSCRIBEMODEL_FLAG = "0";
	/**需弹窗，因为需要关注**/
	public static final String WEIXIN_VOTEPK_SHOW_SUBSCRIBEMODEL_FLAG = "1";
	
	//投票活动，参数配置名称定义。
	/**排行榜每页显示数据数量**/
	public static final String VOTEPK_CONFIG_SORT_ROWSPERPAGE = "sortperpage";
	/**朋友圈分享投票数量**/
	public static final String VOTEPK_CONFIG_FRINEDS_SHARE_COUNT = "friendssharecount";
	/**快捷关注URL配置**/
	public static final String VOTEPK_CONFIG_SUBSCRIBE_ARTICLEURL = "subscribearticleurl";
	/**活动规则URL配置**/
	public static final String VOTEPK_CONFIG_RULE= "ruleurl";
	/**活动介绍URL配置**/
	public static final String VOTEPK_CONFIG_INTRODUCE= "introduceurl";
	/**活动攻略URL配置**/
	public static final String VOTEPK_CONFIG_VOTE_STRATEGY= "strategy";
	/**分享朋友圈标题**/
	public static final String VOTEPK_CONFIG_SHARE_TITLE = "sharetitle";
	/**分享朋友圈内容**/
	public static final String VOTEPK_CONFIG_SHARE_CONTENT = "sharecontent";
	/**投票人接收到的信息**/
	public static final String VOTEUSER_RESPONSETEXT = "voteresponsetext";
	/**被投票人接受到的信息**/
	public static final String VOTEDUSER_RESPONSETEXT = "votedresponsetext";
	/**投票支持文字内容**/
	public static final String VOTEPK_CONFIG_VOTESUPPORT ="votesupport";
	/**支持有礼文字内容**/
	public static final String VOTEPK_CONFIG_SUPPORTGIFT ="supportgift";
	/**活动地区限制**/
	public static final String VOTEPK_CONFIG_LOCATIONVALID ="locationvalid";
	/**普通投票转盘次数**/
	public static final String VOTEPK_CONFIG_NORMALVOTE_LETTECOUNT ="normalvotelettecount";
	/**扫描投票转盘次数**/
	public static final String VOTEPK_CONFIG_QRCODEVOTE_LETTECOUNT ="qrcodevotelettecount";
	/**分享投票转盘次数**/
	public static final String VOTEPK_CONFIG_SHAREVOTE_LETTECOUNT ="sharevotelettecount";
	
	public static final String AWARD_IMG_URL ="plug-in/weixin/images/ggl";
	public static final String AWARD_IMG_URL_SHAKE ="plug-in/weixin/shakePrize/images";
	
	/**会员卡默认会员头像**/
	public static final String MEMBER_DEFAULT_HEADIMG_URL ="template/vip/default/css/new/images/defaulthead.png";
	/**积分获取**/
	public static final String INTEGRAL_DETAILTYPE_GET = "0";
	/**积分使用**/
	public static final String INTEGRAL_DETAILTYPE_USE = "1";
	/**签到积分规则标识**/
	public static final String INTEGRAL_TYPE_SIGN = "1";
	/**有奖问答积分规则标识**/
	public static final String INTEGRAL_TYPE_AWARDQUESTION = "2";
	/**投票积分规则标识**/
	public static final String INTEGRAL_TYPE_VOTE = "3";
	/**调研问卷积分规则标识**/
	public static final String INTEGRAL_TYPE_SURVEY = "4";
	/**兑换积分规则标识**/
	public static final String INTEGRAL_TYPE_EXCHANGE = "5";
	
	/**发货状态**/
	public static final String EXPRESS_SEND_STATUS = "1";
	/**未发货状态**/
	public static final String EXPRESS_UNSEND_STATUS = "0";
	
	//素材状态，共享和非共享
	public static final String SUCAI_SHARE_STATUS = "Y";
	public static final String SUCAI_UNSHARE_STATUS = "N";
	
	/**
	 * 微信菜单父亲与子菜单共享状态
	 */
	/**共享**/
	public static final String MENU_SHARE_STATUS = "Y";
	/**私有**/
	public static final String MENU_UNSHARE_STATUS = "N";
	/**父享**/
	public static final String MENU_PARENT_SHARE_STATUS = "P";
	
	/**
	 * 自定义菜单来源,1 本账号，2：父账号
	 */
	public static final String MENU_MENU_SOURCE_TYPE_1 = "1";
	public static final String MENU_MENU_SOURCE_TYPE_2 = "2";
	
	
	/**自定义菜单显示**/
	public static final int  MENU_HIDE= 1;
	/**自定义菜单隐藏**/
	public static final int MENU_SHOW= 0;
	
	/**
	 * 关键字来源,1 本账号，2：父账号
	 */
	public static final String AUTO_SOURCE_TYPE_PARENT = "2";
	public static final String AUTO_SOURCE_TYPE_SELF= "1";
	
	/**
	 * 客服接待状态 0 待接待 1 已接待
	 */
	public static final int RECEPTSTATUS_WAIT = 0;
	public static final int RECEPTSTATUS_DOING= 1;

	/**微信多媒体消息类型-图片**/
	public static final String MEDIA_IMAGE = "image";
	/**微信多媒体消息类型-语音**/
	public static final String MEDIA_VOICE = "voice";
	/**微信多媒体消息类型-视频**/
	public static final String MEDIA_VIDEO = "video";
	/**微信多媒体消息类型-小视频**/
	public static final String MEDIA_SHORTVIDEO = "shortvideo";
	
	/**客服新消息图标 **/
	public static final String NEW_MSG_URL ="plug-in/weixin/images/newmsg.gif";
	/**客服旧消息图标 **/
	public static final String OLD_MSG_URL ="plug-in/weixin/images/oldmsg.gif";
	/**管理员角色名称 **/
	public static final String ADMIN_ROLENAME = "admin";
	/**微信返回成功消息标志 **/
	public static final String WEIXIN_ERRMSG_OK = "ok";
	
	
	/**
	 * 是否中奖可参与       0中奖后不可参与     1中奖后可继续参与
	 */
	public static final Integer PRIZE_STATUS_0 = 0;
	public static final Integer PRIZE_STATUS_1= 1;
	
	
	/**订单状态---未支付**/
	public static final String ORDER_STATUS_NO_PAY = "未支付";
	/**订单状态---支付成功**/
	public static final String ORDER_STATUS_PAY_SUCC = "支付成功";
	/**订单状态--- 已发货**/
	public static final String ORDER_STATUS_EXPRESS_SEND = "已发货";
	/**订单状态--- 退款成功**/
	public static final String ORDER_STATUS_REFUND_SUCC = "退款成功";
	//update-begin---author:fanpengcheng----date:20170419-----for:添加已完成订单状态
	/**订单状态--- 已完成**/
	public static final String ORDER_STATUS_COMPLETE = "已完成";
	//update-end---author:fanpengcheng----date:20170419-----for:添加已完成订单状态
	
	
	/**交易订单状态---待支付**/
	public static final String TRANS_STATUS_PAY_00 = "00";
	/**交易订单状态---支付成功**/
	public static final String TRANS_STATUS_PAY_01 = "01";
	/**交易订单状态---支付失败**/
	public static final String TRANS_STATUS_PAY_99 = "99";
	
	//update-begin---author:fanpengcheng----date:20170419-----for:添加退款状态
	/**订单退款状态---申请退款**/
	public static final String ORDER_REFUND_STATUS_1 = "1";
	/**订单退款状态---退款成功**/
	public static final String ORDER_REFUND_STATUS_2 = "2";
	/**订单退款状态---退款失败**/
	public static final String ORDER_REFUND_STATUS_3 = "3";
	/**订单退款状态---退款申请驳回**/
	public static final String ORDER_REFUND_STATUS_4 = "4";
	//update-begin---author:fanpengcheng----date:20170419-----for:添加退款状态
}