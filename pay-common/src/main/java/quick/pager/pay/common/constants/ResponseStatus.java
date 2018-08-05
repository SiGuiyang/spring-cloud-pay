package quick.pager.pay.common.constants;

/**
 * 响应对象枚举
 *
 * @author siguiyang
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功"),
    PARAMETER_ERROR(202, "参数异常"),
    SYSTEM_ERROR(500,"系统繁忙"),
    USERNAME_OR_PASSWORD_INCORRECT(204, "用户名或密码不正确"),
    LOGIN_EXPIRE(205, "登录过期"),
    ROLE_NOT_EXISTS(206, "未知角色"),
    PARAMETER_IS_BLANK(207, "参数为空"),
    UNKNOWN_ROLE(208, "未知角色"),
    ALIPAY_REQUEST_RESPONSE(209, "请求支付宝接口结果异常"),
    ALIPAY_REQUEST_EXCEPTION(210, "请求支付宝接口异常"),
    ALIPAY_REQUEST_TYPE_EXCEPTION(211, "请求支付宝入参类型不匹配"),
    WEIXIN_REQUEST_TYPE_EXCEPTION(212, "请求微信支付入参类型不匹配"),;

    public int code;

    public String msg;

    ResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
