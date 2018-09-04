package quick.pager.pay.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 支付响应对象
 *
 * @author siguiyang
 */
@Data
@Builder
@RequiredArgsConstructor
public class PayResponse<T> implements Serializable {
    private static final long serialVersionUID = -6120197778709732868L;
    /**
     * 商户号
     */
    @NonNull
    private String merchantNo;
    /**
     * 支付渠道
     */
    @NonNull
    private String payChannel;
    /**
     * 支付中心订单号
     */
    @NonNull
    private String orderNo;
    /**
     * 支付成功返回结果
     */
    private String result;

}
