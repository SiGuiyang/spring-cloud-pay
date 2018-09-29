package quick.pager.pay.model.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import quick.pager.pay.model.common.IModel;

import java.math.BigDecimal;

/**
 * 订单表<br />
 * <code>t_order</code>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Order extends IModel {
    private static final long serialVersionUID = 1720185339889033013L;

    /**
     * 通知状态<br />
     * 0:未下发 1:已下发 2:已通知<br />
     */
    private Integer notificationStatus;
    /**
     * 支付状态<br />
     * 0：已创建 1：已取消 2：成功 3：失败<br />
     */
    private Integer payStatus;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 退款订单号
     */
    private String refundCode;
    /**
     * 商户订单号
     */
    private String merchantOrderCode;
    /**
     * 交易流水号
     */
    private String tradeCode;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 订单回调地址
     */
    private String notifyUrl;
    /**
     * 平台回调域名
     */
    private String platformNotifyUrl;

    /**
     * 返回地址
     */
    private String returnUrl;
    /**
     * 支付主体
     */
    private String payBody;
    /**
     * 支付客户端的IP地址
     */
    private String payClientId;
    /**
     * 支付跳转页面地址
     */
    private String payJumpUrl;
    /**
     * 交易金额
     */
    private BigDecimal payAmount;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 费率金额
     */
    private BigDecimal rateAmount;
    /**
     * 扣量金额
     */
    private BigDecimal deductionAmount;

}
