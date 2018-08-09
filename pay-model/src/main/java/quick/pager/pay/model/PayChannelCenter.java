package quick.pager.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 商户支付渠道<br />
 * <code>t_merchant_pay_channel</code>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PayChannelCenter extends IModel {
    private static final long serialVersionUID = 6148475859603578666L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付渠道Id
     */
    private Long payChannelCenterId;
}
