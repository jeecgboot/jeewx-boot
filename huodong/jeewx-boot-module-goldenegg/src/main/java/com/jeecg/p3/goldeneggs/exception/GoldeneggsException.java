package com.jeecg.p3.goldeneggs.exception;

import org.jeecgframework.p3.core.common.exception.ActBizException;

/**
 * 保险异常类, 所有错误都封装为此错误, 以错误区分.
 *
 * @version $ v1.0 Sep 3, 2014 $
 */
public class GoldeneggsException extends ActBizException {
    private static final long serialVersionUID = 7787204828254391226L;

    /** Chinese Description */
    private String chineseMsg;

    public GoldeneggsException(Throwable cause, String defineCode, String errorMessage) {
        super(defineCode, errorMessage, cause);
    }

    public GoldeneggsException(Throwable cause, GoldeneggsExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg(), cause);
        this.chineseMsg = exp.getErrChineseMsg();
    }

    /**
     * Constructor.
     * @param defineCode error code
     */
    public GoldeneggsException(String defineCode) {
        super(defineCode);
    }

    public GoldeneggsException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public GoldeneggsException(GoldeneggsExceptionEnum exp) {
        super(exp.getErrCode(), exp.getErrMsg());
        this.chineseMsg = exp.getErrChineseMsg();
    }

    public GoldeneggsException(GoldeneggsExceptionEnum exp, String errMsg) {
        super(exp.getErrCode(), errMsg);
    }

    public GoldeneggsException(GoldeneggsExceptionEnum exp, String errMsg, String chineseMsg) {
        super(exp.getErrCode(), errMsg);
        this.chineseMsg = chineseMsg;
    }

    public GoldeneggsException newInstance(String message, Object... args) {
        GoldeneggsException ex = new GoldeneggsException(this.defineCode);
        ex.setMessage(message, args);
        return ex;
    }

    public String getChineseMsg() {
        return getMessage();
    }

    public String getMessage() {
        if (null == chineseMsg) {
            return message;
        }
        return chineseMsg;
    }
}
