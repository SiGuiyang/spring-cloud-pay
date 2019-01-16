package quick.pager.pay.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubmitPayDTO extends BaseDTO {
    private static final long serialVersionUID = -6485453830955746741L;

    // 商户号
    private String merchantNo;
    // 商户订单号
    private String merchantOrderCode;
    // 回调地址
    private String notifyUrl;
    // 客户端请求Ip地址
    private String clientIp;
    // 支付金额
    private BigDecimal payAmount;
    // 银行卡号
    private String bankCardNum;
    // 银行编号
    private String bankCode;
    // 支付主题
    private String body;
    // 支付类型
    private String payType;
    // 支付时时间戳
    private String timestamp;
    // 签名类型
    private String signType;
    // 签名
    private String sign;
}
