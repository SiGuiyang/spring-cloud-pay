package quick.pager.pay.common.client.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 手机网站支付|h5支付
 * @author siguiyang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AlipayWapRequest extends BaseAlipayRequest {

    private static final long serialVersionUID = -4946720607358782059L;
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
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    private String timeoutExpress;
    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;
    /**
     * 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
     */
    private String productCode;
}
