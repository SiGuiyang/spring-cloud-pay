package quick.pager.pay.dto.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.dto.BaseDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatSubmitDTO extends BaseDTO {
    private static final long serialVersionUID = -5137827593536080438L;

    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 回调通知地址
     */
    private String notifyUrl;

    /**
     * 支付时间戳
     */
    private String timestamp;
    /**
     * 支付主题说明
     */
    private String body;
    /**
     * 订单金额
     */
    private BigDecimal amount;
}
