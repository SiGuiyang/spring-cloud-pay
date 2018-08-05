package quick.pager.pay.common.client.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分账明细信息
 */
public class AlipayOpenApiRoyaltyDetailRequest implements Serializable {

    private static final long serialVersionUID = 4998214515907605299L;
    /**
     * 分账支出方账户，类型为userId<br />
     * 本参数为要分账的支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
     */
    private String transOut;
    /**
     * 分账收入方账户，类型为userId<br />
     * 本参数为要分账的支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字。
     */
    private String transIn;
    /**
     * 分账的金额，单位为元
     */
    private BigDecimal amount;
    /**
     * 分账信息中分账百分比。取值范围为大于0，少于或等于100的整数
     */
    private BigDecimal amountPercentage;
    /**
     * 分账描述
     */
    private String desc;

    public String getTransOut() {
        return transOut;
    }

    public void setTransOut(String transOut) {
        this.transOut = transOut;
    }

    public String getTransIn() {
        return transIn;
    }

    public void setTransIn(String transIn) {
        this.transIn = transIn;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountPercentage() {
        return amountPercentage;
    }

    public void setAmountPercentage(BigDecimal amountPercentage) {
        this.amountPercentage = amountPercentage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
