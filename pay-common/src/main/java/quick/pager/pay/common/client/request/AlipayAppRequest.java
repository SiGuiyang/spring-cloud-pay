package quick.pager.pay.common.client.request;

import java.math.BigDecimal;

/**
 * app支付接口2.0
 */
public class AlipayAppRequest extends BaseAlipayRequest {

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

}
