package quick.pager.pay.vo.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import quick.pager.pay.vo.VO;

import java.math.BigDecimal;

/**
 * 微信签名的VO
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinSignVO extends VO{
    private static final long serialVersionUID = 1651509124995762319L;
    /**
     * 订单号
     */
    private String merchantOrderNo;

    /**
     * 回调通知地址
     */
    private String notifyUrl;

    /**
     * 支付主题说明
     */
    private String body;
    /**
     * 订单金额
     */
    private BigDecimal amount;
}
