package quick.pager.pay.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.IModel;

import java.math.BigDecimal;

/**
 * 交易流水<br />
 * <code>t_trade_order</code>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TradeOrder extends IModel {
    private static final long serialVersionUID = -8952845250752554281L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 流水号
     */
    private String tradeCode;
    /**
     * 交易状态
     */
    private Integer tradeStatus;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;
    /**
     * 费率金额
     */
    private BigDecimal rateAmount;
    /**
     * 扣量金额
     */
    private BigDecimal deductionAmount;


}
