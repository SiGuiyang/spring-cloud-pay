package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import quick.pager.pay.dto.BaseDTO;

/**
 * 支付基础入参
 *
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseWeChatPayDTO extends BaseDTO {
    private static final long serialVersionUID = -4262300078280429735L;
    // 支付appid
    private String appId;
    // 支付商户Id
    private String mchId;
    // 支付商户密钥
    private String secret;
    // 支付金额 单位分
    private BigDecimal payAmount;
    // 平台订单号
    private String orderCode;
    // 三方回调地址
    private String notifyUrl;
    // 平台回调域名地址
    private String platformNotifyUrl;
    // 支付主题
    private String body;
    /**
     * 签名
     */
    private String sign;

}
