package quick.pager.pay.weixin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信退款返回数据对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeChatRefundResponseDTO extends WeChatBaseResponseDTO {
    private static final long serialVersionUID = 1875664726625632560L;
    // 微信订单号
    private String transaction_id;
    // 商户订单号
    private String out_trade_no;
    // 商户退款单号
    private String out_refund_no;
    // 微信退款单号
    private String refund_id;
    // 退款金额
    private String refund_fee;
    // 标价金额
    private String total_fee;
    // 现金支付金额
    private String cash_fee;
}
