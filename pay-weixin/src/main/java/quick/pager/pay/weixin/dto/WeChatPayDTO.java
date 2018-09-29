package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

/**
 * 微信支付统一支付对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatPayDTO extends BaseDTO {
    private static final long serialVersionUID = -6246993706082239265L;

    // 公众账号ID
    private String appid;
    // 商户号
    private String mchId;
    // 签名
    private String sign;
    // 签名类型
    private String signType;
    // 商品描述
    private String body;
    // 商户订单号
    private String outTradeNo;
    // 标价金额
    private String totalFee;
    // 终端IP
    private String spbillCreateIp;
    // 通知地址
    private String notifyUrl;
    // 交易类型 JSAPI 公众号支付 NATIVE 扫码支付 APP APP支付
    private String tradeType;
    // 用户标识
    private String openid;

    private String secret;
}
