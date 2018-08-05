package quick.pager.pay.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 商户余额表<br />
 * <code>t_merchant_balance</code>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MerchantBalance extends IModel {
    private static final long serialVersionUID = -2940950581115357062L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 账户余额
     */
    private BigDecimal balanceAmount;
    /**
     * 冻结金额
     */
    private BigDecimal frozenAmount;
    /**
     * 当前可体现金额
     */
    private BigDecimal withdrawAmount;

}
