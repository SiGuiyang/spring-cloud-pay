package quick.pager.pay.dto.pay;

import lombok.Data;
import quick.pager.pay.dto.BaseDto;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WeiXinSubmitPayDto extends BaseDto {
    private static final long serialVersionUID = -5137827593536080438L;

    /**
     * 订单号
     */
    private String merchantOrderNo;
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
