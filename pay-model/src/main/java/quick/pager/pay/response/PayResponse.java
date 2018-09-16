package quick.pager.pay.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付响应对象
 *
 * @author siguiyang
 */
@Data
@Builder
public class PayResponse<T> implements Serializable {
    private static final long serialVersionUID = -6120197778709732868L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 支付渠道
     */
    private String payChannel;
    /**
     * 支付中心订单号
     */
    private String orderNo;
    /**
     * 支付成功返回结果
     */
    private String result;

    public PayResponse() {
    }

    public PayResponse(String merchantNo, String payChannel, String orderNo, String result) {
        this.merchantNo = merchantNo;
        this.payChannel = payChannel;
        this.orderNo = orderNo;
        this.result = result;
    }
}
