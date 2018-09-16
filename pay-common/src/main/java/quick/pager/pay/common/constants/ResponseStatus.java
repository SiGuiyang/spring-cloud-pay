package quick.pager.pay.common.constants;

/**
 * 响应对象枚举
 *
 * @author siguiyang
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功"),
    USER_NOT_EXISTS(201, "用户不存在"),
    PARAMETER_ERROR(202, "参数异常"),
    SYSTEM_ERROR(500, "系统繁忙"),
    USERNAME_OR_PASSWORD_INCORRECT(204, "用户名或密码不正确"),
    LOGIN_EXPIRE(205, "登录过期"),
    ROLE_NOT_EXISTS(206, "未知角色"),
    PARAMETER_IS_BLANK(207, "参数为空"),
    LOGIN_TOKEN_EXPIRE(213, "Token已过期"),
    ALIPAY_REQUEST_RESPONSE(209, "请求支付宝接口结果异常"),
    ALIPAY_REQUEST_EXCEPTION(210, "请求支付宝接口异常"),
    ALIPAY_REQUEST_TYPE_EXCEPTION(211, "请求支付宝入参类型不匹配"),
    WEIXIN_REQUEST_TYPE_EXCEPTION(212, "请求微信支付入参类型不匹配"),

    UNKNOWN_CHANNEL_CENTER(1000, "未知支付中心"),
    UNKNOWN_ROLE(1001, "未知角色"),
    UNKNOWN_SIGN(2001, "签名不通过"),
    UNKNOWN_PAY_MERCHANT_NO(3001, "未知商户号"),
    UNKNOWN_PAY_MERCHANT_ORDER_CODE(3001, "未知商户号订单号"),
    UNKNOWN_PAY_AMOUNT(3002, "未知支付金额"),
    UNKNOWN_PAY_BANK_CARD_NUM(3003, "未知支付银行卡号"),
    UNKNOWN_PAY_BANK_CODE(3004, "未知支付银行卡号"),
    UNKNOWN_PAY_NOTIFY_URL(3005, "未知支付回调地址"),
    UNKNOWN_PAY_SIGN(3006, "未知支付签名"),
    UNKNOWN_PAY_SIGN_TYPE(3007, "未知支付签名"),
    UNKNOWN_PAY_TIMESTAMP(3008, "未知支付时间戳"),
    UNKNOWN_PAY_TYPE(3009, "未知支付类型"),
    PAY_SIGN_NOT_CORRECT(4000, "签名不正确"),
    PAY_OPENID_ERROR(4001, "公众号openId失效"),
    PAY_WECHAT_FAIL(4002,"微信支付失败"),
    PAY_WECHAT_COMMUNICATION(4003,"微信支付通讯异常");

    public int code;

    public String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
