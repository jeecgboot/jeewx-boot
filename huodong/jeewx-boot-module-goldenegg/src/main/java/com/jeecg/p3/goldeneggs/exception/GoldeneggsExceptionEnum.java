package com.jeecg.p3.goldeneggs.exception;

/**
 * 错误枚举.
 *
 */
public enum GoldeneggsExceptionEnum {

    SUCCESS("000000", "SUCCESS", "请求成功"),

    /**
     * 01 系统、参数异常类
     */
    SYS_ERROR("01001","SYS_ERROR","系统异常, 请稍后重试"),
    ARGUMENT_ERROR("01002","ARGUMENT_ERROR","参数错误"),
    SIGN_ERROR("01003","ARGUMENT_ERROR","验签失败"),
    DATA_CHANGED_ERROR("01004","DATA_CHANGED_ERROR","信息已经改变"),
    UNAUTHORIZED_AC_ERR("01005", "UNAUTHORIZED_ERR", "未授权或不支持操作"),
    VALIDATE_RANDCODE_ERROR("01006","VALIDATE_RANDCODE_ERROR","验证码输入错误"),
    DATABASE_ERROR("01007","DATABASE_ERROR","数据库操作异常"),
    DATA_INTEGRITY_ERROR("01008","DATA_INTEGRITY_ERROR","数据不一致"),
    DATA_NOT_EXIST_ERROR("01009","DATA_NOT_EXIST_ERROR","数据不存在"),
    DATA_EXIST_ERROR("01010","DATA_EXIST_ERROR","数据已存在"),

    /**
     * 02  砍价活动异常类
     */
    ACT_BARGAIN_PRIZE_NONE("02001","ACT_BARGAIN_PRIZE_NONE","奖品已领取完"),
    ACT_BARGAIN_PRIZE_INVALID("02002","ACT_BARGAIN_PRIZE_INVALID","奖品信息无效"),
    ACT_BARGAIN_PRIZE_CONDITION_NOT_ENOUGH("02003","ACT_BARGAIN_PRIZE_CONDITION_NOT_ENOUGH","奖品条件不符合"),
    ACT_BARGAIN_FINISH("02004","ACT_BARGAIN_FINISH","砍价已结束"),
    ACT_BARGAIN_NO_FOCUS("02005","ACT_BARGAIN_NO_FOCUS","非关注用户"),
    ACT_BARGAIN_PRIZE_RECEIVED("02006","ACT_BARGAIN_PRIZE_RECEIVED","已领取奖品"),
    ACT_BARGAIN_NO_START("02007","ACT_BARGAIN_NO_START","活动未开始"),
    ACT_BARGAIN_END("02008","ACT_BARGAIN_END","活动未激活"),
    ACT_BARGAIN_PRIZE_NO_GET("02009","ACT_BARGAIN_PRIZE_NO_GET","未中奖"),
    ACT_BARGAIN_RULE_ERROR("02010","ACT_BARGAIN_RULE_ERROR","砍价规则异常"),
    ACT_BARGAIN_DATA_ERROR("02011","ACT_BARGAIN_DATA_ERROR","活动数据异常"),
    ACT_BARGAIN_JOIN_PRD("02012","ACT_BARGAIN_JOIN_PRD","已参加过商品砍价"),
    ACT_BARGAIN_AWARDS_END_TIME("02013","ACT_BARGAIN_AWARDS_END_TIME","兑奖已过截止日期"),
    ACT_BARGAIN_CARD_NO_FIND("02014","ACT_BARGAIN_CARD_NO_FIND","卡券没有找到"),
    ACT_BARGAIN_GEN_OAUTH_URL_ERROR("02015","ACT_BARGAIN_GEN_OAUTH_URL_ERROR","生成授权地址错误"),
    
    /** 
     * 99 其它
     */
    NOTIFY_ERR("99001", "NOTIFY_ERR", "回调通知失败, 系统会再次发起"),
    UNKONWN_NOTIFY_CMD_ERR("99002", "UNKONWN_NOTIFY_CMD_ERR", "未知通知类型");

    private String errCode;
    private String errMsg;
    private String errChineseMsg;

    /**
     * Default constructor.
     * Properties initialization can invoke setter.
     *
     * @param errCode
     * @param errMsg
     * @param errChineseMsg
     */
    private GoldeneggsExceptionEnum(String errCode, String errMsg, String errChineseMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.errChineseMsg = errChineseMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getErrChineseMsg() {
        return errChineseMsg;
    }
}
