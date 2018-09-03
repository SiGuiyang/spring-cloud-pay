package quick.pager.pay.common.client.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * app支付接口2.0
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayAppRequest extends BaseAlipayRequest {

    private static final long serialVersionUID = 6150004426267732234L;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等。
     */
    private String subject;
    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
     */
    private String body;
    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    private BigDecimal totalAmount;
    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

}
